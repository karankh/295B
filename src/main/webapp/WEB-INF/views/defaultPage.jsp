<%@    taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>WebApp</title>
</head>
<body>
    <form:form id="defaultPage" commandName="userpageDetails">
        <table>
         <tr>
                <td colspan="3"><label>${Message}</label></td>
            </tr>
           
            <tr>
                <td colspan="3" align="center"><font color="red"><form:errors /></font></td>
            </tr>
        </table>
         <a href='${pageContext.request.contextPath}/uploadImageN' >Upload Pics</a>
         <a href='${pageContext.request.contextPath}/homepage' >App HomePage</a>
    </form:form>
</body>
</html>