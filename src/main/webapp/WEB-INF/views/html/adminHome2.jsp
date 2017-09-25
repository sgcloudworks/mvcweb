<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%
	response.setContentType("text/html");
%>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Bootstrap Example</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
  <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    

  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  
  <script type="text/javascript">
  
  $('#checkAll').click(function () {    
	     $('input:checkbox').prop('checked', this.checked);    
	 });
  
  function showTabContent(tabId){
	  $('#tBodyId').empty();
	  $('#tempTable').hide();
	  $('#btnGrp').hide();
	  //alert(" tab id"+tabId);
	  //debugger;
  }
  
  function getAllEmployees(){
	  getActiveEmployees();
  }
  
   
  function getActiveEmployees(){
	  $('#tBodyId').empty();
	  debugger;
	  $.ajax({
	        url: "${contextPath}/admin/list"
	    }).then(function(data) {
	       debugger;
	       
	       var trHTML = '';
	        $.each(data, function (i, item) {
	            trHTML += '<tr>'+'<td> <label><input type="checkbox" name="check[]" value='+item.adminEmail+'></label></td>'+
	            '<td>' + item.firstName + '&nbsp' + item.lastName + '</td><td>' + item.active +'</td><td>' + item.adminEmail + '</td></tr>';
	        });
	        $('#tempTable').append(trHTML);
	        $('#btnGrp').show();
	       $('#tempTable').show();
	    });
	  /* $.get("http://localhost:9090/SpringRestExample//#/app/getAllActiveEmployees",function(data) {
	   		$(data).each(function(index) {
	   		 debugger;//test1QuestionsData[index] = data[index];        		   			
	   		});
	  }); */
  }
  </script>
</head>
<body>
<br/><br/>

<div class="container panel panel-default">
  <h2>Dynamic Tabs</h2> 
  <p align="right" class="text-capitalize"><b class="text-info">welcome<i class="text-primary"> ${name}&nbsp; </i></b><a href="${contextPath}/admin/">logout</a></p>
  <p>To make the tabs toggleable, add the data-toggle="tab" attribute to each link. Then add a .tab-pane class with a unique ID for every tab and wrap them inside a div element with class .tab-content.</p>
	<!-- TABS -->
  <ul class="nav nav-tabs">
    <li class="active"><a data-toggle="tab" href="#home"><b><i class="fa fa-home" style="font-size:30px"></i></b></a></li>
    <li><a data-toggle="tab" href="#menu1" onclick="showTabContent(1)"><b><span class="glyphicon glyphicon-th-list" style="font-size:30px"></span></b></a></li>
    <li style="text-align: center "><a data-toggle="tab" href="#menu2"  onclick="showTabContent(2)"><b><i class="material-icons" style="font-size:30px">person_add</i></b></a></li>
    <li><a data-toggle="tab" href="#menu3"  onclick="showTabContent(3)"><b>Menu 3</b></a></li>
  </ul>
<!-- TABS -->

  <div class="tab-content ">
    <div id="home" class="tab-pane fade in active">
      <h3>HOME</h3>
      <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>
    </div>
    <div id="menu1" class="tab-pane fade">
    		<br/>    	
		    <ul class="list-inline">
		    <li><a href="#" onclick="getActiveEmployees()">Active Admins</a></li>
		    <li><a href="#" onclick="getAllEmployees()">Employee Records</a></li>
		    <li><a href="#">By Division/Dept</a></li>
		    <li><a href="#">By Division/Emp Type</a></li>
		  </ul>
		  <div id="dataContent">
		  <br/>
		  <div class="btn-group-sm" id="btnGrp">
		    <button type="button" class="btn btn-primary">Edit</button>&nbsp;
		    <button type="button" class="btn btn-primary">Delete</button>&nbsp;
		    <button type="button" class="btn btn-primary">Terminate Employee</button>&nbsp;
		    <button type="button" class="btn btn-primary">Send Email</button>&nbsp;
			    <div class="btn-group">
				    <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
				    View <span class="caret"></span></button>
				    <ul class="dropdown-menu" role="menu">
				      <li><a href="#">Tablet</a></li>
				      <li><a href="#">Smartphone</a></li>
				    </ul>
				  </div>
				  
				  <div class="btn-group">
				    <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
				    Email Merge <span class="caret"></span></button>
				    <ul class="dropdown-menu" role="menu">
				      <li><a href="#">Tablet</a></li>
				      <li><a href="#">Smartphone</a></li>
				    </ul>
				  </div>
				  
				  <div class="btn-group">
				    <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
				    More <span class="caret"></span></button>
				    <ul class="dropdown-menu" role="menu">
				      <li><a href="#">Tablet</a></li>
				      <li><a href="#">Smartphone</a></li>
				    </ul>
				  </div>	    
		  <br/>
		  <br/>
		  <div id="tableContent panel panel-default center-block">
		  <table class="table-bordered table-striped table-condensed"  id="tempTable">
			    <thead class="bg-primary">
			      <tr>
			      <th>
					  <label><input type="checkbox" name="checkAll" id="checkAll" value=""></label>
					</th>
			        <th>Name</th>
			        <th>Active</th>
			        <th>Email</th>		        
			      </tr>
			    </thead>
			    <tbody id="tBodyId">
			    <tr>			    
			        <td></td>
			        <td></td>
			        <td></td>
			      </tr>			     
			    </tbody>
			  </table>
			  <br/>
		  </div>
		  </div>
    </div>
    </div>
    <div id="menu2" class="tab-pane fade">
      <h3>Menu 2</h3>
      <p>Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam.</p>
      
      <div class="panel-body">
				<form method="POST" action="${contextPath}/admin/login" >
					<div class="col-md-12 col-sm-12">


							<div class="form-group col-md-6 col-sm-6">
								<div class="input-group">
									<div class="input-group-addon">
										<span class="glyphicon glyphicon-user"></span>
									</div>
									<input type="text" required placeholder="Enter First Name"
										name="firstName" class="form-control" id="firstNameId"
										autofocus="true" />
								</div>
							</div>

							<div class="form-group col-md-6 col-sm-6">
								<div class="input-group">
									<div class="input-group-addon">
										<span class="glyphicon glyphicon-user"></span>
									</div>
									<input type="text" required placeholder="Enter Last Name"
										name="lastName" class="form-control" id="lastNameId"
										autofocus="true" />
								</div>
							</div>
							
							<div class="form-group col-md-6 col-sm-6">
								<div class="input-group">
									<div class="input-group-addon">
										<span class="glyphicon glyphicon-phone"></span>
									</div>
									<input type="text" required placeholder="Enter Mobile Number"
										name="mobileNumber" class="form-control" id="mobileNumberId"
										autofocus="true" />
								</div>
							</div>

							<div class="form-group col-md-6 col-sm-6">
								<div class="input-group">
									<div class="input-group-addon">
										<span class="glyphicon glyphicon-envelope"></span>
									</div>
									<input type="email" required placeholder="Enter E-Mail id"
										name="email" class="form-control" id="emailId"
										autofocus="true" />
								</div>
							</div>
							
							<div class="form-group col-md-6 col-sm-6">
								<div class="input-group">
									<div class="input-group-addon">
										<i class="fa fa-address-card-o"></i>
									</div>
									<input type="text" required placeholder="Enter City Name"
										name="city" class="form-control" id="cityId"
										autofocus="true" />
								</div>
							</div>
							
							<div class="form-group col-md-6 col-sm-6">
								<div class="input-group">
									<div class="input-group-addon">
										<i class="fa fa-address-card-o"></i>
									</div>
									<input type="text" required placeholder="Enter Zip Code"
										name="zipCode" class="form-control" id="zipCodeId"
										autofocus="true" />
								</div>
							</div>
							
							
							

							<div class="form-group col-md-6 col-sm-6">
								<div class="input-group">
									<div class="input-group-addon">										
										<i class="fa fa-address-card-o"></i>
									</div>
									<select class="form-control" id="stateId" name="state">
										<option value="">--Select State from the drop-down --</option>
										<option value="AK">Alaska</option>
										<option value="AL">Alabama</option>
										<option value="AR">Arkansas</option>
										<option value="AZ">Arizona</option>
										<option value="CA">California</option>
										<option value="CO">Colorado</option>
										<option value="CT">Connecticut</option>
										<option value="DC">District of Columbia</option>
										<option value="DE">Delaware</option>
										<option value="FL">Florida</option>
										<option value="GA">Georgia</option>
										<option value="HI">Hawaii</option>
										<option value="IA">Iowa</option>
										<option value="ID">Idaho</option>
										<option value="IL">Illinois</option>
										<option value="IN">Indiana</option>
										<option value="KS">Kansas</option>
										<option value="KY">Kentucky</option>
										<option value="LA">Louisiana</option>
										<option value="MA">Massachusetts</option>
										<option value="MD">Maryland</option>
										<option value="ME">Maine</option>
										<option value="MI">Michigan</option>
										<option value="MN">Minnesota</option>
										<option value="MO">Missouri</option>
										<option value="MS">Mississippi</option>
										<option value="MT">Montana</option>
										<option value="NC">North Carolina</option>
										<option value="ND">North Dakota</option>
										<option value="NE">Nebraska</option>
										<option value="NH">New Hampshire</option>
										<option value="NJ">New Jersey</option>
										<option value="NM">New Mexico</option>
										<option value="NV">Nevada</option>
										<option value="NY">New York</option>
										<option value="OH">Ohio</option>
										<option value="OK">Oklahoma</option>
										<option value="OR">Oregon</option>
										<option value="PA">Pennsylvania</option>
										<option value="PR">Puerto Rico</option>
										<option value="RI">Rhode Island</option>
										<option value="SC">South Carolina</option>
										<option value="SD">South Dakota</option>
										<option value="TN">Tennessee</option>
										<option value="TX">Texas</option>
										<option value="UT">Utah</option>
										<option value="VA">Virginia</option>
										<option value="VT">Vermont</option>
										<option value="WA">Washington</option>
										<option value="WI">Wisconsin</option>
										<option value="WV">West Virginia</option>
										<option value="WY">Wyoming</option>
									</select>
								</div>
							</div>
							
							<div class="form-group col-md-6 col-sm-6">
								<div class="input-group">
									<div class="input-group-addon">
										<span class="glyphicon glyphicon-globe"></span>
									</div>
									<input type="text" required name="country" value="USA" readonly class="form-control" id="countryId" />
								</div>
							</div>
							
							<div class="form-group col-md-6 col-sm-6">
								<div class="input-group">
									<div class="input-group-addon">
										<i class="material-icons">&#xe0da;</i>
									</div>
									<input type="text" required placeholder="Enter Invitation Key"
										name="inviteCode" class="form-control" id="inviteCodeId"
										autofocus="true" />
								</div>
							</div>
				            	
				            	
				            
								<div class="col-md-12 col-sm-12">
									<div class="form-group col-md-3 col-sm-3 pull-right">
										<input type="submit" class="btn btn-primary" value="Submit" />
									</div>
								</div>
							</div>
					</form>
			</div>
      
    </div>
    <div id="menu3" class="tab-pane fade">
      <h3>Menu 3</h3>
      <p>Eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo.</p>
    </div>
  
</div>

</body>
</html>
