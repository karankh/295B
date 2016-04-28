<%@    taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
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
</head>
<body>
    <form:form id="upload" name="myForm" action='${pageContext.request.contextPath}/uploadImageN' onsubmit="return validateForm();" method="post"
        modelAttribute="fileBucket" enctype="multipart/form-data">
        <table>
            <tr>
                <td colspan="3"><h3>Welcome To WebApp Upload Image Page</h3></td>
            </tr>
         
            <tr>
                <td><label>Enter Image Description</label></td>
                <td><form:input name="imagedesc" type="text" path="imagedesc"></form:input></td>
                
            </tr>
            
             <tr>
                
                <td><form:input name="file" type="file" path="file"></form:input></td>
                
            </tr>
         
                <tr>
                <td colspan="2" align="center"><input type="submit"
                    value="UPLOAD" /></td>
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
    <a href='${pageContext.request.contextPath}/homepage' >App HomePage</a>
    <a href='${pageContext.request.contextPath}/listdocs' >View All Images</a>
</body>
</html>