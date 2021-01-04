<%@page import="in.co.sunrays.proj3.util.ServletUtility"%>
<%@page import="in.co.sunrays.proj3.controller.LoginCtl"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ include file="header.jsp"%>
<html>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!------ Include the above in your HEAD tag ---------->

 <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css">

<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

<style type="text/css">
body {
            background: orange;
             background-color:#E6E6FA;
        }
        .card {
            border: 10px solid #aacf;
            background-color:white;
        }
         .card-login {
            margin-top: 130px;
            padding: 18px;
            max-width: 30rem;
        }

        .card-header {
            color: #fff;
            background:#C0C0C0 ;
            font-family: sans-serif;
            font-size: 20px;
            font-weight: 600 !important;
            margin-top: 10px;
            border-bottom: 0;
        }

        .input-group-prepend span{
            width: 50px;
            background-color: blue;
            color: #fff;
            border:0 !important;
        }

        input:focus{
            outline: 0 0 0 0  !important;
            box-shadow: 0 0 0 0 !important;
        }

        .login_btn{
            width: 130px;
        }

        .login_btn:hover{
            color: #fff;
            background-color: #ff0000;
        }

        .btn-outline-danger {
            color: #fff;
            font-size: 18px;
            background-color: #28a745;
            background-image: none;
            border-color: #28a745;
        }

       /*  .form-control {
            display: block;
            width: 100%;
            height: calc(2.25rem + 2px);
            padding: 0.375rem 0.75rem;
            font-size: 1.2rem;
            line-height: 1.6;
            color: #28a745;
            background-color: transparent;
            background-clip: padding-box;
            border: 1px solid #28a745;
            border-radius: 0;
            transition: border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;
        }
 */
       /*  .input-group-text {
            display: -ms-flexbox;
            display: flex;
            -ms-flex-align: center;
            align-items: center;
            padding: 0.375rem 0.75rem;
            margin-bottom: 0;
            font-size: 1.5rem;
            font-weight: 700;
            line-height: 1.6;
            color: #495057;
            text-align: center;
            white-space: nowrap;
            background-color: #e9ecef;
            border: 1px solid #ced4da;
            border-radius: 0;
        } */</style>
</head>
<body >
<form action="<%%>" method="post">
<div class="container">

<jsp:useBean id="dto" class="in.co.sunrays.proj3.dto.UserDTO" scope="request"></jsp:useBean>
    <div class="card card-login mx-auto text-center bg-dark">
        <!-- <div class="card-header mx-auto bg-dark">
            <span> <img src="https://amar.vote/assets/img/amarVotebd.png" class="w-75" alt="Logo"> </span><br/>
                        <h3><span class="logo_title mt-5">SignIn</span></h3>

        </div>
 -->   
                               <h1 align="center" style="color: white">SignIn</h1>
      
        <%if(ServletUtility.getSuccessMessage(request) !=null && ServletUtility.getSuccessMessage(request).length()>0 ){ %>
  <div class="alert alert-success alert-dismissible fade show">
    <button type="button" class="close" data-dismiss="alert">&times;</button>
    <strong>Success..!</strong> Your Session is Experied.. Please Login Again.
  </div>
  <%} %>
  <%if(ServletUtility.getErrorMessage(request) !=null && ServletUtility.getErrorMessage(request).length()>0 ){ %>
 
  <div class="alert alert-danger alert-dismissible fade show">
    <button type="button" class="close" data-dismiss="alert">&times;</button>
    <strong>Error..!</strong> User Id or Password is Invalid .
  </div>
  <%} %>
  
        
       <%--  <div class="alert alert-success">
    <strong><%=ServletUtility.getSuccessMessage(request)%></strong>
  </div>
  --%>
       <%--  <H2>
                <font color="green"><%=ServletUtility.getSuccessMessage(request)%>
                </font>
                 <font color="red"><%=ServletUtility.getErrorMessage(request)%>
                </font>
            </H2>
        --%> 
        <div class="card-body">
            <font style="color: red" ><%=ServletUtility.getErrorMessage("login", request)%></font>
                <div class="input-group form-group">
                
                    <div class="input-group-prepend">
                        <span class="input-group-text" style="background-color: ;"><i class="fas fa-user"></i></span>
                    </div>
                    <input type="text" name="login" class="form-control" placeholder="Emailid">
                </div>
         <font style="color: red"><%=ServletUtility.getErrorMessage("password", request)%></font>
                <div class="input-group form-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text"><i class="fas fa-key"></i></span>
                    </div>
                    <input type="password" name="password" class="form-control" placeholder="Password">
                </div>

                <div class="form-group">
                    <input type="submit" name="operation" value="<%=LoginCtl.OP_SIGN_IN%>" class="btn btn-outline-danger float-right login_btn">
                </div><br/><br/>
<div class="card-footer">
				<div class="d-flex justify-content-center links">
					<span style="color: yellow;">Don't have an account?&nbsp;<a href="<%=ORSView.USER_REGISTRATION_CTL %>" style="color: white"></span>Sign Up</a>
				</div>
				<div class="d-flex justify-content-center">
					<a href="<%=ORSView.FORGET_PASSWORD_CTL%>" style="color: white">Forgot your password?</a>
				</div>
			</div>

           
        </div>
    </div>
</div></form>
<%@ include file="Footer.jsp"%>
</body>
</html>