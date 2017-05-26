<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
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
	<link href="static/css/bootstrap-datetimepicker.css" rel="stylesheet">
	<script src="static/assets/js/custom.js"></script>
  	<script type="text/javascript" src="static/js/moment-with-locales.js"></script>  
   	<script type="text/javascript" src="static/js/bootstrap-datetimepicker.js"></script>  
	<script src='static/js/bootbox.min.js'></script>




<script type="text/javascript">
    $(function () {
        $('#datetimepicker6').datetimepicker({
        	viewMode:'days',
        	format: 'YYYY-MM-DD'
        });
        $('#datetimepicker7').datetimepicker({
        	viewMode:'days',
        	format: 'YYYY-MM-DD'
        });
        $("#datetimepicker6").on("dp.change",function (e) {
        	
            $('#datetimepicker7').data("DateTimePicker").minDate(e.date);
        });
        $("#datetimepicker7").on("dp.change",function (e) {
            $('#datetimepicker6').data("DateTimePicker").maxDate(e.date);
        });
       
    });
    
</script>
</head>
<body>
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
                    	<a href="userForeignExchange" class="hvr-sweep-to-right"><i class="fa fa-usd"></i> Foreign Exchange</a>      
                    </li> 
                    <li>
                    	<a href="userPayment" class="active-menu"><i class="fa fa-paypal"></i> Payment Service</a>     
                    </li>             
                </ul>

            </div>

        </nav>
		<div id="page-wrapper">
        <!-- /. NAV SIDE  -->
		  <div id="page-inner">
			  <div class="row">
                	 <div class="col-md-1"></div>
                    <div class="col-md-10">
                         <div class="panel panel-info">
                        <div class="panel-heading" style="background-color:#7BCEC4">
                          <label>pay for others</label>
                        </div>
                        <div class="panel-body">
                            <form id="form" method="post" action="billPayConfirm" onSubmit="return billIsMatch()">
                            			<div class="form-group">
                                             <label  >Please input the bill number</label>
                                              <input  id="billNumber" name="billNumber" type="text" class="form-control" required="required" >
                                           
                                        </div>
                          
                                 		<div  class="form-group">
                              
		                                   <label >Please input the owner of the bill</label>
		                                
		                                   <input  id="billUserName" name="billUserName" class="form-control" type="text" required="required">
		                             	
                                 		</div>
                                 		<br>
                                      <div class="row" style="margin-top:10px">
                                      	<div class="col-md-4 col-lg-4" ></div>
                                    	<button type="submit" class="btn btn-info col-md-4 col-lg-4">Confirm</button>
                                    	<div class="col-md-4 col-lg-4" ></div>
									</div>
                             </form>
                         </div>
                        </div>
                        <!-- /. ROW  -->
                        
                       

                    </div>
                  
                    <!--/.Chat Panel End-->
                </div>
                
                
                </div>
        <!-- /. PAGE WRAPPER  -->
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
 	function billIsMatch(){
 		var billNumber=document.getElementById("billNumber");
 		var billUserName=document.getElementById("billUserName");
 		var form=document.getElementById("form");
 		$.ajax({
			url:"<%=request.getContextPath()%>/billIsMatch",
			type:"GET",
			dataType:"text",
			data:
			{
			
			'billNumber':billNumber.value,
			'billUserName':billUserName.value,
			},
			success:function(data){
				console.log(data);
				if(data=="match"){
					form.submit();
					return true;
				}
				else{
					bootbox.alert("<br><B>The bill Number and the name of the owner is not matched,<br>please check it again.")
					return false;
				}
				},
				error:function(){},
		});
		return false;
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