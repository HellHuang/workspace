<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page session="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<html>
<head>
<title>Transfer</title>
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
	<link href='static/css/bootstrap.min.css' rel='stylesheet'>
	<link href='static/css/font-face.css' rel='stylesheet'>
	<script src='static/js/bootbox.min.js'></script>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                        <a href="userDeposit" class="active-menu"><i class="fa fa-jpy"></i> Time Deposit</a>                       
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
               
                <!-- /. ROW  -->
                
                <!-- /. ROW  -->

                <div class="row">
                	 <div class="col-md-1"></div>
                    <div class="col-md-10">
                         <div class="panel panel-info">
                        <div class="panel-heading" style="background-color:#7BCEC4">
                          <label>Time deposit confirmation </label>
                        </div>
                        <div class="panel-body">
                            <form role="form" method="post"  onSubmit="return checkCard()">
                                        <div class="form-group">
                                            <label class="col-md-6 col-lg-6">Deposit cardNumber:</label>
                                            <label class="col-md-6 col-lg-6">${cardNumber }</label>
											 <input id="cardNumber" name="cardNumber" type="hidden" value=${cardNumber }>
                                           	
                                        </div>
                                        
                                         <div class="form-group">
                                            <label class="col-md-6 col-lg-6">Deposit term: </label>
                                            <label class="col-md-6 col-lg-6">${term }</label>
											 <input class="col-md-8 col-lg-8" id="term" name="term" type="hidden" value=${time }>
                                           	
                                        </div>
                                        
                                 		<div class="form-group">
                                            <label class="col-md-6 col-lg-6">Deposit amount:</label>
                                             <label class="col-md-6 col-lg-6">CNY ${amount1 }</label>
                                            <input id="amount" name="amount" type="hidden"  value=${amount2 }>
                                        </div>
                                        
                                        <div class="form-group">
                                            <label class="col-md-6 col-lg-6">Balance:</label>
                                             <label class="col-md-6 col-lg-6">CNY ${balance }</label>
                                        </div>
                                        
										<div class="form-group">
                                            
                                             <label class="col-md-6 col-lg-6">Deposit type:</label>
                                             <label class="col-md-6 col-lg-6">Lump Fixed Deposit</label>
                                        </div>
                                        <div class="form-group">
                                          
                                            
                                            <label class="col-md-6 col-lg-6">Interest rate:</label>
                                            <label class="col-md-6 col-lg-6">${interestRate }%</label>
                                            <input class="col-md-8 col-lg-8" id="interestRate" name="interestRate" type="hidden" value=${interestRate }>
                                        </div>
                                         <div class="form-group">
                                         	<label class="col-md-6 col-lg-6">Maturity date: </label>
                                            <label class="col-md-6 col-lg-6">${maturityDate }</label>
                                         
                                        </div>
                                         <div class="form-group">
                                         	<label class="col-md-6 col-lg-6">Estimate rate of return:</label>
                                            <label class="col-md-6 col-lg-6">${returnRate }</label>
                                           
                                        </div>
                                         <div class="form-group">
                                            <label class="col-md-6 col-lg-6" >Card PIN</label>
                                            <input  id="cardpin" name="cardpin" class="col-md-4 col-lg-4 form-control" type="password" required="required" maxlength="6">
                                        </div>
                                        <br>
                                        <br>
                                          <label class="col-md-3 col-lg-3" ><br></label>
					                      <button type="submit" class="col-md-2 col-lg-2 btn btn-info" style="margin-top:10px">Confirm</button>
					                      <label class="col-md-2 col-lg-2" ><br></label>
					                      <button onclick="back()" type="button" class="col-md-2 col-lg-2 btn btn-info" style="margin-top:10px">Back</button>
					                       <label class="col-md-3 col-lg-3" ></label>
									
                                    </form>
                            </div>
                        </div>
                        <!-- /. ROW  -->
                        <hr />
                       

                    </div>
                  
                    <!--/.Chat Panel End-->
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


<!--footer star here-->
	<div class="copyright">
			<p style="color:black">2016 HD Banks . All rights reserved | Design by  <a  target="_blank">  Deft Lee & Helen Huang</a></p>
		</div>
<!--footer end here-->
	<script>
		function checkCard(){
			var cardNumber=document.getElementById("cardNumber");
			var cardpin=document.getElementById("cardpin");
			var term=document.getElementById("term");
			var amount=document.getElementById("amount");
			var interestRate=document.getElementById("interestRate");
			$.ajax({
				url:"<%=request.getContextPath()%>/userDepositConfirmCheck",
				type:"POST",
				dataType:"text",
				data:
				{
				'cardNumber':cardNumber.value,
				'cardpin':cardpin.value,
				'amount':amount.value,
				'interestRate':interestRate.value,
				'term':term.value
				},
				success:function(data){
					console.log(data);
					if(data=="locked"){
						bootbox.alert("<br><B>Your card is locked,you are not allowed to this operation");
						return false;
					}
					else if(data=="shouldLocked"){
						bootbox.alert("<br><B>Sorry you have input wrong card pin for 3 times, your card is locked. Please contact system administrator to unlock");
						return false;
					}
					else if(data=="wrong"){
						bootbox.alert("<br><B>Your card pin is wrong, if you input incorrectly for more than 3 times within 1 hour, the card will be locked");
						return false;
					}
					
					else if(data=="right"){
						bootbox.dialog({
				 			message:"<br><B>Time deposit action is complete.<br><br>",
				 			title:"Notice",
				 			buttons:{
				 				
				 				   Return: {
				 				      label: "Return",
				 				      className: "btn-success",
				 				      callback: function() {
				 				    	 window.location.href="userDeposit";
				 				      }
				 				    }
				 			}
				 		});
						return false;
					}
					else{
						bootbox.dialog({
				 			message:"<br><B>Sorry,for unkown reason this action is not successful.<br><br>",
				 			title:"Notice",
				 			buttons:{
				 				
				 				   Return: {
				 				      label: "Return",
				 				      className: "btn-success",
				 				      callback: function() {
				 				    	 window.location.href="userDeposit";
				 				      }
				 				    }
				 			}
				 		});
						return false;
					}
				},
				error:function(){},
			});
			return false;
		}
	
	</script>
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
 	function back(){
 		window.history.back();
 	}
 </script>
</body>
</html>