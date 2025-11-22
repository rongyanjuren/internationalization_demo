package com.shiyulong.common.exception;

import com.shiyulong.common.ResultCode;
import com.shiyulong.utils.I18nUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 自定义异常
 *
 * @author 石玉龙
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class DemoException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final String msg;

    private final int code;

    public DemoException(String msg) {
        super(I18nUtil.get(msg));
        this.msg = I18nUtil.get(msg);
        this.code = ResultCode.BASE_EXCEPTION.getCode();
    }

    public DemoException(String msg, Throwable e) {
        super(I18nUtil.get(msg), e);
        this.msg = I18nUtil.get(msg);
        this.code = ResultCode.BASE_EXCEPTION.getCode();
    }

    public DemoException(int code, String msg) {
        super(I18nUtil.get(msg));
        this.msg = I18nUtil.get(msg);
        this.code = code;
    }

    public DemoException(int code, String msg, Throwable e) {
        super(I18nUtil.get(msg), e);
        this.msg = I18nUtil.get(msg);
        this.code = code;
    }

    public DemoException(ResultCode resultCode) {
        super(I18nUtil.get(resultCode.getI18nKey()));
        this.msg = I18nUtil.get(resultCode.getI18nKey());
        this.code = resultCode.getCode();
    }
}
