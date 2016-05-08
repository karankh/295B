<%@    taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<title>WebPortal SignUP</title>
<script>
function validateForm() 
{
	var fn = document.myForm.firstname.value;
	var ln = document.myForm.lastname.value;
	var pwd = document.myForm.passwrd.value;
	var cnfpasswrd = document.myForm.cnfpasswrd.value;
	if (fn.length == 0) 
		{alert("FirstName can't be empty");
	    return false;}
	
	if (ln.length == 0) 
		{alert("LastName can't be empty");
	return false;}
	
	if (pwd.length == 0 || pwd.length < 6) 
		{alert("Password can't be empty or lesser than 6 characters in length.");
	return false;}
	
	if (cnfpasswrd.length == 0 || cnfpasswrd!==pwd) 
	{alert("Confirm Password can't be empty or different than Password.");
return false;}
	
    var x = document.myForm.email.value;
    var atpos = x.indexOf("@");
    var dotpos = x.lastIndexOf(".");
    if (atpos<1 || dotpos<atpos+2 || dotpos+2>=x.length) 
	{
        alert("Please input a valid e-mail address");
        return false;
    }
    return true;
}
</script>
</head>
<body>
    <form:form id="registration" name="myForm" action='${pageContext.request.contextPath}/signUp' onsubmit="return validateForm();" method="post"
        commandName="registrationDetails">
        <table>
            <tr>
                <td colspan="3"><h3>Welcome To WebPortal SignUP Page</h3></td>
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
                <td><label>Password</label></td>
                <td><form:input path="passwrd" type="password" ></form:input></td>
                
            </tr>
            
             <tr>
                <td><label>Confirm Password</label></td>
                <td><input name="cnfpasswrd" type="password" ></input></td>
                
            </tr>
                      <tr>
                <td colspan="2" align="center"><input type="submit"
                    value="Create" /></td>
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
    <a href='${pageContext.request.contextPath}/homepage' >App HomePage</a><br/>
    <a href='${pageContext.request.contextPath}/login' >SignIN</a>
</body>
</html>