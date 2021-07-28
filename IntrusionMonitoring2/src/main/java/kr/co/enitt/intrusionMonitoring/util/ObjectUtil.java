package kr.co.enitt.intrusionMonitoring.util;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

public class ObjectUtil {
	@SuppressWarnings("rawtypes")
	public static boolean isEmpty(Object obj) {
		if (obj instanceof String) return obj == null || "".equals(obj.toString().trim());
		else if (obj instanceof List) return obj == null || ((List) obj).isEmpty();
		else if (obj instanceof Map) return obj == null || ((Map) obj).isEmpty();
		else if (obj instanceof Object[]) return obj == null || Array.getLength(obj) == 0;
		else return obj == null;
	}
	public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }
	public static boolean isNotEmpty(Object obj) {
		return !isEmpty(obj);
	}
	
	public static String toString(Object obj, String def) {
		if (obj != null) { return String.valueOf(obj); }
		else { return def; }
	}
	
	public static int toInt(Object obj, int def) {
		try { return (int) toDouble(String.valueOf(obj), def); }
		catch (Exception e) { return def; }
	}
	public static float toFloat(Object obj, float def) {
		try { return (float) toDouble(String.valueOf(obj), def); }
		catch (Exception e) { return def; }
	}
	
	public static long toLong(Object obj, long def) {
		try { return Long.parseLong(String.valueOf(obj)); }
		catch (Exception e) { return def; }
	}
	
	public static double toDouble(Object obj, double def) {
		try { return Double.parseDouble(String.valueOf(obj)); }
		catch (Exception e) {return def; }
	}
	
	public static boolean toBoolean(Object obj, boolean def) {
		try { return Boolean.parseBoolean(String.valueOf(obj)); }
		catch (Exception e) { return def; }
	}
	
	public static void log(String obj){
		if(obj != null){
			System.out.println(obj);
		}
	}
	
	public static String getMapValue(Object obj, String str){
		try {
			Map<String, Object> map = new HashMap<String,Object>();
			map = BeanUtils.describe(obj);
			String value = toString(map.get(str),"");
			return value;
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			return "";
		}
	}
	
	public static Map<String, Object> toMap(Object obj){
		try {
			Map<String, Object> map = new HashMap<String,Object>();
			map = BeanUtils.describe(obj);
			return map;
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			return null;
		}
	}
	
	public static Object getObject( Map<String, Object> map, String str){
		return map.get(str);
	}
	
	public static ArrayList<Float> getTempSprit(String date) throws Exception {
		ArrayList<Float> result = new ArrayList<Float>();
		String strArray[] = date.split(",");
		for(String str : strArray) {
			result.add(ObjectUtil.toFloat(str, 0));
		}
		return result;
	}
	
	public static Float getTemp(String date, int num) throws Exception {
		ArrayList<Float> tempArray = getTempSprit(date);
		return tempArray.get(num);
	}
	
	@SuppressWarnings("deprecation")
	public static Long getUTC(String date) throws Exception {
		ArrayList<Integer> dateArray = getDateSprit(date); 
		
		return Date.UTC(dateArray.get(0)-1900, dateArray.get(1)-1 ,dateArray.get(2),dateArray.get(3),dateArray.get(4),dateArray.get(5));
	}
	public static ArrayList<Integer> getDateSprit(String date) throws Exception {
		ArrayList<Integer> result = new ArrayList<Integer>();
		String strArray[] = date.split("-| |:");
		for(String str : strArray) {
			result.add(ObjectUtil.toInt(str, 0));
		}
		return result;
	}
	
	
	
	public static String getNow(String addType, int addInt) throws Exception {
		Calendar beforeYear = Calendar.getInstance(); 
		if("SECOND".equals(addType)) {
			beforeYear.add(Calendar.SECOND , addInt);
		}else if("MINUTE".equals(addType)) {
			beforeYear.add(Calendar.MINUTE , addInt);
		}else if("HOUR".equals(addType)) {
			beforeYear.add(Calendar.HOUR , addInt);
		}else if("DATE".equals(addType)) {
			beforeYear.add(Calendar.DATE , addInt);
		}else if("MONTH".equals(addType)) {
			beforeYear.add(Calendar.MONTH , addInt);
		}else if("YEAR".equals(addType)) {
			beforeYear.add(Calendar.YEAR , addInt);
		}
		
		SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd H:m:s"); 
		String timeStr = time.format(beforeYear.getTime());
		
		return timeStr;
	}
	
}
