package com.huike.aspectj;

import com.alibaba.fastjson.JSON;
import com.huike.common.annotation.Log;
import com.huike.domain.system.SysOperLog;
import com.huike.domain.system.dto.LoginUser;
import com.huike.service.ISysOperLogService;
import com.huike.utils.ip.AddressUtils;
import com.huike.utils.ip.IpUtils;
import com.huike.web.service.TokenService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Aspect
@Slf4j
@Component
public class SystemOperateLogAspect {

    @Autowired
    private ISysOperLogService sysOperLogService;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private TokenService tokenService;

    /**
     * 环绕通知 日志记录-错误情况
     */
    @SneakyThrows
    @AfterThrowing(value = "execution(* com.huike.controller..*.*(..)) && @annotation(log)", throwing = "throwable")
    public void logRecorder(JoinPoint jp, Log log, Throwable throwable) {
        insertOperlog(jp, log, null, throwable);

    }

    /**
     * 环绕通知 日志记录-异常情况
     */
    @SneakyThrows
    @AfterReturning(value = "execution(* com.huike.controller..*.*(..)) && @annotation(log)", returning = "result")
    public void logRecorder(JoinPoint jp, Log log, Object result) {
        insertOperlog(jp, log, result, null);
    }


    /**
     * 抽离成公共方法
     */
    private void insertOperlog(JoinPoint jp, Log log, Object result, Throwable throwable) {
        // 获取当前登录用户
        LoginUser loginUser = tokenService.getLoginUser(request);

        SysOperLog sysOperLog = new SysOperLog();

        // 方法存在返回值，说明方法正常运行
        if (result != null) {
            sysOperLog.setStatus(0);
            sysOperLog.setJsonResult(JSON.toJSONString(result)); //方法执行结果
        }
        // 方法抛出了异常，异常被注解@AfterThrowing捕获
        if (throwable != null) {
            sysOperLog.setStatus(1);
            sysOperLog.setErrorMsg(throwable.getMessage());
        }


        sysOperLog.setTitle(log.title());
        sysOperLog.setBusinessType(log.businessType().ordinal());
        sysOperLog.setMethod(jp.getTarget().getClass().getName() + "." + jp.getSignature().getName()); //类.方法
        sysOperLog.setRequestMethod(request.getMethod()); //请求方式
        sysOperLog.setOperatorType(log.operatorType().ordinal());
        sysOperLog.setOperName(loginUser.getUser().getRealName());
        sysOperLog.setDeptName(loginUser.getUser().getDept().getDeptName());
        sysOperLog.setOperIp(IpUtils.getIpAddr(request));//ip地址
        sysOperLog.setOperLocation(AddressUtils.getRealAddressByIP(IpUtils.getIpAddr(request))); //地址
        sysOperLog.setOperParam(JSON.toJSONString(jp.getArgs()));//请求参数
        sysOperLog.setOperTime(new Date());
        sysOperLog.setOperUrl(request.getRequestURI());

        // 开启新线程执行插入操作
/*        new Thread(new Runnable() {
            @Override
            public void run() {
                sysOperLogService.insertOperlog(sysOperLog);
            }
        });*/

        //使用Spring异步线程池执行插入操作
        sysOperLogService.insertOperlog(sysOperLog);

    }
}
