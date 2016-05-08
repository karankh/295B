<%@    taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <meta name="viewport" content="width=device-width, initial-scale=1">

    <meta name="description" content="">

    <meta name="author" content="">

<title>SignIn</title>


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

<!-- Sign In CSS -->

<link href="${pageContext.request.contextPath}/css/login.css" rel="stylesheet">
    
</head>
<body background="${pageContext.request.contextPath}/img/bglogin1.jpg">
	<div class = "container">
	<div class = "login">
	<h1>SignIn to WebApp</h1>
    <form:form id="login" name="myForm" class = "form-signin" action='${pageContext.request.contextPath}/login' onsubmit="return validateForm();" method="post"
        commandName="logindetails">
        
        <p><label>Email ID</label>
        <form:input path="email" type = "text" value="" placeholder="Email (abc@xyz.com)"></form:input>
                <font color="red"><form:errors path="email"></form:errors></font></p>
        <p><label>Password</label>
                <form:input path="passwrd" type="password" name = "password" value="" placeholder="Password"></form:input></p><br>
               <div class = "buttonHolder">
               <p class = "submit"> <input type="submit" name = "commit" value="LogIn" /></p>
               </div>
                 <font color="red"><form:errors /></font>
         		<p> <label>${Message}</label><p>
          
    </form:form>
    </div>
    <div class = "login-help">
    <p><a href='${pageContext.request.contextPath}/homepage' >App HomePage</a></p>
    </div>
    </div>
    
  
</body>
</html>