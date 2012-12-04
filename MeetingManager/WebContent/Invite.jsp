<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<t:template>
	<jsp:attribute name="title">
		<s:property value="day" /> Invites
	</jsp:attribute>
    <jsp:body>
    	<s:property value="message" />
		<ol>
			<s:iterator value="invites">
				<li>
					<s:url action="meeting" var="meetingUrl">
						<s:param name="id" value="meeting.id" />
					</s:url>
					<s:url action="is_attending" var="responseUrlYes">
						<s:param name="response" value="'yes'" />
						<s:param name="id" value="id" />
					</s:url>
					<s:url action="is_attending" var="responseUrlNo">
						<s:param name="response" value="'no'" />
						<s:param name="id" value="id" />
					</s:url>
					<a href="${meetingUrl}">
						<s:date name="meeting.startTime" format="hh:mm a" /> - 
						<s:date name="meeting.endTime" format="hh:mm a" /></a>
					Attending? <a href="${responseUrlYes}">Yes</a> or
					<a href="${responseUrlNo}">No</a>
				</li>
			</s:iterator>
		</ol>
			<s:if test="invites.size() == 0">
				There are no meeting invites awaiting your response.
			</s:if>
    </jsp:body>
</t:template>
