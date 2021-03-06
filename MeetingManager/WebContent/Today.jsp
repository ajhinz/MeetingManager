<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<t:template>
	<jsp:attribute name="title">
		<s:property value="day" /> Schedule
	</jsp:attribute>
    <jsp:body>
		<p>
        	<s:url action="today" var="yesterday">
        		<s:param name="day"><s:date name="yesterday" format="yyyyMMdd" /></s:param>
        	</s:url>
        	<s:url action="today" var="tomorrow">
        		<s:param name="day"><s:date name="tomorrow" format="yyyyMMdd" /></s:param>
        	</s:url>
			<a href="${yesterday}">&lt;&lt;</a>
			<s:property value="day" /> Schedule
			<a href="${tomorrow}">&gt;&gt;</a>
		</p>
		<ol>
			<s:iterator value="schedule">
				<li>
					<s:url action="meeting" var="meetingUrl">
						<s:param name="id" value="id" />
					</s:url>
					<a href="${meetingUrl}">
						<s:date name="startTime" format="hh:mm a" /> - 
						<s:date name="endTime" format="hh:mm a" />
					</a>
				</li>	
			</s:iterator>
		</ol>
    </jsp:body>
</t:template>
