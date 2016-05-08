<%@    taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>WebApp</title>




</head>
<body background="${pageContext.request.contextPath}/img/bglogin1.jpg">

	
    <form:form id="faltu" method="post" action='${pageContext.request.contextPath}/userlist/' commandName="userImage">
       
  
         
           
    <tr>
     <td> <label>Your Image is:</label> </td>
        <td><img  src="data:image/jpeg;base64,${userImage}" height="342px" width="442px"/></td>
       
    </tr>
    <br />

         
         
         
    </form:form>
    <a href='${pageContext.request.contextPath}/homepage' >App HomePage</a>
</body>
</html>