<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>WebPortal</title>
</head>
<body>
<h2>Reports Page</h2>
    <form:form id="reports"  commandName="listReports">

    <br />
    <h4>Reports</h4><br/><br/>
             <c:forEach items="${listReports}" var="report" varStatus="loop">
            
             <tr>
             <td>${loop.count}</td>
              <td>Report Name:${report.documentName}</td>
               <c:choose>
      <c:when test="${report.isPdf eq 'yes'}">
       <td><a href="${pageContext.request.contextPath}/viewReport-${report.docid}">View Report </a></td>
         </c:when>
         </c:choose>
            
            <td><a href="${pageContext.request.contextPath}/downloadReport-${report.docid}">Export Report </a></td>
        </tr><br/>
        <tr>
        <td>Report Description:${report.documentDescp}</td></tr><br/><br/>
       
 
         </c:forEach>

          <b><a href="${pageContext.request.contextPath}/homepage">Home</a></b>
         
    </form:form>
    
</body>
</html>