package com.vs.vision.contorller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebController {

    /**
     * 实现首页跳转
     * @return
     */
    @RequestMapping("/index")
    public String find(){
        return "pages/starter";
    }
    
    @RequestMapping("doPageUI.do")
	  public String doPageUI(){
		  return "pages/common/page";
	  }

}
