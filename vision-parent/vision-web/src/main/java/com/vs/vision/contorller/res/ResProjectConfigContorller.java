package com.vs.vision.contorller.res;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ResProjectConfig")
public class ResProjectConfigContorller {

	@RequestMapping("/doResProjectConfigUI")
	public String doResProjectConfigUI() {
		return "/pages/sys/ResProjectConfig_List";
	}
}
