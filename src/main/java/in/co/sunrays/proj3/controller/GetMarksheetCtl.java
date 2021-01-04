package in.co.sunrays.proj3.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import in.co.sunrays.proj3.dto.BaseDTO;
import in.co.sunrays.proj3.dto.MarksheetDTO;
import in.co.sunrays.proj3.exception.ApplicationException;
import in.co.sunrays.proj3.model.MarksheetModelInt;
import in.co.sunrays.proj3.model.ModelFactory;
import in.co.sunrays.proj3.util.DataUtility;
import in.co.sunrays.proj3.util.DataValidator;
import in.co.sunrays.proj3.util.PropertyReader;
import in.co.sunrays.proj3.util.ServletUtility;
 
import java.io.IOException;
 
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import org.apache.log4j.Logger;
 
/**
 * Get Marksheet functionality Controller. Performs operation for Get
 * Marksheet
 *  
 * @author SUNRAYS Technologies
 * @version 1.0
 * @Copyright (c) SUNRAYS Technologies
 */
 
@ WebServlet(name="GetMarksheetCtl",urlPatterns={"/ctl/GetMarksheetCtl"})
public class GetMarksheetCtl extends BaseCtl {
 
    private static Logger log = Logger.getLogger(GetMarksheetCtl.class);
 
    @Override
    protected boolean validate(HttpServletRequest request) {
    	
		log.debug("GetMarksheetCTL Method validate Started");

		boolean pass = true;
		
		if(DataValidator.isNull(request.getParameter("rollNo"))){
			
			request.setAttribute("rollno",PropertyReader.getValue("error.require","Roll Number"));
			pass=false;
		}
		
		/*else if(!DataValidator.isRollNo(request.getParameter("rollNo"))){
			
			request.setAttribute("rollNo",PropertyReader.getValue("error.rollno","Invalid "));
			
			pass=false;
		}
	*/
		log.debug("GetMarksheetCTL Method validate Ended");
		
		return pass;
    }
 
    @Override
    protected BaseDTO populateDTO(HttpServletRequest request) {
 
        log.debug("GetMarksheetCtl Method populateDTO Started");
 
        MarksheetDTO dto = new MarksheetDTO();
 
        dto.setRollNo(DataUtility.getString(request.getParameter("rollNo")));
		       log.debug("GetMarksheetCtl Method populateDTO Ended");
 
        return dto;
    }
    
    
	/**
     * Concept of Display method
     * 
     */
      protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			System.out.println("do get");
			
			ServletUtility.forward(getView(), request, response);
	}
		

 
    /**
     * Contains Submit logics
     */    
  protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
 
        log.debug("GetMarksheetCtl Method doGet Started");
 
        String op = DataUtility.getString(request.getParameter("operation"));
 
        // get model
        MarksheetModelInt model = ModelFactory.getInstance()
                .getMarksheetModel();
 
        MarksheetDTO dto = (MarksheetDTO) populateDTO(request);
 
        long id = DataUtility.getLong(request.getParameter("id"));
 
        if (OP_GO.equalsIgnoreCase(op)) {
 
            try {
                dto = model.findByRollNo(dto.getRollNo());
                if (dto != null) {
                    ServletUtility.setDto(dto, request);
                } else {
                    ServletUtility.setErrorMessage("RollNo Does Not exists",
                            request);
                }
            } catch (ApplicationException e) {
                log.error(e);
                ServletUtility.setDto(dto, request);
                ServletUtility.handleException(e, request, response);
                return;
            }
 
        }
 
        ServletUtility.forward(ORSView.GET_MARKSHEET_VIEW, request, response);
 
        log.debug("MarksheetCtl Method doGet Ended");
    }
 
    @Override
    protected String getView() {
        return ORSView.GET_MARKSHEET_VIEW;
    }
 
}

