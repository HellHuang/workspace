<%@ page session="true" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Withdrawal</title>
    <meta name="description" content="">
    <meta name="author" content="templatemo">
    
    <link href="static/css/font-face.css" rel="stylesheet">
    <link href="static/css/font-awesome.min.css" rel="stylesheet">
    <link href="static/css/bootstrap.min.css" rel="stylesheet">
    <link href="static/css/templatemo-style.css" rel="stylesheet">
    <script type="text/javascript" src="static/js/jquery-1.11.2.min.js"></script>   
    <script src="static/js/bootstrap.min.js"></script> 
    <script src="static/js/bootbox.min.js"></script>   

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
          <h1>Withdrawal</h1>
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
            <li><a href="towithdrawal" class="active"><i class="fa fa-database fa-fw"></i>Withdrawl</a></li>
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
            <h2 class="margin-bottom-10">Withdraw Money</h2><br />
            <form id="vertify" action="withdrawal" class="templatemo-login-form" method="post">
              <div class="row form-group">
              	<div class="col-lg-3 col-md-3 form-group"></div>
              	<div class="col-lg-6 col-md-6 form-group">
              		<label for="inputTpye">Select Type</label>
              		<select class="form-control" name="select" id="select" required="required">
              			<option value="Ordinary withdrawal">Ordinary Withdrawal</option>
              			<option value="Withdrawal in Advance">Withdrawal in Advance</option>
              		</select>
              	</div>
              	<div class="col-lg-3 col-md-3 form-group"></div>
              </div>
              <div class="row form-group">
			    <div class="col-lg-3 col-md-3 form-group"></div>
                <div class="col-lg-6 col-md-6 form-group">                  
                    <label for="inputUsername">Card Number or Time Deposit Number</label>
                    <input name="cardnumber" type="text" class="form-control" id="inputCardnumber" required="required" onchange="checkCardNumber()" maxlength="16">                  
                </div>
				<div class="col-lg-3 col-md-3 form-group">
				</div>
              </div>
			  <div class="row form-group">
			    <div class="col-lg-3 col-md-3 form-group"></div>
                <div class="col-lg-6 col-md-6 form-group">                  
                    <label for="inputAmount">Amount(CNY)</label>
                    <input type="number" min="0" name="amount" type="text" class="form-control" id="inputAmount" required="required" >                 
                </div>
				<div class="col-lg-3 col-md-3 form-group"></div>
              </div>
              <div class="form-group text-center">
                <button type="submit" class="templatemo-blue-button">Submit</button>
                <button type="reset" class="templatemo-white-button">Reset</button>
              </div>                           
            </form>
            <br>
            <br>
            <br>
            <br>
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
  	function checkCardNumber(){
     	var cardNumber=document.getElementById("inputCardnumber");
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
        						$('#cardInfo').text("* Card number is not existed.");
        						 
        						
        					}
        				},
        				error:function(){
        					
        				}
        				
        		});
     		
     		
      		$('#cardInfo').text("");
         }
  	}
  	</script>
  	
  	<!--<script type="text/javascript">
    function checkNumber(){
   	 var number =document.getElementById("inputAmount");
       
        var num=parseFloat((number.value));
        console.log("num");
        console.log(num);
       if(num>=50000){
       	var  code = prompt("Amount is >= CNY 50,000"+"\n"+"Please input the authentication code.",'');
       	var form=document.getElementById("vertify");
       	$.ajax({
			url:"codeVertify",
			type:"POST",
			dataType:"text",
			data:
				{
				
				
				},
				success:function(data){
					console.log("code"+code);
					console.log(data);
					//data=data[0]+data[1]+data[2];
					console.log(data);
					if(data!=code){
						alert("The authentication code is wrong.");
						//window.history.go(-1);
						//window.location.href='towithdrawal';
						return false;
						//return false;
						
						
					}else{
					 	
						//window.location.href="wrong";
						//return true;
						form.submit();
						return true;
						
					}
				},
				error:function(){
					
				}
		});
       	/*if(code!=("123")){
       		alert("code is wrong");
       		//window.location.href='histroy.back()';
       		//number.setCustomValidity("authentication is not successful");
       		return false;
       	}
       	else {
       		//number.setCustomValidity("");
       		
       		number.setCustomValidity("");
       		return true;
       	}*/
    	   

       	
       }
       else{

       		number.setCustomValidity("");
       		form.submit();
       		return true;
       }

   		return false;
   }   
    </script>-->
  </body>
</html>
