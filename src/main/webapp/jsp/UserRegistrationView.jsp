<%@page import="in.co.sunrays.proj3.controller.ORSView"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="in.co.sunrays.proj3.controller.UserRegistrationCtl"%>
<%@page import="java.util.HashMap"%>
<%@page import="in.co.sunrays.proj3.util.HTMLUtility"%>
<%@page import="in.co.sunrays.proj3.util.DataUtility"%>
<%@page import="in.co.sunrays.proj3.util.ServletUtility"%>
<%@page errorPage="ErrorView.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- <link rel="icon" href="img/customLogo1.png" type="image/png" sizes="16x16"> -->
<title>UserRegistraion</title>
<link rel="stylesheet"
	href="<%=ORSView.APP_CONTEXT%>/css/comcss.css">
<style type="text/css">


</style>
</head>
<body>
	<%-- <form action="<%=ORSView.USER_REGISTRATION_CTL%>" method="post"
		style="min-height: 590PX">

		<%@ include file="Header.jsp"%>
		<script type="text/javascript" src="../js/calendar.js"></script>
		<jsp:useBean id="bean" class="in.co.sunrays.proj3.dto.UserDTO"
			scope="request"></jsp:useBean>
		<div align="center">
			<h1 align="center" style="margin-bottom: -15">User Registration</h1>
			<div style="height: 15px" margin-bottom:12px>
				<H2 align="center">
					<font color="green"> <%=ServletUtility.getSuccessMessage(request)%>
					</font>
				</H2>
				<H2 align="center">
					<font color="red"> <%=ServletUtility.getErrorMessage(request)%>
					</font>
				</H2>
			</div>
		</div>

		<input type="hidden" name="id" value="<%=bean.getId()%>"> <input
			type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
		<input type="hidden" name="modifiedBy"
			value="<%=bean.getModifiedBy()%>"> <input type="hidden"
			name="createdDatetime"
			value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
		<input type="hidden" name="modifiedDatetime"
			value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">
		<br>

		<table style="margin-left: 38%;">

			<tr>
				<th align="left">First Name<font color="red">*</font></th>
				<td><input type="text" name="firstName" size=30
					placeholder="Enter FirstName"
					value="<%=DataUtility.getStringData(bean.getFirstName())%>"><font
					color="red"> <%=ServletUtility.getErrorMessage("firstName", request)%></font></td>
			</tr>
			<tr>
				<th align="left">Last Name<font color="red">*</font></th>
				<td><input type="text" name="lastName" size=30
					placeholder="Enter LastName"
					value="<%=DataUtility.getStringData(bean.getLastName())%>"><font
					color="red"> <%=ServletUtility.getErrorMessage("lastName", request)%></font></td>
			</tr>
			<tr>
				<th align="left">LoginId<font color="red">*</font></th>
				<td><input type="text" name="login" size=30
					placeholder="Must be Email ID"
					value="<%=DataUtility.getStringData(bean.getLogin())%>"><font
					color="red"> <%=ServletUtility.getErrorMessage("login", request)%></font></td>
			</tr>
			<tr>
				<th align="left">Password<font color="red">*</font></th>
				<td><input type="password" name="password" size=30
					placeholder="Enter Password"
					value="<%=DataUtility.getStringData(bean.getPassword())%>"><font
					color="red"> <%=ServletUtility.getErrorMessage("password", request)%></font></td>
			</tr>
			<tr>
				<th align="left">Confirm Password<font color="red">*</font></th>
				<td><input type="password" name="confirmPassword" size=30
					placeholder="Enter Confirm Password"
					value="<%=DataUtility.getStringData(bean.getConfirmPassword())%>"><font
					color="red"> <%=ServletUtility.getErrorMessage("confirmPassword", request)%></font></td>
			</tr>
			<tr>
				<th align="left">Gender<font color="red">*</font></th>
				<td>
					<%
						HashMap map = new HashMap();
						map.put("M", "Male");
						map.put("F", "Female");

						String htmlList = HTMLUtility.getList("gender", bean.getGender(), map);
					%> <%=htmlList%> <%
 	System.out.println("UserRegistration" + bean.getGender());
 %> <font color="red"> <%=ServletUtility.getErrorMessage("gender", request)%></font>

				</td>
			</tr>



			</tr>

			<tr>
				<th align="left">Date Of Birth<font color="red">*</font></th>
				<td><input type="text" id="datepicker" name="dob"
					readonly="readonly" size=30 placeholder="Enter DOB"
					value="<%=DataUtility.getDateString(bean.getDob())%>"> </a><font
					color="red"> <%=ServletUtility.getErrorMessage("dob", request)%></font></td>
			</tr>
			<tr>
					<th align="left">Mobile No<font color="red">*</font></th>
					<td><input type="text" name="mobno" size="30"
						placeholder="Enter mobile no."
						value="<%=DataUtility.getStringData(bean.getMobileNo())%>"><font
						color="red"> <%=ServletUtility.getErrorMessage("mobno", request)%></font></td>
				</tr>
			<tr>
				<th></th>
				<td align="left"><input type="submit" name="operation"
					value="<%=UserRegistrationCtl.OP_SIGN_UP%>"> <input
					type="submit" name="operation" class="btn_other"
					value="<%=UserRegistrationCtl.OP_RESET%>"></td>
			</tr>
		</table>
	</form>
	</center>
	<%@ include file="Footer.jsp"%>
 --%>
 <div><%@ include file="header.jsp"%></div>
 <div style="margin-bottom: 5%">
<div class="container-fluid" id="back">
	<div class="row">
			<div class="col-sx-12">
				<p class="form-title"style="font-size: 3vw;" align="center ">User Registration</p>
			</div>
		</div>
		
		<div class="row">
		<div class="col-lg-3"></div>
			<div class="col-lg-6">
				
					<h2 style="font-size: 3vw;" align="center">
						<font color="green"><%=ServletUtility.getSuccessMessage(request)%>
						</font>
					</h2>

					<H2 style="font-size: 3vw;"align="center">


						<font color="red"> <%=ServletUtility.getErrorMessage(request)%>
						</font>
					</H2>
        
					
				
			</div>
			<div class="col-lg-3"></div>
		</div>
		<form class="login" action="<%=ORSView.USER_REGISTRATION_CTL%>" method="post">
		<jsp:useBean id="dto" class="in.co.sunrays.proj3.dto.UserDTO"
			scope="request"></jsp:useBean>
			<input type="hidden" name="id" value="<%=dto.getId()%>"> <input
			type="hidden" name="createdBy" value="<%=dto.getCreatedBy()%>">
		<input type="hidden" name="modifiedBy"
			value="<%=dto.getModifiedBy()%>"> <input type="hidden"
			name="createdDatetime"
			value="<%=DataUtility.getTimestamp(dto.getCreatedDatetime())%>">
		<input type="hidden" name="modifiedDatetime"
			value="<%=DataUtility.getTimestamp(dto.getModifiedDatetime())%>">
			<div class="row">
      <div class="col-sm-6">
      	<input type="text" name="firstName"
					placeholder="First Name"
					value="<%=DataUtility.getStringData(dto.getFirstName())%>"><br><font
					color="red"> <%=ServletUtility.getErrorMessage("firstName", request)%></font>
      </div>
      <br>
      <div class="col-sm-6">
      	<input type="text" name="lastName"
					placeholder="Last Name"
					value="<%=DataUtility.getStringData(dto.getLastName())%>"><br><font
					color="red"> <%=ServletUtility.getErrorMessage("lastName", request)%></font>
      </div>
      <div class="row"><div class="col-sm-12"></div></div>
    <div class="row"><div class="col-sm-12"></div></div>
    <div class="row"><div class="col-sm-12"></div></div>
    <div class="row"><div class="col-sm-12"></div></div>
    <div class="row"><div class="col-sm-12"></div></div>
    <div class="row"><div class="col-sm-12"></div></div>
    <div class="row"><div class="col-sm-12"></div></div>
    <div class="row"><div class="col-sm-12"></div></div>
    <div class="row"><div class="col-sm-12"></div></div>
    <div class="row"><div class="col-sm-12"></div></div>
    <div class="row">
      <div class="col-sm-3"></div><div class="col-sm-6">Gender<br><%
						HashMap map = new HashMap();
						map.put("M", "Male");
						map.put("F", "Female");

						String htmlList = HTMLUtility.getList("gender", dto.getGender(), map);
					%> <%=htmlList%> <%
 	System.out.println("UserRegistration" + dto.getGender());
 %> <font color="red"> <%=ServletUtility.getErrorMessage("gender", request)%></font></div>
    </div>  <div class="col-sm-3"></div></div>
    <br>
    
    <div class="row">
      <div class="col-sm-6">
      	<input type="text" name="login"
					placeholder="Login"
					value="<%=DataUtility.getStringData(dto.getLogin())%>"><br><font
					color="red"> <%=ServletUtility.getErrorMessage("login", request)%></font>
      </div>
      <div class="col-sm-6">
      	<input type="password" name="password"
					placeholder="Password"
					value="<%=DataUtility.getStringData(dto.getPassword())%>"><br><font
					color="red"> <%=ServletUtility.getErrorMessage("password", request)%></font>
      			</div></div> 
      			
      			<div class="row"><div class="col-sm-12"></div></div>
    <div class="row"><div class="col-sm-6"></div></div>
    <div class="row"><div class="col-sm-6"></div></div>
    <div class="row"><div class="col-sm-6"></div></div>
    <div class="row"><div class="col-sm-6"></div></div>
    <div class="row"><div class="col-sm-12"></div></div>
    <div class="row"><div class="col-sm-12"></div></div>
    <div class="row"><div class="col-sm-12"></div></div>
    <div class="row"><div class="col-sm-12"></div></div>
    <div class="row"><div class="col-sm-12"></div></div>
    <div class="row"><div class="col-sm-12"></div></div>
    <div class="row"><div class="col-sm-12"></div></div>
    <div class="row"><div class="col-sm-12"></div></div>
    <div class="row"><div class="col-sm-12"></div></div>
    <div class="row"><div class="col-sm-12"></div></div>
    <div class="row"><div class="col-sm-12"></div></div>
    <div class="row"><div class="col-sm-12"></div></div>
    <div class="row"><div class="col-sm-12"></div></div>
    <div class="row"><div class="col-sm-12"></div></div>
    <div class="row"><div class="col-sm-12"></div></div><br><br>
    
      			
      	<div class="row">
      <div class="col-sm-6">
      	<input type="password" name="confirmPassword"
					placeholder="Confirm Password"
					value="<%=DataUtility.getStringData(dto.getConfirmPassword())%>"><br><font
					color="red"> <%=ServletUtility.getErrorMessage("confirmPassword", request)%></font>
      </div>
      <div class="col-sm-6">
      	<input type="text" name="mobno"
					placeholder="Mobile Number"
					value="<%=DataUtility.getStringData(dto.getMobileNo())%>"><br><font
					color="red"> <%=ServletUtility.getErrorMessage("mobno", request)%></font>
      			</div></div> 
      	
      	
      	<div class="row"><div class="col-sm-12"></div></div>
    <div class="row"><div class="col-sm-12"></div></div>
    <div class="row"><div class="col-sm-12"></div></div>
    <div class="row"><div class="col-sm-12"></div></div>
    <div class="row"><div class="col-sm-12"></div></div>
    <div class="row"><div class="col-sm-12"></div></div>
    <div class="row"><div class="col-sm-12"></div></div>
    <div class="row"><div class="col-sm-12"></div></div>
    <div class="row"><div class="col-sm-12"></div></div>
    <div class="row"><div class="col-sm-12"></div></div>
    <div class="row"><div class="col-sm-12"></div></div>
    <div class="row"><div class="col-sm-12"></div></div>
    <div class="row"><div class="col-sm-12"></div></div>
    <div class="row"><div class="col-sm-12"></div></div>
    <div class="row"><div class="col-sm-12"></div></div>
    <div class="row"><div class="col-sm-12"></div></div>
    <div class="row"><div class="col-sm-12"></div></div>
    <div class="row"><div class="col-sm-12"></div></div>
    <div class="row"><div class="col-sm-12"></div></div>
    <div class="row"><div class="col-sm-12"></div></div><br><br>
    
    <div class="row">
      <div class="col-sm-3"></div><div class="col-sm-6"><input type="text" id="datepicker" name="dob"
					readonly="readonly" placeholder="DOB"
					value="<%=DataUtility.getDateString(dto.getDob())%>"> </a><font
					color="red"> <%=ServletUtility.getErrorMessage("dob", request)%></font></div>
    </div>  <div class="col-sm-3"></div>
    <br>
    
    
    <div class="row">
      <div class="col-sm-6">
      	<button type="submit" class="btn btn-primary" name="operation"
								value="<%=UserRegistrationCtl.OP_SIGN_UP%>" style="background-color:#2ecc71">
								<span class="glyphicon glyphicon-user"></span>
								<%=UserRegistrationCtl.OP_SIGN_UP%>
							</button>
					     </div>
      <div class="col-sm-6">
      	<button type="submit" class="btn btn-primary" name="operation"
								value="<%=UserRegistrationCtl.OP_RESET%>" style="background-color:#2ecc71">
								<i class="fa fa-refresh fa-spin"></i>
								<%=UserRegistrationCtl.OP_RESET%>
							</button>
      			</div></div>
      			
      			<div class="row"><div class="col-sm-12"></div></div>
    <div class="row"><div class="col-sm-12"></div></div>
    <div class="row"><div class="col-sm-12"></div></div>
    <div class="row"><div class="col-sm-12"></div></div>
    <div class="row"><div class="col-sm-12"></div></div>
    <div class="row"><div class="col-sm-12"></div></div>
    <div class="row"><div class="col-sm-12"></div></div>
    <div class="row"><div class="col-sm-12"></div></div>
    <div class="row"><div class="col-sm-12"></div></div>
    <div class="row"><div class="col-sm-12"></div></div>
    <div class="row"><div class="col-sm-12"></div></div>
    <div class="row"><div class="col-sm-12"></div></div>
    <div class="row"><div class="col-sm-12"></div></div>
    <div class="row"><div class="col-sm-12"></div></div>
    <div class="row"><div class="col-sm-12"></div></div>
    <div class="row"><div class="col-sm-12"></div></div>
    <div class="row"><div class="col-sm-12"></div></div>
    <div class="row"><div class="col-sm-12"></div></div>
    <div class="row"><div class="col-sm-12"></div></div>
    <div class="row"><div class="col-sm-12"></div></div>
		</form>
		
   </div>
    </div>
     
    
    
	<div><%@ include file="Footer.jsp"%></div>
</body>
</html>