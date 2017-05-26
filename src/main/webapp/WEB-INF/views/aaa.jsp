<%@ page session="true" %>
<!DOCTYPE HTML>
<html>
<head>
<title>userMainpage</title>
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
	<script src='static/js/bootbox.min.js'></script>
	<script src="http://echarts.baidu.com/build/dist/echarts.js"></script>
	
	
	<script src="static/assets/js/jquery.metisMenu.js"></script>
	<script src="static/assets/js/custom.js"></script>
	<link href='static/css/bootstrap.min.css' rel='stylesheet'>
	<link href='static/css/font-face.css' rel='stylesheet'>
	<link href='static/css/detail.css' rel='stylesheet'>

		
<script>
$(function(){
	$('a[title]').tooltip();
	});
 function checkOut(){
		bootbox.dialog({
			message:"Do you want to logout?",
			title:"Notice",
			buttons:{
				confirm: {
				      label: "Confirm",
				      className: "btn-success",
				      callback: function() {
				        window.location.href="userLogin";
				      }
				    },
				   Return: {
				      label: "Return",
				      className: "btn-primary",
				      callback: function() {
				      }
				    }
			}
		});
	}
 </script>
 <script src='static/js/drawT3.js'></script>
 	<script src='static/js/drawT.js'></script>
	<script src='static/js/drawT2.js'></script>
	
</head>

<body>
<!--banner start here-->
<div class="banner">
  <div class="header">
	<div class="container">
		 <div class="header-main">
				<div class="logo">
					<h1>HD Bank</h1>
				</div>
				<div class="header-right">
					<div class="head-top">
					<br>
					<br>
					<div class="top-nav-right">
						<h3 style="color:black">${message}</h3>
				   </div>
				   
				  <div class="ph-numb">  
				   	<div id="loginContainer">
				   		<a onclick="checkOut()" style="text-align:right"><button class="btn btn-warning" style="width:96px;height:35px;background:#FFAD06"><font style="font-size:1.1em;color:#fff">Logout</font></button></a>
					</div>
				 </div>
				</div>
				 
				 
		    </div>

		 <div class="clearfix"> </div>
	  </div>
		 <div class="container " style="width:100%;">


		 	<div id="wrapper" >
        
        <!-- /. NAV TOP  -->
        
        <!--  <nav class="navbar-default navbar-side" role="navigation">
            <div class="sidebar-collapse" style="font-size:17px">
                <ul class="nav" id="main-menu">
                   <li>
                        <a class="active-menu" href="userMainpage"><i class="fa fa-dashboard "></i><label>Home</label></a>
                    </li>
                   
                     
                    <li>
                        <a href="userTransfer" class="hvr-sweep-to-right"><i class="fa fa-flash "></i><label>Transfer</label></a>
                        
                    </li>
                    <li>
                        <a href="userPayment" class="hvr-sweep-to-right"><i class="fa fa-flash "></i><label>Payment Service</label></a>
                        
                    </li>
                     <li>
                        <a href="userBalancePage" class="hvr-sweep-to-right"><i class="fa fa-bicycle "></i><label>Account Enquiry</label></a>
                         <!-- <ul class="nav nav-second-level collapse">
                           
                             <li>
                                <a href="getUserBalance" class="hvr-sweep-to-right"><i class="fa fa-desktop "></i>Balance </a>
                            </li>
                            <li>
                                <a href="userLastTen" class="hvr-sweep-to-right"><i class="fa fa-code "></i>Last ten</a>
                            </li>
                            <li>
                                <a href="userHistory" class="hvr-sweep-to-right"><i class="fa fa-code "></i>History</a>
                            </li>
                             
                           
                        </ul> -->
                   <!--  </li>                
                </ul>

            </div>

        </nav>-->
        
       
 <nav class="navbar  sidebar navbar-fixed-top" role="navigation">
<div class="nav-side-menu navbar-default navbar-side" role="navigation">
  
  
        <div class="menu-list">
  
            <ul id="menu-content" class="nav menu-content collapse out">
                <li>
                  <a href="#" class="hvr-sweep-to-right">
                  <i class="fa fa-dashboard fa-lg "></i> Home
                  </a>
                </li>

                <li data-toggle="collapse" data-target="#service" class="collapsed">
                  <a href="userTransfer" class="hvr-sweep-to-right"><i class="fa fa-globe fa-lg"></i> Transfer <span class="arrow"></span></a>
                </li>  
                <!--   <ul class="sub-menu collapse" id="service">
                  <li>New Service 1</li>
                  <li>New Service 2</li>
                  <li>New Service 3</li>
                </ul> -->
              
               
                 <li>
                  <a href="userEnquiry" class="hvr-sweep-to-right">
                  <i class="fa fa-user fa-lg"></i> Account Enquiry
                  </a>
                  </li>

                 <li>
                  <a href="FundPage" class="hvr-sweep-to-right">
                  <i class="fa fa-users fa-lg"></i> Funds
                  </a>
                </li>
                <li>
                  <a href="userDeposit" class="hvr-sweep-to-right">
                  <i class="fa fa-users fa-lg"></i> Time Deposit
                  </a>
                </li>
                
                <li>
                  <a href="userForeignExchange" class="hvr-sweep-to-right">
                  <i class="fa fa-users fa-lg"></i> Foreign Exchange
                  </a>
                </li>
                
                 <li data-toggle="collapse" data-target="#new" class="collapsed">
                  <a href="userPayment" class="hvr-sweep-to-right"><i class="fa fa-car fa-lg"></i> Payment Service<span class="arrow"></span></a>
                </li>
            </ul>
     </div>
</div>
  </nav>
   <!-- /. NAV SIDE  -->
      
	
           
            <!-- /. PAGE INNER  -->
       
        <input   type="hidden" id="cardNumber" value=${cardNumber}>
        <!-- /. PAGE WRAPPER  -->
        
            <div id="" class="col-md-12" style="background-color:rgba(255, 255, 255, 0.88)">
				<div class="row">
									<div class="col-md-12">
										<h1 style="text-align: center" class="page-head-line">Annual Data Report</h1>


									</div>
				</div>
				<div id="one" class="col-md-6" style="height:400px;border:0px solid red;text-align:center;padding-top:20px;margin-top:5px; margin-bottom:5px; margin-left:auto; margin-right:auto;background-color:rgba(255, 255, 255, 0.71)">
		
				</div>	
				<div id="two" class="col-md-6" style="height:400px;border:0px solid red;text-align:center;padding-top:20px;margin-top:5px; margin-bottom:5px; margin-left:auto; margin-right:auto;background-color:rgba(255, 255, 255, 0.71)">
		
				</div>		
				<div id="three" class="col-md-12" style="height:400px;border:0px solid red;text-align:center;padding-top:20px;margin-top:5px; margin-bottom:5px; margin-left:auto; margin-right:auto;background-color:rgba(255, 255, 255, 0.71)">
		
				</div  >
				<br>
				
				<div class="col-md-5">
				</div>
				
				  
				  <a onclick="tiao()" class=" col-md-2 btn btn-success btn-outline-rounded green"> Back <span style="margin-left:10px;" class="glyphicon glyphicon-send"></span></a>	
            </div>
                  
                    <!--/.Chat Panel End-->
                </div>
        
        
    </div>

		 </div>
	 </div>
 </div>
</div>
 <script>
 	function checkOut(){
 		bootbox.dialog({
 			message:"Do you want to logout?",
 			title:"Notice",
 			buttons:{
 				confirm: {
 				      label: "Confirm",
 				      className: "btn-success",
 				      callback: function() {
 				        window.location.href="userLogin";
 				      }
 				    },
 				   Return: {
 				      label: "Return",
 				      className: "btn-primary",
 				      callback: function() {
 				      }
 				    }
 			}
 		});
 	}
 </script>
 <script>
	function tiao(){
		window.history.back();
	}
</script>
<!--banner start here-->
<!--banner strip here-->
		<div class="copyright">
				<p style="color:black">© 2015 Treks . All rights reserved | Design by  <a href="http://w3layouts.com/" target="_blank">  W3layouts </a></p>
		</div>
<!--footer end here-->

</body>
</html>