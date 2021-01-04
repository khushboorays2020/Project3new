<%@page import="in.co.sunrays.proj3.controller.CollegeCtl"%>
<%@page import="in.co.sunrays.proj3.util.HTMLUtility"%>
<%@page import="in.co.sunrays.proj3.util.DataUtility"%>

<%@page import="in.co.sunrays.proj3.util.ServletUtility"%>
<%@page import="in.co.sunrays.proj3.controller.LoginCtl"%>
<%@page import="java.util.HashMap"%>

<!DOCTYPE html>
<html>
<head>
<body background="/Project3/img/backgroundNew9.jpg" width="318"
					height="70">
<%@ include  file="header.jsp" %>
<title>College</title>


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
<form action="<%=ORSView.COLLEGE_CTL%>" method="post">
<div class="container-fluid" align="center" style="margin-top:100px;" >

<jsp:useBean id="dto" class="in.co.sunrays.proj3.dto.CollegeDTO" scope="request"></jsp:useBean>

<aside class="col-sm-4">

<div class="card"  align="center" style="border:4px solid black; width:">
<article class="card-body">


<%if(dto.getId()>0){ %>

<h4 class="card-title text-center mb-4 mt-1" style="color: black;">Update College</h4>

<%}else{ %>
	<h4 class="card-title text-center mb-4 mt-1" style="color: black;">Add College</h4>
	<%} %>
	<hr>
	
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
			
    <div align="left" style="color:#8A2BE2; " for="validationDefault02">Name<span style="color: red">*</span></div>
	<div class="input-group">
    <div class="input-group-prepend">
    <span class="input-group-text"><i class="fa fa-university"></i> </span></div>
		 
    <input type="text" name="name" class="form-control" placeholder="college" value="<%=DataUtility.getStringData(dto.getName())%>">
		
	</div>
	<div align="left"><font style="color: red" ><%=ServletUtility.getErrorMessage("name", request)%></font></div>
	
	</div>
	
	<div class="form-group">
			
    <div align="left" style="color:#8A2BE2; " for="validationDefault02">Address<span style="color: red">*</span></div>
	<div class="input-group">
    <div class="input-group-prepend">
    <span class="input-group-text"><i class='fas fa-crosshairs'></i></span></div>
		 
    <input type="text" name="address" class="form-control" placeholder="Address" value="<%=DataUtility.getStringData(dto.getAddress())%>">
		
	</div>
	<div align="left"><font style="color: red" ><%=ServletUtility.getErrorMessage("address", request)%></font></div>
	
	</div>
	
	<div class="form-group">
			
    <div align="left" style="color:#8A2BE2; " for="validationDefault02">State<span style="color: red">*</span></div>
	<div class="input-group">
    <div class="input-group-prepend">
    <span class="input-group-text"> <i class='fas fa-fighter-jet'></i></span></div>
		 
    <%-- <input type="text" name="login" class="form-control" placeholder="LoginId" value="<%=DataUtility.getStringData(dto.getLogin())%>">
 --%>	
 <%
 HashMap map = new HashMap();
 map.put("madhya pradesh", "	madhaya predesh");
 map.put("maharastra", "maharastra");
 map.put("jammu kashmir", "Jammu kashmir");
 map.put("gujrat", "gujrat");
 map.put("rajisthan", "rajisthan");
 map.put("tamilnadu", "tamilnadu");
 map.put("keral", "Keral");
 map.put("jharkhand", "jharkhand");
 map.put("west bangal", "west bangal");
 map.put("delhi", "delhi");
 map.put("goa", "goa");
 map.put("andhara predesh", "andhra predesh");
 map.put("karnatka", "karnataka");
 map.put("utrakhand", "utrakhand");
 map.put("panjab", "panjab");
 map.put("himachal predesh", "himachal predesh");
 
 String html=HTMLUtility.getList("state",dto.getState(),map);
	
	%>

	<%=html%><br> 	
	</div>
	<div align="left"><font style="color: red" ><%=ServletUtility.getErrorMessage("state", request)%></font></div>
	
	</div>
	
	<div class="form-group">
			
    <div align="left" style="color:#8A2BE2; " for="validationDefault02">City<span style="color: red">*</span></div>
	<div class="input-group">
    <div class="input-group-prepend">
    <span class="input-group-text"> <i class='fas fa-city'></i></span></div>
		 
    <%-- <input type="text" name="login" class="form-control" placeholder="LoginId" value="<%=DataUtility.getStringData(dto.getLogin())%>">
 --%>	
 <%
 HashMap map1 = new HashMap();
 map1.put("BHOPAL","BHOPAL");
 map1.put("INDORE", "INDORE");
 map1.put("DELHI", "DELHI");
 map1.put("CHENNAI", "CHENNAI");
 map1.put("MUMBAI", "MUMBAI");
 map1.put("NAGPUR", "NAGPUR");
 map1.put("KOLKATA", "KOLKATA");
 map1.put("MOHALI", "MOHALI");
 map1.put("PUNE", "PUNE");
 map1.put("HYDRABAD", "HYDRABAD");
 map1.put("BETUL", "BETUL");
 map1.put("AMRAVATI", "AMRAVATI");
 map1.put("VISAKHAPATANUM", "VISAKHAPATANUM");
 map1.put("GURGAO", "GURGAO");
 map1.put("SHREENAGAR", "SHREENAGAR");
 map1.put("PRAYAGRAJ", "PRAYAGRAJ");
 map1.put("GADHINAGAR", "GANDHINAGAR");
 map1.put("RACHI", "RACHI");
 map1.put("ASAM", "ASAM");

 String html1=HTMLUtility.getList("city",dto.getCity(), map1);
	
	%>

	<%=html1%><br>              
 	
	</div>
	<div align="left"><font style="color: red" ><%=ServletUtility.getErrorMessage("city", request)%></font></div>
	
	</div>
	
	
	
	
	<div class="form-group">

							<div align="left" style="color: #8A2BE2;"
								for="validationDefault02">
								Mobile<span style="color: red">*</span>
							</div>
							<div class="input-group">
								<div class="input-group-prepend">
									<span class="input-group-text"><i
										class='fas fa-mobile-alt'></i></span>
								</div>

								<input type="text" name="mobile" class="form-control"
									placeholder="mobile"
									value="<%=DataUtility.getStringData(dto.getMobile())%>">

							</div>
							<div align="left">
								<font style="color: red"><%=ServletUtility.getErrorMessage("mobile", request)%></font>
							</div>

						</div>
	<%if(dto.getId()>0){ %>
	
	 <button type="submit" name="operation" style="float: left;width: 80px; margin-left: 50px;"  value="<%=CollegeCtl.OP_UPDATE%>"  class="btn btn-primary btn-block button2 msgBtn" >Update </button>
		 
	
	<button type="submit" name="operation" style="width: 80px;margin-left: 150px;" value="<%=CollegeCtl.OP_CANCEL%>"  class="btn btn-primary btn-block button2" >Cancle</button>
	
	
	
	<%} else{%>

		
	 <button type="submit" name="operation" style="float: left;width: 80px; margin-left: 50px;"  value="<%=CollegeCtl.OP_SAVE%>"  class="btn btn-primary btn-block button2 msgBtn" > Submit </button>
		 
	
	<button type="submit" name="operation" style="width: 80px;margin-left: 150px;" value="<%=CollegeCtl.OP_RESET%>"  class="btn btn-primary btn-block button2" >Reset</button>
		 
	<%
	
	
	} 
	
	%>

	
   </article>
</div>

	</aside>
</div> 

</div> 

</form>
</body><br><br><br>
<%@ include file="Footer.jsp"%>
</html>