package com.ocean.ad.report.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;

import com.ocean.ad.report.model.User;

public interface IUserDao {
	
	@Insert("INSERT INTO user(username,password,remark,create_time,update_time) VALUES (#{username},#{password},#{remark},#{createTime},#{updateTime})")
	public void insertUser(User user);
	
	@Update("update user set username=#{username},remark=#{remark},update_time=#{updateTime} where id=#{id}")
	public void updateUser(User user);
	
	@Select("SELECT count(*) from user where username=#{username} and id <>#{id}")
	public int selectCountUserByName(@Param("username")String username,@Param("id")Integer id);
	
	@SelectProvider(type = UserProvider.class, method = "getUserByLoginSql") 
	public User selectUserByLogin(@Param("username")String username,@Param("password")String password);
	
	@SelectProvider(type = UserProvider.class, method = "getQuerySql") 
	public List<User> selectUser(@Param("username")String username,@Param("index")int index,@Param("rowCount")int rowCount);
	
	@SelectProvider(type = UserProvider.class, method = "getQueryCountSql") 
	public int selectCountUser(@Param("username")String username);
	
	@SelectProvider(type = UserProvider.class, method = "getUserByIdSql") 
	public User selectUserById(@Param("id")int id);
	
	@UpdateProvider(type = UserProvider.class, method = "getUpdatePassSql") 
	public int updatePass(@Param("id")int id,@Param("password")String password);
	
}
