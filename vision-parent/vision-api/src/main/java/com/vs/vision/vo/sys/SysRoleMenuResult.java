package com.vs.vision.vo.sys;
import java.util.Date;
import java.util.List;
/***
 * 借助此对象封装角色和菜单相关信息
 * @author ta
 */
public class SysRoleMenuResult {
	/**角色id*/
	private Integer id;
	/**角色名称*/
	private String name;
	/**角色描述*/
	private String note;
	private Date createdTime;
	private Date modifiedTime;
	/**角色对应的菜单id*/
	private List<Integer> menuIds;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	public Date getModifiedTime() {
		return modifiedTime;
	}
	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}
	public List<Integer> getMenuIds() {
		return menuIds;
	}
	public void setMenuIds(List<Integer> menuIds) {
		this.menuIds = menuIds;
	}
	@Override
	public String toString() {
		return "SysRoleMenuResult [id=" + id + ", name=" + name + ", note=" + note + ", createdTime=" + createdTime
				+ ", modifiedTime=" + modifiedTime + ", menuIds=" + menuIds + "]";
	}
	
	
	
}
