package com.huike.service;

import com.huike.domain.contract.vo.TransferVo;
import com.huike.domain.system.SysUser;

import java.util.List;
import java.util.Map;

public interface ITransferService {

    public List<TransferVo> selectTransferList(SysUser user);


    public Map<String, Object> assignment(String type, Long userId, Long transferUserId);
}
