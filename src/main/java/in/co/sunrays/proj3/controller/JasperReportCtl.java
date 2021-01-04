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


import in.co.sunrays.proj3.dto.UserDTO;
import in.co.sunrays.proj3.util.HibDataSource;
import in.co.sunrays.proj3.util.JDBCDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

@WebServlet(name= "JasperReportCtl" ,urlPatterns={"/ctl/JasperReport"})
public class JasperReportCtl extends BaseCtl {

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		    System.out.println("DoGet Jasper Report");
		try {
			JasperReport jasperReport = JasperCompileManager.compileReport("C:/Users/User/JaspersoftWorkspace/MyReports/Simple_Blue.jrxml");
			
			HttpSession session = request.getSession(true);
			UserDTO dto = (UserDTO) session.getAttribute("user");
			dto.getFirstName();
			dto.getLastName();
			
			Map<String, Object> map = new HashMap();
			map.put("user", dto.getFirstName() + " " + dto.getLastName());
			Connection conn = null;
			
			

			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, map, conn);
			byte[] pdf = JasperExportManager.exportReportToPdf(jasperPrint);
			response.setContentType("application/pdf");
			response.getOutputStream().write(pdf);
			response.getOutputStream().flush();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected String getView() {
		return null;
	}
}
