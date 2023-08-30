package com.huike.controller.clues;

import com.huike.common.annotation.Log;
import com.huike.common.annotation.PreAuthorize;
import com.huike.common.enums.BusinessType;
import com.huike.controller.core.BaseController;
import com.huike.domain.clues.TbActivity;
import com.huike.domain.common.AjaxResult;
import com.huike.page.TableDataInfo;
import com.huike.service.ITbActivityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * 活动管理Controller
 */
@Api(tags = "活动管理")
@RestController
@RequestMapping("/clues/activity")
public class TbActivityController extends BaseController {
    @Autowired
    private ITbActivityService tbActivityService;

    /**
     * 查询活动管理列表
     */
    @ApiOperation("活动-列表查询")
    @PreAuthorize("clues:activity:list")
    @GetMapping("/list")
    public TableDataInfo list(TbActivity tbActivity) {
        startPage();
        List<TbActivity> list = tbActivityService.selectTbActivityList(tbActivity);
        return getDataTable(list);
    }


    /**
     * 获取渠道下活动
     *
     * @param channel
     * @return
     */
    @ApiOperation("活动-根据渠道查询")
    @GetMapping("/listselect/{channel}")
    public AjaxResult list(@PathVariable("channel") String channel) {
        TbActivity tbActivity = new TbActivity();
        tbActivity.setChannel(channel);
        tbActivity.setParams(new HashMap() {
            {
                put("now", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            }
        });
        return AjaxResult.success(tbActivityService.selectTbActivityList(tbActivity));
    }

    /**
     * 获取活动管理详细信息
     */
    @ApiOperation("活动-详细查询")
    @PreAuthorize("clues:activity:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(tbActivityService.selectTbActivityById(id));
    }

    /**
     * 新增活动管理
     */
    @ApiOperation("活动-新增")
    @PreAuthorize("clues:activity:add")
    @Log(title = "活动管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TbActivity tbActivity) {
        return toAjax(tbActivityService.insertTbActivity(tbActivity));
    }


    /**
     * 修改活动管理
     */
    @ApiOperation("活动-修改")
    @PreAuthorize("clues:activity:edit")
    @Log(title = "活动管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TbActivity tbActivity) {
        return toAjax(tbActivityService.updateTbActivity(tbActivity));
    }

    /**
     * 删除活动管理
     */
    @ApiOperation("活动-删除")
    @PreAuthorize("clues:activity:remove")
    @Log(title = "活动管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(tbActivityService.deleteTbActivityByIds(ids));
    }
}
