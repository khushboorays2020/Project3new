<%@page import="in.co.sunrays.proj3.util.DataUtility"%>

<%@page import="in.co.sunrays.proj3.util.ServletUtility"%>
<%@page import="in.co.sunrays.proj3.controller.LoginCtl"%>

<!DOCTYPE html>
<html>
<head>
<body background="img/profilrbackground.jpeg" width="318"
					height="70">
<title>Login</title>
<style type="text/css">
	.button2 {
  background-color: white; 
  color: black; 
  border: 2px solid #008CBA;
}
</style>

</head>
<body background="/Project3/img/profilrbackground.jpeg" width="318"
					height="70">

<form action="<%=ORSView.LOGIN_CTL%>" method="post">
<div class="container-fluid" align="center" style="margin-top:150px;" >

<jsp:useBean id="dto" class="in.co.sunrays.proj3.dto.UserDTO" scope="request"></jsp:useBean>
<%@ include file="header.jsp"%>
<aside class="col-md-4">

<div class="card" style="border:4px solid black;">
<article class="card-body">

<a href="<%=ORSView.USER_REGISTRATION_CTL %>" class="float-center btn btn-outline-dark">Sign up</a>

	<h4 class="card-title text-center mb-4 mt-1" style="color: black;">Sign in</h4>
	
	<hr>
	
  <%if(ServletUtility.getSuccessMessage(request) !=null && ServletUtility.getSuccessMessage(request).length()>0 ){ %>
  <div class="alert alert-success alert-dismissible fade show">
    <button type="button" class="close" data-dismiss="alert">&times;</button>
    <strong>Success..!</strong>Logout successfully
  </div>
  <%} %>
  
  <%if(ServletUtility.getErrorMessage(request) !=null && ServletUtility.getErrorMessage(request).length()>0 ){ %>
 
  <div class="alert alert-danger alert-dismissible fade show">
    <button type="button" class="close" data-dismiss="alert">&times;</button>
    <strong>Error..!</strong> User Id or Password is Invalid .
  </div>
  <%} %>
  
  <%if(request.getAttribute("message") !=null){%>
 
  <div class="alert alert-danger alert-dismissible fade show">
    <button type="button" class="close" data-dismiss="alert">&times;</button>
    <strong>Error..!</strong>Your session has been expired please login again
  </div>
  <%} %>
 
 	
	<div class="form-group">
			
    <div align="left" style="color:#8A2BE2; " for="validationDefault02">LoginId<span style="color: red">*</span></div>
	<div class="input-group">
    <div class="input-group-prepend">
    <span class="input-group-text"><i class="fas fa-envelope"></i> </span></div>
		 
    <input type="text" name="login" class="form-control" placeholder="LoginId" value="<%=DataUtility.getStringData(dto.getLogin())%>">
		
	</div>
	<div align="left"><font style="color: red" ><%=ServletUtility.getErrorMessage("login", request)%></font></div>
	
	</div>
	
	<div class="form-group">
	
	<div align="left" style="color:#8A2BE2;  for="validationDefault02">Password<span style="color: red">*</span></div>
	<div class="input-group">
	<div class="input-group-prepend">
    <span class="input-group-text"> <i class="fa fa-lock"></i> </span></div>
	    
	 <input type="password" name="password" class="form-control" placeholder="********" value="<%=DataUtility.getStringData(dto.getPassword())%>">
	</div>
	
	<div align="left"> <font style="color: red"><%=ServletUtility.getErrorMessage("password", request)%></font></div>
	</div>
	
    <button type="submit" name="operation"  value="<%=LoginCtl.OP_SIGN_IN%>"  class="btn btn-dark btn-block button2" title="this is for login"> <i  class='fa fa-paper-plane' style="color:" aria-hidden="true"></i> Login  </button>
	
	<p class="text-center"><a href="<%=ORSView.FORGET_PASSWORD_CTL%>" class="btn " title="this is for recover password">Forgot password?</a></p>
	</div>
	
   </article>
</div>

	</aside>
</div> 

</div> 

</form>
</body><br><br><br><br><br><br><br>
<%@ include file="Footer1.jsp"%>
</html>