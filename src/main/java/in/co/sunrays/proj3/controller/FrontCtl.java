package in.co.sunrays.proj3.controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import in.co.sunrays.proj3.util.ServletUtility;


/**
 * Main Controller performs session checking and logging operations before
 * calling any application controller. It prevents any user to access
 * application without login.
 * 
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */
@WebFilter(urlPatterns={"/doc/*" , "/ctl/*"})
public class FrontCtl implements Filter {

	public void destroy() {
		// TODO Auto-generated method stub
	
	}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)throws IOException, ServletException {
		
		HttpServletRequest request=(HttpServletRequest)req;
		HttpServletResponse response=(HttpServletResponse)res;
		
		HttpSession session=request.getSession(); 
		// TODO Auto-generated method s
		if(session.getAttribute("user")==null){
			
	    request.setAttribute("message","your session has been expired---!!! please login");
			  
		String str=request.getRequestURI();
		session.setAttribute("URI",str);
			
     	ServletUtility.forward(ORSView.LOGIN_VIEW, request, response);		
	   
	    return;
		}
		else{
			
			chain.doFilter(req, res);
		}
		
	}

	    public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}


}