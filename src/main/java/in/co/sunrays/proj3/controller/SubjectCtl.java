package in.co.sunrays.proj3.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.sunrays.proj3.dto.BaseDTO;
import in.co.sunrays.proj3.dto.SubjectDTO;
import in.co.sunrays.proj3.exception.ApplicationException;
import in.co.sunrays.proj3.exception.DuplicateRecordException;
import in.co.sunrays.proj3.model.CourseModelInt;
import in.co.sunrays.proj3.model.ModelFactory;
import in.co.sunrays.proj3.model.SubjectModelInt;
import in.co.sunrays.proj3.util.DataUtility;
import in.co.sunrays.proj3.util.DataValidator;
import in.co.sunrays.proj3.util.PropertyReader;
import in.co.sunrays.proj3.util.ServletUtility;
 
/**
 * SubjectCtl functionality Controller. Performs operation for add, update,
 * delete and get College
 *  
 * @author SUNRAYS Technologies
 * @version 1.0
 * @Copyright (c) SUNRAYS Technologies
 */
@ WebServlet(name="SubjectCtl",urlPatterns={"/ctl/SubjectCtl"})
public class SubjectCtl extends BaseCtl {
	 
    private static final long serialVersionUID = 1L;
 
    private static Logger log = Logger.getLogger(SubjectCtl.class);
    
    
protected void preload(HttpServletRequest request){
		
	CourseModelInt model = ModelFactory.getInstance().getCourseModel();
    SubjectModelInt model1=ModelFactory.getInstance().getSubjectModel();
  
		try {
			List l=model.list();
			//List l1=model1.list();
			
			 request.setAttribute("courceName",l);
			 //request.setAttribute("subjectName",l1);
			System.out.println("preload");
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error(e);
		}
	}


    
 
    @Override
    protected boolean validate(HttpServletRequest request) {
 
    	log.debug("SubjectCtl Method validate Started");
		
		boolean pass=true;
		
		if(DataValidator.isNull(request.getParameter("courceId"))){
			
			
			request.setAttribute("courceId",PropertyReader.getValue("error.require","Cource Name"));
			
			pass=false;
		}
		
        if(DataValidator.isNull(request.getParameter("sname"))){
			
			
			request.setAttribute("sname",PropertyReader.getValue("error.require","Subject Name"));
			
			pass=false;
		}
        
        else if(!DataValidator.isCourse(request.getParameter("sname"))){
			
			
			request.setAttribute("sname",PropertyReader.getValue("error.name","Invalid Subject "));
			
			pass=false;
		}
	
	
	
	
		if(DataValidator.isNull(request.getParameter("description"))){
				
			request.setAttribute("description",PropertyReader.getValue("error.require","Description"));
			
			pass=false;
			System.out.println("description value=  "+pass);
		}
		 else if(!DataValidator.isCourse(request.getParameter("description"))){
				
				
				request.setAttribute("description",PropertyReader.getValue("error.description","Invalid "));
				
				pass=false;
			}
		
		
		 log.debug("SubjectCtl Method validate Ended");
               System.out.println("validate");
	        return pass;

   }
 
    @Override
    protected BaseDTO populateDTO(HttpServletRequest request) {
 
        log.debug("SubjectCtl Method populateDTO Started");
 
       SubjectDTO dto = new SubjectDTO();
       
    	
       
       dto.setId(DataUtility.getLong(request.getParameter("id")));

		 
		 dto.setCourceName(DataUtility.getString(request.getParameter("courceId")));
		  
		  
		  dto.setCourceId(DataUtility.getInt(request.getParameter("courceId")));
		  
		 dto.setSubjectName(DataUtility.getString(request.getParameter("sname")));
		 
		 
dto.setsubjectId(DataUtility.getInt(request.getParameter("subjectId")));
		 
		dto.setDescription(DataUtility.getString(request.getParameter("description")));
		 
		  log.debug("SubjectCtl Method populatebean Ended");
		  System.out.println("populate Baean");
		 		    
	        populateDTO(dto, request);
	 
	        return dto; 
   }

    

	/**
     * Contains Display logics
     */    
   	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   		
   		log.debug("SubjectCtl Method doGet Started");
		 System.out.println("get suru");
		 String op=DataUtility.getString(request.getParameter("operation"));
		 
		SubjectModelInt model = ModelFactory.getInstance().getSubjectModel();
		  
		 long id=DataUtility.getLong(request.getParameter("id"));
		 
		 if(id>0 || op!=null){
			 System.out.println("id>0");	 
			SubjectDTO dto =new SubjectDTO();
			 
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
	     log.debug("SubjectCtl Method doGetEnded");
	     System.out.println("do get");


   		
		}

    
    
	/**
     * Contains Submit logics
     */    
 protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
 
        log.debug("SubjectCtl Method doGet Started");
        System.out.println("do <post></post>");
		    
 
        String op = DataUtility.getString(request.getParameter("operation"));
 
        // get model
        SubjectModelInt model = ModelFactory.getInstance().getSubjectModel();
		
        long id = DataUtility.getLong(request.getParameter("id"));
        SubjectDTO dto = (SubjectDTO)populateDTO(request);
        
		 if (OP_SAVE.equalsIgnoreCase(op)) {
			 System.out.println("save Start");
	            try {
	               
	                   //model.update(bean);
	                
	               	//System.out.println("if me gaya");
	                 
					try {
						long pk = model.add(dto);
						// bean.setId(pk);
						 ServletUtility.setSuccessMessage("Subject is successfully added",request);
						// ServletUtility.setDto(dto, request);
			               System.out.println("Save end");
					
					} catch (DuplicateRecordException e) {
						// TODO Auto-generated catch block
						//e.printStackTrace();
			
						ServletUtility.setErrorMessage("Subject already exists", request);
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
		               
		               ServletUtility.setSuccessMessage("Subject is successfully updated",request);
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
	        		   ServletUtility.setErrorMessage("Subject already exists", request);
	        		   
					e.printStackTrace();
				}
	        	   
	        	   
	        	   
	           }
	        	   
	           
		  else if (OP_RESET.equalsIgnoreCase(op)) {
	     	System.out.println("cancle Start");
	         ServletUtility.forward(getView(), request, response);
	     	System.out.println("cancle end");
	         return;
	         
	     }else if (OP_CANCEL.equalsIgnoreCase(op)) {

	            ServletUtility.redirect(ORSView.SUBJECT_LIST_CTL, request,
	                    response);
	            return;

	        }

		 ServletUtility.forward(getView(), request, response);
		 
		 log.debug("SubjectCtl Method doPOst Ended");

    }
 
    @Override
    protected String getView() {
        return ORSView.SUBJECT_VIEW;
    }
 
}
