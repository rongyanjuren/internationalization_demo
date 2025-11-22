package com.shiyulong.config;

import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

@RequiredArgsConstructor
public class HeaderLocaleResolver implements LocaleResolver {


	@Override
	public Locale resolveLocale(HttpServletRequest request) {
		String languageHeader = request.getHeader("Accept-Language");
		if (StringUtils.hasText(languageHeader)) {
			// 处理 zh-cn 格式
			if (languageHeader.equalsIgnoreCase("zh-cn")) {
				return Locale.SIMPLIFIED_CHINESE;
			} else if (languageHeader.equalsIgnoreCase("en")) {
				return Locale.US;
			}
		}
		return Locale.US;
	}

	@Override
	public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {
		throw new UnsupportedOperationException(
				"Cannot change HTTP header locale - use a different locale resolution strategy");
	}

}