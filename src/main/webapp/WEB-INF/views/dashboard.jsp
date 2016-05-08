<%@    taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>WebPortal</title>
</head>
<body>
<h2>Result Page</h2>
    <form:form id="dashboard"  commandName="listgraphs">
    
    

    <br />
    <h4>Graphs</h4><br/><br/>
    
    <table>
    <tr><b><label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:: JVM - Average Response Time for Web Transactions for App Server ::</label></b></tr><br/><br/>
    <tr>
   <b><b> <label>Dedicated Virtual Machine 
   </label></b></b>
   
   
   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    
   
   <b><b> <label>Amazon Web Service Machine
   </label></b></b>
   
    <td><input type="hidden"/><td><input type="hidden"/><td>    
    <td><input type="hidden"/><td><input type="hidden"/><td>    
    <td><input type="hidden"/><td><input type="hidden"/><td>
    
        <td><iframe src="https://rpm.newrelic.com/public/charts/4kfZ9kvsZZ7" width="450" height="300" scrolling="no" frameborder="no"></iframe>
    </td>
    <td><input type="hidden"/><td><input type="hidden"/><td>    
    <td><input type="hidden"/><td><input type="hidden"/><td>    
    <td><input type="hidden"/><td><input type="hidden"/><td>
    
 
    <td><iframe src="https://rpm.newrelic.com/public/charts/5YJkVlKYqcY" width="450" height="300" scrolling="no" frameborder="no"></iframe>
    </td>
    </tr>
    
    
     
    
    
    </table>
    
    <br/><br/>
    
      <table>
    <tr><b><label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:: Memory Usage Monitoring  ::</label></b></tr><br/><br/>
    <tr>
   <b><b> <label>Docker on Google Cloud Platform 
   </label></b></b>
   
   
   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    
   
   <b><b> <label>Amazon Web Service Machine
   </label></b></b>
   
    <td><input type="hidden"/><td><input type="hidden"/><td>    
    <td><input type="hidden"/><td><input type="hidden"/><td>    
    <td><input type="hidden"/><td><input type="hidden"/><td>
    
        <td><iframe src="https://rpm.newrelic.com/public/charts/9pGs8vJPwn8" width="450" height="300" scrolling="no" frameborder="no"></iframe>

    </td>
    <td><input type="hidden"/><td><input type="hidden"/><td>    
    <td><input type="hidden"/><td><input type="hidden"/><td>    
    <td><input type="hidden"/><td><input type="hidden"/><td>
    
 
    <td><iframe src="https://rpm.newrelic.com/public/charts/g4JAVpAIiQ4" width="450" height="300" scrolling="no" frameborder="no"></iframe>

    </td>
    </tr>
    
    
     
    
    
    </table>
    
    
    
    
    
    
             <c:forEach items="${listgraphs}" var="graph" varStatus="graphno">
             <br/>
               ${graph} <br/>
            <c:choose>
         
         <c:when test="${graphno.count % 2 == 0}">
           <br/>
                 <br/>
              
         </c:when>

      
      </c:choose> 
          
 
         </c:forEach>
         
         
        
          <b><a href="${pageContext.request.contextPath}/selection">EXPLORE</a></b>
     
    </form:form>
    
</body>
</html>


  