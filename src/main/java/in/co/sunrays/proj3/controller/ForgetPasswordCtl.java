package in.co.sunrays.proj3.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ForgetPasswordCtl
 */
import in.co.sunrays.proj3.dto.BaseDTO; 
import in.co.sunrays.proj3.dto.UserDTO; 
import in.co.sunrays.proj3.exception.ApplicationException; 
import in.co.sunrays.proj3.exception.RecordNotFoundException; 
import in.co.sunrays.proj3.model.ModelFactory; 
import in.co.sunrays.proj3.model.UserModelInt; 
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
 * Forget Password functionality Controller. Performs operation for Forget 
 * Password 
 *  
 * @author SUNRAYS Technologies 
 * @version 1.0 
 * @Copyright (c) SUNRAYS Technologies 
 */ 
 
@WebServlet(name="ForgetPasswordCtl",urlPatterns={"/ForgetPasswordCtl"}) 
public class ForgetPasswordCtl extends BaseCtl { 
 
    private static Logger log = Logger.getLogger(ForgetPasswordCtl.class); 
 
    @Override 
    protected boolean validate(HttpServletRequest request) { 
 
        log.debug("ForgetPasswordCtl Method validate Started"); 
 
        boolean pass = true; 
         
        String login = request.getParameter("login"); 
         
        if (DataValidator.isNull(login)) { 
            request.setAttribute("login", 
                    PropertyReader.getValue("error.require", "Email Id")); 
            pass = false; 
        }else if (!DataValidator.isEmail(login)) { 
            request.setAttribute("login", 
                    PropertyReader.getValue("error.email", "Invalid")); 
            pass = false; 
        } 
        log.debug("ForgetPasswordCtl Method validate Ended"); 
 
        return pass; 
    } 
 
    @Override 
    protected BaseDTO populateDTO(HttpServletRequest request) { 
 
        log.debug("ForgetPasswordCtl Method populateDTO Started"); 
 
        UserDTO dto = new UserDTO(); 
 
        dto.setLogin(DataUtility.getString(request.getParameter("login"))); 
 
        log.debug("ForgetPasswordCtl Method populateDTO Ended"); 
 
        return dto; 
    } 
    /**
     * DIsplay Concept are there
     */
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        log.debug("ForgetPasswordCtl Method doGet Started");

        ServletUtility.forward(getView(), request, response);

    }

    /**
     * Submit Concepts
     */
   protected void doPost(HttpServletRequest request, 
            HttpServletResponse response) throws ServletException, IOException { 
        log.debug("ForgetPasswordCtl Method doGet Started"); 
 
        String op = DataUtility.getString(request.getParameter("operation")); 
 
        UserDTO dto = (UserDTO) populateDTO(request); 
 
        // get model 
        UserModelInt model = ModelFactory.getInstance().getUserModel(); 
 
        if (OP_GO.equalsIgnoreCase(op)) { 
 
            try { 
                model.forgetPassword(dto.getLogin()); 
                ServletUtility.setSuccessMessage( 
                        "Password has been sent to your email id", 
                        request); 
            } catch (RecordNotFoundException e) { 
                ServletUtility.setErrorMessage(e.getMessage(), request); 
                log.error(e); 
            } catch (ApplicationException e) { 
                log.error(e); 
                ServletUtility.handleException(e, request, response); 
                return; 
            } 
                ServletUtility.forward(ORSView.FORGET_PASSWORD_VIEW, request, 
                        response); 
        } else { 
            ServletUtility.forward(ORSView.FORGET_PASSWORD_VIEW, request, 
                    response); 
        } 
 
        log.debug("ForgetPasswordCtl Method doGet Ended"); 
    } 
 
    @Override 
    protected String getView() { 
        return ORSView.FORGET_PASSWORD_VIEW; 
    } 
 
}

