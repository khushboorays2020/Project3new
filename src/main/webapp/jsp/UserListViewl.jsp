 <%@page import="in.co.sunrays.proj3.dto.RoleDTO"%>
<%@page import="in.co.sunrays.proj3.util.DataUtility"%>
<%@page import="java.text.SimpleDateFormat"%>

 <%@page import="in.co.sunrays.proj3.controller.ORSView"%>
<%@page import="in.co.sunrays.proj3.controller.UserListCtl"%>
<%@page import="in.co.sunrays.proj3.util.ServletUtility"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@page import="in.co.sunrays.proj3.dto.UserDTO"%>


<!DOCTYPE html>
<html lang="en">
<head>
  <title>User list</title>
  <%@ include file="hhh.jsp"%>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">

 
  <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css">
 
   <script src="<%=ORSView.APP_CONTEXT%>/js/jquery.min.js"></script>
<script src="<%=ORSView.APP_CONTEXT%>/js/Checkbox11.js"></script>
  
</head>
<body background="/pro3/image/abc.png">
<form action="<%=ORSView.USER_LIST_CTL%>" method="post">
<%UserDTO userBean1 = (UserDTO) session.getAttribute("user");
int i = DataUtility.getInt(request.getAttribute("next").toString());
%>
<jsp:useBean id="dto" class="in.co.sunrays.proj3.dto.RoleDTO" scope="request"></jsp:useBean>


<div class="container-fluid">

<h1 align="center" style="color:#C71585">User list</h1>
<%List l4=ServletUtility.getList(request); 
  %>     
  
 <%if(ServletUtility.getSuccessMessage(request) !=null && ServletUtility.getSuccessMessage(request).length()>0 ){ %>
  <div class="alert alert-success ">
    <button type="button" class="close" data-dismiss="alert">&times;</button>
    <strong>Success..!</strong><%=ServletUtility.getSuccessMessage(request)%>
  </div>
  <%} %>
  
  <%if(ServletUtility.getErrorMessage(request) !=null && ServletUtility.getErrorMessage(request).length()>0 ){ %>
 
  <div class="alert alert-danger">
    <button type="button" class="close" data-dismiss="alert">&times;</button>
    <strong>Error..!</strong><%=ServletUtility.getErrorMessage(request)%>
  </div>
  <%} %>

<jsp:useBean id="model" class="in.co.sunrays.proj3.model.RoleModelHibImpl" scope="request"></jsp:useBean>
<jsp:useBean id="dto2" class="in.co.sunrays.proj3.dto.RoleDTO" scope="request"></jsp:useBean>

 <div class="row">
 <div class="col-sm-3"></div>
 <div class="col-sm-2">
 <%List l=ServletUtility.getList(request); %>
 
       <i class="fa fa-user"></i> <label >First Name:</label>
      <input type="text" class="form-control" id="email" placeholder="first name" name="firstName">
    </div>
   <div class="col-sm-2">
   
     <i class="fa fa-envelope"></i> <label>LoginId</label>
      <input type="text" class="form-control" id="pwd" placeholder="last name" name="login">
    </div>
    <div class="col-sm-2"> 
    <button type="submit" value="<%=UserListCtl.OP_SAVE%>" class="btn btn-primary" style="margin-top: 25px;"><i class="fa fa-search" aria-hidden="true"></i>&nbsp;Search</button>
 
    <button type="submit" value="<%=UserListCtl.OP_RESET%>" class="btn btn-primary" style="margin-top: 25px;"><i class="fa fa-refresh" aria-hidden="true"></i>&nbsp;Reset</button>
 </div>
 <div class="col-sm-3"></div>
   </div>
   <br>

 <div class="table-responsive">
   <table class="table " border="1px" style="border-color:#C71585; ">
   
    <thead>
      <tr style="background-color:#C71585">  
       <th style="color:white;"><input type="checkbox" id="select_all" name="select" >Select All</th>
                    <th style="color:white;" class="text-center">S.No</th>
                   <th style="color:white;" class="text-center">FirstName</th>
					<th style="color:white;" class="text-center">LastName</th>
					<th style="color:white;" class="text-center">Role</th>
						<th style="color:white;" class="text-center">LoginId</th>
					<th style="color:white;" class="text-center">Gender</th>
					<th style="color:white;" class="text-center">DOB</th>
			  
                    <%if(userBean1.getRoleId()==4 || userBean1.getRoleId()==3||userBean1.getRoleId()==2){ %>
                    
                    <%}else{ %>
                   <th style="color:white;">Edit</th>
                   <%} %>
           
        
      </tr>
    </thead>
    
     <%
					int pageNo = ServletUtility.getPageNo(request);
						int pageSize = ServletUtility.getPageSize(request);
						int index = ((pageNo - 1) * pageSize) + 1;
						UserDTO dto1 = null;
						List list = ServletUtility.getList(request);
						Iterator<UserDTO> it = list.iterator();

						while (it.hasNext()) {
							dto1 = (UserDTO) it.next();

						 	 dto2 = model.findByPK(dto1.getRoleId()); 
						 	dto1.setRoleName(dto2.getName());
		
				
								 			%>
   
    
    <tbody>
      <tr class="info">
         <td><input type="checkbox" class="checkbox" name="ids" value="<%=dto1.getId()%>" <%if (dto1.getRoleId() == 1) {%>
						<%="disabled"%> <%}%> ></td>
                     <td align="center"><%=index++%></td>
                    <td align="center"><%=dto1.getFirstName()%></td>
                    <td align="center"><%=dto1.getLastName()%></td>
                    <td align="center"><%=dto1.getRoleName()%></td>
                      <td align="center"><%=dto1.getLogin()%></td>
                      <td align="center"><%=dto1.getGender()%></td>
                      <%SimpleDateFormat sm=new SimpleDateFormat("dd-MM-yyyy"); %>
                     <td align="center"><%=sm.format(dto1.getDob())%></td>
                     <%if(userBean1.getRoleId()==4 ||userBean1.getRoleId()==3 ||userBean1.getRoleId()==2){ %>
                    
                     <%}else{ %>
                     <td align="center"><a href="UserCtl?id=<%=dto1.getId()%>" <%if (userBean1.getId() == dto1.getId() || dto1.getRoleId() == RoleDTO.ADMIN) {%>
						onclick="return false;" <%}%>><i class="fa fa-edit" aria-hidden="true"></i></a></td>
                     <%} %>
           
         
        </tr>
       </tbody>
       <%
					}
				%>
	 
  </table> </div>
</div>
</center>
<div class="container">
           
  <ul class="pager">
      <center>
     
     <%if(pageNo==1){ %>
     <button type="submit" name="operation" value="<%=UserListCtl.OP_PREVIOUS%>" class="previous btn btn-primary" disabled="disabled"><i  class='fas fa-caret-square-left' style="color:" aria-hidden="true"></i>&nbsp;Previous</button>
     <%}else{ %>
     
  <button type="submit" name="operation" value="<%=UserListCtl.OP_PREVIOUS%>" class="previous btn btn-primary"><i  class='fas fa-caret-square-left' style="color:" aria-hidden="true"></i>&nbsp;Previous</button>
       
     <%} %>
     
     
      <%if(userBean1.getRoleId()==2||userBean1.getRoleId()==4||userBean1.getRoleId()==3){ %> 
                       
      <button type="submit" name="operation" value="<%=UserListCtl.OP_DELETE%>" class="btn btn-danger" disabled="disabled"><i class="fa fa-trash" aria-hidden="true"></i>Delete</button>
<%}else{ %>
<button type="submit" name="operation" value="<%=UserListCtl.OP_DELETE%>" class="btn btn-danger"><i class="fa fa-trash" aria-hidden="true"></i>Delete</button>

<%} %>

<%if(userBean1.getRoleId()==2||userBean1.getRoleId()==4||userBean1.getRoleId()==3){ %> 
      
       <button type="submit" name="operation" value="<%=UserListCtl.OP_NEW%>" class="btn btn-success" disabled="disabled"><i class="fa fa-plus" aria-hidden="true"></i>Add</button>
       <%}else{ %>
        <button type="submit" name="operation" value="<%=UserListCtl.OP_NEW%>" class="btn btn-success"><i class="fa fa-plus" aria-hidden="true"></i>Add</button>
     <%} %>
       
      <%if(i>0){ %>  
       <button type="submit" name="operation" value="<%=UserListCtl.OP_NEXT%>" class="next btn btn-primary" >Next &nbsp;<i  class='fas fa-caret-square-right' style="color:" aria-hidden="true"></i></button></center>
       <%}else{ %>
       
          <button type="submit" name="operation" value="<%=UserListCtl.OP_NEXT%>" class="next btn btn-primary" disabled="disabled">Next &nbsp;<i  class='fas fa-caret-square-right' style="color:" aria-hidden="true"></i></button></center>
   <%} %>
       </center>
  </ul>
</div>


</form>
</body><br><br><br><br><br><br><br><br><br><br><br><br><br>
<%   
int pageNo1 = ServletUtility.getPageNo(request);

if(pageNo1==2){ %>

<br><br><br><br><br><br><br><br>
<%} %>
<%if(l4.size()==1||l4.size()==2||l4.size()==3 || l4.size()==4 || l4.size()==5 || l4.size()==6){ %>

<br><br><br>
<%} %>


 <%@ include file="Footer.jsp"%>
</html>

