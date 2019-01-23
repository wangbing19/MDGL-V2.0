package com.vs.vision.controller.sys;

import com.vs.vision.vo.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/dept")
public class SysDeptController {
    private static final String sys_url = "http://localhost:8029";
    @Autowired
    private RestTemplate restTemplate;
    /**
     *
     * @return 组织管理--加载页面
     */
    @RequestMapping("/doDeptListUI.do")
    public String doDeptListUI() {
        return "pages/sys/sys_dept_list";
    }
    /**
     * 加载编辑页面
     * @return
     */
    @RequestMapping("/doDeptEditUI.do")
    public String doDeptEditUI() {
        return "pages/sys/sys_dept_edit";
    }

    @RequestMapping("/doFindObjects.do")
    @ResponseBody
    public JsonResult doFindObjects(){
        System.out.println("doFindObjects");
        return restTemplate.getForObject(sys_url+"/doFindObjects",JsonResult.class);
    }

}
