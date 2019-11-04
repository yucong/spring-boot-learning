package com.yucong.vo.menu;

import java.util.List;

import lombok.Data;

@Data
public class PermissionVO implements Comparable<PermissionVO>{

	private Long id;
	private Long parentId;
    private String name;
    private String iconCls;
    private Integer sort;
    private String permission;
	private List<PermissionVO> children;
	private String checked; //是否选中，编辑角色时使用
	private String memo;
    private String type;//菜单类型：1:menu、2:btn

	@Override
	public int compareTo(PermissionVO o) {
		return sort.compareTo(o.getSort());//通过menuSort升序
	}
}
