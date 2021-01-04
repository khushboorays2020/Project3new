package in.co.sunrays.proj3.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * This class validates input data
 * 
 * @author MEMENTO
 * @version 1.0
 * @Copyright (c) SunilOS
 */

public class DataValidator1 {

	/**
	 * Checks if value is Null
	 * 
	 * @param val
	 * @return
	 */
	public static boolean isNull(String val) {
		if (val == null || val.trim().length() == 0) {
			return true;
		} else {
			return false;
		}
	}
	

	/**
	 * Checks if value is NOT Null
	 * 
	 * @param val
	 * @return
	 */
	public static boolean isNotNull(String val) {
		return !isNull(val);
	}

	/**
	 * Checks if value is an Integer
	 * 
	 * @param val
	 * @return
	 */

	public static boolean isInteger(String val) {

		if (isNotNull(val)) {
			try {
				Integer.parseInt(val);
				return true;
			} catch (NumberFormatException e) {
				return false;
			}

		} else {
			return false;
		}
	}

	/**
	 * Checks if value is Long
	 * 
	 * @param val
	 * @return
	 */
	public static boolean isLong(String val) {
		if (isNotNull(val)) {
			try {
				Long.parseLong(val);
				return true;
			} catch (NumberFormatException e) {
				return false;
			}

		} else {
			return false;
		}
	}

	/**
	 * Checks if value is valid Email ID
	 * 
	 * @param val
	 * @return
	 */
	public static boolean isEmail(String val) {

		String emailreg = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

		if (isNotNull(val)) {
			try {
				return val.matches(emailreg);
			} catch (NumberFormatException e) {
				return false;
			}

		} else {
			return false;
		}
	}
	/**
	 * Checks if value is valid QUalification
	 * 
	 * @param val
	 * @return
	 */
	public static boolean isQua(String val) {

		String emailreg = "^[A-Za-z]+(|.)+[A-Za-z]";

		if (isNotNull(val)) {
			try {
				return val.matches(emailreg);
			} catch (NumberFormatException e) {
				return false;
			}

		} else {
			return false;
		}
	}
	/**
	 * Checks if value is valid First name
	 * 
	 * @param val
	 * @return
	 */
	public static boolean isName(String val) {

	
		String emailreg = "^[A-Za-z ]*";

		if (isNotNull(val)) {
			try {
				return val.matches(emailreg);
			} catch (NumberFormatException e) {
				return false;
			}

		} else {
			return false;
		}
	}
	/**
	 * Checks if value is valid Last name
	 * 
	 * @param val
	 * @return
	 */
	public static boolean isLastName(String val) {
		String regex = "[a-zA-Z]+\\.?";
		if (isNotNull(val)) {
			try {
				return val.matches(regex);
			} catch (NumberFormatException e) {
				return true;
			}
		} else {
			return false;
		}
	}
	/**
	 * Checks if value is valid Last name
	 * 
	 * @param val
	 * @return
	 */
	public static boolean isRoll(String val) {
		String regex = "[0-9]+[a-zA-Z_]+[0-9]+";
		if(isNotNull(val)) {
			return val.matches(regex);
		}else {
			return false;
		}
		
	}
	/**
	 * Checks if value is valid Last name
	 * 
	 * @param val
	 * @return
	 */
	public static boolean isSpace(String val) {
		String regex = ".*\\s+.*";
		if (isNotNull(val)) {
			try {
				return val.matches(regex);
			} catch (NumberFormatException e) {
				return true;
			}
		} else {
			return false;
		}
	}
	/**
	 * Checks length of password
	 * 
	 * @param val
	 * @return
	 */
	public static boolean isPwdLength(String val) {

		String regex = "^.{6,12}$";
		if (isNotNull(val)) {
			try {
				return val.matches(regex);
			} catch (NumberFormatException e) {
				return false;
			}
		} else {
			return false;
		}

	}
	/**
	 * Checks if value is valid Password
	 * 
	 * @param val
	 * @return
	 */
	public static boolean isValidPassword(String val) {

		String regex = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,12}$";
		if (isNotNull(val)) {
			try {
				return val.matches(regex);
			} catch (NumberFormatException e) {
				return false;
			}
		} else {
			return false;
		}

	}
	/**
	 * Checks if Date selected is greator than 17 years
	 * 
	 * @param val
	 * @return
	 */
	public static boolean isDateGreater(String val) {
	    SimpleDateFormat format= new SimpleDateFormat("MM/dd/yyyy");
		Date date= new Date();
	    format.format(date);
		if(DataUtility.getDate(val).after(date)) {
			return false;
		}
		else{
			return true;
		}
	}
	/**
	 * Checks if value is valid Date
	 * 
	 * @param val
	 * @return
	 */
	public static boolean isValidDate(String val) throws ParseException {
	    SimpleDateFormat format= new SimpleDateFormat("MM/dd/yyyy");
		Date date= format.parse(val);
		Date today= new Date();
	    Calendar cal= Calendar.getInstance();
	    cal.setTime(today);
	    cal.add(Calendar.YEAR, -17);
	    Date before= cal.getTime();
	    
		if(before.compareTo(date)==-1) {
			return false;
		}
		else{
			return true;
		}
	}
	/**
	 * Checks if value is valid Mobile Number
	 * 
	 * @param val
	 * @return
	 */
	public static boolean isMobile(String val) {
		String regex = "^[7-9][0-9]{9}$";
		if (isNotNull(val)) {
			try {
				return val.matches(regex);
			} catch (NumberFormatException e) {
				return true;
			}
		} else {
			return false;
		}
	}
	/**
	 * Checks if value is valid Mobile Number
	 * 
	 * @param val
	 * @return
	 */
	public static boolean isNumber(String val) {
	String regex = "[A-Za-z ~!@#$%^&*_=-|?/><.,]*";
		if(val.matches(regex)) {
			return false;
			
		}
		else {
			return true;
			
		}
	}
	/**
	 * Checks if Mobile Number is of 10 digits
	 * 
	 * @param val
	 * @return
	 */
	public static boolean isMobileNo(String val) {
		if(val.length()==10) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Checks if value is Date
	 * 
	 * @param val
	 * @return
	 */
	public static boolean isDate(String val) {

		Date d = null;
		if (isNotNull(val)) {
			d = DataUtility.getDate(val);
		}
		return d != null;
	}
	/**
	 * Checks if value is valid exam date
	 * 
	 * @param val
	 * @return
	 */
	public static boolean futureDate(String val) throws ParseException {

		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		Date userDate = format.parse(val);
		Date todayDate = new Date();
		if (userDate.compareTo(todayDate) == -1 || userDate.compareTo(todayDate) == 0) {
			return false;
		} else {
			return true;
		}
	}
	

}