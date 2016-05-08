<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <meta name="viewport" content="width=device-width, initial-scale=1">

    <meta name="description" content="">

    <meta name="author" content="">

<title>WebAPP</title>

    <style>
.container {
  margin: 50px auto;
  width: 640px;
  text-align: center;
}

</style>
</head>
<body background="${pageContext.request.contextPath}/img/bglogin1.jpg">
		<div class = "container">
		<h2>ERROR 404</h2>
		<h2>${msg}
			 </h2>
			  <a href='${pageContext.request.contextPath}/uploadImageN' >Click here to Upload Pics</a>
		</div>
</body>
</html>