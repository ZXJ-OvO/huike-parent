package com.huike.controller.system;

import com.huike.common.annotation.Log;
import com.huike.common.annotation.PreAuthorize;
import com.huike.common.enums.BusinessType;
import com.huike.controller.core.BaseController;
import com.huike.domain.common.AjaxResult;
import com.huike.domain.system.SysDictData;
import com.huike.page.TableDataInfo;
import com.huike.service.ISysDictDataService;
import com.huike.service.ISysDictTypeService;
import com.huike.utils.StringUtils;
import com.huike.utils.poi.ExcelUtil;
import com.huike.web.CurrentUserHolder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据字典信息
 */
@Api(tags = "数据字典")
@RestController
@RequestMapping("/system/dict/data")
public class SysDictDataController extends BaseController {
    @Autowired
    private ISysDictDataService dictDataService;

    @Autowired
    private ISysDictTypeService dictTypeService;

    @ApiOperation("数据字典-数据-列表查询")
    @PreAuthorize("system:dict:list")
    @GetMapping("/list")
    public TableDataInfo list(SysDictData dictData) {
        startPage();
        List<SysDictData> list = dictDataService.selectDictDataList(dictData);
        return getDataTable(list);
    }

    @ApiOperation("数据字典-数据-导出")
    @Log(title = "字典数据", businessType = BusinessType.EXPORT)
    @PreAuthorize("system:dict:export")
    @GetMapping("/export")
    public AjaxResult export(SysDictData dictData) {
        List<SysDictData> list = dictDataService.selectDictDataList(dictData);
        ExcelUtil<SysDictData> util = new ExcelUtil<SysDictData>(SysDictData.class);
        return util.exportExcel(list, "字典数据");
    }

    /**
     * 查询字典数据详细
     */
    @ApiOperation("数据字典-数据-详情查询")
    @PreAuthorize("system:dict:query")
    @GetMapping(value = "/{dictCode}")
    public AjaxResult getInfo(@PathVariable Long dictCode) {
        return AjaxResult.success(dictDataService.selectDictDataById(dictCode));
    }

    /**
     * 根据字典类型查询字典数据信息
     */
    @ApiOperation("数据字典-数据-根据类型查询")
    @GetMapping(value = "/type/{dictType}")
    public AjaxResult dictType(@PathVariable String dictType) {
        List<SysDictData> data = dictTypeService.selectDictDataByType(dictType);
        if (StringUtils.isNull(data)) {
            data = new ArrayList<SysDictData>();
        }
        return AjaxResult.success(data);
    }

    /**
     * 新增字典数据
     */
    @ApiOperation("数据字典-数据-新增")
    @PreAuthorize("system:dict:add")
    @Log(title = "字典数据", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody SysDictData dict) {
        dict.setCreateBy(CurrentUserHolder.getUserName());
        dict.setUpdateBy(CurrentUserHolder.getUserName());
        return toAjax(dictDataService.insertDictData(dict));
    }

    /**
     * 修改保存字典数据
     */
    @ApiOperation("数据字典-数据-修改")
    @PreAuthorize("system:dict:edit")
    @Log(title = "字典数据", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody SysDictData dict) {
        dict.setUpdateBy(CurrentUserHolder.getUserName());
        return toAjax(dictDataService.updateDictData(dict));
    }

    /**
     * 删除字典数据
     */
    @ApiOperation("数据字典-数据-删除")
    @PreAuthorize("system:dict:remove")
    @Log(title = "字典类型", businessType = BusinessType.DELETE)
    @DeleteMapping("/{dictCodes}")
    public AjaxResult remove(@PathVariable Long[] dictCodes) {
        return toAjax(dictDataService.deleteDictDataByIds(dictCodes));
    }
}
