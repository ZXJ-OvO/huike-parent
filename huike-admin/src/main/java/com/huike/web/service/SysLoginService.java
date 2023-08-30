package com.huike.web.service;

import com.huike.common.constant.Constants;
import com.huike.common.constant.MessageConstants;
import com.huike.common.enums.UserStatus;
import com.huike.common.exception.CustomException;
import com.huike.domain.system.SysUser;
import com.huike.domain.system.dto.LoginUser;
import com.huike.mapper.SysUserMapper;
import com.huike.utils.BcryptUtils;
import com.huike.utils.StringUtils;
import com.huike.utils.redis.RedisCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 登录校验方法
 */
@Slf4j
@Service
public class SysLoginService {

    @Autowired
    private SysUserMapper userMapper;

    @Autowired
    private SysPermissionService permissionService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private RedisCache redisCache;

    /**
     * 登录验证
     *
     * @param username 用户名
     * @param password 密码
     * @param code     验证码
     * @param uuid     唯一标识
     * @return 结果
     */
    public String login(String username, String password, String code, String uuid) {
        //1. 获取验证码
        String verifyKey = Constants.CAPTCHA_CODE_KEY + uuid;
        String captcha = redisCache.getCacheObject(verifyKey);
        redisCache.deleteObject(verifyKey);

        //2. 校验验证码
        if (captcha == null) { //验证码为空
            log.info("登录用户：验证码为空.");
            throw new CustomException(MessageConstants.VERIFY_CODE_EMPTY);
        }

        if (!code.equalsIgnoreCase(captcha)) { //验证码错误
            log.info("登录用户：验证码错误.");
            throw new CustomException(MessageConstants.VERIFY_CODE_ERROR);
        }

        //3. 根据用户名查询用户基本信息
        SysUser sysUser = userMapper.selectUserByUserName(username);

        //4. 校验用户状态 及 密码
        if (StringUtils.isNull(sysUser)) { //账号不存在
            log.info("登录用户：{} 不存在.", username);
            throw new CustomException(MessageConstants.LOGIN_USER_NOT_FOUND);
        } else if (UserStatus.DELETED.getCode().equals(sysUser.getDelFlag())) { //账号被删除
            log.info("登录用户：{} 已被删除.", username);
            throw new CustomException(MessageConstants.LOGIN_USER_HAS_DELETED);
        } else if (UserStatus.DISABLE.getCode().equals(sysUser.getStatus())) { //账号被停用
            log.info("登录用户：{} 已被停用.", username);
            throw new CustomException(MessageConstants.LOGIN_USER_HAS_DISABLE);
        } else if (!BcryptUtils.checkPassword(password, sysUser.getPassword())) { //账号密码有误
            log.info("登录用户: {}, 输入的密码 {} 有误", username, password);
            throw new CustomException(MessageConstants.LOGIN_USER_PASSWORD_ERROR);
        }

        //5. 构建用户信息-包含权限数据
        LoginUser loginUser = createLoginUser(sysUser);

        //6. 生成token
        return tokenService.createToken(loginUser);
    }

    private LoginUser createLoginUser(SysUser user) {
        return new LoginUser(user, permissionService.getMenuPermission(user)); //这部分是在查询权限信息
    }
}
