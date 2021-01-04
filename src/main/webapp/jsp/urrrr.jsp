<%@page import="in.co.sunrays.proj3.controller.UserRegistrationCtl"%>
<%@page import="in.co.sunrays.proj3.util.ServletUtility"%>
<%@page import="in.co.sunrays.proj3.util.HTMLUtility"%>
<%@page import="in.co.sunrays.proj3.util.DataUtility"%>
<%@page import="in.co.sunrays.proj3.controller.LoginCtl"%>

<%@page import="java.util.HashMap"%>
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
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
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
body {
            background:#E6E6FA;
            
        }
        .card {
            border: 10px solid #aacf;
            background-color:;
        }
         .card-login {
            margin-top: 100px;
            padding: 18px;
            max-width: 30rem;
        }

        .card-header {
            color: #fff;
            background: #ff0000;
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
<body background="/pro3/image/abc.png">

<form action="<%=ORSView.USER_REGISTRATION_CTL%>" method="post">


<jsp:useBean id="dto" class="in.co.sunrays.proj3.dto.UserDTO" scope="request"></jsp:useBean>
<%-- <H2>
                <font color="green"><%=ServletUtility.getSuccessMessage(request)%>
                </font>
                 <font color="red"><%=ServletUtility.getErrorMessage(request)%>
                </font>
            </H2>
        
 --%>        

<div class="container">
    <div class="card card-login mx-auto text-center bg-dark" style="margin-top: 60px">
      
          <!--   <span> <img src="https://amar.vote/assets/img/amarVotebd.png" class="w-75" alt="Logo"> </span><br/> -->
                        <h1 align="center" style="color: white">SignUp</h1>

      <%if(ServletUtility.getSuccessMessage(request) !=null && ServletUtility.getSuccessMessage(request).length()>0 ){ %>
  <div class="alert alert-success alert-dismissible fade show">
    <button type="button" class="close" data-dismiss="alert">&times;</button>
    <strong>Success..!</strong> User added succefully
  </div>
  <%} %>
  <%if(ServletUtility.getErrorMessage(request) !=null && ServletUtility.getErrorMessage(request).length()>0 ){ %>
 
  <div class="alert alert-danger alert-dismissible fade show">
    <button type="button" class="close" data-dismiss="alert">&times;</button>
    <strong>Error..!</strong> Confirmpassword is not matching .
  </div>
  <%} %>
 
        
         <input type="hidden" name="id" value="<%=dto.getId()%>">
            <input type="hidden" name="createdBy" value="<%=dto.getCreatedBy()%>">
            <input type="hidden" name="modifiedBy" value="<%=dto.getModifiedBy()%>"> 
            <input type="hidden" name="createdDatetime" value="<%=DataUtility.getTimestamp(dto.getCreatedDatetime())%>">
            <input type="hidden" name="modifiedDatetime" value="<%=DataUtility.getTimestamp(dto.getModifiedDatetime())%>">
            
        <div class="card-body">
          
           <font style="color: red" ><%=ServletUtility.getErrorMessage("firstname", request)%></font>
                 <div class="input-group form-group">
                 
                    <div class="input-group-prepend">
                        <span class="input-group-text"><i class="fas fa-user"></i></span>
                        
                    </div>
                    <input type="text" name="firstname" class="form-control" placeholder="firstname" value=<%=DataUtility.getStringData(dto.getFirstName())%>>
                  
                </div>
                
                
                <font style="color: red" ><%=ServletUtility.getErrorMessage("lastname", request)%></font>
                 <div class="input-group form-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text"><i class="fas fa-user"></i></span>
                    </div>
                    <input type="text" name="lastname" class="form-control" placeholder="lastname" value=<%=DataUtility.getStringData(dto.getLastName())%>>
                </div>
                 <font style="color: red" ><%=ServletUtility.getErrorMessage("login", request)%></font>
                 <div class="input-group form-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text"><i class='fas fa-mail-bulk'></i></span>
                    </div>
                    <input type="text" name="login" class="form-control" placeholder="login" value=<%=DataUtility.getStringData(dto.getLogin())%>>
                </div>
                 <font style="color: red" ><%=ServletUtility.getErrorMessage("password", request)%></font>
                 <div class="input-group form-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text"><i class="fas fa-key"></i></span>
                    </div>
                    <input type="password" name="password" class="form-control" placeholder="password" value=<%=DataUtility.getStringData(dto.getPassword())%>>
                </div>
                 <font style="color: red" ><%=ServletUtility.getErrorMessage("confirmpassword1", request)%></font>
                <div class="input-group form-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text"><i class="fas fa-key"></i></span>
                    </div>
                    <input type="password" name="confirmpassword" class="form-control" placeholder="confirmpassword" value=<%=DataUtility.getStringData(dto.getConfirmPassword())%>>
                </div>
                 <font style="color: red" ><%=ServletUtility.getErrorMessage("gender", request)%></font>
                <div class="input-group form-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text"><i class='fas fa-venus-double'></i></span>
                    </div>
                    <!-- <input type="text" name="email" class="form-control" placeholder="gender"> -->
                     <%
						HashMap map=new HashMap();
						map.put("MALE", "MALE");
						map.put("FEMALE","FEMALE");
						String html=HTMLUtility.getList("gender",dto.getGender(), map);
						
						%>
				
						<%=html%><br>
						
                </div>
               
                 <font style="color: red"><%=ServletUtility.getErrorMessage("dob", request)%></font>
                <div class="input-group form-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text"><i class='fas fa-calendar-alt'></i></span>
                    </div>
                    <input type="text" name="dob" class="form-control" id="date" placeholder="date of birth" readonly="readonly" value=<%=DataUtility.getDateString(dto.getDob())%> >
                </div>
                 <font style="color: red" ><%=ServletUtility.getErrorMessage("mobile", request)%></font>
                 <div class="input-group form-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text"><i class='fas fa-mobile-alt'></i></i></span>
                    </div>
                <input type="text" name="mobile" class="form-control" placeholder="mobile" value=<%=DataUtility.getStringData(dto.getMobileNo())%>>
                </div>
                


                <div class="form-group">
                    <input type="submit" name="operation" value="<%=UserRegistrationCtl.OP_RESET%>" class="btn btn-outline-danger float-right login_btn">
                </div>
                <div class="form-group">
                    <input style="margin-right: 85px" type="submit" name="operation" value="<%=UserRegistrationCtl.OP_SIGN_UP%>" class="btn btn-outline-danger float-right login_btn">
                </div>
			</div>

        
        </div>
    </div>
</div>
</form>
<%@ include file="Footer.jsp"%>
</body>
</html>