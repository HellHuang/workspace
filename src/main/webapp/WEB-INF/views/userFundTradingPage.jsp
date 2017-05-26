<%@ page session="true" %>
<%@ page language="java" contentType="text/html; charset=gb2312"
 pageEncoding="gb2312"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<html>
<head>
<title>Fund Trading</title>
	<link href="static/css/bootstrap.css" rel="stylesheet" type="text/css" media="all">
	<script src="static/js/jquery-1.11.0.min.js"></script>
	<link href="static/css/style.css" rel="stylesheet" type="text/css" media="all"/>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
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
                        <a href="FundPage" class="active-menu"><i class="fa fa-money"></i> Fund</a>                       
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
                        <h1 class="page-head-line" style="text-align:center">TRADING CONFIRM</h1>
                        

                    </div>
                </div>
                <!-- /. ROW  -->
                
                <!-- /. ROW  -->

                <div class="row">
                	<div class="col-md-2"></div>
                    <div class="col-md-8">
						<div class="panel-body">
							<form role="form" method="post" onsubmit="return tan()">
								<div class="form-group">
                                	<label>Please select your card account number</label>
                                    <select class="form-control" name="select" id="select">
                                    	<option value="">Please select your card account number</option>
                                        <c:forEach var="x" items="${list}"> 
                                        	<option value=${x.cardNumber}>${x.cardNumber}</option>
                                        </c:forEach>	
                                    </select> 
                                    </br>
                                    <label id="balance"></label>                                        
                                </div>
                                
								<div class="form-group">
									<label>Purchase Amount</label>
									<input type="hidden" value="${nav}" id="nav" name="nav">
									<input type="hidden" value="${name}" id="name" name="name">
									<input type="hidden" value="${type}" id="type" name="type">
									<input type="hidden" value="${code}" id="code" name="code">
									<input type="hidden" value="${rate}" id="rate" name="rate">
									<input id="amount" name="amount" class="form-control" type="number" min=1 required="required">
									<p id="amountInfo" style="color:red;margin-top:30px"></p>
								</div>
								
								<div class="form-group">
                                    <label>Card PIN</label>
                                	<input id="cardpin" name="cardpin" class="form-control" type="password" required="required" maxlength="6">
                                </div>
                                
                                <button type="submit" class="btn btn-info">Submit</button>
							</form>
						</div>	
                    </div>
                    <div class="col-md-2"></div>
                    <!--/.Chat Panel End-->
                </div>
                <!-- /. ROW  -->


                <!--/.Row-->
        
               
                <!--/.ROW-->

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
 	function checkOut(){
 		bootbox.dialog({
 			message:"Do you want to logout?",
 			title:"Notice",
 			buttons:{
 				confirm: {
 				      label: "Confirm",
 				      className: "btn-success",
 				      callback: function() {
 				        window.location.href="userLogout";
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
 	function tan(){
 		var amount=document.getElementById("amount");
 		var cardpin=document.getElementById("cardpin");
 		var nav=document.getElementById("nav");
 		var name=document.getElementById("name");
 		var code=document.getElementById("code");
 		var type=document.getElementById("type");
 		var rate=document.getElementById("rate");
 		var select=document.getElementById("select");
 		 $.ajax({
 			url:"<%=request.getContextPath()%>/FundPurchase",
 			type:"POST",
 			dataType:"text",
 			data:{
 				'amount':amount.value,
 				'cardpin':cardpin.value,
 				'nav':nav.value,
 				'name':name.value,
 				'code':code.value,
 				'type':type.value,
 				'rate':rate.value,
 				'select':select.value
 			},
 			success:function(data){
 				console.log(data);
 				if(data=="noCardNumber"){
 					bootbox.alert("<br><B>Please select your card account number",function (){
						});
					return false;
 				}if(data=="frozen"){
 					bootbox.alert("<br><B>Your card is frozen, cannot purchase fund",function (){
					});
				return false;
				}
 				else if(data=="locked"){
					bootbox.alert("<br><B>Your card is locked, please contact system administrator to unlock",function (){
						});
					return false;
				}
 				else if(data=="shouldLocked"){
					bootbox.alert("<br><B>Sorry you have inputted wrong card pin for 3 times, your card is locked. Please contact system administrator to unlock",function (){
						});
					return false;
				}
 				else if(data=="wrongPin"){
 					bootbox.alert("<br><B>Your card pin is wrong, if you input incorrectly for more than 3 times within 1 hour, the card will be locked",function (){
						});
					return false;
 				}
 				else if(data=="noBalance"){
 					bootbox.alert("<br><B>Your balance is not enough",function (){
						});
					return false;
 				}
 				else if(data=="success"){
 					bootbox.alert("<br><B>Purchase is successful.",function (){
						window.location.href="FundPage";});
					return false;
 				}
 				else{
 					bootbox.alert("<br><B>Failed, please try again",function (){
						});
					return false;
 				}
 				return false;
 			},
 			error:function(){}
 		 });
 		 return false;
 	}
 </script>
 <script>
 	var card=document.getElementById("select");
 	card.onchange=function balance(){ 		
 		$.ajax({
 			url:"<%=request.getContextPath()%>/userFundPurchase",
 			type:"GET",
 			dataType:"text",
 			data:{
 				'card':card.value,
 			},
 			success:function(data){
 				console.log(data);
 				$('#balance').text("Balance: CNY "+data);
 			},
 			error:function(){}
 		})
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