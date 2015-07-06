package com.ocean.ad.report.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.ocean.ad.report.model.EventMonitorLineVo;
import com.ocean.ad.report.model.UserReport;
import com.ocean.ad.report.model.UserReportOnDay;

public interface IReportDao {

	
	@Select("select sum(uv) as userNum,event from ${tableName} where "
			+ " min_date >= #{start} and min_date <=#{end}  group by event;") 
	public List<UserReport> selectLogEventUV(@Param("tableName")String tableName,@Param("start")String start,@Param("end")String end);
	
	@Select("select sum(uv) as userNum,event,substring(min_date,0,10) as date from ${tableName} where "
			+ "min_date >= #{start} and min_date <=#{end}  group by event,date;") 
	public List<UserReportOnDay> selectLogEventUVOnDay(@Param("tableName") String tableName,@Param("start")String start,@Param("end")String end);
	
	@Select("SELECT count as num,min_date as time FROM ${tableName} where "
			+ "event =#{event} and min_date like concat(#{date},'%') order by time;")
	public List<EventMonitorLineVo> selectLogEventOnMin(@Param("tableName") String tableName,@Param("event") String event,@Param("date") String date);
	
	@Select("SELECT count as num,min_date as time FROM ${tableName} where "
			+ "event =#{event} and min_date > #{currMin} group by time order by time;")
	public List<EventMonitorLineVo> selectLogEventIncrOnMin(@Param("tableName") String tableName,@Param("event") String event,@Param("currMin") String currMin);
	
	@Select("SELECT count as num,min_date as time FROM ${tableName} where "
			+ "event =#{event} and min_date >= #{start} and min_date < #{end} order by time;")
	public List<EventMonitorLineVo> selectLogEventDynOnMin(@Param("tableName") String tableName,@Param("start") String start,@Param("end") String end,@Param("event") String event);

}
