package in.co.sunrays.proj3.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import in.co.sunrays.proj3.dto.BaseDTO;
import in.co.sunrays.proj3.dto.MarksheetDTO;

import in.co.sunrays.proj3.exception.ApplicationException;
import in.co.sunrays.proj3.exception.RecordNotFoundException;
import in.co.sunrays.proj3.model.CollegeModelInt;
import in.co.sunrays.proj3.model.CourseModelInt;
import in.co.sunrays.proj3.model.MarksheetModelInt;
import in.co.sunrays.proj3.model.ModelFactory;
import in.co.sunrays.proj3.util.DataUtility;
import in.co.sunrays.proj3.util.PropertyReader;
import in.co.sunrays.proj3.util.ServletUtility;
 
/**
 * Marksheet List functionality Controller. Performs operation for list, search
 * and delete operations of Marksheet
 *  
 * @author SUNRAYS Technologies
 * @version 1.0
 * @Copyright (c) SUNRAYS Technologies
 */
 

@WebServlet(name="MarksheetListCtl",urlPatterns={"/ctl/MarksheetListCtl"}) 
public class MarksheetListCtl extends BaseCtl {
 
    private static Logger log = Logger.getLogger(MarksheetListCtl.class);
 

    @Override 
protected void preload(HttpServletRequest request){
		CollegeModelInt model= ModelFactory.getInstance().getCollegeModel();
		
		List l = null;
		
		
			try {
				 l = model.list();
			} catch (ApplicationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
               request.setAttribute("Name",l);
			
	}
	
    
    
    
    @Override
    protected BaseDTO populateDTO(HttpServletRequest request) {
        MarksheetDTO dto = new MarksheetDTO();
         dto.setRollNo(DataUtility.getString(request.getParameter("rollNo")));
		
		dto.setName(DataUtility.getString(request.getParameter("name")));
		
		
	
        return dto;
    }
 
    
    /**
     * Contains Display logics
     */
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("inside dogetppppppp");
	 log.debug("UserListCtl doGet Start");
	 String op = DataUtility.getString(request.getParameter("operation"));
	 System.out.println("1");
 List list = null;
 List next = null;
 System.out.println("2");
 int pageNo = 1;
 int pageSize = DataUtility.getInt(PropertyReader.getValue("page.size"));
 System.out.println("3");
// UserDTO dto = (UserDTO) populateDTO(request);

 MarksheetDTO dto=null;
 System.out.println("4");
   //get the selected checkbox ids array for delete list
 String[] ids = request.getParameterValues("ids");
 System.out.println("5");
 MarksheetModelInt model =ModelFactory.getInstance().getMarksheetModel();
 System.out.println("6");
 try {
    list=model.search(dto, pageNo, pageSize);
    next=model.search(dto, pageNo+1, pageSize);
    request.setAttribute("next",next.size());
    
    
    System.out.println("7");
     ServletUtility.setList(list, request);
     if (list == null || list.size() == 0 && !OP_DELETE.equalsIgnoreCase(op)) {
         ServletUtility.setErrorMessage("No record found ", request);
     }
     System.out.println("8");
     ServletUtility.setPageNo(pageNo, request);
     ServletUtility.setPageSize(pageSize, request);
     
	 
	 System.out.println("get view ke upr");
	 ServletUtility.forward(getView(),request,response);
		System.out.println("get view ke niche");
 } catch (ApplicationException e) {
	 e.printStackTrace();
     log.error(e);
     ServletUtility.handleException(e, request, response);
     return;
 }
 catch (HibernateException e) {
	 e.printStackTrace();
     log.error(e);
     ServletUtility.handleException(e, request, response);
     return;
 }
 
 
 log.debug("UserListCtl doPOst End");
}


   
    /**
    * Contains Submit logics
    */

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
 
        log.debug("MarksheetListCtl doGet Start");
 
        List list = null;
 
        int pageNo = DataUtility.getInt(request.getParameter("pageNo"));
        int pageSize = DataUtility.getInt(request.getParameter("pageSize"));
 
        pageNo = (pageNo == 0) ? 1 : pageNo;
 
        pageSize = (pageSize == 0) ? DataUtility.getInt(PropertyReader
                .getValue("page.size")) : pageSize;
 
        MarksheetDTO dto = (MarksheetDTO) populateDTO(request);
 
        String op = DataUtility.getString(request.getParameter("operation"));
 System.out.println("operation in dopost"+op);
        MarksheetModelInt model = ModelFactory.getInstance()
                .getMarksheetModel();
 
        try {
 
        	 if (OP_SEARCH.equalsIgnoreCase(op) || OP_NEXT.equalsIgnoreCase(op)
                     || OP_PREVIOUS.equalsIgnoreCase(op)||OP_DELETE.equalsIgnoreCase(op)||OP_NEW.equalsIgnoreCase(op)||OP_RESET.equalsIgnoreCase(op)||OP_BACK.equalsIgnoreCase(op)) {
  
                 if (OP_SEARCH.equalsIgnoreCase(op)) {
                     pageNo = 1;
                 } else if (OP_NEXT.equalsIgnoreCase(op)) {
                     pageNo++;
                 } else if (OP_PREVIOUS.equalsIgnoreCase(op) && pageNo > 1) {
                     pageNo--;
                 }else if (OP_NEW.equalsIgnoreCase(op)) { 
                     ServletUtility.redirect(ORSView.MARKSHEET_CTL, request, response); 
                     return; 
                 } else if (OP_DELETE.equalsIgnoreCase(op)) { 
                     pageNo = 1; 
                     // get the selected checkbox ids array for delete list 
                     String[] ids = request.getParameterValues("ids"); 
                     if (ids != null && ids.length > 0) { 
                         MarksheetDTO deletedDto = new MarksheetDTO(); 
                         for (String id : ids) { 
                             deletedDto.setId(DataUtility.getLong(id)); 
                             model.delete(deletedDto); 
                             ServletUtility.setSuccessMessage("Marksheet deleted successfully", request);
                       	    
                         } 
                     }
                     else {
                     	ServletUtility.setErrorMessage("Select atleast one record", request);
                     }
                
  
             }
                 else if(OP_RESET.equalsIgnoreCase(op)) {
                 ServletUtility.redirect(ORSView.MARKSHEET_LIST_CTL, request, response);
                 return;
                 } 
                 
                 else if(OP_BACK.equalsIgnoreCase(op)) {
                     ServletUtility.redirect(ORSView.MARKSHEET_LIST_CTL, request, response);
                     return;
                     }    
                
             
             }
             System.out.println("pageno h== "+pageNo);
             System.out.println("pageSize h== "+pageSize);
             
             list = model.search(dto, pageNo, pageSize);
             ServletUtility.setList(list, request);
             if (list == null || list.size() == 0) {
                 ServletUtility.setErrorMessage("No record found ", request);
             }
             List next;
             next = model.search(dto, pageNo+1, pageSize);
             request.setAttribute("next",next.size());
            
             ServletUtility.setList(list, request);
  
             ServletUtility.setPageNo(pageNo, request);
             ServletUtility.setPageSize(pageSize, request);
             ServletUtility.forward(ORSView.MARKSHEET_LIST_VIEW, request, response);
  
        } catch (ApplicationException e) {
            log.error(e);
            ServletUtility.handleException(e, request, response);
            return;
        }
 
        log.debug("MarksheetListCtl doGet End");
    }
 
    @Override
    protected String getView() {
        return ORSView.MARKSHEET_LIST_VIEW;
    }
 
}

