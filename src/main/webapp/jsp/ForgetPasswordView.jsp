<%@page import="in.co.sunrays.proj3.controller.ForgetPasswordCtl"%>
<%@page import="in.co.sunrays.proj3.util.DataUtility"%>
<%@ include file="header.jsp"%>
<%@page import="in.co.sunrays.proj3.util.ServletUtility"%>
<%@page import="in.co.sunrays.proj3.controller.LoginCtl"%>

<!DOCTYPE html>
<html>
<head>
<title>Forget password</title>

<!-- <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/all.css">
 -->	
<style type="text/css">
	.button2 {
  background-color: white; 
  color: black; 
  border: 2px solid #008CBA;
}
</style>

</head>

<body background="/pro3/img/abc.png">
<form action="<%=ORSView.FORGET_PASSWORD_CTL%>" method="post">
<div class="container-fluid" align="center" style="margin-top:150px;" >
<jsp:useBean id="dto" class="in.co.sunrays.proj3.dto.UserDTO" scope="request"></jsp:useBean>

<aside class="col-sm-4">

<div class="card" style="border:4px solid #6A5ACD;">
<article class="card-body">


	<h4 class="card-title text-center mb-4 mt-1" style="color: #C71585;">Forget password</h4>
	  <lable style="color: #9400D3;">Submit your email address and we'll send you password.</lable><br><br>
               
	<hr>
	
  <%if(ServletUtility.getSuccessMessage(request) !=null && ServletUtility.getSuccessMessage(request).length()>0 ){ %>
  <div class="alert alert-success alert-dismissible fade show">
    <button type="button" class="close" data-dismiss="alert">&times;</button>
    <strong>Success..!</strong><%=ServletUtility.getSuccessMessage(request) %>
  </div>
  <%} %>
  
  <%if(ServletUtility.getErrorMessage(request) !=null && ServletUtility.getErrorMessage(request).length()>0 ){ %>
 
  <div class="alert alert-danger alert-dismissible fade show">
    <button type="button" class="close" data-dismiss="alert">&times;</button>
    <strong>Error..!</strong><%=ServletUtility.getErrorMessage(request)%>
  </div>
  <%} %>
  
  <%if(request.getAttribute("message") !=null){%>
 
  <div class="alert alert-danger alert-dismissible fade show">
    <button type="button" class="close" data-dismiss="alert">&times;</button>
    <strong>Error..!</strong>Your session has been expired please login again
  </div>
  <%} %>
 
 	
	<div class="form-group">
			
    <div align="left" style="color:#8A2BE2; " for="validationDefault02">EmailId<span style="color: red">*</span></div>
	<div class="input-group">
    <div class="input-group-prepend">
    <span class="input-group-text"> <i class="fas fa-envelope"></i> </span></div>
		 
    <input type="text" name="login" class="form-control" placeholder="LoginId" value="<%=DataUtility.getStringData(dto.getLogin())%>">
		
	</div>
	<div align="left"><font style="color: red" ><%=ServletUtility.getErrorMessage("login", request)%></font></div>
	
	</div>	
	
    <button type="submit" name="operation"  value="<%=ForgetPasswordCtl.OP_GO%>"  class="btn btn-primary btn-block button2" ><i  class='fa fa-paper-plane' style="color:" aria-hidden="true"></i> GO  </button>
	
	</div>
	
   </article>
</div>

	</aside>
</div> 

</div> 

</form>
</body><br><br><br><br><br><br><br><br><br><br><br>
<%@ include file="Footer.jsp"%>
</html>