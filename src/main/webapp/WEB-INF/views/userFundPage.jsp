<%@ page session="true" %>
<%@ page language="java" contentType="text/html; charset=gb2312"
 pageEncoding="gb2312"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<html>
<head>
<title>Fund Page</title>
	<link href="static/css/bootstrap.css" rel="stylesheet" type="text/css" media="all">
	<script src="static/js/jquery-1.11.0.min.js"></script>
	<link href="static/css/style.css" rel="stylesheet" type="text/css" media="all"/>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="Content-Type" content="text/html; charset=gb2312"/>
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
                    	<form method="post" action="FundManage">
                        <h1 class="page-head-line" style="text-align:center">FUND TRADING&nbsp&nbsp&nbsp<button type="submit" class="btn btn-lg btn-info">MY Fund</button></h1>                       
                        </form>
                    </div>
                </div>
                <!-- /. ROW  -->
                
                <!-- /. ROW  -->

                <div class="row">
                    <div class="col-md-12">
						<div class="table-responsive">
							<div class="col-md-6">
							</div>
							<form class="col-md-6 form-inline" style="margin-bottom:10px" method="post" action="fundSearch">
							
								<div class="form-group">
								
								    <input name="search" type="text" class=" form-control  input-md " placeholder="please input code or name">
								  	
								    <button type="submit" class=" btn btn-md btn-info"><i class="glyphicon glyphicon-search"> </i></button>
								</div>
   								
								
								

							</form>
							</div>
										
							
                                
							<table class="table table-striped table-bordered table-hover">							
								<thead>
									<tr>
                                        <th>Fund name</th>
                                        <th>Fund code</th>
                                        <th>Fund type</th>
                                        <th>Annualized rate of return</th>
                                        <th>Purchase</th>
									</tr>
								</thead>
								
								<tbody>
								<c:forEach var="x" items="${fundList}"> 
									<tr>
										<form method="post" action="userFundDetail">
										<input type="hidden" value=${currentPage} name="currentPage">
										<input type="hidden" value=${x.code} name="code">
									
                                        <input type="hidden" value=${x.name} name="name">
                                        <input type="hidden" value=${x.type} name="type">
                                        <input type="hidden" value=${x.returnRate} name="rate">
                                        <input type="hidden" value=${x.nav} name="nav">
                                        <td><button type="submit" class="btn btn-success">${x.name}</button></td>
                                        </form>
                                        
                                        <td>${x.code}</td>
                                        <td>${x.type}</td>
                                        <td>${x.returnRate}</td>
                                        
                                        <form action="userFundPurchase" method="post">                                       
                                        <input type="hidden" value=${x.code} name="code">
                                        <input type="hidden" value=${x.name} name="name">
                                        <input type="hidden" value=${x.type} name="type">
                                        <input type="hidden" value=${x.returnRate} name="rate">
                                        <input type="hidden" value=${x.nav} name="nav">
                                        <td><button type="submit" class="btn btn-success">Purchase</button></td>
                                        </form>
                                    </tr>
                                </c:forEach>
								</tbody>
															
							</table>
							<ul class="pagination col-md-10">
                               <%
                               		String Scurrentpage=(String)session.getAttribute("fundCurrentPage"); %>
                              
                               
                               <%;
                                	int currentPage=Integer.parseInt(Scurrentpage);
                               		int pages=10; 	
                                	int prePage;
                                	int nextPage;
                                	if(currentPage==1){
                                		prePage=1;
                                	}
                                	else{
                                		prePage=currentPage-1;
                                	}
                                	if(currentPage==pages){
                                		nextPage=pages;
                                	}
                                	else{
                                		nextPage=currentPage+1;
                                	}
                                %> 
                                 <li><a href="FundPaging?currentPage=<%= prePage%>">&laquo;</a></li>
                                <%
                                	
                                	for(int i=1;i<10+1;i++){%>
                                		
                                	
                                	<li ><a  href="FundPaging?currentPage=<%=i %>"><%=i %></a>
                                
                                <% }%>
                                <li onclick='add(this)'><a href="FundPaging?currentPage=<%= nextPage%>">&raquo;</a></li>
                                
                                </ul>	
						</div>
						 
                    </div>
                   
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
<!--banner start here-->
<!--banner strip here-->
		<div class="copyright">
			<p style="color:black">2016 HD Banks . All rights reserved | Design by  <a  target="_blank">  Deft Lee & Helen Huang</a></p>
		</div>
<!--footer end here-->

</body>
</html>