package com.ocean.ad.report.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.ocean.ad.report.model.EventMonitorLineVo;

public interface IReportService {


	List<EventMonitorLineVo> getEventOnMin(Calendar ca, String event,
			int interval, boolean curr, Date now);


	List<EventMonitorLineVo> getEventIncrOnMin(Calendar ca, String event,
			String currMin, int interval, Date now);

	List<EventMonitorLineVo> getEventDynOnMin(Calendar ca, String event,
			String start, String end, int interval, Date now);
	
}
