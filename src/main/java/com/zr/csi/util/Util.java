package com.zr.csi.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zr.csi.parameter.CODE;
import com.zr.csi.parameter.MSG;

public class Util {
	public static Map<String , Object> setMapUpdate(int result, Map<String , Object> map){
		map = new HashMap<>();
		if (result > 0) {
			map.put(CODE.COD_EKEY,CODE.OK);
			map.put(MSG.MSG_KEY,MSG.UPDATE_SUCC);
		} else {
			map.put(CODE.COD_EKEY,CODE.FAILURE);
			map.put(MSG.MSG_KEY,MSG.UPDATE_FAIL);
		}
		return map;
	}
	
	public static Map<String , Object> setMapInsert(int result, Map<String , Object> map){
		map = new HashMap<>();
		if (result > 0) {
			map.put(CODE.COD_EKEY,CODE.OK);
			map.put(MSG.MSG_KEY,MSG.INSERT_SUCC);
		} else if (result == -1) {
			map.put(CODE.COD_EKEY,CODE.FAILURE);
			map.put(MSG.MSG_KEY,MSG.INSERT_FAIL);
		} else {
			map.put(CODE.COD_EKEY,CODE.FAILURE);
			map.put(MSG.MSG_KEY,MSG.INSERT_NAME_FAIL);
		}
		return map;
	}
	
	public static Map<String , Object> setMapQuery(List<?> list, Map<String , Object> map){
		map = new HashMap<>();
		map.put(CODE.COD_EKEY,CODE.OK);
		map.put(MSG.MSG_KEY,MSG.QUERY_SUCC);
		map.put("count", list.size());
		map.put("data", list);
		return map;
	}

	public static Map<String, Object> setMapDelete(int result, Map<String, Object> map) {
		map = new HashMap<>();
		if (result > 0) {
			map.put(CODE.COD_EKEY,CODE.OK);
			map.put(MSG.MSG_KEY,MSG.DELETE_SUCC);
		} else {
			map.put(CODE.COD_EKEY,CODE.FAILURE);
			map.put(MSG.MSG_KEY,MSG.DELETE_FAIL);
		}
		return map;
	}

	public static Map<String, Object> setMapDeletes(int count, int length, Map<String, Object> map) {
		map = new HashMap<>();
		if (count == length) {
			map.put(CODE.COD_EKEY, CODE.OK);
			map.put(MSG.MSG_KEY, MSG.DELETE_SUCC);
		} else if (count == 0) {
			map.put(CODE.COD_EKEY, CODE.FAILURE);
			map.put(MSG.MSG_KEY, MSG.DELETE_FAIL);
		} else {
			map.put(CODE.COD_EKEY, CODE.FAILURE);
			map.put(MSG.MSG_KEY, MSG.DELETE_PART_SUCC);
		}
		return map;
	}
}