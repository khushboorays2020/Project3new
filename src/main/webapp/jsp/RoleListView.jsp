<%@page import="in.co.sunrays.proj3.controller.RoleListCtl"%>
<%@page import="in.co.sunrays.proj3.controller.BaseCtl"%>
<%@page import="in.co.sunrays.proj3.dto.RoleDTO"%>
<%@page import="in.co.sunrays.proj3.util.ServletUtility"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@page import="in.co.sunrays.proj3.util.HTMLUtility"%>
<html>
<head>
<title>Role List </title>
<link rel="stylesheet"
	href="http://localhost:8080/ORSProj3/css/list.css">
	<script type="text/javascript">
window.location.hash = "#focus";
</script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
</head>
<body>
 
<div class="container">
 
 
</head>
<body>

    <%@include file="header.jsp"%>
    
 <br><br><br>
	
    <jsp:useBean id="bean" class="in.co.sunrays.proj3.dto.RoleDTO"
			scope="request"></jsp:useBean>
			<%  List l1=(List)request.getAttribute("list"); %>
        <form action="<%=ORSView.ROLE_LIST_CTL%>" method="post">
        <%UserDTO userBean1 = (UserDTO) session.getAttribute("user");

System.out.println(userBean1.getFirstName());
%>
         <%
         List <?> list1 = ServletUtility.getList(request);
		 %>
           
<h1 align="center" style="color:black">Role list</h1>
 <%List l4=ServletUtility.getList(request); 
  %>      
  
   <%if(ServletUtility.getSuccessMessage(request) !=null && ServletUtility.getSuccessMessage(request).length()>0 ){ %>
  <div class="alert alert-success ">
    <button type="button" class="close" data-dismiss="alert">&times;</button>
    <strong>Success..!</strong><%=ServletUtility.getSuccessMessage(request) %>
  </div>
  <%} %>
  
  <%if(ServletUtility.getErrorMessage(request) !=null && ServletUtility.getErrorMessage(request).length()>0 ){ %>
 
  <div class="alert alert-danger">
    <button type="button" class="close" data-dismiss="alert">&times;</button>
    <strong>Error..!</strong><%=ServletUtility.getErrorMessage(request)%>
  </div>
  <%} %>
 
 
  
 
 <div class="row">
 
  <div class="col-sm-4"></div>
 <div class="col-sm-4">
 <%List l=ServletUtility.getList(request); %>
  <%if(l.size()!=0){ %>
 
        <i class="fa fa-user"></i> <label for="email" style="color:black"">Role Name:</label>
   <%=HTMLUtility.getList("name",String.valueOf(bean.getName()),l1)%>      
    </div>
  
    <div class="col-sm-4"> 
    <button type="submit" value="<%=RoleListCtl.OP_SEARCH%>" class="btn btn-dark" style="margin-top: 25px;"><i class="fa fa-search" aria-hidden="true"></i>&nbsp;Search</button>
 
    <button type="submit" value="<%=RoleListCtl.OP_RESET %>" class="btn btn-dark" style="margin-top: 25px;"><i class="fa fa-refresh" aria-hidden="true"></i>&nbsp;Reset</button>
 </div>
   </div>
   <br>

  <div class="table-responsive">
   <table class="table table-hover " border="5px" style="border-color:black; ">
    
    <thead>
    <tr style="background-color:black;">
                    <th style="color:white;" class="text-center"><input type="checkbox" id="select_all" name="select" class="text-center">Select All</th>
                    <th style="color:white;" class="text-center">S.No</th>
                    <th style="color:white;" class="text-center">Name</th>
                    <th style="color:white;" class="text-center">Description</th>
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
                    Iterator<RoleDTO> it = list.iterator();
                    while (it.hasNext()) {
                        RoleDTO dto1 = it.next();
                %>
    
    <tbody>
     <tr>
                   <td><input type="checkbox" class="checkbox" name="ids" value="<%=dto1.getId()%>"  
                   ></td>
                     <td align="center"><%=index++%></td> 
                    <td align="center"><%=dto1.getName()%></td>
                    <td align="center"><%=dto1.getDescription()%></td>
                    <%if(userBean1.getRoleId()==4 ||userBean1.getRoleId()==3 ||userBean1.getRoleId()==2){ %>
                    
                     <%}else{ %>
                     <td align="center"><a href="RoleCtl?id=<%=dto1.getId()%>" <%if (userBean1.getId() == dto1.getId() || dto1.getId() == RoleDTO.ADMIN) {%>
						onclick="return false;" <%}%>><i class="fa fa-edit" aria-hidden="true"></i></a></td>
                     <%} %>
           
         
      
                </tr>
                <%
                    }
                %>
         </tbody>
    </table> </div>
						</div>
					</div>
					<br>
					<div align="center">
						<div class="form-inline"  align="center">
							<div   align="center" class="form-group">

								<%
									if (pageNo == 1) {
								%>

								<button type="submit"  align="center" name="operation" class="btn btn-primary"
									style="background-color: #9f9fff; color: black"
									disabled="disabled" value="<%=RoleListCtl.OP_PREVIOUS%>">
									<span class="fas fa-caret-square-left"></span>Previous
								</button>
								&nbsp;&nbsp;&nbsp;
								<%
									}

										else {
								%>
								<button type="submit"  align="center" name="operation" class="btn btn-primary"
									value="<%=RoleListCtl.OP_PREVIOUS%>">
									<span class="fas fa-caret-square-left"></span>Previous
								</button>
								&nbsp;&nbsp;&nbsp;
								<%
									}
								%>
								<button type="submit" name="operation" class="btn btn-primary"  align="center"
									style="width: 90px" value="<%=RoleListCtl.OP_NEW%>"><span class="fa fa-plus"></span>New</button>
								&nbsp;&nbsp;&nbsp; <input type ="text" readonly="readonly"
									style="text-align: center; background-color: lightgreen; font-family: serif;  width: 150px"
                                    class="form-control"
									value = "Page <%=pageNo%> / <%=1%>">&nbsp;&nbsp;&nbsp;

								<button type="submit"   align="center"class="btn btn-danger" id="del"
									name="operation" value="<%=RoleListCtl.OP_DELETE%>"
									onclick="return confirm('Are you sure?')">
									<span class="fa fa-trash"></span> </span> Delete
								</button>
								&nbsp;&nbsp;&nbsp;
								<%
									if (index - 1 >=5) {
								%>
								<Button type="submit" name="operation" class="btn btn-primary"
									style="background-color: #9f9fff; color: black; width: 90px"
									disabled="disabled" value="<%=RoleListCtl.OP_NEXT%>">
									Next<span class="fas fa-caret-square-right"></span>
								</Button>
								<%
									} else {
								%>
								<button type="submit" name="operation" id="next"
									class="btn btn-primary" value="<%=RoleListCtl.OP_NEXT%>">
									Next<span class="fas fa-caret-square-right"></span>
								</button>
								&nbsp;&nbsp;&nbsp;
								<%
									}
								%>
							</div>

							<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
								type="hidden" name="pageSize" value="<%=pageSize%>">
							<%
								}
							%>
						</div>


					</div>
				</div>
			</div>
		</div>
	</form>

</body><br><br><br>
<%@include file="Footer.jsp"%>
</html>
