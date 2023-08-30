package com.huike.web;

import com.huike.common.constant.HttpStatus;
import com.huike.common.exception.CustomException;
import com.huike.utils.ServletUtils;
import com.huike.utils.spring.SpringUtils;
import com.huike.web.service.TokenService;

public class CurrentUserHolder {

    /**
     * 获取当前登录用户的用户ID
     *
     * @return
     */
    public static Long getUserId() {
        try {
            TokenService tokenService = SpringUtils.getBean(TokenService.class);
            return tokenService.getLoginUser(ServletUtils.getRequest()).getUser().getUserId();
        } catch (Exception e) {
            throw new CustomException("获取用户账户异常", HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * 获取当前登录用户的用户名
     *
     * @return
     */
    public static String getUserName() {
        try {
            TokenService tokenService = SpringUtils.getBean(TokenService.class);
            return tokenService.getLoginUser(ServletUtils.getRequest()).getUser().getUserName();
        } catch (Exception e) {
            throw new CustomException("获取用户账户异常", HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * 获取当前登录用户的部门ID
     *
     * @return
     */
    public static Long getDeptId() {
        try {
            TokenService tokenService = SpringUtils.getBean(TokenService.class);
            return tokenService.getLoginUser(ServletUtils.getRequest()).getUser().getDeptId();
        } catch (Exception e) {
            throw new CustomException("获取用户账户异常", HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * 获取当前登录用户的昵称
     *
     * @return
     */
    public static String getRealName() {
        try {
            TokenService tokenService = SpringUtils.getBean(TokenService.class);
            return tokenService.getLoginUser(ServletUtils.getRequest()).getUser().getRealName();
        } catch (Exception e) {
            throw new CustomException("获取用户账户异常", HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * 是否为管理员
     *
     * @param userId 用户ID
     * @return 结果
     */
    public static boolean isAdmin(Long userId) {
        return userId != null && 1L == userId;
    }

    /**
     * 获取管理员工的用户ID
     *
     * @return
     */
    public static Long getAdmin() {
        return 1L;
    }

}
