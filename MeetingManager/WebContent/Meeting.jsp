<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<t:template>
	<jsp:attribute name="title">
		Meeting
	</jsp:attribute>
    <jsp:body>
		<table border=1>
			<tr>
				<td>Starts at</td>
				<td><s:date name="meeting.startTime" format="MM/dd/YYYY hh:mm a" /></td>
			</tr>
			<tr>
				<td>Ends at</td>
				<td><s:date name="meeting.endTime" format="MM/dd/YYYY hh:mm a" /></td>
			</tr>
			<tr>
				<td>Created by</td>
				<td><s:property value="meeting.createdBy.name" /></td>
			</tr>
			<tr>
				<td>Location</td>
				<td><s:property value="meeting.location.name" />, 
					<s:property value="meeting.location.city" /></td>
			</tr>
			<tr>
				<td>Minutes</td>
				<td>
					<s:if test="meeting.hasMinutes">
						<s:property value="meeting.minutes" />
					</s:if>
					<s:elseif test="currentUser.id == meeting.createdBy.id">
						<s:form action="save_minutes">
							<s:hidden name="id" value="%{meeting.id}" />
							<s:textarea name="minutes" cols="50" rows="6" />
							<s:submit />
						</s:form>
					</s:elseif>
					<s:else>
						[No Minutes]
					</s:else>
				</td>
			</tr>
		</table>
    </jsp:body>
</t:template>
