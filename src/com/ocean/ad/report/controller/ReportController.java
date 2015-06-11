package com.ocean.ad.report.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ocean.ad.report.controller.resp.BootgridResult;
import com.ocean.ad.report.dao.IReportDao;
import com.ocean.ad.report.model.DonutVo;
import com.ocean.ad.report.model.EventMonitorLineVo;
import com.ocean.ad.report.model.UserReport;
import com.ocean.ad.report.model.UserReportOnDay;
import com.ocean.ad.report.model.UserReportOnDayVo;
import com.ocean.ad.report.service.IReportService;
import com.ocean.util.DateUtil;

@Controller
public class ReportController extends BaseController{
	@Resource
	private IReportDao reportDao;
	
	@Resource
	private IReportService reportService;
	
	
	@RequestMapping(value="admin/report/selectusereport.json",method=RequestMethod.POST)
	@ResponseBody
	public BootgridResult<UserReport> selectUserReport(String start,String end,HttpServletRequest req){
		 List<UserReport> data = reportDao.selectLogEventUV(start, end);
		 BootgridResult<UserReport> re = new BootgridResult<>(0, 0,data,0);
		return re;
	}
	
	@RequestMapping(value="admin/report/selectuserdonut.json",method=RequestMethod.POST)
	@ResponseBody
	public List<DonutVo> selectUserDonut(String start,String end,HttpServletRequest req){
		 List<UserReport> data = reportDao.selectLogEventUV(start, end);
		 List<DonutVo> vdata = new ArrayList<DonutVo>();
		 for (UserReport ur : data) {
			 DonutVo v = new DonutVo(ur.getEvent(),ur.getDefaultUserNum());
			 vdata.add(v);
		}
		return vdata;
	}
	
	@RequestMapping(value="admin/report/selectusereportonday.json",method=RequestMethod.POST)
	@ResponseBody
	public List<UserReportOnDayVo> selectUserReportOnDay(String start,String end,HttpServletRequest req){
		List<UserReportOnDay> data=	reportDao.selectLogEventUVOnDay(start, end);
		List<UserReportOnDayVo> voDate = new ArrayList<UserReportOnDayVo>();
		Set<String> set = new LinkedHashSet<>();
		for (UserReportOnDay d : data) {
			set.add(d.getDate());
		}
		for (String date : set) {
			UserReportOnDayVo v = new UserReportOnDayVo();
			v.setFmtDate(date);
			for (UserReportOnDay d : data) {
				if(d.getDate().equals(date)){
					if("dau".equals(d.getEvent())){
						v.setDau(d.getUserNum());
					}else if("init_f".equals(d.getEvent())){
						v.setInit_f(d.getUserNum());
					}else if("show_s".equals(d.getEvent())){
						v.setShow_s(d.getUserNum());
					}
				}
			}
			voDate.add(v);
		}
		
		 return voDate;
	}
	/**
	 * 今天数据
	 * */
	@RequestMapping(value="admin/report/eventonmin.json",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,List<EventMonitorLineVo>> selectEventOnMin(String event,Integer interval){
		Date now = new Date();
		Calendar ca = Calendar.getInstance();
		ca.setTime(now);
		ca.set(Calendar.HOUR_OF_DAY, 0);
		ca.set(Calendar.MINUTE, 0);
		ca.set(Calendar.SECOND,0);
		ca.set(Calendar.MILLISECOND,0);
		List<EventMonitorLineVo> data=	reportService.getEventOnMin(ca,event,interval==null?1:interval,true,now);
		
		ca.setTime(now);
		ca.add(Calendar.DAY_OF_YEAR,-1);
		ca.set(Calendar.HOUR_OF_DAY, 0);
		ca.set(Calendar.MINUTE, 0);
		ca.set(Calendar.SECOND,0);
		ca.set(Calendar.MILLISECOND,0);
		List<EventMonitorLineVo> ydata=	reportService.getEventOnMin(ca,event,interval==null?0:interval,false,now);
		Map<String,List<EventMonitorLineVo>>  map =new HashMap<String,List<EventMonitorLineVo>>();		
		
		String end = DateUtil.DateMinFmt(now);
		Calendar dca = Calendar.getInstance();
		dca.setTime(now);
		dca.add(Calendar.HOUR_OF_DAY,-1);
		dca.set(Calendar.SECOND,0);
		dca.set(Calendar.MILLISECOND,0);
		List<EventMonitorLineVo> ddata= reportService.getEventDynOnMin(dca,event, DateUtil.DateMinFmt(dca.getTime()), end, 1, now);
		
		map.put("c",data);
		map.put("y",ydata);
		map.put("d",ddata);
		 return map;
	}
	/**
	 * 昨天数据
	 * */
	@RequestMapping(value="admin/report/eventonminy.json",method=RequestMethod.POST)
	@ResponseBody
	public List<EventMonitorLineVo> selectEventOnMinY(String event,Integer interval){
		Date now = new Date();
		Calendar ca = Calendar.getInstance();
		ca.setTime(now);
		ca.add(Calendar.DAY_OF_YEAR,-1);
		ca.set(Calendar.HOUR_OF_DAY, 0);
		ca.set(Calendar.MINUTE, 0);
		ca.set(Calendar.SECOND,0);
		ca.set(Calendar.MILLISECOND,0);
		List<EventMonitorLineVo> ydata=	reportService.getEventOnMin(ca,event,interval==null?1:interval,false,now);
		 return ydata;
	}
	
	/**
	 * 分钟增量数据
	 * @throws ParseException 
	 * */
	@RequestMapping(value="admin/report/eventincronmin.json",method=RequestMethod.POST)
	@ResponseBody
	public List<EventMonitorLineVo> selectEventIncrOnMin(String currMin,String event,Integer interval){
		Date now = new Date();
		Calendar ca = Calendar.getInstance();
		try {
			ca.setTime(DateUtil.parseMinFmt(currMin));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		};
		ca.add(Calendar.MINUTE,1);
		ca.set(Calendar.SECOND,0);
		ca.set(Calendar.MILLISECOND,0);
		List<EventMonitorLineVo> data=	reportService.getEventIncrOnMin(ca, event, currMin,interval==null?1:interval,now);
		 return data;
	}
	
	@RequestMapping(value="admin/report/eventdynonmin.json",method=RequestMethod.POST)
	@ResponseBody
	public List<EventMonitorLineVo> selectEventDynOnMin(Integer interval,String event){
		Date now = new Date();
		String end = DateUtil.DateMinFmt(now);
		Calendar ca = Calendar.getInstance();
		ca.setTime(now);
		ca.add(Calendar.HOUR_OF_DAY,-1);
		ca.set(Calendar.SECOND,0);
		ca.set(Calendar.MILLISECOND,0);
		return reportService.getEventDynOnMin(ca, event,DateUtil.DateMinFmt(ca.getTime()), end, interval==null?1:interval, now);
	}
	
}