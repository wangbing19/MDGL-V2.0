package com.vs.vision.contorller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebController {

    @RequestMapping("/find")
 //   @ResponseBody
    public String find(){
        return "pages/starter";
    }

}
