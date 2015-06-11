package com.ocean.util;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSON;

public class JsonUtils {
	public static String OtoJson(Object o){
		if(null==o)
			return "{}";
		if(o instanceof String){
			return (String)o;
		}
		return JSON.toJSONString(o);
	}
	public static <T> Object JsonToO(String json,Class<T> c){
		if(null==json||"{}".equals(json))
			return null;
		if("String".equals(c.getSimpleName())){
			return json;
		}
		return JSON.parseObject(json,c);
	}
	public static <T> Object JsonToArr(String json,Class<T> c){
		if(null==json||"[]".equals(json))
			return null;
		return JSON.parseArray(json,c);
	}
	public static String ArrtoJson(Object o){
		if(null==o)
			return "[]";
		return JSON.toJSONString(o);
	}
	public static <T> Object JsonStrArrtoArr(List<String> strs,Class<T> c){
		if(null==strs||strs.size()==0)
			return null;
		StringBuffer buf = new StringBuffer("[");
		for (int i=0,len=strs.size();i<len;i++) {
			String s = strs.get(i);
			if(StringUtils.isBlank(s))continue;
			buf.append(strs.get(i)+",");
		}
		String json = buf.toString();
		if(json.contains(","))
			json= json.substring(0,json.lastIndexOf(","));
		json = json+"]";
		return JsonToArr(json, c);
	}
	public static void main(String[] args) {
	}
}
