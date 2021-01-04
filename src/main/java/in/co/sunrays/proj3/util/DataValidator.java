package in.co.sunrays.proj3.util;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * This class validates input data
 * 
 * @author Strategy
 * @version 1.0
 * Copyright (c) Strategy
 */

public class DataValidator {

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
                int i = Integer.parseInt(val);
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
                long i = Long.parseLong(val);
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
	 * Checks if value is valid First Name
	 * 
	 * @param val
	 * @return boolean
	 */
    public static boolean isName(String val) {

    	

		String name = "^[a-zA-Z]{1}$";
		if (isNotNull(val)) {
			try {

				return val.matches(name);

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
	 * Checks if value is valid Roll Number
	 * 
	 * @param val
	 * @return boolean
	 */
	public static boolean isRollNo(String val) {

		String rollno = "[0-9]+[a-zA-Z_]+[0-9]+";

		if (isNotNull(val)) {
			try {
				return val.matches(rollno);
			} catch (NumberFormatException e) {
				return false;
			}

		} else {
			return false;
		}
	}

	/**
	 * Checks if value contains any number
	 * 
	 * @param val
	 * @return boolean
	 */
	public static boolean isNumber(String val) {
		String reg = "[A-Za-z ~!@#$%^&*_=-|?/><.,]*";
		if (val.matches(reg)) {
			return false;
		} else {
			return true;
		}
	}/**
	 * Checks if value is valid Course Name
	 * 
	 * @param val
	 * @return boolean
	 */
	public static boolean isCourse(String val) {

		String mo = "^[a-zA-Zs.\\s]+$";

		if (val.matches(mo)) {
			return true;
		} else {
			return false;
		}
	}
	/**
	 * Checks if value is valid State Name
	 * 
	 * @param val
	 * @return boolean
	 */
	
	public static boolean isState(String val) {
		String reg = "([a-zA-Z]+(\\.| |))*[a-zA-Z]*";
	
		if (val.matches(reg)) {
			return false;
		} else {
			return true;
		}
	}




	/**
	 * Checks if value contains White Space
	 * 
	 * @param val
	 * @return boolean
	 */
	public static boolean isWhiteSpace(String val) {
			
		if (val.matches(".*\\s+.*")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Checks if value contains special character
	 * 
	 * @param val
	 * @return boolean
	 */
	public static boolean isSpecial(String val) {
		String reg = "[A-Za-z0-9\\s]*";

		if (val.matches(reg)) {
			return false;
		} else {
			return true;
		}
	}
	/**
	 * Checks if value is valid Mobile Number
	 * 
	 * @param val
	 * @return boolean
	 */
	
	public static boolean isMobileNo(String val) {
		String mobileRegex = "^[789][0-9]{9}$";
		if (val.matches(mobileRegex)) {
			return true;
		} else
			return false;
	}
	/**
	 * Checks if value is valid Password
	 * 
	 * @param val
	 * @return boolean
	 */
	public static boolean isPassword(String val) {

		String pwd = "^.*(?=.{8,15})(?=..*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$";

		if (isNotNull(val)) {
			try {
				return val.matches(pwd);
			} catch (NumberFormatException e) {
				return false;
			}

		} else {
			return false;
		}
	}

	/**
	 * Checks if value is valid College Name
	 * 
	 * @param val
	 * @return boolean
	 */
	// [a-zA-z0-9]*([,.0-9\\s]+[a-zA-Z0-9]*)*
	public static boolean isCollege(String val) {
		//String ch = "^[a-zA-Zs][a-zA-Z0-9]*$";
		String s="/^[a-zA-Z][a-zA-Z\\s]+$/;";
		if (isNotNull(val)) {
			try {
				return val.matches(s);
			} catch (NumberFormatException e) {
				return false;
			}

		} else {
			return false;
		}
	}

	/**
1		 * Checks if value is valid College address
	 * 
	 * @param val
	 * @return boolean
	 */
	
	public static boolean isCollegeAddress(String val) {
		String ch = "[a-zA-z0-9]*([,.0-9\\s]+[a-zA-Z0-9]*)*";
		String s1="\\d{1,5}\\s\\w.\\s(\\b\\w*\\b\\s){1,2}\\w*\\.";
		if (isNotNull(val)) {
			try {
				return val.matches(s1);
			} catch (NumberFormatException e) {
				return false;
			}

		} else {
			return false;
		}
	}

	/**
	 * Checks if value is valid phone number
	 * 
	 * @param val
	 * @return boolean
	 */
	
	public static boolean isPhoneNo(String val) {
		String phoneRegex =  "^[0][0-9]{10}$";
		if (val.matches(phoneRegex)) {
			return true;
		} else
			return false;
	}
	/**
	 * Check mark should be valid
	 * 
	 * @param val
	 * @return boolean
	 */
	
	public static boolean isMarks(String val) {
		String rg =  "^[0-9]+[0-9]*$";
		if (isNotNull(val)) {
			return true;
		} else
			return false;
	}

	
	
	public static void main(String[] args) {
		/*boolean i;
		boolean j;
		boolean k;
		i=isNull("surabhi");
		j=isNull("1");
		k=isNull("");
		System.out.println(i);
		System.out.println(j);
		System.out.println(k);
		i=isNotNull("surabhi");
		j=isNotNull("1");
		k=isNotNull("");
		System.out.println("isNotNull"+i);
		System.out.println("isNotNull"+j);
		System.out.println("isNotNull"+k);*/
		//boolean p;
		//System.out.println(isInteger("surabhi"));
		//System.out.println(isInteger("22#"));
		//System.out.println(isInteger("22"));
		//System.out.println(isDate("45/78/1995"));
		System.out.println(isPassword("surabhi123$"));
		
		
	}

  
}
