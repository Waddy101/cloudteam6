<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<t:applicationLayout>
	<link href="https://gitcdn.github.io/bootstrap-toggle/2.2.2/css/bootstrap-toggle.min.css" rel="stylesheet">
	<script src="https://gitcdn.github.io/bootstrap-toggle/2.2.2/js/bootstrap-toggle.min.js"></script>
	<script>
	$(document).ready(function(){
	    var otherHeight = $("#navbar").outerHeight(true);
		$(window).resize(function() {
			$('#appFrame').height( $(window).height() - otherHeight );
		}).resize();
		$.urlParam = function(name){
		    var results = new RegExp('[\?&]' + name + '=([^&#]*)').exec(window.location.href);
		    if (results==null){
		       return null;
		    }
		    else{
		       return results[1] || 0;
		    }
		};
		$.sendAjax = function sendAjax(val) {	
			$.post("/cloudteam6/toggleApp", {active: !val, appName: $.urlParam('appName')})
        };
		
	    $.appToggle = function(element){
	       	$.sendAjax(!element.checked);
	    };
	    

	});
	
	window.onload = function(){
		document.forms['userForm'].submit()
	}
	</script>
	<c:choose>
		<c:when test="${admin}">
			<div class="row">
				<div class="col-xs-8 col-xs-offset-2">
					<div class="col-xs-4 text-center">
						Viewing application in administration mode
					</div>
					<div class="col-xs-4 pull-right">
						<p> Toggle activation of app: <input ${active} data-toggle="toggle" onchange="$.appToggle(this)" id="appToggle" type="checkbox"></p>
					</div>		
				</div>
			</div>
		</c:when>
		<c:when test="${dev}">
			<div class="row">
				<div class="col-xs-8 col-xs-offset-2">
					<div class="col-xs-4 text-center">
						Viewing application in developer mode
					</div>
					<div class="col-xs-4 text-center">
						Active notice here
					</div>		
				</div>
			</div>
		</c:when>
	</c:choose>
	<iframe id="appFrame" width=100% height=100% src="${contextPath}${appURL}">
		Please use a browser that supports iFrame to access applications on our website
	</iframe>
</t:applicationLayout>