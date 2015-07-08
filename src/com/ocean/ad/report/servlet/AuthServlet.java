package com.ocean.ad.report.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ocean.ad.report.model.User;
import com.ocean.util.DateUtil;

public class AuthServlet extends HttpServlet implements Filter {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		// TODO Auto-generated method stub
		 HttpServletRequest request=(HttpServletRequest)arg0;     
         HttpServletResponse response  =(HttpServletResponse) arg1;      
         HttpSession session = request.getSession(true);
         User user = (User)session.getAttribute("user");
         request.setAttribute("today", DateUtil.DateDefaultFmt(new Date()));
         String path = request.getServletPath();
         if(path.indexOf("admin/report")>-1||user!=null){
        	  arg2.doFilter(arg0, arg1);
         }else{
        	 response.sendRedirect(request.getContextPath()+"/login.jsp"); 
         }
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
