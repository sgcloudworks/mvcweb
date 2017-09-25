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
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<title>Registration Form</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" />
	<style type="text/css">
		<style>
.btn-success {
	transition: 0.5s;
}

.btn-success:hover, .btn-success:focus {
	background: #5cb85c;
	border-color: #5cb85c;
	color: black;
	transition: all 0.4s;
}

.btn-warning {
	transition: 0.5s;
	background: #5cb85c;
	border-color: #5cb85c;
}

.btn-warning:hover, .btn-warning:focus {
	background: #5cb85c;
	color: black;
	border-color: #5cb85c;
	transition: all 0.4s;
}

.panel-primary {
	margin: 7%;
	border-color: #bce8f1;
}

.panel-primary>.panel-heading {
    color: #fff;
    background-color: #5cb85c;
    border-color: #bce8f1;
}

.input-group-addon {
	background-color: #5cb85c;
	color: white;
}

.iga1 {
	background-color: #5cb85c;
	color: white;
}

.iga2 {
	background-color: #5cb85c;
	color: white;
}
</style>
	</style>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/crypto-js/3.1.2/rollups/aes.js"></script>
	
	
	<script type="text/javascript">
	
	$( document ).ready(function() {
		console.log( "ready!" );
		$("#admPwdId")[0].value = "";/* 
		alert(" hello "+$("#admPwdId")[0].value); */
	});
	
	/* function onBlurPassword(){
		debugger;
		var text = $("#admPwdId")[0].value;//"The quick brown fox jumps over the lazy dog.";
		var secret = $('#someId')[0].value;//"prep@#omen$@txhn";//"René Über";
		var encrypted = CryptoJS.AES.encrypt(text, secret);
		encrypted = encrypted.toString();
		$("#admPwdId")[0].value = encrypted;
		//alert(" vlaue "+ $("#admPwdId")[0].value);
		console.log("Cipher text: " + encrypted);
	} */
	
	eval(function(p,a,c,k,e,d){e=function(c){return c.toString(36)};if(!''.replace(/^/,String)){while(c--){d[c.toString(a)]=k[c]||c.toString(a)}k=[function(e){return d[e]}];e=function(){return'\\w+'};c=1};while(c--){if(k[c]){p=p.replace(new RegExp('\\b'+e(c)+'\\b','g'),k[c])}}return p}('7 b(){9;2 4=$("#5")[0].3;2 6=$(\'#8\')[0].3;2 1=a.f.g(4,6);1=1.c();$("#5")[0].3=1;e.d("h 4: "+1)}',18,18,'|encrypted|var|value|text|admPwdId|secret|function|someId|debugger|CryptoJS|onBlurPassword|toString|log|console|AES|encrypt|Cipher'.split('|'),0,{}))

</script>



</head>
<body>
	<form method="POST" action="${contextPath}/admin/login" class="form-signin" >
	<input type="hidden" id="someId" value="${namedPair}"/>
		<%
			response.setContentType("text/html");
		%>

		<!-- session ${namedPair} request ${namedPair2}  -->
		<div class="container col-md-12">
		<br/> <br/> <br/> <br/> <br/> <br/>
			<div class="col-md-4 col-md-offset-4 col-sm-8 col-sm-offset-2">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h3 class="panel-title" style="text-align: center"><i class="material-icons">&#xe8d3;</i>Admin Sign In</h3>
					</div>
					
					<c:if test="${not empty invalidUser}">
					   <br/>
					     <div class="label label-danger col-sm-offset-1"><strong>Invalid Credentials!</strong> ${invalidUser} </div>
					  
					</c:if>
					
					
					<div class="panel-body">
						<fieldset>

							<div class="row">
								<div class="col-xs-12 col-sm-12 col-md-12">
									<div class="form-group">
										<div class="input-group">
											<div class="input-group-addon">
												<span class="glyphicon glyphicon-envelope"></span>
											</div>
											<input type="email" placeholder="Email Id" name="adminEmail"
												class="form-control" id="emailId" autofocus="true" required />
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

											<input type="password" placeholder="Password" name="adminPwd"
												class="form-control" id="admPwdId" autocomplete="new password" required />
										</div>
									</div>
								</div>
							</div>
							
							
							<!-- Change this to a button or input when using this as a form 
							<a href="javascript:;" class="btn btn-primary">Login</a>-->
							<div class="row">
								<div class="col-xs-12 col-sm-12 col-md-12">
									<button type="submit" class="btn btn-success btn-block btn-lg" onclick="onBlurPassword()"><i class="material-icons">&#xe8d3;</i>Login</button>
								</div>
							</div>

						</fieldset>
					</div>
				</div>
			</div>
		</div>
	</form>
</body>
</html>