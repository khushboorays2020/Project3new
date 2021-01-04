<%@page import="in.co.sunrays.proj3.controller.TimeTableCtl"%>
<%@page import="in.co.sunrays.proj3.util.HTMLUtility"%>
<%@page import="in.co.sunrays.proj3.util.DataUtility"%>

<%@page import="in.co.sunrays.proj3.util.ServletUtility"%>
<%@page import="in.co.sunrays.proj3.controller.LoginCtl"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>


<!DOCTYPE html>
<html>
<head>
<title>TimeTable</title>
<%@ include file="header.jsp"%>
 <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/all.css">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/all.css">
 <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

	
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
<form action="<%=ORSView.TIMETABLE_CTL%>" method="post">
<div class="container-fluid" align="center" style="margin-top:140px;" >
<jsp:useBean id="dto" class="in.co.sunrays.proj3.dto.TimeTableDTO" scope="request"></jsp:useBean>

<aside class="col-sm-4">

<div class="card"  align="center" style="border:4px solid black; width:">
<article class="card-body">


<%if(dto.getId()>0) {%>
<h4 class="card-title text-center mb-4 mt-1" style="color: black;">Update timeTable</h4>
	<%}else{ %>
	<h4 class="card-title text-center mb-4 mt-1" style="color: black;">Add timeTable</h4>
	<%} %>
	<hr>
	<%
        List l = (List) request.getAttribute("courceName");
        
        %>
       <%
       List l1 = (List) request.getAttribute("subjectName");
       
       
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
			
    <div align="left" style="color:#8A2BE2; " for="validationDefault02">Course name<span style="color: red">*</span></div>
	<div class="input-group">
    <div class="input-group-prepend">
    <span class="input-group-text"><i class="fa fa-book"></i> </span></div>
		 
   <%--  <input type="text" name="courceId" class="form-control" placeholder="role name" value="<%=DataUtility.getStringData(dto.getLogin())%>">
	 --%>	
	 <%=HTMLUtility.getList("courseId",String.valueOf(dto.getCourseId()),l)%>
	</div>
	<div align="left"><font style="color: red" ><%=ServletUtility.getErrorMessage("courseId", request)%></font></div>
	
	</div>
	
	<div class="form-group">
			
    <div align="left" style="color:#8A2BE2; " for="validationDefault02">Subject name<span style="color: red">*</span></div>
	<div class="input-group">
    <div class="input-group-prepend">
    <span class="input-group-text"><i class="fa fa-copy"></i> </span></div>
		 
   <%--  <input type="text" name="subjectId" class="form-control" placeholder="role name" value="<%=DataUtility.getStringData(dto.getLogin())%>">
	 --%>	
	  <%=HTMLUtility.getList("subjectId",String.valueOf(dto.getSubjectId()),l1) %>
	</div>
	<div align="left"><font style="color: red" ><%=ServletUtility.getErrorMessage("subjectId", request)%></font></div>
	
	</div>
	
	
	<div class="form-group">
			
    <div align="left" style="color:#8A2BE2; " for="validationDefault02">Semester<span style="color: red">*</span></div>
	<div class="input-group">
    <div class="input-group-prepend">
   <span class="input-group-text"><i class="fa fa-book"></i> </span></div>
		 
    <%-- <input type="text" name="login" class="form-control" placeholder="LoginId" value="<%=DataUtility.getStringData(dto.getLogin())%>">
 --%>	
 <% HashMap map = new HashMap();
 map.put("1st", "first");
 map.put("2nd", "second");
 map.put("3rd", "third");
 map.put("4th", "forth");
 map.put("5th", "fifth");
 map.put("6th", "sixth");
 map.put("7th", "seventh");
 map.put("8th", "eight");
 map.put("9th", "nineth");
 map.put("10", "tenth");
 String html1=HTMLUtility.getList("semester",dto.getSemester(), map);
	
	%>

	<%=html1%><br>						
              
 	
	</div>
	<div align="left"><font style="color: red" ><%=ServletUtility.getErrorMessage("semester", request)%></font></div>
	
	</div>
	
	<div class="form-group">
			
    <div align="left" style="color:#8A2BE2; " for="validationDefault02">Examtime<span style="color: red">*</span></div>
	<div class="input-group">
    <div class="input-group-prepend">
    <span class="input-group-text"><i class="fas fa-clock"></i> </span></div>
	
 
 <% 
  HashMap map1 = new HashMap();
                            map1.put("8:00 am to 11:00 am ", "8:00 am to 11:00 am ");
                            map1.put("9:00 am to 12:00 pm", "9:00 am to 12:00 pm");
                            map1.put("10:00 am to 1:00 pm", "10:00 am to 1:00 pm");
                            map1.put("11:00 am to 2:00 pm", "11:00 am to 2:00 pm");
                            map1.put("12:00 pm to 3:00 pm", "12:00 pm to 3:00 pm");
                            map1.put("12:00 pm to 3:00 pm", "12:00 pm to 3:00 pm");
                            map1.put("2:00 am to 5:00 pm", "2:00 am to 5:00 pm");
                     
	%>

    <%=HTMLUtility.getList("time",dto.getTime(), map1) %>          
 	
	</div>
	<div align="left"><font style="color: red" ><%=ServletUtility.getErrorMessage("time", request)%></font></div>
	
	</div>
	
	
	
	
	
	<div class="form-group">
			
    <div align="left" style="color:#8A2BE2; " for="validationDefault02">Exam date<span style="color: red">*</span></div>
	<div class="input-group">
    <div class="input-group-prepend">
      <span class="input-group-text"><i class="fal fa-table"></i></span></div>	 
    <input type="text" name="examDate" id="datepicker2" class="form-control" placeholder="ExamDate" value="<%=DataUtility.getDateString(dto.getExamDate())%>">
		
	</div>
	<div align="left"><font style="color: red" ><%=ServletUtility.getErrorMessage("examDate", request)%></font></div>
	
	</div>
	
	<%if(dto.getId()>0){ %>
	
	 <button type="submit" name="operation" style="float: left;width: 80px; margin-left: 50px;"  value="<%=TimeTableCtl.OP_UPDATE%>"  class="btn btn-primary btn-block button2 msgBtn" >Update </button>
		 
	
	<button type="submit" name="operation" style="width: 80px;margin-left: 150px;" value="<%=TimeTableCtl.OP_CANCEL%>"  class="btn btn-primary btn-block button2" >Cancle</button>
	
	
	
	<%} else{%>
		
	 <button type="submit" name="operation" style="float: left;width: 80px;margin-left: 50px;"  value="<%=TimeTableCtl.OP_SAVE%>"  class="btn btn-dark btn-block button2 msgBtn" > Submit </button>
		 
	
	<button type="submit" name="operation" style="width: 80px;margin-left: 100px;" value="<%=TimeTableCtl.OP_RESET%>"  class="btn btn-dark btn-block button2" >Reset</button>
		 
	<%} %>

	
   </article>
</div>

	</aside>
</div> 

</div> 

</form>
</body><br>
<%@ include file="Footer.jsp"%>
</html>