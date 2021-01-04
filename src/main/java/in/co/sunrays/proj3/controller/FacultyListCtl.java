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
import in.co.sunrays.proj3.model.FacultyModelInt;
import in.co.sunrays.proj3.model.ModelFactory;
import in.co.sunrays.proj3.model.UserModelInt;
import in.co.sunrays.proj3.util.DataUtility;
import in.co.sunrays.proj3.util.PropertyReader;
import in.co.sunrays.proj3.util.ServletUtility; 
 
/** 
 * User List functionality Controller. Performs operation for list, search and 
 * delete operations of User  
 *  
 * @author SUNRAYS Technologies 
 * @version 1.0 
 * @Copyright (c) SUNRAYS Technologies 
 */ 
@WebServlet(name="FacultyListCtl",urlPatterns={"/ctl/FacultyListCtl"}) 
public class FacultyListCtl extends BaseCtl { 
 
    private static Logger log = Logger.getLogger(FacultyListCtl.class); 
 
    @Override 
    protected BaseDTO populateDTO(HttpServletRequest request) { 
        FacultyDTO dto = new FacultyDTO(); 
 

        dto.setFirst_Name(DataUtility.getString(request.getParameter("firstname")));

       

        dto.setLogin_Id(DataUtility.getString(request.getParameter("login")));

        return dto;
    } 
    
    
    /**
     * Contains Display logics
     */
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("inside doget");
	 log.debug("UserListCtl doGet Start");
	 String op = DataUtility.getString(request.getParameter("operation"));
 List list = null;
 List next = null;
 int pageNo = 1;
 int pageSize = DataUtility.getInt(PropertyReader.getValue("page.size"));
// UserDTO dto = (UserDTO) populateDTO(request);

 FacultyDTO dto=null;
   //get the selected checkbox ids array for delete list
 String[] ids = request.getParameterValues("ids");
  
 FacultyModelInt model =ModelFactory.getInstance().getFacultyModel();
 try {
	 list = model.search(dto, pageNo, pageSize);
	    next = model.search(dto, pageNo+1, pageSize);
	    request.setAttribute("next",next.size());
	    ServletUtility.setList(list, request);
     ServletUtility.setPageNo(pageNo, request);
     ServletUtility.setPageSize(pageSize, request);
    
     if (list == null || list.size() == 0 && !OP_DELETE.equalsIgnoreCase(op)) {
         ServletUtility.setErrorMessage("No record found ", request);
     }
     
     
	 
	 System.out.println("get view ke upr");
	 ServletUtility.forward(getView(),request,response);
		System.out.println("get view ke niche");
 } catch (ApplicationException e) {
     log.error(e);
     ServletUtility.handleException(e, request, response);
     return;
 }
 log.debug("UserListCtl doPOst End");
}





/**
* Contains Submit logics
*/
	
 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
 System.out.println("dopost k ander");
        log.debug("UserListCtl doGet Start"); 
        System.out.println("1");
        List list = null; 
        System.out.println("2");
        int pageNo = DataUtility.getInt(request.getParameter("pageNo")); 
        System.out.println("3");
        int pageSize = DataUtility.getInt(request.getParameter("pageSize"));
        System.out.println("4");
        
 
        pageNo = (pageNo == 0) ? 1 : pageNo;
        System.out.println("5");
        pageSize = (pageSize == 0) ? DataUtility.getInt(PropertyReader 
                .getValue("page.size")) : pageSize; 
        System.out.println("6");
 
        FacultyDTO dto = (FacultyDTO) populateDTO(request);
        System.out.println("7");
 
        String op = DataUtility.getString(request.getParameter("operation")); 
        
        System.out.println("operation value in dopost =  "+op);
 
        FacultyModelInt model = ModelFactory.getInstance().getFacultyModel(); 
        System.out.println("8");
        try { 
 
        	  if (OP_SEARCH.equalsIgnoreCase(op) || OP_NEXT.equalsIgnoreCase(op)
                      || OP_PREVIOUS.equalsIgnoreCase(op)||OP_DELETE.equalsIgnoreCase(op)||OP_NEW.equalsIgnoreCase(op)||OP_BACK.equalsIgnoreCase(op)||OP_RESET.equalsIgnoreCase(op)) {
   
                  if (OP_SEARCH.equalsIgnoreCase(op)) {
                      pageNo = 1;
                  } else if (OP_NEXT.equalsIgnoreCase(op)) {
                      pageNo++;
                  } else if (OP_PREVIOUS.equalsIgnoreCase(op) && pageNo > 1) {
                      pageNo--;
                  }else if (OP_NEW.equalsIgnoreCase(op)) { 
                      ServletUtility.redirect(ORSView.FACULTY_CTL, request, response); 
                      return; 
                  } else if (OP_DELETE.equalsIgnoreCase(op)) { 
                      pageNo = 1; 
                      // get the selected checkbox ids array for delete list 
                      String[] ids = request.getParameterValues("ids"); 
                      if (ids != null && ids.length > 0) { 
                          FacultyDTO deletedDto = new FacultyDTO(); 
                          for (String id : ids) { 
                              deletedDto.setId(DataUtility.getLong(id)); 
                              model.delete(deletedDto); 
                              ServletUtility.setSuccessMessage("Faculty deleted successfully", request);
                        	    
                          } 
                      }
                      else {
                      	ServletUtility.setErrorMessage("Select atleast one record", request);
                      }
                 
   
              }
                  else if(OP_RESET.equalsIgnoreCase(op)) {
                  ServletUtility.redirect(ORSView.FACULTY_LIST_CTL, request, response);
                  return;
                  }    
                  else if(OP_BACK.equalsIgnoreCase(op)) {
                      ServletUtility.redirect(ORSView.FACULTY_LIST_CTL, request, response);
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
              ServletUtility.forward(ORSView.FACULTY_LIST_VIEW, request, response);
   
        } catch (ApplicationException e) { 
            log.error(e); 
            ServletUtility.handleException(e, request, response); 
            return; 
        } 
 
        log.debug("UserListCtl doGet End"); 
    } 
 
    @Override 
    protected String getView() { 
        return ORSView.FACULTY_LIST_VIEW; 
    } 
 
} 
