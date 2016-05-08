<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<script>
function validateForm() 
{
	var platsuccess=false;
	var i,j;
	for (i = 0; i < document.selection.platforms.length; i++)
	{
		if(document.selection.platforms[i].checked)
		{
			platsuccess=true;
			break;
		}
	}
	if(!platsuccess) {
		alert("Platform selection is must.");
		return false;
	}
	
	var parasuccess=false;
	for (j = 0; j < document.selection.parameters.length; j++)
	{
		if(document.selection.parameters[j].checked)
		{
			parasuccess=true;
			break;
		}
	}
	if(!parasuccess) {
		alert("Parameters selection is must.");
		return false;
	}
	
	
	
	return true;
    
}
</script>
<title>WebPortal</title>
</head>
<body>
<h2>Selection Page</h2>
    <form:form id="selection"  name="selection" commandName="selectedItems" action='${pageContext.request.contextPath}/selection' onsubmit="return validateForm();" method="post">

    <h4>Platforms For Application Benchmarking</h4>  
    <form:checkbox path="platforms" value="AWS"/>AWS 
    <form:checkbox path="platforms" value="Docker"/>Docker 
    <form:checkbox path="platforms" value="OpenStack"/>OpenStack
    <br/><br/>
    
    <h4>Parameters For Application Benchmarking</h4>   
    <form:checkbox path="parameters" value="CPU"/>CPU 
    <form:checkbox path="parameters" value="Memory"/>Memory 
    <form:checkbox path="parameters" value="Throughput"/>Throughput
    <form:checkbox path="parameters" value="Transactions"/>Transactions
    <form:checkbox path="parameters" value="Availability"/>Availability
    <br/><br/>
    
    <h4>Concurrent Load</h4>
    <form:checkbox path="load" value="Yes"/>Enable Load 

    
            <br/><br/> <input type="submit"
                    value="SEARCH" /><br/><br/>
                    
          <b><a href="${pageContext.request.contextPath}/homepage">Home</a></b>
         
    </form:form>
    
</body>
</html>