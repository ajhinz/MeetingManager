<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ tag description="Overall Page template" pageEncoding="UTF-8"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ attribute name="title" fragment="true" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><jsp:invoke fragment="title"/></title>
<link rel="stylesheet" type="text/css" href="static/style.css">
<sx:head/>
</head>
<body>
	<div id="heading">Meeting Manager</div>

	<ol id="top_menu">
		<li><a href="<s:url action='today' />">Today</a></li>
		<li><a href="<s:url action='schedule' />">Schedule a Meeting</a></li>
		<li><a href="<s:url action='invites' />">Invites</a></li>
		<li><a href="<s:url action='admin' />">Admin</a></li>
		<li><a href="<s:url action='login' />">Logout</a></li>
	</ol>

	<div style="clear:both" id="main">
		<jsp:doBody/>
	</div>

</body>
</html>