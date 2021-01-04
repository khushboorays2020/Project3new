<%@page import="in.co.sunrays.proj3.controller.ORSView"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="in.co.sunrays.proj3.controller.GetMarksheetCtl"%>
<%@page import="in.co.sunrays.proj3.util.DataUtility"%>
<%@page import="in.co.sunrays.proj3.util.ServletUtility"%>
<html>
<head>
<link rel="icon" type="image/png"
	href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16x16" />
	<style type="text/css">
	.button2 {
  background-color: white; 
  color: black; 
  border: 2px solid #008CBA;
}
</style>
	
</head>
<body
	style="background-color: #E6E6FA;">
	<%@ include file="header.jsp"%>

	<jsp:useBean id="dto" class="in.co.sunrays.proj3.dto.MarksheetDTO"
		scope="request"></jsp:useBean>

	<div class="section section-signup page-header">
		<div class="container" style="margin-top: 150px;">
			<div class="row" style="margin-top: 50px;">
				<div class="col-lg-6 col-md-6 ml-auto mr-auto">
					<div class="card card-login">
						<form style="min-height: 300px;" class="form" method="post"
							action="<%=ORSView.GET_MARKSHEET_CTL%>">
							<%if (dto.getRollNo()==null){%>
							<div class="card-header card-header-primary text-center" style="background-color: black;">
								<h4 class="card-title" style="color: white;">Get Marksheet</h4>
							</div>
							<%
								if (ServletUtility.getSuccessMessage(request) != null
										&& ServletUtility.getSuccessMessage(request).length() > 0) {
							%>
							<div class="alert alert-success"
								style="line-height: 10px; margin-left: 20px; margin-right: 20px;">
								<div class="container" style="text-align: center;">
									<div class="alert-icon">
										<i class="fa fa-thumbs-o-up" aria-hidden="true"></i>
									</div>
									<button type="button" class="close" data-dismiss="alert"
										aria-label="Close">
										<span aria-hidden="true"><i class="material-icons">clear</i></span>
									</button>
									<b><%=ServletUtility.getSuccessMessage(request)%></b>
								</div>
							</div>
							<%
								}
							%>
							<%
								if (ServletUtility.getErrorMessage(request) != null
										&& ServletUtility.getErrorMessage(request).length() > 0) {
							%>
							<div class="alert alert-danger"
								style="line-height: 3px; margin-left: 20px; margin-right: 20px;">
								<div class="container" style="text-align: center;">
									<div class="alert-icon">
										<i class="fa fa-exclamation-circle" aria-hidden="true"></i>
									</div>
									<button type="button" class="close" data-dismiss="alert"
										aria-label="Close">
										<span aria-hidden="true"><i class="material-icons">clear</i></span>
									</button>
													<b><%=ServletUtility.getErrorMessage(request)%></b>
								</div>
							</div>
							
							<%} %>
					
							<%
								}
							%>
							<input type="hidden" name="id" value="<%=dto.getId()%>">

							<div class="card-body">
								<div
									class="input-group<%=(dto.getRollNo() != null && dto.getRollNo().trim().length() > 0) ? "" : " mt-5"%>">
									<div class="input-group-prepend">
										<span class="input-group-text"> <i
											class="material-icons">rollnumber</i>
										</span>
									</div>
									<input type="text" class="form-control" name="rollNo"
										placeholder="Enter Your RollNo..."
										value="<%=ServletUtility.getParameter("rollNo", request)%>">
								</div>
								<p class="description text-right">
									<font color="red"><%=ServletUtility.getErrorMessage("rollNo", request)%></font>
								</p>
								<div class="row">
									<div class="col-md-12 d-flex justify-content-center">
										  <button type="submit" name="operation"  value="<%=GetMarksheetCtl.OP_GO%>"  class="btn btn-dark btn-block button2" > <i  class='fa fa-paper-plane' style="color:" aria-hidden="true"></i>GO </button>
			</div>
								</div>
								<br>
							</div>
						</form>
					</div>
				</div>
				<%
					int physics = DataUtility.getInt(DataUtility.getStringData(dto.getPhysics()));
					int chemistry = DataUtility.getInt(DataUtility.getStringData(dto.getChemistry()));
					int maths = DataUtility.getInt(DataUtility.getStringData(dto.getMaths()));

					int total = physics + chemistry + maths;
					float percentage = (float) total / 3;
					percentage = Float.parseFloat(new DecimalFormat("##.##").format(percentage));

					if (dto.getRollNo() != null && dto.getRollNo().trim().length() > 0) {
				%>
				<div class="col-lg-6" style="border-color: #6610f2;">
					<div class="row p-2 border border-primary bg-light">
						<div 
							class="col-lg-12 bg-warning d-flex justify-content-center text-dark text-center " 
							 style="background-color: #6610f2;">
							<h3 style="color: #6610f2;">Rays Technologies, Indore</h3>
						</div>
						<div
							class="col-lg-12 border border-secondary d-flex justify-content-center">
							<div class="row w-100 d-flex justify-content-between text-dark">
								<div class="col-2 d-flex justify-content-center">Name:</div>
								<div
									class="col-4 d-flex justify-content-center border text-capitalize">
									<%=DataUtility.getStringData(dto.getName())%>
								</div>
								<div class="col-3 d-flex justify-content-center border">
									Roll No.:</div>
								<div class="col-3 d-flex justify-content-center text-uppercase">
									<%=DataUtility.getStringData(dto.getRollNo())%>
								</div>
							</div>
						</div>
						<div
							class="col-lg-12 d-flex table-responsive justify-content-center border border-secondary">
							<table class="table">
								<tr style="background-color: #e6e6e485;">
									<th align="center" style="width: 25%">Subject</th>
									<th align="center" style="width: 25%">Earned Credits</th>
									<th align="center" style="width: 25%">Total Credits</th>
									<th align="center" style="width: 25%">Grade</th>
								</tr>
								<tr>
									<td align="center">Physics</td>
									<td align="center"><%=physics%> <%
 	if (physics < 33) {
 %><span style="color: red">*</span> <%
 	}
 %></td>
									<td align="center">100</td>
									<td align="center">
										<%
											if (physics > 90 && physics <= 100) {
										%>A+ <%
											} else if (physics > 80 && physics <= 90) {
										%>A <%
											} else if (physics > 70 && physics <= 80) {
										%>B+ <%
											} else if (physics > 70 && physics <= 80) {
										%>B <%
											} else if (physics > 60 && physics <= 70) {
										%>C+ <%
											} else if (physics > 50 && physics <= 60) {
										%>C <%
											} else if (physics >= 33 && physics <= 50) {
										%>D <%
											} else if (physics >= 0 && physics < 33) {
										%><span style="color: red;">F</span> <%
 	}
 %>
									</td>
								</tr>
								<tr>
									<td align="center">Chemistry</td>
									<td align="center"><%=chemistry%> <%
 	if (chemistry < 33) {
 %> <span style="color: red">*</span> <%
 	}
 %></td>
									<td align="center">100</td>
									<td align="center">
										<%
											if (chemistry > 90 && chemistry <= 100) {
										%> A+ <%
											} else if (chemistry > 80 && chemistry <= 90) {
										%> A <%
											} else if (chemistry > 70 && chemistry <= 80) {
										%> B+ <%
											} else if (chemistry > 70 && chemistry <= 80) {
										%> B <%
											} else if (chemistry > 60 && chemistry <= 70) {
										%> C+ <%
											} else if (chemistry > 50 && chemistry <= 60) {
										%> C <%
											} else if (chemistry >= 33 && chemistry <= 50) {
										%> D <%
											} else if (chemistry >= 0 && chemistry < 33) {
										%> <span style="color: red;">F</span> <%
 	}
 %>
									</td>
								</tr>
								<tr>
									<td align="center">Maths</td>
									<td align="center"><%=maths%> <%
 	if (maths < 33) {
 %> <span style="color: red">*</span> <%
 	}
 %></td>
									<td align="center">100</td>
									<td align="center">
										<%
											if (maths > 90 && maths <= 100) {
										%> A+ <%
											} else if (maths > 80 && maths <= 90) {
										%> A <%
											} else if (maths > 70 && maths <= 80) {
										%>B+ <%
											} else if (maths > 70 && maths <= 80) {
										%>B <%
											} else if (maths > 60 && maths <= 70) {
										%>C+ <%
											} else if (maths > 50 && maths <= 60) {
										%>C <%
											} else if (maths >= 33 && maths <= 50) {
										%>D <%
											} else if (maths >= 0 && maths < 33) {
										%><span style="color: red;">F</span> <%
 	}
 %>
									</td>
								</tr>
							</table>
						</div>
						<div
							class="col-lg-12 d-flex justify-content-center border border-secondary">
							<table class="table">
								<tr style="background-color: #e6e6e485;">
									<th align="center" style="width: 25%">Total Marks</th>
									<th align="center" style="width: 25%">Percentage (%)</th>
									<th align="center" style="width: 25%">Division</th>
									<th align="center" style="width: 25%">Result</th>

								</tr>
								<tr>
									<th align="center"><%=total%> <%
 	if (total < 99 || physics < 33 || chemistry < 33 || maths < 33) {
 %><span style="color: red;">*</span> <%
 	}
 %></th>
									<th align="center"><%=percentage%>%</th>
									<th align="center">
										<%
											if (percentage >= 60 && percentage <= 100) {
										%>1<sup>st</sup> <%
 	} else if (percentage >= 40 && percentage < 60) {
 %>2<sup>nd</sup> <%
 	} else if (percentage >= 0 && percentage < 40) {
 %>3<sup>rd</sup> <%
 	}
 %>
									</th>
									<th align="center">
										<%
											if (physics >= 33 && chemistry >= 33 && maths >= 33) {
										%> <span style="color: forestgreen;">Pass</span> <%
 	} else {
 %><span style="color: red;">Fail</span> <%
 	}
 %>
									</th>
								</tr>
							</table>
						</div>
					</div>
				</div>
				<%
					}
				%>
			</div>
		</div>
	</div><br><br><br><br><br><br><br><br><br><br>
	<%@ include file="Footer.jsp"%>
</body>
</html>