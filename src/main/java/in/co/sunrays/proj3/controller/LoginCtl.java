package in.co.sunrays.proj3.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import in.co.sunrays.proj3.dto.BaseDTO;
import in.co.sunrays.proj3.dto.RoleDTO;
import in.co.sunrays.proj3.dto.UserDTO;
import in.co.sunrays.proj3.exception.ApplicationException;
import in.co.sunrays.proj3.exception.RecordNotFoundException;
import in.co.sunrays.proj3.model.ModelFactory;
import in.co.sunrays.proj3.model.RoleModelHibImpl;
import in.co.sunrays.proj3.model.RoleModelInt;
import in.co.sunrays.proj3.model.UserModelHibImpl;
import in.co.sunrays.proj3.model.UserModelInt;
import in.co.sunrays.proj3.util.DataUtility;
import in.co.sunrays.proj3.util.DataValidator;
import in.co.sunrays.proj3.util.PropertyReader;
import in.co.sunrays.proj3.util.ServletUtility;

/**
 * Login functionality Controller. Performs operation for Login
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */
@ WebServlet(name="LoginCtl",urlPatterns={"/LoginCtl"})

public class LoginCtl extends BaseCtl {


	private static final long serialVersionUID = 1L;
	
	public static final String OP_SIGN_IN = "SignIn";
	public static final String OP_SIGN_UP = "SignUp";
	public static final String OP_LOG_OUT = "logout";

	private static Logger log = Logger.getLogger(LoginCtl.class);

	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("LoginCtl method validate started");
		boolean pass = true;
		String op = request.getParameter("operation");
		if (OP_SIGN_UP.equals(op) || OP_LOG_OUT.equals(op)) {
			return pass;
		}
		String login = request.getParameter("login");
		if (DataValidator.isNull(login)) {
			request.setAttribute("login", PropertyReader.getValue("error.require", "Login id"));
			pass = false;
			
		} else if (!DataValidator.isEmail(login)) {
			request.setAttribute("login", PropertyReader.getValue("error.email", "Login"));
			pass = false;
		}	

		if (DataValidator.isNull(request.getParameter("password"))) {
			request.setAttribute("password", PropertyReader.getValue("error.require", "Password"));
			pass = false;
			
		}

		/*else if (!DataValidator.isPassword(request.getParameter("password"))) {
			request.setAttribute("password", PropertyReader.getValue("error.password", ""));
			pass = false;
		}
*/
		log.debug("LoginCtl Method validate Ended");

		return pass;
	}
	/**
	 * Populates bean object from request parameters
	 *
	 * @param request
	 * @return
	 */

	protected BaseDTO populateBean(HttpServletRequest request) {

		log.debug("loginctl method populatedbean started");

		UserDTO bean = new 	UserDTO();

		bean.setId(DataUtility.getLong(request.getParameter("id")));

		bean.setLogin(DataUtility.getString(request.getParameter("login")));

		bean.setPassword(DataUtility.getString(request.getParameter("password")));

		log.debug("loginctl method populatebean ended");

		return bean;

	}

	/**
	 * Display Login form
	 * 
	 * @throws ServletException
	 * @throws IOException
	 * 
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		HttpSession session = request.getSession();

		log.debug("method doget started");

		String op = DataUtility.getString(request.getParameter("operation"));

		// get model
		UserModelInt model = new UserModelHibImpl();

		//long id = DataUtility.getLong(request.getParameter("id"));

		/*if (id > 0) {

			UserBean userbean = new UserBean();
			try {
				userbean = model.findByPK(id);
				ServletUtility.setBean(userbean, request);
				return;

			} catch (Exception e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}
		}*/  
		if (OP_LOG_OUT.equals(op)) {

			session.invalidate();
			ServletUtility.setSuccessMessage("You have successfully Logged Out...!!!!!", request);

		}
		ServletUtility.forward(getView(), request, response);
		log.debug("userctl method dopost ended");
	}

	/**
	 * Submitting or login action performing
	 * 
	 */

	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(true);

		log.debug("method doPost started");

		String op = DataUtility.getString(request.getParameter("operation"));
		// get model
		 UserModelInt model = ModelFactory.getInstance().getUserModel();
	        RoleModelInt role = ModelFactory.getInstance().getRoleModel();
	 
		long id = DataUtility.getLong(request.getParameter("id"));
		if (OP_SIGN_UP.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.USER_REGISTRATION_CTL, request, response);
			return;	

		}
		if (OP_SIGN_IN.equalsIgnoreCase(op)) {

			UserDTO dto = (UserDTO) populateBean(request);
			try {
				dto = model.authenticate(dto.getLogin(), dto.getPassword());

				if ( dto != null) {

					session.setAttribute("user",  dto);

					long rollId =  dto.getRoleId();

					RoleDTO rolebean = role.findByPK(rollId);

					if (rolebean != null) {
						session.setAttribute("role", rolebean.getName());
					}
					
					String uri = (String) session.getAttribute("uri");
					
					if(uri==null){
						ServletUtility.redirect(ORSView.WELCOME_CTL, request, response);
					    return;
					}
					else{
						ServletUtility.redirect(uri, request, response);
						return;
					}
					}
				
				ServletUtility.setDto(dto, request);
				ServletUtility.setErrorMessage("Invalid LoginId And Password", request);
				ServletUtility.forward(getView(), request, response);
			}

			catch (ApplicationException e) {
				ServletUtility.setErrorMessage("Invalid LoginId And Password", request);
				
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}

			
		}
		ServletUtility.setErrorMessage("Invalid LoginId And Password", request);
		
		ServletUtility.forward(getView(), request, response);

		log.debug("UserCtl Method doPost Ended");

	}

	@Override
	protected String getView() {
		return ORSView.LOGIN_VIEW;

	}


	
}
