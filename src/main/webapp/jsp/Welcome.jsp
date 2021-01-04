<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="in.co.sunrays.proj3.controller.ORSView"%>
<%@page import="in.co.sunrays.proj3.dto.UserDTO" %>
<%@page import="in.co.sunrays.proj3.dto.RoleDTO" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!--  <link rel="icon" href="img/customLogo1.png" type="image/png" sizes="16x16"> -->
<title>Welcome Page</title>
<style type="text/css">
 input[type=button] {
	 background-color: #1E90FF; /* Green */
    border: none;
    width:500px;
    font-size:25px;
    font-style:italic;
    color: white;
    padding: 15px 60px;
    text-align: center;
    margin: 4px 2px;
    cursor: pointer;
    transition-duration: 0.4s;
   box-shadow: 0 8px 26px 0 rgba(0,0,0,0.2), 0 6px 30px 0 rgba(0,0,0,0.19); 
}
</style>


</head>
<body background="/Project3/img/My-Journal-PPT-Backgrounds.jpg" 
					height="70"><div class="container-fluid">
<div class="row">
<div class="col-sm-12">
<div style="width:100%;">
        <%@ include file="header.jsp"%> 
     </div>
     </div>
     </div>
     <div class="row">
	<div class="col-sm-12">
     <!-- <div style="margin-bottom: 100px;"> -->
     <form action="<%=ORSView.WELCOME_CTL%>">
	<div id="demo" class="carousel slide" data-ride="carousel">

  <!-- Indicators -->
  <ul class="carousel-indicators">
    <li data-target="#demo" data-slide-to="0" class="active"></li>
    <li data-target="#demo" data-slide-to="1"></li>
    <li data-target="#demo" data-slide-to="2"></li>
  </ul>
  
  <!-- The slideshow -->
    <%if(userLoggedIn){ %> 
 
  <%}else{ %>
  
  <div class="carousel-inner" align="center">
    <div class="carousel-item active">
            <img src="/Project3/img/orsproj3.jpg" align="center" width="100px" height="300px" class="d-block w-50">
  </div>
    <div class="carousel-item ">
      <img src="img/exam2.jpg" align="center"  width="100px" height="300px" class="d-block w-50">
    </div>
    <div class="carousel-item">
   <img src="img/exam3.jpg" align="center" width="100px" height="300px" class="d-block w-50">
   
    </div>
    <div class="carousel-item">
   <img src="img/ks1.jpg" align="center" width="100px" height="300px" class="d-block w-50">
   
    </div>
  </div>
  <%} %>
  
  <!-- Left and right controls -->
  <a class="carousel-control-prev" href="#demo" data-slide="prev">
    <span class="carousel-control-prev-icon"></span>
  </a>
  <a class="carousel-control-next" href="#demo" data-slide="next">
    <span class="carousel-control-next-icon"></span>
  </a>
</div>
</form>
                   <font style="color:#FF0000; text-transform: 34px"><h1 align="center" style="margin-top:100px">WELCOME TO ONLINE RESULT SYSTEM</h1></font>
 <br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
        
                    <%
                    UserDTO beanUserDTO = (UserDTO) session.getAttribute("user");
                        if (beanUserDTO != null) {
                            if (beanUserDTO.getRoleId() == RoleDTO.STUDENT) {
                    %>
        
                    <h2 align="Center">
                        <a href="<%=ORSView.GET_MARKSHEET_CTL%>">Click here to see your
                            Marksheet </a>
                    </h2>
                     
                     <%
                            }
                        }
                     %>
                </div>
                </form>
                </div>
        </div>
        <div class="row">
		<div class="col-sm-20">
        <div style="width:100%; ">
        <%@ include file="Footer1.jsp"%> 
		</div>
		</div>
		</div></div>
</div>
</body>
></html>