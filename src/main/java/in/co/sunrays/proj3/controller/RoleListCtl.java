package in.co.sunrays.proj3.controller;

import in.co.sunrays.proj3.dto.BaseDTO;
import in.co.sunrays.proj3.dto.RoleDTO;
import in.co.sunrays.proj3.exception.ApplicationException;
import in.co.sunrays.proj3.model.ModelFactory;
import in.co.sunrays.proj3.model.RoleModelInt;
import in.co.sunrays.proj3.util.DataUtility;
import in.co.sunrays.proj3.util.PropertyReader;
import in.co.sunrays.proj3.util.ServletUtility;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * Role List functionality Controller. Performs operation for list, search
 * operations of Role
 * 
 * @author Decorator
 * @version 1.0
 * Copyright (c) Decorator
 */
@ WebServlet(name="RoleListCtl",urlPatterns={"/ctl/RoleListCtl"})
public class RoleListCtl extends BaseCtl {
    private static Logger log = Logger.getLogger(RoleListCtl.class);

    
    @Override
	protected void preload(HttpServletRequest request) {
		
    	RoleModelInt roleModel= ModelFactory.getInstance().getRoleModel();
		try{
			List list=roleModel.list();
			request.setAttribute("roleList", list);
		}catch(ApplicationException e){
			log.error(e);
		}
		
    }
    
    
    @Override
    protected BaseDTO populateDTO(HttpServletRequest request) {
        RoleDTO dto = new RoleDTO();
        
        dto.setId(DataUtility.getLong(request.getParameter("roleId")));
        dto.setName(DataUtility.getString(request.getParameter("name")));

        return dto;
    }

    /**
     * Contains Display logics
     */
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        log.debug("RoleListCtl doGet Start");
        
        List list = null;
        int pageNo = 1;
        int pageSize = DataUtility.getInt(PropertyReader.getValue("page.size"));
        
        RoleDTO dto = (RoleDTO) populateDTO(request);
        String op = DataUtility.getString(request.getParameter("operation"));
      
        RoleModelInt model= ModelFactory.getInstance().getRoleModel();
        try{
			list=model.search(dto, pageNo, pageSize);
			List listNext = model.search(dto, pageNo+1, pageSize);
			request.setAttribute("listNext", listNext);
			if(list==null || list.size()==0){
				ServletUtility.setErrorMessage("No record Found", request);
			}
			ServletUtility.setList(list, request);
			ServletUtility.setPageNo(pageNo, request);
			ServletUtility.setPageSize(pageSize, request);
			ServletUtility.forward(getView(), request, response);
			}catch(ApplicationException e){
				e.printStackTrace();
				ServletUtility.handleException(e, request, response);
				return;
			}
        log.debug("RoleListCtl doGet End");
    }

    /**
     * Contains Submit logics
     */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List list=null;
		int pageNo=DataUtility.getInt(request.getParameter("pageNo"));
		int pageSize=DataUtility.getInt(request.getParameter("pageSize"));
		
		pageNo=(pageNo==0)?1:pageNo;
		pageSize=(pageSize==0)?DataUtility.getInt(PropertyReader.getValue("page.size")):pageSize;
		String op=DataUtility.getString(request.getParameter("operation"));
		
		RoleDTO dto=(RoleDTO) populateDTO(request);
		

		// get the selected checkbox ids array for delete list
		String ids[]=request.getParameterValues("ids");
		 RoleModelInt model= ModelFactory.getInstance().getRoleModel();
		
		try{
			
			if (OP_SEARCH.equalsIgnoreCase(op) || "Next".equalsIgnoreCase(op) || "Previous".equalsIgnoreCase(op)) {

				if (OP_SEARCH.equalsIgnoreCase(op)) {
					pageNo = 1;
				} else if (OP_NEXT.equalsIgnoreCase(op)) {
					pageNo++;
				} else if (OP_PREVIOUS.equalsIgnoreCase(op) && pageNo > 1) {
					pageNo--;
				}
			}else if(OP_NEW.equalsIgnoreCase(op)){
				ServletUtility.redirect(ORSView.ROLE_CTL, request, response);
				return;
				
			}else if(OP_DELETE.equalsIgnoreCase(op)){
				pageNo=1;
				if(ids!=null&&ids.length>0){
					RoleDTO deletedto=new RoleDTO();
					for (String id : ids) {
						deletedto.setId(DataUtility.getLong(id));
						model.delete(deletedto);
						ServletUtility.setSuccessMessage("Record successfully deleted", request);
					}
				}
					else{
						ServletUtility.setErrorMessage("Select at least one record", request);
					}
			
				}else if(OP_RESET.equalsIgnoreCase(op)){
					ServletUtility.redirect(ORSView.ROLE_LIST_CTL, request, response);
					return;
				}
			list=model.search(dto, pageNo, pageSize);
			request.setAttribute("roleList", list);
			ServletUtility.setList(list, request);
			List listNext = model.search(dto, pageNo+1, pageSize);
			request.setAttribute("listNext", listNext);
			if(!OP_DELETE.equalsIgnoreCase(op)){
				if(list==null||list.size()==0){
					ServletUtility.setErrorMessage("No records Found", request);
				}
			}
			
			ServletUtility.setDto(dto, request);
			ServletUtility.setList(list, request);
			ServletUtility.setPageNo(pageNo, request);
			ServletUtility.setPageSize(pageSize, request);
			ServletUtility.forward(getView(), request, response);
		}catch(ApplicationException e){
			e.printStackTrace();
			ServletUtility.handleException(e, request, response);
			return;
		}
	}

    @Override
    protected String getView() {
        return ORSView.ROLE_LIST_VIEW;
    }

}