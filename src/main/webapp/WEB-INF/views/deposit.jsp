<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Deposit</title>
    <meta name="description" content="">
    <meta name="author" content="templatemo">
     <script type="text/javascript" src="static/js/jquery-1.11.2.min.js"></script>        <!-- jQuery -->
   <script type="text/javascript" src="static/js/bootstrap.min.js"></script> 
    <link href="static/css/font-face.css" rel="stylesheet">
	<link href="static/css/font-awesome.min.css" rel="stylesheet">
    <link href="static/css/bootstrap.min.css" rel="stylesheet">
    <link href="static/css/templatemo-style.css" rel="stylesheet">
    
	<script type="text/javascript" src="static/js/bootbox.min.js"></script> 
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
          <h1>Deposit</h1>
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
            <li><a href="todeposit" class='active'><i class="fa fa-database fa-fw"></i>Deposit</a></li>
            <li><a href="tochangeCardPin" ><i class="fa fa-sliders fa-fw"></i>Change Card Pin</a></li>
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
            <h2 class="margin-bottom-10">Deposit Money</h2><br />
            <form  id="vertify" class="templatemo-login-form" method="POST" action="deposit">
              <div class="row form-group">
			    <div class="col-lg-3 col-md-3 form-group"></div>
                <div class="col-lg-6 col-md-6 form-group">                  
                    <label for="inputUsername">Card Number</label>
                    <input type="text" class="form-control" id="cardNumber" name="cardNumber" required="required" maxlength="16" onchange="checkCardnumber()">                  
                </div>
				<div class="col-lg-3 col-md-3 form-group">
					
				</div>
              </div>
              
               <div class="row ">
              	<div class="col-lg-3 col-md-3 "></div>
              	<div class="col-lg-8 col-md-8 ">
					<p id="cardInfo" style="color:red;"></p>
				</div>
              </div>
              
			  <div class="row form-group">
			    <div class="col-lg-3 col-md-3 form-group"></div>
                <div class="col-lg-6 col-md-6 form-group">                  
                    <label for="inputAmount">Amount(CNY)</label>
                    <input type="number" class="form-control" id="inputAmount" min="0" name="amount" required="required" >                 
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
            <br>
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
    <script>
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
    
   <!-- <script type="text/javascript">
    function checkCard(){
     	var cardNumber=document.getElementById("cardNumber");
     	var reg=new RegExp("[0-9]{16}");
     	if(!reg.test(cardNumber.value)){
     		cardNumber.setCustomValidity("Please input 16 digits  card number.");    		
     	}
     	else{    		
     		cardNumber.setCustomValidity("");
         }
     }
  	</script>
  
    <script type="text/javascript">
    function checkNumber(){
    	var cardNumber=document.getElementById("cardNumber").value;
   	 	var number =document.getElementById("inputAmount"); 
   	 	var amount=number.value;
      	var num=parseFloat((number.value));
      	console.log("num");
      	console.log(num);
		var has;
      	var code;
      	
      	
      	 $.ajax({
    			url:"<%=request.getContextPath()%>/checkCardNumber",
    			type:"POST",
    			dataType:"text",
    			data:
    				{
    				'cardNumber':cardNumber,
    				},
    				success:function(data){
    					console.log(data);
    					if(data=="has"){
    						
    						if(num<50000){
    							 $.ajax({
    				        			url:"<%=request.getContextPath()%>/depositC",
    				        			type:"POST",
    				        			dataType:"text",
    				        			data:
    				        				{
    				        				'cardNumber':cardNumber,
    				        				'amount':amount,
    				        				},
    				        				success:function(data){
    				        					console.log(data);
    				        					if(data=="update success"){
    				        						bootbox.alert("update success",function(){
    				        							window.location.href="tomainpage";
    				        						});
    				        						return true;
    				        					}
    				        					else{
    				        						bootbox.alert("unknow reason,you can log out and try again",function (){
    				        							window.location.href="todeposit";
    				        						});
    				        						//window.location.href="tomainpage";
    				        						//alert("Wrong number");
    				        						//window.location.href="wrong";
    				        						//$("#message").text("* Admin name or password is wrong");
    				        						return false;
    				        						
    				        					}
    				        				},
    				        				error:function(){
    				        					
    				        				}
    				        		});
    							 return false;
    						}
    						else{
    							
    							$.ajax({
    				    			url:"codeVertify",
    				    			type:"GET",
    				    			dataType:"text",
    				    			data:
    				    				{
    				    				},
    				    				success:function(data){
    				    			          bootbox.prompt({
    				    			              title: "please input authority code",
    				    			              value: "",
    				    			              callback: function(result) {
    				    			              if (result === data) {
    				    			              bootbox.alert("you input correct right");
    				    			              deposit(cardNumber,amount);
    				    			              return true;
    				    			              } else {
    				    			              bootbox.alert("you input wrong",function (){
    				    			            	  bootbox.prompt({
    	    				    			              title: "please input authority code",
    	    				    			              value: "",
    	    				    			              callback: function(result) {
    	    				    			              if (result === data) {
    	    				    			              bootbox.alert("you input correct right");
    	    				    			              deposit(cardNumber,amount);
    	    				    			              return true;
    	    				    			              } else {
    	    				    			              bootbox.alert("Operation failed.",function (){
    	    				    			            	  window.location.href='todeposit';
    	    				    			              });
    	    				    			             	return false;
    	    				    			              }
    	    				    			              }
    	    				    			            });
    				    			              });
    				    			             	return false;
    				    			              }
    				    			              }
    				    			            });

    				    					},
    				    						 				    				
    				    				error:function(){
    				    					return false
    				    				}
    				    		});
    							
    							return false;
    						}
    						return false;
    					}
    					
    					else{
    						bootbox.alert("card number not found",function(){
    							window.location.href='todeposit';
    						});
    			      		return false;
    						
    					}
    				},
    				error:function(){
    					has="no";
    					return false;
    				}
    				
    		});
      	
      	return false;
      	
      
      	
      	function hasCard(cardNumber){
     		 $.ajax({
       			url:"<%=request.getContextPath()%>/checkCardNumber",
       			type:"POST",
       			dataType:"text",
       			data:
       				{
       				'cardNumber':cardNumber,
       				},
       				success:function(data){
       					console.log(data);
       					if(data=="has"){
       						has="has";
       						return "has";
       					}
       					
       					else{
       						has="no";
       						return "no";
       						
       					}
       				},
       				error:function(){
       					has="no";
       					return "no";
       				}
       				
       		});
     		
     	}
      	
      	function deposit(cardNumber,amount){
      		 $.ajax({
        			url:"<%=request.getContextPath()%>/depositC",
        			type:"POST",
        			dataType:"text",
        			data:
        				{
        				'cardNumber':cardNumber,
        				'amount':amount,
        				},
        				success:function(data){
        					console.log(data);
        					if(data=="update success"){
        						bootbox.alert("update success",function(){
        							window.location.href="tomainpage";
        						});
        						return true;
        					}
        					else{
        						bootbox.alert("unknow reason,you can log out and try again",function (){
        							window.location.href="tomainpage";
        						});
        				
        						return false;
        						
        					}
        				},
        				error:function(){
        					
        				}
        		});
      		 return false;
      	}
      	
      	function getCode(){
      		$.ajax({
    			url:"codeVertify",
    			type:"GET",
    			dataType:"text",
    			data:
    				{
    				},
    				success:function(data){
    					
    					console.log(data);
    					
    						
    						code=data;
    						return data;
    						
    					
    				},
    				error:function(){
    					code="";
    				}
    		});
      		return "";
      	}
        	  
        }
        
    
        
       /*if(num>=50000){
       	var  code = prompt("amount is >= CNY 50,000"+"\n"+"please input the authentication code",'');
       	var form=document.getElementById("vertify");
       	
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
    	   

       	
       /*}
       else{

       		number.setCustomValidity("");
       		form.submit();
       		return true;
       }

   		return false;*/

 
    
    
    </script>
    <script>
  
    </script>-->
  
    <script type="text/javascript" src="static/js/bootstrap-filestyle.min.js"></script>  <!-- http://markusslima.github.io/bootstrap-filestyle/ -->
    <script type="text/javascript" src="static/js/templatemo-script.js"></script>        <!-- Templatemo Script -->
  </body>
</html>
