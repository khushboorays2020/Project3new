package in.co.sunrays.proj3.controller;


import in.co.sunrays.proj3.dto.BaseDTO; 
import in.co.sunrays.proj3.dto.RoleDTO; 
import in.co.sunrays.proj3.dto.UserDTO; 
import in.co.sunrays.proj3.exception.ApplicationException; 
import in.co.sunrays.proj3.exception.DuplicateRecordException; 
import in.co.sunrays.proj3.model.ModelFactory; 
import in.co.sunrays.proj3.model.UserModelInt; 
import in.co.sunrays.proj3.util.DataUtility; 
import in.co.sunrays.proj3.util.DataValidator; 
import in.co.sunrays.proj3.util.PropertyReader; 
import in.co.sunrays.proj3.util.ServletUtility; 
 
import java.io.IOException;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse; 
 
import org.apache.log4j.Logger; 
 
/** 
 * User registration functionality Controller. Performs operation for User 
 * Registration 
 *  
 * @author SUNRAYS Technologies 
 * @version 1.0 
 * @Copyright (c) SUNRAYS Technologies 
 */ 
@WebServlet(name="UserRegistrationCtl",urlPatterns={"/UserRegistrationCtl"})

public class UserRegistrationCtl extends BaseCtl { 
 
    public static final String OP_SIGN_UP = "SignUp";
   
 
    private static Logger log = Logger.getLogger(UserRegistrationCtl.class); 
 
    @Override 
    protected boolean validate(HttpServletRequest request) { 
 
        log.debug("UserRegistrationCtl Method validate Started"); 
 
        boolean pass = true; 
 
        String login = request.getParameter("login"); 
        String dob = request.getParameter("dob"); 
 
        if (DataValidator.isNull(request.getParameter("firstname"))) { 
            request.setAttribute("firstname", 
                    PropertyReader.getValue("error.require", "First Name")); 
            pass = false; 
        } 
        if (DataValidator.isNull(request.getParameter("lastname"))) { 
            request.setAttribute("lastname", 
                    PropertyReader.getValue("error.require", "Last Name")); 
            pass = false; 
        } 
        if (DataValidator.isNull(login)) { 
            request.setAttribute("login", 
                    PropertyReader.getValue("error.require", "Login Id")); 
            pass = false; 
        } else if (!DataValidator.isEmail(login)) { 
            request.setAttribute("login", 
                    PropertyReader.getValue("error.email", "Login ")); 
            pass = false; 
        } 
        if (DataValidator.isNull(request.getParameter("password"))) { 
            request.setAttribute("password", 
                    PropertyReader.getValue("error.require", "Password")); 
            pass = false; 
        } 
        if (DataValidator.isNull(request.getParameter("confirmpassword"))) { 
            request.setAttribute("confirmpassword1", PropertyReader.getValue( 
                    "error.require", "Confirm Password")); 
            pass = false; 
        } 
        if (DataValidator.isNull(request.getParameter("gender"))) { 
            request.setAttribute("gender", 
                    PropertyReader.getValue("error.require", "Gender")); 
            pass = false; 
        } 
        if (DataValidator.isNull(dob)) { 
            request.setAttribute("dob", 
                    PropertyReader.getValue("error.require", "Date of birth")); 
            pass = false; 
        }
 
              if (DataValidator.isNull(request.getParameter("mobile"))) { 
            request.setAttribute("mobile", 
                    PropertyReader.getValue("error.require", "Mobile")); 
            pass = false; 
        } 

        
        log.debug("UserRegistrationCtl Method validate Ended"); 
 
        return pass; 
    } 
 
    @Override 
    protected BaseDTO populateDTO(HttpServletRequest request) { 
 
        log.debug("UserRegistrationCtl Method populateDTO Started"); 
 
        UserDTO dto = new UserDTO(); 
 
        dto.setId(DataUtility.getLong(request.getParameter("id"))); 
 
        dto.setRoleId(RoleDTO.STUDENT); 
 
        dto.setFirstName(DataUtility.getString(request 
                .getParameter("firstname"))); 
 
        dto.setLastName(DataUtility.getString(request.getParameter("lastname"))); 
 
        dto.setLogin(DataUtility.getString(request.getParameter("login"))); 
 
        dto.setPassword(DataUtility.getString(request.getParameter("password"))); 
 
        dto.setConfirmPassword(DataUtility.getString(request 
                .getParameter("confirmpassword"))); 
 
        dto.setGender(DataUtility.getString(request.getParameter("gender"))); 
 
        dto.setDob(DataUtility.getDate(request.getParameter("dob")));
     
        dto.setMobileNo(DataUtility.getString(request.getParameter("mobile")));
 
        log.debug("UserRegistrationCtl Method populateDTO Ended"); 
        
        populateDTO(dto, request);
 
        return dto; 
    } 
    
    /**
     * Display concept of user registration
     */
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
    	
    	log.debug("UserRegistrationCtl Method doGet Started");
        
    	ServletUtility.forward(getView(), request, response);

    }
    

    /**
     * Submit concept of user registration
     */
   
   
    protected void doPost(HttpServletRequest request, 
            HttpServletResponse response) throws ServletException, IOException { 
    	
    	
        log.debug("UserRegistrationCtl Method doGet Started"); 
 
        String op = DataUtility.getString(request.getParameter("operation")); 
        
        UserModelInt model = ModelFactory.getInstance().getUserModel(); 
        
        if (OP_SIGN_UP.equalsIgnoreCase(op)) { 
        
        	UserDTO dto = (UserDTO) populateDTO(request); 
            
            try { 
                long pk = model.registerUser(dto); 
                ServletUtility.setSuccessMessage("user added succefully", request);
                ServletUtility.setDto(dto, request);
                ServletUtility.forward(ORSView.USER_REGISTRATION_VIEW1, request, 
                        response); 
             } catch (ApplicationException e) { 
            	
                log.error(e); 
                ServletUtility.handleException(e, request, response); 
                return; 
             } catch (DuplicateRecordException e) { 
                log.error(e); 
                ServletUtility.setDto(dto, request); 
                ServletUtility.setErrorMessage("Login id already exists", 
                        request); 
               
              } 
 
              }
               else if(OP_RESET.equalsIgnoreCase(op)) {
        	   ServletUtility.forward(getView(), request, response);
        
             }
         
           
               log.debug("UserRegistrationCtl Method doGet Ended"); 
    } 
 
                @Override 
                protected String getView() { 
              
    	        return ORSView.USER_REGISTRATION_VIEW1; 
    } 
 
}
