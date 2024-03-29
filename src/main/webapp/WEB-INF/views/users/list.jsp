<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<t:applicationLayout>
	<script>
		$(document).ready(function(){
			$.sendAjax = function sendAjax(val, remove) {
				if (remove) {
					$.post("/cloudteam6/user/removeRole", {role: val})
				} else {
					$.post("/cloudteam6/user/addRole", {role: val})
				}
	        };
	        $('#makeDev').click(function() {
	        	$.sendAjax("ROLE_DEV", false);
	        	$(this).toggleClass("hidden");
	        	$('#removeDev').toggleClass("hidden");
	        });
	        $('#makeAdmin').click(function() {
	        	$.sendAjax("ROLE_ADMIN", false);
	        	$(this).toggleClass("hidden");
	        	$('#removeAdmin').toggleClass("hidden");
	        }); 
	        $('#removeDev').click(function() {
	        	$.sendAjax("ROLE_DEV", true);
	        	$(this).toggleClass("hidden");
	        	$('#makeDev').toggleClass("hidden");
	        });
	        $('#removeAdmin').click(function() {
	        	$.sendAjax("ROLE_ADMIN", true);
	        	$(this).toggleClass("hidden");
	        	$('#makeAdmin').toggleClass("hidden");
	        }); 
		});
	
	</script>
	<c:if test="${not empty msg}">
	    <div class="alert alert-${css} alert-dismissible" role="alert">
		<button type="button" class="close" data-dismiss="alert"
                               aria-label="Close">
			<span aria-hidden="true">×</span>
		</button>
		<strong>${msg}</strong>
	    </div>
	</c:if>
	<div class="row">
  		<div class="col-md-6 col-md-offset-3">
  			<h1 class="central">All Users</h1>
  		</div>
	</div>
	<br>
	<div class="row">
		<div class="col-md-6 col-md-offset-3">
			<div class="panel panel-default">
  				<div class="panel-heading">
  					<h3 class="panel-title">My Societies</h3>
  				</div>
				<div class="panel-body remove-padding">
    				<div class="table-responsive">
  						<table class="table table-bordered table-hover remove-margin">
  							<tr>
    							<th>#ID</th>
								<th>Username</th>
								<th>Actions</th>
  							</tr>
  							<c:forEach var="user" items="${users}">
							    <tr>
									<td>
										${user.id}
									</td>
									<td>${user.username}</td>
									<td>
										<c:choose>
											<c:when test="${user.roles.contains('ROLE_DEV')}">
												<button class="btn btn-primary" id="makeDev">Make Dev</button>
												<button class="btn btn-danger hidden" id="removeDev">Remove Dev</button>
											</c:when>
											<c:otherwise>
												<button class="btn btn-primary hidden" id="makeDev">Make Dev</button>
												<button class="btn btn-danger" id="removeDev">Remove Dev</button>
											</c:otherwise>
								        </c:choose>
								        <c:choose>
								        	<c:when test="${user.roles.contains('ROLE_ADMIN')}">
												<button class="btn btn-primary" id="makeAdmin">Make Admin</button>
												<button class="btn btn-danger hidden" id="removeAdmin">Remove Admin</button>
											</c:when>
											<c:otherwise>
												<button class="btn btn-primary hidden" id="makeAdmin">Make Admin</button>
												<button class="btn btn-danger" id="removeAdmin">Remove Admin</button>
											</c:otherwise>
								        </c:choose>   
									</td>
							    </tr>
							</c:forEach>
  						</table>
					</div>
  				</div>
			</div>
		</div>
	</div> 
</t:applicationLayout>