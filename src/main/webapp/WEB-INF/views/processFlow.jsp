<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>WebPortal</title>
</head>
<body>
<h2>Process Flow Page</h2>
    <form:form id="processFlow"  commandName="listProcessFlow">

    <br />
    <h4>Process Overview, Used For Application Benchmarking</h4><br/><br/>
             <c:forEach items="${listProcessFlow}" var="process" varStatus="loop">
            
             <tr>
             <td>${loop.count}</td>
              <td>Process Flow Name:${process.documentName}</td>
              <c:choose>
      <c:when test="${process.isPdf eq 'yes'}">
        <td><a href="${pageContext.request.contextPath}/viewFile-${process.docid}">View Process Flow</a></td>
         </c:when>
         </c:choose>
          
            <td><a href="${pageContext.request.contextPath}/downloadFile-${process.docid}">Export Process Flow</a></td>
        </tr><br/>
        <tr>
        <td>Process Description:${process.documentDescp}</td></tr><br/><br/>
       
 
         </c:forEach>

          <b><a href="${pageContext.request.contextPath}/homepage">Home</a></b>
         
    </form:form>
    
</body>
</html>