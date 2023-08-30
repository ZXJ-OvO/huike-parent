package com.huike.controller.clues;

import com.huike.common.annotation.Log;
import com.huike.common.annotation.PreAuthorize;
import com.huike.common.enums.BusinessType;
import com.huike.controller.core.BaseController;
import com.huike.domain.clues.TbClue;
import com.huike.domain.clues.TbClueTrackRecord;
import com.huike.domain.clues.vo.ClueTrackRecordVo;
import com.huike.domain.common.AjaxResult;
import com.huike.page.TableDataInfo;
import com.huike.service.ITbClueTrackRecordService;
import com.huike.utils.DateUtils;
import com.huike.web.CurrentUserHolder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 线索跟进记录Controller
 */
@Api(tags = "线索管理")
@RestController
@RequestMapping("/clues/record")
public class TbClueTrackRecordController extends BaseController {
    @Autowired
    private ITbClueTrackRecordService tbClueTrackRecordService;


    /**
     * 查询线索跟进记录列表
     */
    @ApiOperation("线索跟进记录-列表查询")
    @PreAuthorize("clues:record:list")
    @GetMapping("/list")
    public TableDataInfo list(@RequestParam("clueId") Long clueId) {
        startPage();
        List<TbClueTrackRecord> list = tbClueTrackRecordService.selectTbClueTrackRecordList(clueId);
        return getDataTable(list);
    }


    /**
     * 新增线索跟进记录
     */
    @ApiOperation("线索跟进记录-新增")
    @PreAuthorize("clues:record:add")
    @Log(title = "线索跟进记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ClueTrackRecordVo tbClueTrackRecord) {
        TbClueTrackRecord trackRecord = new TbClueTrackRecord();

        BeanUtils.copyProperties(tbClueTrackRecord, trackRecord);
        trackRecord.setCreateTime(DateUtils.getNowDate());
        trackRecord.setCreateBy(CurrentUserHolder.getUserName());

        TbClue tbClue = new TbClue();

        BeanUtils.copyProperties(tbClueTrackRecord, tbClue);
        tbClue.setStatus(TbClue.StatusType.FOLLOWING.getValue()); //进行中
        tbClue.setId(tbClueTrackRecord.getClueId());
        return toAjax(tbClueTrackRecordService.insertTbClueTrackRecord(tbClue, trackRecord));
    }
}
