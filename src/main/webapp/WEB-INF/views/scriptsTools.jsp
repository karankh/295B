<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>WebPortal</title>
</head>
<body>
<h2>Scripts/Tools Page</h2>
    <form:form id="scriptsTools"  commandName="listScripts">

    <br />
    <h4>Scripts Used For Application Benchmarking</h4><br/><br/>
             <c:forEach items="${listScripts}" var="script" varStatus="loop">
            
             <tr>
              <td>${loop.count}</td>
              <td>Script Name:${script.documentName}</td>
              <c:choose>
      <c:when test="${script.isPdf eq 'yes'}">
        <td><a href="${pageContext.request.contextPath}/viewFile-${script.docid}">View Script</a></td>
         </c:when>
         </c:choose>
           
            <td><a href="${pageContext.request.contextPath}/downloadFile-${script.docid}">Export Script</a></td>
        </tr><br/>
        <tr>
        <td>Script Description:${script.documentDescp}</td></tr><br/><br/>
       
 
         </c:forEach>
         
    <h4>Tools Used For Application Benchmarking</h4><br/><br/>
    
    <label>New Relic</label> <label>link for new relic</label> <label>Image for new relic</label>
       
          <b><a href="${pageContext.request.contextPath}/homepage">Home</a></b>
         
    </form:form>
    
</body>
</html>