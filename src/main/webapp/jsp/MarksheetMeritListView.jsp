 <%@page import="in.co.sunrays.proj3.controller.MarksheetMeritListCtl"%>

<%@page import="in.co.sunrays.proj3.controller.FacultyListCtl"%>

 <%@page import="in.co.sunrays.proj3.controller.ORSView"%>
<%@page import="in.co.sunrays.proj3.controller.UserListCtl"%>
<%@page import="in.co.sunrays.proj3.util.ServletUtility"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@page import="in.co.sunrays.proj3.util.HTMLUtility"%>
<%@page import="in.co.sunrays.proj3.util.HTMLUtility"%>
<%@page import="in.co.sunrays.proj3.dto.MarksheetDTO"%>
<%@page import="java.text.DecimalFormat"%>


<!DOCTYPE html>
<html lang="en">
<head>
  <title>Marksheet merit list</title>
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
<body style="background-color: #E6E6FA;">
<form action="<%=ORSView.MARKSHEET_MERIT_LIST_CTL%>" method="post">
<div class="container-fluid">

<jsp:useBean id="dto" class="in.co.sunrays.proj3.dto.TimeTableDTO"
       
        scope="request"></jsp:useBean>
   
 

<h1 align="center" style="color:black">Marksheet merit list</h1>
  <%List l4=ServletUtility.getList(request); 
  %>      
    <br>

 <div class="table-responsive">
   <table class="table " style="border-color:black;" border="3px">
   
    <thead>
    <tr style="background-color:black;">
                    <th class="text-center" style="color: white;">S.No</th>
                    <th class="text-center" style="color: white;">Roll No</th>
                    <th class="text-center" style="color: white;">Name</th>
                    <th class="text-center" style="color: white;">Physics</th>
                    <th class="text-center" style="color: white;">Chemistry</th>
                    <th class="text-center" style="color: white;">Maths</th>
                    <th class="text-center" style="color: white;">Total</th>
                    <th class="text-center" style="color: white;">Percentage</th>
                   
                       </thead>
    
               
                <% 
                    int pageNo = ServletUtility.getPageNo(request);
                    int pageSize = ServletUtility.getPageSize(request);
                    int index = ((pageNo - 1) * pageSize) + 1;

                    List list = ServletUtility.getList(request);
                    Iterator<MarksheetDTO> it = list.iterator();

                    while (it.hasNext()) {

                        MarksheetDTO bean = it.next();
                        String grade;
                        int phyMarks=bean.getPhysics();
                        int chemMarks=bean.getChemistry();
                        int mathMarks=bean.getMaths();
                        long total=phyMarks+chemMarks+mathMarks;
                        float percentage=(((phyMarks+chemMarks+mathMarks)*100)/300);
                       	//float percentage = (float) total / 3;
                        percentage= Float.parseFloat(new DecimalFormat("##.##").format(percentage));
                        
                        if(percentage>35 && percentage<50)
                        {
                    	    if(percentage<35 || phyMarks<35 || chemMarks<35 || mathMarks<35)
                    	    {
                    	    	grade="Fail";
                    	   }
                    	    else
                    	    {
                    	    	grade="C";
                    	    }
                        }
                        else if(percentage>50 && percentage<60)
                        { 
                        	if(percentage<35 || phyMarks<35 || chemMarks<35 || mathMarks<35)
                        	{
                        		grade= "Fail";	
                        	}
                        	else {
                        		grade= "B";
                        	}
                        	
                        }
                        else if(percentage>60 && percentage<80)
                        {
                        	if(percentage<35 || phyMarks<35 || chemMarks<35 || mathMarks<35)
                        	{
                        		grade= "Fail";	
                        	}
                        	else {
                        		grade= "A";
                        	}
                        		
                        }
                        else if(percentage>80 && percentage<90)
                        {
                        	if(percentage<35 || phyMarks<35 || chemMarks<35 || mathMarks<35)
                        	{
                        		grade= "Fail";	
                        	}
                        	else {
                        		grade= "A+";
                        	}
                        		
                        }
                        else if(percentage>90 && percentage<=100)
                        {
                        	if(percentage<35 || phyMarks<35 || chemMarks<35 || mathMarks<35)
                        	{
                        		grade= "Fail";	
                        	}
                        	else {
                        		grade= "A++";
                        	}
                        		
                        }
                        else if(percentage<35 || phyMarks<35 || chemMarks<35 || mathMarks<35)
                        {
                        	grade= "Fail";	
                        }

                        
                %>
    
    <tbody>
     <tr>
                   <td  class="text-center"><%=index++%></td>
                    <td  class="text-center"><%=bean.getRollNo()%></td>
                    <td  class="text-center"><%=bean.getName()%></td>
                    <td class="text-center"><%=bean.getPhysics()%></td>
                    <td  class="text-center"><%=bean.getChemistry()%></td>
                    <td class="text-center"><%=bean.getMaths()%></td>
                    <td class="text-center"><%=total %></td>
                     <td class="text-center"><%=percentage+"%"%></td>
                   
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
      <button type="submit" name="operation" value="<%=MarksheetMeritListCtl.OP_BACK%>" class="btn btn-dark" style="margin-top: 25px;"><i class="fa fa-back" aria-hidden="true"></i>&nbsp;Back</button>
         <a href="<%=ORSView.JASPER_CTL%>" class="btn btn-dark" style="margin-top: 25px;">Print</a>
           </center>                 
      </ul>
</div>

<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
                type="hidden" name="pageSize" value="<%=pageSize%>">
         

</form>
</body><br><br>
 <br><br><br><br><br><br><br>
<%@ include file="Footer1.jsp"%>
</html>

