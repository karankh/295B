<%@    taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>WebApp</title>
</head>
<body>
    <form:form id="userpage" method="post" action='${pageContext.request.contextPath}/userpage/${userpageDetails.id}' commandName="userpageDetails">
        <table>
         <tr>
                <td colspan="3"><label>${Message}</label></td>
            </tr>
            <tr>
                <td colspan="3"><h3>Welcome 2 Your Page</h3></td>
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
               <td colspan="2" align="center"><input type="submit" name="update"
                    value="Update" /></td>
                    
                <td>
                
                <td colspan="2" align="center"><input type="submit" name="delete"
                    value="Delete" /></td>
                    
                <td>
            
		</td>
            </tr>
            <tr>
                <td colspan="3" align="center"><font color="red"><form:errors /></font></td>
            </tr>
        </table>
         <a href='${pageContext.request.contextPath}/uploadImageN' >Upload Pics</a>
         <a href='${pageContext.request.contextPath}/homepage' >App HomePage</a>
          <b><a href="${pageContext.request.contextPath}/logout">LogOut</a></b>
    </form:form>
</body>
</html>