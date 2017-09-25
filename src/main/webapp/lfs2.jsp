<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"      "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Prep At Home</title>
<link rel="shortcut icon" href="prepathome.png" />
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <script type="text/javascript">
  </script>
  <style>
  .btn-success
   {
      transition: 0.5s;
   }

   .btn-success:hover, .btn-success:focus
   {
      background: #5cb85c;
      border-color: #5cb85c;
      color: black;
      transition: all 0.4s;
   }

   .btn-warning
   {
      transition: 0.5s;
      background: #5cb85c;
      border-color: #5cb85c;
   }

   .btn-warning:hover, .btn-warning:focus
   {
      background: #5cb85c;
      color: black;
      border-color: #5cb85c;
       transition: all 0.4s;
   }

   .panel.with-nav-tabs .nav-tabs
   {
      border-bottom: none;
  	
   }
   .panel.with-nav-tabs .nav-justified
   {
      margin-bottom: -4px;
     
   }

     .panel
   {
      margin: 7%;
      
      
   }
   
   .panel-info>.panel-heading {
	    color: yellow;
	    background-color: #5cb85c;
	    border-color: #bce8f1;
    }

   .modal-header
   {
      background: #5cb85c;
      color: white;
      text-align: center;
   }

   .input-group-addon
   {
      background-color: #5cb85c;
      color: white;
   }

   .iga1
   {
      background-color: #5cb85c;
      color: white;
   }

   .iga2
   {
      background-color: #5cb85c;
      color: white;
   }
   
	
	
  </style>
  </head>
  <body>
  <% response.setContentType("text/html"); %>
  <!-- background-repeat: no-repeat;  -->
  <div class="panel panel-default" style="background-image: url(./resources/someImage.jpg); border: 1px solid black; ">
  
  		<br/><h2 align="center">Outer Container LFS outof web-inf......</h2>
  		
  	<div class="container" >
  	
  	 <form method="POST" action="${contextPath}/login" class="form-signin">
  	
      <div class="row">
         <div class="col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
         <div class="panel with-nav-tabs panel-info">
            <div class="panel-heading">
               <ul class="nav nav-tabs">
                  <li class="active"><a href="#login" data-toggle="tab"> Login test </a></li>
                  <li><a href="#signup" data-toggle="tab"> Signup </a></li>
               </ul>
            </div>
           
			
            <div class="panel-body">
               <div class="tab-content">
                  <div id="login" class="tab-pane fade in active register">
                     <div class="container-fluid">
                        <div class="row">
                              <h2 class="text-center" style="color: #5cb85c;"> <strong> Login  </strong></h2><hr />

                              <div class="row">
                                 <div class="col-xs-12 col-sm-12 col-md-12">
                                    <div class="form-group">
                                       <div class="input-group">
                                          <div class="input-group-addon">
                                             <span class="glyphicon glyphicon-envelope"></span>
                                          </div>
                                          <input type="text" placeholder="Email Id" name="emailId" class="form-control" id="emailId" autofocus="true"/>
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

                                          <input type="password" placeholder="Password" name="password" class="form-control" id="password"/>
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

                                  <div class="col-xs-6 col-sm-6 col-md-6" style="float:right">
                                    <div class="form-group" style="float:right">
                                       <a href="#forgot" data-toggle="modal"> Forgot Password? </a>
                                    </div>
                                 </div>
                              </div>
                              <hr />
                              <div class="row">
                                 <div class="col-xs-12 col-sm-12 col-md-12">
                                    <button type="submit" class="btn btn-success btn-block btn-lg" onclick="loginValidation()"> Login </button>
                                 </div>
                              </div>

                        </div>
                     </div> 
                  </div>

                  <div id="signup" class="tab-pane fade">
                     <div class="container-fluid">
                        <div class="row">
                              <h2 class="text-center" style="color: #5cb85c;"> <Strong> Register </Strong></h2> <hr />
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
                                                <span class="glyphicon glyphicon-user"></span>
                                             </div>
                                             <input type="email" class="form-control" placeholder="Enter First Name" name="firstName" id="firstName"/>
                                          </div>
                                       </div>
                                    </div>
                                 </div>
                                 
                                 <div class="row">
                                    <div class="col-xs-12 col-sm-12 col-md-12">
                                       <div class="form-group">
                                          <div class="input-group">
                                             <div class="input-group-addon iga1">
                                                <span class="glyphicon glyphicon-user"></span>
                                             </div>
                                             <input type="email" class="form-control" placeholder="Enter Last Name" name="lastName" id="lastName"/>
                                          </div>
                                       </div>
                                    </div>
                                 </div>
                                 
                                 
                                 <div class="row">
                                    <div class="col-xs-12 col-sm-12 col-md-12">
                                       <div class="form-group">
                                          <div class="input-group">
                                             <div class="input-group-addon iga1">
                                                <span class="glyphicon glyphicon-envelope"></span>
                                             </div>
                                             *************
                                             <input type="email" class="form-control" placeholder="Enter E-Mail, this will be your user id for login" name="emailId" id="emailId"/>
                                          </div>
                                       </div>
                                    </div>
                                 </div>

                                 <div class="row">
                                    <div class="col-xs-12 col-sm-12 col-md-12">
                                       <div class="form-group">
                                          <div class="input-group">
                                             <div class="input-group-addon iga1">
                                                <span class="glyphicon glyphicon-lock"></span>
                                             </div>
                                             <input type="password" class="form-control" placeholder="Enter Password" name="password" id="password"/>
                                          </div>
                                       </div>
                                    </div>
                                 </div>
                                 
                                 <div class="row">
                                    <div class="col-xs-12 col-sm-12 col-md-12">
                                       <div class="form-group">
                                          <div class="input-group">
                                             <div class="input-group-addon iga1">
                                                <span class="glyphicon glyphicon-lock"></span>
                                             </div>
                                             <input type="password" class="form-control" placeholder="Confirm Password" name="passwordConfirm" id="passwordConfirm"/>
                                          </div>
                                       </div>
                                    </div>
                                 </div>
                                 
                                 <div class="row">
                                    <div class="col-xs-12 col-sm-12 col-md-12">
                                       <div class="form-group">
                                          <div class="input-group">
                                             <div class="input-group-addon iga1">
                                                <span class="glyphicon glyphicon-barcode"></span>
                                             </div>
                                             <input type="text" class="form-control" placeholder="Invitation Code" name="invitationCode" id="invitationCode"/>
                                          </div>
                                       </div>
                                    </div>
                                 </div>
                                 
                                 <hr>
                                 <div class="row">
                                    <div class="col-xs-12 col-sm-12 col-md-12">
                                       <div class="form-group">
                                          <button type="submit" class="btn btn-lg btn-block btn-warning" onclick="regValidation()"> Register</button>
                                       </div>
                                    </div>
                                 </div>
                        </div>
                     </div>
                  </div>
               </div>
            </div>
         </div>
      </div>
   </div>
   </form>
</div>


   <div class="modal fade" id="forgot">
      <div class="modal-dialog">
         <div class="modal-content">
            <div class="modal-header">
               <button type="button" class="close" data-dismiss='modal' aria-hidden="true"><span class="glyphicon glyphicon-remove"></span></button>
               <h4 class="modal-title" style="font-size: 32px; padding: 12px;"> Recover Your Password </h4>
            </div>

            <div class="modal-body">
               <div class="container-fluid">
                  <div class="row">
                     <div class="col-xs-12 col-sm-12 col-md-12">
                        <div class="form-group">
                           <div class="input-group">
                              <div class="input-group-addon iga2">
                                 <span class="glyphicon glyphicon-envelope"></span>
                              </div>
                              <input type="email" class="form-control" placeholder="Enter Your E-Mail ID" name="email" id="resetEmailId"/>
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
                              <input type="password" class="form-control" placeholder="Enter Your New Password" name="newpwd" id="resetPwdId"/>
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
                              <input type="password" class="form-control" placeholder="Confirm New Password" name="confirmPassword" id="resetCnfmPwdId"/>
                           </div>
                        </div>
                     </div>
                  </div>
                  
               </div>
            </div>

            <div class="modal-footer">
               <div class="form-group">
                  <button type="submit" class="btn btn-lg btn-info" onclick="resetPassword()"> Save <span class="glyphicon glyphicon-saved"></span></button>

                  <button type="button" data-dismiss="modal" class="btn btn-lg btn-default"> Cancel <span class="glyphicon glyphicon-remove"></span></button>
               </div>
            </div>
         </div>
      </div>
   </div>
  			
  	
  </div>
    </body>
  </html>
