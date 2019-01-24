package com.vs.vision.contorller.exp;

import com.vs.vision.vo.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/dia/")
public class WebRemoteDiagnoseController {

    private static final String provider_url = "http://localhost:8024";
    @Autowired
    private RestTemplate restTemplate;

    /**
     * 进入远程诊断的显示页面
     * @return
     */
    @RequestMapping("doRemoteDiagnose.do")
    public String doRemoteDiagnose(){
        return "pages/sys/exp_remote_diagnose";
    }

    /**
     * 对应前端页面远程诊断模块呈现页面,
     * 从前端获取当前页码值并的开始下标返回指定长度的信息,
     * 获取输入框中customerName的名字,没有则查询全部
     * 并在数据库进行分页查询返回查询的信息
     * @param customerName
     * @param pageCurrent
     * @return
     */
    @RequestMapping("doFindPageObjects.do")
    @ResponseBody
    public JsonResult doFindPageObjects(
            String customerName,Integer pageCurrent){
        Map<Object, Object> map = new HashMap<>();
        map.put("customerName",customerName);
        map.put("pageCurrent",pageCurrent);
        JsonResult postForObject = restTemplate.postForObject(provider_url+"/doFindPageObjects",map,JsonResult.class);
        return  postForObject;

    }

}
