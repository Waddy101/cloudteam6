<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:applicationLayout>
    <h1>Available Applications</h1>
   	
   	<table id="applicationTable" class="table table-striped">
   		<thead>
   			<tr>
	   			<th>Application Name</th>
	   			<th>Pricing Information</th>
	   			<th>Application Options</th>
   			</tr>
  		</thead>
  		<tbody>
  			<tr>
	  			<td>Hello World</td>
	  			<td>10p per MB used</td>
	  			<td><a href="${contextPath}/cloudteam6/loadApp?appName=HelloWorld">Start</a></td>
  			</tr>
		</tbody>
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
