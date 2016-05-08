<%@    taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>WebPortal Upload</title>
<script>
function validateForm() 
{
	var documentDescp = document.myForm.documentDescp.value;
	var file = document.myForm.file.value;
	
	if (documentDescp.length == 0) 
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
    <form:form id="upload" name="myForm" action='${pageContext.request.contextPath}/uploadReport' onsubmit="return validateForm();" method="post"
        modelAttribute="fileBucket" enctype="multipart/form-data">
        <table>
            <tr>
                <td colspan="3"><h3>Welcome To WebPortal Upload Report Page</h3></td>
            </tr>
         
            <tr>
                <td><label>Enter Report Description</label></td>
                <td><form:input name="documentDescp" type="text" path="documentDescp"></form:input></td>
                
            </tr>
             
            
             <tr>
                
                <td><form:input name="file" type="file" path="file"></form:input></td>
                
            </tr>
             <tr>
                <td><label>Select Report Type</label></td>
                <td><form:select path="documentCategory">
                     <option value="Generated">Generated</option>
                     <option value="Recommendations">Recommendations</option>
                     <option value="Subjective">Subjective</option>
                    </form:select></td>
                
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
        <c:choose>
         
         <c:when test="${upid ne 'false'}">
           <br/>
           <a href='${pageContext.request.contextPath}/viewReport-${upid}' >View uploaded File Here</a>
                 <br/>
              
         </c:when>

      
      </c:choose>
    </form:form>
    <a href='${pageContext.request.contextPath}/homepage' >App HomePage</a>
    <a href='${pageContext.request.contextPath}/listdocs' >View All Images</a>
</body>
</html>