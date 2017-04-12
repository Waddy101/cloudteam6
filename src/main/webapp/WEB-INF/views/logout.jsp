<form id="logoutForm" method="POST" action="${contextPath}/cloudteam6/logout">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>