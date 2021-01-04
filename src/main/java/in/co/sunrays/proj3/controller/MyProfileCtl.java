package in.co.sunrays.proj3.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import in.co.sunrays.proj3.dto.BaseDTO;
import in.co.sunrays.proj3.dto.UserDTO;
import in.co.sunrays.proj3.exception.ApplicationException;
import in.co.sunrays.proj3.exception.DuplicateRecordException;
import in.co.sunrays.proj3.model.ModelFactory;
import in.co.sunrays.proj3.model.UserModelInt;
import in.co.sunrays.proj3.util.DataUtility;
import in.co.sunrays.proj3.util.DataValidator;
import in.co.sunrays.proj3.util.PropertyReader;
import in.co.sunrays.proj3.util.ServletUtility;
 
/**
 * My Profile functionality Controller. Performs operation for update
 * your Profile
 *  
 * @author SUNRAYS Technologies
 * @version 1.0
 * @Copyright (c) SUNRAYS Technologies
 */
 

@ WebServlet(name="MyProfileCtl",urlPatterns={"/ctl/MyProfileCtl"})
public class MyProfileCtl extends BaseCtl {
 
    public static final String OP_CHANGE_MY_PASSWORD = "ChangePassword";
 
    private static Logger log = Logger.getLogger(MyProfileCtl.class);
 
    @Override
    protected boolean validate(HttpServletRequest request) {
 
    	
        log.debug("MyProfileCtl Method validate Started");
        
        boolean pass=true;
        
        String op=DataUtility.getString(request.getParameter("operation"));
       

		if(OP_CHANGE_MY_PASSWORD.equalsIgnoreCase(op)){
			
			return pass;
		}
		
	
      /*  if(OP_SAVE.equalsIgnoreCase(op)){
        	return pass;
        }
        
      */  if (DataValidator.isNull(request.getParameter("firstName"))){
		request.setAttribute("firstName",PropertyReader.getValue("error.require","First Name"));
		pass=false;
		}
      
      else if  (!DataValidator.isName(request.getParameter("firstName"))){
  		request.setAttribute("firstName",PropertyReader.getValue("error.name","Invalid "));
  		pass=false;
  		}
  		
		
		if(DataValidator.isNull(request.getParameter("lastName"))){
			request.setAttribute("lastName",PropertyReader.getValue("error.require","LastName"));
			pass=false;
			
			
		}
		else if(!DataValidator.isName(request.getParameter("lastName"))){
			request.setAttribute("lastName",PropertyReader.getValue("error.name","Invalid "));
			pass=false;
				}
		
		
		if(DataValidator.isNull(request.getParameter("dob")))
		{
		request.setAttribute("dob",PropertyReader.getValue("error.require","Date of Birth"));
		pass=false;
		}
		
		if(DataValidator.isNull(request.getParameter("mobileNo"))){
			
			request.setAttribute("mobileNo", PropertyReader.getValue("error.require","mobile number "));
			pass=false;
		}
		
		else if(!DataValidator.isMobileNo(request.getParameter("mobileNo"))){
			
			request.setAttribute("mobileNo", PropertyReader.getValue("error.mobile","Invalid mobile "));
			pass=false;
		}
		if(DataValidator.isNull(request.getParameter("gender"))){
			request.setAttribute("gender",PropertyReader.getValue("error.require","Gender"));
			pass=false;
			
			
		}
	
		
		 log.debug("MyProfileCtl Method validate Ended");
         System.out.println("validate method");
	        return pass;
 
    }
 
    @Override
    protected BaseDTO populateDTO(HttpServletRequest request) {
        log.debug("MyProfileCtl Method populateDTO Started");
 
        UserDTO dto = new UserDTO();
 
        dto.setId(DataUtility.getLong(request.getParameter("id")));
		 dto.setLogin(DataUtility.getString(request.getParameter("login")));
		 dto.setFirstName(DataUtility.getString(request.getParameter("firstName")));
		
		 dto.setLastName(DataUtility.getString(request.getParameter("lastName")));
		 dto.setGender(DataUtility.getString(request.getParameter("gender")));
		 dto.setDob(DataUtility.getDate(request.getParameter("dob")));
	     dto.setMobileNo(DataUtility.getString(request.getParameter("mobileNo")));
		
		
		 populateDTO(dto, request);

        return dto;
    }
 
    /**
     * Contains Display logics
     */    
   	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   		
   		log.debug("SubjectCtl Method doGet Started");
		 System.out.println("get suru");
		 HttpSession session=request.getSession();
		 UserDTO us=(UserDTO)session.getAttribute("user");
		 long id;
		 
		 id=us.getId();
		 String op=DataUtility.getString(request.getParameter("operation"));
		 
		UserModelInt model = ModelFactory.getInstance().getUserModel();
		  
		/* long id=DataUtility.getLong(request.getParameter("id"));
		*/ 
		 if(id>0 || op!=null){
			 System.out.println("id>0");	 
			UserDTO dto =new UserDTO();
			 
			 try {
				dto=model.findByPK(id);
				ServletUtility.setDto(dto, request);
			
			} catch (ApplicationException e) {
		       
				 log.error(e);
	             ServletUtility.handleException(e, request, response);
	             return;

			}
		 }
		 System.out.println("get puri");
		 ServletUtility.forward(getView(), request, response);
	     log.debug("timetableCtl Method doGetEnded");
	     System.out.println("do get");


   		
		}

 
    
    
	/**
     * Contains Submit logics
     */    
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        log.debug("MyprofileCtl Method doGet Started");
 
        UserDTO userdto = (UserDTO) session.getAttribute("user");
        long id = userdto.getId();
        String op = DataUtility.getString(request.getParameter("operation"));
 
        // get model
        UserModelInt model = ModelFactory.getInstance().getUserModel();
 
        if (OP_SAVE.equalsIgnoreCase(op)) {
            UserDTO dto = (UserDTO) populateDTO(request);
            try {
                if (id > 0) {
                    userdto.setFirstName(dto.getFirstName());
                    userdto.setLastName(dto.getLastName());
                    userdto.setGender(dto.getGender());
                    userdto.setMobileNo(dto.getMobileNo());
                    userdto.setDob(dto.getDob());
                    model.update(userdto);
                    ServletUtility.setDto(dto, request);
                    ServletUtility.setSuccessMessage(
                            "Profile has been updated successfully ", request);
 
                }
                ServletUtility.setDto(dto, request);
                ServletUtility.setSuccessMessage(
                        "Profile has been updated successfully ", request);
            } catch (ApplicationException e) {
                log.error(e);
                ServletUtility.handleException(e, request, response);
                return;
            } catch (DuplicateRecordException e) {
            	e.printStackTrace();
                ServletUtility.setDto(dto, request);
                ServletUtility.setErrorMessage("Login id already exists",
                        request);
            }
        } else if (OP_CHANGE_MY_PASSWORD.equalsIgnoreCase(op)) {
 
            ServletUtility.redirect(ORSView.CHANGE_PASSWORD_CTL, request,
                    response);
            return;
 
        } else { // View page
            if (id > 0 || op != null) {
                System.out.println("in id > 0  condition");
                UserDTO dto;
                try {
                    dto = model.findByPK(id);
                    ServletUtility.setDto(dto, request);
                } catch (ApplicationException e) {
                    log.error(e);
                    ServletUtility.handleException(e, request, response);
                    return;
                }
            }
 
        }
 
        ServletUtility.forward(ORSView.MY_PROFILE_VIEW, request, response);
 
        log.debug("MyProfileCtl Method doGet Ended");
    }
 
    @Override
    protected String getView() {
        return ORSView.MY_PROFILE_VIEW;
    }
 
}
