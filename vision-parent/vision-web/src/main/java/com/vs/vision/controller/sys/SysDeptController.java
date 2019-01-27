package com.vs.vision.controller.sys;

import com.vs.vision.annoation.RequiresLog;
import com.vs.vision.pojo.sys.Depts;
import com.vs.vision.pojo.sys.Users;
import com.vs.vision.service.DeptService;
import com.vs.vision.utils.ShiroUtils;
import com.vs.vision.vo.JsonResult;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/dept")
@PropertySource("classpath:/url.properties")
public class SysDeptController {
	@Value("${sys_dept_url}")
	private String sys_url;
    @Autowired
    private RestTemplate restTemplate;
    /**
     *
     * @return 组织管理--加载页面
     */
    
	@RequiresPermissions("sys:dept:view")
    @RequestMapping("/doDeptListUI.do")
    public String doDeptListUI() {
        return "pages/sys/sys_dept_list";
    }
    /**
     * 加载编辑页面
     * @return
     */
	@RequiresPermissions("sys:dept:add")
    @RequestMapping("/doDeptEditUI.do")
    public String doDeptEditUI() {
        return "pages/sys/sys_dept_edit";
    }
	@RequiresLog("组织管理查询")
    @RequestMapping("/doFindObjects.do")
    @ResponseBody
    public JsonResult doFindObjects(){
        return restTemplate.getForObject(sys_url+"/doFindObjects",JsonResult.class);
    }
    
    
    /**
	 * 查询组织结构数据
	 * @return
	 */
	@RequestMapping("doFindZTreeNodes.do")
	@ResponseBody
	public JsonResult doFindZTreeNodes() {
		JsonResult jsonResult = restTemplate.getForObject(sys_url+"/doFindZTreeNodes",JsonResult.class);
		System.out.println(jsonResult);
		return jsonResult;
	}
	/**
	 * 新增保存
	 * @param entity
	 * @return
	 */
	@RequestMapping("doSaveObject.do")
	@ResponseBody
	public JsonResult doSaveObject(Depts entity){
		System.out.println(entity);
		return restTemplate.postForObject(sys_url+"/doSaveObject", entity, JsonResult.class);
	}
	
	/**
	 * 更新
	 */
	@RequestMapping("doUpdateObject.do")
	@ResponseBody
	public JsonResult doUpdateObject(Depts entity){
		
		return restTemplate.postForObject(sys_url+"/doUpdateObject", entity, JsonResult.class);
	}
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@RequiresPermissions("sys:dept:delete")
	@RequestMapping("doDeleteObject.do")
	@ResponseBody
	public JsonResult doDeleteObject(Integer id){
		return restTemplate.postForObject(sys_url+"/doDeleteObject", id, JsonResult.class);
	}

}
