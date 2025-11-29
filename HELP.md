# springboot的国际化方案&#x20;

不多BB，直接上代码[代码地址](https://github.com/rongyanjuren/internationalization_demo)

## 指定处理的请求头

用Accept-Language作为请求头，如果请求头的值为zh-cn则为中文，en则为英文，如果没有值则默认英文

```java
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
```

## 在properties文件中指定多语言文件的位置

    spring.messages.basename=i18n.messages

## 在指定文件夹下创建多语言文件

    messages.properties
    messages_en_US.properties
    messages_zh_CN.properties

### 注意事项

创建的文件名称要和Locale类的值对应，例如
进入`Locale`类中，发现
`static public final Locale SIMPLIFIED_CHINESE = createConstant("zh", "CN");`
由于在properties中已经指定了文件名称为messages，则SIMPLIFIED_CHINESE（简体中文）对应的文件名称为messages_zh_CN.properties

## 创建工具类
```java
package com.shiyulong.utils;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class I18nUtil {
	private static MessageSource messageSource;

	public I18nUtil(MessageSource messageSource) {
		I18nUtil.messageSource = messageSource;
	}

	/**
	 * 获取单个国际化翻译值
	 */
	public static String get(String msgKey) {
		return messageSource.getMessage(msgKey, null, LocaleContextHolder.getLocale());
	}

	/**
	 * 获取单个国际化翻译值动态
	 */
	public static String get(String msgKey, String... args) {
		return messageSource.getMessage(msgKey, args, LocaleContextHolder.getLocale());
	}
}

```

## 测试
```java
package com.shiyulong.controller;

import com.shiyulong.common.Result;
import com.shiyulong.utils.I18nUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class DemoController {



	@GetMapping("/demo")
	public Result<?> demo(@RequestParam String name) {

		return Result.ok(I18nUtil.get("user.greeting",name));
	}


}

```
调用接口 http://localhost:8080/api/demo?name=张三   即可获取到结果























