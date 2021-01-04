<%@page import="java.util.List"%>
<%@page import="in.co.sunrays.proj3.controller.SubjectCtl"%>
<%@page import="in.co.sunrays.proj3.util.HTMLUtility"%>
<%@page import="in.co.sunrays.proj3.util.DataUtility"%>

<%@page import="in.co.sunrays.proj3.util.ServletUtility"%>
<%@page import="in.co.sunrays.proj3.controller.LoginCtl"%>
<%@page import="java.util.HashMap"%>

<!DOCTYPE html>
<html>
<head>
<body background="/Project3/img/orsproj3.jpg" width="318"
					height="70">
<title>Subject</title>
<%@ include file="header.jsp"%>

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
<form action="<%=ORSView.SUBJECT_CTL%>" method="post">
<div class="container-fluid" align="center" style="margin-top:200px;" >
<jsp:useBean id="dto" class="in.co.sunrays.proj3.dto.SubjectDTO" scope="request"></jsp:useBean>

<aside class="col-sm-4">

<div class="card"  align="center" style="border:4px solid black; width:">
<article class="card-body">


<%if(dto.getId()>0){ %>
<h4 class="card-title text-center mb-4 mt-1" style="color: black;">Update subject</h4>


<%}else{ %>
	<h4 class="card-title text-center mb-4 mt-1" style="color: black;">Add subject</h4>
	<%} %>
	<hr>
	
	<input type="hidden" name="id" value="<%=dto.getId()%>">
            <input type="hidden" name="createdBy" value="<%=dto.getCreatedBy()%>">
            <input type="hidden" name="modifiedBy" value="<%=dto.getModifiedBy()%>"> 
            <input type="hidden" name="createdDatetime" value="<%=DataUtility.getTimestamp(dto.getCreatedDatetime())%>">
            <input type="hidden" name="modifiedDatetime" value="<%=DataUtility.getTimestamp(dto.getModifiedDatetime())%>">
       
 <%List l=(List)request.getAttribute("courceName"); %>
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
  
  <div class="form-group">
			
    <div align="left" style="color:#8A2BE2; " for="validationDefault02">Course name<span style="color: red">*</span></div>
	<div class="input-group">
    <div class="input-group-prepend">
    <span class="input-group-text"><i class="fa fa-book"></i> </span></div>
		 
   <%--  <input type="text" name="subject" class="form-control" placeholder="subject name" value="<%=DataUtility.getStringData(dto.getsubjectId())%>">
	 --%>
	 
	  <%=HTMLUtility.getList("courceId",String.valueOf(dto.getCourceId()),l)%></td>
     	
	</div>
	<div align="left"><font style="color: red" ><%=ServletUtility.getErrorMessage("courceId", request)%></font></div>
	
	</div>
	
  
 	
	<div class="form-group">
			
    <div align="left" style="color:#8A2BE2; " for="validationDefault02">Subject name<span style="color: red">*</span></div>
	<div class="input-group">
    <div class="input-group-prepend">
    <span class="input-group-text"><i class="fa fa-copy"></i> </span></div>
		 
    <input type="text" name="sname" class="form-control" placeholder="subject name" value="<%=DataUtility.getStringData(dto.getSubjectName())%>">
		
	</div>
	<div align="left"><font style="color: red" ><%=ServletUtility.getErrorMessage("sname", request)%></font></div>
	
	</div>
	
	<div class="form-group">
			
    <div align="left" style="color:#8A2BE2; " for="validationDefault02">Description<span style="color: red">*</span></div>
	<div class="input-group">
    <div class="input-group-prepend">
    <span class="input-group-text"><i class='fas fa-qrcode'></i></span></div>
		 
    <input type="text" name="description" class="form-control" placeholder="Description" value="<%=DataUtility.getStringData(dto.getDescription())%>">
		
	</div>
	<div align="left"><font style="color: red" ><%=ServletUtility.getErrorMessage("description", request)%></font></div>
	
	</div>
	
	<%if(dto.getId()>0){ %>
	
	 <button type="submit" name="operation" style="float: left;width: 80px; margin-left: 50px;"  value="<%=SubjectCtl.OP_UPDATE%>"  class="btn btn-primary btn-block button2 msgBtn" >Update </button>
		 
	
	<button type="submit" name="operation" style="width: 80px;margin-left: 150px;" value="<%=SubjectCtl.OP_CANCEL%>"  class="btn btn-primary btn-block button2" >Cancle</button>
	
	
	
	<%} else{%>
	
		
	 <button type="submit" name="operation" style="float: left;width: 80px; margin-left: 50px;"  value="<%=SubjectCtl.OP_SAVE%>"  class="btn btn-primary btn-block button2 msgBtn" > Submit </button>
		 
	
	<button type="submit" name="operation" style="width: 80px;margin-left: 150px;" value="<%=SubjectCtl.OP_RESET%>"  class="btn btn-primary btn-block button2" >Reset</button>
		 
	<%} %>

	
   </article>
</div>

	</aside>
</div> 

</div> 

</form>
</body><br><br><br><br><br><br><br><br>
<%@ include file="Footer.jsp"%>
</html>