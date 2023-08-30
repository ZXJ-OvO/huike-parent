package com.huike.controller.clues;

import com.huike.common.annotation.Log;
import com.huike.common.annotation.PreAuthorize;
import com.huike.common.enums.BusinessType;
import com.huike.controller.core.BaseController;
import com.huike.domain.clues.TbCourse;
import com.huike.domain.common.AjaxResult;
import com.huike.page.TableDataInfo;
import com.huike.service.ITbCourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 课程管理Controller
 */
@Api(tags = "课程管理")
@RestController
@RequestMapping("/clues/course")
public class TbCourseController extends BaseController {
    @Autowired
    private ITbCourseService tbCourseService;

    /**
     * 查询课程管理列表
     */
    @ApiOperation("课程-列表查询")
    @PreAuthorize("clues:course:list")
    @GetMapping("/list")
    public TableDataInfo list(TbCourse tbCourse) {
        startPage();
        List<TbCourse> list = tbCourseService.selectTbCourseList(tbCourse);
        return getDataTable(list);
    }


    /**
     * @param subject
     * @return
     * @ApiOperation("课程下拉列表")
     */
    @ApiOperation("课程-根据学科查询(下拉列表)")
    @GetMapping("/listselect")
    public AjaxResult list(String subject) {
        TbCourse query = new TbCourse();
        if (subject != null) {
            query.setSubject(subject);
        }
        return AjaxResult.success(tbCourseService.selectTbCourseList(query));
    }

    /**
     * 获取课程管理详细信息
     */
    @ApiOperation("课程-详情查询")
    @PreAuthorize("clues:course:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(tbCourseService.selectTbCourseById(id));
    }

    /**
     * 新增课程管理
     */
    @ApiOperation("课程-新增")
    @PreAuthorize("clues:course:add")
    @Log(title = "课程管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TbCourse tbCourse) {
        return toAjax(tbCourseService.insertTbCourse(tbCourse));
    }

    /**
     * 修改课程管理
     */
    @ApiOperation("课程-修改")
    @PreAuthorize("clues:course:edit")
    @Log(title = "课程管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TbCourse tbCourse) {
        return toAjax(tbCourseService.updateTbCourse(tbCourse));
    }

    /**
     * 删除课程管理
     */
    @ApiOperation("课程-删除")
    @PreAuthorize("clues:course:remove")
    @Log(title = "课程管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(tbCourseService.deleteTbCourseByIds(ids));
    }
}
