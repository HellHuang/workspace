<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>HSBC - Withdrawal</title>
    <meta name="description" content="">
    <meta name="author" content="templatemo">
    
    <link href="css/font-face.css" rel="stylesheet">
    <link href="css/font-awesome.min.css" rel="stylesheet">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/templatemo-style.css" rel="stylesheet">

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
          <div class="square"></div>
          <h1>Withdrawal</h1>
        </header>
        <div class="profile-photo-container">
          <img src="images/profile-photo.jpg" alt="Profile Photo" class="img-responsive">
          <div class="profile-photo-overlay"></div>
        </div>
        <div class="mobile-menu-icon">
            <i class="fa fa-bars"></i>
          </div>
        <nav class="templatemo-left-nav">
          <ul>
            <li><a href="openaccount.jsp" class="active"><i class="fa fa-home fa-fw"></i>Open Account</a></li>
            <li><a href="inquire.jsp"><i class="fa fa-bar-chart fa-fw"></i>Inquire</a></li>
            <li><a href="withdrawal.jsp"><i class="fa fa-database fa-fw"></i>Withdrawal</a></li>
            <li><a href="deposit.jsp"><i class="fa fa-map-marker fa-fw"></i>Deposit</a></li>
          </ul>
        </nav>
      </div>
      <!-- Main content -->
      <div class="templatemo-content col-1 light-gray-bg">
        <div class="templatemo-top-nav-container">
          <div class="row">
            <nav class="templatemo-top-nav col-lg-12 col-md-12">
              <ul class="text-uppercase">
                <li><a href="mainpage.jsp">Dashboard</a></li>
                <li><a href="loginpage.jsp">Sign out</a></li>
              </ul>
            </nav>
          </div>
        </div>
        <div class="templatemo-content-container">
          <div class="templatemo-content-widget white-bg">
            <h2 class="margin-bottom-10">Withdraw Money</h2><br />
            <form action="mainpage.html" class="templatemo-login-form" method="post" enctype="multipart/form-data">
              <div class="row form-group">
			    <div class="col-lg-3 col-md-3 form-group"></div>
                <div class="col-lg-6 col-md-6 form-group">                  
                    <label for="inputUsername">Card Number</label>
                    <input type="text" class="form-control" id="inputUsername">                  
                </div>
				<div class="col-lg-3 col-md-3 form-group"></div>
              </div>
			  <div class="row form-group">
			    <div class="col-lg-3 col-md-3 form-group"></div>
                <div class="col-lg-6 col-md-6 form-group">                  
                    <label for="inputAmount">Amount(CNY)</label>
                    <input type="text" class="form-control" id="inputAmount">                 
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
    <script type="text/javascript" src="js/jquery-1.11.2.min.js"></script>        <!-- jQuery -->
    <script type="text/javascript" src="js/bootstrap-filestyle.min.js"></script>  <!-- http://markusslima.github.io/bootstrap-filestyle/ -->
    <script type="text/javascript" src="js/templatemo-script.js"></script>        <!-- Templatemo Script -->
  </body>
</html>
