package in.co.sunrays.proj3.controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.internal.SessionImpl;

import in.co.sunrays.proj3.dto.UserDTO;
import in.co.sunrays.proj3.util.HibDataSource;
import in.co.sunrays.proj3.util.JDBCDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
/*import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;*/
import net.sf.jasperreports.engine.JasperReport;



/**
 * The Class JasperCtl.
 */
@WebServlet(name="JasperCtl",urlPatterns={"/ctl/JasperCtl"})
public class JasperCtl extends BaseCtl{
	
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			
			System.out.println("in jasperctl .....");
//got jrxml file location
		
			//get the state of user from session ,username and password
			HttpSession session = req.getSession(true);
			UserDTO dto =(UserDTO) session.getAttribute("user");
			String strName = dto.getFirstName();
			String strLastName= dto.getLastName();
			
			//put or object in hashmap in key value form.
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("user",	 strName+" "+strLastName);
			
			//got the instance of Connection
			Connection conn = null;
			
			//got the object of resource bundle and matched condition of database and returned instance
			ResourceBundle rb = ResourceBundle.getBundle("in.co.sunrays.proj3.bundle.system");
			String database = rb.getString("DATABASE");
			String path = req.getServletContext().getRealPath("/WEB-INF/Simple_Blue.jrxml");
	
			JasperReport jasperreport = JasperCompileManager.compileReport(path);
			
			
			if("Hibernate".equals(database)){
			conn = ((SessionImpl) HibDataSource.getSession()).connection();	
			}
		if("JDBC".equals(database)){
			conn = JDBCDataSource.getConnection();
			
		}	
			
			

		JasperPrint jasperprint = JasperFillManager.fillReport(jasperreport, map,conn);
		byte[] pdf = JasperExportManager.exportReportToPdf(jasperprint);
		
		resp.setContentType("application/pdf");
		resp.getOutputStream().write(pdf);
		resp.getOutputStream().flush();
		
		}catch (Exception e){
			
			e.printStackTrace();
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	@Override
	protected String getView() {
		
		return ORSView.MARKSHEET_MERIT_LIST_VIEW;
	}

}