package com.vs.vision.contorller.pre;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.vs.vision.pojo.pre.DiagnosisResult;
import com.vs.vision.vo.JsonResult;


@Controller
@RequestMapping("/diagnosis")

public class WebDiagnosisResultController {
	@Autowired
	private RestTemplate restTemplate;
	@RequestMapping("/doDiagnosis.do")
	public String diagnasisResultUI() {
		return "pages/sys/diagnosis_list";
	}
	
	@RequestMapping("/doFindObjects.do")
	@ResponseBody
	public JsonResult doFindObjects(){
		System.out.println("我是前台controller");
		String url = "http://localhost:8026/diagnosisResult/doFindObjects";
		 List<DiagnosisResult> forObject = restTemplate.getForObject(url,List.class);
		 System.out.println("结果："+forObject);
		 return new JsonResult().oK(forObject);
	}
}




