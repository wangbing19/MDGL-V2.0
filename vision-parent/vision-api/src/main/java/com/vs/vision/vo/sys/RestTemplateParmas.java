package com.vs.vision.vo.sys;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vs.vision.pojo.sys.Roles;

@JsonIgnoreProperties(ignoreUnknown=true)
public class RestTemplateParmas {
	private Roles role;
	private Integer[] ids;
	private String name;
	private Integer pageCurrent;
	private Integer valid;
	private Integer id;

	public Roles getRole() {
		return role;
	}

	public void setRole(Roles role) {
		this.role = role;
	}

	public Integer[] getIds() {
		return ids;
	}

	public void setIds(Integer[] ids) {
		this.ids = ids;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPageCurrent() {
		return pageCurrent;
	}

	public void setPageCurrent(Integer pageCurrent) {
		this.pageCurrent = pageCurrent;
	}

	public Integer getValid() {
		return valid;
	}

	public void setValid(Integer valid) {
		this.valid = valid;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "RestTemplateParmas [role=" + role + ", ids=" + Arrays.toString(ids) + ", name=" + name
				+ ", pageCurrent=" + pageCurrent + ", valid=" + valid + ", id=" + id + "]";
	}

}
