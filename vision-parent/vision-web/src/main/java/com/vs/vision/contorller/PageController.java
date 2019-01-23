package com.vs.vision.contorller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/") 
@Controller
public class PageController{//类加载(将类读到内存)-->Class
	  
	  @RequestMapping("doIndexUI")
	  public String doIndexUI(){
		  return "pages/starter";
	  }
	  
	  @RequestMapping("doLoginUI")
	  public String doLoginUI(){
		  return "pages/login";
	  }
	  @RequestMapping("doNullUI")
	  public String doNullUI(){
		  return "pages/starter2";
	  }
	  
	  @RequestMapping("doPageUI")
	  public String doPageUI(){
		  return "pages/common/page";
	  }
	  
}
