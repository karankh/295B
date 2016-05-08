<%@    taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>WebPortal</title>
</head>
<body>

    <form:form id="userpage" method="post" action='${pageContext.request.contextPath}/userpage/${userpageDetails.id}' commandName="userpageDetails">
        <table>
         <tr>
                <td colspan="3"><label>${Message}</label></td>
            </tr>
            <tr>
                <td colspan="3"><h3>Welcome To Your Page</h3></td>
            </tr>
            
            <tr>
                <td><label>ID</label></td>
                <td><form:label path="id" >${userpageDetails.id}</form:label></td>
            </tr>
            <tr>
                <td><label>First Name</label></td>
                <td><form:input path="firstname" ></form:input></td>
                <td><font color="red"><form:errors path="firstname"></form:errors></font></td>
            </tr>
            <tr>
                <td><label>Last Name</label></td>
                <td><form:input path="lastname" ></form:input></td>
               
            </tr>
            <tr>
                <td><label>Email</label></td>
                <td><form:input path="email" ></form:input></td>
                
            </tr>
           
          
            <tr>
                <td colspan="3" align="center"><font color="red"><form:errors /></font></td>
            </tr>
       
         <c:set var="isUserlogin" scope="session" value="${userpageDetails.isUserlogin}" />
         <c:set var="userType"  scope="session" value="${userpageDetails.usertype}"/>
         <c:choose>
         
         <c:when test="${isUserlogin=='true'}">
        
                 <tr>
                <td><label>UserType</label></td>
                <td><form:label path="usertype" >${userpageDetails.usertype}</form:label></td>
            </tr>
         
        <tr>
        
               <td colspan="2" align="center"><input type="submit" name="update"
                    value="Update" /></td>
                    
                <td>
                
               <td><a href='${pageContext.request.contextPath}/userpaged/${userpageDetails.id}' >Delete Account</a></td>
              
            </tr>
      
      
         <a href='${pageContext.request.contextPath}/homepage' >WebPortal HomePage</a><br/>
         <b><a href="${pageContext.request.contextPath}/logout">LogOut</a></b><br/>

      <br />
         </c:when>

      <c:otherwise>
      <b><a href='${pageContext.request.contextPath}/login' >SignIn</a></b>
      <br />
      </c:otherwise>
      
      </c:choose>
      
      <%--  <c:choose>
     
       <c:when test="${userType eq 'admin'}">
        <a href='${pageContext.request.contextPath}/uploadFile' >Upload Scripts/Process Flows</a><br/>
          <b><a href="${pageContext.request.contextPath}/searchImagesByUser">View Your Uploaded Scripts</a></b><br/>
           <a href='${pageContext.request.contextPath}/uploadReport' >Upload Reports</a><br/>
           <br />
         </c:when>
         </c:choose> --%>
 
        
          </table>
    </form:form>
     <c:choose>
     
       <c:when test="${userType eq 'admin'}">
        <a href='${pageContext.request.contextPath}/uploadFile' >Upload Scripts/Process Flows</a><br/>
          <b><a href="${pageContext.request.contextPath}/searchImagesByUser">View Your Uploaded Scripts</a></b><br/>
           <a href='${pageContext.request.contextPath}/uploadReport' >Upload Reports</a><br/>
           <br />
         </c:when>
         </c:choose>
</body>
</html>