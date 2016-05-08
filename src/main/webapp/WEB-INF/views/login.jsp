<%@    taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<title>WebPortal SignIN</title>
<script>
function validateForm() 
{
	var em = document.myForm.email.value;
	var pwd = document.myForm.passwrd.value;
	
	if (em.length == 0) 
		{alert("email can't be empty");
	    return false;}
	
	if (pwd.length == 0) 
		{alert("Passwrd can't be empty");
	return false;}
	
    var atpos = em.indexOf("@");
    var dotpos = em.lastIndexOf(".");
    if (atpos<1 || dotpos<atpos+2 || dotpos+2>=em.length) 
	{
        alert("Please input a valid e-mail address");
        return false;
    }
	
    return true;
}
</script>
</head>
<body>
    <form:form id="login" name="myForm" action='${pageContext.request.contextPath}/login' onsubmit="return validateForm();" method="post"
        commandName="logindetails">
        <table>
            <tr>
                <td colspan="3"><h3>Welcome To WebApp SignIN Page</h3></td>
            </tr>
         
            <tr>
                <td><label>Email ID</label></td>
                <td><form:input path="email" ></form:input></td>
                <td><font color="red"><form:errors path="email"></form:errors></font></td>
            </tr>
            <tr>
                <td><label>Password</label></td>
                <td><form:input path="passwrd" type="password" ></form:input></td>
               
            </tr>
                <tr>
                <td colspan="2" align="center"><input type="submit"
                    value="LogIn" /></td>
                <td></td>
            </tr>
            <tr>
                <td colspan="3" align="center"><font color="red"><form:errors /></font></td>
            </tr>
             <tr>
                <td colspan="3"><label>${Message}</label></td>
            </tr>
        </table>
    </form:form>
    <a href='${pageContext.request.contextPath}/homepage' >WebPortal HomePage</a><br/>
    <a href='${pageContext.request.contextPath}/signUp' >SignUp</a>
</body>
</html>