package com.itrjp.demo.result;

import org.slf4j.MDC;

/**
 * Result
 *
 * @author renjp
 * @date 2022/4/28 10:14
 */
public class Result<T> {
    private final String traceId = MDC.get("traceId");
    private int code;
    private String msg;
    private T body;

    protected Result(int code, String msg, T body) {
        this.code = code;
        this.msg = msg;
        this.body = body;
    }

    public static Result<Void> ok() {
        return new Result<>(200, "ok", null);
    }

    public static <T> Result<T> ok(T body) {
        return new Result<>(200, "ok", body);
    }

    public static Result<Void> error() {
        return new Result<>(-1, "error", null);
    }

    public static Result<Void> error(int code, String msg) {
        return new Result<>(code, msg, null);
    }

    public static Result<Void> error(String msg) {
        return new Result<>(-1, msg, null);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }

    public String getRequestId() {
        return traceId;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", body=" + body +
                ", traceId='" + traceId + '\'' +
                '}';
    }
}
