package com.ocean.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	
	
	public static String DateDefaultFmt(Date date){
		DateFormat DEFAULT_FMT=new SimpleDateFormat("yyyy-MM-dd");
        return DEFAULT_FMT.format(date);
	}	
	public static String DateYYMMDDFmt(Date date){
		DateFormat YYMMDD_FMT=new SimpleDateFormat("yyyyMMdd");
        return YYMMDD_FMT.format(date);
	}	
	public static String DateMinFmt(Date date){
		 DateFormat MIN_FMT = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return MIN_FMT.format(date);
	}
	public static String DateYYYYFmt(Date date){
		DateFormat YYYY_FMT=new SimpleDateFormat("yyyy");
        return YYYY_FMT.format(date);
	}
	public static Date parseMinFmt(String date) throws ParseException{
		 DateFormat MIN_FMT = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		return MIN_FMT.parse(date);
	}
}
