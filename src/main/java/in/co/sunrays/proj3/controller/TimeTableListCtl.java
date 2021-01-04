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
import in.co.sunrays.proj3.model.CourseModelInt;
import in.co.sunrays.proj3.model.ModelFactory;
import in.co.sunrays.proj3.model.SubjectModelInt;
import in.co.sunrays.proj3.model.TimeTableModelInt;
import in.co.sunrays.proj3.util.DataUtility;
import in.co.sunrays.proj3.util.PropertyReader;
import in.co.sunrays.proj3.util.ServletUtility;
 
/**
 * Student List functionality Controller. Performs operation for list, search
 * and delete operations of Student
 *  
 * @author SUNRAYS Technologies
 * @version 1.0
 * @Copyright (c) SUNRAYS Technologies
 */
@WebServlet(name="TimeTableListCtl",urlPatterns={"/ctl/TimeTableListCtl"}) 
 public class TimeTableListCtl extends BaseCtl {
 
    private static Logger log = Logger.getLogger(TimeTableListCtl.class);
 
protected void preload(HttpServletRequest request){
		
		
		SubjectModelInt model=ModelFactory.getInstance().getSubjectModel();
		CourseModelInt model1=ModelFactory.getInstance().getCourseModel();
		
		try {
			List l=model.list();
			List l1=model1.list();
			
			request.setAttribute("subjectname",l);
			request.setAttribute("courcename",l1);
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

    
    @Override
    protected BaseDTO populateDTO(HttpServletRequest request) {
 
        TimeTableDTO dto = new TimeTableDTO();
 

    
      // System.out.println("P DAte="+request.getParameter("examdate"));
       dto.setCourseId(DataUtility.getInt(request.getParameter("courceId")));
       dto.setSubjectId(DataUtility.getInt(request.getParameter("subjectId")));
       dto.setExamDate(DataUtility.getDate(request.getParameter("examDate")));
       
       
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

 TimeTableDTO dto=null;
/* System.out.println("course name============= "+dto.getCource_Name());
*/ 
   //get the selected checkbox ids array for delete list
 String[] ids = request.getParameterValues("ids");
  
 TimeTableModelInt model =ModelFactory.getInstance().getTimetableModel();
 try {
    list = model.search(dto, pageNo, pageSize);
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

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        log.debug("StudentListCtl doGet Start");
 
        List list = null;
 
        int pageNo = DataUtility.getInt(request.getParameter("pageNo"));
        int pageSize = DataUtility.getInt(request.getParameter("pageSize"));
 
        pageNo = (pageNo == 0) ? 1 : pageNo;
 
        pageSize = (pageSize == 0) ? DataUtility.getInt(PropertyReader
                .getValue("page.size")) : pageSize;
 
        TimeTableDTO dto = (TimeTableDTO) populateDTO(request);
 
        String op = DataUtility.getString(request.getParameter("operation"));
        System.out.println("operation in dopost== "+op);
 
        TimeTableModelInt model = ModelFactory.getInstance().getTimetableModel();
 
        try {
 
            if (OP_SEARCH.equalsIgnoreCase(op) || OP_NEXT.equalsIgnoreCase(op)
                    || OP_PREVIOUS.equalsIgnoreCase(op)||OP_DELETE.equalsIgnoreCase(op)||OP_NEW.equalsIgnoreCase(op)) {
 
                if (OP_SEARCH.equalsIgnoreCase(op)) {
                    pageNo = 1;
                } else if (OP_NEXT.equalsIgnoreCase(op)) {
                    pageNo++;
                } else if (OP_PREVIOUS.equalsIgnoreCase(op) && pageNo > 1) {
                    pageNo--;
                }else if (OP_NEW.equalsIgnoreCase(op)) { 
                    ServletUtility.redirect(ORSView.TIMETABLE_CTL, request, response); 
                    return; 
                } else if (OP_DELETE.equalsIgnoreCase(op)) { 
                    pageNo = 1; 
                    // get the selected checkbox ids array for delete list 
                    String[] ids = request.getParameterValues("ids"); 
                    if (ids != null && ids.length > 0) { 
                        TimeTableDTO deletedDto = new TimeTableDTO(); 
                        for (String id : ids) { 
                            deletedDto.setId(DataUtility.getLong(id)); 
                            model.delete(deletedDto); 
                            ServletUtility.setSuccessMessage("TimeTable deleted successfully", request);
                      	    
                        } 
                    }
                    else {
                    	ServletUtility.setErrorMessage("Select atleast one record", request);
                    }
               
 
            }
                else if(OP_RESET.equalsIgnoreCase(op)) {
                ServletUtility.redirect(getView(), request, response);
                return;
                }    
            
            }
            System.out.println("pageno h== "+pageNo);
            System.out.println("pageSize h== "+pageSize);
            
            list = model.search(dto, pageNo, pageSize);
            
            System.out.println("==============="+list.size());
          //  ServletUtility.setList(list, request);
            if (list == null || list.size() == 0) {
                ServletUtility.setErrorMessage("No record found ", request);
            }
            List next;
            next = model.search(dto, pageNo+1, pageSize);
            request.setAttribute("next",next.size());
           
 
            ServletUtility.setList(list, request);
 
            ServletUtility.setPageNo(pageNo, request);
            ServletUtility.setPageSize(pageSize, request);
            ServletUtility.forward(ORSView.TIMETABLE_LIST_VIEW, request, response);
 
        } catch (ApplicationException e) {
            e.printStackTrace();
        	log.error(e);
            ServletUtility.handleException(e, request, response);
            return;
        }
        log.debug("StudentListCtl doGet End");
    }
 
    @Override
    protected String getView() {
        return ORSView.TIMETABLE_LIST_VIEW;
    }
}
