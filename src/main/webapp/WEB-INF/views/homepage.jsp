<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="en">

<head>


    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>WebApp</title>

    <!-- Bootstrap Core CSS -->
    <link href='${pageContext.request.contextPath}/css/bootstrap.min.css' rel="stylesheet">

    <!-- Custom CSS -->
    <link href='${pageContext.request.contextPath}/css/business-casual.css' rel="stylesheet">
    <!-- Homepage CSS -->

<link href="${pageContext.request.contextPath}/css/homepage.css" rel="stylesheet">

    <!-- Fonts -->
    <link href="http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800" rel="stylesheet" type="text/css">
    <link href="http://fonts.googleapis.com/css?family=Josefin+Slab:100,300,400,600,700,100italic,300italic,400italic,600italic,700italic" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

	<div class="brand">Image Web Application</div>
    <div class="container">

        <div class="row">
            <div class="box">
                <div class="col-lg-12 text-center">
                    <div id="carousel-example-generic" class="carousel slide">
                        <!-- Indicators -->
                        <ol class="carousel-indicators hidden-xs">
                            <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                            <li data-target="#carousel-example-generic" data-slide-to="1"></li>
                            <li data-target="#carousel-example-generic" data-slide-to="2"></li>
                        </ol>

                        <!-- Wrapper for slides -->
                        <div class="carousel-inner">
                            <div class="item active">
                                <img class="img-responsive img-full" src='${pageContext.request.contextPath}/img/slide-1.jpg' alt="">
                            </div>
                            <div class="item">
                                <img class="img-responsive img-full" src='${pageContext.request.contextPath}/img/slide-2.jpg' alt="">
                            </div>
                            <div class="item">
                                <img class="img-responsive img-full" src='${pageContext.request.contextPath}/img/slide-3.jpg' alt="">
                            </div>
                        </div>

                        <!-- Controls -->
                        <a class="left carousel-control" href="#carousel-example-generic" data-slide="prev">
                            <span class="icon-prev"></span>
                        </a>
                        <a class="right carousel-control" href="#carousel-example-generic" data-slide="next">
                            <span class="icon-next"></span>
                        </a>
                    </div>
                    
    <div class = "formcontainer">
	<div class = "homepage">
	
        <form:form id="homepage" name="homepage" action="homepage" onsubmit="return validateForm();" method="post"
        commandName="homepageDetails">
        <c:set var="isUserlogin" scope="session" value="${homepageDetails.isUser}"/>
         <c:choose>
         
      <c:when test="${isUserlogin=='true'}">
      <p><label>Hello,
      <%= session.getAttribute("USERFIRSTNAME") %></label><br/>
      
      <b><a href='${pageContext.request.contextPath}/uploadImageN' >Upload Images</a></b>
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      <b><a href='${pageContext.request.contextPath}/userpage/<%= session.getAttribute("USERID") %>/?brief=true' >View Your Account</a></b>
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      <b><a href="${pageContext.request.contextPath}/logout" >LogOut</a></b>
      </p>
      </c:when>

      <c:otherwise>
     <p><label>Welcome Guest,</label>
     <br/>
      <b><a href='${pageContext.request.contextPath}/signUp' >SignUp</a></b> &nbsp;&nbsp;&nbsp;&nbsp;
      <b><a href='${pageContext.request.contextPath}/login' >SignIn</a></b>
      <br /></p>
      </c:otherwise>
      </c:choose>
       <p><h3>Explore WebApp</h3></div>
         
           
              
                <p><form:input path="searchText" class = "search" type="text" placeholder = "Type here to search images"></form:input></p>
                <br/>
            <div class = "buttonholder">
            <p><input type="submit" class = "search"
                    value="SEARCH"  /></p>
            </div>   
          
              <p><label>${Message}</label></p>
            
        

    	</form:form>
    
          </div>      
            </div>
        </div>

      </div>  
  
</div>
    <!-- jQuery -->
    <script src='${pageContext.request.contextPath}/js/jquery.js'></script>

    <!-- Bootstrap Core JavaScript -->
    <script src='${pageContext.request.contextPath}/js/bootstrap.min.js'></script>

    <!-- Script to Activate the Carousel -->
    <script>
	$('.carousel').carousel({
        interval: 2000 //changes the speed
    })
     
    function validateForm() 
{
	var searchText = document.homepage.searchText.value;
	
	
	if (searchText.length == 0) 
		{
		alert("search text can't be empty");
	    return false;
	    }

	
    return true;
}
    </script>
<!-- jQuery -->
    <script src="js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.min.js"></script>
</body>

</html>


