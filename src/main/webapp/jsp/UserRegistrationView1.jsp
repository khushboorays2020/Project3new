<%@page import="in.co.sunrays.proj3.controller.ORSView"%>
<%@page import="in.co.sunrays.proj3.controller.UserRegistrationCtl"%>
<%@page import="in.co.sunrays.proj3.util.HTMLUtility"%>
<%@page import="in.co.sunrays.proj3.util.DataUtility"%>
<%@ include file="header.jsp"%>
<%@page import="in.co.sunrays.proj3.util.ServletUtility"%>
<%@page import="in.co.sunrays.proj3.controller.LoginCtl"%>
<%@page import="java.util.HashMap"%>

<!DOCTYPE html>
<html>
<head>
<title>Sign Up</title>
<!-- <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/all.css">
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
 --><script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
	function DisableSunday(date){
		var day = date.getDay();

		if (day == 0) {
			return [ false ];

		} else {
			return [ true ];
		}

	}
	var d = new Date(90, 0, 1);
	$(function() {
		$("#date").datepicker({
			/*  beforeShowDay:DisableSunday, */
			changeMonth : true,
			changeYear : true,
			yearRange : '1980:2001',
			dateFormat : 'dd-mm-yy',
		/*minDate:0 */
		});
	});
</script>

	
<style type="text/css">
	.button2 {
  background-color: white; 
  color: black; 
  border: 2px solid #008CBA;
}
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

.button2:hover {
  background-color: #008CBA;
  color: white;
}




</style>

</head>

<body background="/pro3/image/abc.png">
<form action="<%=ORSView.USER_REGISTRATION_CTL%>" method="post">
<div class="container-fluid" align="center" style="margin-top:100px;" >
<jsp:useBean id="dto" class="in.co.sunrays.proj3.dto.UserDTO" scope="request"></jsp:useBean>

<aside class="col-sm-4">

<div class="card"  align="center" style="border:4px solid black; width:">
<article class="card-body">



	<h4 class="card-title text-center mb-4 mt-1" style="color: black;">Sign Up</h4>
	
	<hr>
	
  <%if(ServletUtility.getSuccessMessage(request) !=null && ServletUtility.getSuccessMessage(request).length()>0 ){ %>
  <div class="alert alert-success alert-dismissible fade show">
    <button type="button" class="close" data-dismiss="alert">&times;</button>
    <strong>Success..!</strong>User Added successfully
  </div>
  <%} %>
  
  <%if(ServletUtility.getErrorMessage(request) !=null && ServletUtility.getErrorMessage(request).length()>0 ){ %>
 
  <div class="alert alert-danger alert-dismissible fade show">
    <button type="button" class="close" data-dismiss="alert">&times;</button>
    <strong>Error..!</strong>LoginId already exists
  </div>
  <%} %>
  
   <input type="hidden" name="id" value="<%=dto.getId()%>">
            <input type="hidden" name="createdBy" value="<%=dto.getCreatedBy()%>">
            <input type="hidden" name="modifiedBy" value="<%=dto.getModifiedBy()%>"> 
            <input type="hidden" name="createdDatetime" value="<%=DataUtility.getTimestamp(dto.getCreatedDatetime())%>">
            <input type="hidden" name="modifiedDatetime" value="<%=DataUtility.getTimestamp(dto.getModifiedDatetime())%>">
       
 	
	<div class="form-group">
			
    <div align="left" style="color:#8A2BE2; " for="validationDefault02">First name<span style="color: red">*</span></div>
	<div class="input-group">
    <div class="input-group-prepend">
    <span class="input-group-text"><i class="fas fa-user"></i> </span></div>
		 
    <input type="text" name="firstname" class="form-control" placeholder="first name" value="<%=DataUtility.getStringData(dto.getFirstName())%>">
		
	</div>
	<div align="left"><font style="color: red" ><%=ServletUtility.getErrorMessage("firstname", request)%></font></div>
	
	</div>
	
	<div class="form-group">
			
    <div align="left" style="color:#8A2BE2; " for="validationDefault02">Last name<span style="color: red">*</span></div>
	<div class="input-group">
    <div class="input-group-prepend">
    <span class="input-group-text"> <i class="fas fa-user"></i></i> </span></div>
		 
    <input type="text" name="lastname" class="form-control" placeholder="last name" value="<%=DataUtility.getStringData(dto.getLastName())%>">
		
	</div>
	<div align="left"><font style="color: red" ><%=ServletUtility.getErrorMessage("lastname", request)%></font></div>
	
	</div>
	
	<div class="form-group">
			
    <div align="left" style="color:#8A2BE2; " for="validationDefault02">LoginId<span style="color: red">*</span></div>
	<div class="input-group">
    <div class="input-group-prepend">
    <span class="input-group-text"> <i class="fas fa-envelope"></i> </span></div>
		 
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
	
	<div class="form-group">
	
	<div align="left" style="color:#8A2BE2;  for="validationDefault02">Confirm password<span style="color: red">*</span></div>
	<div class="input-group">
	<div class="input-group-prepend">
    <span class="input-group-text"> <i class="fa fa-lock"></i> </span></div>
	    
	 <input type="password" name="confirmpassword" class="form-control" placeholder="********" value="<%=DataUtility.getStringData(dto.getConfirmPassword())%>">
	</div>
	
	<div align="left"> <font style="color: red"><%=ServletUtility.getErrorMessage("confirmpassword1", request)%></font></div>
	</div>
	
	
	<div class="form-group">
			
    <div align="left" style="color:#8A2BE2; " for="validationDefault02">Gender<span style="color: red">*</span></div>
	<div class="input-group">
    <div class="input-group-prepend">
    <span class="input-group-text"> <i class='fas fa-venus-double'></i></span></div>
		 
    <%-- <input type="text" name="login" class="form-control" placeholder="LoginId" value="<%=DataUtility.getStringData(dto.getLogin())%>">
 --%>	
 <%
						HashMap map=new HashMap();
						map.put("MALE", "MALE");
						map.put("FEMALE","FEMALE");
						String html=HTMLUtility.getList("gender",dto.getGender(), map);
						
						%>
				
						<%=html%><br>
						
              
 	
	</div>
	<div align="left"><font style="color: red" ><%=ServletUtility.getErrorMessage("gender", request)%></font></div>
	
	</div>
	
	<div class="form-group">
			
    <div align="left" style="color:#8A2BE2; " for="validationDefault02">DOB<span style="color: red">*</span></div>
	<div class="input-group">
    <div class="input-group-prepend">
    <span class="input-group-text"> <i class='fas fa-calendar-alt'></i></span></div>
		 
    <input type="text" name="dob" class="form-control" placeholder="DOB" id="date" value="<%=DataUtility.getDateString(dto.getDob())%>">
		
	</div>
	<div align="left"><font style="color: red" ><%=ServletUtility.getErrorMessage("dob", request)%></font></div>
	
	</div>
	
	<div class="form-group">
			
    <div align="left" style="color:#8A2BE2; " for="validationDefault02">Mobile<span style="color: red">*</span></div>
	<div class="input-group">
    <div class="input-group-prepend">
    <span class="input-group-text"><i class='fas fa-mobile-alt'></i></span></div>
		 
    <input type="text" name="mobile" class="form-control" placeholder="mobile" value="<%=DataUtility.getStringData(dto.getMobileNo())%>">
		
	</div>
	<div align="left"><font style="color: red" ><%=ServletUtility.getErrorMessage("mobile", request)%></font></div>
	
	</div>
	
	
	 <button type="submit" name="operation" style="float: left;width: 80px; margin-left: 50px;"  value="<%=UserRegistrationCtl.OP_SIGN_UP%>"  class="btn btn-primary btn-block button2 msgBtn" > Submit </button>
		 
	
	<button type="submit" name="operation" style="width: 80px;margin-left: 150px;" value="<%=UserRegistrationCtl.OP_RESET%>"  class="btn btn-primary btn-block button2" >Reset</button>
		 
	

	
   </article>
</div>

	</aside>
</div> 

</div> 

</form>
</body>
<%@ include file="Footer.jsp"%>
</html>