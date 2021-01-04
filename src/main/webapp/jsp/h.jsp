 <!DOCTYPE html>
<%@page import="in.co.sunrays.proj3.controller.ORSView"%>
<%@page import="in.co.sunrays.proj3.dto.UserDTO"%>
<html lang="en">
<head>
  <title>Bootstrap Example</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
   <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
   
   <script src="https://kit.fontawesome.com/a076d05399.js"></script>
   <link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
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
   
</head>
<body>
<div>
<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
  <!-- Brand -->
   <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
    <span class="navbar-toggler-icon"></span>
    </button> 
   
   <img  class="navbar-brand"  alt="" src="/pro3/image/customLogo.jpg" width="200px"
						height="50px">
						
	   				
  
      <%
		UserDTO userBean=new UserDTO();/* = (UserDto) session.getAttribute("user"); */

		boolean userLoggedIn = userBean == null;

		String welcomeMsg = "Hi, ";

		if (userLoggedIn) {
			String role = (String) session.getAttribute("role");
			welcomeMsg += userBean.getFirstName() + " (" + role + ")";
		} else {
			welcomeMsg += "Guest";

		}
	%>
	 <ul class="nav navbar-nav"><li><font style="color: white"><h5>
					<%=welcomeMsg%></h5></font></li></ul>
	
    	&nbsp;   &nbsp;  
   
  <!-- Links -->
  <ul class="navbar-nav">
   <li class="nav-item">
      <a class="nav-link" href="<%=ORSView.WELCOME_CTL%>"><i class="fas fa-home" style="color: white" ></i></a>
    </li>
  
  <%-- <%if(userLoggedIn) {%> --%>
   <!-- Dropdown -->
    <li class="nav-item dropdown">
      <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
       <i class="fas fa-user" style="color: white"></i> User
      </a>
      <div class="dropdown-menu">
        <a class="dropdown-item" href="#"> <i class="fas fa-user"></i>Add user</a>
        <a class="dropdown-item" href="#"><i class="fa fa-users"></i>User list</a>
       
      </div>
      
    </li>
    <li class="nav-item dropdown">
      <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown" >
        <i class="fa fa-user-circle" style="color: white"></i>Student
      </a>
      <div class="dropdown-menu">
        <a class="dropdown-item" href="#"><i class="fa fa-user-circle"></i>Add student</a>
        <a class="dropdown-item" href="#"><i class="fa fa-users"></i>Student list</a>
       
      </div>
      
    </li>
    <li class="nav-item dropdown">
      <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
      <i class="fa fa-address-book" style="color: white;"></i>Marksheet
      </a>
      <div class="dropdown-menu">
        <a class="dropdown-item" href="#"><i class="fa fa-address-book"></i>Add marksheet</a>
        <a class="dropdown-item" href="#"><i class="fa fa-server"></i>Marksheet list</a>
       
      </div>
      
    </li>
 <li class="nav-item dropdown">
      <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
       <i class='fas fa-chalkboard-teacher' style="color: white"></i>Faculty
      </a>
      <div class="dropdown-menu">
        <a class="dropdown-item" href="#"><i class='fas fa-chalkboard-teacher'></i>Add Faculty</a>
        <a class="dropdown-item" href="#"><i class="fa fa-users"></i>Faculty list</a>
       
      </div>
      
    </li>
 <li class="nav-item dropdown">
      <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
     <i class="fa fa-university" style="color: white;"></i>College
      </a>
      <div class="dropdown-menu">
        <a class="dropdown-item" href="#"> <i class="fa fa-university"></i>Add College</a>
        <a class="dropdown-item" href="#"><i class="fa fa-server"></i>College list</a>
       
      </div>
      
    </li>
 <li class="nav-item dropdown">
      <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
        <i  class="fa fa-book" style="color: white;"></i>Course
      </a>
      <div class="dropdown-menu">
        <a class="dropdown-item" href="#"><i  class="fa fa-book"></i>Add course</a>
        <a class="dropdown-item" href="#"><i class="fa fa-server"></i>Course list</a>
       
      </div>
      
    </li>
 <li class="nav-item dropdown">
      <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
        <i  class="fa fa-copy" style="color: white;"></i>Subject
      </a>
      <div class="dropdown-menu">
        <a class="dropdown-item" href="#"><i  class="fa fa-copy"></i>Add Subject</a>
        <a class="dropdown-item" href="#"><i  class="fa fa-server"></i>Subject list</a>
       
      </div>
      
    </li>
 <li class="nav-item dropdown">
      <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
       <i  class="fa fa-male" style="color: white;"></i> Role
      </a>
      <div class="dropdown-menu">
        <a class="dropdown-item" href="#"><i  class="fa fa-male"></i>Add Role</a>
        <a class="dropdown-item" href="#"><i  class="fa fa-male"></i>Role list</a>
       
      </div>
      
    </li>
 <li class="nav-item dropdown">
      <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
        <i  class="fa fa-server" style="color: white;"></i> TimeTable
      </a>
      <div class="dropdown-menu">
        <a class="dropdown-item" href="#"><i  class="fa fa-server" ></i>Add Timetable</a>
        <a class="dropdown-item" href="#"><i  class="fa fa-server"></i>TimeTable list</a>
       
      </div>
      
    </li>
   <%--  <%} %>
 --%>    &nbsp;   &nbsp;  
         
         
  <%if(!userLoggedIn){ %>      
 <li class="nav-item">
      <a class="nav-link" href="<%=ORSView.USER_REGISTRATION_CTL%>" style="color: white">
      <i class='fas fa-chalkboard-teacher'" style="color: white" ></i>SignUp</a>
    </li>
  
 <li class="nav-item">
      <a class="nav-link" href="<%=ORSView.LOGIN_CTL%>" style="color: white">
      <i class='fas fa-caret-square-right' style="color: white" ></i>Login</a>
    </li>
    <%}else{ %>
    <li class="nav-item">
      <a class="nav-link" href="<%=ORSView.LOGIN_CTL%>" style="color: white">
      <i class='fas fa-caret-square-right' style="color: white" ></i>Logout</a>
    </li>
 <%} %>
 
  </ul>
</nav>
</div>
<br>
  
</body>
</html>
