package util;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateTimeHelper {
	public static final String DATE_FORMAT="d-MM-yyyy";
	public static final String TIME_FORMAT="HH:mm";
	public static final long MINUTES_BEFORE=15;
	
	public static LocalDate convertStringToDate(String dateStr) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
		return LocalDate.parse(dateStr, formatter);
	}
	
	public static String convertDateToString(LocalDate date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
		return date.format(formatter);
	}
	
	public static LocalTime convertStringToTime(String timeStr) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(TIME_FORMAT);
		return LocalTime.parse(timeStr, formatter);
	}
	
	public static String convertTimeToString(LocalTime time) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(TIME_FORMAT);
		return time.format(formatter);
	}
	
	public static boolean isToday(LocalDate date) {
		return date.isEqual(LocalDate.now());
	}
	
	public static boolean isToday(String dateStr) {
		LocalDate date = convertStringToDate(dateStr);
		return isToday(date);
	}
	
	public static boolean fromToday(LocalDate date) {
		return isToday(date) || date.isAfter(LocalDate.now());
	}
	
	public static boolean checkAfterMinutesFromNow(LocalTime time) {
		Duration duration = Duration.between(LocalTime.now(), time);
		return (duration.toMinutes() > MINUTES_BEFORE);
	}
	
	public static boolean checkAfterMinutesFromNow(String timeStr) {
		LocalTime time = convertStringToTime(timeStr);
		return checkAfterMinutesFromNow(time);
	}
	
	public static int getMinutesTillMidnight(LocalTime time) {
		return (int) (Duration.between(time, LocalTime.MIDNIGHT).toMinutes()+60*24);
	}
	
	public static int getMinutesTillMidnight(String timeStr) {
		LocalTime time = convertStringToTime(timeStr);
		return getMinutesTillMidnight(time);
	}
}
