package com.huike.controller.contract;

import com.huike.common.annotation.Log;
import com.huike.common.annotation.PreAuthorize;
import com.huike.common.enums.BusinessType;
import com.huike.controller.core.BaseController;
import com.huike.domain.common.AjaxResult;
import com.huike.domain.contract.TbContract;
import com.huike.page.TableDataInfo;
import com.huike.service.ITbClueService;
import com.huike.service.ITbContractService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 合同Controller
 */
@Api(tags = "合同管理")
@RestController
@RequestMapping("/contract")
public class TbContractController extends BaseController {
    @Autowired
    private ITbContractService tbContractService;

    @Autowired
    private ITbClueService tbClueService;


    /**
     * 商机转合同
     *
     * @param id
     * @param tbContract
     * @return
     */
    @ApiOperation("商机转合同")
    @PreAuthorize("contract:contract:change")
    @Log(title = "商机转合同", businessType = BusinessType.UPDATE)
    @PutMapping("/changeContract/{id}")
    public AjaxResult changeContract(@PathVariable Long id, @RequestBody TbContract tbContract) {
        return toAjax(tbContractService.changeContract(id, tbContract));
    }

    /**
     * 查询合同列表
     */
    @ApiOperation("合同-列表查询")
    @PreAuthorize("contract:contract:list")
    @GetMapping("/list")
    public TableDataInfo list(TbContract tbContract) {
        List<TbContract> contractList = tbContractService.selectTbContract(tbContract);
        return getDataTablePage(contractList);
    }


    /**
     * 获取合同详细信息
     */
  /*  @ApiOperation("合同-详情查询")
    @PreAuthorize("contract:contract:detail")
    @GetMapping(value = "/detail/{id}")
    public AjaxResult detail(@PathVariable("id") Long id) {
        return AjaxResult.success(tbContractService.selectTbContractById(id));
    }*/

    /**
     * 获取合同详细信息
     */
    @ApiOperation("合同-详情查询")
    @PreAuthorize("contract:contract:detail")
    @GetMapping(value = "/{id}")
    public AjaxResult detailById(@PathVariable("id") Long id) {
        return AjaxResult.success(tbContractService.selectTbContractById(id));
    }


    /**
     * 新增合同
     */
    @ApiOperation("合同-新增")
    @PreAuthorize("contract:contract:add")
    @Log(title = "合同", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TbContract tbContract) {
        if (!tbClueService.checkCluePhoneExis(tbContract.getPhone())) return error("手机号已存在");
        return toAjax(tbContractService.insertTbContract(tbContract));
    }


    /**
     * 修改合同
     */
    @ApiOperation("合同-修改")
    @PreAuthorize("contract:contract:edit")
    @Log(title = "合同", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TbContract tbContract) {
        return toAjax(tbContractService.updateTbContract(tbContract));
    }

}
