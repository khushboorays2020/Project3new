package in.co.sunrays.proj3.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.sunrays.proj3.dto.BaseDTO;
import in.co.sunrays.proj3.dto.FacultyDTO;
import in.co.sunrays.proj3.exception.ApplicationException;
import in.co.sunrays.proj3.exception.DuplicateRecordException;
import in.co.sunrays.proj3.model.CollegeModelInt;
import in.co.sunrays.proj3.model.CourseModelInt;
import in.co.sunrays.proj3.model.FacultyModelInt;
import in.co.sunrays.proj3.model.ModelFactory;
import in.co.sunrays.proj3.model.SubjectModelInt;
import in.co.sunrays.proj3.util.DataUtility;
import in.co.sunrays.proj3.util.DataValidator;
import in.co.sunrays.proj3.util.PropertyReader;
import in.co.sunrays.proj3.util.ServletUtility;
 
/**
 * Faculty functionality Controller. Performs operation for add, update,
 * delete and get College
 *  
 * @author SUNRAYS Technologies
 * @version 1.0
 * @Copyright (c) SUNRAYS Technologies
 */
@ WebServlet(name="FacultyCtl",urlPatterns={"/ctl/FacultyCtl"})
public class FacultyCtl extends BaseCtl {
	 
    private static final long serialVersionUID = 1L;
 
    private static Logger log = Logger.getLogger(FacultyCtl.class);
    
    
protected void preload(HttpServletRequest request){
		
	CourseModelInt model = ModelFactory.getInstance().getCourseModel();
	
    SubjectModelInt model1=ModelFactory.getInstance().getSubjectModel();
    
    CollegeModelInt model2=ModelFactory.getInstance().getCollegeModel();
    
		
		try {
		
			List l=model.list();
			 
			List l1=model1.list();
			
			List l2=model2.list();
		
			
			 request.setAttribute("courceName",l);
		
			 request.setAttribute("subjectName",l1);
			
			 request.setAttribute("collegeName",l2);
		
			 
			 
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error(e);
		}
	}


    @Override
    protected boolean validate(HttpServletRequest request) {
 
log.debug("FacultyCtl Method validate Started");
		
		boolean pass=true;
		System.out.println((request.getParameter("login")));
		 
		if(DataValidator.isNull(request.getParameter("fname"))){
			
			
			request.setAttribute("fname",PropertyReader.getValue("error.require","First Name"));
			
			pass=false;
		}
		else if(!DataValidator.isCourse(request.getParameter("fname"))){
			
			
			request.setAttribute("fname",PropertyReader.getValue("error.name","Invalid First "));
			
			pass=false;
		}
		
		
        if(DataValidator.isNull(request.getParameter("lname"))){
			
			
			request.setAttribute("lname",PropertyReader.getValue("error.require","Last Name"));
			
			pass=false;
		}
        
        else if(!DataValidator.isCourse(request.getParameter("lname"))){
			
			
			request.setAttribute("lname",PropertyReader.getValue("error.name","Invalid Last "));
			
			pass=false;
		}
	
	

      if(DataValidator.isNull(request.getParameter("login"))){
			
			request.setAttribute("login",PropertyReader.getValue("error.require","Login id"));
			     
			pass=false;
		}
     /* else if(!DataValidator.isEmail(request.getParameter("login"))){
			
			request.setAttribute("login",PropertyReader.getValue("error.email","Invalid "));
			
			pass=false;
		}
  */  
if(DataValidator.isNull(request.getParameter("doj"))){
			
			request.setAttribute("doj",PropertyReader.getValue("error.require","Date Of Joining"));
			
			pass=false;
		}
		

if(DataValidator.isNull(request.getParameter("qualification"))){
	
	request.setAttribute("qualification1",PropertyReader.getValue("error.require","Qualification"));
	
	pass=false;
}

if(DataValidator.isNull(request.getParameter("mobile"))){
	
	request.setAttribute("mobileno",PropertyReader.getValue("error.require","Mobile No"));
	
	pass=false;
}
else if(!DataValidator.isMobileNo(request.getParameter("mobile"))){
	
	request.setAttribute("mobileno",PropertyReader.getValue("error.mobile","Invalid Mobile "));
	
	pass=false;
}




if (DataValidator.isNull(request.getParameter("gender"))) {
    request.setAttribute("gender1",PropertyReader.getValue("error.require", "Gender"));
    pass = false;
}


if(DataValidator.isNull(request.getParameter("collegeId"))){
	request.setAttribute("collegeId1",PropertyReader.getValue("error.require","College Name"));
	
	pass=false;
}
System.out.println(request.getParameter("courceId")+"     selected course");
if(DataValidator.isNull(request.getParameter("courceId"))){
	
	request.setAttribute("courceId",PropertyReader.getValue("error.require","Cource Name"));
	
	pass=false;
}

if(DataValidator.isNull(request.getParameter("subjectId"))){
	
	request.setAttribute("subjectId1",PropertyReader.getValue("error.require","Subject Name"));
	
	pass=false;
}


if(DataValidator.isNull(request.getParameter("address"))){
	
	
	request.setAttribute("address",PropertyReader.getValue("error.require","Address"));
	
	pass=false;
}

else if(!DataValidator.isCourse(request.getParameter("address"))){
	
	
	request.setAttribute("address",PropertyReader.getValue("error.address","Address "));
	
	pass=false;
}



         log.debug("FacultyCtl Method validate Ended");
               System.out.println("validate");
               return pass;

   }
 
    @Override
    protected BaseDTO populateDTO(HttpServletRequest request) {
 
        log.debug("FacultyCtl Method populateDTO Started");
 
       FacultyDTO dto = new FacultyDTO();
       
    	
       
		 dto.setId(DataUtility.getLong(request.getParameter("id")));
		
		 
		 dto.setFirst_Name(DataUtility.getString(request.getParameter("fname")));
		  
		  
		 dto.setLast_Name(DataUtility.getString(request.getParameter("lname")));
		 
		 dto.setAddress(DataUtility.getString(request.getParameter("address")));
		
		 
		 dto.setLogin_Id(DataUtility.getString(request.getParameter("login")));
		 
		 dto.setDate_Of_joining(DataUtility.getDate(request.getParameter("doj")));
		 
		 dto.setQualification(DataUtility.getString(request.getParameter("qualification")));
		 
		 dto.setMobile_No(DataUtility.getString(request.getParameter("mobile")));
		 
		// bean.setCollege_Name(DataUtility.getString(request.getParameter("collegeId")));
		 
		 dto.setCource_Id(DataUtility.getInt(request.getParameter("courceId")));
		 
		 dto.setCource_Name(DataUtility.getString(request.getParameter("courceId")));
		 
		 System.out.println(" course  id is "+(request.getParameter("courceId")));
		 
		 dto.setSubject_Id(DataUtility.getInt(request.getParameter("subjectId")));
		 
		 dto.setSubject_Name(DataUtility.getString(request.getParameter("subjectId")));
		
		 dto.setCollege_Id(DataUtility.getInt(request.getParameter("collegeId")));
		 
		 dto.setCollege_Name(DataUtility.getString(request.getParameter("collegeName")));
		 
		 dto.setGrnder(DataUtility.getString(request.getParameter("gender")));
		 
		    
	        populateDTO(dto, request);
	 
	        return dto; 
   }

    

	/**
     * Contains Display logics
     */    
   	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   		
   		log.debug("FacultyCtl Method doGet Started");
		 System.out.println("get suru");
		 String op=DataUtility.getString(request.getParameter("operation"));
		 
		FacultyModelInt model = ModelFactory.getInstance().getFacultyModel();
		  
		 long id=DataUtility.getLong(request.getParameter("id"));
		 
		 if(id>0 || op!=null){
			 System.out.println("id>0");	 
			FacultyDTO dto =new FacultyDTO();
			 
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
     * Contains Display logics
     */    
 protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
 
        log.debug("FacultyCtl Method doGet Started");
 
        String op = DataUtility.getString(request.getParameter("operation"));
 
        // get model
        FacultyModelInt model = ModelFactory.getInstance().getFacultyModel();
		
        long id = DataUtility.getLong(request.getParameter("id"));
        FacultyDTO dto = (FacultyDTO)populateDTO(request);
        
		 if (OP_SAVE.equalsIgnoreCase(op)) {
			 System.out.println("save Start");
	            try {
	               
	                   //model.update(bean);
	                
	               	//System.out.println("if me gaya");
	                   long pk;
					try {
						pk = model.add(dto);
						// bean.setId(pk);
						 ServletUtility.setSuccessMessage("Faculty successfully added",request);
						// ServletUtility.setDto(dto, request);
			               System.out.println("Save end");
					
					} catch (DuplicateRecordException e) {
						// TODO Auto-generated catch block
						//e.printStackTrace();
			
						ServletUtility.setErrorMessage("Email id already exists", request);
						ServletUtility.setDto(dto, request);
					}
	                  
	              //ServletUtility.setBean(bean, request);
	               
	              
	           } catch (ApplicationException e) {
	           	e.printStackTrace();
	               log.error(e);
	               
	               ServletUtility.handleException(e, request, response);
	               
	               return;
	           }
	            }
	           
	           else if(OP_UPDATE.equalsIgnoreCase(op)){
	        	   
	        	   try {
	        		   if(id>0){
					model.update(dto);
					ServletUtility.setDto(dto, request);
		               
		               ServletUtility.setSuccessMessage("Faculty successfully updated",request);
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
	        		   ServletUtility.setErrorMessage("Faculty already exists", request);
	        		   
					e.printStackTrace();
				}
	        	   
	        	   
	        	   
	           }
	        	   
	           
		  else if (OP_RESET.equalsIgnoreCase(op)) {
	     	System.out.println("cancle Start");
	         ServletUtility.forward(getView(), request, response);
	     	System.out.println("cancle end");
	         return;
	         
	     }else if (OP_CANCEL.equalsIgnoreCase(op)) {

	            ServletUtility.redirect(ORSView.FACULTY_LIST_CTL, request,
	                    response);
	            return;

	        }

		 ServletUtility.forward(getView(), request, response);
		 
		 log.debug("FacultyCtl Method doPOst Ended");

    }
 
    @Override
    protected String getView() {
        return ORSView.FACULTY_VIEW;
    }
 
}
