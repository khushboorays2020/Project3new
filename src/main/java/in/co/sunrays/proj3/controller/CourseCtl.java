package in.co.sunrays.proj3.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.sunrays.proj3.dto.BaseDTO;
import in.co.sunrays.proj3.dto.CourseDTO;
import in.co.sunrays.proj3.exception.ApplicationException;
import in.co.sunrays.proj3.exception.DuplicateRecordException;
import in.co.sunrays.proj3.model.CourseModelInt;
import in.co.sunrays.proj3.model.ModelFactory;
import in.co.sunrays.proj3.util.DataUtility;
import in.co.sunrays.proj3.util.DataValidator;
import in.co.sunrays.proj3.util.PropertyReader;
import in.co.sunrays.proj3.util.ServletUtility;
 
/**
 * Course functionality Controller. Performs operation for add, update,
 * delete and get College
 *  
 * @author SUNRAYS Technologies
 * @version 1.0
 * @Copyright (c) SUNRAYS Technologies
 */
@ WebServlet(name="CourseCtl",urlPatterns={"/ctl/CourseCtl"})
public class CourseCtl extends BaseCtl {
	 
    private static final long serialVersionUID = 1L;
 
    private static Logger log = Logger.getLogger(CourseCtl.class);
    
    
 
    @Override
    protected boolean validate(HttpServletRequest request) {
 
log.debug("CourceCtl Method validate Started");
		
		boolean pass=true;
		
		if(DataValidator.isNull(request.getParameter("name"))){
			
			
			request.setAttribute("name",PropertyReader.getValue("error.require","name"));
			
			pass=false;
		}
		else if(!DataValidator.isCourse(request.getParameter("name"))){
			
			
			request.setAttribute("name",PropertyReader.getValue("error.course","Invalid "));
			
			pass=false;
		}
		
		
		
		if(DataValidator.isNull(request.getParameter("duration"))){
			
			request.setAttribute("duration1",PropertyReader.getValue("error.require","Duration"));
			
			pass=false;
		}
		else if(!DataValidator.isInteger(request.getParameter("duration"))){
			
			request.setAttribute("duration1",PropertyReader.getValue("error.integer","Duration "));
			
			pass=false;
		}
		
		 log.debug("CourceCtl Method validate Ended");
               System.out.println("validate");
	        return pass;

   }
 
    @Override
    protected BaseDTO populateDTO(HttpServletRequest request) {
 
        log.debug("CourseCtl Method populateDTO Started");
 
       CourseDTO dto = new CourseDTO();
       
    	
       
		 dto.setId(DataUtility.getLong(request.getParameter("id")));
		
		 
		 dto.setName(DataUtility.getString(request.getParameter("name")));
		
		  
		 dto.setDuration(DataUtility.getInt(request.getParameter("duration")));
		
		 
		 
		  log.debug("CourceCtl Method populatebean Ended");
		  System.out.println("populate Baean");
		 		    
	        populateDTO(dto, request);
	 
	        return dto; 
   }

    
    /**
     * Contains Display logics
     */    
   	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   		
   		log.debug("CourseCtl Method doGet Started");
		 System.out.println("get suru");
		 String op=DataUtility.getString(request.getParameter("operation"));
		 
		CourseModelInt model = ModelFactory.getInstance().getCourseModel();
		  
		 long id=DataUtility.getLong(request.getParameter("id"));
		 
		 if(id>0 || op!=null){
			 System.out.println("id>0");	 
			CourseDTO dto =new CourseDTO();
			 
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
 
        log.debug("CourseCtl Method doGet Started");
 
        String op = DataUtility.getString(request.getParameter("operation"));
 
        // get model
        CourseModelInt model = ModelFactory.getInstance().getCourseModel();
		
        long id = DataUtility.getLong(request.getParameter("id"));
        CourseDTO dto = (CourseDTO)populateDTO(request);
        
		 if (OP_SAVE.equalsIgnoreCase(op)) {
			 System.out.println("save Start");
	            try {
	               
	                   //model.update(bean);
	                
	               	//System.out.println("if me gaya");
	                   long pk;
					try {
						pk = model.add(dto);
						// bean.setId(pk);
						 ServletUtility.setSuccessMessage("Course successfully added",request);
						 //ServletUtility.setDto(dto, request);
			               System.out.println("Save end");
					
					} catch (DuplicateRecordException e) {
						// TODO Auto-generated catch block
						//e.printStackTrace();
			
						ServletUtility.setErrorMessage("Course already exists", request);
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
		               
		               ServletUtility.setSuccessMessage("Course successfully updated",request);
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
	        		   ServletUtility.setErrorMessage("Course already exists", request);
	        		   
					e.printStackTrace();
				}
	        	   
	        	   
	        	   
	           }
	        	   
	           
		  else if (OP_RESET.equalsIgnoreCase(op)) {
	     	System.out.println("cancle Start");
	         ServletUtility.forward(getView(), request, response);
	     	System.out.println("cancle end");
	         return;
	         
	     }else if (OP_CANCEL.equalsIgnoreCase(op)) {

	            ServletUtility.redirect(ORSView.COURSE_LIST_CTL, request,
	                    response);
	            return;

	        }

		 ServletUtility.forward(getView(), request, response);
		 
		 log.debug("CourceCtl Method doPOst Ended");

    }
 
    @Override
    protected String getView() {
        return ORSView.COURSE_VIEW;
    }
 
}
