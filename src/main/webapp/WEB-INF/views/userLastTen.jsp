<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page session="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
                        <a href="userEnquiry" class="active-menu"><i class="fa fa-pie-chart"></i> Account Enquiry</a>
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
                         <div class="panel panel-info">
                        <div class="panel-heading" style="background-color:#7BCEC4">
                         ${cardNumber } Last ten transactions
                        </div>
                        <div class="panel-body">
                            <div class="table-responsive">
                              	 <label>&nbsp&nbspRMB Balance:&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
                              	 &nbsp&nbsp&nbsp&nbspCNY ${balance }</label>
                                 <br>
                                   <c:forEach var="cur1" items="${cur}"> 
                                   
                                   <label>&nbsp&nbsp${cur1}</label>
                                   <br>
                                   </c:forEach>
                                 
                                <table class="table table-striped table-hover" id="table">
                                    <thead>
                                        <tr>
                                            
                                            <th>Transaction Date</th>
                                            <th>Transaction Type</th>
                                            <th>Transaction Amount</th>
                                        </tr>
                                    </thead>
                                    
                                    <tbody>
                                    <c:forEach var="x" items="${list}"> 
                                     	<tr class="info">
	                                    	<td><B>${x.transactionDate }</B></td>
	                                    	<td> <B> ${x.transactionType }</B> </td>
	                                    	<td> <B>&nbsp CNY ${x.transactionAmount }</B></td>
                                    	</tr>
                                    </c:forEach>
                                       
                                    </tbody>
                                </table>
                                
                                <form role="form" method="post" action="userHistory">
                                        <div class="form-group">
                                        	
                                        	 <input type="hidden" name="cardNumber" id="cardNumber" value=${cardNumber } >
                                        	 <br>
                                            
                                        </div>
	                                  <button type="submit" class="btn btn-info">History transactions</button>

                                    </form>
                                
                                
                                
                            </div>
                            </div>
                        </div>
                       


                    </div>
                
                </div>
               


          

            </div>
            
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
 window.onload=higtlight();
 function higtlight(){
	 var table = document.getElementById("table"); 
	 var rows = table.rows;
	// alert(rows[1].cells[1].innerHTML);
	 for( var i=1;i<rows.length;i++){
		 var type=rows[i].cells[1].innerText;
		 var amount=rows[i].cells[2].innerText;
		 if( type==("transfer out" )){
			 rows[i].cells[2].innerHTML="<B>-"+amount+"</B>";
			 rows[i].cells[2].style.color="green";
		 }
		 else if(type==("electricity bill payment")){
			 rows[i].cells[2].innerHTML="<B>-"+amount+"</B>";
			 rows[i].cells[2].style.color="green";
		 }
		 else if(type==("gas bill payment")){
			 rows[i].cells[2].innerHTML="<B>-"+amount+"</B>";
			 rows[i].cells[2].style.color="green";
		 }
		 else if(type==("water bill payment")){
			 rows[i].cells[2].innerHTML="<B>-"+amount+"</B>";
			 rows[i].cells[2].style.color="green";
		 }
		 else if(type==("foreign currency buy")){
			 rows[i].cells[2].innerHTML="<B>-"+amount+"</B>";
			 rows[i].cells[2].style.color="green";
		 }
		 else if(type==("withdrawal")){
			 rows[i].cells[2].innerHTML="<B>-"+amount+"</B>";
			 rows[i].cells[2].style.color="green";
		 }
		 else if(type==("fund purchase")){
			 rows[i].cells[2].innerHTML="<B>-"+amount+"</B>";
			 rows[i].cells[2].style.color="green";
		 }
		 else if(type==("time deposit")){
			 rows[i].cells[2].innerHTML="<B>-"+amount+"</B>";
			 rows[i].cells[2].style.color="green";
		 }
				
		 else if( type==("deposit" )){
			 rows[i].cells[2].innerHTML="<B>&nbsp"+amount+"</B>";
			 rows[i].cells[2].style.color="red";
		 }
		else if( type==("foreign currency buy" )){
			rows[i].cells[2].innerHTML="<B>&nbsp"+amount+"</B>";
			rows[i].cells[2].style.color="red";
		 }
		else if( type==("fund redeem" )){
			rows[i].cells[2].innerHTML="<B>&nbsp"+amount+"</B>";
			rows[i].cells[2].style.color="red";
		}
		else if( type==("foreign currency sell" )){
			rows[i].cells[2].innerHTML="<B>&nbsp"+amount+"</B>";
			rows[i].cells[2].style.color="red";
		}
		else if( type==("transfer in" )){
			rows[i].cells[2].innerHTML="<B>&nbsp"+amount+"</B>";
			rows[i].cells[2].style.color="red";
		}	 
		 else{
			 
		 }
		 
		 //var type=document.getElementById("transactionType").value;
		// var amount=document.getElementById("transactionAmount");
		
	 }
	 
	 
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