<%@page import="in.co.sunrays.proj3.controller.LoginCtl"%>
<%@page import="in.co.sunrays.proj3.controller.ORSView"%>
<%@page import="in.co.sunrays.proj3.dto.UserDTO"%>

  <html lang="en">
<head>
  <title></title>
 <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">

 
  <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
 
 
 <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

 <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
  <link href="https://fonts.googleapis.com/css?family=Raleway:400,500,500i,700,800i" rel="stylesheet">
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
 <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
  
<script type="text/javascript">
$(document).ready(function () {
$('.navbar-light .dmenu').hover(function () {
        $(this).find('.sm-menu').first().stop(true, true).slideDown(150);
    }, function () {
        $(this).find('.sm-menu').first().stop(true, true).slideUp(105)
    });
});
</script>
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


 
 
  <style type="text/css">.social-part .fa{
    padding-right:30px;
}
ul li a{
    margin-right: 15px;
}
  </style>
</head>
  <body>

   <nav class="navbar  fixed-top navbar-expand-sm   navbar-light bg-light">
    <%
		UserDTO userBean=(UserDTO)session.getAttribute("user");
  

		boolean userLoggedIn = userBean !=null;

		String welcomeMsg = " Hi, ";

		if (userLoggedIn) {
			String role = (String) session.getAttribute("role");
		 welcomeMsg += userBean.getFirstName() + " (" + role + ")"; 
		} else {
			welcomeMsg += "Guest";

		}
	%>
    
   
 <img align="right" alt="" src="/pro3/image/sunn.jpg" width="120px" height="40px">
    
<button  <%if(userLoggedIn){ %>style="margin-left:270px; margin-top: -38px;"<%}else{%>style="margin-right:0px"<%} %> class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo03" aria-controls="navbarTogglerDemo03" aria-expanded="false" aria-label="Toggle navigation">
 <span class="navbar-toggler-icon"></span>
</button>
         
    	 
     
       <div class="collapse navbar-collapse" id="navbarTogglerDemo03">
         
         &nbsp;<ul class="navbar-nav ml-auto mt-2 mt-lg-0">
         
            <li class="nav-item ">
             <a class="nav-link" href="<%=ORSView.WELCOME_CTL%>"><i class="fa fa-home fa-2x " aria-hidden="true" ></i></a>
            </li>
            
           <%if(userLoggedIn){ %> 
            
            <li class="nav-item dropdown dmenu">
            <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
            <i class="fas fa-user" style="color: black"></i>User
            </a>
            <div class="dropdown-menu sm-menu">
              <a class="dropdown-item" href="<%=ORSView.USER_CTL%>"><i class="fas fa-user"></i>Add user</a>
              <a class="dropdown-item" href="<%=ORSView.USER_LIST_CTL%>"><i class="fa fa-users"></i>User list</a>
             
            </div>
          </li>
          
          <li class="nav-item dropdown dmenu">
            <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
              <i class="fa fa-user-circle" style="color:black"></i>Student
            </a>
            <div class="dropdown-menu sm-menu">
              <a class="dropdown-item" href="<%=ORSView.STUDENT_CTL%>"><i class="fa fa-user-circle"></i>Add student</a>
              <a class="dropdown-item" href="<%=ORSView.STUDENT_LIST_CTL%>"><i class="fa fa-users"></i>Student list</a>
              
            </div>
          </li><li class="nav-item dropdown dmenu">
            <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
              <i class="fa fa-address-book" style="color:black;"></i>Marksheet
            </a>
            <div class="dropdown-menu sm-menu">
              <a class="dropdown-item" href="<%=ORSView.MARKSHEET_CTL%>"><i class="fa fa-address-book"></i>Add marksheet</a>
              <a class="dropdown-item" href="<%=ORSView.MARKSHEET_MERIT_LIST_CTL%>"><i class="fa fa-server"></i>Marksheet merit list</a>
              <a class="dropdown-item" href="<%=ORSView.MARKSHEET_LIST_CTL%>"><i class="fa fa-server"></i>Marksheetlist</a>
              
              <a class="dropdown-item" href="<%=ORSView.GET_MARKSHEET_CTL%>">Get Marksheet</a>
            </div>
          </li><li class="nav-item dropdown dmenu">
            <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
              <i class='fas fa-chalkboard-teacher' style="color:black"></i>Faculty
            </a>
            <div class="dropdown-menu sm-menu">
              <a class="dropdown-item" href="<%=ORSView.FACULTY_CTL%>"><i class='fas fa-chalkboard-teacher'></i>Add faculty</a>
              <a class="dropdown-item" href="<%=ORSView.FACULTY_LIST_CTL%>"><i class="fa fa-users"></i>Faculty list</a>
          
            </div>
          </li>
          <li class="nav-item dropdown dmenu">
            <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
              
              <i class="fa fa-university" style="color:black;"></i>College
            </a>
            <div class="dropdown-menu sm-menu">
              <a class="dropdown-item" href="<%=ORSView.COLLEGE_CTL%>"><i class="fa fa-university"></i>Add college</a>
              <a class="dropdown-item" href="<%=ORSView.COLLEGE_LIST_CTL%>"><i class="fa fa-server"></i>College List</a>
            
            </div>
          </li>
          
          <li
           class="nav-item dropdown dmenu">
            <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
             <i  class="fa fa-book" style="color:black;"></i> Course
            </a>
            <div class="dropdown-menu sm-menu">
              <a class="dropdown-item" href="<%=ORSView.COURSE_CTL%>"><i  class="fa fa-book"></i>Add course</a>
              <a class="dropdown-item" href="<%=ORSView.COURSE_LIST_CTL%>"><i class="fa fa-server"></i>Course list</a>
             
            </div>
          </li>
          
          <li class="nav-item dropdown dmenu">
            <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
             <i  class="fa fa-copy" style="color:black;"></i> Subject
            </a>
            <div class="dropdown-menu sm-menu">
              <a class="dropdown-item" href="<%=ORSView.SUBJECT_CTL%>"><i  class="fa fa-copy"></i>Add subject</a>
              <a class="dropdown-item" href="<%=ORSView.SUBJECT_LIST_CTL%>"><i  class="fa fa-server"></i>Subject list</a>
             
            </div>
          </li><li class="nav-item dropdown dmenu">
            <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
             <i  class="fa fa-male" style="color:black;"></i> Role
            </a>
            <div class="dropdown-menu sm-menu">
              <a class="dropdown-item" href="<%=ORSView.ROLE_CTL%>"><i  class="fa fa-male"></i>Add role</a>
              <a class="dropdown-item" href="<%=ORSView.ROLE_LIST_CTL%>"><i  class="fa fa-male"></i>Role List</a>
          
            </div>
          </li><li class="nav-item dropdown dmenu">
            <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
             <i  class="fa fa-server" style="color:black;"></i> Timetable
            </a>
            <div class="dropdown-menu sm-menu">
              <a class="dropdown-item" href="<%=ORSView.TIMETABLE_CTL%>"><i  class="fa fa-server" ></i>Add timetable</a>
              <a class="dropdown-item" href="<%=ORSView.TIMETABLE_LIST_CTL%>"><i  class="fa fa-server"></i>Timetable list</a>
             
            </div>
          </li>
          <%} %>
          
          
           <li class="nav-item dropdown dmenu">
           <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
           <i class="fa fa-user-circle" style="color:orange"></i><span style="color: blue;"><%=welcomeMsg%>
           </span> </a>
           <div class="dropdown-menu sm-menu">
            
             <%if(userLoggedIn){ %>
             <a class="dropdown-item" href="<%=ORSView.MY_PROFILE_CTL%>"><i class='fas fa-caret-square-right' style="color:" ></i>My profile</a>
             <a class="dropdown-item" href="<%=ORSView.CHANGE_PASSWORD_CTL%>"><i class='fas fa-caret-square-right' style="color:" ></i>Change password</a> 
              <a class="dropdown-item" href="<%=ORSView.JAVA_DOC_VIEW%>" target="blank"><i class='fas fa-caret-square-right' style="color:" ></i>Java doc</a>
            
             <a class="dropdown-item" href="<%=ORSView.LOGIN_CTL%>?operation=<%=LoginCtl.OP_LOG_OUT%>"><i class='fas fa-caret-square-right' style="color:" ></i>Logout</a>
             
            
              <%}else{ %>
             <a class="dropdown-item" href="<%=ORSView.USER_REGISTRATION_CTL%>"> <i class='fas fa-chalkboard-teacher'" style="color:" ></i>SighUp</a>
              <a class="dropdown-item" href="<%=ORSView.LOGIN_CTL%>"><i class='fas fa-caret-square-right' style="color:" ></i>SignIn</a>
              
             <%} %>
            </div>
          </li>
    
          </ul>
            </div>
            
      </nav>
</body>
</html>
