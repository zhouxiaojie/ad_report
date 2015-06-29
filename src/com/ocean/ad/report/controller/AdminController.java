package com.ocean.ad.report.controller;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.ocean.ad.report.controller.resp.BootgridResult;
import com.ocean.ad.report.controller.resp.Result;
import com.ocean.ad.report.dao.IUserDao;
import com.ocean.ad.report.model.User;
import com.ocean.util.MD5;

@Controller
public class AdminController extends BaseController{
	
	@Resource
	private IUserDao userDao;
	@Transactional
	@RequestMapping(value="login.json",method=RequestMethod.POST)
	@ResponseBody
	public Result login(String username,String password,HttpServletRequest request,HttpServletResponse response){
		User user=userDao.selectUserByLogin(username, MD5.GetMD5Code(password));   
		Result re = new Result();
		if(user!=null){
			HttpSession session = request.getSession();
			session.setAttribute("user",user);
			session.setMaxInactiveInterval(1800);
		}else{
			re.setCode(Result.USER_ERROR);
		}
		return re;
	}
	
	@RequestMapping(value="loginout.do",method=RequestMethod.GET)
	public String loginout(HttpServletRequest request){
		HttpSession session = request.getSession();
		session.invalidate();
		return "redirect:login.jsp";
	}
	@Transactional
	@RequestMapping(value="admin/user/addupdateuser.json",method=RequestMethod.POST)
	@ResponseBody
	public Result addUpdateUser(Integer id,String username,String remark){
		 int count = userDao.selectCountUserByName(username,id==null?0:id);
		 Result re = new Result();
		 if(count>0){
			 re.setCode(Result.USER_EXISTS);
			 return re;
		 }
		 Timestamp now = new Timestamp(System.currentTimeMillis());
		 User user = new User(id,username,MD5.GetMD5Code("123456"),remark,now,now);
		 if(id!=null&&id>0){
			 userDao.updateUser(user); 
		 }else{
			 userDao.insertUser(user); 
		 }		 
		return re;
	}
	
	@RequestMapping(value="admin/user/valiuser.do",method=RequestMethod.POST)
	@ResponseBody
	public JSONObject valiUser(String username,Integer id){
		 int count = userDao.selectCountUserByName(username,id==null?0:id);
		 JSONObject json = new JSONObject();
		 if(count>0){
			 json.put("valid", false);
			 return json;
		 }
		 json.put("valid", true);
		return json;
	}
	
	@RequestMapping(value="admin/user/selectuser.json",method=RequestMethod.GET)
	@ResponseBody
	public BootgridResult<User> selectUser(String username,int current,int rowCount){
		 List<User> users=userDao.selectUser(username,(current-1)*rowCount,rowCount);
		 BootgridResult<User> re = new BootgridResult<>(current, rowCount,users,userDao.selectCountUser(username));
		return re;
	}
	
	@RequestMapping(value="admin/user/selectuserbyid.json",method=RequestMethod.GET)
	@ResponseBody
	public User selectUserById(int id){
		return userDao.selectUserById(id);
	}
	@Transactional(rollbackFor=Exception.class)
	@RequestMapping(value="admin/user/updatepass.json",method=RequestMethod.POST)
	@ResponseBody
	public Result updatepass(String oldpass,String newpass,HttpServletRequest request){
		String username =((User)request.getSession().getAttribute("user")).getUsername();
		User user=userDao.selectUserByLogin(username, MD5.GetMD5Code(oldpass));
		Result re = new Result();
		if(user!=null){
			userDao.updatePass(user.getId(),MD5.GetMD5Code(newpass));
			throw new RuntimeException("1");
		}else{
			re.setCode(Result.USER_ERROR);
		}
		
		return re;
	}
}