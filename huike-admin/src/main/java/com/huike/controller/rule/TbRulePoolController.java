package com.huike.controller.rule;

import com.huike.common.annotation.Log;
import com.huike.common.enums.BusinessType;
import com.huike.controller.core.BaseController;
import com.huike.domain.clues.TbRulePool;
import com.huike.domain.common.AjaxResult;
import com.huike.service.ITbRulePoolService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 线索/商机规则Controller
 */
@Api(tags = "系统管理")
@RestController
@RequestMapping("/rule/pool")
public class TbRulePoolController extends BaseController {
    @Autowired
    private ITbRulePoolService tbRulePoolService;

    /**
     * 查询线索池规则列表
     */
    @ApiOperation("线索/商机规则管理-根据类型查询")
    @GetMapping("/{type}")
    public AjaxResult getInfo(@PathVariable("type") String type) {
        return AjaxResult.success(tbRulePoolService.selectTbRulePoolByType(type));
    }


    /**
     * 新增线索池规则
     */
    @ApiOperation("线索/商机规则管理-新增")
    @Log(title = "线索池规则", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TbRulePool tbRulePool) {
        return toAjax(tbRulePoolService.insertTbRulePool(tbRulePool));
    }

    /**
     * 修改线索池规则
     */
    @ApiOperation("线索/商机规则管理-修改")
    @Log(title = "线索池规则", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TbRulePool tbRulePool) {
        return toAjax(tbRulePoolService.updateTbRulePool(tbRulePool));
    }


}
