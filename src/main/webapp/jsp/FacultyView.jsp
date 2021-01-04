<%@page import="in.co.sunrays.proj3.controller.FacultyCtl"%>
<%@page import="in.co.sunrays.proj3.dto.FacultyDTO"%>

<%@page import="in.co.sunrays.proj3.util.HTMLUtility"%>
<%@page import="in.co.sunrays.proj3.util.DataUtility"%>
<%@page import="in.co.sunrays.proj3.util.ServletUtility"%>
<%@page import="in.co.sunrays.proj3.controller.LoginCtl"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>



<!DOCTYPE html>
<html>
<head>
<body background="/Project3/img/c1.jpg" width="318"
					height="70">
<title>Faculty</title>

<%@ include file="header.jsp"%>

<!-- <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/all.css">
 --><link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
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


<form action="<%=ORSView.FACULTY_CTL%>" method="post">
<div class="container-fluid" align="center" style="margin-top:100px;" >
<jsp:useBean id="dto" class="in.co.sunrays.proj3.dto.FacultyDTO" scope="request"></jsp:useBean>

<aside class="col-sm-4">

<div class="card"  align="center" style="border:4px solid black; width:">
<article class="card-body">


<%if(dto.getId()!=0){ %>
<h4 class="card-title text-center mb-4 mt-1" style="color: black;">Update faculty</h4>
	<%}else{ %>
	<h4 class="card-title text-center mb-4 mt-1" style="color: black;">Add faculty</h4>
	<%} %>
	<hr>
	
	<%
		
 List courseList=(List)request.getAttribute("courceName");
 List subjectList=(List)request.getAttribute("subjectName");
 List collegeList=(List)request.getAttribute("collegeName");
 
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
    <span class="input-group-text"><i class="fas fa-user"></i> </span></div>
		  
    <input type="text" name="fname" class="form-control" placeholder="first name" value="<%=DataUtility.getStringData(dto.getFirst_Name())%>">
		
	</div>
	<div align="left"><font style="color: red" ><%=ServletUtility.getErrorMessage("fname", request)%></font></div>
	
	</div>
	
	<div class="form-group">
			
    <div align="left" style="color:#8A2BE2; " for="validationDefault02">Last name<span style="color: red">*</span></div>
	<div class="input-group">
    <div class="input-group-prepend">
    <span class="input-group-text"> <i class="fas fa-user"></i></i> </span></div>
		 
    <input type="text" name="lname" class="form-control" placeholder="last name" value="<%=DataUtility.getStringData(dto.getLast_Name())%>">
		
	</div>
	<div align="left"><font style="color: red" ><%=ServletUtility.getErrorMessage("lname", request)%></font></div>
	
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
						String html=HTMLUtility.getList("gender",dto.getGrnder(), map);
						
						%>
				
						<%=html%><br>
						
              
 	
	</div>
	<div align="left"><font style="color: red" ><%=ServletUtility.getErrorMessage("gender1", request)%></font></div>
	
	</div>
	
	<div class="form-group">
			
    <div align="left" style="color:#8A2BE2; " for="validationDefault02">Address<span style="color: red">*</span></div>
	<div class="input-group">
    <div class="input-group-prepend">
    <span class="input-group-text"><i class='fas fa-mobile-alt'></i></span></div>
		 
    <input type="text" name="address" class="form-control" placeholder="Address" value="<%=DataUtility.getStringData(dto.getAddress())%>">
		
	</div>
	<div align="left"><font style="color: red" ><%=ServletUtility.getErrorMessage("address", request)%></font></div>
	
	</div>
	
	
	
	<div class="form-group">
			
    <div align="left" style="color:#8A2BE2; " for="validationDefault02">LoginId<span style="color: red">*</span></div>
	<div class="input-group">
    <div class="input-group-prepend">
    <span class="input-group-text"> <i class="fas fa-envelope"></i> </span></div>
		 
    <input type="text" name="login" class="form-control" placeholder="LoginId" value="<%=DataUtility.getStringData(dto.getLogin_Id())%>">
		
	</div>
	<div align="left"><font style="color: red" ><%=ServletUtility.getErrorMessage("login", request)%></font></div>
	
	</div>
	
	<div class="form-group">
			
    <div align="left" style="color:#8A2BE2; " for="validationDefault02">Date of joining<span style="color: red">*</span></div>
	<div class="input-group">
    <div class="input-group-prepend">
    <span class="input-group-text"> <i class='fas fa-calendar-alt'></i></span></div>
		 
    <input type="text" name="doj" class="form-control" id="date" placeholder="DOB" value="<%=DataUtility.getDateString(dto.getDate_Of_joining())%>">
		
	</div>
	<div align="left"><font style="color: red" ><%=ServletUtility.getErrorMessage("doj", request)%></font></div>
	
	</div>
	
	<div class="form-group">
			
    <div align="left" style="color:#8A2BE2; " for="validationDefault02">Qualification<span style="color: red">*</span></div>
	<div class="input-group">
    <div class="input-group-prepend">
    <span class="input-group-text"> <i class='fas fa-venus-double'></i></span></div>
		 
    <%-- <input type="text" name="login" class="form-control" placeholder="LoginId" value="<%=DataUtility.getStringData(dto.getLogin())%>">
 --%>	
 <% HashMap map1 = new HashMap();
 map1.put("BCA", "BCA");
 map1.put("BTECH", "BTECH");
 map1.put("BE", "BE");
 map1.put("BSC", "BSC");
 map1.put("MSC", "MSC");
 map1.put("MTECH", "MTECH");
 map1.put("MBA", "MBA");
 map1.put("MCA", "MCA");
 map1.put("POLYTECHNIQUE", "POLYTECHNIQUE");
 map1.put("BA", "BA");
 map1.put("MA", "MA");
 map1.put("MBBS", "MBBS");
 map1.put("PHD", "PHD");
 String html1=HTMLUtility.getList("qualification",dto.getQualification(), map1);
	
	%>

	<%=html1%><br>						
              
 	
	</div>
	<div align="left"><font style="color: red" ><%=ServletUtility.getErrorMessage("qualification1", request)%></font></div>
	
	</div>
	
	<div class="form-group">
			
    <div align="left" style="color:#8A2BE2; " for="validationDefault02">Mobile<span style="color: red">*</span></div>
	<div class="input-group">
    <div class="input-group-prepend">
    <span class="input-group-text"><i class='fas fa-mobile-alt'></i></span></div>
		 
    <input type="text" name="mobile" class="form-control" placeholder="mobile" value="<%=DataUtility.getStringData(dto.getMobile_No())%>">
		
	</div>
	<div align="left"><font style="color: red" ><%=ServletUtility.getErrorMessage("mobileno", request)%></font></div>
	
	</div>
	
	<div class="form-group">
			
    <div align="left" style="color:#8A2BE2; " for="validationDefault02">College name<span style="color: red">*</span></div>
	<div class="input-group">
    <div class="input-group-prepend">
    <span class="input-group-text"><i class="fa fa-university"></i> </span></div>
		 
    <%-- <input type="text" name="collegeId" class="form-control" placeholder="college" value="<%=DataUtility.getStringData(dto.getCollege_Name())%>">
	 --%>	
	  <%=HTMLUtility.getList("collegeId",String.valueOf(dto.getCollege_Id()),collegeList)%>
             
	</div>
	<div align="left"><font style="color: red" ><%=ServletUtility.getErrorMessage("collegeId1", request)%></font></div>
	
	</div>
	
	<div class="form-group">
			
    <div align="left" style="color:#8A2BE2; " for="validationDefault02">Course name<span style="color: red">*</span></div>
	<div class="input-group">
    <div class="input-group-prepend">
    <span class="input-group-text"><i class="fa fa-book"></i> </span></div>
		 
    
	  <%=HTMLUtility.getList("courceId",String.valueOf(dto.getCource_Id()),courseList)%></td>
                 
	</div>
	<div align="left"><font style="color: red" ><%=ServletUtility.getErrorMessage("courceId", request)%></font></div>
	
	</div>
	<%--  <input type="text" name="courceId" class="form-control" placeholder="college" value="<%=DataUtility.getStringData(dto.getCource_Name())%>">
	 --%>	
	
	
	<div class="form-group">
			
    <div align="left" style="color:#8A2BE2; " for="validationDefault02">Subject name<span style="color: red">*</span></div>
	<div class="input-group">
    <div class="input-group-prepend">
    <span class="input-group-text"><i class="fa fa-copy"></i> </span></div>
		 
    <%-- <input type="text" name="subjectId" class="form-control" placeholder="college" value="<%=DataUtility.getStringData(dto.getCollege_Name())%>">
	 --%>
	 
	  <%=HTMLUtility.getList("subjectId",String.valueOf(dto.getSubject_Id()),subjectList)%></td>
          
	 	
	</div>
	<div align="left"><font style="color: red" ><%=ServletUtility.getErrorMessage("subjectId1", request)%></font></div>
	
	</div>
		
	<%if(dto.getId()>0){ %>
	
	 <button type="submit" name="operation" style="float: left;width: 80px; margin-left: 50px;"  value="<%=FacultyCtl.OP_UPDATE%>"  class="btn btn-primary btn-block button2 msgBtn" >Update </button>
		 
	
	<button type="submit" name="operation" style="width: 80px;margin-left: 150px;" value="<%=FacultyCtl.OP_CANCEL%>"  class="btn btn-primary btn-block button2" >Cancle</button>
	
	
	
	<%} else{%>
	
	 <button type="submit" name="operation" style="float: left;width: 80px; margin-left: 50px;"  value="<%=FacultyCtl.OP_SAVE%>"  class="btn btn-primary btn-block button2 msgBtn" > Submit </button>
		 
	
	<button type="submit" name="operation" style="width: 80px;margin-left: 150px;" value="<%=FacultyCtl.OP_RESET%>"  class="btn btn-primary btn-block button2" >Reset</button>
		 
	<%} %>

	
   </article>
</div>

	</aside>
</div> 

</div> 

</form>
</body><br><br><br>
<%@ include file="Footer.jsp"%>
</html>