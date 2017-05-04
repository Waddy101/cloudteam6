<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
 <t:applicationLayout>
 	<div class="container">
		<div class="row">
			<div class="col-md-6 col-md-offset-3">
				<div class="panel panel-default">
		 			<div class="panel-heading">
		 				<h3 class="panel-title">Log In</h3>
		 			</div>
		 			<div class="panel-body">
		 				<form method="POST" action="${contextPath}/cloudteam6/login" class="form-horizontal">
					        <div class="form-group col-md-12" ${error != null ? 'has-error' : ''}">
					            <span>${message}</span>
	    						<div class="form-group">
		    						<div class="col-sm-12">
		      							<input name="username" type="text" class="form-control" id="username" placeholder="Username">
		    						</div>
		    					</div>
	    						<div class="form-group">
		    						<div class="col-sm-12">
		      							<input name="password" type="password" class="form-control" id="password" placeholder="Password">
		    						</div>
		    					</div>
					            <span>${error}</span>
					            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
					            <div class="col-md-6 col-md-offset-3">
					            	<button class="btn btn-lg btn-primary btn-block " type="submit">Log In</button>
					            	<h4 class="text-center"><a href="${contextPath}/cloudteam6/registration">Create an account</a></h4>
					            </div>
					        </div>
						</form>
		 			</div>
				</div>
			</div>
		</div>
	</div>
</t:applicationLayout>