package com.vs.vision.contorller.pre;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/diagnosis")

public class WebDiagnosisResultController {
	@RequestMapping("/doDiagnosis.do")
	public String diagnasisResultUI() {
		return "pages/sys/diagnosis_list";
	}
}


