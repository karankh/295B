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

<title>WebApp SignUP</title>
<script>
function validateForm() 
{
	var fn = document.myForm.firstname.value;
	var ln = document.myForm.lastname.value;
	var pwd = document.myForm.passwrd.value;
	
	if (fn.length == 0) 
		{alert("FirstName can't be empty");
	    return false;}
	
	if (ln.length == 0) 
		{alert("LastName can't be empty");
	return false;}
	
	if (pwd.length == 0 || pwd.length < 6) 
		{alert("Password can't be empty or lesser than 6 characters in length.");
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


<!-- Sign Up CSS -->

<link href="${pageContext.request.contextPath}/css/signup.css" rel="stylesheet">

</head>
<body background="${pageContext.request.contextPath}/img/bglogin1.jpg">

<div class = "container">
<div class = "signup">
<h1>SignUP to WebApp</h1>
    <form:form id="registration" name="myForm" action='${pageContext.request.contextPath}/signUp' onsubmit="return validateForm();" method="post"
        commandName="registrationDetails">
       
                <p><label>First Name</label>
                <form:input path="firstname" type = "text" value="" placeholder="First Name"></form:input></p>
                <p><label>Last Name</label>
                <form:input path="lastname" type = "text" value="" placeholder="Last Name"></form:input></p>
               
           		<p><label>Email</label>
                <form:input path="email" type = "text" value="" placeholder="Email (abc@xyz.com)"></form:input></p>
                
            
                <p><label>Password</label>
                <form:input path="passwrd" type="password" name = "password" value="" placeholder="Password"></form:input></p><br>
                
           		<div class = "buttonholder">
                <input type="submit" class = "submit"
                    value="Create" />
                    
                    </div>
              
               <p><label>${Message}</label></p>
          
    </form:form>
    </div>
    </div>
    <div class = "signup-help">
    <a href='${pageContext.request.contextPath}/homepage' >App HomePage</a>
    </div>
</body>
</html>