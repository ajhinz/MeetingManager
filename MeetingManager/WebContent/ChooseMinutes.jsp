<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<t:template>
	<jsp:attribute name="title">
		Meeting
	</jsp:attribute>
    <jsp:body>
		<p>
			You are about to overwrite minutes that you already saved.
		</p>
		<p>
			<table border=1>
				<tr>
					<td>Original minutes</td>
					<td></td>
				</tr>
				<tr>
					<td><s:property value="originalMinutes" /></td>
					<td><s:url action="meeting" var="meetingUrl">
							<s:param name="id" value="meeting.id" />
						</s:url>
						<a href="${meetingUrl}">Keep original minutes</a>
					</td>
				</tr>
			</table>
		</p>
		<p>
			<table border=1>
				<tr>
					<td>Your new minutes</td>
					<td></td>
				</tr>
				<tr>
					<td><s:property value="meeting.minutes" /></td>
					<td>
						<s:form action="save_minutes">
							<s:hidden name="id" value="%{meeting.id}" />
							<s:hidden name="overwrite" value="1" />
							<s:hidden name="minutes" value="%{meeting.minutes}" />
							<s:submit value="Save new minutes" />
						</s:form>
					</td>
				</tr>
			</table>
		</p>
    </jsp:body>
</t:template>
