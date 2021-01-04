<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ include file="header.jsp"%>
<%@page import="in.co.sunrays.proj3.util.ServletUtility"%>
<%@page import="in.co.sunrays.proj3.controller.LoginCtl"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User resistration</title>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!------ Include the above in your HEAD tag ---------->

<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/all.css">
	
	<style type="text/css">
	.button {
  background-color: #4CAF50; /* Green */
  border: none;
  color: white;
  padding: 16px 32px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
  margin: 4px 2px;
  -webkit-transition-duration: 0.4s; /* Safari */
  transition-duration: 0.4s;
  cursor: pointer;
}
	
	
	.button2 {
  background-color: white; 
  color: black; 
  border: 2px solid #008CBA;
}
</style>

</head>
<body background="/pro3/image/abc.png">
<form action="<%=ORSView.LOGIN_CTL%>" method="post">
<div class="container" align="center" style="margin-top:150px;" >
<jsp:useBean id="dto" class="in.co.sunrays.proj3.dto.UserDTO" scope="request"></jsp:useBean>

	<aside class="col-sm-4">


<div class="card" style="border:4px solid #6A5ACD;">
<article class="card-body">
	<h4 class="card-title text-center mb-4 mt-1" style="color: #C71585;">Sign up</h4>
	<hr>
  <%if(ServletUtility.getSuccessMessage(request) !=null && ServletUtility.getSuccessMessage(request).length()>0 ){ %>
  <div class="alert alert-success alert-dismissible fade show">
    <button type="button" class="close" data-dismiss="alert">&times;</button>
    <strong>Success..!</strong> Your Session is Experied.. Please Login Again.
  </div>
  <%} %>
  <%if(ServletUtility.getErrorMessage(request) !=null && ServletUtility.getErrorMessage(request).length()>0 ){ %>
 
  <div class="alert alert-danger alert-dismissible fade show">
    <button type="button" class="close" data-dismiss="alert">&times;</button>
    <strong>Error..!</strong> User Id or Password is Invalid .
  </div>
  <%} %>
 	
	

	<div class="form-group">
	
	<div class="input-group">
		
		<div class="input-group-prepend">
		    <span class="input-group-text"> <i class="fas fa-envelope"></i> </span>
		 </div>
		  
		<input type="text" name="login" class="form-control" placeholder="Emailid"">
		
	</div> <!-- input-group.// -->
	</div> <!-- form-group// -->
	<div class="form-group">
	
	<div class="input-group">
		
		<div class="input-group-prepend">
		    <span class="input-group-text"> <i class="fas fa-envelope"></i> </span>
		 </div>
		  
		<input type="text" name="login" class="form-control" placeholder="Emailid"">
		
	</div> <!-- input-group.// -->
	</div> <!-- form-group// -->
	<div class="form-group">
	
	<div class="input-group">
		
		<div class="input-group-prepend">
		    <span class="input-group-text"> <i class="fas fa-envelope"></i> </span>
		 </div>
		  
		<input type="text" name="login" class="form-control" placeholder="Emailid"">
		
	</div> <!-- input-group.// -->
	</div> <!-- form-group// -->
	<div class="form-group">
	
	<div class="input-group">
		
		<div class="input-group-prepend">
		    <span class="input-group-text"> <i class="fas fa-envelope"></i> </span>
		 </div>
		  
		<input type="text" name="login" class="form-control" placeholder="Emailid"">
		
	</div> <!-- input-group.// -->
	</div> <!-- form-group// -->
	<div class="form-group">
	
	<div class="input-group">
		
		<div class="input-group-prepend">
		    <span class="input-group-text"> <i class="fas fa-envelope"></i> </span>
		 </div>
		  
		<input type="text" name="login" class="form-control" placeholder="Emailid"">
		
	</div> <!-- input-group.// -->
	</div> <!-- form-group// -->
	<div class="form-group">
	
	<div class="input-group">
		
		<div class="input-group-prepend">
		    <span class="input-group-text"> <i class="fas fa-envelope"></i> </span>
		 </div>
		  
		<input type="text" name="login" class="form-control" placeholder="Emailid"">
		
	</div> <!-- input-group.// -->
	</div> <!-- form-group// -->
	<div class="form-group">
	
	<div class="input-group">
		
		<div class="input-group-prepend">
		    <span class="input-group-text"> <i class="fas fa-envelope"></i> </span>
		 </div>
		  
		<input type="text" name="login" class="form-control" placeholder="Emailid"">
		
	</div> <!-- input-group.// -->
	</div> <!-- form-group// -->
	
	<div class="form-group">
	 <div class="input-group">
		<div class="input-group-prepend">
		    <span class="input-group-text"> <i class="fa fa-lock"></i> </span>
		 </div>
	    <input type="password" name="password" class="form-control" placeholder="***********">
	</div> <!-- input-group.// -->
	</div> <!-- form-group// -->
		
	<button class="button button2">Save</button>

	<p class="text-center"><a href="<%=ORSView.FORGET_PASSWORD_CTL%>" class="btn">Forgot password?</a></p>
	</div> <!-- form-group// -->
	
	
</article>
</div> <!-- card.// -->

	</aside> <!-- col.// -->
</div> <!-- row.// -->

</div> 
<!--container end.//-->
</form>
</body>
</html>