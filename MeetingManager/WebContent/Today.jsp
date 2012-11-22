<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<t:template>
	<jsp:attribute name="title">
		<s:property value="day" /> Schedule
	</jsp:attribute>
    <jsp:body>
        <p><s:property value="day" /> Schedule</p>
		<p>
        	<s:url action="today" var="yesterday">
        		<s:param name="day"><s:date name="yesterday" format="yyyyMMdd" /></s:param>
        	</s:url>
        	<s:url action="today" var="tomorrow">
        		<s:param name="day"><s:date name="tomorrow" format="yyyyMMdd" /></s:param>
        	</s:url>
			<a href="${yesterday}"><<</a>
			<a href="${tomorrow}">>></a>
		
		</p>
    </jsp:body>
</t:template>
