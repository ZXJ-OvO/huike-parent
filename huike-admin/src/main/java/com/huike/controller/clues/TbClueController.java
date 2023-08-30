package com.huike.controller.clues;

import com.alibaba.excel.EasyExcel;
import com.huike.common.annotation.Log;
import com.huike.common.annotation.PreAuthorize;
import com.huike.common.enums.BusinessType;
import com.huike.controller.core.BaseController;
import com.huike.domain.clues.TbClue;
import com.huike.domain.clues.vo.AssignmentVo;
import com.huike.domain.clues.vo.FalseClueVo;
import com.huike.domain.clues.vo.TbClueExcelVo;
import com.huike.domain.common.AjaxResult;
import com.huike.excel.ExcelListener;
import com.huike.page.TableDataInfo;
import com.huike.service.ITbActivityService;
import com.huike.service.ITbBusinessService;
import com.huike.service.ITbClueService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 线索管理Controller
 */
@Api(tags = "线索管理")
@RestController
@RequestMapping("/clues/clue")
public class TbClueController extends BaseController {
    @Autowired
    private ITbClueService tbClueService;

    @Autowired
    private ITbBusinessService tbBusinessService;

    @Autowired
    private ITbActivityService activityService;

    /**
     * 查询线索管理列表
     */
    @ApiOperation("线索-列表查询")
    @PreAuthorize("clues:clue:list")
    @GetMapping("/list")
    public TableDataInfo list(TbClue tbClue) {
        List<TbClue> list = tbClueService.selectTbClueList(tbClue);
        return getDataTablePage(list);
    }

    /**
     * 查询线索池
     *
     * @param tbClue
     * @return
     */
    @ApiOperation("线索-查询线索池")
    @PreAuthorize("clues:clue:pool")
    @GetMapping("/pool")
    public TableDataInfo pool(TbClue tbClue) {
        startPage();
        List<TbClue> list = tbClueService.selectTbCluePool(tbClue);
        return getDataTable(list);
    }

    /**
     * 获取线索管理详细信息
     */
    @ApiOperation("线索-详情查询")
    @PreAuthorize("clues:clue:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(tbClueService.selectTbClueById(id));
    }

    /**
     * 新增线索管理
     */
    @ApiOperation("线索-新增")
    @PreAuthorize("clues:clue:add")
    @Log(title = "线索管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TbClue tbClue) {
        if (!tbClueService.checkCluePhoneExis(tbClue.getPhone())) {
            return error("手机号已存在");
        }
        return toAjax(tbClueService.insertTbClue(tbClue));
    }

    /**
     * 修改线索管理
     */
    @ApiOperation("线索-修改")
    @PreAuthorize("clues:clue:edit")
    @Log(title = "线索管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TbClue tbClue) {
        return toAjax(tbClueService.updateTbClue(tbClue));
    }

    /**
     * 线索转商机
     *
     * @param id
     * @return
     */
    @ApiOperation("线索-转商机")
    @PreAuthorize("clues:clue:changeBusiness")
    @Log(title = "线索转商机", businessType = BusinessType.UPDATE)
    @PutMapping("/changeBusiness/{id}")
    public AjaxResult changeBusiness(@PathVariable Long id) {
        return toAjax(tbBusinessService.changeBusiness(id));
    }

    /**
     * 伪线索
     *
     * @param id
     * @param falseClueVo
     * @return
     */
    @ApiOperation("线索-伪线索")
    @PreAuthorize("clues:clue:false")
    @Log(title = "伪线索", businessType = BusinessType.UPDATE)
    @PutMapping("/false/{id}")
    public AjaxResult cluesFalse(@PathVariable Long id, @RequestBody FalseClueVo falseClueVo) {
        return toAjax(tbClueService.falseClue(id, falseClueVo.getReason(), falseClueVo.getRemark()));
    }

    /**
     * 批量分配
     *
     * @param assignmentVo
     * @return
     */
    @ApiOperation("线索-批量分配")
    @PreAuthorize("clues:clue:assignment")
    @Log(title = "批量分配", businessType = BusinessType.UPDATE)
    @PutMapping("/assignment")
    public AjaxResult assignment(@RequestBody AssignmentVo assignmentVo) {
        return AjaxResult.success(tbClueService.assign(assignmentVo.getIds(), assignmentVo.getUserId()));
    }

    /**
     * 批量捞取
     *
     * @param assignmentVo
     * @return
     */
    @ApiOperation("线索-批量捞取")
    @PreAuthorize("clues:clue:gain")
    @Log(title = "批量捞取", businessType = BusinessType.UPDATE)
    @PutMapping("/gain")
    public AjaxResult gain(@RequestBody AssignmentVo assignmentVo) {
        return AjaxResult.success(tbClueService.gain(assignmentVo.getIds(), assignmentVo.getUserId()));
    }

    /**
     * 上传线索
     *
     * @param file
     * @return
     * @throws Exception
     */
    @ApiOperation("线索-上传(导入)")
    @Log(title = "上传线索", businessType = BusinessType.IMPORT)
    @PostMapping("/importData")
    public AjaxResult importData(MultipartFile file) throws Exception {
        ExcelListener excelListener = new ExcelListener(tbClueService);
        EasyExcel.read(file.getInputStream(), TbClueExcelVo.class, excelListener).sheet().doRead();
        return AjaxResult.success(excelListener.getResult());
    }
}
