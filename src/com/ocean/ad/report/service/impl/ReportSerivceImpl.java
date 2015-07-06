package com.ocean.ad.report.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ocean.ad.report.constant.TablePre;
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
		
		String tableName =TablePre.LOG_EVENT_LOG_COUNT+ DateUtil.DateYYYYFmt(ca.getTime());
//		注释掉为没有任务系统时的方法
//		Map<String,Integer> data = listToMap(reportDao.selectLogEventOnMin(tableName, event));
//		List<EventMonitorLineVo> vdata = getAllMinData(ca,data,interval,now);
		//保证数据完整性，去掉最新可能不完全的数据
//		if(curr)
//			vdata.remove(vdata.size()-1);
//		return vdata;
		List<EventMonitorLineVo> data=reportDao.selectLogEventOnMin(tableName, event,DateUtil.DateDefaultFmt(ca.getTime()));
		return getShowData(ca,interval,data);
	}
	
	@Override
	public List<EventMonitorLineVo> getEventDynOnMin(Calendar ca,String event,String start,String end,int interval,Date now){
		String tableName =TablePre.LOG_EVENT_LOG_COUNT+ DateUtil.DateYYYYFmt(ca.getTime());
//		Map<String,Integer> data = listToMap(reportDao.selectLogEventDynOnMin(tableName,start,end,event));
//		List<EventMonitorLineVo> vdata = getAllMinData(ca,data,interval,now);
//		//保证数据完整性，去掉最新可能不完全的数据
//		if(vdata.size()>0)
//			vdata.remove(vdata.size()-1);
//		return vdata;
		List<EventMonitorLineVo> data=reportDao.selectLogEventDynOnMin(tableName,start,end,event);
		return getShowData(ca,interval,data);
	}
	
	@Override
	public List<EventMonitorLineVo> getEventIncrOnMin(Calendar ca,String event,String currMin,int interval,Date now){
		
		String tableName =TablePre.LOG_EVENT_LOG_COUNT+ DateUtil.DateYYYYFmt(ca.getTime());
//		Map<String,Integer> data = listToMap(reportDao.selectLogEventIncrOnMin(tableName, event,currMin));
//		List<EventMonitorLineVo> vdata = getAllMinData(ca,data,interval,now);
//		//保证数据完整性，去掉最新可能不完全的数据
//		if(vdata.size()>0)
//			vdata.remove(vdata.size()-1);	
//		return vdata;
		List<EventMonitorLineVo> data=reportDao.selectLogEventIncrOnMin(tableName,event,currMin);
		return getShowData(ca,interval,data);
	}
	
	private List<EventMonitorLineVo> getShowData(Calendar ca,int interval,List<EventMonitorLineVo> data){
		List<EventMonitorLineVo> vdata = new ArrayList<EventMonitorLineVo>();
		String minDate = DateUtil.DateMinFmt(ca.getTime());
		EventMonitorLineVo emlv = new EventMonitorLineVo(minDate,0);
		boolean init = (ca.get(Calendar.MINUTE)==0&&ca.get(Calendar.HOUR_OF_DAY)==0);	
		if(init)vdata.add(emlv);
		for(int i=0;i<data.size()/interval;i++){	
			emlv = new EventMonitorLineVo("",0);
			int jstart = i*interval;
			int jrange=jstart+interval;
			for(int j=jstart;j<jrange;j++){
				EventMonitorLineVo vo = data.get(j);
				emlv.setNum(emlv.getNum()+vo.getNum());
				emlv.setTime(vo.getTime());			
			}
			vdata.add(emlv);
		}
		return vdata;
	}
	
//	private Map<String,Integer> listToMap(List<EventMonitorLineVo>  data){
//		Map<String,Integer> map = new HashMap<String, Integer>();
//		for (EventMonitorLineVo d : data) {
//			map.put(d.getTime(),d.getNum());
//		}
//		return map;
//	}
//	private List<EventMonitorLineVo>  getAllMinData(Calendar ca,Map<String,Integer> data,int interval,Date now){
//		List<EventMonitorLineVo> vdata = new ArrayList<EventMonitorLineVo>();
//		String minDate = DateUtil.DateMinFmt(ca.getTime());
//		EventMonitorLineVo emlv = new EventMonitorLineVo(minDate,0);
//		boolean init = (ca.get(Calendar.MINUTE)==0&&ca.get(Calendar.HOUR_OF_DAY)==0);	
//		if(init)vdata.add(emlv);
//		for(int i=0;i<24*60/interval&&ca.getTimeInMillis()<now.getTime();i++){		
//				emlv = new EventMonitorLineVo(minDate,0);
//				int jrange=i*interval+interval;
//				for(int j=i*interval;j<jrange;j++){
//					minDate = DateUtil.DateMinFmt(ca.getTime());
//					Integer value = data.get(minDate);
//					emlv.setNum(emlv.getNum()+(value!=null?value:0));
//					emlv.setTime(minDate);
//					ca.add(Calendar.MINUTE,1);
//				}
//				vdata.add(emlv);
//		}
//		return vdata;
//	}
	public static void main(String[] args) {
		//getEventMonData();
	}
	
}
