package com.ocean.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	private static final DateFormat DEFAULT_FMT=new SimpleDateFormat("yyyy-MM-dd");
	
	private static final DateFormat MIN_FMT = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	
	private static final DateFormat YYMMDD_FMT=new SimpleDateFormat("yyyyMMdd");
	
	private static final DateFormat YYYY_FMT=new SimpleDateFormat("yyyy");
	
	public static String DateDefaultFmt(Date date){
        return DEFAULT_FMT.format(date);
	}	
	public static String DateYYMMDDFmt(Date date){
        return YYMMDD_FMT.format(date);
	}	
	public static String DateMinFmt(Date date){
        
        return MIN_FMT.format(date);
	}
	public static String DateYYYYFmt(Date date){
        
        return YYYY_FMT.format(date);
	}
	public static Date parseMinFmt(String date) throws ParseException{
		return MIN_FMT.parse(date);
	}
}
