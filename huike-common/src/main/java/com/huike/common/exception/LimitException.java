package com.huike.common.exception;


/**
 * @author zxj
 * @mail zxjOvO@gmail.com
 * @date 2023/08/31 23:20
 */
public class LimitException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private Integer code;

    private String message;

    public LimitException(String message) {
        this.message = message;
    }

    public LimitException(String message, Integer code) {
        this.message = message;
        this.code = code;
    }

    public LimitException(String message, Throwable e) {
        super(message, e);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public Integer getCode() {
        return code;
    }
}


