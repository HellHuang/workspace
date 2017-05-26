<%@ page session="true" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Change Card Pin</title>
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
          <h1>Change Card Pin</h1>
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
            <li><a href="tochangeCardPin" class="active"><i class="fa fa-sliders fa-fw"></i>Change Card Pin</a></li>
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
            <h2 class="margin-bottom-10">Change Card Pin</h2><br />
            <form action="changePassword" name="changePassword" id="changePassword"  class="templatemo-login-form"  method="post" onsubmit="return checkSubmit()">
              <div class="row form-group">
			    <div class="col-lg-3 col-md-3 form-group"></div> 
                <div class="col-lg-6 col-md-6 form-group">                  
                    <label for="inputFirstName">Card Number</label>
                    <input name="cardNumber" type="text" class="form-control" id="cardNumber" required="required"  maxlength="16" onchange="checkCardnumber()">                  
                	
                </div>
     
				
				</div>
				  <div class="row ">
			   <div class="col-lg-3 col-md-3 ">
			   </div>
					<div class="col-lg-8 col-md-8 ">
						<p id="cardInfo" style="color:red;"></p>
					</div> 
			   </div>
			  <div class="row form-group">
				<div class="col-lg-3 col-md-3 form-group"></div> 
				<div class="col-lg-6 col-md-6 form-group">                  
                    <label for="inputUsername">Customer ID Number</label>
                    <input id="userid" name="userid" type="text" class="form-control" required="required" onchange="checkId()" maxlength="18">                  
                </div>
			
				 
              </div>
			   
			   <div class="row ">
			   <div class="col-lg-3 col-md-3 ">
			   </div>
					<div class="col-lg-9 col-md-9 ">
						<p id="idInfo" style="color:red;"></p>
					</div> 
			   </div>
			   
			   
              <div class="row form-group">
			    <div class="col-lg-3 col-md-3 form-group"></div> 
                <div class="col-lg-6 col-md-6 form-group">                  
                    <label for="inputNewPassword">New Password</label>
                    <input id="password" name="cardpin" type="password" onchange="checkCardpin()" class="form-control" required="required" maxlength="6">
                </div>
			
			  </div>
			  
			   <div class="row ">
			   <div class="col-lg-3 col-md-3 ">
			   </div>
					<div class="col-lg-8 col-md-8 ">
						<p id="PassInfo" style="color:red;"></p>
					</div> 
			   </div>
				
			  <div class="row form-group">
				<div class="col-lg-3 col-md-3 form-group"></div> 
                <div class="col-lg-6 col-md-6 form-group">                  
                    <label for="inputConfirmNewPassword">Confirm New Password</label>
                    <input id="confirm" name="confirm" type="password" onchange="checkPassword()" class="form-control" required="required" maxlength="6">
                </div> 
				
				
              </div>
               <div class="row ">
			   <div class="col-lg-3 col-md-3 ">
			   </div>
					<div class="col-lg-6 col-md-6 ">
						<p id="ConfirmInfo" style="color:red;"></p>
					</div> 
			   </div>
              <div class="row form-group">
				<div class="col-lg-3 col-md-3 form-group"></div> 
                <div class="col-lg-6 col-md-6 form-group">                  
                    <label for="inputAuthenticationCode">Authorization Code</label>
                    <input id="authcode" name="authcode" type="password" class="form-control">
                </div> 
				<div class="col-lg-3 col-md-3 form-group"></div> 
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
  	//jQuery('#changePassword').blur(checkCardnumber);
  	 function checkId(){
     	var userId=document.getElementById("userid");
     	var reg=new RegExp("[0-9]{18}|([0-9]{17}X)");
     	if(!reg.test(userId.value)){
     		//inquireUser
     		//$('#cardInfo').text("Please input 18 digits ID card number.");
     		$('#idInfo').text("*Please input 18 digits ID card number and check form.");    		
     	}
     	else{
     		 $.ajax({
     			url:"<%=request.getContextPath()%>/hasUserId",
     			type:"POST",
     			dataType:"text",
     			data:
     				{
     				'inqUser':(userId.value),
     				},
     				success:function(data){
     					console.log(data);
     					if(data=="no"){
     						$('#idInfo').text("* This user is not existed.");   						
     					}
     					
     					else{
     						$('#idInfo').text("");
     					}
     				},
     				error:function(){
     					
     				}
     				
     		});
     		
         	userId.setCustomValidity("");
         }
     }
 
  		function checkPassword(){
  			var password=document.getElementById("password");
  			var confirm=document.getElementById("confirm");
  			if(password.value!=confirm.value){
  				
  				$('#ConfirmInfo').text("* Please input the same password.");
  				return false;
  			}
  			else{    		
  				$('#ConfirmInfo').text("");
  				return true;
  	         }
  		}
  
  	 function checkCardnumber(){
     	var cardNumber=document.getElementById("cardNumber");
     	var reg=new RegExp("[0-9]{16}");
     	if(!reg.test(cardNumber.value)){
     		$('#cardInfo').text("* Please input 16 digits card number and check form.");    		
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
        						$('#cardInfo').text("");
        					}
        					
        					else{
        						$('#cardInfo').text("* cardNumber is not existed.");
        						 
        						
        					}
        				},
        				error:function(){
        					
        				}
        				
        		});
     		
     		
      		$('#cardInfo').text("");
         }
     	/*var password=document.getElementById("password");
			var confirm=document.getElementById("confirm");
			if(password.value!=confirm.value){
				
				confirm.setCustomValidity("Please input the same password.");
			}
			else{    		
	         	confirm.setCustomValidity("");
	         }*/
     }
  	 
  	 /*function checkMatch(){
  		var cardNumber=document.getElementById("cardNumber");
  		var userId=document.getElementById("userid");
  		$.ajax({
  			url:"<%=request.getContextPath()%>/checkMatch",
			type:"POST",
			dataType:"text",
			data:
				{
				'cardNumber':(cardNumber.value),
				'userId':(userId.value),
				},
				success:function(data){
					console.log(data);
					if(data.equals("true")){
						$('#idMatch').text("");
					}
					else{
						$('#idMatch').text("* The user and card are not matched.");
					}
				},
				error:function(){}
  		})
  	 }*/
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
  			if(checkPassword()&&checkCardpin()){
  				return true;
  			}
  			else{
  				return false;
  			}
  		}
  	</script>
  </body>
</html>
