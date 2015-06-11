package com.ocean.ad.report.dao;


import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

public class UserProvider {
	private final String USER_COL="id,username,remark,create_time as createTime";
	public  String getQuerySql(Map<String, Object> parameters) {  
		final String username = (String)parameters.get("username");
        return new SQL(){{
        	SELECT(USER_COL);
        	FROM("user");
        	if(username!=null)
        		WHERE("username like #{username} "+" '%'");
        	
        	
        }}.toString()+"limit #{index},#{rowCount}";
    }
	
	public  String getQueryCountSql(Map<String, Object> parameters) {  
		final String username = (String)parameters.get("username");
        return new SQL(){{
        	SELECT("count(*)");
        	FROM("user");
        	if(username!=null)
        		WHERE("username like #{username} "+" '%'");
        	
        	
        }}.toString();
    } 
	
	public  String getUserByIdSql(Map<String, Object> parameters) {  
        return new SQL(){{
        	SELECT(USER_COL);
        	FROM("user");
        	WHERE("id = #{id}");      	
        }}.toString();
    } 
	
	public  String getUserByLoginSql(Map<String, Object> parameters) {  
        return new SQL(){{
        	SELECT(USER_COL);
        	FROM("user");
        	WHERE("username = #{username}");
        	WHERE("password = #{password}");   
        }}.toString();
    } 
	
	public  String getUpdatePassSql(Map<String, Object> parameters) {  
        return new SQL(){{
        	UPDATE("user");
        	SET("password=#{password}");
        	WHERE("id = #{id}");
        }}.toString();
    } 

}
