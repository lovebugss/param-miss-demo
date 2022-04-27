package com.itrjp.demo.exception;

/**
 * 业务异常
 *
 * @author renjp
 * @date 2022/4/27 22:52
 */
public class BizException extends RuntimeException {
    private final int code;
    private final String msg;

    public BizException(int code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
