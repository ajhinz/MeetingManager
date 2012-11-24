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
				<td><s:date name="meeting.startTime" format="hh:mm a" /></td>
			</tr>
			<tr>
				<td>Ends at</td>
				<td><s:date name="meeting.endTime" format="hh:mm a" /></td>
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
				<td><s:property value="meeting.minutes" /></td>
			</tr>
		</table>
    </jsp:body>
</t:template>
