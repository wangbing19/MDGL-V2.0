package com.vs.sys.controller;

import com.vs.sys.service.DeptService;
import com.vs.vision.vo.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class DetpController {
    @Autowired
    private DeptService deptService;

    @RequestMapping("/doFindObjects")
    public JsonResult doFindObjects(){
        List<Map<String, Object>> objects = deptService.findObjects();
        System.out.println(objects);
        return new JsonResult(objects);
    }
}
