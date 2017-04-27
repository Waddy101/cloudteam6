<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:applicationLayout>
    <h1>Welcome</h1>
    
    <input type="text" id="amount" name="amount" placeholder="type an amount here"/>
    <button id="deposit" class="btn btn-success">Deposit</button>
    <button id="charge" class="btn btn-danger">Charge</button>
    <input type="hidden" id="username" name="username" value="${pageContext.request.userPrincipal.name}"/>
    <input type="hidden" id="csrf" name="${_csrf.headerName}" value="${_csrf.token}"/>
    
    <script>
    $(function() {
    	const headerName = $("#csrf").attr("name");
    	const token = $("#csrf").val();
    	let myHeader = {};
    	
    	myHeader[headerName] = token;
    	
    	function sendAjax(transaction) {
            $.ajax({
                url: "/cloudteam6/" + transaction,
                type: 'post',
                data: JSON.stringify({
                    amount: $("#amount").val(),
                    transaction: transaction,
                    username: $("#username").val()
                }),
                contentType: "application/json",
                dataType: "json",
                headers: myHeader,
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
