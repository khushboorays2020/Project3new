 <%@page import="in.co.sunrays.proj3.controller.TimeTableCtl"%>
<%@page import="in.co.sunrays.proj3.util.DataUtility"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="in.co.sunrays.proj3.controller.TimeTableListCtl"%>
<%@page import="in.co.sunrays.proj3.controller.FacultyListCtl"%>

 <%@page import="in.co.sunrays.proj3.controller.ORSView"%>
<%@page import="in.co.sunrays.proj3.controller.UserListCtl"%>
<%@page import="in.co.sunrays.proj3.util.ServletUtility"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@page import="in.co.sunrays.proj3.util.HTMLUtility"%>

<%@page import="in.co.sunrays.proj3.dto.TimeTableDTO"%>


<!DOCTYPE html>
<html lang="en">
<head>
  <title>TimeTable list</title>
  <%@ include file="header.jsp"%>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
   <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css">
 
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
  <script src="<%=ORSView.APP_CONTEXT%>/js/jquery.min.js"></script>
<script src="<%=ORSView.APP_CONTEXT%>/js/Checkbox11.js"></script>
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
	//var d = new Date(90, 0, 1);
	$(function() {
		$("#date").datepicker({
			/*  beforeShowDay:DisableSunday, */
			changeMonth : true,
			changeYear : true,
			yearRange : '1980:2019',
			dateFormat : 'dd-mm-yy',
		/*minDate:0 */
		});
	});
</script>

  
</head>
<body style="background-color: #E6E6FA;">
<form action="<%=ORSView.TIMETABLE_LIST_CTL%>" method="post">
<div class="container-fluid">
<%UserDTO userBean1 = (UserDTO) session.getAttribute("user");
int i = DataUtility.getInt(request.getAttribute("next").toString());


%>

<jsp:useBean id="dto" class="in.co.sunrays.proj3.dto.TimeTableDTO"
       
        scope="request"></jsp:useBean>
   
 <%
        List l = (List) request.getAttribute("subjectname");
 List l1 = (List) request.getAttribute("courcename");
 
        
        %>
      
 

<h1 align="center" style="color:black">TimeTable list</h1>

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
 
 
  <%List l4=ServletUtility.getList(request); 
  
  %>
  <%List l9=ServletUtility.getList(request); %>
 <%if(l9.size()!=0){ %>
       
 <div class="row" ">
 <div class="col-sm-2"> </div>
 
 <div class="col-sm-2">

         <i class="fa fa-book"></i> <label for="email" style="color:black"">Course Name:</label>
       <%=HTMLUtility.getList("courceId",String.valueOf(dto.getCourseId()),l1)%>                       
                   </div>
   <div class="col-sm-2">
   
     <i class="fa fa-copy"></i> <label for="pwd" style="color:black">Subject Name:</label>
      <%=HTMLUtility.getList("subjectId",String.valueOf(dto.getSubjectId()),l)%>
                     </div>
    
      <div class="col-sm-2">
   
     <i class="fas fa-calendar-alt"></i> <label for="pwd" style="color:black">Exam Date:</label>
      <input type="text"  class="form-control" id="datepicker2" placeholder="Exam date" name="examDate">
    </div>
 
    <div class="col-sm-2"> 
    <button type="submit" value="<%=TimeTableListCtl.OP_SEARCH %>" class="btn btn-dark" style="margin-top: 25px;"><i class="fa fa-search" aria-hidden="true"></i>&nbsp;Search</button>
 
    <button type="submit" value="<%=TimeTableListCtl.OP_RESET%>" class="btn btn-dark" style="margin-top: 25px;"><i class="fa fa-refresh" aria-hidden="true"></i>&nbsp;Reset</button>
 </div>
  <div class="col-sm-2"> </div>
   </div>
   <br>

 <div class="table-responsive">
   <table class="table table-hover " border="5px" style="border-color:black border="3px">
   
    <thead>
    <tr style="background-color:black;">
                    <th style="color:white;" class="text-center"><input type="checkbox" id="select_all" name="select" >Select All</th>
                     <th style="color:white;" class="text-center">S.No</th>
                    <th style="color:white;" class="text-center">Course Name</th>
                    <th style="color:white;" class="text-center">subject Name</th>
                    <th style="color:white;" class="text-center">Semester</th>
                    <th style="color:white;" class="text-center">Exam Date</th>
                    <th style="color:white;" class="text-center">Exam Time</th>
                    <%if(userBean1.getRoleId()==4 || userBean1.getRoleId()==3||userBean1.getRoleId()==2){ %>
                    
                    <%}else{ %>
                   <th style="color:white;"  class="text-center">Edit</th>
                   <%} %>
            </thead>
    
            <%
                    int pageNo = ServletUtility.getPageNo(request);
                    int pageSize = ServletUtility.getPageSize(request);
                    int index = ((pageNo - 1) * pageSize) + 1;

                    List list = ServletUtility.getList(request);
                    Iterator<TimeTableDTO> it = list.iterator();
                    while (it.hasNext()) {
                        TimeTableDTO dto1 = it.next();
                %>
    
    <tbody>
     <tr>
                    <td><input type="checkbox" class="checkbox" name="ids" value="<%=dto1.getId()%>"  ></td>
                    <td align="center"><%=index++ %></td>
                    <td align="center "><%=dto1.getCourseName()%></td>
                    <td align="center"><%=dto1.getSubjectName()%></td>
                     <td align="center"><%=dto1.getSemester()%></td>
                   <%SimpleDateFormat sm=new SimpleDateFormat("yyyy-dd-mm"); %>
                       <td align="center"><%= dto1.getExamDate()%></td>
                      <td align="center"><%=dto1.getTime()%></td>
                     <%if(userBean1.getRoleId()==4 ||userBean1.getRoleId()==3 ||userBean1.getRoleId()==2){ %>
                    
                     <%}else{ %>
                     <td align="center"><a href="TimeTableCtl?id=<%=dto1.getId()%>"><i class="fa fa-edit" aria-hidden="true"></i></a></td>
                     <%} %>
           
         
      
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
     <button type="submit" name="operation" value="<%=TimeTableCtl.OP_PREVIOUS%>" class="previous btn btn-primary" disabled="disabled"><i  class='fas fa-caret-square-left' style="color:" aria-hidden="true"></i>&nbsp;Previous</button>
     <%}else{ %>
     
  <button type="submit" name="operation" value="<%=TimeTableListCtl.OP_PREVIOUS%>" class="previous btn btn-primary"><i  class='fas fa-caret-square-left' style="color:" aria-hidden="true"></i>&nbsp;Previous</button>
       
     <%} %>
     
     
      <%if(userBean1.getRoleId()==2||userBean1.getRoleId()==4||userBean1.getRoleId()==3){ %> 
                       
      <button type="submit" name="operation" value="<%=TimeTableListCtl.OP_DELETE%>" class="btn btn-danger" disabled="disabled"><i class="fa fa-trash" aria-hidden="true"></i>Delete</button>
<%}else{ %>
<button type="submit" name="operation" value="<%=TimeTableListCtl.OP_DELETE%>" class="btn btn-danger"><i class="fa fa-trash" aria-hidden="true"></i>Delete</button>

<%} %>

<%if(userBean1.getRoleId()==2||userBean1.getRoleId()==4||userBean1.getRoleId()==3){ %> 
      
       <button type="submit" name="operation" value="<%=TimeTableListCtl.OP_NEW%>" class="btn btn-success" disabled="disabled"><i class="fa fa-plus" aria-hidden="true"></i>Add</button>
       <%}else{ %>
        <button type="submit" name="operation" value="<%=TimeTableListCtl.OP_NEW%>" class="btn btn-success"><i class="fa fa-plus" aria-hidden="true"></i>Add</button>
     <%} %>
       
      <%if(i>0){ %>  
       <button type="submit" name="operation" value="<%=TimeTableListCtl.OP_NEXT%>" class="next btn btn-primary" >Next &nbsp;<i  class='fas fa-caret-square-right' style="color:" aria-hidden="true"></i></button></center>
       <%}else{ %>
       
          <button type="submit" name="operation" value="<%=TimeTableListCtl.OP_NEXT%>" class="next btn btn-primary" disabled="disabled" >Next &nbsp;<i  class='fas fa-caret-square-right' style="color:" aria-hidden="true"></i></button></center>
   <%} %>
    <%} %>
 
       </center>
      </ul>
</div>
<%if(l9.size()==0){ %>
<button type="submit" name="operation" value="<%=TimeTableListCtl.OP_BACK%>" class="next btn btn-primary" >Back </button></center>
 <%} %>


         

</form>
</body><br><br>
<%if(l9.size()==1||l9.size()==2||l9.size()==3 || l4.size()==4 || l4.size()==5 || l4.size()==6){ %>

<br><br><br><br><br><br><br><br><br><br><br><br><br>
<%} %>


 <%@ include file="Footer.jsp"%>
</html>

