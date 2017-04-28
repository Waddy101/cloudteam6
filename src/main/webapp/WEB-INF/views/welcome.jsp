<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:applicationLayout>
    <h1>Welcome</h1>
    
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
