package com.shiyulong.common.exception;

import com.shiyulong.common.Result;
import com.shiyulong.common.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Desc: 异常处理器
 *
 * @author 石玉龙
 * @date 2021/12/23 19:03
 */
@Slf4j
@RestControllerAdvice
public class DemoExceptionHandler {

	/**
	 * 处理自定义异常
	 */
	@ExceptionHandler(DemoException.class)
	public Result<?> handleException(DemoException e) {
		log.error(e.getMessage(), e);
		return Result.error(e.getCode(), e.getMsg());
	}


	@ExceptionHandler(HttpMessageNotReadableException.class)
	public Result<?> handleException(HttpMessageNotReadableException e) {
		log.error(e.getMessage(), e);
		return Result.error(ResultCode.BASE_EXCEPTION.getCode(), e.getMessage());
	}


	@ExceptionHandler(Exception.class)
	public Result<?> handleException(Exception e) {
		log.error(e.getMessage(), e);
		return Result.error(ResultCode.BASE_EXCEPTION);
	}

}
