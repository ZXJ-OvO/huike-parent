package com.huike.aspectj;


import com.huike.common.annotation.PreAuthorize;
import com.huike.common.context.BaseContext;
import com.huike.domain.common.AjaxResult;
import com.huike.mapper.PreAuthorizeMapper;
import com.huike.page.TableDataInfo;
import com.huike.utils.StringUtils;
import lombok.SneakyThrows;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Aspect
@Component
public class PreAuthorizeAspect {
    @Resource
    private PreAuthorizeMapper preAuthorizeMapper;

    @SneakyThrows
    @Around("execution(* com.huike.controller.*.*.*(..))&&@annotation(com.huike.common.annotation.PreAuthorize)")
    public Object preAuthorize(ProceedingJoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        PreAuthorize annotation = signature.getMethod().getAnnotation(PreAuthorize.class);
        String value = annotation.value();

        Long currentId = BaseContext.getCurrentId();
        // 放行超级管理员
        if (currentId == 1) {
            return joinPoint.proceed();
        }

        Long count = preAuthorizeMapper.selectPerms(value, currentId);

        // 有权限
        if (count != null && count == 1) {
            return joinPoint.proceed();
        }

        // 没权限
        String msg = StringUtils.format("您没有数据的权限,前联系管理员添加权限,{}", "[" + value + "]");
        TableDataInfo tableDataInfo = new TableDataInfo();
        tableDataInfo.setCode(500);
        tableDataInfo.setMsg(msg);
        if (value.contains("list")) {
            return tableDataInfo;
        }

        return new AjaxResult(500, msg);
    }
}
