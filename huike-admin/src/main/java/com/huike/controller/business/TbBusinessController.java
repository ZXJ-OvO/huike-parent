package com.huike.controller.business;

import com.huike.common.annotation.Log;
import com.huike.common.annotation.PreAuthorize;
import com.huike.common.enums.BusinessType;
import com.huike.controller.core.BaseController;
import com.huike.domain.business.TbBusiness;
import com.huike.domain.clues.vo.AssignmentVo;
import com.huike.domain.common.AjaxResult;
import com.huike.page.TableDataInfo;
import com.huike.service.ITbBusinessService;
import com.huike.service.ITbClueService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 商机Controller
 */
@Api(tags = "商机管理")
@RestController
@RequestMapping("/business")
public class TbBusinessController extends BaseController {

    @Autowired
    private ITbBusinessService tbBusinessService;

    @Autowired
    private ITbClueService tbClueService;

    /**
     * 查询商机列表
     */
    @ApiOperation("商机-查询列表")
    @PreAuthorize("business:business:list")
    @GetMapping("/list")
    public TableDataInfo list(TbBusiness tbBusiness, HttpServletRequest req) {
        startPage();
        List<TbBusiness> list = tbBusinessService.selectTbBusinessList(tbBusiness);
        return getDataTable(list);
    }


    @ApiOperation("商机-查询公海池")
    @PreAuthorize("business:business:pool")
    @GetMapping("/pool")
    public TableDataInfo pool(TbBusiness tbBusiness, HttpServletRequest req) {
        startPage();
        List<TbBusiness> list = tbBusinessService.selectTbBusinessPool(tbBusiness);
        return getDataTable(list);
    }


    /**
     * 获取商机详细信息
     */
    @ApiOperation("商机-获取详细信息")
    @PreAuthorize("business:business:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(tbBusinessService.selectTbBusinessById(id));
    }

    /**
     * 新增商机
     */
    @ApiOperation("商机-新增")
    @PreAuthorize("business:business:add")
    @Log(title = "商机", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TbBusiness tbBusiness) {
        if (!tbClueService.checkCluePhoneExis(tbBusiness.getPhone()) || !tbBusinessService.checkBusinessPhoneExis(tbBusiness.getPhone())) {
            return error("手机号已存在");
        }
        return toAjax(tbBusinessService.insertTbBusiness(tbBusiness));
    }

    /**
     * 修改商机
     */
    @ApiOperation("商机-修改")
    @PreAuthorize("business:business:edit")
    @Log(title = "商机", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TbBusiness tbBusiness) {
        return toAjax(tbBusinessService.updateTbBusiness(tbBusiness));
    }

    /**
     * 删除商机
     */
    @ApiOperation("商机-删除")
    @PreAuthorize("business:business:remove")
    @Log(title = "商机", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(tbBusinessService.deleteTbBusinessByIds(ids));
    }

    /**
     * 商机分配
     *
     * @param assignmentVo
     * @return
     */
    @ApiOperation("商机-分配")
    @PreAuthorize("business:business:assignment")
    @Log(title = "批量分配", businessType = BusinessType.UPDATE)
    @PutMapping("/assignment")
    public AjaxResult assignment(@RequestBody AssignmentVo assignmentVo) {
        return AjaxResult.success(tbBusinessService.assign(assignmentVo.getIds(), assignmentVo.getUserId()));
    }

    /**
     * 批量捞取
     */
    @ApiOperation("商机-批量捞取")
    @PreAuthorize("business:business:gain")
    @Log(title = "批量捞取", businessType = BusinessType.UPDATE)
    @PutMapping("/gain")
    public AjaxResult gain(@RequestBody AssignmentVo assignmentVo) {
        return AjaxResult.success(tbBusinessService.gain(assignmentVo.getIds(), assignmentVo.getUserId()));
    }


    /**
     * 退回公海
     *
     * @param id
     * @param reason
     * @return
     */
    @ApiOperation("商机-退回公海")
    @PreAuthorize("business:business:back")
    @Log(title = "退回公海", businessType = BusinessType.UPDATE)
    @PutMapping("/back/{id}/{reason}")
    public AjaxResult back(@PathVariable("id") Long id, @PathVariable("reason") String reason) {
        return AjaxResult.success(tbBusinessService.backPool(id, reason));
    }

}
