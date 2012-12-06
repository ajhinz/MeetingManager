<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<t:template>
	<jsp:attribute name="title">
		Meeting Manager
	</jsp:attribute>
    <jsp:body>
        <p>Welcome to the site, <s:property value="user.name" /></p>
    </jsp:body>
</t:template>
