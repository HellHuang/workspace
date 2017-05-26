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
                          <label>Deposit</label>
                        </div>
                        <div class="panel-body">
                            <form role="form" id="form" method="post"  action="userDepositConfirm" >
                                        <div class="form-group">
                                            <label>Please select your card account number</label>
                                            <select class="form-control" name="cardNumber" id="cardNumber" required="required">
                                            	<option value="">Please select your card account number</option>
                                            	<c:forEach var="x" items="${list}"> 
                                            		<option value=${x.cardNumber}>${x.cardNumber}</option>
                                            	</c:forEach>	
                                            </select>
                                            <br>
                                    		<label id="balance"></label>  
                                        </div>
                                        
                                         <div class="form-group">
                                            <label>Please select the deposit term</label>
                                            <select class="form-control" name="term" id="term" required="required">
                                            	<option value="">Please select the deposit term</option>
                                            
                                            	<option value="3 months">3 months</option>
                                  				<option value="6 months">6 months</option>
                                  				<option value="1 year">1 year</option>
                                  				<option value="2 years">2 years</option>
                                  				<option value="3 years">3 years</option>
                                  				<option value="5 years">5 years</option>
                                            </select>
                                           
                                        </div>
                                        
                                 		<div class="form-group">
                                            <label>Deposit amount</label>
                                            <input id="amount" name="amount" class="form-control" type="number" min="1" required="required" >
                                     		<p id="amountInfo" type="number" style="color:red;margin-top:30px"></p>
                                        </div>

					                      <button type="submit" class="btn btn-info">Next</button>

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
  function check(){
	  var cardNumber=document.getElementById("cardNumber");
	  var amountObj=document.getElementById("amount");
	  var amount=parseFloat(amountObj.value);
	  
	  var form=document.getElementById("form");
	 alert(amount);
	  $.ajax({
	  	url:"<%=request.getContextPath()%>/hasUserBalance",
		type:"POST",
		dataType:"text",
		data:
			{
			'cardNumber':cardNumber,
			},
			success:function(data){
					console.log(data);
					var balance=parseFloat(data);
					alter(data);
					if(balance<amount){
						 bootbox.dialog({
       						 message: "<br><B><div class='row'> <br><label class='col-sm-8 control-label no-padding-right' > Sorry, you do not have enough money<br></label></div>\
                      			",
					        title: "",
					        buttons:             
					        {
					            "success" :
					             {
					                "label" : "<i class='icon-ok'></i> return",
					                "className" : "btn-sm btn-success",
					                "callback": function() {
					                	window.location.href="userDepositPage";
					                }
					            }
					        }
					    });
						return false;
					}
					else{
						//form.submit();
						return true;
					}
					
				return false;
			},
			error:function(){
				
			}
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
 	var card=document.getElementById("cardNumber");
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
</body>
</html>