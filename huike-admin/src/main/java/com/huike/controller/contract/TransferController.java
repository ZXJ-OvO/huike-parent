package com.huike.controller.contract;

import com.huike.common.annotation.Log;
import com.huike.common.annotation.PreAuthorize;
import com.huike.common.enums.BusinessType;
import com.huike.controller.core.BaseController;
import com.huike.domain.common.AjaxResult;
import com.huike.domain.contract.vo.TransferVo;
import com.huike.domain.system.SysUser;
import com.huike.page.TableDataInfo;
import com.huike.service.ITransferService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "合同管理")
@RestController
@RequestMapping("/transfer")
public class TransferController extends BaseController {

    @Autowired
    ITransferService transferService;

    /**
     * 获取转派列表
     */
    @ApiOperation("合同-获取转派列表")
    @PreAuthorize("transfer:transfer:list")
    @GetMapping("/list")
    public TableDataInfo list(SysUser user) {
        List<TransferVo> list = transferService.selectTransferList(user);
        return getDataTablePage(list);
    }

    /**
     * 转派处理
     *
     * @param type
     * @param userId
     * @param transferUserId
     * @return
     */
    @ApiOperation("合同-转派处理")
    @PreAuthorize("transfer:transfer:assignment")
    @Log(title = "转派处理", businessType = BusinessType.UPDATE)
    @PutMapping("/assignment/{type}/{userId}/{transferUserId}")
    public AjaxResult assignment(@PathVariable("type") String type, @PathVariable("userId") Long userId, @PathVariable("transferUserId") Long transferUserId) {
        return AjaxResult.success(transferService.assignment(type, userId, transferUserId));
    }
}
