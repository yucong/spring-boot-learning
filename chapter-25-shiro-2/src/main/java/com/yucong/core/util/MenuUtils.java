package com.yucong.core.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.util.CollectionUtils;

import com.yucong.vo.menu.PermissionVO;


/**
 * 处理menu返回树形结构数据
 * 
 * @author YN
 *
 */
public class MenuUtils {

	public static List<PermissionVO> formatMenu(List<PermissionVO> sysMenuList) {
        List<PermissionVO> treeList = new ArrayList<PermissionVO>();
        List<Long> idList = new ArrayList<Long>();
        if (!CollectionUtils.isEmpty(sysMenuList)) {
            for (int i = 0; i < sysMenuList.size(); i++) {
            	PermissionVO sysMenuVO = sysMenuList.get(i);
                idList.add(sysMenuVO.getId());
            }
            for (int j = 0; j < sysMenuList.size(); j++) {
            	PermissionVO sysMenuVO = sysMenuList.get(j);
                if (!idList.contains(sysMenuVO.getParentId())) {
                    sysMenuVO.setChildren(getRecursionChildList(sysMenuList, sysMenuVO.getId()));
                    treeList.add(sysMenuVO);
                }
            }
        }
        Collections.sort(treeList);
        return treeList;
    }

    private static List<PermissionVO> getRecursionChildList(List<PermissionVO> dataList, long parentId) {
        List<PermissionVO> childList = new ArrayList<PermissionVO>();
        if (!CollectionUtils.isEmpty(dataList)) {
            for (int i = 0; i < dataList.size(); i++) {
            	PermissionVO sysMenuVO = dataList.get(i);
                if (String.valueOf(parentId).equals(String.valueOf(sysMenuVO.getParentId()))) {
                    sysMenuVO.setChildren(getRecursionChildList(dataList, sysMenuVO.getId()));
                    childList.add(sysMenuVO);
                }
            }
        }
        Collections.sort(childList);
        return childList;
    }
}
