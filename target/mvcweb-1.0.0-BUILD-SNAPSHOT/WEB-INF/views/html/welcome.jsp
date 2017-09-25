<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"      "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Prep At Home</title>
<link rel="shortcut icon" href="prepathome.png" />
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript">
  </script>
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

.panel.with-nav-tabs .nav-tabs {
	border-bottom: none;
}

.panel.with-nav-tabs .nav-justified {
	margin-bottom: -4px;
}

.panel {
	margin: 7%;
}

.panel-info>.panel-heading {
	color: yellow;
	background-color: #5cb85c;
	border-color: #bce8f1;
}

.modal-header {
	background: #5cb85c;
	color: white;
	text-align: center;
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

.focused {
   border-color:red;
}
</style>
<script type="text/javascript">

var formErrorCounter = 0;

var goodColor = "#66cc66";
var badColor = "#ff6666";

function validate(password) {
	debugger;
    var minMaxLength = /^[\s\S]{8,32}$/,
        upper = /[A-Z]/,
        lower = /[a-z]/,
        number = /[0-9]/,
        special = /[ !"#$%&'()*+,\-./:;<=>?@[\\\]^_`{|}~]/;

    if (minMaxLength.test(password) &&
        upper.test(password) &&
        lower.test(password) &&
        number.test(password) &&
        special.test(password)
    ) {
        return true;
    }

    return false;
}

function pwdStrengthCheck(str){
	
	//validate(str);
	
	
	var meetsPwdStrengthFlag = true;
	var flagMessage = new Array(2);
	flagMessage[0] = true;
	flagMessage[1] = "Ok";
	
	if(str) {
		if (str.length < 8) {
			meetsPwdStrengthFlag = false;
			flagMessage[0] = false;
			flagMessage[1] = "Password must be atlest 8 characters long";			
		} else if (str.search(/\d/) == -1) {
			meetsPwdStrengthFlag = false;
			flagMessage[0] = false;
			flagMessage[1] = "Password must contain at least one digit from 0 to 9";
		} else if (str.search(/[a-z]/) == -1) {
			meetsPwdStrengthFlag = false;
			flagMessage[0] = false;
			flagMessage[1] = "Password must contain at least one lowercase letter";
		} else if (str.search(/[A-Z]/) == -1) {
			meetsPwdStrengthFlag = false;
			flagMessage[0] = false;
			flagMessage[1] = "Password must contain at least one uppercase letter";
		}else if (str.search(/[\!\@\#\$\*]/) == -1) {
			flagMessage[0] = false;
			flagMessage[1] = "Password must contain any of these special characters. [@,#,$,*]";
			meetsPwdStrengthFlag = false;
		}else if (str.search(/[^a-zA-Z0-9\!\@\#\$\*]/) != -1) {
			flagMessage[0] = false;
			flagMessage[1] = "Invalid character found. Allowed characters are [a-z][ A-Z][0-9][!,@,#,*]";
			meetsPwdStrengthFlag = false;
		} else if (str.length >30) {
			flagMessage[0] = false;
			flagMessage[1] = "Too lengthy password. Please limit the lenght of password between 8 to 30 characters long";
			meetsPwdStrengthFlag = false;
		}
	} else {
		flagMessage[0] = false;
		flagMessage[1] = "Password can not be empty";
		meetsPwdStrengthFlag = false;
	}
	
	return flagMessage;
}

function checkPwdStrength(arg, msg,btn){
	
	var pwdElementId = arg.getAttribute('id');
	var str =  $('#'+pwdElementId)[0].value;
	var regIdPwdId = '#'+pwdElementId;
	
	var pwdElementMsg = document.getElementById(msg);
	
	var meetsPwdStrengthFlag =  pwdStrengthCheck(str);
	
	if(meetsPwdStrengthFlag[0] == false){              
       	$(regIdPwdId).addClass("focused alert alert-warning");
       	pwdElementMsg.style.color = badColor;
       	pwdElementMsg.innerHTML = meetsPwdStrengthFlag[1] ;
       	formErrorCounter = formErrorCounter + 1;
       	//return false;
	} else {
		$(regIdPwdId).removeClass("focused alert alert-warning");
		pwdElementMsg.style.color = "";		
		pwdElementMsg.innerHTML =  "";
		if(formErrorCounter>0){
			formErrorCounter = formErrorCounter - 1;
		}
		//return false;
	}	
	
	validateForm(btn);
}

function matchPwds(arg,pwd,msg,btn){
	
	var cnfElementId = arg.getAttribute('id');
	var elementBox = '#'+cnfElementId;	
	var msgBox = document.getElementById(msg);
	
	var pwd1Value = document.getElementById(pwd).value;
	var pwd2Value =  $(elementBox)[0].value;
	
	if (pwd1Value && pwd2Value && pwd1Value.length > 0
			&& pwd2Value.length > 0) {
		
		var meetsPwdStrengthFlag =  pwdStrengthCheck(pwd2Value);
		
		var regIdPwdId = elementBox;
		
		if(meetsPwdStrengthFlag[0] == false){              
	       	$(regIdPwdId).addClass("focused alert alert-warning");
	       	msgBox.style.color = badColor;
	       	msgBox.innerHTML = meetsPwdStrengthFlag[1] ;
	       	enableButtons = false;
	       	formErrorCounter = formErrorCounter + 1;
	       	//return false;
		} else if(meetsPwdStrengthFlag[0] == true && pwd1Value !=  pwd2Value) {
			$(regIdPwdId).addClass("focused alert alert-warning");
			msgBox.style.color = badColor;
			msgBox.innerHTML = "Passwords don't match. Please input same password in Password and Confirm Password fields";
			formErrorCounter = formErrorCounter + 1;
	       	//return false;
		} else { 
			$(regIdPwdId).removeClass("focused alert alert-warning");
			msgBox.style.color = "";
			msgBox.innerHTML =  "";
			if(formErrorCounter>0){
				formErrorCounter = formErrorCounter - 1;
			}
			//return false;
		}
		
		validateForm(btn);
	
	}
	
}
	function verifyInviteCode(arg,msg,btn) {
		var servData;
		var email = $('#regEmailId')[0].value;
		var iCode = $('#inviteCode')[0].value;
		var iCodeMsg = document.getElementById("iCodeMessage");
		//alert("email" + email);
		$.ajax({
			url : "${contextPath}/user/verifyCode?emailId="+email+"&inviteCode="+iCode
		}).then(function(data) {
			 
			 servData = data;
			 if(data != "valid"){
				 $('#inviteCode').addClass("focused alert alert-warning");
			 	iCodeMsg.innerHTML = data;
			 	iCodeMsg.style.color = badColor;
			 	enableButtons = false;
			 	formErrorCounter = formErrorCounter + 1;
			 } else {
				 $('#inviteCode').removeClass("focused alert alert-warning");
				 iCodeMsg.style.color = "";
				 iCodeMsg.innerHTML =  "";
				 if(formErrorCounter>0){
						formErrorCounter = formErrorCounter - 1;
				 }
				 //enableButtons = true;
			 }
	       	
	       	console.log("Message from Server on email verification "+data);
	       	//return false;
		});
			if(formErrorCounter>0) {
				validateForm(btn);
				validateSubmit(btn,msg);
			}
		//return ("ok");
	}
	
	function studEmailIdExists(arg,msg,btn){
		
		var servData;
		
		var elementId = arg.getAttribute('id');
		var str =  $('#'+elementId)[0].value;
		var elementBox = '#'+elementId;
		
		var msgBox = document.getElementById(msg);
		
		$.ajax({
			url : "${contextPath}/user/emailEnrolled?emailId=" + str + ""
		}).then(function(data) {
			servData = data;
			if(data != "valid"){
				 $(elementBox).addClass("focused alert alert-warning");
				 msgBox.innerHTML = data;
				 msgBox.style.color = badColor;
				 formErrorCounter = formErrorCounter + 1;
				 enableButtons = false;
			 } else {
				 $(elementBox).removeClass("focused alert alert-warning");
				 msgBox.style.color = "";
				 msgBox.innerHTML =  "";
				 if(formErrorCounter>0){
					formErrorCounter = formErrorCounter - 1;
				 }
				 //enableButtons = true;
			 }
			
			console.log("Message from Server on email verification "+data);
		});
		if(formErrorCounter>0) {
			validateForm(btn);
			validateSubmit(btn,msg);
		}
		//return ("ok");
	}
	
	
function userExists(arg,msg,btn){
	
		var servData;
		
		var elementId = arg.getAttribute('id');
		var str =  $('#'+elementId)[0].value;
		var elementBox = '#'+elementId;
		
		var msgBox = document.getElementById(msg);
		
		var request = $.ajax({
			url : "${contextPath}/userExists?emailId=" + str + ""
		}).then(function(data) {
			servData = data;
			if(data != "valid"){
				 $(elementBox).addClass("focused alert alert-warning");
				 msgBox.innerHTML = data;
				 msgBox.style.color = badColor;
				 enableButtons = false;
				 formErrorCounter = formErrorCounter + 1;
				
			 } else {
				 $(elementBox).removeClass("focused alert alert-warning");
				 msgBox.style.color = "";
				 msgBox.innerHTML =  "";
				 if(formErrorCounter>0){
					formErrorCounter = formErrorCounter - 1;
				 }
				 //enableButtons = true;
			 }
			
			console.log("Message from Server on email verification "+data);
		});
		request.done(function( msg ) {
			//$( "#log" ).html( msg );
			 
	if (formErrorCounter >= 0) {
				//alert("hello formErrorCOunter at userExists"+formErrorCounter);
				validateForm(btn);
				validateSubmit(btn,msg);
			}
		});

		//return ("ok");
	}

	function validateForm(arg) {
		var elementId = document.getElementById(arg).id;// arg.getAttribute('id');//
		var element = '#' + elementId;
		debugger;

		if (formErrorCounter > 0) {
			$(element).attr("disabled", true);
		} else if (formErrorCounter == 0) {
			$(element).attr("disabled", false);
		}

		//enableButtons = true;
	}

	function validateSubmit(arg, msg) {
		//alert(" formErrorCounter" + formErrorCounter);
		var elementId = arg.getAttribute('id');//document.getElementById(arg).id;
		var element = '#' + elementId;
		debugger;
		var msgBox = document.getElementById(msg);

		if (formErrorCounter > 0 || ($('#emlMsg')[0].innerHTML.length > 0)
				|| ($('#emailMessage')[0].innerHTML.length > 0)
				|| ($('#iCodeMessage')[0].innerHTML.length > 0)) {
			msgBox.innerHTML = "Please clear all the errors in the form before submitting";
			msgBox.style.color = badColor;
			$(element).attr("disabled", true);
		} else if (formErrorCounter == 0) {
			msgBox.innerHTML = "";
			msgBox.style.color = "";
			$(element).attr("disabled", false);
		}
	}
</script>
</head>
<body>
	<!--<form method="POST" action="${contextPath}/login" class="form-signin" id="loginForm">-->
		<% response.setContentType("text/html"); %>
		<!-- background-repeat: no-repeat;  -->
		<div class="panel panel-default"
			style="background-image: url(./resources/someImage.jpg); border: 1px solid black;">

			<br />
			<h2 align="center">Welcome......</h2>

			<div class="container">

				<!--<form method="POST" action="${contextPath}/login" class="form-signin">-->

				<div class="row">
					<div class="col-md-8 col-sm-offset-2">
						<div class="panel with-nav-tabs panel-info">
							<div class="panel-heading">
								<ul class="nav nav-tabs">
									<li class="active"><a href="#login" data-toggle="tab">
											Login </a></li>
									<li><a href="#signup" data-toggle="tab"> Signup </a></li>
								</ul>
							</div>
							
							<div class="panel-body">
								<div class="tab-content">
									<div id="login" class="tab-pane fade in active register">
									<form method="POST" action="${contextPath}/login" class="form-signin">
										<div class="container-fluid">
											<div class="row">
												<h2 class="text-center" style="color: #5cb85c;">
													<strong> Login </strong>
												</h2>
												<hr />

												<div class="row">
													<div class="col-xs-12 col-sm-12 col-md-12">
													<c:if test="${not empty errorMessage}">													  
													     <!-- <div class="label label-danger col-sm-offset3"><strong>${errorMessage}</strong> </div> -->
													    <p id="loginMsg" class=" alert alert-danger loginMsg" style='word-wrap: break-word;'><strong>${errorMessage}</strong></p>
													     <hr/>													  
													</c:if>
													<c:if test="${not empty msg}">													  
													     <!-- <div class="label label-danger col-sm-offset3"><strong>${errorMessage}</strong> </div> -->
													    <p id="successMsg" class=" alert alert-success successMsg" style='word-wrap: break-word;'><strong>${msg}</strong></p>
													     <hr/>													  
													</c:if>
													<br/>
														<div class="form-group">
															<div class="input-group">
																<div class="input-group-addon">
																	<span class="glyphicon glyphicon-envelope"></span>
																</div>
																<input type="email" placeholder="Email Id" name="emailId"
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

																<input type="password" placeholder="Password"
																	name="password" class="form-control" id="password" required/>
															</div>
														</div>
													</div>
												</div>

												<div class="col-xs-12 col-sm-12 col-md-12">
													<!-- <div class="col-xs-6 col-sm-6 col-md-6">
                                    <div class="form-group">
                                       <input type="checkbox" name="check" checked> Remember Me
                                    </div>
                                 </div> -->

													<div class="col-xs-6 col-sm-6 col-md-6"
														style="float: right">
														<div class="form-group" style="float: right">
															<a href="#forgot" data-toggle="modal"> Forgot
																Password? </a>
														</div>
													</div>
												</div>
												<hr />
												<div class="row">
													<div class="col-xs-12 col-sm-12 col-md-12">
														<button type="submit"
															class="btn btn-success btn-block btn-lg">Login</button>
													</div>
												</div>

											</div>
										</div>
										</form>
									</div>

									<div id="signup" class="tab-pane fade">
										<div class="container-fluid">
										<form method="POST" action="${contextPath}/regUser" class="form-signin">
											<div class="row">
												<h2 class="text-center" style="color: #5cb85c;">
													<Strong> Register </Strong>
												</h2>
												
												<p id="regMsg" class=" alert alert-danger regMsg" style='word-wrap: break-word; display:none'><strong></strong></p>
												<span id="regErrMsg" class="regErrMsg"></span>
												<hr />
												<!-- <div class="row">
                                    <div class="col-xs-12 col-sm-12 col-md-12">
                                       <div class="form-group">
                                          <div class="input-group">
                                             <div class="input-group-addon iga1">
                                                <span class="glyphicon glyphicon-user"></span>
                                             </div>
                                             <input type="text" class="form-control" placeholder="Enter User Name" name="name">
                                          </div>
                                       </div>
                                    </div>
                                 </div>-->


												<div class="row">
													<div class="col-xs-12 col-sm-12 col-md-12">
														<div class="form-group">
															<div class="input-group">
																<div class="input-group-addon iga1">
																	<span class="glyphicon glyphicon-user" data-toggle="tooltip" title="First Name*"></span>
																</div>
																<input type="text" class="form-control"
																	placeholder="Enter First Name" name="firstName"
																	id="regFirstName" required />
															</div>
														</div>
													</div>
												</div>

												<div class="row">
													<div class="col-xs-12 col-sm-12 col-md-12">
														<div class="form-group">
															<div class="input-group">
																<div class="input-group-addon iga1">
																	<span class="glyphicon glyphicon-user" data-toggle="tooltip" title="Last Name*"></span>
																</div>
																<input type="text" class="form-control"
																	placeholder="Enter Last Name" name="lastName"
																	id="regLstName" required/>
															</div>
														</div>
													</div>
												</div>


												<div class="row">
													<div class="col-xs-12 col-sm-12 col-md-12">
														<div class="form-group">
															<div class="input-group">
																<div class="input-group-addon iga1">
																	<span class="glyphicon glyphicon-envelope" data-toggle="tooltip" title="Email*"></span>
																</div>
																<!-- <input type="email" class="form-control" onblur="getInvitationCodeFromEmailId()" -->
																<input type="email" class="form-control" 
																	placeholder="Enter E-Mail, this will be your user id for login"
																	name="emailId" id="regEmailId" required onblur="studEmailIdExists(this,'emailMessage','regBtn'); return false;" />
																	<span id="emailMessage" class="emailMessage"></span>
															</div>
														</div>
													</div>
												</div>
												
												<div class="row">
													<div class="col-xs-12 col-sm-12 col-md-12">
														<div class="form-group">
															<div class="input-group">
																<div class="input-group-addon iga1">
																	<span class="glyphicon glyphicon-barcode" data-toggle="tooltip" title="Invite Code*"></span>
																</div>
																<input type="text" class="form-control"
																	placeholder="Invitation Code" name="inviteCode"
																	id="inviteCode" required onblur="verifyInviteCode(this,'iCodeMessage','regBtn')"/>
																	<span id="iCodeMessage" class="iCodeMessage"></span>
																	<input type="hidden" id="hiddenInviteCode"/>
															</div>
														</div>
													</div>
												</div>

												<div class="row">
													<div class="col-xs-12 col-sm-12 col-md-12">
														<div class="form-group">
															<div class="input-group">
																<div class="input-group-addon iga1">
																	<span class="glyphicon glyphicon-lock" data-toggle="tooltip" title="Password*"></span>
																</div>
																<input type="password" class="form-control"
																	placeholder=" Password - minimum 8 characters long. Must consist any from [a-z][ A-Z][0-9][!,@,#,*] " name="password" 																	
																	id="regPassword" required data-toggle="tooltip" title="Password*" onblur="checkPwdStrength(this,'pwdMessage','regBtn'); return false"/>
																	<span id="pwdMessage" class="pwdMessage"></span>
															</div>
														</div>
													</div>
												</div>

												<div class="row">
													<div class="col-xs-12 col-sm-12 col-md-12">
														<div class="form-group">
															<div class="input-group">
																<div class="input-group-addon iga1">
																	<span class="glyphicon glyphicon-lock" data-toggle="tooltip" title="Confirm Password*"></span>
																</div>
																<input type="password" class="form-control"
																	placeholder="Confirm Password must match password entered above" tooltip="Password - minimum 8 chars long having [a-z A-Z][0-9][!,@,#,*]"name="confirmPassword"
																	id="regConfirmPassword" onblur="matchPwds(this,'regPassword','confirmMessage','regBtn'); return false;"/>
																	<span id="confirmMessage" class="confirmMessage"></span>
															</div> 
														</div>
													</div>
												</div>												

												<hr>
												<div class="row">
													<div class="col-xs-12 col-sm-12 col-md-12">
														<div class="form-group">
															<button type="submit" class="btn btn-lg btn-block btn-warning" id="regBtn" onclick="validateSubmit(this,'regErrMsg')"
																>Register</button>
														</div>
													</div>
												</div>
											</div>
											</form>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!--</form>-->
			</div>


			<div class="modal fade" id="forgot">
			<form method="POST" action="${contextPath}/reset" class="form-signin">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss='modal'
								aria-hidden="true">
								<span class="glyphicon glyphicon-remove"></span>
							</button>
							<h4 class="modal-title" style="font-size: 32px; padding: 12px;">
								Recover Your Password</h4>
						</div>

						<div class="modal-body">

							<div class="container-fluid">
								<span id="fgtMsg" class="fgtMsg"></span>
								<c:if test="${not empty errorMessage}">

									<!-- <div class="label label-danger col-sm-offset3"><strong>${errorMessage}</strong> </div> -->
									<p id="loginMsg" class=" alert alert-danger loginMsg"
										style='word-wrap: break-word;'>
										<strong>${errorMessage}</strong>
									</p>
									
									<hr />
								</c:if>
								<br />

								<div class="row">
									<div class="col-xs-12 col-sm-12 col-md-12">
										<div class="form-group">
											<div class="input-group">
												<div class="input-group-addon iga2">
													<span class="glyphicon glyphicon-envelope"></span>
												</div>
												<input type="email" class="form-control"
													placeholder="Enter Your E-Mail ID" name="emailId" onblur="userExists(this,'emlMsg','saveBtn')"
													id="restEmailId" />
													<span id="emlMsg" class="emlMsg"></span>
											</div>
										</div>
									</div>
								</div>

								<div class="row">
									<div class="col-xs-12 col-sm-12 col-md-12">
										<div class="form-group">
											<div class="input-group">
												<div class="input-group-addon iga1">
													<span class="glyphicon glyphicon-lock"
														data-toggle="tooltip" title="Password*"></span>
												</div>
												<input type="password" class="form-control"
													placeholder=" Password - minimum 8 characters long. Must consist any from [a-z][ A-Z][0-9][!,@,#,*] "
													name="password" id="resetPwd" required
													data-toggle="tooltip" title="Password*"
													onblur="checkPwdStrength(this,'pwdMsg','saveBtn'); return false" /> 
													<span id="pwdMsg" class="pwdMsg"></span>
											</div>
										</div>
									</div>
								</div>

								<div class="row">
									<div class="col-xs-12 col-sm-12 col-md-12">
										<div class="form-group">
											<div class="input-group">
												<div class="input-group-addon iga1">
													<span class="glyphicon glyphicon-lock"
														data-toggle="tooltip" title="Confirm Password*"></span>
												</div>
												<input type="password" class="form-control"
													placeholder="Confirm Password must match password entered above"
													tooltip="Password - minimum 8 chars long having [a-z A-Z][0-9][!,@,#,*]"
													name="confirmPassword" id="resetConfirmPwd"
													onblur="matchPwds(this,'resetPwd','confirmMsg','saveBtn'); return false;" /> 
													<span id="confirmMsg" class="confirmMsg"></span>
											</div>
										</div>
									</div>
								</div>

								<!--  <div class="row">
									<div class="col-xs-12 col-sm-12 col-md-12">
										<div class="form-group">
											<div class="input-group">
												<div class="input-group-addon iga2">
													<span class="glyphicon glyphicon-lock"></span>
												</div>
												<input type="password" class="form-control"
													placeholder="Enter Your New Password" name="password"
													id="password" />
											</div>
										</div>
									</div>
								</div>

								<div class="row">
									<div class="col-xs-12 col-sm-12 col-md-12">
										<div class="form-group">
											<div class="input-group">
												<div class="input-group-addon iga2">
													<span class="glyphicon glyphicon-lock"></span>
												</div>
												<input type="password" class="form-control"
													placeholder="Confirm New Password" name="confirmPassword"
													id="confirmPassword" />
											</div>
										</div>
									</div>
								</div>-->
							
							</div>

						</div>

						<div class="modal-footer">
							<div class="form-group">
								<button type="submit" class="btn btn-lg btn-info" id="saveBtn" onclick="validateSubmit(this,'fgtMsg')">
									Save <span class="glyphicon glyphicon-saved"></span>
								</button>

								<button type="button" data-dismiss="modal"
									class="btn btn-lg btn-default">
									Cancel <span class="glyphicon glyphicon-remove"></span>
								</button>
							</div>
						</div>
					</div>
				</div>
				</form>
			</div>


		</div>
	
</body>
</html>
