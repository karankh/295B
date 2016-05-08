<%@    taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <meta name="viewport" content="width=device-width, initial-scale=1">

    <meta name="description" content="">

    <meta name="author" content="">
<title>WebApp Upload</title>
<script>
function validateForm() 
{
	var imagedesc = document.myForm.imagedesc.value;
	var file = document.myForm.file.value;
	
	if (imagedesc.length == 0) 
		{alert("description can't be empty");
	    return false;}
	
	 if (file == "")
     {
        alert("You forgot to attach file!");
        return false;  
    }
	
    return true;
}
</script>

<!-- Sign In CSS -->

<link href="${pageContext.request.contextPath}/css/upload.css" rel="stylesheet">
</head>
<body background="${pageContext.request.contextPath}/img/bglogin1.jpg">
<div class="login-help"><a href='${pageContext.request.contextPath}/homepage' >Go To HomePage</a></div>
	<div class = "container">
	<div class = "upload">
	<h3>Welcome To WebApp Upload Image Page</h3>
	
	
    <form:form id="upload" name="myForm" action='${pageContext.request.contextPath}/uploadImageN' onsubmit="return validateForm();" method="post"
        modelAttribute="fileBucket" enctype="multipart/form-data">
        
                <br>

                <p><label><b>Enter Image Description</b></label> &nbsp; &nbsp;&nbsp;
                <form:input name="imagedesc" type="text" path="imagedesc"></form:input>
                </p>
          <br>
                
               <p> <form:input name="file" type="file" path="file"></form:input><input type="submit"
                    value="UPLOAD" /></p>
             <font color="red"><form:errors /></font>
            
                <p><label>${Message}</label></p>
    </form:form>
     <c:choose>
         
         <c:when test="${upid ne 'false'}">
           <br/>
           <a href='${pageContext.request.contextPath}/viewImageN-${upid}' >View Uploaded Image Here</a>
                 <br/>
              
         </c:when>

      
      </c:choose>
    <br>
    <div class = "link">
    
 
    
    <div class = "login-help">
    <a href='${pageContext.request.contextPath}/listdocs' >View All Images</a>
    </div>
    
    </div>
    </div>
    </div>
</body>
</html>