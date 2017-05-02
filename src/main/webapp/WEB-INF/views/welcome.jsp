<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="baseURL" value="${pageContext.request.localName}"/>
<c:set var="basePort" value="${pageContext.request.localPort}"/>

<t:applicationLayout>
    <h1>Available Applications</h1>
   	
   	<table id="applicationTable" class="table table-striped">
  		<c:forEach items="${appList}" var="app">
  			<c:if test="${app.active || admin}">
	            <tr>
	                <td><c:out value="${app.name}" /></td>
	                <td><a href="/cloudteam6/loadApp?appName=<c:out value="${app.name}"/>">Start</a></td>
	                <td><img src="http://${baseURL}:${basePort}<c:out value="${app.applicationImageURL}" />"></td>
	            </tr>
            </c:if>
        </c:forEach>
	</table>    
    <!-- Delete this later, only there for the purpose of testing the peanut bank REST endpoints -->
    <input type="text" id="amount" name="amount" placeholder="type an amount here"/>
    <button id="deposit" class="btn btn-success">Deposit</button>
    <button id="charge" class="btn btn-danger">Charge</button>
    <input type="hidden" id="username" name="username" value="${pageContext.request.userPrincipal.name}"/>
    
    <script>
    $(function() {
    	function sendAjax(transaction) {
            $.ajax({
                url: "/cloudteam6/" + transaction,
                type: 'post',
                data: JSON.stringify({
                    amount: $("#amount").val(),
                    username: $("#username").val()
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
