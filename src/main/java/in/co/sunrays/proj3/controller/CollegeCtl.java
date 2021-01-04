package in.co.sunrays.proj3.controller;

import in.co.sunrays.proj3.dto.BaseDTO;
import in.co.sunrays.proj3.dto.CollegeDTO;
import in.co.sunrays.proj3.exception.ApplicationException;
import in.co.sunrays.proj3.exception.DuplicateRecordException;
import in.co.sunrays.proj3.model.CollegeModelInt;
import in.co.sunrays.proj3.model.ModelFactory;
import in.co.sunrays.proj3.util.DataUtility;
import in.co.sunrays.proj3.util.DataValidator;
import in.co.sunrays.proj3.util.PropertyReader;
import in.co.sunrays.proj3.util.ServletUtility;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * College functionality Controller. Performs operation for add, update, delete
 * and get College
 * 
 * @author Decorator
 * @version 1.0
 * Copyright (c) Decorator
 */
@ WebServlet(name="CollegeCtl",urlPatterns={"/ctl/CollegeCtl"})
public class CollegeCtl extends BaseCtl {

    private static final long serialVersionUID = 1L;

    private static Logger log = Logger.getLogger(CollegeCtl.class);

    @Override
    protected boolean validate(HttpServletRequest request) {

        log.debug("CollegeCtl Method validate Started");

        boolean pass = true;

        if (DataValidator.isNull(request.getParameter("name"))) {
            request.setAttribute("name",
                    PropertyReader.getValue("error.require", "Name"));
            pass = false;
        }else if ( DataValidator.isSpecial(request.getParameter("name"))
				|| DataValidator.isNumber(request.getParameter("name"))
				|| DataValidator.isName(request.getParameter("name"))) {

		
			if (DataValidator.isSpecial(request.getParameter("name"))) {
				request.setAttribute("name", PropertyReader.getValue("error.special", "college name"));
				pass = false;
			}
			if (DataValidator.isNumber(request.getParameter("name"))) {
				request.setAttribute("name", PropertyReader.getValue("error.number", "college name"));
				pass = false;
			}
			if (DataValidator.isName(request.getParameter("name"))) {
				request.setAttribute("name", PropertyReader.getValue("error.name", "college name"));
				pass = false;
			}
			if (DataValidator.isSpecial(request.getParameter("name"))
					&& DataValidator.isNumber(request.getParameter("name"))) {
				request.setAttribute("name", PropertyReader.getValue("error.specialnumber", "name"));
				pass = false;
			}
			
		}

        if (DataValidator.isNull(request.getParameter("address"))) {
            request.setAttribute("address",
                    PropertyReader.getValue("error.require", "Address"));
            pass = false;
        }
        else if(DataValidator.isName(request.getParameter("address"))){
        	request.setAttribute("address", PropertyReader.getValue("error.name","Address" ));
        	pass=false;
        	
        }

        else if (DataValidator.isSpecial(request.getParameter("address"))) {
			request.setAttribute("address", PropertyReader.getValue("error.special", "Address"));
			pass = false;
		}
        
       
        if (DataValidator.isNull(request.getParameter("state"))) {
			request.setAttribute("state", PropertyReader.getValue("error.require", "State"));
			pass = false;
		} else if (DataValidator.isNumber(request.getParameter("state"))) {
			request.setAttribute("state", PropertyReader.getValue("error.number", "State"));
			pass = false;
		}else if (DataValidator.isState(request.getParameter("state"))) {
			request.setAttribute("state", PropertyReader.getValue("error.state", "State"));
			pass = false;
		}
        else if (DataValidator.isName(request.getParameter("state"))) {
		request.setAttribute("state", PropertyReader.getValue("error.name", "State"));
		pass = false;
	}



        
      
        if (DataValidator.isNull(request.getParameter("city"))) {
            request.setAttribute("city",
                    PropertyReader.getValue("error.require", "City"));
            pass = false;
        }else if (DataValidator.isWhiteSpace(request.getParameter("city"))
				|| DataValidator.isSpecial(request.getParameter("city"))
				|| DataValidator.isNumber(request.getParameter("city"))
				|| DataValidator.isName(request.getParameter("city"))) {

			if (DataValidator.isWhiteSpace(request.getParameter("city"))) {
				request.setAttribute("city", PropertyReader.getValue("error.whiteSpace", "city "));
				pass = false;
			}
			if (DataValidator.isSpecial(request.getParameter("city"))) {
				request.setAttribute("city", PropertyReader.getValue("error.special", "city name"));
				pass = false;
			}
			if (DataValidator.isNumber(request.getParameter("city"))) {
				request.setAttribute("city", PropertyReader.getValue("error.number", " city name"));
				pass = false;
			}
			if (DataValidator.isName(request.getParameter("city"))) {
				request.setAttribute("city", PropertyReader.getValue("error.name", " city name"));
				pass = false;
			}
			if (DataValidator.isWhiteSpace(request.getParameter("city"))
					&& DataValidator.isSpecial(request.getParameter("city"))) {
				request.setAttribute("city", PropertyReader.getValue("error.spacespecial", "city name"));
				pass = false;
			}
			if (DataValidator.isWhiteSpace(request.getParameter("city"))
					&& DataValidator.isNumber(request.getParameter("city"))) {
				request.setAttribute("city", PropertyReader.getValue("error.spacenumber", "city name"));
				pass = false;
			}
			if (DataValidator.isSpecial(request.getParameter("city"))
					&& DataValidator.isNumber(request.getParameter("city"))) {
				request.setAttribute("city", PropertyReader.getValue("error.specialnumber", "city name"));
				pass = false;
			}
			
			
		}
        if (DataValidator.isNull(request.getParameter("mobile"))) {
            request.setAttribute("mobile",
                    PropertyReader.getValue("error.require", "Phone No"));
            pass = false;
        }else if (!DataValidator.isLong(request.getParameter("mobile"))) {
			request.setAttribute("mobile", "phone  must be in numbers");
			pass = false;
	
		} else if (!DataValidator.isMobileNo(request.getParameter("mobile"))) {
			request.setAttribute("mobile", PropertyReader.getValue("error.phoneno", "phone no."));
			pass = false;
		}

        log.debug("CollegeCtl Method validate Ended");

        return pass;
    }

    @Override
    protected BaseDTO populateDTO(HttpServletRequest request) {

        log.debug("CollegeCtl Method populatebean Started");
        CollegeDTO dto = new CollegeDTO();

        dto.setId(DataUtility.getLong(request.getParameter("id")));
        System.out.println("PopulateBean"+dto.getId());

        dto.setName(DataUtility.getString(request.getParameter("name")));

        dto.setAddress(DataUtility.getString(request.getParameter("address")));

        dto.setState(DataUtility.getString(request.getParameter("state")));

        dto.setCity(DataUtility.getString(request.getParameter("city")));

        dto.setMobile(DataUtility.getString(request.getParameter("mobile")));

        populateDTO(dto, request);

        log.debug("CollegeCtl Method populatebean Ended");

        return dto;
    }

    /**
     * Contains display logic
     */
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        String op = DataUtility.getString(request.getParameter("operation"));

        CollegeModelInt model=ModelFactory.getInstance().getCollegeModel();

        long id = DataUtility.getLong(request.getParameter("id"));

        if (id > 0) {
            CollegeDTO dto;
            try {
                dto = model.findByPK(id);
                ServletUtility.setDto(dto, request);
            } catch (ApplicationException e) {
                log.error(e);
                ServletUtility.handleException(e, request, response);
                return;
            }
        }

        ServletUtility.forward(getView(), request, response);
    }

    /**
     * Contains Submit logics
     */
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        System.out.println("1");
        log.debug("CollegeCtl Method doPost Started");

        String op = DataUtility.getString(request.getParameter("operation"));

        // get model
        CollegeModelInt model=ModelFactory.getInstance().getCollegeModel();

        long id = DataUtility.getLong(request.getParameter("id"));

        if (OP_SAVE.equalsIgnoreCase(op) || OP_UPDATE.equalsIgnoreCase(op)) {

            CollegeDTO dto = (CollegeDTO) populateDTO(request);
//ServletUtility.setBean(bean, request);
            try {
                if (id > 0) {
                    model.update(dto);
                    ServletUtility.setDto(dto, request);
                    ServletUtility.setSuccessMessage("Data is successfully updated",
                            request);
                } else {
                    long pk = model.add(dto);
                    dto.setId(pk);
                
               // ServletUtility.setBean(bean, request);
                ServletUtility.setSuccessMessage("Data is successfully saved",
                        request);
                }
            } catch (ApplicationException e) {
                e.printStackTrace();
                log.error(e);
                ServletUtility.handleException(e, request, response);
                return;
            } catch (DuplicateRecordException e) {
                ServletUtility.setDto(dto, request);
                ServletUtility.setErrorMessage("College Name already exists",
                        request);
            }

        } else if (OP_DELETE.equalsIgnoreCase(op)) {

            CollegeDTO bean = (CollegeDTO) populateDTO(request);
            try {
                model.delete(bean);
                ServletUtility.redirect(ORSView.COLLEGE_LIST_CTL, request,
                        response);
                return;

            } catch (ApplicationException e) {
                log.error(e);
                ServletUtility.handleException(e, request, response);
                return;
            }

        } else if (OP_CANCEL.equalsIgnoreCase(op)) {

            ServletUtility
                    .redirect(ORSView.COLLEGE_LIST_CTL, request, response);
            return;

        }else if (OP_RESET.equalsIgnoreCase(op)) {
        	ServletUtility.forward(getView(), request, response);
            
            return;
        }

        ServletUtility.forward(getView(), request, response);

        log.debug("CollegeCtl Method doGet Ended");
    }

    @Override
    protected String getView() {
        return ORSView.COLLEGE_VIEW;
    }

}
