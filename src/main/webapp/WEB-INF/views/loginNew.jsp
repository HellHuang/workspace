<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
<!-- BEGIN # BOOTSNIP INFO -->
<head>

<script type="text/javascript" src="static/js/login.js"></script> 
<script type="text/javascript" src="static/js/jquery-1.11.2.min.js"></script> 

 <link href="static/css/font-face.css" rel="stylesheet">
<link href="static/css/font-awesome.min.css" rel="stylesheet">
 <link href="static/css/bootstrap.min.css" rel="stylesheet">
<link href="static/css/templatemo-style.css" rel="stylesheet">
<link href="static/css/login.css" rel="stylesheet">


<script type="text/javascript" src="static/js/bootstrap.min.js"></script> 

 
</head>

<body class="light-gray-bg">

<div >

</div>


<section class="hero">

    <div class="container">
        <div class="row">
        	<div>
        	<img alt="" src="static/images/HD.png">
        	</div>
            <div class="col-md-10 col-md-offset-1">
                <div class="hero-content text-center">
                    <h1 style="font-size:60px">Welcome to HD BANK</h1>
                    <p class="intro"> A responsive HTML5 website exclusively for Administrators, <br> designed & developed by Deft Lee and Helen Huang.</p>
                    <a href="#" class="btn btn-primary btn-lg btn-large btn-margin-right"  role="button" data-toggle="modal" data-target="#login-modal">Log  in</a> 
                    <a href="#" class="btn btn-primary btn-lg btn-large btn-margin-right">contact us</a>
                </div>
            </div>
        </div>
    </div>


</section>



<!-- BEGIN # MODAL LOGIN -->
<div class="modal fade" id="login-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
    	<div class=" modal-dialog">
			<div class="templatemo-content-widget templatemo-login-widget white-bg ">
				
				<header class="text-center">
	          		<div class="square"></div>
	          		<h1>HD BANK - Login</h1>
	        	</header>
			
                
                <!-- Begin # DIV Form -->
                <div  class=" templatemo-login-form" >
                
                    <!-- Begin # Login Form -->
                    <form  class="templatemo-login-form" method="post" onSubmit="return check()" >
		           
                           
							<p id="message" style="font-size:15px;color:red"> </p>	
				    		<div class="form-group">
                                <div class="input-group">
                                    <div class="input-group-addon"><i class="fa fa-user fa-fw"></i></div>                   
                                    <input  id="login_username" name="login_username" type="text" class="form-control" placeholder="admin name" required="required"> 
                                    <br>          
                                 </div>  
                            

				    		<br>
                            <div class="form-group">
                                <div class="input-group">
                                 <div class="input-group-addon"><i class="fa fa-key fa-fw"></i></div>                    
                                    <input id="login_password" name="login_password" type="password" class="form-control" placeholder="password"  required="required">  

                                            
                                </div>  
                            </div> 

				    		<div class="form-group">
                                <div class="checkbox squaredTwo">
                                    <input type="checkbox" id="c1" name="cc" />
                                    <label for="c1"><span></span>Remember me</label>
                                    <br>
                                    <br>
                                </div>                  
                            </div>
                
                            <div class="form-group">
                                <button type="submit" class="templatemo-blue-button width-100"  >Login</button>
                                    
                                    <div>

                                        <button id="login_lost_btn" type="button" class="col-lg-6 col-md-6 btn btn-link">Lost Password?</button>
                                        <button id="login_register_btn" type="button" class="col-lg-6 col-md-6 btn btn-link">   Register</button>
                                    </div>
                            </div>
                 

                          
        		    	</div>

				       


                    </form>

                    <!-- End # Login Form -->
                    
                    <!-- Begin | Lost Password Form -->
                    <form id="lost-form" style="display:none;">
    	    		    <div class="modal-body">
		    				<div id="div-lost-msg">
                                <div id="icon-lost-msg" class="glyphicon glyphicon-chevron-right"></div>
                                <span id="text-lost-msg">Type your e-mail.</span>
                            </div>
		    				<input id="lost_email" class="form-control" type="text" placeholder="E-Mail (type ERROR for error effect)" required>
            			</div>
		    		    <div class="modal-footer">
                            <div>
                                <button type="submit" class="btn btn-primary btn-lg btn-block">Send</button>
                            </div>
                            <div>
                                <button id="lost_login_btn" type="button" class="btn btn-link">Log In</button>
                                <button id="lost_register_btn" type="button" class="btn btn-link">Register</button>
                            </div>
		    		    </div>
                    </form>
                    <!-- End | Lost Password Form -->
                    


                    <!-- Begin | Register Form -->
                    <form id="register-form" style="display:none;">
            		    <div class="modal-body">
		    				<div id="div-register-msg">
                                <div id="icon-register-msg" class="glyphicon glyphicon-chevron-right"></div>
                                <span id="text-register-msg">Register an account.</span>
                            </div>
		    				<input id="register_username" class="form-control" type="text" placeholder="Username (type ERROR for error effect)" required>
                            <input id="register_email" class="form-control" type="text" placeholder="E-Mail" required>
                            <input id="register_password" class="form-control" type="password" placeholder="Password" required>
            			</div>
		    		    <div class="modal-footer">
                            <div>
                                <button type="submit" class="btn btn-primary btn-lg btn-block">Register</button>
                            </div>
                            <div>
                                <button id="register_login_btn" type="button" class="btn btn-link">Log In</button>
                                <button id="register_lost_btn" type="button" class="btn btn-link">Lost Password?</button>
                            </div>
		    		    </div>
                    </form>
                    <!-- End | Register Form -->
                    

                </div>
                <!-- End # DIV Form -->
                
			</div>
		</div>
	</div>
    <!-- END # MODAL LOGIN -->
    <script>
    function check(){
    	var adminName=document.getElementById("login_username").value;
    	var adminPassword=document.getElementById("login_password").value;
    	var message=document.getElementById("message");
    	$.ajax({
			url:"<%=request.getContextPath()%>/login",
			type:"POST",
			dataType:"text",
			data:
				{
				'login_username':adminName,
				'login_password':adminPassword,
				
				
				},
				success:function(data){
					console.log(data);
					if(data=="1"){
						window.location.href="tomainpage";
						return true;
						
						
					}else{
						//alert("Wrong number");
						//window.location.href="wrong";
						$("#message").text("* Admin name or password is wrong");
						return false;
						
					}
				},
				error:function(){
					
				}
		});
    	return false;
    }
    	
    
    </script>
</body>
</html>