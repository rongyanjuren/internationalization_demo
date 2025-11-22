package com.shiyulong.common;

import lombok.Getter;

/**
 * @author 石玉龙
 * @create 2021-12-20 17:29
 * @description 错误码和错误信息定义类
 */
@Getter
public enum ResultCode {

    /**
     * 请求成功
     */
    SUCCESS(0, "成功","success"),

    /**
     * 未知异常
     */
    BASE_EXCEPTION(10002, "系统未知异常","base_exception"),


   ;

    private final int code;

    private final String msg;

    private final String i18nKey;

    ResultCode(int code, String msg, String i18nKey) {
        this.code = code;
        this.msg = msg;
        this.i18nKey = i18nKey;
    }

}
