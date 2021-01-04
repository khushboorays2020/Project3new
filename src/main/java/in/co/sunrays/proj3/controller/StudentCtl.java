package in.co.sunrays.proj3.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.sunrays.proj3.dto.BaseDTO;
import in.co.sunrays.proj3.dto.StudentDTO;
import in.co.sunrays.proj3.exception.ApplicationException;
import in.co.sunrays.proj3.exception.DuplicateRecordException;
import in.co.sunrays.proj3.model.CollegeModelInt;
import in.co.sunrays.proj3.model.ModelFactory;
import in.co.sunrays.proj3.model.StudentModelInt;
import in.co.sunrays.proj3.util.DataUtility;
import in.co.sunrays.proj3.util.DataValidator;
import in.co.sunrays.proj3.util.PropertyReader;
import in.co.sunrays.proj3.util.ServletUtility;
 
/**
 * Student functionality Controller. Performs operation for add, update,
 * delete and get Student
 *  
 * @author SUNRAYS Technologies
 * @version 1.0
 * @Copyright (c) SUNRAYS Technologies
 */
@ WebServlet(name="StudentCtl",urlPatterns={"/ctl/StudentCtl"}) 
public class StudentCtl extends BaseCtl {
 
    private static Logger log = Logger.getLogger(StudentCtl.class);
 
    @Override
    protected void preload(HttpServletRequest request) {
        CollegeModelInt model = ModelFactory.getInstance().getCollegeModel();
        try {
            List l = model.list();
            request.setAttribute("collegeList", l);
        } catch (ApplicationException e) {
            log.error(e);
        }
 
    }
 
    @Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("StudentCtl Method validate Started");
		System.out.println("StudentCtl Method validate Started");

		boolean pass = true;

		String op = DataUtility.getString(request.getParameter("operation"));
		String email = request.getParameter("email");
		String dob = request.getParameter("dob");

		if (DataValidator.isNull(request.getParameter("firstName"))) {
			request.setAttribute("firstName", PropertyReader.getValue("error.require", "First name"));
			pass = false;
		} else if (DataValidator.isWhiteSpace(request.getParameter("firstName"))
				|| DataValidator.isSpecial(request.getParameter("firstName"))
				|| DataValidator.isNumber(request.getParameter("firstName"))
				|| DataValidator.isName(request.getParameter("firstName"))) {

			if (DataValidator.isWhiteSpace(request.getParameter("firstName"))) {
				request.setAttribute("firstName", PropertyReader.getValue("error.whiteSpace", "First name"));
				pass = false;
			}
			if (DataValidator.isSpecial(request.getParameter("firstName"))) {
				request.setAttribute("firstName", PropertyReader.getValue("error.special", "First name"));
				pass = false;
			}
			if (DataValidator.isNumber(request.getParameter("firstName"))) {
				request.setAttribute("firstName", PropertyReader.getValue("error.number", "First name"));
				pass = false;
			}
			if (DataValidator.isName(request.getParameter("firstName"))) {
				request.setAttribute("firstName", PropertyReader.getValue("error.name", "First name"));
				pass = false;
			}
			if (DataValidator.isWhiteSpace(request.getParameter("firstName"))
					&& DataValidator.isSpecial(request.getParameter("firstName"))) {
				request.setAttribute("firstName", PropertyReader.getValue("error.spacespecial", "First name"));
				pass = false;
			}
			if (DataValidator.isWhiteSpace(request.getParameter("firstName"))
					&& DataValidator.isNumber(request.getParameter("firstName"))) {
				request.setAttribute("firstName", PropertyReader.getValue("error.spacenumber", "First name"));
				pass = false;
			}
			if (DataValidator.isSpecial(request.getParameter("firstName"))
					&& DataValidator.isNumber(request.getParameter("firstName"))) {
				request.setAttribute("firstName", PropertyReader.getValue("error.specialnumber", "First name"));
				pass = false;
			}

		}

		if (DataValidator.isNull(request.getParameter("lastName"))) {
			request.setAttribute("lastName", PropertyReader.getValue("error.require", "Last name"));
			pass = false;
		} else if (DataValidator.isWhiteSpace(request.getParameter("firstName"))
				|| DataValidator.isSpecial(request.getParameter("lastName"))
				|| DataValidator.isNumber(request.getParameter("lastName"))
				|| DataValidator.isName(request.getParameter("lastName")))
			if (DataValidator.isWhiteSpace(request.getParameter("firstName"))) {
				request.setAttribute("firstName", PropertyReader.getValue("error.whiteSpace", "First name"));
				pass = false;
			}
		{

			if (DataValidator.isSpecial(request.getParameter("lastName"))) {
				request.setAttribute("lastName", PropertyReader.getValue("error.special", "Last name"));
				pass = false;
			}
			if (DataValidator.isNumber(request.getParameter("lastName"))) {
				request.setAttribute("lastName", PropertyReader.getValue("error.number", "Last name"));
				pass = false;
			}

			if (DataValidator.isName(request.getParameter("lastName"))) {
				request.setAttribute("lastName", PropertyReader.getValue("error.name", "Last name"));
				pass = false;
			}
			if (DataValidator.isWhiteSpace(request.getParameter("lastName"))
					&& DataValidator.isSpecial(request.getParameter("lastName"))) {
				request.setAttribute("lastName", PropertyReader.getValue("error.spacespecial", "Last name"));
				pass = false;
			}
			if (DataValidator.isWhiteSpace(request.getParameter("lastName"))
					&& DataValidator.isNumber(request.getParameter("lastName"))) {
				request.setAttribute("lastName", PropertyReader.getValue("error.spacenumber", "Last name"));
				pass = false;
			}

			if (DataValidator.isSpecial(request.getParameter("lastName"))
					&& DataValidator.isNumber(request.getParameter("lastName"))) {
				request.setAttribute("lastName", PropertyReader.getValue("error.specialnumber", "Last name"));
				pass = false;
			}
		}

		if (DataValidator.isNull(request.getParameter("mobno"))) {
			request.setAttribute("mobno", PropertyReader.getValue("error.require", "Mobile number"));
			pass = false;
		} else if (!DataValidator.isLong(request.getParameter("mobno"))) {
			request.setAttribute("mobno", "Mobile Number must be in numbers");
			pass = false;

		} else if (!DataValidator.isMobileNo(request.getParameter("mobno"))) {
			request.setAttribute("mobno", PropertyReader.getValue("error.mobNo", "Mobile number"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("collegeId"))) {
			request.setAttribute("collegeId", PropertyReader.getValue("error.require", "College Name"));
			pass = false;
		} else if (DataValidator.isNotNull(request.getParameter("collegeId"))) {
			if ("----Select----".equals(request.getParameter("collegeId"))) {
				request.setAttribute("collegeId", PropertyReader.getValue("error.require", "College Name"));
				pass = false;
			}
		}

		if (DataValidator.isNull(email)) {
			request.setAttribute("email", PropertyReader.getValue("error.require", "Email "));
			pass = false;
		} else if (!DataValidator.isEmail(email)) {
			request.setAttribute("email", PropertyReader.getValue("error.email", "Email "));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("collegeId"))) {
			request.setAttribute("collegeId", PropertyReader.getValue("error.require", "College Name"));
			pass = false;
		}
		if (DataValidator.isNull(dob)) {
			request.setAttribute("dob", PropertyReader.getValue("error.require", "Date of birth"));
			pass = false;

		} else if (!DataValidator.isDate(dob)) {
			request.setAttribute("dob", PropertyReader.getValue("error.date", "Date of birth"));
			pass = false;
		}

		log.debug("StudentCtl Method validate Ended");

		return pass;
	}

	@Override
	protected BaseDTO populateDTO(HttpServletRequest request) {

		log.debug("StudentCtl Method populatebean Started");

		StudentDTO dto = new StudentDTO();

		dto.setId(DataUtility.getLong(request.getParameter("id")));
		System.out.println("populateBean" + dto.getId());

		dto.setFirstName(DataUtility.getString(request.getParameter("firstName")));
		System.out.println("populateBean" + dto.getFirstName());

		dto.setLastName(DataUtility.getString(request.getParameter("lastName")));
		System.out.println("populateBean" + dto.getLastName());

		dto.setDob(DataUtility.getDate(request.getParameter("dob")));
		System.out.println("populateBean" + dto.getDob());

		dto.setMobileNo(DataUtility.getString(request.getParameter("mobno")));
		System.out.println("populateBean" + dto.getMobileNo());

		dto.setEmail(DataUtility.getString(request.getParameter("email")));
		System.out.println("populateBean" + dto.getEmail());
		dto.setCollegeId(DataUtility.getLong(DataUtility.getString(request.getParameter("collegeId"))));

		System.out.println(DataUtility.getLong(request.getParameter("collegeId")));
		System.out.println("Populatebean" + request.getParameter("collegeId"));
		System.out.println("populateBean" + dto.getCollegeId());

		populateDTO(dto, request);

		log.debug("StudentCtl Method populatebean Ended");

		return dto;
	}

    /**
     * Contains Display logics
     */    
   	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   		
   		log.debug("SubjectCtl Method doGet Started");
		 System.out.println("get suru");
		 String op=DataUtility.getString(request.getParameter("operation"));
		 
		StudentModelInt model = ModelFactory.getInstance().getStudentModel();
		  
		 long id=DataUtility.getLong(request.getParameter("id"));
		 
		 if(id>0 || op!=null){
			 System.out.println("id>0");	 
			StudentDTO dto =new StudentDTO();
			 
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
	     log.debug("StudentCtl Method doGetEnded");
	     System.out.println("do get");


   		
		}



	/**
     * Contains Submit logics
     */  
    
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
 
        log.debug("StudentCtl Method doGet Started");
        System.out.println("get suru");
        String op = DataUtility.getString(request.getParameter("operation"));
 
        // get model
 
        StudentModelInt model = ModelFactory.getInstance().getStudentModel();
 
        long id = DataUtility.getLong(request.getParameter("id"));
        StudentDTO dto = (StudentDTO) populateDTO(request);
        
        if (OP_SAVE.equalsIgnoreCase(op)) {
 
           
            try {
                if (id > 0) {
                    model.update(dto);
                    ServletUtility.setDto(dto, request);
                    
                } else {
                    long pk = model.add(dto);
                    dto.setId(pk);
                }
 
                ServletUtility.setSuccessMessage("Student successfully added",
                        request);
 
            } catch (ApplicationException e) {
                log.error(e);
                ServletUtility.handleException(e, request, response);
                return;
            } catch (DuplicateRecordException e) {
                ServletUtility.setDto(dto, request);
                ServletUtility.setErrorMessage(
                        "Email Id already exists", request);
            }
 
        } else if(OP_UPDATE.equalsIgnoreCase(op)){
     	   
     	   try {
     		   if(id>0){
				model.update(dto);
				ServletUtility.setDto(dto, request);
	               
	               ServletUtility.setSuccessMessage("Student updated successfully ",request);
	               //ServletUtility.forward(getView(), request, response);
	               System.out.println("Save end");
     		   }
				
			} catch (ApplicationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
     	   
     	   catch (DuplicateRecordException e) {
				// TODO Auto-generated catch block
     		   ServletUtility.setDto(dto, request);
     		   ServletUtility.setErrorMessage("Email id already exists", request);
     		   
				e.printStackTrace();
			}
     	   
     	   
     	   
        }
        else if (OP_RESET.equalsIgnoreCase(op)) {
	     	System.out.println("cancle Start");
	         ServletUtility.forward(getView(), request, response);
	     	System.out.println("cancle end");
	         return;
	         
	     }
     	   
        else if (OP_CANCEL.equalsIgnoreCase(op)) {
 
            ServletUtility
                    .redirect(ORSView.STUDENT_LIST_CTL, request, response);
            return;
 
        } 
        ServletUtility.forward(ORSView.STUDENT_VIEW, request, response);
 
        log.debug("StudentCtl Method doGet Ended");
    }
 
    @Override
    protected String getView() {
        return ORSView.STUDENT_VIEW;
    }
 
}
