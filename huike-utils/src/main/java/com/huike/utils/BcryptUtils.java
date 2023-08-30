package com.huike.utils;


import cn.hutool.crypto.digest.BCrypt;

/**
 * @author zxj
 */
public class BcryptUtils {

    /**
     * 对明文密码进行Bcrypt加密
     *
     * @param passwordText 明文密码
     * @return
     */
    public static String hashPassword(String passwordText) {
        return BCrypt.hashpw(passwordText, BCrypt.gensalt());
    }

    /**
     * Bcrypt加密密码校验
     *
     * @param passwordText   明文
     * @param hashedPassword 密文
     * @return
     */
    public static boolean checkPassword(String passwordText, String hashedPassword) {
        return BCrypt.checkpw(passwordText, hashedPassword);
    }

}
