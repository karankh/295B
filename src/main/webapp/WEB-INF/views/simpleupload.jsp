<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<title>WebApp</title>
</head>
<body>
    <form:form id="homepage" action="homepage" method="post"
        commandName="homepageDetails">
        <c:set var="isUserlogin" scope="session" value="${homepageDetails.isUser}"/>
         <c:choose>
         
      <c:when test="${isUserlogin=='true'}">
      <label>Hello,
      <%= session.getAttribute("USERNAME") %></label>
      <b><a href='${pageContext.request.contextPath}/uploadImage' >ImageUpload</a></b>
      <b><a href="${pageContext.request.contextPath}/logout">LogOut</a></b>
      </c:when>

      <c:otherwise>
     <label>Welcome Guest,</label>
      <b><a href='${pageContext.request.contextPath}/signUp' >SignUp</a></b>
      <b><a href='${pageContext.request.contextPath}/login' >SignIn</a></b>
      <br />
      </c:otherwise>
      </c:choose>
        <table>
            <tr>
                <td colspan="3"><h3>Welcome To WebApp HomePage</h3></td>
            </tr>
           <tr>
                <td><label>Search:</label></td>
                <td><form:input path="searchText" type="text"></form:input></td>
                <td><font color="red"><form:errors path="searchText"></form:errors></font></td>
            </tr> 
            <tr>
                <td colspan="2" align="center"><input type="submit"
                    value="SEARCH" /></td>
                
            </tr>
            <tr>
                <td colspan="3" align="center"><font color="red"><form:errors /></font></td>
                 <td colspan="3"><label>${Message}</label></td>
            </tr>
        </table>
        

    </form:form>
    

</body>
</html>


