<%@ page session="true" %>
<!DOCTYPE HTML>
<html>
<head>
<title>Retrieval Password</title>
	<link href="static/css/bootstrap.css" rel="stylesheet" type="text/css" media="all">
	<script src="static/js/jquery-1.11.0.min.js"></script>
	<link href="static/css/style.css" rel="stylesheet" type="text/css" media="all"/>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<meta name="keywords" content="Treks Responsive web template, Bootstrap Web Templates, Flat Web Templates, Android Compatible web template, 
	Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />
	<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
	<script src="static/js/menu_jquery.js"></script>
	<link href="static/assets/css/bootstrap.css" rel="stylesheet" />
	<link href="static/assets/css/font-awesome.css" rel="stylesheet" />
	<link href="static/assets/css/basic.css" rel="stylesheet" />
	<link href="static/assets/css/custom.css" rel="stylesheet" />
	<link href='http://fonts.useso.com/css?family=Open+Sans' rel='stylesheet' type='text/css' />
	<script src="static/assets/js/jquery-1.10.2.js"></script>
	<script src="static/assets/js/bootstrap.js"></script>
	<script src="static/assets/js/jquery.metisMenu.js"></script>
	<script src="static/assets/js/custom.js"></script>
	<link rel="stylesheet" href="static/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="static/css/bootstrapValidator.css"/>
    <script type="text/javascript" src="static/js/jquery-1.11.2.min.js"></script>
    <script type="text/javascript" src="static/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="static/js/bootstrapValidator.js"></script>
</head>

<body>
<!--banner start here-->
<div class="banner">
  <div class="header">
	<div class="container">
		 <div class="header-main">
				<div class="logo">
					<h1>XX Bank</h1>
				</div>
				<div class="header-right">
					<div class="head-top">
					<div class="top-nav-right">
						<br>
						<br>
						<div id="loginContainer">
							<a href="#" id="loginButton"><span>Login</span></a>
							   <div id="loginBox">                
                                    
                                   <form id="loginForm" method="post" class="form-horizontal" onsubmit="return checkLogin()">
                                     
                                         <fieldset>
	                                        <div class="col-md-4 col-lg-4"></div>
	                                        <div class="col-md-8 col-lg-8"><h2> <a>Login </a></h2></div>
	                                       	 	<p class="col-md-1 col-lg-1"></p>
	                                       		<p class="col-md-10 col-lg-10" id="idInfo" style="color:red;margin-top:0px;font-size:small"></p> 
	                                       		<p class="col-md-1 col-lg-1"></p> 
                                        </fieldset>
                                        <fieldset>
                           
                                        <h2> </h2>
                                        <div class="form-group">
                                            <div class="col-md-1 col-lg-1"></div>
                                            <a class="col-md-3 col-lg-3 control-label">User Id</a>
                                            <div class="col-md-7 col-lg-7">
                                                <input id="userId" type="text" class="form-control" name="userId" onchange="checkId()" required="required" maxlength="18"/>												
                                            </div>
                                            <div class="col-md-1 col-lg-1"></div>
                                        </div>
                                
                                        <div class="form-group">
                                            <div class="col-md-1 col-lg-1"></div>
                                            <a class="col-md-3 col-lg-3 control-label">Password</a>
                                            <div class="col-md-7 col-lg-7">
                                                <input id="userPassword" type="password" class="form-control" name="userPassword" required="required"/>
                                             </div>
                                              <div class="col-md-1 col-lg-1"></div>
                                        </div>
                                        </fieldset>



                                        <div class="form-group">
                                            <div class="col-md-1 col-lg-1"></div>
                                            <div class="col-md-10 col-lg-10 " >
                                                <button type="submit" class="btn btn-primary col-md-12 col-lg-12 hvr-sweep-to-right">Login</button>
                                                <br>
                                                <a class="btn btn-primary col-md-12 col-lg-12 hvr-sweep-to-right " style="margin-top:15px" href="userRegister">register</a>
                                            <div class="col-md-1 col-lg-1"></div>  
                                                
                                 
                                            </div>
                                            <div class="col-md-1 col-lg-1"></div>

                                        <div class="col-md-6 col-lg-6"> <a href="userForgetPass"> forget password? </a></div>
                                        
                                    </div>
                                    </form>


                            </div>
					</div>
				   </div>
				   
				   <div class="ph-numb">  
				   		<div id="loginContainer"><a href="userRegister" id="loginButton"><span>Register</span></a>							    
					</div>
				 </div>
				 </div>
		    </div>
		 <div class="clearfix"> </div>
	  </div>
		 <div class="container " style="width:100%;">


		 	<div id="wrapper" >
        
        <!-- /. NAV TOP  -->
        <nav class="navbar-default navbar-side" role="navigation">
            <div class="sidebar-collapse" style="font-size:17px">
                <ul class="nav" id="main-menu">
                   <li>
                        <a href="userMainpage" class="hvr-sweep-to-right"><i class="fa fa-university"></i> Home</a>                       
                    </li>    
                    <li>
                        <a href="userTransfer"  class="hvr-sweep-to-right"><i class="fa fa-exchange"></i> Transfer</a>
                    </li>                  
                     <li>
                        <a href="userEnquiry" class="hvr-sweep-to-right"><i class="fa fa-pie-chart"></i> Account Enquiry</a>
                    </li> 
                    <li>
                        <a href="FundPage" class="hvr-sweep-to-right"><i class="fa fa-money"></i> Fund</a>                       
                    </li>   
                     <li>
                        <a href="userDeposit" class="hvr-sweep-to-right"><i class="fa fa-jpy"></i> Time Deposit</a>                       
                    </li> 
                    <li>
                        <a href="userForeignExchange" class="hvr-sweep-to-right"><i class="fa fa-usd"></i> Foreign Exchange</a>                       
                    </li>    
                    <li>
                        <a href="userPayment" class="hvr-sweep-to-right"><i class="fa fa-paypal"></i> Payment Service</a>                       
                    </li>    
                </ul>

            </div>

        </nav>

        <!-- /. NAV SIDE  -->
        <div id="page-wrapper">
            <div id="page-inner">
                <div class="row">
                    <div class="col-md-12">
                         <h1 class="page-head-line" style="text-align:center">Retrieval Password</h1>
                    </div>
                </div>
                <!-- /. ROW  -->
                
                <!-- /. ROW  -->
			<div class="col-lg-8 col-lg-offset-2">
			<div class="panel panel-info">
                 <div class="panel-body">
				<form id="defaultForm" method="post" action="RetrievalPassword" class="form-horizontal" >
                	<div class="col-md-12">
                    	<div class="row">						
							<label class="col-md-4 control-label">Password</label>
							<div class="col-md-8">
                                 <input type="password" class="form-control" name="password" />
                                 <input type="hidden" name="userId" value=${userId} />
                                 <input type="hidden" name="userAccount" value=${userAccount} />                                                         
                            </div>
                  	  	</div>
                  	  	<br/>
                  	  	<div class="row">
                  	  		<label class="col-md-4 control-label">Retype password</label>
                            <div class="col-md-8">
                                 <input type="password" class="form-control" name="confirmPassword" />                              
                            </div>
                  	  	</div>
                  	  	<br/>
                  	  	<div class="form-group">
                         
                            <div class="col-lg-3 col-md-3 "></div>
                            <div class="col-lg-6 col-md-6 ">
                            	<div class="col-lg-3 col-md-3 "></div>
                             	 <div class="col-lg-3 col-md-3 ">
                                	<button type="submit" class="btn btn-primary ">Submit</button> 
                                 </div>
                           		<div class="col-lg-3 col-md-3 "></div>
                           		<div class="col-lg-3 col-md-3 ">
                               		 <button id="register_login_btn" type="button" class="btn btn-primary" onclick="javascript:window.location.href='userForgetPass';">Return</button>
                               	</div>
                              </div>
                            <div class="col-lg-3 col-md-3 "> </div>
                           
		    		    </div>
               	 	</div>
				</form>
				</div>
				</div>
            </div>
            <!-- /. PAGE INNER  -->
        </div>
        <!-- /. PAGE WRAPPER  -->
    </div>

		 </div>
	 </div>
 </div>
</div> 

<script type="text/javascript">
window.onload=check();

function check(){
    $('#defaultForm').bootstrapValidator({
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
              idNumber: {
              
                validators: {
                    notEmpty: {
                        message: 'The idNumber is required and can\'t be empty'
                    },
                    stringLength: {
                        min: 6,
                        max: 18,

                        message: 'The idNumber must be 18 characters long'
                    },
                    regexp: {
                        regexp: /^[0-9]{18}$|^[0-9]{17}X$/,
                        message: 'Id number format is not correct'
                    }
                }
            },
            userName:{
            	 validators: {
                     notEmpty: {
                         message: 'The idNumber is required and can\'t be empty'
                     },
                     stringLength: {
                        min:0,
                        message: ''
                     },
                     regexp: {
                    	 regexp: /[ -~]/,
                    	 message: ''
                     }
                 }
            },
         
          
            email: {
                validators: {
                    notEmpty: {
                        message: 'The email address is required and can\'t be empty'
                    },
                    emailAddress: {
                        message: 'The input is not a valid email address'
                    }
                }
            },
            password: {
                validators: {
                    notEmpty: {
                        message: 'The password is required and can\'t be empty'
                    },
                    regexp: {
                   	 regexp: /^(?=.*[0-9].*)(?=.*[A-Z].*)(?=.*[a-z].*).{6,20}$/,
                   	 message: 'password should be at least 6 characters and contains a mixture of lower and upper case letter and digit'
                    },
                    
                    
                    identical: {
                       field: 'confirmPassword',
                       message: 'The password and its confirm are not the same'
                    }
                }
            },
            confirmPassword: {
                validators: {
                    notEmpty: {
                        message: 'The confirm password is required and can\'t be empty'
                    },
                    identical: {
                        field: 'password',
                        message: 'The password and its confirm are not the same'
                    }
                }
            },
           
            
        }
    });
}
</script>

<!--banner start here-->
<!--banner strip here-->
		<div class="copyright">
			<p style="color:black">2016 HD Banks . All rights reserved | Design by  <a  target="_blank">  Deft Lee & Helen Huang</a></p>
		</div>
<!--footer end here-->

</body>
</html>