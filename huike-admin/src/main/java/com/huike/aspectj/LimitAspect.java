package com.huike.aspectj;


import com.huike.common.annotation.LimitAccess;
import com.huike.common.exception.LimitException;
import com.huike.utils.StringUtils;
import com.huike.utils.redis.RedisCache;
import lombok.SneakyThrows;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author zxj
 * @mail zxjOvO@gmail.com
 * @date 2023/08/31 23:05
 */
@Component
@Aspect
public class LimitAspect {
    @Resource
    private RedisCache redisCache;

    @SneakyThrows
    @Before("execution(* com.huike.controller.*.*.*(..)) && @annotation(com.huike.common.annotation.LimitAccess)")
    public void LimitVisitCount(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        LimitAccess annotation = signature.getMethod().getAnnotation(LimitAccess.class);
        String count = annotation.count();
        String time = annotation.time();
        String name = "limit:access" + joinPoint.getTarget().getClass().getName() +
                ":" +
                signature.getMethod().getName();

        Integer c = redisCache.getCacheObject(name);
        if (c == null) {
            // 设置访问次数为1
            redisCache.setCacheObject(name, 1, Long.valueOf(time).intValue(), TimeUnit.SECONDS);
        } else {
            redisCache.increment(name, 1);
            if (c >= Integer.parseInt(count)) {
                throw new LimitException(StringUtils.format("访问次数超限，限制访问次数为{}次", count));
            }
        }

    }
}
