<%@ page session="true" %>
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
                        <a href="userTransfer"  class="active-menu"><i class="fa fa-exchange"></i> Transfer</a>
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
               
                <!-- /. ROW  -->
                
                <!-- /. ROW  -->

                <div class="row">
                	 <div class="col-md-1"></div>
                    <div class="col-md-10">
                         <div class="panel panel-info">
                        <div class="panel-heading" style="background-color:#7BCEC4" onclick="showPic()">
                          <label>Transfer</label>
                        </div>
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
                                            <label>Transfer amount</label>
                                            <input id="amount" name="amount" class="form-control" type="number" min="1" required="required">
                                     		<p id="amountInfo" style="color:red;margin-top:30px"></p>
                                        </div>
                                        <div class="form-group">
                                            <label>Receiver's account</label>
                                            <input id="receivercard" name="receivercard" id="receivercard" class="form-control" type="text" required="required" maxlength="16" onchange="checkCardNumber()">
                                            <p id="receiverInfo" style="color:red;margin-top:30px"></p>
                                        </div>
                                  		
                                  		<div class="form-group">
                                            <label>Receiver's name</label>
                                            <input id="receivername" name="receivername" class="form-control" type="text" required="required">
                                        </div>

                                        <div class="form-group">
                                            <label>Card PIN</label>
                                            <input id="cardpin" name="cardpin" class="form-control" type="password" required="required" maxlength="6">
                                        </div>

                                 
                                        <button type="submit" class="btn btn-info">Submit</button>

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
function showPic(){
	bootbox.dialog({
		  message: "<img alt='' src='static/images/28.jpg'>",
		  title: "Techonology Detail",
		  
		});
}

</script>

<script>
  	function checkCardNumber(){
     	var cardNumber=document.getElementById("receivercard");
     	var reg=new RegExp("[0-9]{16}");
     	if(!reg.test(cardNumber.value)){
     		$('#receiverInfo').text("* Please input 16 digits card number and check form."); 
     		return false;
     	}
     	else{  
      		 $.ajax({
        			url:"<%=request.getContextPath()%>/CheckCardNumber",
        			type:"POST",
        			dataType:"text",
        			data:
        				{
        				'cardNumber':(cardNumber.value),
        				},
        				success:function(data){
        					console.log(data);
        					if(data=="has"){
        						//$('#cardInfo').text("has carNumber.");
        						$('#receiverInfo').text("");
        						return true;
        					}       					
        					else{
        						$('#receiverInfo').text("* Card number is not existed.");  
        						return false;
        					}
        				},
        				error:function(){	
        				}	
        		});		
      		$('#receiverInfo').text("");
         }
  	}
  </script>
  <script> 
  function tan(){
	  var select=document.getElementById("select");
	  var amount=document.getElementById("amount");
	  var receivercard=document.getElementById("receivercard");
	  var receivername=document.getElementById("receivername");
	  var cardpin=document.getElementById("cardpin");
  $.ajax({
	  url:"<%=request.getContextPath()%>/Transfer",
		type:"POST",
		dataType:"text",
		data:
			{
			'select':select.value,
			'amount':amount.value,
			'receivercard':receivercard.value,
			'receivername':receivername.value,
			'cardpin':cardpin.value,
			},
			success:function(data){
					console.log(data);
					if(data=="noFcardNumber"){
						bootbox.alert("<br><B>Please select your card account number");
						return false;
					}
					else if(data=="Ffrozen"){
						bootbox.alert("<br><B>Your card is frozen, please contact administrator to unfreeze you card");
						return false;
					}
					else if(data=="Rfrozen"){
						bootbox.alert("<br><B>Receiver's card is frozen, you cannot transfer");
						return false;
					}
					else if(data=="locked"){
						bootbox.alert("<br><B>Your card is locked, please contact administrator to unlock");
						return false;
					}
					else if(data=="Rlocked"){
						bootbox.alert("<br><B>Receiver's card is locked, you cannot transfer");
						return false;
					}
					else if(data=="same"){
						bootbox.alert("<br><B>The two cards should not be the same");
						return false;
					}
					else if(data=="shouldLocked"){
						bootbox.alert("<br><B>You have input wrong card pin for 3 times, your card is locked. Please contact administrator to unlock");
						return false;
					}
					else if(data=="wrongCradPin"){
						bootbox.alert("<br><B>Your card pin is wrong, if you input incorrectly for more than 3 times within 1 hour, the card will be locked");
						return false;
					}
					else if(data=="noRcard"){
						bootbox.alert("<br><B>The receiver's card number does not exist");
						return false;
					}
					else if(data=="noRmatch"){
						bootbox.alert("<br><B>The receiver's card number and name are not matched");
						return false;
					}
					else if(data=="noBalance"){
						bootbox.alert("<br><B>Your balance is not enough");
						return false;
					}
					else if(data=="success"){
						bootbox.alert("<br><B>Transfer successful",function (){
							window.location.href="userTransfer";});
						return false;
					}
					else{
						bootbox.alert("<br><B>Transfer failed, please try again");
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
</body>
</html>