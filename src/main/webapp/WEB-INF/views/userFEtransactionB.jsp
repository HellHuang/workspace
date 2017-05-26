<%@ page session="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<html>
<head>
<title>FE TransactionB</title>
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
                        <a href="userDeposit" class="hvr-sweep-to-right"><i class="fa fa-jpy"></i> Time Deposit</a>                       
                    </li> 
                    <li>
                        <a href="userForeignExchange" class="active-menu"><i class="fa fa-usd"></i> Foreign Exchange</a>                       
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
                        <h1 class="page-head-line" style="text-align:center">FOREIGN EXCHANGE</h1>
                        

                    </div>
                </div>
                <!-- /. ROW  -->
                
                <!-- /. ROW  -->

                <div class="row">
                	<div class="col-md-1"></div>
                    <div class="col-md-10">
                        <div class="row">
                        <form method="post" action="userFEConfirmB">
							<div class="panel panel-info">
								<div class="panel-heading">FOREIGN CURRENCY TO SELL</div>
								<div class="panel-body">
									<div class="form-group">
										<label>Card Number: ${cardNumber}</label>
										<input type="hidden" value=${cardNumber} name="cardNumber" id="cardNumber">
									</div>
									<div class="form-group">
										<label>Foreign Currency Balance: </label>
										<label>${currencyInfo1}</label>&nbsp&nbsp
										<label>${currencyInfo2}</label>&nbsp&nbsp
										<label>${currencyInfo3}</label>&nbsp&nbsp
										<label>${currencyInfo4}</label>&nbsp&nbsp
										<label>${currencyInfo5}</label>
									<div class="form-group">
										<select required="required" class="form-control" id="select" name="select">
											<option value="">Please Select Currency Type</option>
											<c:forEach var="x" items="${type}" varStatus="status"> 
											<option value="${x}">${x}</option>
											<!-- <option value="USD">USD</option>
											<option value="JPY">JPY</option>
											<option value="HKD">HKD</option>
											<option value="GBP">GBP</option>
											<option value="AUD">AUD</option> -->
											</c:forEach>
										</select>
										</br>
										<label id="rate"></label>
										<input type="hidden" name="price" id="price">
									</div>
									<div class="form-group">
										<label>Amount To Exchange</label>
										<input class="form-control" type="number" min="0" required="required" id="amount" name="amount">
										</br>
										<label id="Sincome"></label>
										<input type="hidden" name="income" id="income">
									</div>
									<div class="form-group">
										<div class="col-md-2"></div>
										<div class="col-md-3">
											<input class="btn btn-info" type="button" value="Return" onclick="tiao()">
										</div>
										<div class="col-md-2"></div>
										<div class="col-md-3">
											<input class="btn btn-info" type="submit" value="Next">
										</div>
										<div class="col-md-2"></div>
									</div>
								</div>
							</div>
							</form>
                        </div>
                        <!-- /. ROW  -->
                        <hr />
                    </div>
                    <div class="col-md-1"></div>
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
 	function tiao(){
 		window.location.href="userForeignExchange";
 	}
 </script>
 <script>
 	var currencyType=document.getElementById("select");
 	currencyType.onchange=function info(){
 		$('#rate').empty();
		$('#price').empty();
 		var currencyType=document.getElementById("select");
 		$.ajax({
 			url:"<%=request.getContextPath()%>/userFEtransactionB",
 			type:"GET",
 			dataType:"json",
 			data:{
 				'currencyType':currencyType.value,
 			},
 			success:function(data){
 				console.log(data);
 				var Sinfo=data.Sinfo;
 				var rate=data.rate;
 				$('#rate').text(Sinfo);
 				$('#price').val(rate);
 				$('#Sincome').text("");
 			},
 			error:function(){}
 		});
 	}
 	
 </script>
 
 <script>
 	var amount=document.getElementById("amount");
 	amount.onchange=function income(){
 		$('#Sincome').empty();
 		var amount=document.getElementById("amount");
 		var price=document.getElementById("price");
 		var currencyType=document.getElementById("select");
 		var cardNumber=document.getElementById("cardNumber");
 		$.ajax({
 			url:"<%=request.getContextPath()%>/userFEtransactionBincome",
 			type:"GET",
 			dataType:"json",
 			data:{
 				'amount':amount.value,
 				'price':price.value,
 				'currencyType':currencyType.value,
 				'cardNumber':cardNumber.value,
 			},
 			success:function(data){
 				console.log(data);
 				var Sincome=data.Sincome;
 				var income=data.income;
 				$('#Sincome').text(Sincome);
 				$('#income').val(income);
 			},
 			error:function(){}
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