<%@    taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
           <style>
.circle-image{
         
 	 width: 250px;
  height: 250px;
  border-radius: 175px;
           }
           table.test td {
 
    margin: 20px 20px 20px 20px;
    padding: 20px 20px 20px 20px;
		}
		
		table.test a{
		  text-decoration: none;
  color:#3333ff;
  font-size: 16px;}

.container{
 position: absolute; 
 top:0; 
 bottom: 0; 
 left: 35%; 
 right: 0; 
 }
 .shift{
  position: absolute; 
 top:5%; 
 bottom: 0; 
 left: 1%; 
 right: 0;}
 .shift a{
  text-decoration: none;
  color:#3333ff;
  font-size: 16px; 
 }
           </style>
<!DOCTYPE html>
<html lang="en">


<head>

    <meta charset="utf-8">

    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <meta name="viewport" content="width=device-width, initial-scale=1">

    <meta name="description" content="">

    <meta name="author" content="">



    <title>WebApp
    </title>




</head>
<body background="${pageContext.request.contextPath}/img/bglogin1.jpg">
<br><br>
	<div class ="shift"><b><a href="${pageContext.request.contextPath}/homepage">Go to HomePage</a></b></div>
   <div class ="container"> <form:form id="faltu"  commandName="userImages">

    <br />
             <c:forEach items="${userImages}" var="image">
             <tr>
           <%--  <td><a href="${pageContext.request.contextPath}/viewImageN-${image.imageid}">View Image</a></td> --%>
        
       <%--  <td>Image UserId:${image.userid}</td>  --%>
      
        
     <td> <img  class ="circle-image" src="data:image/jpeg;base64,${image.imagerepresentation}" height="250" width="250"/></td>
 			<br><br>
 			<table class="test">
 		  <td>Image Name:${image.imagename}</td>
 		
 		<td><a href="${pageContext.request.contextPath}/viewImageN-${image.imageid}">View Image</a></td>
 		
        <td>Image Description:${image.imagedesc}</td>
    	</tr>
       </table>
    
    <br />
</c:forEach>
       
       
      
          
         
    </form:form></div>
    
</body>
</html>