<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<script>
$(function() {
    var otherHeight = $("#navbar").outerHeight(true);
    $(window).resize(function() {
        $('#frame').height( $(window).height() - otherHeight );
    }).resize();
});
</script>

<t:applicationLayout>
	<iframe id="appFrame" src="${contextPath}/HelloWorld" width=100% height=100%>
		Please use a browser that supports iFrame to access applications on our website
	</iframe>
</t:applicationLayout>