package com.huike.task;

import com.huike.service.IRecoveryService;
import com.huike.service.ITbClueService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 定时任务回收资源
 * <p>
 * 每天6点执行回收任务
 */
@Component
@Slf4j
public class RecoveryTask {

    @Autowired
    private IRecoveryService recoveryService;

    @Resource
    private ITbClueService iTbClueService;

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


    @Scheduled(cron = "0 0/5 * * * ? ") // 每隔5分钟询问
    // @Scheduled(cron = "0/5 * * * * ? ") // 每隔5分钟询问
    public void sendMsg() {
        new Thread(() -> iTbClueService.selectTbClueByStatusAndLatest()).start();
        new Thread(() -> iTbClueService.selectTbBusinessByStatusAndLatest()).start();
    }

    @Scheduled(cron = "0 0/1 * * * ? ")
    public void sendMail() {
        log.info("开始发送数据报表邮件,{}", System.currentTimeMillis());
        new Thread(() -> iTbClueService.selectThisReportData()).start();
    }
}
