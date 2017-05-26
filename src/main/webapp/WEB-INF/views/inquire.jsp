<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Inquiry</title>
    <meta name="description" content="">
    <meta name="author" content="templatemo">
    <script type="text/javascript" src="static/js/jquery-1.11.2.min.js"></script>   
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
          <h1>Inquiry</h1>
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
          <div class="templatemo-content-widget white-bg">
            <h2 class="margin-bottom-10">User Information Inquiry</h2><br />
            <form action="inquireUser" method="post" >
             <div class="row form-group">
	              <div class="col-lg-3 col-md-3 form-group"></div>
	              <div class="col-lg-6 col-md-6 form-group">  
	              <label>Please choose the inquiry type</label>
	                <select class="form-control" name="type" id="type" required="required">                             
	                 <option value="1">Debit Card Inquiry</option>
	                 <option value="2">Online Banking Account Inquiry</option>
	
	                 </select>
	              </div>
	             	<div class="col-lg-3 col-md-3 form-group">
	             	</div>
	                                           
               </div>
              <div class="row form-group">
			    <div class="col-lg-3 col-md-3 form-group"></div>
                <div class="col-lg-6 col-md-6 form-group">                  
                    <label >Customer ID Number</label>
                    <input name="inqUser" id="inqUser" type="text" class="form-control" onchange="checkId()" required="required" maxlength="18"/>                  
                </div>
				<div class="col-lg-3 col-md-3 form-group">
				
				</div>
              </div>
              <div class="row ">
              	<div class="col-lg-3 col-md-3 "></div>
              	<div class="col-lg-3 col-md-3 ">
					<p id="idInfo" style="color:red;"> </p>
				</div>
              </div>
              
              <div class="form-group text-center">
                <button type="submit" class="templatemo-blue-button">Submit</button>
                <button type="reset" class="templatemo-white-button">Reset</button>
              </div> 
              <br>
              <br>
              <br>
              <br>
                                        
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
	<script>
	 function checkId(){
	     	var userId=document.getElementById("inqUser");
	     	var reg=new RegExp("[0-9]{18}|([0-9]{17}X)");
	     	if(!reg.test(userId.value)){
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
	     		
	     		$('#idInfo').text("");
	         }
	     }
  	</script>
    <!-- JS -->
         <!-- jQuery -->
    <script type="text/javascript" src="static/js/bootstrap-filestyle.min.js"></script>  <!-- http://markusslima.github.io/bootstrap-filestyle/ -->
    <script type="text/javascript" src="static/js/templatemo-script.js"></script>        <!-- Templatemo Script -->
  </body>
</html>
