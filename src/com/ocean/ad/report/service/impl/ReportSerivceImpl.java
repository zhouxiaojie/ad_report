package com.ocean.ad.report.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ocean.ad.report.dao.IReportDao;
import com.ocean.ad.report.model.EventMonitorLineVo;
import com.ocean.ad.report.service.IReportService;
import com.ocean.util.DateUtil;

@Service("reportService")
public class ReportSerivceImpl implements IReportService{
	@Resource
	private IReportDao reportDao;
	
	@Override
	public List<EventMonitorLineVo> getEventOnMin(Calendar ca,String event,int interval,boolean curr,Date now){
		
		String date = DateUtil.DateDefaultFmt(ca.getTime());
		Map<String,Integer> data = listToMap(reportDao.selectLogEventOnMin(date, event));
		List<EventMonitorLineVo> vdata = getAllMinData(ca,data,interval,now);
		//保证数据完整性，去掉最新可能不完全的数据
		if(curr)
			vdata.remove(vdata.size()-1);
		return vdata;
	}
	
	@Override
	public List<EventMonitorLineVo> getEventDynOnMin(Calendar ca,String event,String start,String end,int interval,Date now){
		Map<String,Integer> data = listToMap(reportDao.selectLogEventDynOnMin(start,end,event));
		List<EventMonitorLineVo> vdata = getAllMinData(ca,data,interval,now);
		//保证数据完整性，去掉最新可能不完全的数据
		if(vdata.size()>0)
			vdata.remove(vdata.size()-1);
		return vdata;
	}
	
	@Override
	public List<EventMonitorLineVo> getEventIncrOnMin(Calendar ca,String event,String currMin,int interval,Date now){
		
		String date = DateUtil.DateDefaultFmt(ca.getTime());
		Map<String,Integer> data = listToMap(reportDao.selectLogEventIncrOnMin(date, event,currMin));
		List<EventMonitorLineVo> vdata = getAllMinData(ca,data,interval,now);
		//保证数据完整性，去掉最新可能不完全的数据
		if(vdata.size()>0)
			vdata.remove(vdata.size()-1);	
		return vdata;
	}
	private Map<String,Integer> listToMap(List<EventMonitorLineVo>  data){
		Map<String,Integer> map = new HashMap<String, Integer>();
		for (EventMonitorLineVo d : data) {
			map.put(d.getTime(),d.getNum());
		}
		return map;
	}
	private List<EventMonitorLineVo>  getAllMinData(Calendar ca,Map<String,Integer> data,int interval,Date now){
		List<EventMonitorLineVo> vdata = new ArrayList<EventMonitorLineVo>();
		String minDate = DateUtil.DateMinFmt(ca.getTime());
		EventMonitorLineVo emlv = new EventMonitorLineVo(minDate,0);
		boolean init = (ca.get(Calendar.MINUTE)==0&&ca.get(Calendar.HOUR_OF_DAY)==0);	
		if(init)vdata.add(emlv);
		for(int i=0;i<24*60/interval&&ca.getTimeInMillis()<now.getTime();i++){		
				emlv = new EventMonitorLineVo(minDate,0);
				int jrange=i*interval+interval;
				for(int j=i*interval;j<jrange;j++){
					minDate = DateUtil.DateMinFmt(ca.getTime());
					Integer value = data.get(minDate);
					emlv.setNum(emlv.getNum()+(value!=null?value:0));
					emlv.setTime(minDate);
					ca.add(Calendar.MINUTE,1);
				}
				vdata.add(emlv);
		}
		return vdata;
	}
	public static void main(String[] args) {
		//getEventMonData();
	}
	
}
