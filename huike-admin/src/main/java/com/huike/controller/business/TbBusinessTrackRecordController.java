package com.huike.controller.business;

import com.huike.common.annotation.Log;
import com.huike.common.annotation.PreAuthorize;
import com.huike.common.enums.BusinessType;
import com.huike.controller.core.BaseController;
import com.huike.domain.business.TbBusiness;
import com.huike.domain.business.TbBusinessTrackRecord;
import com.huike.domain.business.vo.BusinessTrackVo;
import com.huike.domain.common.AjaxResult;
import com.huike.service.ISysDictDataService;
import com.huike.service.ITbBusinessTrackRecordService;
import com.huike.utils.DateUtils;
import com.huike.web.CurrentUserHolder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商机跟进记录Controller
 */
@Api(tags = "商机管理")
@RestController
@RequestMapping("/business/record")
public class TbBusinessTrackRecordController extends BaseController {

    @Autowired
    private ITbBusinessTrackRecordService tbBusinessTrackRecordService;

    @Autowired
    private ISysDictDataService sysDictDataService;

    /**
     * 查询商机跟进记录列表
     */
    @ApiOperation("商机跟进记录-查询")
    @PreAuthorize("business:record:list")
    @GetMapping("/list")
    public AjaxResult list(@RequestParam("businessId") Long id) {
        List<TbBusinessTrackRecord> list = tbBusinessTrackRecordService.selectTbBusinessTrackRecordList(id);
        for (TbBusinessTrackRecord businessTrackRecord : list) {
            if (businessTrackRecord.getKeyItems() != null) {
                String[] items = businessTrackRecord.getKeyItems().split(",");
                for (String item : items) {
                    String dictLable = sysDictDataService.selectDictLabel("communication_point", item);
                    businessTrackRecord.getKeys().add(dictLable);
                }
            }
        }
        return AjaxResult.success(list);
    }


    /**
     * 新增商机跟进记录
     */
    @ApiOperation("商机跟进记录-新增")
    @PreAuthorize("business:record:add")
    @Log(title = "商机跟进记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BusinessTrackVo businessTrackVo) {
        System.out.println("-------" + businessTrackVo);
        TbBusinessTrackRecord trackRecord = new TbBusinessTrackRecord();
        BeanUtils.copyProperties(businessTrackVo, trackRecord);
        trackRecord.setCreateTime(DateUtils.getNowDate());
        trackRecord.setCreateBy(CurrentUserHolder.getUserName());

        TbBusiness business = new TbBusiness();
        BeanUtils.copyProperties(businessTrackVo, business);
        business.setStatus(TbBusiness.StatusType.FOLLOWING.getValue());
        business.setId(businessTrackVo.getBusinessId());
        return toAjax(tbBusinessTrackRecordService.insertTbBusinessTrackRecord(business, trackRecord));
    }


}
