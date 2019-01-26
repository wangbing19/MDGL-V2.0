package com.vs.vision.vo.sys;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vs.vision.pojo.sys.Logs;
import com.vs.vision.pojo.sys.Roles;
import com.vs.vision.pojo.sys.Users;

@JsonIgnoreProperties(ignoreUnknown=true)
public class RestTemplateParmas {
	private Roles role;
	private Integer[] ids;
	private String name;
	private Integer pageCurrent;
	private Integer valid;
	private Integer id;
	private Logs logs;
	private Users user;
	private Users userentity;

	public Users getUserentity() {
		return userentity;
	}

	public void setUserentity(Users userentity) {
		this.userentity = userentity;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public Logs getLogs() {
		return logs;
	}

	public void setLogs(Logs logs) {
		this.logs = logs;
	}

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
				+ ", pageCurrent=" + pageCurrent + ", valid=" + valid + ", id=" + id + ", logs=" + logs + ", user="
				+ user + ", userentity=" + userentity + "]";
	}

}
