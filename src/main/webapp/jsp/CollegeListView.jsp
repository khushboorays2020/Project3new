 <%@page import="in.co.sunrays.proj3.util.DataUtility"%>
<%@page import="in.co.sunrays.proj3.controller.CollegeListCtl"%>
<%@page import="in.co.sunrays.proj3.controller.StudentListCtl"%>
<%@page import="in.co.sunrays.proj3.controller.FacultyListCtl"%>

 <%@page import="in.co.sunrays.proj3.controller.ORSView"%>
<%@page import="in.co.sunrays.proj3.controller.UserListCtl"%>
<%@page import="in.co.sunrays.proj3.util.ServletUtility"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@page import="in.co.sunrays.proj3.dto.CollegeDTO"%>


<!DOCTYPE html>
<html lang="en">
<head>
<body background="/Project3/img/My-Journal-PPT-Backgrounds.jpg" width="318"
					height="70">
  <title>College list</title>
  <%@ include file="header.jsp"%>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
   <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css">
 
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
  <script src="<%=ORSView.APP_CONTEXT%>/js/jquery.min.js"></script>
<script src="<%=ORSView.APP_CONTEXT%>/js/Checkbox11.js"></script>
  
</head>
<body style="background-color: #E6E6FA;">
<form action="<%=ORSView.COLLEGE_LIST_CTL%>" method="post">
<div class="container-fluid">
<%UserDTO userBean1 = (UserDTO) session.getAttribute("user");
int i = DataUtility.getInt(request.getAttribute("next").toString());

System.out.println(userBean1.getFirstName());
%>
	<%  List l1=(List)request.getAttribute("list"); %>
<h1 align="center" style="color:black">College list</h1>


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
 
 <div class="row">
 <div class="col-sm-3"></div>
 <div class="col-sm-2">
 <%List l=ServletUtility.getList(request); %>
 
 <%if(l.size()!=0){ %>
 
        <i class="fa fa-university"></i><label for="email" style="color:black"">College Name:</label>
      <input type="text" class="form-control" id="email" placeholder="college name" name="name">
    </div>
   <div class="col-sm-2">
   
     <i class="fa fa-city"></i> <label for="pwd" style="color:black">City Name:</label>
      <input type="text" class="form-control" id="pwd" placeholder="city name" name="city">
    </div>
    
    <div class="col-sm-2"> 
    <button type="submit" name="operation" value="<%=StudentListCtl.OP_SEARCH %>" class="btn btn-dark" style="margin-top: 25px;"><i class="fa fa-search" aria-hidden="true"></i>&nbsp;Search</button>
 
    <button type="submit" name="operation" value="<%=StudentListCtl.OP_RESET %>" class="btn btn-dark" style="margin-top: 25px;"><i class="fa fa-refresh" aria-hidden="true"></i>&nbsp;Reset</button>
 </div>
 <div class="col-sm-3"></div>
   </div>
   <br>

 <div class="table-responsive">
   <table class="table table-hover"  " border="1px" style="border-color: black;">
   
    <thead>
    <tr style="background-color: black;">
                    <th style="color:white;"><input type="checkbox" id="select_all" name="select" >Select All</th>
                     <th style="color:white;" class="text-center">S.No</th>
                    <th style="color:white;" class="text-center">Name.</th>
                    <th style="color:white;" class="text-center">Address.</th>
                    <th style="color:white;" class="text-center">State.</th>
                    <th style="color:white;" class="text-center">City.</th>
                    <th style="color:white;" class="text-center">PhoneNo.</th>
                  <%if(userBean1.getRoleId()==4 || userBean1.getRoleId()==3||userBean1.getRoleId()==2){ %>
                    
                    <%}else{ %>
                   <th style="color:white;" class="text-center">Edit</th>
                   <%} %>
            </thead>
    
            <%
                    int pageNo = ServletUtility.getPageNo(request);
                    int pageSize = ServletUtility.getPageSize(request);
                    int index = ((pageNo - 1) * pageSize) + 1;

                    List list = ServletUtility.getList(request);
                    Iterator<CollegeDTO> it = list.iterator();
                    while (it.hasNext()) {
                        CollegeDTO dto1 = it.next();
                %>
    
    <tbody>
     <tr>
                    <td><input type="checkbox" class="checkbox" name="ids" value="<%=dto1.getId()%>"  ></td>
                    <td align="center"><%=index++%></td>
                    <td align="center"><%=dto1.getName()%></td>
                    <td align="center"><%=dto1.getAddress()%></td>
                    <td align="center"><%=dto1.getState()%></td>
                    <td align="center"><%=dto1.getCity()%></td>
                    <td align="center"><%=dto1.getMobile()%></td>
                   
           <%if(userBean1.getRoleId()==4 || userBean1.getRoleId()==3||userBean1.getRoleId()==2){ %>
                    
                     <%}else{ %>
                     <td align="center"><a href="CollegeCtl?id=<%=dto1.getId()%>"><i class="fa fa-edit" aria-hidden="true"></i></a></td>
                     <%} %>
           
         
            </thead>
    
      
                </tr>
                <%
                    }
                %>
         </tbody>
    </table> </div>
</div>
</center>
<div class="container">
           
  <ul class="pager">
     <center>
     
     <%if(pageNo==1){ %>
     <button type="submit" name="operation" value="<%=CollegeListCtl.OP_PREVIOUS%>" class="previous btn btn-primary" disabled="disabled"><i  class='fas fa-caret-square-left' style="color:" aria-hidden="true"></i>&nbsp;Previous</button>
     <%}else{ %>
     
  <button type="submit" name="operation" value="<%=CollegeListCtl.OP_PREVIOUS%>" class="previous btn btn-primary"><i  class='fas fa-caret-square-left' style="color:" aria-hidden="true"></i>&nbsp;Previous</button>
       
     <%} %>
     
     
      <%if(userBean1.getRoleId()==2||userBean1.getRoleId()==4||userBean1.getRoleId()==3){ %> 
                       
      <button type="submit" name="operation" value="<%=CollegeListCtl.OP_DELETE%>" class="btn btn-danger" disabled="disabled"><i class="fa fa-trash" aria-hidden="true"></i>Delete</button>
<%}else{ %>
<button type="submit" name="operation" value="<%=CollegeListCtl.OP_DELETE%>" class="btn btn-danger"><i class="fa fa-trash" aria-hidden="true"></i>Delete</button>

<%} %>

<%if(userBean1.getRoleId()==2||userBean1.getRoleId()==4||userBean1.getRoleId()==3){ %> 
      
       <button type="submit" name="operation" value="<%=CollegeListCtl.OP_NEW%>" class="btn btn-success" disabled="disabled"><i class="fa fa-plus" aria-hidden="true"></i>Add</button>
        <%}else{ %>
        <button type="submit" name="operation" value="<%=CollegeListCtl.OP_NEW%>" class="btn btn-success"><i class="fa fa-plus" aria-hidden="true"></i>Add</button>
     <%} %>
       
      <%if(i>0){ %>  
       <button type="submit" name="operation" value="<%=CollegeListCtl.OP_NEXT%>" class="next btn btn-primary" >Next &nbsp;<i  class='fas fa-caret-square-right' style="color:" aria-hidden="true"></i></button></center>
       <%}else{ %>
       
         <button type="submit" name="operation" value="<%=CollegeListCtl.OP_NEXT%>" class="next btn btn-primary" disabled="disabled">Next &nbsp;<i  class='fas fa-caret-square-right' style="color:" aria-hidden="true"></i></button></center>
   <%} %>
     
       
       <%} %>

  </center>
  </ul>
</div>
<%if(l.size()==0){ %>
<button type="submit" name="operation" value="<%=CollegeListCtl.OP_BACK%>" class="next btn btn-primary" >Back </button></center>
 <%} %>

</form>
</body><br><br>
<%
int pageNo = ServletUtility.getPageNo(request);

if(pageNo==2){ %>

<br><br><br><br><br><br><br><br>
<%} %>

<%if(l4.size()==1||l4.size()==2||l4.size()==3 || l4.size()==4 || l4.size()==5 || l4.size()==6){ %>

<br><br><br><br><br><br><br><br><br><br><br><br><br><br>
<%} %>


 <%@ include file="Footer1.jsp"%>
</html>
