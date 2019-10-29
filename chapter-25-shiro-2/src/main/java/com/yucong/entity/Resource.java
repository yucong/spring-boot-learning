package com.yucong.entity;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "sys_resource")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Resource implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; //编号
    private String name; //资源名称
    private String type;; //资源类型
    private String url; //资源路径
    private String permission; //权限字符串
    private Long parentId; //父编号
    private String parentIds; //父编号列表
    private Boolean available = Boolean.FALSE;

    public static enum ResourceType {
        menu("菜单"), button("按钮");

        private final String info;
        private ResourceType(String info) {
            this.info = info;
        }

        public String getInfo() {
            return info;
        }
    }


}
