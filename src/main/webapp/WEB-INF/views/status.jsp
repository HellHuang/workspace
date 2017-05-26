<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">  
    <title>Status</title>
    <meta name="description" content="">
    <meta name="author" content="templatemo">
    
    <link href="static/css/font-face.css" rel="stylesheet">
    <link href="static/css/font-awesome.min.css" rel="stylesheet">
    <link href="static/css/bootstrap.min.css" rel="stylesheet">
    <link href="static/css/templatemo-style.css" rel="stylesheet">
    
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

  </head>
  <body>  
    <!-- Left column -->
    <div class="templatemo-flex-row">
      <div class="templatemo-sidebar">
        <header class="templatemo-site-header">
         
          <h1>Status</h1>
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
            <li><a href="toinquire" class="active"><i class="fa fa-search fa-fw"></i>Inquiry</a></li>
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
        <div class="templatemo-content-container">
          <div class="templatemo-content-widget no-padding">
            <div class="panel panel-default table-responsive">
             <c:forEach var="x" items="${arr}" varStatus="status"> 
              <table class="table table-striped table-bordered templatemo-user-table">
				<thead>
				  <tr>
				 
					<td colspan="2"><h2 style="text-align: center">Card Information</h2></td>
				  </tr>
				</thead>
                <tbody>				
				
        <tr> 
          <td>Card Number</td>
          <td>${x.cardNumber}</td> 
        </tr> 
        <tr> 
          <td>Customer name</td>
          <td>${x.userName}</td> 
        </tr> 
        <tr>
             <td>Balance</td>
             <td> 	CNY ${x.balance}</td>
         </tr>  
       	<tr>                 	
              <td>Status</td>
              <td>  ${x.status}</td>              
        </tr> 
        <tr>                 	
              <td>Foreign Currency</td>
              <td>  ${x.currencyInfo}</td>              
        </tr> 
		<tr>
			<td colspan="2">
				
				<form class="col-md-3 col-lg-3" action="closeCard" method="post">
					<input type="hidden" value=${x.cardNumber} name="cardNumber"/>
					<input type="hidden" value=${x.balance} name="balance"/>
					<button type="submit" value="Close Account" class="templatemo-red-button">Close</button>
				</form>
				<form class="col-md-3 col-lg-3" action="unLock" method="post">
					<input type="hidden" value=${x.cardNumber} name="cardNumber"/>
					<input type="hidden" value=${x.status} name="status"/>
					<button type="submit" value="Close Account" class="templatemo-green-button">Unlock</button>
				</form>
				<form class="col-md-3 col-lg-3" action="frozen" method="post">
					<input type="hidden" value=${x.cardNumber} name="cardNumber"/>
					<input type="hidden" value=${x.status} name="status"/>
					<button type="submit" value="Close Account" class="templatemo-blue-button">Freeze</button>
				</form>
				<form class="col-md-3 col-lg-3" action="Unfreeze" method="post">
					<input type="hidden" value=${x.cardNumber} name="cardNumber"/>
					<input type="hidden" value=${x.status} name="status"/>
					<button type="submit" value="Close Account" class="templatemo-green-button">Unfreeze</button>
				</form>
			</td>
		</tr>
		</c:forEach>			 
 
                  
                </tbody>
              </table>    
            </div>                          
          </div>          
        </div>
      </div>
    </div>
    
    <!-- JS -->
    <script type="text/javascript" src="js/jquery-1.11.2.min.js"></script>      <!-- jQuery -->
    <script type="text/javascript" src="js/templatemo-script.js"></script>      <!-- Templatemo Script -->
    <script>
      $(document).ready(function(){
        // Content widget with background image
        var imageUrl = $('img.content-bg-img').attr('src');
        $('.templatemo-content-img-bg').css('background-image', 'url(' + imageUrl + ')');
        $('img.content-bg-img').hide();        
      });
    </script>
    <script>
    	function check(){
    		return true;
    	}
    </script>
  </body>
</html>