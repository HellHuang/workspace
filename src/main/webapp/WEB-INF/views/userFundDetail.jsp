<%@ page session="true" %>
<%@ page language="java" contentType="text/html; charset=gb2312"
 pageEncoding="gb2312"%>
<!DOCTYPE HTML>
<html>
<head>
<title>Fund Information</title>
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
 	<script src='static/js/draw.js'></script>
	<script src='static/js/drawBar.js'></script>
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
                    <li>
                        <a href="FundPage" class="hvr-sweep-to-right"><i class="fa fa-flash "></i><label>Fund</label></a>                       
                    </li>               
                </ul>

            </div>

        </nav> -->

        <!-- /. NAV SIDE  -->
        <div id="">
            <div id="page-inner">
            	
            	
            	
                <!-- /. ROW  -->
                
                <!-- /. ROW  -->
				
				 <div class="row">
                    <div class="col-md-12">
                        <div class="row" style="margin-left:2px;margin-right:2px">
                        	
                        	<div class="col-md-3 alert alert-info">
                              Code: ${code}
                            </div>
                            <div class="col-md-1">
                        	</div>
                            <div class="col-md-4 alert alert-info">
                              Name: ${name}
                            </div>
                            <div class="col-md-1">
                        	</div>
                            <div class="col-md-3 alert alert-info">
                              Type: ${type}
                            </div>
                         </div>
                         <div class="row" style="margin-left:2px;margin-right:2px">
                            <div class="">
                                <div class="col-md-3 main-box mb-pink" style="margin-bottom:0px">
                                	<a href="#">
                                		<i class="fa fa-dollar fa-5x"></i>
                                		<br>
                                		<h5>nav: ${nav }</h5>
                                	</a>
                               	</div>
                            </div>
                            <div class="col-md-1">
                        	</div>
                            <div class="">
                                <div class=" col-md-4 main-box mb-pink" style="margin-bottom:0px">
                                	<a href="#">
                                		<i class="fa fa-dollar fa-4x"></i>
                                		<h5>Annualized rate of return:${rate }</h5>
                                	</a>
                               	</div>
                               
                               	
                            </div> 
                            <div class="col-md-1">
                        	</div>
                            <div class="">
                                <div class=" col-md-3 main-box mb-pink" style="margin-bottom:0px">
                                	<a href="#">
                                		<i class="fa fa-dollar fa-4x"></i>
                                		<h5>total assets: ${total }  </h5>
                                		<input type="hidden" id="code" value=${code}>
                                	</a>
                               	</div>
                               
                               	
                            </div> 
                                 	              
                        </div>
                        <!-- /. ROW  -->
                        <hr />
                    </div>
                  
                    <!--/.Chat Panel End-->
                </div>
                
                
				
               
                <!-- /. ROW  -->


                <!--/.Row-->
        
               
                <!--/.ROW-->
            <div class="row" >
	           
	            <div id="gdpDist" class="col-md-6" style="height:400px;border:0px solid red;text-align:center;padding-top:20px;margin-top:5px; margin-bottom:5px; margin-left:auto; margin-right:auto">
		
				</div>
				<div id="bar" class="col-md-6" style="height:400px;border:0px solid red;text-align:center;padding-top:20px;margin-top:5px; margin-bottom:5px; margin-left:auto; margin-right:auto">
		
				</div>
				
            </div>
			
			
			 <!--  <div class="row">
                    <div class="col-md-12">
						<div class="table-responsive">
							<table class="table table-striped table-bordered table-hover">
								<thead>
									<tr>
                                        <th>Fund background</th>
                                        <th>Fund manager introduction</th>
                                        <th>Investment portfolio</th>
                                        <th>Purchase rate</th>
									</tr>
								</thead>
								<tbody>
									<tr>
                                        <td>${background}</td>
                                        <td>${manager}</td>
                                        <td>${zichan}</td>
                                        <td>${manageRate}</td>                       
                                    </tr>
								</tbody>
							</table>
							<a href="FundPage"><button class="btn btn-success">Return</button></a>
							
						</div>						
                    </div>
                    <!--/.Chat Panel End-->
               <!--   </div>-->
                
                <!--  <div class="row">
	                <div class="col-md-4 col-sm-4">
	                    <div class="panel panel-primary">
	                        <div class="panel-heading">
	                            Fund background
	                        </div>
	                        <div class="panel-body">
	                            <p>${background}</p>
	                        </div>
	                        <div class="panel-footer">
	                            
	                        </div>
	                    </div>
	                </div>
	                <div class="col-md-8 col-sm-8">
	                    <div class="panel panel-primary">
	                        <div class="panel-heading">
	                             Manager background
	                        </div>
	                        <div class="panel-body">
	                            <p>${manager}</p>
	                        </div>
	                        <div class="panel-footer">
	                          
	                        </div>
	                    </div>
	                </div>
                </div>-->
                
                <!-- info start -->
			    <section style="background:#efefe9;">
			        <div class="container">
			            <div class="row" style="margin-left:2px;margin-right:2px">
			                <div class="board" style="margin-left:2px;margin-right:2px">
			                    <!-- <h2>Welcome to IGHALO!<sup>™</sup></h2>-->
			                    <div class="board-inner">
			                    <ul class="nav nav-tabs" id="myTab">			                     
			
			                  <li class="active"><a href="#profile" data-toggle="tab" title="Fund Background">
			                     <span class="round-tabs two">
			                         <i class="glyphicon glyphicon-gift"></i>
			                     </span> 
			           			</a>
			                 </li>
			                 <li><a href="#messages" data-toggle="tab" title="Fund Manager">
			                     <span class="round-tabs three">
			                          <i class="glyphicon glyphicon-user"></i>
			                     </span> </a>
			                  </li>
			                     
			                  </ul></div>
			
			                     <div class="tab-content">
			                     
			                      <div class="tab-pane fade  in active" id="profile">
			                          <h3 class="head text-center">
			                           <a href="#" class="btn btn-success btn-outline-rounded green"> Fund background <span style="margin-left:10px;" class="glyphicon glyphicon-send"></span></a>
			                         <sup></sup> </h3>
			                          <p class="narrow text-left">
			                          	  <br>
			                          	  <br>
			                              ${background}
			                          </p>
			                          <br>
			                         <h3 class="head text-center">
			                           <a onclick="tiao()"  class="btn btn-success btn-outline-rounded green"> Back <span style="margin-left:10px;" class="glyphicon glyphicon-send"></span></a>
			                         <sup></sup> </h3>
			                      </div>
			                      <div class="tab-pane fade" id="messages">
			                          <h3 class="head text-center">
			                           <a href="" class="btn btn-success btn-outline-rounded green"> Manager background <span style="margin-left:10px;" class="glyphicon glyphicon-send"></span></a>
			                          
			                          </h3>
			                          <p class="narrow text-left">
			                         	${manager}
			                          </p>	
			                          <br>
			                     	<h3 class="head text-center">
			                           <a onclick="tiao()" class="btn btn-success btn-outline-rounded green"> Back <span style="margin-left:10px;" class="glyphicon glyphicon-send"></span></a>
			                         <sup></sup> </h3>                        
			                      </div>			                      
			</div>
			</div>
				
			</div>
			</div>
			</section>
                   
                <!-- info end -->
            </div>
            <!-- /. PAGE INNER  -->
        </div>
        <!-- /. PAGE WRAPPER  -->
    </div>

		 </div>
	 </div>
 </div>
</div>
<script>
	function tiao(){
		window.history.back();
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