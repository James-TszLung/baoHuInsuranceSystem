package com.xiaobaozi.commons.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import com.xiaobaozi.dataSystem.usermanage.vo.Function;

public class MenuUtil {

	/**
	 * 获取用户权限菜单-专用方法
	 * 
	 * @param menus
	 * @return
	 */
	public static String parseMenuObjToJson(List<Function> menus, boolean flag) {
		// 先获知菜单层级数
		int maxLevel = 1;
		int minLevel = 1;
		Map<Integer, List<Function>> levelMap = new HashMap<Integer, List<Function>>();
		// 按层级分组
		for (Function fun : menus) {
			int funLevel = fun.getLevel();
			// 更新最高层级
			if (funLevel > maxLevel)
				maxLevel = funLevel;
			if (funLevel < minLevel)
				minLevel = funLevel;
			// 同时将该模块添加到map对应的位置
			if (levelMap.get(funLevel) != null) {
				levelMap.get(funLevel).add(fun);
			} else {
				List<Function> funs = new ArrayList<Function>();
				funs.add(fun);
				levelMap.put(funLevel, funs);
			}
		}
		if (flag) {
			// 从最底层开始递归，最终获取包含所有子级菜单的最上级菜单
			Map<Integer, List<Function>> temp = new HashMap<Integer, List<Function>>();
			addChildToList(levelMap, maxLevel, minLevel);
			Function root = new Function();
			root.setId("0");
			root.setFunName("根节点");
			root.setChilds(levelMap.get(minLevel));
			return JSONArray.fromObject(root).toString();
		} else {
			addChildToList(levelMap, maxLevel, minLevel);
			return JSONArray.fromObject(levelMap.get(minLevel)).toString();
		}
	}

	private static void addChildToList(Map<Integer, List<Function>> levelMap, int level, int minLevel) {
		List<Function> childs = levelMap.get(level);
		List<Function> fathers = levelMap.get(level - 1);
		// 将level级的菜单绑定到上级菜单中
		if (fathers != null) {
			for (Function child : childs) {
				for (Function fat : fathers) {
					if (child.getParentMenu().equals(fat.getId()))
						fat.getChilds().add(child);
				}
			}
		}
		// 最后等级上升1级，继续递归，当等级到1时退出
		return;
	}

}
