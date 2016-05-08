<%@    taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>WebPortal</title>
</head>
<body>
    <form:form id="faltu"  commandName="userImages">

    <br />
             <c:forEach items="${userImages}" var="image">
             <tr>
            <td><a href="${pageContext.request.contextPath}/viewFile-${image.imageid}">View Image</a></td>
            
        
        <td>Image Name:${image.imagename}</td>
        <td>Image Description:${image.imagedesc}</td>
        <td>Image UserId:${image.userid}</td> 
        <td> <img  src="data:image/jpeg;base64,${image.imagerepresentation}" height="250" width="250"/></td>
    </tr>
       
    
    <br />
</c:forEach>
       
       
      
          <b><a href="${pageContext.request.contextPath}/homepage">Home</a></b>
         
    </form:form>
    
</body>
</html>