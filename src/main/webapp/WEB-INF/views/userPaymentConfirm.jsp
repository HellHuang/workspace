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
				   		<a onclick="checkOut()"  style="text-align:right"><button class="btn btn-warning" style="width:96px;height:35px;background:#FFAD06"><font style="font-size:1.1em;color:#fff">Logout</font></button></a>
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
                        <a href="userPayment" class="active-menu"><i class="fa fa-paypal"></i> Payment Service</a>                       
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
                          <label>Payment confirmation </label>
                        </div>
                        <div class="panel-body">
                            <form role="form"   onSubmit="return pay()">
                                        <div class="form-group">
                                            <label class="col-md-6 col-lg-6">Number:</label>
                                            <label class="col-md-6 col-lg-6">${billNumber }</label>
											 <input id="billNumber" name="billNumber" type="hidden" value=${billNumber }>
                                           	
                                        </div>
                                        
                                         <div class="form-group">
                                            <label class="col-md-6 col-lg-6">Item</label>
                                            <label class="col-md-6 col-lg-6">${billItem }</label>
                                        </div>
                                        
                                 		<div class="form-group">
                                            <label class="col-md-6 col-lg-6">Amount:</label>
                                             <label class="col-md-6 col-lg-6">CNY ${billAmount }</label>
                                              <input id="amount" name="amount" type="hidden" value=${billAmount }>
                                        </div>
										<div class="form-group">
                                            
                                             <label class="col-md-6 col-lg-6">Period:</label>
                                             <label class="col-md-6 col-lg-6">${billPeriod }</label>
                                        </div>
                                         <div class="form-group">
                                            <label>&nbsp&nbsp&nbsp&nbspPlease select your card account number</label>
			                                    <select class="form-control" name="cardNumber" id="cardNumber" onchange="getBalance()">
			                                    	<option value="">Please select your card account number</option>
			                                        <c:forEach var="x" items="${cardList}"> 
			                                        	<option value=${x.cardNumber} >${x.cardNumber}</option>
			                                        </c:forEach>	
			                                    </select>   
			                                <label id="balance" style="color:red"></label>
                                        </div>
                                        
                                        
                                         <div class="form-group">
                                            <label class="col-md-6 col-lg-6" >Card PIN</label>
                                            <input  id="cardPin" name="cardPin" class="col-md-4 col-lg-4 form-control" type="password" required="required" maxlength="6">
                                        </div>
                                        <br>
                                        <br>
                                          <label class="col-md-4 col-lg-4" ><br></label>
					                      <button type="submit" class="col-md-4 col-lg-4 btn btn-info" style="margin-top:10px">Confirm</button>
					                       <label class="col-md-4 col-lg-4" ></label>
									
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
		function getBalance(){
			var cardNumber=document.getElementById("cardNumber");
			var balance=document.getElementById("balance");
			$.ajax({
				url:"<%=request.getContextPath()%>/getCardBalance",
				type:"POST",
				dataType:"text",
				data:
				{
				'cardNumber':cardNumber.value,

				},
				success:function(data){
					console.log(data);
					//alert(data);
					$("#balance").text("Balance is CNY"+data);
				},
				error:function(){},
			});
		
		}
	
	</script>
	<script>
		function pay(){
			var cardNumber=document.getElementById("cardNumber");
			var cardPin=document.getElementById("cardPin");
			var amount=document.getElementById("amount");
			var billNumber=document.getElementById("billNumber");
			$.ajax({
				url:"<%=request.getContextPath()%>/billPay",
				type:"POST",
				dataType:"text",
				data:
				{
				'cardNumber':cardNumber.value,
				'cardPin':cardPin.value,
				'amount':amount.value,
				'billNumber':billNumber.value

				},
				success:function(data){
					console.log(data);
					if(data=="success"){
						bootbox.alert("<br><B>This payment is completed",function(){
							window.location.href="userPayment"
						});
						return false;
					}
					else if(data=="frozen"){
						bootbox.alert("<br><B>Your card is frozen,you are not allowed to do this operation<br>Please change another card.");
						return false;
					}
					else if(data=="nobalance"){
						bootbox.alert("<br><B>Balance of this card is not enough <br>Please change another card.");
						return false;
					}
					else if(data=="locked"){
						bootbox.alert("<br><B>Your card is locked, you are not allowed to do this operation<br>Please change another card.");
						return false;
					}
					else if(data=="shouldLocked"){
						bootbox.alert("<br><B>You have input wrong card pin for 3 times, your card is locked. Please contact administrator to unlock");
						return false;
					}
					else if(data=="wrong"){
						bootbox.alert("<br><B>Your card pin is wrong, if you input incorrectly for more than 3 times within 1 hour, the card will be locked");
						return false;
					}
					else{
						bootbox.alert("<br><B>Unkown reason");
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
</body>
</html>