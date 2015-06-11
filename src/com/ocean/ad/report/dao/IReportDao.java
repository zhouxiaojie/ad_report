package com.ocean.ad.report.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.ocean.ad.report.model.EventMonitorLineVo;
import com.ocean.ad.report.model.UserReport;
import com.ocean.ad.report.model.UserReportOnDay;

public interface IReportDao {

	
	@Select("select count(*) as userNum,event from (select distinct imei,mac,event from log_event where "
			+ "imei is not null and date_format(create_time,'%Y-%m-%d') >= #{start} and date_format(create_time,'%Y-%m-%d') <=#{end}) a group by event;") 
	public List<UserReport> selectLogEventUV(@Param("start")String start,@Param("end")String end);
	
	@Select("select count(*) as userNum,event,date from (select distinct imei,mac,event,date_format(create_time,'%Y-%m-%d') as date from log_event where "
			+ "imei is not null and date_format(create_time,'%Y-%m-%d') >= #{start} and date_format(create_time,'%Y-%m-%d') <=#{end}) a group by event,date;") 
	public List<UserReportOnDay> selectLogEventUVOnDay(@Param("start")String start,@Param("end")String end);
	
	@Select("SELECT count(*) as num,date_format(create_time,'%Y-%m-%d %H:%i') as time FROM ad_interface.log_event where "
			+ "event =#{event} and date_format(create_time,'%Y-%m-%d')=#{date}  group by time order by time;")
	public List<EventMonitorLineVo> selectLogEventOnMin(@Param("date") String date,@Param("event") String event);
	
	@Select("SELECT count(*) as num,date_format(create_time,'%Y-%m-%d %H:%i') as time FROM ad_interface.log_event where "
			+ "event =#{event} and date_format(create_time,'%Y-%m-%d')=#{date} and date_format(create_time,'%Y-%m-%d %H:%i') > #{currMin} group by time order by time;")
	public List<EventMonitorLineVo> selectLogEventIncrOnMin(@Param("date") String date,@Param("event") String event,@Param("currMin") String currMin);
	
	@Select("SELECT count(*) as num,date_format(create_time,'%Y-%m-%d %H:%i') as time FROM ad_interface.log_event where "
			+ "event =#{event} and date_format(create_time,'%Y-%m-%d %H:%i') >= #{start} and date_format(create_time,'%Y-%m-%d %H:%i') < #{end}  group by time order by time;")
	public List<EventMonitorLineVo> selectLogEventDynOnMin(@Param("start") String start,@Param("end") String end,@Param("event") String event);

}
