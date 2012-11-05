<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Meeting Manager</title>
</head>
<body>
<h1>Admin Actions</h1>
<p><a href="<s:url action='create_tables'/>">Create Tables</a></p>
<p><a href="<s:url action='sample_data'/>">Add Sample Data</a></p>
<p><a href="<s:url action='drop_tables'/>">Drop Tables</a></p>
</body>
</html>