package com.vs.vision.contorller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class PageController {// 类加载(将类读到内存)-->Class

	@RequestMapping("/doIndexUI.do")
	public String doIndexUI() {
		return "pages/starter";
	}

	@RequestMapping("/doLoginUI")
	public String doLoginUI() {
		return "pages/login";
	}

	@RequestMapping("/doNullUI.do")
	public String doNullUI() {
		return "pages/starter2";
	}

	@RequestMapping("/doPageUI")
	public String doPageUI() {
		return "pages/common/page";
	}

	@RequestMapping("/doPageUI.do")
	public String doPageUIdo() {
		return "pages/common/page";
	}

	@RequestMapping("doViwepager")
	public String doViwepager() {
		return "pages/Viwepager";
	}

	@RequestMapping("skipzhifu")
	public String skipzifu() {
		return "pages/zhifu";
	}

}
