<%@page import="in.co.sunrays.proj3.dto.RoleDTO"%>
<%@page import="in.co.sunrays.proj3.controller.LoginCtl"%>
<%@page import="in.co.sunrays.proj3.dto.UserDTO"%>
<%@page errorPage="ErrorView.jsp" %>
<%@page import="in.co.sunrays.proj3.controller.ORSView"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- bootstrap -->
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="<%=ORSView.APP_CONTEXT%>/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<link rel="stylesheet"
	 href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<!-- jQuery library -->
<script src="<%=ORSView.APP_CONTEXT%>/js/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="<%=ORSView.APP_CONTEXT%>/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="<%=ORSView.APP_CONTEXT%>/css/navbar-fixed-top.css">

<!-- bootstrap end -->
<link rel="stylesheet"
	href="/ORS_Project3/css/jquery-ui.css">
<script src="/ORS_Project3/js/jquery.min.js"></script>
<script src="/ORS_Project3/js/jquery-1.12.4.js"></script>
<script src="/ORS_Project3/js/jquery-ui.js"></script>

<style type="text/css">
#dcolor {
	color: white;
}

/* .btn-primary {
	color: #fff;
	background-color: #7986CB;
	border-color:none;
} */

.text-primary {
	color: blue;
	/* background-color: blue; */

}

.navbar-inverse {
	 background-color:	#ad804d;  
	//border-color: black;
	color: white;
	/*  background-image: url("img/images (2)navar00.jpg");  */
	//background : url("/ORS_Proj3//img/ppppppppppppp.jpg"); 
	background-repeat: no-repeat;
	

}

element.style {
	color: #000;
}

body {
	padding-top: 130px;
}

#top {
	margin-bottom: 50px;
}

 .panel-heading{
 
background : url("/ORS_Proj3/img/images (2)navar00.jpg") no-repeat;
background-position: center;
background-size: cover;
-webkit-background-size: cover;
-moz-background-size: cover;
-o-background-size: cover;

} 

.list-heading{
//background: url("/ORS_Proj3/img/images (2)navar00.jpg") no-repeat;
}

body {
    background: url('<%=ORSView.APP_CONTEXT%>/img/backgroundNew12.jpg') no-repeat center center fixed;
    -webkit-background-size: cover;
    -moz-background-size: cover;
    -o-background-size: cover;
    background-size: cover;
   // color:#fff;
    //background-color:#333;
    font-family: 'Open Sans', Arial, Helvetica, Sans-Serif;
}



</style>

<script type="text/javascript">
	function selectAll(source) {
		checkboxes = document.getElementsByName('ids');

		for (var i = 0, n = checkboxes.length; i < n; i++) {
			checkboxes[i].checked = source.checked;
		}
	}

	$(document).ready(function() {
		$('[name="ids"]').click(function() {
			if (!($(this)[0].checked)) {
				$('[onclick="selectAll(this)"]')[0].checked = false;
			}
			;

		});

	});
</script>


<script type="text/javascript">
	$(function() {
		$(".dropdown").hover(function() {
			$('.dropdown-menu', this).stop(true, true).fadeIn("fast");
			$(this).toggleClass('open');
		}, function() {
			$('.dropdown-menu', this).stop(true, true).fadeOut("fast");
			$(this).toggleClass('open');
		});
	});
</script>

 <script type="text/javascript">
 $(function() {
		$("#datepicker").datepicker({
			changeMonth: true,
		    changeYear:true,
		    yearRange :"-60:-18",
		    maxDate:'-18Y'
		    
			
		});
	});

	function DisableSunday(date) {
		var day = date.getDay();
		if (day == 0) {
			return [ false ];

		} else {
			return [ true ];
		}
	}


	$(function() {
		$("#datepicker1").datepicker({
			changeMonth : true,
			changeYear : true,
			beforeShowDay : DisableSunday,
			yearRange : "0:+10",//"2019:2029",
			minDate : 0,

		});
	});
</script> 
</head>
<html>
<body>
	
	<nav class="navbar navbar-inverse navbar-fixed-top" width="150"
		height="90">
		<div class="container-fluid">
			<ul class="nav navbar-nav navbar-left">
				<a href="<%=ORSView.WELCOME_CTL%>"> <img
					src="<%=ORSView.APP_CONTEXT%>/img/customLogo.png"
					class="img-responsive" width="150" border="0"></a>
			</ul>

			<div class="navbar-header">
				<button type="button" class="navbar-toggle"
					data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
					aria-expanded="false">
					<span class="sr-only"></span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="<%=ORSView.WELCOME_CTL%>"
					style="color: white;">&emsp;&emsp;
					 <i class="fa fa-home " aria-hidden="true"style="font-size:24px"></i>
				</a>


			</div>
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">

				<ul class="nav navbar-nav navbar-right" style="background:">

					<%
						UserDTO userdto = (UserDTO) session.getAttribute("user");
						if (userdto == null) {
					%>
					<li><a href="<%=ORSView.LOGIN_CTL%>" id="dcolor"
						onclick="document.getElementById('id01').style.display='block'"
						style="width: auto;"><span class="glyphicon glyphicon-log-in"></span>
							Sign In</a></li>
					<li><a href="<%=ORSView.USER_REGISTRATION_CTL%>" id="dcolor"><span
							class="glyphicon glyphicon-user"></span> Sign Up</a></li>

					<%
						} else {
					%>

					<li class="dropdown pull-right"><a href="#" id="dcolor"
						class="dropdown-toggle" " data-toggle="dropdown role=" button"
						aria-haspopup="true" aria-expanded="false"><span
							class="glyphicon glyphicon-user" aria-hidden="true"></span><b>
								<%
									if (userdto != null) {
								%> Hello : <%=userdto.getFirstName()%>(<%=session.getAttribute("role")%>)<span
								class="caret"></span> <%
 	}
 %>
	 					</b></a>
						<ul class="dropdown-menu" style="background-color: #ccbeb2">
							<li><a href="<%=ORSView.MY_PROFILE_CTL%>"><span
									class="glyphicon glyphicon-edit" aria-hidden="true"></span>
									Edit My Profile</a></li>
							<li><a href="<%=ORSView.CHANGE_PASSWORD_CTL%>">
							<i class="fa fa-cog fa-spin" ></i>
									Change Password</a></li>
							<li><a href="<%=ORSView.JAVA_DOC_VIEW%> " target="blank"><span
									class="glyphicon glyphicon-file" aria-hidden="true"></span>
									Java Doc</a></li>
							<li><a href="<%=ORSView.LOGIN_CTL%>?operation=logout"" ><span
									class="glyphicon glyphicon-log-out" aria-hidden="true"></span>
									Logout</a></li>
							<%
								}
							%>
						</ul></li>
				</ul>
				<%
					UserDTO user1 = (UserDTO) session.getAttribute("user");
					if (user1 != null) {
						if (userdto.getRoleId() == 1) {
				%>

				<ul class="nav navbar-nav ">
					<li></li>


					<li class="dropdown"><a class="dropdown-toggle" id="dcolor"
						data-toggle="dropdown" href="#">User<span class="caret"></span></a>
				<ul class="dropdown-menu" style="background-color: #ccbeb2">
							<li><a href="<%=ORSView.USER_CTL%>"><i 
							class="fa fa-user-plus"></i> Add User</a></li>
							<li><a href="<%=ORSView.USER_LIST_CTL%>"><span
									class="glyphicon glyphicon-list-alt"></span> User List</a></li>
						</ul>
					<li class="dropdown"><a class="dropdown-toggle" id="dcolor"
						data-toggle="dropdown" href="#">Role<span class="caret"></span></a>
						<ul class="dropdown-menu" style="background-color: #ccbeb2">
							<li><a href="<%=ORSView.ROLE_CTL%>"><span
									class="glyphicon glyphicon-education"></span> Add Role</a></li>
							<li><a href="<%=ORSView.ROLE_LIST_CTL%>"><span
									class="glyphicon glyphicon-list-alt"></span> Role List</a></li>
						</ul>
					<li class="dropdown"><a class="dropdown-toggle" id="dcolor"
						data-toggle="dropdown" href="#">Student<span class="caret"></span></a>
						<ul class="dropdown-menu" style="background-color: #ccbeb2">
							<li><a href="<%=ORSView.STUDENT_CTL%>"><span
									class="glyphicon glyphicon-education"></span> Add Student</a></li>
							<li><a href="<%=ORSView.STUDENT_LIST_CTL%>"><span
									class="glyphicon glyphicon-list-alt"></span> Student List</a></li>
						</ul>
					<li class="dropdown"><a class="dropdown-toggle" id="dcolor"
						data-toggle="dropdown" href="#">College<span class="caret"></span></a>
						<ul class="dropdown-menu" style="background-color: #ccbeb2">
							<li><a href="<%=ORSView.COLLEGE_CTL%>">
									<i class="fa fa-university"></i> Add College</a></li>
							<li><a href="<%=ORSView.COLLEGE_LIST_CTL%>"><span
									class="glyphicon glyphicon-list-alt"></span> College List</a></li>
						</ul>
					<li class="dropdown"><a class="dropdown-toggle" id="dcolor"
						data-toggle="dropdown" href="#">Marksheet <span class="caret"></span></a>
						<ul class="dropdown-menu" style="background-color: #ccbeb2">
							<li><a href="<%=ORSView.GET_MARKSHEET_CTL%>"><span
									class="glyphicon glyphicon-education"></span> Get Marksheet</a></li>
							<li><a href="<%=ORSView.MARKSHEET_CTL%>"><span
									class="glyphicon glyphicon-list-alt"></span> Add Marksheet</a></li>
							<li><a href="<%=ORSView.MARKSHEET_LIST_CTL%>"><span
									class="glyphicon glyphicon-list-alt"></span> Marksheet List</a></li>
							<li><a href="<%=ORSView.MARKSHEET_MERIT_LIST_CTL%>"><span
									class="glyphicon glyphicon-list-alt"></span> Marksheet Merit
									List</a></li>


						</ul></li>
					<li class="dropdown"><a class="dropdown-toggle" id="dcolor"
						data-toggle="dropdown" href="#">Faculty<span class="caret"></span></a>
						<ul class="dropdown-menu" style="background-color: #ccbeb2">
							<li><a href=<%=ORSView.FACULTY_CTL%>><span
									class="glyphicon glyphicon-education"></span> Add Faculty</a></li>
							<li><a href="<%=ORSView.FACULTY_LIST_CTL%>"><span
									class="glyphicon glyphicon-list-alt"></span> Faculty List</a></li>
						</ul>
					<li class="dropdown"><a class="dropdown-toggle" id="dcolor"
						data-toggle="dropdown" href="#">Course<span class="caret"></span></a>
						<ul class="dropdown-menu" style="background-color: #ccbeb2">
							<li><a href="<%=ORSView.COURSE_CTL%>"><span
									class="glyphicon glyphicon-education"></span> Add Course</a></li>
							<li><a href="<%=ORSView.COURSE_LIST_CTL%>"><span
									class="glyphicon glyphicon-list-alt"></span> Course List</a></li>
						</ul>
					<li class="dropdown"><a class="dropdown-toggle" id="dcolor"
						data-toggle="dropdown" href="#">Subject<span class="caret"></span></a>
						<ul class="dropdown-menu" style="background-color: #ccbeb2">
							<li><a href="<%=ORSView.SUBJECT_CTL%>"><span
									class="glyphicon glyphicon-education"></span> Add Subject</a></li>
							<li><a href="<%=ORSView.SUBJECT_LIST_CTL%>"><span
									class="glyphicon glyphicon-list-alt"></span> Subject List</a></li>
						</ul>
					<li class="dropdown"><a class="dropdown-toggle" id="dcolor"
						data-toggle="dropdown" href="#">TimeTable<span class="caret"></span></a>
						<ul class="dropdown-menu" style="background-color: #ccbeb2">
							<li><a href="<%=ORSView.TIMETABLE_CTL%>"><span
									class="glyphicon glyphicon-education"></span> Add TimeTable</a></li>
							<li><a href="<%=ORSView.TIMETABLE_LIST_CTL%>"><span
									class="glyphicon glyphicon-list-alt"></span> TimeTable List</a></li>
						</ul> <%
 	}
 		if (userdto.getRoleId() == 2) {
 %><ul class="nav navbar-nav ">
							<li class="dropdown">
							<li class="dropdown"><a class="dropdown-toggle"
								data-toggle="dropdown" href="#">Course<span class="caret"></span></a>
								<ul class="dropdown-menu" style="background-color: #FFFFCC">
									<li><a href="<%=ORSView.COURSE_LIST_CTL%>">Course List</a></li>
								</ul>
							<li class="dropdown"><a class="dropdown-toggle" id="dcolor"
								data-toggle="dropdown" href="#">Subject<span class="caret"></span></a>
								<ul class="dropdown-menu" style="background-color: #FFFFCC">
									<li><a href="<%=ORSView.SUBJECT_LIST_CTL%>">Subject
											List</a></li>
								</ul>
							<li class="dropdown"><a class="dropdown-toggle" id="dcolor"
								data-toggle="dropdown" href="#">TimeTable<span class="caret"></span></a>
								<ul class="dropdown-menu" style="background-color: #FFFFCC">
									<li><a href="<%=ORSView.TIMETABLE_LIST_CTL%>">TimeTable
											List</a></li>
								</ul> <%
 	}
 		if (userdto.getRoleId() == 5) {
 %>
								<ul class="nav navbar-nav ">

									<li class="dropdown"><a class="dropdown-toggle"
										data-toggle="dropdown" href="#">Marksheet <span
											class="caret"></span></a>
										<ul class="dropdown-menu" style="background-color: #FFFFCC">
											<li><a href="<%=ORSView.MARKSHEET_MERIT_LIST_CTL%>">Marksheet
													Merit List</a></li>
											<li><a href="<%=ORSView.GET_MARKSHEET_CTL%>">Get
													Marksheet</a></li>
											<li><a href="<%=ORSView.MARKSHEET_LIST_CTL%>">Marksheet
													List</a></li>
										</ul></li>

									<li class="dropdown"><a class="dropdown-toggle"
										id="dcolor" data-toggle="dropdown" href="#">Course<span
											class="caret"></span></a>
										<ul class="dropdown-menu" style="background-color: #FFFFCC">
											<li><a href="<%=ORSView.COURSE_LIST_CTL%>">Course
													List</a></li>
										</ul>
									<li class="dropdown"><a class="dropdown-toggle"
										id="dcolor" data-toggle="dropdown" href="#">Subject<span
											class="caret"></span></a>
										<ul class="dropdown-menu" style="background-color: #FFFFCC">
											<li><a href="<%=ORSView.SUBJECT_LIST_CTL%>">Subject
													List</a></li>
										</ul>
										<%}} %>
			</div>
		</div>
	</nav>
</body>
</html>