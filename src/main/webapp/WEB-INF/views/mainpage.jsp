<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">  
    <title>Main Page</title>
    <meta name="description" content="">
    <meta name="author" content="templatemo">
    
    <!--<link href='http://fonts.useso.com/css?family=Open+Sans:400,300,400italic,700' rel='stylesheet' type='text/css'>
	-->
    <link href="static/css/font-face.css" rel="stylesheet">
	<link href="static/css/font-awesome.min.css" rel="stylesheet">
	
    <link href="static/css/bootstrap.min.css" rel="stylesheet">
    <link href="static/css/templatemo-style.css" rel="stylesheet">
     <script src="http://echarts.baidu.com/build/dist/echarts.js"></script>
    <script type="text/javascript" src="static/js/jquery-1.11.2.min.js"></script> 
    <script type="text/javascript" src="static/js/bootstrap-filestyle.min.js"></script>  
     <script type="text/javascript" src="static/js/templatemo-script.js"></script>  
   	  <script type="text/javascript" src="static/js/draw.js"></script>  


	    <link rel="stylesheet" href="static/css/clndr.css" type="text/css" />
	<script src="static/js/underscore-min.js" type="text/javascript"></script>
	<script src= "static/js/moment-2.2.1.js" type="text/javascript"></script>
	<script src="static/js/clndr.js" type="text/javascript"></script>
	<script src="static/js/site.js" type="text/javascript"></script>
	<link href="static/assets/css/basic.css" rel="stylesheet" />
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

  </head>
  <body >  
    <!-- Left column -->
   
    <div class="templatemo-flex-row ">
      <div class="templatemo-sidebar">
        <header class="templatemo-site-header">
          <h1>Main Page</h1>
        </header>
        <div class="profile-photo-container">
          <img src="static/images/HD.jpg" alt="Profile Photo" class="img-responsive">  
          <div class="profile-photo-overlay"></div>
        </div>      
        <div class="mobile-menu-icon">
            <i class="fa fa-bars"></i>
        </div>
        <nav class="templatemo-left-nav">          
          <ul>
            <li><a href="toopenaccount" ><i class="fa fa-user fa-fw"></i>Open Account</a></li>
            <li><a href="toinquire"><i class="fa fa-search fa-fw"></i>Inquiry</a></li>
            <li><a href="towithdrawal"><i class="fa fa-database fa-fw"></i>Withdrawl</a></li>
            <li><a href="todeposit"><i class="fa fa-database fa-fw"></i>Deposit</a></li>
            <li><a href="tochangeCardPin"><i class="fa fa-sliders fa-fw"></i>Change Card Pin</a></li>
          </ul>  
        </nav>
      </div>
      <!-- Main content --> 
      <div class="templatemo-content col-1 light-gray-bg">
        <div class="templatemo-top-nav-container">
          <div class="row">
            <nav class="templatemo-top-nav col-lg-12 col-md-12">
              <ul class="text-uppercase">
                <li><a href="tomainpage">Dashboard</a></li>
                <li><a href="tologinpage">Sign out</a></li>
              </ul>  
            </nav> 
          </div>
        </div>

        <div class="divider">
  
  <div class="templatemo-content-container">
    <div class="row">
      <div class="col-md-7 col-sm-7">
      	<div style="background-color:rgba(0, 153, 136, 0.61)"class=" divider-wrapper divider-two">
          <i class="fa fa-search"></i>
          <h2>Enquiry</h2>
          <p>Enquire customer's card by idNumber</p>
          <!-- <p>Close Account for customer</p> -->
           <!--<p>Unlock Account for customer</p> -->
        
        </div>
      
        
      </div>

      <div class="col-md-5 col-sm-5">
        <div style="background-color:#F44336"class=" divider-wrapper divider-one" >
          <i class="fa fa-user"></i>
          <h2>Open Account</h2>
        
          <p>Open Account  for  customer</p>
         
        
        </div>

    	<!-- calender -->
    	<!-- 
  	<div class="col-md-7 col-sm-7 divider-wrapper cal1 cal_2 clndr"><div class="clndr-controls"><div class="clndr-control-button"><p class="clndr-previous-button">previous</p></div><div class="month">July 2015</div><div class="clndr-control-button rightalign"><p class="clndr-next-button">next</p></div></div><table class="clndr-table" border="0" cellspacing="0" cellpadding="0"><thead><tr class="header-days"><td class="header-day">S</td><td class="header-day">M</td><td class="header-day">T</td><td class="header-day">W</td><td class="header-day">T</td><td class="header-day">F</td><td class="header-day">S</td></tr></thead><tbody><tr><td class="day adjacent-month last-month calendar-day-2015-06-28"><div class="day-contents">28</div></td><td class="day adjacent-month last-month calendar-day-2015-06-29"><div class="day-contents">29</div></td><td class="day adjacent-month last-month calendar-day-2015-06-30"><div class="day-contents">30</div></td><td class="day calendar-day-2015-07-01"><div class="day-contents">1</div></td><td class="day calendar-day-2015-07-02"><div class="day-contents">2</div></td><td class="day calendar-day-2015-07-03"><div class="day-contents">3</div></td><td class="day calendar-day-2015-07-04"><div class="day-contents">4</div></td></tr><tr><td class="day calendar-day-2015-07-05"><div class="day-contents">5</div></td><td class="day calendar-day-2015-07-06"><div class="day-contents">6</div></td><td class="day calendar-day-2015-07-07"><div class="day-contents">7</div></td><td class="day calendar-day-2015-07-08"><div class="day-contents">8</div></td><td class="day calendar-day-2015-07-09"><div class="day-contents">9</div></td><td class="day calendar-day-2015-07-10"><div class="day-contents">10</div></td><td class="day calendar-day-2015-07-11"><div class="day-contents">11</div></td></tr><tr><td class="day calendar-day-2015-07-12"><div class="day-contents">12</div></td><td class="day calendar-day-2015-07-13"><div class="day-contents">13</div></td><td class="day calendar-day-2015-07-14"><div class="day-contents">14</div></td><td class="day calendar-day-2015-07-15"><div class="day-contents">15</div></td><td class="day calendar-day-2015-07-16"><div class="day-contents">16</div></td><td class="day calendar-day-2015-07-17"><div class="day-contents">17</div></td><td class="day calendar-day-2015-07-18"><div class="day-contents">18</div></td></tr><tr><td class="day calendar-day-2015-07-19"><div class="day-contents">19</div></td><td class="day calendar-day-2015-07-20"><div class="day-contents">20</div></td><td class="day calendar-day-2015-07-21"><div class="day-contents">21</div></td><td class="day calendar-day-2015-07-22"><div class="day-contents">22</div></td><td class="day calendar-day-2015-07-23"><div class="day-contents">23</div></td><td class="day calendar-day-2015-07-24"><div class="day-contents">24</div></td><td class="day calendar-day-2015-07-25"><div class="day-contents">25</div></td></tr><tr><td class="day calendar-day-2015-07-26"><div class="day-contents">26</div></td><td class="day calendar-day-2015-07-27"><div class="day-contents">27</div></td><td class="day calendar-day-2015-07-28"><div class="day-contents">28</div></td><td class="day calendar-day-2015-07-29"><div class="day-contents">29</div></td><td class="day calendar-day-2015-07-30"><div class="day-contents">30</div></td><td class="day calendar-day-2015-07-31"><div class="day-contents">31</div></td><td class="day adjacent-month next-month calendar-day-2015-08-01"><div class="day-contents">1</div></td></tr></tbody></table></div></div>
	 -->

       </div>

        <div class="row">
        
      
         <div class="col-md-4 col-sm-4">
        <div class="divider-wrapper divider-three">
          <i class="fa fa-database"></i>
          <h2>Withdrawal</h2>
          <p>Withdraw money for customer</p>
        </div>
      </div>
      <div class="col-md-4 col-sm-4">
        <div class="divider-wrapper divider-four"style="background-color:rgba(31, 179, 34, 0.83)">
          <i class="fa fa-database"></i>
          <h2>Deposit</h2>
          <p>Deposit money for customer</p>
        </div>
      </div>
        
      
	
		 <div class="col-md-4 col-sm-4">
        
        <div class="divider-wrapper divider-four"style="background-color:rgba(121, 72, 120, 0.55) " href="tochangeCardPin">
          <i class="fa fa-sliders" ></i> 
          <h2>Change cardPin</h2>
          
          <p>Change cardPin for customer</p>
          
          
        </div>
       	
      </div>
     
    </div>

	
</div>



      </div>
    </div>
	</div>
  </body>
  

</html>