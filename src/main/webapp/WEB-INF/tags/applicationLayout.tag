<!DOCTYPE html>

<%@tag description="Default Page template" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@attribute name="title" required="false"%>

<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Cloud Platform</title>
	<link href="${pageContext.request.contextPath}/resources/css/application.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/resources/css/login.css" rel="stylesheet">
	<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css" rel="stylesheet">
	<!--[if lt IE 9]>
	    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
	    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
	    <![endif]-->
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script type="text/javascript" src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/angularjs/1.5.8/angular.min.js"></script>
	<script type="text/javascript" src="https://cdn.rawgit.com/angular-ui/bower-ui-grid/master/ui-grid.min.js"></script>
	<sec:csrfMetaTags/>
</head>
<body>
	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
					aria-expanded="false">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="/cloudteam6">Cloud6</a>
			</div>
			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav navbar-right">
					<li>
						<a href="/cloudteam6/users">User Management</a>
					</li>
					<c:if test="${canupload}">
						<li class="dropdown"><a href="#" class="dropdown-toggle" id="left-dropdown"
							data-toggle="dropdown" role="button" aria-haspopup="true"
							aria-expanded="false">Upload<span class="caret"></span></a>
							<ul class="dropdown-menu" id="left-dropdown">
								<li><form:form method="post" action="upload" enctype="multipart/form-data"> 
									<p>
										Application name: <input type="text" name="name" />
									</p>
									<p> 
										Select Image to represent your application: <input type="file" name="imagefile" size="60" />
									</p>
									<p>
										Select file to upload: <input type="file" name="file" size="60" />
									</p>
									<p>
										<input type="submit" value="Upload" />
									</p>
								</form:form></li>
							</ul>
						</li>
					</c:if>
					<li class="dropdown"><a href="#" class="dropdown-toggle" id="left-dropdown"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false">Apps<span class="caret"></span></a>
						<ul class="dropdown-menu" id="left-dropdown">
							<c:choose>
								<c:when test="${appList.size() > 0}" >
									<c:forEach items="${appList}" var="app">
							  			<c:if test="${app.active or admin}">
							  				<li>
								        		<a href="/cloudteam6/loadApp?appName=<c:out value="${app.name}"/>">${app.name}</a>  
							            	<li>
							            </c:if>
							        </c:forEach>
						        </c:when>
						        <c:otherwise>
						        	<li>No Apps exist</li>
						        </c:otherwise>
					        </c:choose>
						</ul>
					</li>
					<c:choose>
						<c:when test="${pageContext.request.userPrincipal.name != null}">
				        	<li>
					        	<%@ include file="/WEB-INF/views/logout.jsp" %>
					        	<h3>Welcome ${pageContext.request.userPrincipal.name} | <a onclick="document.forms['logoutForm'].submit()">Logout</a></h3>		
								<h4>${peanutBalance}</h4>
							</li>
						</c:when>
						<c:otherwise>
							<li class="dropdown"><a href="#" class="dropdown-toggle" id="left-dropdown"
							data-toggle="dropdown" role="button" aria-haspopup="true"
							aria-expanded="false">Login<span class="caret"></span></a>
								<ul class="dropdown-menu" id="left-dropdown">
									<li><form method="POST" action="${contextPath}/cloudteam6/login" class="form-signin">
								        <h2 class="form-heading">Log in</h2>
								        <div class="form-group ${error != null ? 'has-error' : ''}">
								            <span>${message}</span>
								            <input name="username" type="text" class="form-control" placeholder="Username"/>
								            <input name="password" type="password" class="form-control" placeholder="Password"/>
								            <span>${error}</span>
								            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
								
								            <button class="btn btn-lg btn-primary btn-block" type="submit">Log In</button>
								            <h4 class="text-center"><a href="${contextPath}/cloudteam6/registration">Create an account</a></h4>
								        </div>
									</form></li>
								</ul>	
							</li>
						</c:otherwise>
					</c:choose>
				</ul>
			</div>
		</div>
	</nav>
	<div class="container">
		<h3 class="text-muted">Sheffield Student Life Planner</h3>
	</div>
	
	<jsp:doBody />
</body>
</html>