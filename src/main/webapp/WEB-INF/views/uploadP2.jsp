<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
 
<!-- let's add tag srping:url -->
<spring:url value="/cssandjs/crunchify.css" var="crunchifyCSS" />
<spring:url value="/cssandjs/crunchify.js" var="crunchifyJS" />
<spring:url value="/images/apples.jpg" var="image1" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<link href="${crunchifyCSS}" rel="stylesheet" />
<script src="${crunchifyJS}"></script>
<!-- Finish adding tags -->
 
<title>Spring MVC Tutorial by Crunchify - Hello World Spring MVC Example</title>
<style type="text/css">
body {
	background-image: url('http://crunchify.com/bg.png');
}
</style>
</head>
<body>${message}
	<br>
	<label>${image1}</label>
	<img src='${image1}' alt="hihihi"/>
	<label>${pageContext.request.contextPath}/images/some.png</label>
	<img src="${pageContext.request.contextPath}/images/apples.jpg" />
	<div
		style="font-family: verdana; padding: 10px; border-radius: 10px; font-size: 12px; text-align: center;">
 
		<h2>Checkout this font color. Loaded from 'crunchify.css' file under '/WebContent/resources/' folder</h2>
 
		<div id="crunchifyMessage"></div>
 
		<br> Spring MCV Tutorial by <a href="http://crunchify.com">Crunchify</a>.
 
		<br> <br> Click <a
			href="http://crunchify.com/category/java-web-development-tutorial/"
			target="_blank">here</a> for all Java and <a
			href='http://crunchify.com/category/spring-mvc/' target='_blank'>here</a>
		for all Spring MVC, Web Development examples.<br>
	</div>
</body>
</html>