package com.webmodel.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class DateUtil {
	
	public static enum Format{
		
		DATE("yyyy-MM-dd"),

		DATETIME("yyyy-MM-dd HH:mm:ss"),

		DATEHOURMIN("yyyy-MM-dd HH:mm"),
		
		TIME("HH:mm:ss");
		
		private final DateTimeFormatter formatter;
		
		Format(String text){
			formatter = DateTimeFormatter.ofPattern(text);
		}
	}
	
	/**
	 * 将字符串转换成指定日期时间格式
	 * @param date
	 * @return
	 */
	public static LocalDateTime parseDateTime(String dateTime){
		return LocalDateTime.parse(dateTime, Format.DATETIME.formatter);
	}
	
	/**
	 * 将日期格式转换成字符串
	 * @param date
	 * @return
	 */
	public static String format(LocalDate date){
		return date.format(Format.DATE.formatter);
	}
	
	/**
	 * 将日期时间格式转换成指定格式字符串
	 * @param date
	 * @return
	 */
	public static String format(LocalDateTime date,Format text){
		return date.format(text.formatter);
	}
	
	/**
	 * 获取两个字符串格式的日期 相差的天数
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public static int getDiffDay(String startTime,String endTime){
		LocalDate s = LocalDate.parse(startTime);
		LocalDate e = LocalDate.parse(endTime);
		return (int)s.until(e, ChronoUnit.DAYS);
	}
	
	/**
	 * 获取两个日期 相差的天数
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public static int getDiffDay(LocalDate startTime,LocalDate endTime){
		return (int)startTime.until(endTime, ChronoUnit.DAYS);
	}
	
	/**
	 * 获取两个字符串格式的时间 相差的分钟
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public static int getDiffMinutes(String startTime,String endTime){
		LocalTime s = LocalTime.parse(startTime);
		LocalTime e = LocalTime.parse(endTime);
		return (int)s.until(e, ChronoUnit.MINUTES);
	}
	
	/**
	 * 获取两个时间 相差的分钟
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public static int getDiffMinutes(LocalTime startTime,LocalTime endTime){
		return (int)startTime.until(endTime, ChronoUnit.MINUTES);
	}
	
	/**
	 * 获取系统当前日期时间格式字符串
	 * @return
	 */
	public static String getNow(){
		LocalDateTime now = LocalDateTime.now();
		return now.format(Format.DATETIME.formatter);
	}
	
	/**
	 * 根据日期时间格式 获取系统当前时间字符串
	 * @param text
	 * @return
	 */
	public static String getNow(Format text){
		LocalDateTime now = LocalDateTime.now();
		return now.format(text.formatter);
	}
	
	/**
	 * 根据字符串格式日期获取它所处这个星期的星期一
	 * @param date
	 * @return
	 */
	public static String getMonday(String date){
		LocalDate currentDate = LocalDate.parse(date);
		int i = 0;
		if(!"monday".equalsIgnoreCase(currentDate.getDayOfWeek().toString())){
			while(!"monday".equalsIgnoreCase(currentDate.getDayOfWeek().toString())){
				currentDate = currentDate.minusDays(1);
				i++;
				if(i > 7){
					break;
				}
			}
		}
		if(i > 7){
			return null;
		}
		return format(currentDate);
	}
	
	/**
	 * 根据字符串格式日期、星期获取它所处这个星期的某一天
	 * @param date
	 * @param week
	 * @return
	 */
	public static String getDayOfWeek(String date, String week){
		LocalDate currentDate = LocalDate.parse(date);
		int i = 0;
		if(!currentDate.getDayOfWeek().toString().equalsIgnoreCase(week)){
			while(!currentDate.getDayOfWeek().toString().equalsIgnoreCase(week)){
				currentDate = currentDate.plusDays(1);
				if("monday".equalsIgnoreCase(currentDate.getDayOfWeek().toString())){
					currentDate = currentDate.minusWeeks(1);
				}
				i++;
				if(i > 7){
					break;
				}
			}
		}
		if(i > 7){
			return null;
		}
		return format(currentDate);
	}
}
