<!DOCTYPE html>
<html>
<head>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<meta name='viewport' content='width=device-width, initial-scale=1'>
<script src='https://kit.fontawesome.com/a076d05399.js'></script>


<style>
body {margin:0;font-family:Arial}

.topnav {
  overflow: hidden;
  background-color: #333;
}

.topnav a {
  float: left;
  display: block;
  color: #f2f2f2;
  text-align: center;
  padding: 14px 16px;
  text-decoration: none;
  font-size: 17px;
}

.active {
  background-color: #333;
  color: white;
}

.topnav .icon {
  display: none;
}

.dropdown {
  float: left;
  overflow: hidden;
}

.dropdown .dropbtn {
  font-size: 17px;    
  border: none;
  outline: none;
  color: white;
  padding: 14px 16px;
  background-color: inherit;
  font-family: inherit;
  margin: 0;
}

.dropdown-content {
  display: none;
  position: absolute;
  background-color: #f9f9f9;
  min-width: 160px;
  box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
  z-index: 1;
}

.dropdown-content a {
  float: none;
  color: black;
  padding: 12px 16px;
  text-decoration: none;
  display: block;
  text-align: left;
}

.topnav a:hover, .dropdown:hover .dropbtn {
  background-color: #555;
  color: white;
}

.dropdown-content a:hover {
  background-color: #ddd;
  color: black;
}

.dropdown:hover .dropdown-content {
  display: block;
}

@media screen and (max-width: 600px) {
  .topnav a:not(:first-child), .dropdown .dropbtn {
    display: none;
  }
  .topnav a.icon {
    float: right;
    display: block;
  }
}

@media screen and (max-width: 600px) {
  .topnav.responsive {position: relative;}
  .topnav.responsive .icon {
    position: absolute;
    right: 0;
    top: 0;
  }
  .topnav.responsive a {
    float: none;
    display: block;
    text-align: left;
  }
  .topnav.responsive .dropdown {float: none;}
  .topnav.responsive .dropdown-content {position: relative;}
  .topnav.responsive .dropdown .dropbtn {
    display: block;
    width: 100%;
    text-align: left;
  }
}
</style>
</head>
<body>

<div class="topnav" id="myTopnav">
 <img  class="navbar-brand"  alt="" src="/pro3/image/sunn.jpg" width="200px"
						height="45px" style="background-color: white; position: relative">
				
 <a href="" class="active"><i class="fas fa-home" style="color: white" ></i>Home</a>
  
  <div class="dropdown">
    <button class="dropbtn"> <i class="fas fa-user" style="color: black"></i>User 
      <i class="fa fa-caret-down"></i>
    </button>
    <div class="dropdown-content">
      <a href="#"><i class="fas fa-user"></i>Add User</a>
      <a href="#"><i class="fa fa-users"></i>User list</a>
      
    </div>
  </div> 
  <div class="dropdown">
    <button class="dropbtn"><i class="fa fa-user-circle" style="color: white"></i>Student 
      <i class="fa fa-caret-down"></i>
    </button>
    <div class="dropdown-content">
      <a href="#"><i class="fa fa-user-circle"></i>Add student</a>
      <a href="#"><i class="fa fa-users"></i>Student list</a>
     
    </div>
    </div>  
    <div class="dropdown">
    <button class="dropbtn"> <i class="fa fa-address-book" style="color: white;"></i>Marksheet 
      <i class="fa fa-caret-down"></i>
    </button>
    <div class="dropdown-content">
      <a href="#"><i class="fa fa-address-book"></i>Add marksheet</a>
      <a href="#"><i class="fa fa-server"></i>Marksheet list</a>
     
    </div>
  
  </div> 
   <div class="dropdown">
    <button class="dropbtn"><i class='fas fa-chalkboard-teacher' style="color: white"></i>Faculty 
      <i class="fa fa-caret-down"></i>
    </button>
    <div class="dropdown-content">
      <a href="#"><i class='fas fa-chalkboard-teacher'></i>Add Faculty</a>
      <a href="#"><i class="fa fa-users"></i>Faculty list</a>
      
    </div>
  </div> 
   <div class="dropdown">
    <button class="dropbtn"><i class="fa fa-university" style="color: white;"></i>College 
      <i class="fa fa-caret-down"></i>
    </button>
    <div class="dropdown-content">
      <a href="#"><i class="fa fa-university"></i>Add College</a>
      <a href="#"><i class="fa fa-server"></i>College list</a>
      
    </div>
  </div> 
  <div class="dropdown">
    <button class="dropbtn"> <i  class="fa fa-book" style="color: white;"></i>Course 
      <i class="fa fa-caret-down"></i>
    </button>
    <div class="dropdown-content">
      <a href="#"><i  class="fa fa-book"></i>Add course</a>
      <a href="#"><i class="fa fa-server"></i>User course</a>
      
    </div>
  </div> 
  <div class="dropdown">
    <button class="dropbtn"><i  class="fa fa-copy" style="color: white;"></i>Subject 
      <i class="fa fa-caret-down"></i>
    </button>
    <div class="dropdown-content">
      <a href="#"><i  class="fa fa-copy"></i>Add Subject</a>
      <a href="#"><i  class="fa fa-server"></i>Subject list</a>
      
    </div>
  </div> 
  <div class="dropdown">
    <button class="dropbtn"> <i  class="fa fa-male" style="color: white;"></i> Role 
      <i class="fa fa-caret-down"></i>
    </button>
    <div class="dropdown-content">
      <a href="#"><i  class="fa fa-male"></i>Add role</a>
      <a href="#"><i  class="fa fa-male"></i>Role list</a>
      
    </div>
  </div> 
  <div class="dropdown">
    <button class="dropbtn"><i  class="fa fa-server" style="color: white;"></i>Timetable 
      <i class="fa fa-caret-down"></i>
    </button>
    <div class="dropdown-content">
      <a href="#"><i  class="fa fa-server" ></i>Add timetable</a>
      <a href="#"><i  class="fa fa-server"></i>Timetable list</a>
      
    </div>
  </div> 
  <div class="dropdown">
    <button class="dropbtn"><i class="fas fa-user" style="color: white" ></i>Hi guest
      <i class="fa fa-caret-down"></i>
    </button>
    <div class="dropdown-content">
      <a href="#"><i class='fas fa-caret-square-right' style="color: white" ></i>Login</a>
      <a href="#"><i class="fa fa-users"></i>SignUp</a>
      
    </div>
  </div> 
 
  
 <!--  <a href="#about">SignUp</a>
  
  <a href="#about"><i class='fas fa-caret-square-right' style="color: white" ></i>Login</a>
  --> 
  <a href="javascript:void(0);" style="font-size:15px;" class="icon" onclick="myFunction()">&#9776;</a>
</div>


<script>
function myFunction() {
  var x = document.getElementById("myTopnav");
  if (x.className === "topnav") {
    x.className += " responsive";
  } else {
    x.className = "topnav";
  }
}
</script>

</body>
</html>
