<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<t:template>
    <jsp:body>
<h1>Admin Actions</h1>
<p><a href="<s:url action='create_tables'/>">Create Tables</a></p>
<p><a href="<s:url action='sample_data'/>">Add Sample Data</a></p>
<p><a href="<s:url action='drop_tables'/>">Drop Tables</a></p>
    </jsp:body>
</t:template>




