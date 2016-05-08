<%@    taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>WebPortal</title>
</head>
<body>
<h2>Result Page</h2>
    <form:form id="result"  commandName="listgraphs">

    <br />
    <h4>Graphs</h4><br/><br/>
    
             <c:forEach items="${listgraphs}" var="graph" varStatus="graphno">
             <br/>
               ${graph} <br/>
            <%-- <c:choose>
         
         <c:when test="${graphno.count % 2 == 0}">
           <br/>
                 <br/>
              
         </c:when>

      
      </c:choose> --%>
          
 
         </c:forEach>
         
         
          <c:forEach items="${listloads}" var="load" varStatus="loadno">
             <br/>
             
            ${load}
            <br/>
             <img src='${pageContext.request.contextPath}/img/${load}' alt="">
                <br/>
          
         </c:forEach>
         

          <b><a href="${pageContext.request.contextPath}/homepage">Home</a></b>
     
    </form:form>
    
</body>
</html>


  