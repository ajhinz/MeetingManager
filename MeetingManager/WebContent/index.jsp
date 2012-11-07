<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Start Page</title>
<link rel="stylesheet" type="text/css" href="static/style.css">
</head>
<body>
	<div id="heading">Meeting Manager</div>

	<ol id="top_menu">
		<li>Today</li>
		<li>Schedule a Meeting</li>
		<li>Search</li>
		<li>Team</li>
		<li><a href="<s:url action='admin'/>">Admin</a></li>
	</ol>

	<div style="clear:both" id="main">
		Welcome to the site.
	</div>

</body>
</html>