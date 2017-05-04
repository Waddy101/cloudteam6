<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="baseURL" value="${pageContext.request.localName}"/>
<c:set var="basePort" value="${pageContext.request.localPort}"/>

<t:applicationLayout>
   	<div class="row">
  		<div class="col-md-6 col-md-offset-3">
  			<h1 class="central">App Manager</h1>
  		</div>
	</div>
	<div class="row">
		<div class="col-md-8 col-md-offset-2">
			<div class="panel panel-default">
  				<div class="panel-heading">
  					<h3 class="panel-title">Available Apps</h3>
  				</div>
				<div class="panel-body remove-padding">
    				<div class="table-responsive">
  						<table class="table table-bordered table-hover remove-margin">
  							<tr>
    							<th>App Name</th>
    							<th class="text-center">Image</th> 
    							<th class="text-center">Actions</th>
  							</tr>
  							<c:forEach var="app" items="${appList}">
  								<c:if test="${app.active or admin}">
  									<tr>
	  									<td><c:out value="${app.name}"/></td>
	  									<td class="central"><img src="http://${baseURL}:${basePort}<c:out value="${app.applicationimageurl}" />" height="240" width="240"></td>
						                <td>
						                	<form id="form" action="/cloudteam6/loadApp" method="POST" class="text-center">
					    						<input type="hidden" name="appName" value="${app.name}">
					    						<input class="btn btn-success btn-block" type="submit" value="Launch">
					    					</form> 
					    					<br>
					    					<c:if test="${admin}">
						    					<form id="form" action="/cloudteam6/deleteApp" method="POST" class="text-center">
						    						<input type="hidden" name="id" value="${app.id}">
						    						<input class="btn btn-danger btn-block" type="submit" value="Delete">
						    					</form>
						    				</c:if>	
					    				</td>
				    				</tr>	
						        </c:if> 
							</c:forEach>
  						</table>
					</div>
  				</div>
			</div>
		</div>
	</div> 
    <script>
    $(function() {
    	function sendAjax(transaction) {
            $.ajax({
                url: "/cloudteam6/" + transaction,
                type: 'post',
                data: JSON.stringify({
                    amount: 10,
                    username: "darren",
                    password: "darren123"
                }),
                contentType: "application/json",
                dataType: "json",
                success: function(data) {
                    console.log(data);
                }
            });
        }
    	$("#deposit").click(function() {
    		sendAjax("deposit");
    	});
    	$("#charge").click(function() {
    		sendAjax("charge");
    	});
    });
    </script>
    
</t:applicationLayout>
