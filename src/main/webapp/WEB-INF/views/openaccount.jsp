<%@ page session="true" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Open Account</title>
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
          <h1>Account</h1>
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
            <li><a href="toopenaccount" class="active"><i class="fa fa-user fa-fw"></i>Open Account</a></li>
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
                <li><a href="logout">Sign out</a></li>
              </ul>
            </nav>
          </div>
        </div>
        <div class="templatemo-content-container">
          <div class="templatemo-content-widget white-bg">
            <h2 class="margin-bottom-10">Open Account</h2><br />
            <form action="createaccount" class="templatemo-login-form" method="post" onsubmit="return checkSubmit()">
              <div class="row form-group">
			    <div class="col-lg-3 col-md-3 form-group"></div> 
                <div class="col-lg-6 col-md-6 form-group">                  
                    <label for="inputFirstName">Customer Name</label>
                    <input name="username" type="text" class="form-control" id="inputFirstName" required="required">                  
                </div>
				<div class="col-lg-3 col-md-3 form-group"></div> 
				</div>
				
			  <div class="row form-group">
				<div class="col-lg-3 col-md-3 form-group"></div> 
				<div class="col-lg-6 col-md-6 form-group">                  
                    <label for="inputUsername">Customer ID Number</label>
                    <input id="userid" name="userid" type="text" class="form-control" required="required" onchange="checkId()" maxlength="18">                  
                </div>
				<div class="col-lg-3 col-md-3 form-group">
					
				</div> 
              </div>
			  
			  <div class="row ">
			   <div class="col-lg-3 col-md-3 ">
			   </div>
					<div class="col-lg-6 col-md-6 ">
						<p id="idInfo" style="color:red;"></p>
					</div> 
			   </div>
			  
              <div class="row form-group">
			    <div class="col-lg-3 col-md-3 form-group"></div> 
                <div class="col-lg-6 col-md-6 form-group">                  
                    <label for="inputNewPassword">Password</label>
                    <input id="password" name="cardpin" type="password" class="form-control" required="required" onchange="checkCardpin()" maxlength="6">
                </div>
				
			  </div>
			   <div class="row ">
			   <div class="col-lg-3 col-md-3">
			   </div>
					<div class="col-lg-6 col-md-6">
						<p id="PassInfo" style="color:red;"></p>
					</div> 
			   </div>
			   
			  <div class="row form-group">
				<div class="col-lg-3 col-md-3 form-group"></div> 
                <div class="col-lg-6 col-md-6 form-group">                  
                    <label for="inputConfirmNewPassword">Confirm Password</label>
                    <input id="confirm" name="confirm" type="password" class="form-control" required="required" onchange="checkPassword()" maxlength="6">
                </div> 
				<div class="col-lg-3 col-md-3 form-group">
					
				</div> 
              </div>
			  
			   <div class="row">
			   <div class="col-lg-3 col-md-3">
			   </div>
					<div class="col-lg-6 col-md-6">
						<p id="ConfirmInfo" style="color:red;"></p>
					</div> 
			   </div>
			  
              <div class="form-group text-center">
                <button type="submit" class="templatemo-blue-button">Submit</button>
                <button type="reset" class="templatemo-white-button">Reset</button>
              </div>                           
            </form>
          </div>
          <footer class="text-right">
          </footer>
        </div>
      </div>
    </div>

    <!-- JS -->
    <script type="text/javascript" src="static/js/jquery-1.11.2.min.js"></script>        <!-- jQuery -->
    <script type="text/javascript" src="static/js/bootstrap-filestyle.min.js"></script>  <!-- http://markusslima.github.io/bootstrap-filestyle/ -->
    <script type="text/javascript" src="static/js/templatemo-script.js"></script>        <!-- Templatemo Script -->
  	<script>
  	 function checkId(){
     	var userId=document.getElementById("userid");
     	var reg=new RegExp("[0-9]{18}|([0-9]{17}X)");
     	if(!reg.test(userId.value)){
     		$('#idInfo').text("*Please input 18 digits ID card number and check the form."); 
     		return false;
     	}
     	else{    		
     		$('#idInfo').text("");
     		return true;
         }
     }
  	</script>
  	<script>
  		function checkPassword(){
  			var password=document.getElementById("password");
  			var confirm=document.getElementById("confirm");
  			if(password.value!=confirm.value){
  				$('#ConfirmInfo').text("*Please input the same password.");
  				return false;
  			}
  			else{    		
  				$('#ConfirmInfo').text("");
  				return true;
  	         }
  		}
  	</script>
  	<script>
  		function checkCardpin(){
  			var password=document.getElementById("password");
  			var reg=new RegExp("[0-9]{6}");
  			if(!reg.test(password.value)){
  	     		$('#PassInfo').text("*Please input 6 digits password."); 
  	     		return false;
  	     	}
  	     	else{    		
  	     		$('#PassInfo').text("");
  	     		return true;
  	         }
  		}
  	</script>
  	<script>
  		function checkSubmit(){
  			if(checkId()&&checkPassword()&&checkCardpin()){
  				return true;
  			}
  			else{
  				return false;
  			}
  		}
  	</script>
  </body>
</html>
