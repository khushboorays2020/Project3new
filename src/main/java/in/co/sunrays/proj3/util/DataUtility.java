package in.co.sunrays.proj3.util;


import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Data Utility class used to format data from one format to another
 * 
 * @author Decorator
 * @version 1.0
 * @Copyright (c) Decorator
 */
public class DataUtility {
	 /**
     * Application Date Format
     */
	public static final String APP_DATE_FORMAT = "MM/dd/yyyy";
	public static final String APP_TIME_FORMAT = "MM/dd/yyyy HH:mm:ss";
	
	 /**
     * Date formatter
     */
	private static final SimpleDateFormat formatter = new SimpleDateFormat(APP_DATE_FORMAT);
	private static final SimpleDateFormat timeFormatter = new SimpleDateFormat(APP_TIME_FORMAT);
	
	
	 /**
     * Trims the trailing and leading spaces of a String
     * 
     * @param val
     * @return
     */
	
	public static String getString(String val) {
		if(DataValidator.isNotNull(val)) {
			return val.trim();
		}
		else {
			return val;
		}
	}
	
	/**
     * Converts String into Date
     * 
     * @param val
     * @return
     */
	public static Date getDate(String val) {
		Date date = null;
		try {
			date = formatter.parse(val);
		}catch(Exception e) {
			
		}
		return date;
	}
	
	/**
     * Converts and Object to String
     * 
     * @param val
     * @return
     */
	
	public static String getStringData(Object val) {
		if(val!=null) {
			return val.toString();
		}
		else {
			return "";
		}
	}
	
	 /**
     * Converts String into Integer
     * 
     * @param val
     * @return
     */
	public static int getInt(String val) {
		if(DataValidator.isInteger(val)) {
			return Integer.parseInt(val);
		}
		else {
			return 0;
		}
	}
	
	   /**
     * Converts String into Long
     * 
     * @param val
     * @return
     */
	
	public static long getLong(String val) {
		if(DataValidator.isLong(val)) {
			return Long.parseLong(val);
		}
		else {
			return 0;
		}
	}
	
	 /**
     * Converts Date into String
     * 
     * @param date
     * @return
     */
	
	public static String getDateString(Date date) {
		try {
			return formatter.format(date);
		}
		catch(Exception e) {
			
		}
		return "";
	}
	
	 /**
     * Converts String into Time
     * 
     * @param val
     * @return
     */
	
	public static Timestamp getTimestamp(String val) {
		Timestamp timeStamp = null;
		try {
			  timeStamp = new Timestamp((timeFormatter.parse(val)).getTime());
		}
		catch(Exception e) {
			return null;
		}
		return timeStamp;
	}
	
	 /**
     * Converts Long into Time
     * 
     * @param val
     * @return
     */
	
	public static Timestamp getTimestamp(long l) {
		Timestamp timeStamp = null;
		try {
			 timeStamp = new Timestamp(l);
		}
		catch(Exception e) {
			return null;
		}
		return timeStamp;
	}
	/*get current timestamp*/	
	
		public static Timestamp getCurrentTimestamp() {
			Timestamp timeStamp = null;
			try {
				timeStamp = new Timestamp(new Date().getTime());
			}
			catch(Exception e){
				
			}
			return timeStamp;
		}
	
		
		 public static long getTimestamp(Timestamp tm) {
		        try {
		            return tm.getTime();
		        } catch (Exception e) {
		            return 0;
		        }
		    }

	/*public static void main(String[] args) {
		//System.out.println(getString("  Ketki  "));
		System.out.println(getDate("04/01/2019"));
		//DataUtility du = new DataUtility();
		//System.out.println(getStringData(du));
		//System.out.println(getInt("rto"));
		//System.out.println(getLong("rhyiy"));
		
		//Date d = new Date();
		//System.out.println(getDateString(d));
		//System.out.println(getTimestamp("12/11/2018 21:10:00"));
		//System.out.println(getTimestamp(23456));
		System.out.println(getCurrentTimestamp());
		//System.out.println(getTimestamp());
	}*/
}
