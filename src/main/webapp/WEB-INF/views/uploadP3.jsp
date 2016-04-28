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

<script src="${crunchifyJS}"></script>
<!-- Finish adding tags -->
 
<title>sample</title>

</head>
<body>${message}
	<br>
	<label>${image1}</label>
	<img src='${image1}' alt="hihihi"/>
	<label>${pageContext.request.contextPath}/images/apples.jpg</label>
	<img src="${pageContext.request.contextPath}/images/apples.jpg" />
	
</body>
</html>