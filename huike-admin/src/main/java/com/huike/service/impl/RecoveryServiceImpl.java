package com.huike.service.impl;

import com.huike.mapper.RecoveryMapper;
import com.huike.service.IRecoveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class RecoveryServiceImpl implements IRecoveryService {

    @Autowired
    private RecoveryMapper mapper;

    private String Tbclue_RECOVERY = "3";

    private String TbBusiness_RECOVERY = "3";


    @Override
    public void recoveryBusiness() {
        mapper.resetNextTimeAndStatusOnBusiness(TbBusiness_RECOVERY, new Date());
    }

    @Override
    public void recoveryClue() {
        //回收线索
        mapper.resetNextTimeAndStatusOnClue(Tbclue_RECOVERY, new Date());
    }
}
