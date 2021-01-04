<%@page import="in.co.sunrays.proj3.controller.StudentCtl"%>
<%@page import="in.co.sunrays.proj3.util.HTMLUtility"%>
<%@page import="in.co.sunrays.proj3.util.DataUtility"%>

<%@page import="in.co.sunrays.proj3.util.ServletUtility"%>
<%@page import="in.co.sunrays.proj3.controller.LoginCtl"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>

<!DOCTYPE html>
<html>
<head>
<body background="/Project3/img/exam1.jpg" width="318"
					height="70"></head>
<title>Student</title>
<%@ include file="header.jsp"%>

<!-- <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/all.css">
 --><link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/1.7.1/js/bootstrap-datepicker.min.js"></script>

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
<script type="text/javascript">
    $('#datepicker22').datepicker({
        weekStart: 1,
        daysOfWeekHighlighted: "6,0",
        autoclose: true,
        todayHighlight: true,
    });
    $('#datepicker22').datepicker("setDate", new Date());
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

<body background="/Project3/img/exam1" width="318"
					height="70">
<form action="<%=ORSView.STUDENT_CTL%>" method="post">
<div class="container-fluid" align="center" style="margin-top:100px;" >
<jsp:useBean id="dto" class="in.co.sunrays.proj3.dto.StudentDTO" scope="request"></jsp:useBean>
<br>
<br>
<aside class="col-sm-4">

<div class="card"  align="center" style="border:4px solid black; width:">
<article class="card-body">

<%if(dto.getId()>0 && dto!=null){ %>
<h4 class="card-title text-center mb-4 mt-1" style="color: black;">Update Student</h4>


<%}else{ %>

	<h4 class="card-title text-center mb-4 mt-1" style="color: black;">Add Student</h4>
	<%} %>
	<hr>
	
	<%List collegeList=(List)request.getAttribute("collegeList");
	  %>
	
 <%
							if (ServletUtility.getSuccessMessage(request) != null
									&& ServletUtility.getSuccessMessage(request).length() > 0) {
						%>
						<div class="alert alert-success ">
							<button type="button" class="close" data-dismiss="alert">&times;</button>
							<strong>Success..!</strong><%=ServletUtility.getSuccessMessage(request)%>
						</div>
						<%
							}
						%>

						<%
							if (ServletUtility.getErrorMessage(request) != null
									&& ServletUtility.getErrorMessage(request).length() > 0) {
						%>

						<div class="alert alert-danger ">
							<button type="button" class="close" data-dismiss="alert">&times;</button>
							<strong>Error..!</strong><%=ServletUtility.getErrorMessage(request)%>
						</div>
						<%
							}
						%>
						
						
 
   <input type="hidden" name="id" value="<%=dto.getId()%>">
            <input type="hidden" name="createdBy" value="<%=dto.getCreatedBy()%>">
            <input type="hidden" name="modifiedBy" value="<%=dto.getModifiedBy()%>"> 
            <input type="hidden" name="createdDatetime" value="<%=DataUtility.getTimestamp(dto.getCreatedDatetime())%>">
            <input type="hidden" name="modifiedDatetime" value="<%=DataUtility.getTimestamp(dto.getModifiedDatetime())%>">
       
 
 	
	
	<div class="form-group">
			
    <div align="left" style="color:#8A2BE2; " for="validationDefault02">First name<span style="color: red">*</span></div>
	<div class="input-group">
    <div class="input-group-prepend">
    <span class="input-group-text"> <i class="fas fa-user"></i></span></div>
		 
    <input type="text" name="firstName" class="form-control" placeholder="first name" value="<%=DataUtility.getStringData(dto.getFirstName())%>">
		
	</div>
	<div align="left"><font style="color: red" ><%=ServletUtility.getErrorMessage("firstName", request)%></font></div>
	
	</div>
	
	<div class="form-group">
			
    <div align="left" style="color:#8A2BE2; " for="validationDefault02">Last name<span style="color: red">*</span></div>
	<div class="input-group">
    <div class="input-group-prepend">
    <span class="input-group-text"> <i class="fas fa-user"></i></i> </span></div>
		 
    <input type="text" name="lastName" class="form-control" placeholder="Last name" value="<%=DataUtility.getStringData(dto.getLastName())%>">
		
	</div>
	<div align="left"><font style="color: red" ><%=ServletUtility.getErrorMessage("lastName", request)%></font></div>
	
	</div>
	
	<div class="form-group">
			
    <div align="left" style="color:#8A2BE2; " for="validationDefault02">Email<span style="color: red">*</span></div>
	<div class="input-group">
    <div class="input-group-prepend">
    <span class="input-group-text"> <i class='fas fa-envelope'></i></span></div>
		 
    <input type="text" name="email"" class="form-control" placeholder="LoginId" value="<%=DataUtility.getStringData(dto.getEmail())%>">
		
	</div>
	<div align="left"><font style="color: red" ><%=ServletUtility.getErrorMessage("email", request)%></font></div>
	
	</div>
	
	
	
	<div class="form-group">
			
    <div align="left" style="color:#8A2BE2; " for="validationDefault02">College<span style="color: red">*</span></div>
	<div class="input-group">
    <div class="input-group-prepend">
    <span class="input-group-text"><i class="fa fa-university"></i> </span></div>
		 
    <%-- <input type="text" name="college" class="form-control" placeholder="college" value="<%=DataUtility.getStringData(dto.getCollegeName())%>">
	 --%>
	 
	 <%=HTMLUtility.getList("collegeId",String.valueOf(dto.getCollegeId()),collegeList)%></td>
      	
	</div>
	<div align="left"><font style="color: red" ><%=ServletUtility.getErrorMessage("collegeId", request)%></font></div>
	
	</div>
	
	
	<div class="form-group pmd-textfield pmd-textfield-floating-label">
	
	<div align="left" style="color:#8A2BE2;  for="datepicker-view-mode">DOB<span style="color: red">*</span></div>
	<div class="input-group">
	<div class="input-group-prepend">
    <span class="input-group-text"> <i class='fas fa-calendar-alt'></i></span></div>
	    
	 <input type="text" name="dob" class="form-control"  id="datepicker3"  width="276" placeholder="DOB" value="<%=DataUtility.getDateString(dto.getDob())%>">
	</div>
	
	<div align="left"> <font style="color: red"><%=ServletUtility.getErrorMessage("dob", request)%></font></div>
	</div>
	
	<div class="form-group">
	
	<div align="left" style="color:#8A2BE2;  for="validationDefault02">Mobile<span style="color: red">*</span></div>
	<div class="input-group">
	<div class="input-group-prepend">
    <span class="input-group-text"> <i class='fas fa-mobile-alt'></i> </span></div>
	    
	 <input type="text" name="mobno" class="form-control" placeholder="mobile" value="<%=DataUtility.getStringData(dto.getMobileNo())%>">
	</div>
	
	<div align="left"> <font style="color: red"><%=ServletUtility.getErrorMessage("mobno", request)%></font></div>
	</div>
	
<%if(dto.getId()>0 && dto!=null){ %>
	
	 <button type="submit" name="operation" style="float: left;width: 80px; margin-left: 50px;"  value="<%=StudentCtl.OP_UPDATE%>"  class="btn btn-primary btn-block button2 msgBtn" >Update </button>
		 
	
	<button type="submit" name="operation" style="width: 80px;margin-left: 150px;" value="<%=StudentCtl.OP_CANCEL%>"  class="btn btn-primary btn-block button2" >Cancle</button>
	
	
	
	<%} else{%>
	
	
	 <button type="submit" name="operation" style="float: left;width: 80px; margin-left: 50px;"  value="<%=StudentCtl.OP_SAVE%>"  class="btn btn-primary btn-block button2 msgBtn" > Submit </button>
		 
	
	<button type="submit" name="operation" style="width: 80px;margin-left: 150px;" value="<%=StudentCtl.OP_RESET%>"  class="btn btn-primary btn-block button2" >Reset</button>
		 
	
<%} %>
	
   </article>
</div>

	</aside>
</div> 

</div> 

</form>
</body><br><br><br><br>
<%@ include file="Footer.jsp"%>
</html>