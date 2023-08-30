package com.huike.task;

import com.huike.service.IRecoveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 定时任务回收资源
 * <p>
 * 每天6点执行回收任务
 */
@Component
public class RecoveryTask {

    @Autowired
    private IRecoveryService recoveryService;

    /**
     * 回收线索
     * 生产环境 每天6点执行       0 0 12 * * ?
     * 测试环境 每10秒执行一次 0/10 * * * * ?
     */
    @Scheduled(cron = "0 0 6 * * ?")//每天早上6点执行
    public void recoveryClue() {
        recoveryService.recoveryClue();
    }

    /**
     * 回收商机
     * 生产环境 每天6点执行      0 0 12 * * ?
     * 测试环境 每10秒执行一次 0/10 * * * * ?
     */
    @Scheduled(cron = "0 0 6 * * ?")//每天早上6点执行
    public void recoveryBusiness() {
        recoveryService.recoveryBusiness();
    }

}
