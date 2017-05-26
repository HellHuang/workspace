<!DOCTYPE HTML>
<html>
<head>
<title>userLogin</title>
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
	
	 <link href="static/css/aa.css" rel="stylesheet">
 <link href="static/css/nav2.css" rel="stylesheet">
	 <link href="static/css/animate.css" rel="stylesheet" type="text/css" media="all">
	<script src="static/js/wow.min.js"></script>
	<script>
		 new WOW().init();
	</script>
</head>

<body>
<!--banner start here-->
<div class="banner">
  <div class="header">
	<div class="container">
		 <div class="header-main" style="margin-left:15px">
				<div class="logo">
					<h1>HD Bank</h1>
				</div>				   
				<div class="header-right">
					<div class="head-top">
					<div class="top-nav-right">
						<br>
						<br>
						<div id="loginContainer">
							<a href="#" id="loginButton"><span>Login</span></a>
							   <div id="loginBox">                
                                    
                                   <form id="loginForm" method="post" class="form-horizontal" onsubmit="return checkLogin()">
                                     
                                         <fieldset>
	                                        <div class="col-md-4 col-lg-4"></div>
	                                        <div class="col-md-8 col-lg-8"><h2> <a>Login </a></h2></div>
	                                       	 	<p class="col-md-1 col-lg-1"></p>
	                                       		<p class="col-md-10 col-lg-10" id="idInfo" style="color:red;margin-top:0px;font-size:small"></p> 
	                                       		<p class="col-md-1 col-lg-1"></p> 
                                        </fieldset>
                                        <fieldset>
                           
                                        <h2> </h2>
                                        <div class="form-group">
                                            <div class="col-md-1 col-lg-1"></div>
                                            <a class="col-md-3 col-lg-3 control-label">User Id</a>
                                            <div class="col-md-7 col-lg-7">
                                                <input id="userId" type="text" class="form-control" name="userId" onchange="checkId()" required="required" maxlength="18"/>												
                                            </div>
                                            <div class="col-md-1 col-lg-1"></div>
                                        </div>
                                
                                        <div class="form-group">
                                            <div class="col-md-1 col-lg-1"></div>
                                            <a class="col-md-3 col-lg-3 control-label">Password</a>
                                            <div class="col-md-7 col-lg-7">
                                                <input id="userPassword" type="password" class="form-control" name="userPassword" required="required"/>
                                             </div>
                                              <div class="col-md-1 col-lg-1"></div>
                                        </div>
                                        </fieldset>



                                        <div class="form-group">
                                            <div class="col-md-1 col-lg-1"></div>
                                            <div class="col-md-10 col-lg-10 " >
                                                <button type="submit" class="btn btn-primary col-md-12 col-lg-12 hvr-sweep-to-right">Login</button>
                                                <br>
                                                <a class="btn btn-primary col-md-12 col-lg-12 hvr-sweep-to-right " style="margin-top:15px" href="userRegister">register</a>
                                            <div class="col-md-1 col-lg-1"></div>  
                                                
                                 
                                            </div>
                                            <div class="col-md-1 col-lg-1"></div>

                                        <div class="col-md-6 col-lg-6"> <a href="userForgetPass"> forget password? </a></div>
                                        
                                    </div>
                                    </form>


                            </div>
					</div>
				   </div>
				   
				   <div class="ph-numb">  
				   		<div id="loginContainer"><a href="userRegister" id="loginButton"><span>Register</span></a>							    
					</div>
				 </div>
				 </div>
		    </div>
		 <div class="clearfix"> </div>
	  </div>
		 <div class="container " style="width:100%;">

		 	<div id="wrapper" >
            <div id="page-inner" >
              
                <!-- /. ROW  -->
                
                <!-- /. ROW  -->

                <div class="row">
                    <div class="col-md-12">
								<div class="carousel fade-carousel slide" data-ride="carousel" data-interval="2500" id="bs-carousel"  style="width:100%;height:362px">
									  <!-- Overlay -->
									  <div class="overlay"></div>
									
									  <!-- Indicators -->
									  <ol class="carousel-indicators">
									    <li data-target="#bs-carousel" data-slide-to="0" class="active"></li>
									    <li data-target="#bs-carousel" data-slide-to="1"></li>
									    <li data-target="#bs-carousel" data-slide-to="2"></li>
									    <li data-target="#bs-carousel" data-slide-to="3"></li>
									     <li data-target="#bs-carousel" data-slide-to="4"></li>
									  </ol>
									  
									  <!-- Wrapper for slides -->
									  <div class="carousel-inner"  style="width:100%;height:365px">
									    <div class="item slides active" >
									      <div class="slide-1" style="width:100%;height:362px"></div>
									      <div class="hero">
									        <hgroup>
									            <h1>ACCESS ACCOUNTS</h1>      
									            <h3 style="color:black">Check all activities whenever you want</h3>
									            <br>
									        </hgroup>
									        <button class="btn btn-hero btn-lg" role="button">See all features</button>
									      </div>
									    </div>
									    <div class="item slides" >
									      <div class="slide-2" style="width:100%;height:362px"></div>
									      <div class="hero" >        
									        <hgroup>
									            <h1>MANAGE FUNDS</h1>        
									            <h3 style="color:black">Get access to a range of sophisticated investments </h3>
									            <br> 
									        </hgroup>       
									        <button class="btn btn-hero btn-lg" role="button">See all features</button>
									      </div>
									    </div>
									    <div class="item slides" >
									      <div class="slide-3" style="width:100%;height:362px"></div>
									      <div class="hero">        
									        <hgroup>
									            <h1>TERM DEPOSITS</h1>        
									            <h3 style="color:black">Enjoy a fixed rate of return for the nominated term
									            </h3>
									            <br>
									        </hgroup>
									        <button class="btn btn-hero btn-lg" role="button">See all features</button>
									      </div>
									    </div>
									      <div class="item slides" >
									      <div class="slide-4" style="width:100%;height:362px"></div>
									      <div class="hero">        
									        <hgroup>
									            <h1>PAYMENT SERVICES</h1>        
									            <h3 style="color:black">Enjoy an easy way to payment
									            </h3>
									            <br>
									        </hgroup>
									        <button class="btn btn-hero btn-lg" role="button">See all features</button>
									      </div>
									    </div>
									      <div class="item slides" >
									      <div class="slide-5" style="width:100%;height:362px"></div>
									      <div class="hero">        
									        <hgroup>
									            <h1>FOREIGN EXCHANGE</h1>        
									            <h3 style="color:black">Benefit from favorable exchange rate movements
									            </h3>
									            <br>
									        </hgroup>
									        <button class="btn btn-hero btn-lg" role="button">See all features</button>
									      </div>
									    </div>
									  </div> 
									</div>
                            </div>
                  
                    <!--/.Chat Panel End-->
                </div>
                <!-- /. ROW  -->


                <!--/.Row-->
        
               
                <!--/.ROW-->

            </div>
            <!-- /. PAGE INNER  -->
       
        <!-- /. PAGE WRAPPER  -->
    </div>

		 </div>
	 </div>
 </div>
 
</div>

<!-- info start-->
	<div class="col-md-12 " style="background-color:rgba(128, 128, 128, 0.13);padding-bottom:30px">
	<div class="row wow bounceIn animated" data-wow-delay=".5s" style="visibility: visible; -webkit-animation-delay: .5s;padding-bottom: 30px;">
        <div class="col-lg-5 col-md-5 col-sm-8 col-xs-9 bhoechie-tab-container"  style="width : 910px;">
            <h3 class="title title1">Meet Our <span style="font-size:1.8em">Services</span></h3>
            <div class="col-lg-3 col-md-3 col-sm-3 col-xs-3 bhoechie-tab-menu">
              <div class="list-group">
                <a href="#" class="list-group-item active text-center">
                  <h4 class="glyphicon glyphicon-credit-card"></h4><br/>Accounts
                </a>
                <a href="#" class="list-group-item text-center">
                  <h4 class="glyphicon glyphicon-calendar"></h4><br/>Term Deposit
                </a>
                <a href="#" class="list-group-item text-center">
                  <h4 class="glyphicon glyphicon-home"></h4><br/>Funds
                </a>
                <a href="#" class="list-group-item text-center">
                  <h4 class="glyphicon glyphicon-usd"></h4><br/>Foreign Exchange
                </a>
                <a href="#" class="list-group-item text-center">
                  <h4 class="glyphicon glyphicon-list-alt "></h4><br/>Payment Service
                </a>
              </div>
            </div>
            <div class="col-lg-9 col-md-9 col-sm-9 col-xs-9 bhoechie-tab" >
                <!-- flight section -->
                <div class="bhoechie-tab-content active">
                
                <div class=" col-md-6  wow bounceIn animated" data-wow-delay=".3s" style="visibility: visible; -webkit-animation-delay: .3s;" >
                		<br>
                		 <br>
                      <h1 class="glyphicon glyphicon glyphicon-search" style="font-size:10em;color:rgb(22, 160, 133)"></h1>
                      <h2 style="margin-top: 0;color:rgba(0, 0, 0, 0.63)">Everyday Account Smart Access</h2>
                      <h5 style="margin-top: 0;color:rgba(0, 0, 0, 0.63)">Use our app to enquire balance and transactions cash without a card</h5>
                 </div>
                 
                   <div class=" col-md-6  wow bounceIn animated" data-wow-delay=".8s" style="visibility: visible; -webkit-animation-delay: .8s;" >
                 		<br>
                		 <br>
                 	<h1 class="glyphicon glyphicon glyphicon-transfer" style="font-size:10em;color:rgb(22, 160, 133)"></h1>
                      <h2 style="margin-top: 0;color:rgba(0, 0, 0, 0.63)">Smart Transferring</h2>
                      <h5 style="margin-top: 0;color:rgba(0, 0, 0, 0.63)">Use our app to withdraw cash without a card and more</h5>
                </div>
                </div>
                <!-- train section -->
                <div class="bhoechie-tab-content" >
                	<div class=" col-md-6  wow bounceIn animated" data-wow-delay=".3s" style="visibility: visible; -webkit-animation-delay: .3s;" >
                		 <br>
                		 <br>
                		 <h1 class="glyphicon glyphicon-calendar" style="font-size:10em;color:rgb(22, 160, 133)"></h1>
                     	 <h2 style="margin-top: 0;color:rgba(0, 0, 0, 0.63)">Choose a term that suits you</h2>
                      	 <h5 style="margin-top: 0;color:rgba(0, 0, 0, 0.63)">You can choose a term between 3 months to five years.</h5>
                	</div>
                    <div class=" col-md-6  wow bounceIn animated" data-wow-delay=".8s" style="visibility: visible; -webkit-animation-delay: .8s;" >
                    	<br>
                    	<br>
                      <h1 ><img class="image" src="static/images/term.png" style="width:166px;height:140px" ></h1>
                      <h2 style="margin-top: 0;color:rgba(0, 0, 0, 0.63)">Get a fixed rate for a nominated term</h2>
                      <h5 style="margin-top: 0;color:rgba(0, 0, 0, 0.63)">With a term deposit, you will have fixed rate of return for the nominated term. </h5>
                    </div>
                </div>
    
                <!-- hotel search -->
                <div class="bhoechie-tab-content" style="z-index:-1; position : absolute;">
                    <div class=" col-md-6  wow bounceIn animated" data-wow-delay=".3s" style="visibility: visible; -webkit-animation-delay: .3s;" >
                		 <br>
                		 <br>
                		 <h1 class="glyphicon glyphicon-refresh" style="font-size:10em;color:rgb(22, 160, 133)"></h1>
                     	 <h2 style="margin-top: 0;color:rgba(0, 0, 0, 0.63)">RealTime Data</h2>
                      	 <h5 style="margin-top: 0;color:rgba(0, 0, 0, 0.63)">Get access to fund realtime data.</h5>
                	</div>
                    <div class=" col-md-6  wow bounceIn animated" data-wow-delay=".8s" style="visibility: visible; -webkit-animation-delay: .8s;" >
                    	<br>
                    	<br>
                    	
                      <h1 class="glyphicon glyphicon-stats" style="font-size:10em;color:rgb(22, 160, 133)"></h1>
                      <h2 style="margin-top: 0;color:rgba(0, 0, 0, 0.63)">Manage your funds</h2>
                      <h5 style="margin-top: 0;color:rgba(0, 0, 0, 0.63)">Get access to a range of funds, including fund details,fund visualized data. </h5>
                    </div>
                </div>
                <div class="bhoechie-tab-content" >
                    <div class=" col-md-6  wow bounceIn animated" data-wow-delay=".3s" style="visibility: visible; -webkit-animation-delay: .3s;" >
                		 <br>
                		 <br>
                		 <h1 class="glyphicon glyphicon-refresh" style="font-size:10em;color:rgb(22, 160, 133)"></h1>
                     	 <h2 style="margin-top: 0;color:rgba(0, 0, 0, 0.63)">RealTime Data</h2>
                      	 <h5 style="margin-top: 0;color:rgba(0, 0, 0, 0.63)">Get access to real-time foreign currency  rate,Enjoy competitive exchange rates</h5>
                	</div>
                    <div class=" col-md-6  wow bounceIn animated" data-wow-delay=".8s" style="visibility: visible; -webkit-animation-delay: .8s;" >
                    	<br>
                    	<br>
                    	
                      <h1 class="glyphicon glyphicon-tags" style="font-size:10em;color:rgb(22, 160, 133)"></h1>
                      <h2 style="margin-top: 0;color:rgba(0, 0, 0, 0.63)">Manage foreign currency</h2>
                      <h5 style="margin-top: 0;color:rgba(0, 0, 0, 0.63)">Make and receive payments in foreign currency and manage exchange risk.</h5>
                    </div>
                </div>
                <div class="bhoechie-tab-content" >
                    <div class=" col-md-6  wow bounceIn animated" data-wow-delay=".3s" style="visibility: visible; -webkit-animation-delay: .3s;">
                		 <br>
                		 <br>
                		 <h1 class="glyphicon glyphicon glyphicon-list-alt" style="font-size:10em;color:rgb(22, 160, 133)"></h1>
                     	 <h2 style="margin-top: 0;color:rgba(0, 0, 0, 0.63)">Payment Services</h2>
                      	 <h5 style="margin-top: 0;color:rgba(0, 0, 0, 0.63)">A better customer experience by offering a super-fast electronic payment</h5>
                	</div>
                    <div class=" col-md-6  wow bounceIn animated" data-wow-delay=".8s" style="visibility: visible; -webkit-animation-delay: .8s;" >
                    	<br>
                    	<br>
                      <h1 class="glyphicon glyphicon glyphicon-hand-right" style="font-size:10em;color:rgb(22, 160, 133)"></h1>
                      <h2 style="margin-top: 0;color:rgba(0, 0, 0, 0.63)">Pay for others</h2>
                      <h5 class=" wow bounceIn animated" data-wow-delay=".8s" style="visibility: visible; -webkit-animation-delay: .8s;margin-top: 0;color:rgba(0, 0, 0, 0.63)">
                      Electronic payment including water bill,electricity bill,gas bill and so on.
                      </h5>
                    </div>
                </div>
            </div>
        </div>
  </div>
</div>
<script type="text/javascript">
$(document).ready(function() {
    $("div.bhoechie-tab-menu>div.list-group>a").click(function(e) {
        e.preventDefault();
        $(this).siblings('a.active').removeClass("active");
        $(this).addClass("active");
        var index = $(this).index();
        $("div.bhoechie-tab>div.bhoechie-tab-content").removeClass("active");
        $("div.bhoechie-tab>div.bhoechie-tab-content").eq(index).addClass("active");
    });
});

</script>
<!-- info end-->
<!-- team  start-->

<div class="col-md-12 wow bounceIn animated" data-wow-delay=".8s" style="visibility: visible; -webkit-animation-delay: .8s;padding-bottom:0px">
<div class="team  id="team">
		<div class="container wow bounceIn animated" data-wow-delay=".18s" style="visibility: visible; -webkit-animation-delay: .18s;" >

			<h3 class="title title1">Meet Our <span>Team</span></h3>
			<div class="team-info">
				<div class="col-md-3 team-grids wow bounceIn animated" data-wow-delay=".8s" style="visibility: visible; -webkit-animation-delay: .8s;">
					<ul class=" social-networks square spin-icon team-icons">
						
						<h3>Product Manager</h3>
						<br>
					</ul>
					<a href="#">
						<img class="img-responsive" src="static/images/img11.jpg" alt="" />
						<div class="captn">
							<h4>Winton Liang</h4>
							<p>inspection of time progress,reporting every problem,telling  professional suggestion</p>
						</div>
					</a>
				</div>					
				<div class="col-md-3 team-grids wow bounceIn animated" data-wow-delay=".8s" style="visibility: visible; -webkit-animation-delay: .8s;">
					<ul class="social-networks square spin-icon team-icons">
						<h3>Product Owner</h3>
						<br>
					</ul>
					<a href="#">
						<img class="img-responsive" src="static/images/img10.jpg" alt=""/>
						<div class="captn">
							<h4>Allen Lu</h4>
							<p>Promoting user stories,versions,functions, reporting every problem</p>
						</div>
					</a>
				</div>
				<div class="col-md-3 team-grids wow bounceIn animated" data-wow-delay=".8s" style="visibility: visible; -webkit-animation-delay: .8s;">
					<ul class="social-networks square spin-icon team-icons">
						<h3>Programmer</h3>
						<br>
						
					</ul>
					<a href="#">
						<img class="img-responsive" src="static/images/img9.jpg" alt=""  />
						<div class="captn">
							<h4>Deft Lee</h4>
							<p>Responsible for backend,data gathering,testing</p>
						</div>
					</a>
				</div>
				<div class="col-md-3 team-grids wow bounceIn animated" data-wow-delay=".8s" style="visibility: visible; -webkit-animation-delay: .8s;">
					<ul class="social-networks square spin-icon team-icons">
						<h3>Programmer</h3>
						<br>
					</ul>
					<a href="#">
						<img class="img-responsive" src="static/images/img8.jpg" alt="" />
						<div class="captn">
							<h4>Helen Huang</h4>
							<p>Responsible for frontend,data visualization,database management,testing</p>
						</div>
					</a>
				</div>
				<div class="clearfix"> </div>
			</div>
		</div>
	</div>
<!-- team  end-->
</div>
<script>
	function checkId(){
     	var userId=document.getElementById("userId");
     	var reg=new RegExp("[0-9]{18}|([0-9]{17}X)");
     	if(!reg.test(userId.value)){
     		//inquireUser
     		//$('#cardInfo').text("Please input 18 digits ID card number.");
     		$('#idInfo').text("*Please input 18 digits ID card number and check form.");    		
     	}
     	else{
     		$('#idInfo').text("");
     		}
     	}
</script>  
<script>
function checkLogin(){
	var userId=document.getElementById("userId");
	var userPassword=document.getElementById("userPassword");
	$.ajax({
		url:"<%=request.getContextPath()%>/userLoginC",
		type:"POST",
		dataType:"text",
		data:
		{
		'userId':userId.value,
		'userPassword':userPassword.value,
		},
		success:function(data){
			console.log(data);
			if(data=="notExisted"){
				bootbox.alert("<br><B>The user is not existed",function (){
					window.location.href="userLogin";});
				return false;
			}
			if(data=="success"){
				window.location.href="userMainpage";
				return false;
			}
			else if(data=="notActivated"){
				bootbox.alert("<br><B>This id is not activated, please activate first",function (){
					window.location.href="userLogin";});
				return false;
			}
			else if(data=="wrongPin"){
				bootbox.alert("<br><B>User name or password is wrong",function (){
					window.location.href="userLogin";});
				return false;
			}
			else{
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
			<p style="color:black">2016 HD Banks . All rights reserved | Design by  <a  target="_blank">  Deft Lee & Helen Huang</a></p>
		</div>
<!--footer end here-->

	<script type="text/javascript" src="static/js/move-top.js"></script>
		<script type="text/javascript" src="static/js/easing.js"></script>	
		<script type="text/javascript">
				jQuery(document).ready(function($) {
					$(".scroll").click(function(event){		
						event.preventDefault();
						$('html,body').animate({scrollTop:$(this.hash).offset().top},1000);
					});
				});
		</script>
		<!--//end-smoth-scrolling-->
		<!--smooth-scrolling-of-move-up-->
		<script type="text/javascript">
			$(document).ready(function() {
				/*
				var defaults = {
					containerID: 'toTop', // fading element id
					containerHoverID: 'toTopHover', // fading element hover id
					scrollSpeed: 1200,
					easingType: 'linear' 
				};
				*/
				
				$().UItoTop({ easingType: 'easeOutQuart' });
				
			});
		</script>
		<a href="#" id="toTop" style="display: block;"> <span id="toTopHover" style="opacity: 1;"> </span></a>

</body>
</html>