package in.co.sunrays.proj3.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.sunrays.proj3.dto.BaseDTO;
import in.co.sunrays.proj3.dto.UserDTO;
import in.co.sunrays.proj3.exception.ApplicationException;
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
@WebServlet(name="UserListCtl",urlPatterns={"/ctl/UserListCtl"}) 
public class UserListCtlk extends BaseCtl { 
 
    private static Logger log = Logger.getLogger(UserListCtl.class); 
 
    @Override 
    protected BaseDTO populateDTO(HttpServletRequest request) { 
        UserDTO dto = new UserDTO(); 
 
        dto.setFirstName(DataUtility.getString(request.getParameter("firstName")));

	       // bean.setLastName(DataUtility.getString(request.getParameter("lastName")));

	        dto.setLogin(DataUtility.getString(request.getParameter("login")));

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

 UserDTO dto=null;
   //get the selected checkbox ids array for delete list
 String[] ids = request.getParameterValues("ids");
  
 UserModelInt model =ModelFactory.getInstance().getUserModel();
 try {
    list = model.search(dto, pageNo, pageSize);
    System.out.println("llllllllllllllllllllllllllllllllllllllll"+list.size());
    next = model.search(dto, pageNo+1, pageSize);
    request.setAttribute("next",next.size());
     ServletUtility.setList(list, request);
     if (list == null || list.size() == 0 && !OP_DELETE.equalsIgnoreCase(op)) {
         ServletUtility.setErrorMessage("No record found ", request);
     }
     
     ServletUtility.setPageNo(pageNo, request);
     ServletUtility.setPageSize(pageSize, request);
     
	 
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
 
        log.debug("UserListCtl doGet Start"); 
 
        List list = null; 
 
        int pageNo = DataUtility.getInt(request.getParameter("pageNo")); 
        int pageSize = DataUtility.getInt(request.getParameter("pageSize")); 
 
        pageNo = (pageNo == 0) ? 1 : pageNo; 
        pageSize = (pageSize == 0) ? DataUtility.getInt(PropertyReader 
                .getValue("page.size")) : pageSize; 
 
        UserDTO dto = (UserDTO) populateDTO(request); 
 
        String op = DataUtility.getString(request.getParameter("operation")); 
 
        UserModelInt model = ModelFactory.getInstance().getUserModel(); 
 
        try { 
 
            if (OP_SEARCH.equalsIgnoreCase(op) || "Next".equalsIgnoreCase(op) 
                    || "Previous".equalsIgnoreCase(op)) { 
 
                if (OP_SEARCH.equalsIgnoreCase(op)) { 
                    pageNo = 1; 
                } else if (OP_NEXT.equalsIgnoreCase(op)) { 
                    pageNo++; 
                } else if (OP_PREVIOUS.equalsIgnoreCase(op) && pageNo > 1) { 
                    pageNo--; 
                } 
 
            } else if (OP_NEW.equalsIgnoreCase(op)) { 
                ServletUtility.redirect(ORSView.USER_CTL, request, response); 
                return; 
            } else if (OP_DELETE.equalsIgnoreCase(op)) { 
                pageNo = 1; 
                // get the selected checkbox ids array for delete list 
                String[] ids = request.getParameterValues("ids"); 
                if (ids != null && ids.length > 0) { 
                    UserDTO deletedDto = new UserDTO(); 
                    for (String id : ids) { 
                        deletedDto.setId(DataUtility.getLong(id)); 
                        model.delete(deletedDto); 
                        ServletUtility.setSuccessMessage("User deleted successfully", request);
                    } 
                } else { 
                    ServletUtility.setErrorMessage( 
                            "Select at least one record", request); 
                } 
            } 
            else if(OP_RESET.equalsIgnoreCase(op)) {
            	ServletUtility.redirect(getView(), request, response);
            	return;
            	
            }
 
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
            ServletUtility.forward(ORSView.USER_LIST_VIEW, request, response); 
 
        } catch (ApplicationException e) { 
            log.error(e); 
            ServletUtility.handleException(e, request, response); 
            return; 
        } 
 
        log.debug("UserListCtl doGet End"); 
    } 
 
    @Override 
    protected String getView() { 
        return ORSView.USER_LIST_VIEW; 
    } 
 
} 
