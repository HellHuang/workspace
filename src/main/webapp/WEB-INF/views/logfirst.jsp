<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<html>
	<head>
	     <script type="text/javascript" src="static/js/jquery-1.11.2.min.js"></script>        <!-- jQuery -->
   <script type="text/javascript" src="static/js/bootstrap.min.js"></script> 
    <link href="static/css/font-face.css" rel="stylesheet">
	<link href="static/css/font-awesome.min.css" rel="stylesheet">
    <link href="static/css/bootstrap.min.css" rel="stylesheet">
    <link href="static/css/templatemo-style.css" rel="stylesheet">
    
	<script type="text/javascript" src="static/js/bootbox.min.js"></script>
	</head>
	<body>
	<script>
		bootbox.alert("<br><B>Pleasse login first.",function(){
			window.location.href='tologinpage';
		});		
		</script>
	
	</body>
</html>