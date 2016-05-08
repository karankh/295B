<%@    taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <meta name="viewport" content="width=device-width, initial-scale=1">

    <meta name="description" content="">

    <meta name="author" content="">
    
<title>WebApp</title>

<!-- Userpage CSS -->

<link href="${pageContext.request.contextPath}/css/userpage.css" rel="stylesheet">

</head>
<body background="${pageContext.request.contextPath}/img/bglogin1.jpg">
	<div class = "container">
	<div class = "userpage">
    <form:form id="userpage" method="post" action='${pageContext.request.contextPath}/userpage/${userpageDetails.id}' commandName="userpageDetails">
        <h3>${Message}</h3>
        
         <h3>Welcome to Your Page</h3>
            
                <p><label>User ID</label>
                <form:label path="id" >${userpageDetails.id}</form:label></p>
           
                
               <p> <label>First Name</label>&nbsp;&nbsp; &nbsp;<form:input path="firstname" ></form:input></p>
                
            
            
                
               <p> <label>Last Name</label>&nbsp;&nbsp;&nbsp; <form:input path="lastname" ></form:input></p>
               
            
                
               <p> <label>Email ID</label> &nbsp;&nbsp;&nbsp;<form:input path="email" ></form:input></p>
                
         
         <c:set var="isUserlogin" scope="session" value="${userpageDetails.isUserlogin}"/>
         <c:choose>
         
         <c:when test="${isUserlogin=='true'}"><br><br>
        <td><input type="submit" class = "submit" name="update"
                    value="Update" /></td> &nbsp;&nbsp;&nbsp;&nbsp;
                    
        <td><a href='${pageContext.request.contextPath}/userpaged/${userpageDetails.id}' >Delete Account</a></td>
              
      <div class = "link1 a"> 
      <tr>
       <td><a href='${pageContext.request.contextPath}/uploadImageN' >Upload Images</a></td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
         
        <td><a href='${pageContext.request.contextPath}/homepage' >App HomePage</a></td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
         
         
         <td><b><a href="${pageContext.request.contextPath}/logout">LogOut</a></b></td>
         </tr></div>
 
         
        <div class = "link1 a">  <tr>
        
          <b><a href="${pageContext.request.contextPath}/searchImagesByUser">View Your Uploaded Images</a></b>
       </tr></div>
      <br />
         </c:when>

      <c:otherwise>
      <div class = "link1 a">
      <b><a href='${pageContext.request.contextPath}/login' >SignIn</a></b>
      </div>
      <br />
      </c:otherwise>
      </c:choose>
      
        
          
    </form:form>
    </div>
    </div>
</body>
</html>