<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<t:template>
	<jsp:attribute name="title">
		Meeting Manager
	</jsp:attribute>
    <jsp:body>
        <p>Welcome to the site.  Please sign in.</p>
        <s:form action="save_login">
	        <s:select list="allEmployees" listKey="id" listValue="name" name="employeeId" />
	        <s:submit value="Login" />
	    </s:form>	
    </jsp:body>
</t:template>
