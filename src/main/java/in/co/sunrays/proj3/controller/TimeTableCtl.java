package in.co.sunrays.proj3.controller;

import java.io.IOException;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.sunrays.proj3.dto.BaseDTO;
import in.co.sunrays.proj3.dto.TimeTableDTO;
import in.co.sunrays.proj3.exception.ApplicationException;
import in.co.sunrays.proj3.exception.DuplicateRecordException;
import in.co.sunrays.proj3.model.CourseModelInt;
import in.co.sunrays.proj3.model.ModelFactory;
import in.co.sunrays.proj3.model.SubjectModelInt;
import in.co.sunrays.proj3.model.TimeTableModelInt;
import in.co.sunrays.proj3.util.DataUtility;
import in.co.sunrays.proj3.util.DataValidator;
import in.co.sunrays.proj3.util.PropertyReader;
import in.co.sunrays.proj3.util.ServletUtility;
 
/**
 * TimeTableCtl functionality Controller. Performs operation for add, update,
 * delete and get College
 *  
 * @author SUNRAYS Technologies
 * @version 1.0
 * @Copyright (c) SUNRAYS Technologies
 */
@ WebServlet(name="TimeTableCtl",urlPatterns={"/ctl/TimeTableCtl"})
public class TimeTableCtl extends BaseCtl {
	 
    private static final long serialVersionUID = 1L;
 
    private static Logger log = Logger.getLogger(TimeTableCtl.class);
    
    
    protected void preload(HttpServletRequest request){
    	CourseModelInt model = ModelFactory.getInstance().getCourseModel();
		 
    	SubjectModelInt model1=ModelFactory.getInstance().getSubjectModel();
    	
    	try {
    		List l=model.list();
    		List l1=model1.list();
    		
    		 request.setAttribute("courceName",l);
    		 request.setAttribute("subjectName",l1);
    		System.out.println("preload");
    	} catch (ApplicationException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    		log.error(e);
    	}
    }

 
    @Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("TimetableCtl Method validate Started");

		boolean pass = true;

		@SuppressWarnings("unused")
		String op = DataUtility.getString(request.getParameter("operation"));
		String examDate = request.getParameter("examDate");

		if (DataValidator.isNull(request.getParameter("semester"))) {
			request.setAttribute("semester", PropertyReader.getValue("error.require", "Semester"));
			pass = false;
			
		}
		if (DataValidator.isNull(examDate)) {
			request.setAttribute("examDate", PropertyReader.getValue("error.require", "Date of Exam"));
			pass = false;
		} else if (!DataValidator.isDate(examDate)) {
			request.setAttribute("examDate", PropertyReader.getValue("error.date", "Date of Exam"));
			pass = false;
		}
		
		if (DataValidator.isNull(request.getParameter("time"))) {
			request.setAttribute("time", PropertyReader.getValue("error.require", "Time"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("courseId"))) {
			request.setAttribute("courceId", PropertyReader.getValue("error.require", "Course Name"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("subjectId"))) {
			request.setAttribute("subjectId", PropertyReader.getValue("error.require", "Subject Name"));
			pass = false;
		}

		log.debug("TimetableCtl Method validate Ended");

		return pass;
	}

    @Override
    protected BaseDTO populateDTO(HttpServletRequest request) {
 
        log.debug("TimeTableCtl Method populateDTO Started");
 
        TimeTableDTO dto = new TimeTableDTO();
       
    	
		 
		 dto.setId(DataUtility.getLong(request.getParameter("id")));
		 //System.out.println("id  "+s.getId());
		 
		 dto.setCourseId(DataUtility.getInt(request.getParameter("courseId")));
		System.out.println("course id"+(request.getParameter("courseId")));
		
		 dto.setSubjectId(DataUtility.getInt(request.getParameter("subjectId")));
		 
		dto.setCourseName(DataUtility.getString(request.getParameter("courseId")));
		  //System.out.println("name  "+bean.getCource_Name());
		  
		 dto.setSubjectName(DataUtility.getString(request.getParameter("subjectId")));
		 //System.out.println("duration  "+bean.getSubject_Name());
		 
		 dto.setSemester(DataUtility.getString(request.getParameter("semester")));
		 
		dto.setTime(DataUtility.getString(request.getParameter("time")));
		 
		 dto.setExamDate(DataUtility.getDate(request.getParameter("examDate")));
		
		 log.debug("TimeTableCtl Method populateDTO Ended"); 
	        
	        populateDTO(dto, request);
	 
	        return dto; 
   }

    

	/**
     * Contains Display logics
     */    
   	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   		
   		log.debug("TimeTableCtl Method doGet Started");
		 System.out.println("get suru");
		 String op=DataUtility.getString(request.getParameter("operation"));
		 System.out.println("get suru 1");
		 TimeTableModelInt model = ModelFactory.getInstance().getTimetableModel();
		 System.out.println("get suru 2");
		 long id=DataUtility.getLong(request.getParameter("id"));
		 System.out.println("get suru 3");
		 if(id>0 || op!=null){
			 System.out.println("get suru 4");
			 System.out.println("id>0");	 
			 TimeTableDTO dto =new TimeTableDTO();
			 
			 try {
				 System.out.println("get suru5");
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
	     log.debug("TimeTableCtl Method doGetEnded");
	     System.out.println("do get");


   		
		}

    
    
	/**
     * Contains Display logics
     */    
 protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
 
        log.debug("TimeTableCtl Method doGet Started");
 
        String op = DataUtility.getString(request.getParameter("operation"));
 
        // get model
        TimeTableModelInt model = ModelFactory.getInstance().getTimetableModel();
 
        long id = DataUtility.getLong(request.getParameter("id"));
        TimeTableDTO dto = (TimeTableDTO)populateDTO(request);
        
		 if (OP_SAVE.equalsIgnoreCase(op)) {
			 System.out.println("save Start");
	            try {
	               
	                   //model.update(bean);
	                
	               	//System.out.println("if me gaya");
	                   long pk;
					try {
						pk = model.add(dto);
						// bean.setId(pk);
						 ServletUtility.setSuccessMessage("TimeTable successfully added",request);
						// ServletUtility.setDto(dto, request);
			               System.out.println("Save end");
					
					} catch (DuplicateRecordException e) {
						// TODO Auto-generated catch block
						//e.printStackTrace();
			
						ServletUtility.setErrorMessage("TimeTable already exists", request);
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
		               
		               ServletUtility.setSuccessMessage("TimeTable successfully updated",request);
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
	        		   ServletUtility.setErrorMessage("TimeTable already exists", request);
	        		   
					e.printStackTrace();
				}
	        	   
	        	   
	        	   
	           }
	        	   
	           
		  else if (OP_RESET.equalsIgnoreCase(op)) {
	     	System.out.println("cancle Start");
	         ServletUtility.forward(getView(), request, response);
	     	System.out.println("cancle end");
	         return;
	         
	     }else if (OP_CANCEL.equalsIgnoreCase(op)) {

	            ServletUtility.redirect(ORSView.TIMETABLE_LIST_CTL, request,
	                    response);
	            return;

	        }

		 ServletUtility.forward(getView(), request, response);
		 
		 log.debug("CourceCtl Method doPOst Ended");

    }
 
    @Override
    protected String getView() {
        return ORSView.TIMETABLE_VIEW;
    }
 
}
