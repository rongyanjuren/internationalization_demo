package com.shiyulong.common;

import com.shiyulong.utils.I18nUtil;
import lombok.Data;

import java.time.ZonedDateTime;

/**
 *
 * @author shiyulong
 * @param <T>
 */
@Data
public class Result<T> {

    /**
     * 返回代码
     */
    private int code;

    /**
     * 返回信息
     */
    private String msg;

    /**
     * 返回结果
     */
    private T data;

    /**
     * 时间戳
     */
    private ZonedDateTime time = ZonedDateTime.now();


    public static Result<?> error() {
        return error(ResultCode.BASE_EXCEPTION);
    }

    public static<T> Result<T> error(String msg) {
        return error(ResultCode.BASE_EXCEPTION.getCode(), msg);
    }

    public static<T> Result<T> error(int code, String msg) {
        Result<T> r = new Result<>();
        r.setCode(code);
        r.setMsg(msg);
        return r;
    }


    public static<T> Result<T> ok(T data) {
        Result<T> r = new Result<>();
        r.setCode(ResultCode.SUCCESS.getCode());
        r.setData(data);
        return r;
    }
    public static<T> Result<T> ok() {
        return new Result<T>();
    }

    public static<T> Result<T> error(ResultCode resultCode) {
        Result<T> r = new Result<>();
        r.setCode(resultCode.getCode());
        r.setMsg(I18nUtil.get(resultCode.getI18nKey()));
        return r;
    }

}
