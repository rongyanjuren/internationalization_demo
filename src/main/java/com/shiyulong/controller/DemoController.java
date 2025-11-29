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
