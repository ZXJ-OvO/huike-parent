package com.huike.common.constant;

/**
 * 提示信息常量类
 */
public class MessageConstants {
    public static final String VERIFY_CODE_EMPTY = "验证码为空";
    public static final String VERIFY_CODE_ERROR = "验证码错误";
    public static final String LOGIN_USER_NOT_FOUND = "对不起，登录账号不存在";
    public static final String LOGIN_USER_HAS_DELETED = "对不起，登录账号已被删除";
    public static final String LOGIN_USER_HAS_DISABLE = "对不起，登录账号已被停用";
    public static final String LOGIN_USER_PASSWORD_ERROR = "对不起，您输入的密码有误";
    public static final String USER_PASSWORD_RETRY_LIMIT_COUNT = "密码输入错误{}次";
    public static final String USER_PASSWORD_RETRY_LIMIT_EXCEED = "密码输入错误{}次，帐户锁定10分钟";
    public static final String USER_ROLE_HAS_DISABLE = "角色已封禁，请联系管理员";
    public static final String LOGOUT_SUCCESS = "退出成功";
    public static final String LENGTH_NOT_VALID = "长度必须在{}到{}个字符之间";
    public static final String USER_USERNAME_NOT_VALID = "2到20个汉字、字母、数字或下划线组成，且必须以非数字开头";
    public static final String USER_PASSWORD_NOT_VALID = "5-50个字符";
    public static final String USER_EMAIL_NOT_VALID = "邮箱格式错误";
    public static final String USER_MOBILE_PHONE_NUMBER_NOT_VALID = "手机号格式错误";
    public static final String USER_LOGIN_SUCCESS = "登录成功";
    public static final String USER_NOTFOUND = "请重新登录";
    public static final String USER_FORCELOGOUT = "管理员强制退出，请重新登录";
    public static final String USER_UNKNOWN_ERROR = "未知错误，请重新登录";
    public static final String UPLOAD_EXCEED_MAXSIZE = "上传的文件大小超出限制的文件大小！<BR/>允许的文件最大大小是：{}MB！";
    public static final String UPLOAD_FILENAME_EXCEED_LENGTH = "上传的文件名最长{}个字符";
    public static final String NO_PERMISSION = "您没有数据的权限，请联系管理员添加权限 [{}]";
    public static final String NO_CREATE_PERMISSION = "您没有创建数据的权限，请联系管理员添加权限 [{}]";
    public static final String NO_UPDATE_PERMISSION = "您没有修改数据的权限，请联系管理员添加权限 [{}]";
    public static final String NO_DELETE_PERMISSION = "您没有删除数据的权限，请联系管理员添加权限 [{}]";
    public static final String NO_EXPORT_PERMISSION = "您没有导出数据的权限，请联系管理员添加权限 [{}]";
    public static final String NO_VIEW_PERMISSION = "您没有查看数据的权限，请联系管理员添加权限 [{}]";
    public static final String ACTIVITY_HAS_BEGIN_ERROR = "活动已经开始, 不可以修改活动信息";

}
