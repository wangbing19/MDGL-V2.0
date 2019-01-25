package com.vs.vision.contorller.exp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.vs.vision.pojo.exp.ExpertReply;
import com.vs.vision.vo.JsonResult;
import com.vs.vision.vo.PageObject;

@Controller
@RequestMapping("/rep/")
public class WebExpertReplyController {
	
	private static final String provider_url = "http://localhost:8024";
    @Autowired
    private RestTemplate restTemplate;
	
	/**
	 * 通过前端获取远程诊断表remoteDiagnoseId并查询出remoteDiagnoseId对应的信息
	 * @param id
	 * @return
	 */
	@RequestMapping("doSelectRep.do")
	@ResponseBody
	public JsonResult doSelectRep(Integer id) {
		//System.out.println("id="+id);
		try {
			ExpertReply selectRep = 
					restTemplate.postForObject(provider_url+"/doSelectRep",id,ExpertReply.class);
			return JsonResult.oK(selectRep);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonResult.build(201, "当前数据不存在");
	}
	
	
	/**
	 * 从浏览器获取添加信息在专家回复表进行添加
	 * @param entity
	 * @return
	 */
	@RequestMapping("doInsertRep.do")
	@ResponseBody
	public JsonResult doInsertRep(ExpertReply entity) {
		//ExpertReplyService.insertRep(entity);
		try {
			Integer po = restTemplate.postForObject(provider_url+"/doInsertRep",entity,Integer.class);
			if(!StringUtils.isEmpty(po)) {
				return JsonResult.oK("添加成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonResult.build(201,"添加失败");
	}
	
	
	/**
	 * 从浏览器获取要删除的<远程诊断表id>并在专家回复表进行删除
	 * @param id
	 * @return
	 */
	/*@RequestMapping("doDeleteRep.do")
	@ResponseBody
	public JsonResult doDeleteRep(Integer... id) {
		//ExpertReplyService.deleteRep(id);
		try {
		Integer po = restTemplate.postForObject(provider_url+"/doDeleteRep",id,Integer.class);
		if(!StringUtils.isEmpty(po)) {
			return JsonResult.oK("删除成功");
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return JsonResult.build(201,"删除失败");
	}*/
	
	
	/**
	 * 从浏览器获取要修改的信息并在专家回复表进行修改
	 * @param entity
	 * @return
	 */
	@RequestMapping("doUpdateRep.do")
	@ResponseBody
	public JsonResult doUpdateRep(ExpertReply entity) {
		//ExpertReplyService.updateRep(entity);
		try {
		Integer po = restTemplate.postForObject(provider_url+"/doUpdateRep",entity,Integer.class);
		if(!StringUtils.isEmpty(po)) {
			return JsonResult.oK("修改成功");
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return JsonResult.build(201,"修改失败");
	}
}
