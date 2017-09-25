<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<title>Registration Form</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" />
	<style type="text/css">
		.panel-default
		{
		background-image:<c:url value="/resources/someImage.jpg" />
		width: 100%;
		height: auto;
		}
	</style>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
	<form method="POST" action="${contextPath}/admin/login" class="form-signin">
		<%
			response.setContentType("text/html");
		%>

		
		<div class="container">
		<br/> <br/> <br/> <br/> <br/> <br/>
			<div class="col-md-4 col-md-offset-4 col-sm-8 col-sm-offset-2">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h3 class="panel-title">Admin Sign In</h3>
					</div>
					
					<c:if test="${not empty invalidUser}">
					   <br/>
					   <span class="label label-danger" style="align:center"><strong>Invalid Credentials!</strong> ${invalidUser}</span>
					  
					</c:if>
					
					
					<div class="panel-body">
						<fieldset>
							<div class="form-group">
								<input class="form-control" placeholder="Enter E-mail" name="adminEmail"
									type="email" autofocus="true">
							</div>
							<div class="form-group">
								<input class="form-control" placeholder="Enter Password"
									name="adminPwd" type="password" value="">
							</div>
							
							<div class="row">
													<div class="col-xs-12 col-sm-12 col-md-12">
														<div class="form-group">
															<div class="input-group">
																<div class="input-group-addon">
																	<span class="glyphicon glyphicon-envelope"></span>
																</div>
																<input type="text" placeholder="Email Id" name="adminEmail"
																	class="form-control" id="emailId" autofocus="true" />
															</div>
														</div>
													</div>
												</div>

												<div class="row">
													<div class="col-xs-12 col-sm-12 col-md-12">
														<div class="form-group">
															<div class="input-group">
																<div class="input-group-addon">
																	<span class="glyphicon glyphicon-lock"></span>
																</div>

																<input type="password" placeholder="Password"
																	name="adminPwd" class="form-control" id="password" />
															</div>
														</div>
													</div>
												</div>
							
							<!-- Change this to a button or input when using this as a form 
							<a href="javascript:;" class="btn btn-primary">Login</a>-->
							<button type="submit" class="btn btn-primary">Login</button>
							
						</fieldset>
					</div>
				</div>
			</div>
		</div>
	</form>
</body>
</html>