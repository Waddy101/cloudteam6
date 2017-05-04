<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<t:applicationLayout>
	<div class="container">
		<div class="row">
			<div class="col-md-6 col-md-offset-3">
				<div class="panel panel-default">
		 			<div class="panel-heading">
		 				<h3 class="panel-title">Create your account</h3>
		 			</div>
		 			<div class="panel-body">
		 			 <form:form method="POST" modelAttribute="userForm" class="form-signin">
				        <spring:bind path="username">
				            <div class="form-group ${status.error ? 'has-error' : ''}">
				                <form:input type="text" path="username" class="form-control" placeholder="Username"
				                            autofocus="true"></form:input>
				                <form:errors path="username"></form:errors>
				            </div>
				        </spring:bind>
				        
				        <spring:bind path="firstname">
				            <div class="form-group ${status.error ? 'has-error' : ''}">
				                <form:input type="text" path="firstname" class="form-control" placeholder="First Name"
				                            autofocus="true"></form:input>
				                <form:errors path="firstname"></form:errors>
				            </div>
				        </spring:bind>
				        
				        <spring:bind path="lastname">
				            <div class="form-group ${status.error ? 'has-error' : ''}">
				                <form:input type="text" path="lastname" class="form-control" placeholder="Last Name"
				                            autofocus="true"></form:input>
				                <form:errors path="lastname"></form:errors>
				            </div>
				        </spring:bind>
				
				        <spring:bind path="password">
				            <div class="form-group ${status.error ? 'has-error' : ''}">
				                <form:input type="password" path="password" class="form-control" placeholder="Password"></form:input>
				                <form:errors path="password"></form:errors>
				            </div>
				        </spring:bind>
				        
				        <spring:bind path="passwordConfirm">
				            <div class="form-group ${status.error ? 'has-error' : ''}">
				                <form:input type="password" path="passwordConfirm" class="form-control"
				                            placeholder="Confirm your password"></form:input>
				                <form:errors path="passwordConfirm"></form:errors>
				            </div>
				        </spring:bind>
				
				        <button class="btn btn-lg btn-primary btn-block" type="submit">Create Account</button>
				    </form:form>
		 			</div>
	 			</div>
			</div>
		</div>
	</div>
</t:applicationLayout>