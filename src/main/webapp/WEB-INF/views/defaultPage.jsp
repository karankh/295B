<%@    taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">


<head>

    <meta charset="utf-8">

    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <meta name="viewport" content="width=device-width, initial-scale=1">

    <meta name="description" content="">

    <meta name="author" content="">



    <title>WebApp</title>
<style>
.container {
  margin: 50px auto;
  width: 640px;
  text-align: center;
}

</style>

</head>

<body background="${pageContext.request.contextPath}/img/bglogin1.jpg">
	<div class = "container">
    <form:form id="defaultPage" commandName="userpageDetails">
        <table>
         <tr>
                <td colspan="3"><label>${Message}</label></td>
            </tr>
           
            <tr>
                <td colspan="3" align="center"><font color="red"><form:errors /></font></td>
            </tr>
        </table>
        <h2 style = "color: red">Requested page doesn't exist.</h2>
         <a href='${pageContext.request.contextPath}/homepage' >App HomePage</a>
    </form:form>
	</div>
	
</body>
</html>