package com.yucong.core.enums;

public enum PermissionTypeEnum {

	menu("菜单"), 
	button("按钮");

    private final String info;
    
    private PermissionTypeEnum(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }
}
