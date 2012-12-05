<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>

<t:template>
	<jsp:attribute name="title">
		Schedule a Meeting
	</jsp:attribute>
    <jsp:body>
        <h3>Schedule a meeting</h3>
        <s:form action="save_meeting" method="get">
	        <table border=1>
	        	<tr>
	        		<td>Date</td>
	        		<td>
	        			<sx:datetimepicker name="date" id="date" type="date" displayFormat="MM/dd/yyyy" value="%{'today'}" />
	        		</td>
	        	</tr>
	        	<tr>
	        		<td>Start time</td>
	        		<td>
	        			<sx:datetimepicker name="startTime" id="startTime" type="time" displayFormat="HH:mm"/>
	        		</td>
	        	</tr>
	        	<tr>
	        		<td>End time</td>
	        		<td>
						<sx:datetimepicker name="endTime" id="endTime" type="time" displayFormat="HH:mm" />
					</td>
	        	</tr>
	        	<tr>
	        		<td></td>
	        		<td>
	        			<input type="button" onclick="updateLocations()" value="Find available location" />
	        			
	        			
	        		</td>
	        	</tr>
	        	<tr>
	        		<td>Location</td>
	        		<td><select id="location" name="location"></select>
	        	</tr>
	        	<tr>
        			<s:checkboxlist name="invitedEmployees" list="allEmployees" listValue="name" listKey="id" label="Invited to attend" />
	        	</tr>
	        	<tr>
	        		<s:submit value="Create meeting" />
	        	</tr>
	        </table>
	    </s:form>
	    <script>
	    	function createXHR() {
	        	var xhr;
	        	if (window.ActiveXObject) {
	            	try {
	                	xhr = new ActiveXObject("Microsoft.XMLHTTP");
	            	}
	            	catch(e) {
	                	alert(e.message);
	                	xhr = null;
	            	}
	        	}
	        	else {
	            	xhr = new XMLHttpRequest();
	        	}
		        return xhr;
		    }
	    
	    	function zeroFill( number, width ) {
	      		width -= number.toString().length;
	      		if ( width > 0 ) {
	        		return new Array( width + (/\./.test( number ) ? 2 : 1) ).join( '0' ) + number;
	      		}
	      		return number + ""; // always return a string
	    	}
	    
	    	function updateLocations() {
	    		var date = dojo.widget.byId("date").getDate();
	    		var dateStr = '' + date.getFullYear() + zeroFill(date.getMonth() + 1, 2) + zeroFill(date.getDate(), 2); 
	    		
	    		var startTime = new Date(dojo.widget.byId("startTime").getTime());
	    		var startTimeStr = '' + zeroFill(startTime.getHours(), 2) + zeroFill(startTime.getMinutes(), 2);  
	    		
	    		var endTime = new Date(dojo.widget.byId("endTime").getTime());
	    		var endTimeStr = '' + zeroFill(endTime.getHours(), 2) + zeroFill(endTime.getMinutes(), 2);
	    		
	    		var url = "/MeetingManager/available_locations.action?date=" + dateStr + "&startTime=" + startTimeStr + "&endTime=" + endTimeStr;
	    		var xhr = createXHR();
	    		xhr.onreadystatechange = function () {
	    			if (xhr.readyState == 4) {
	    				var locationSelect = document.getElementById('location');
	    				while (locationSelect.hasChildNodes()) {
	    					locationSelect.removeChild(locationSelect.lastChild);
	    				}
	    				var results = JSON.parse(xhr.responseText);
	    				var availableLocations = results.availableLocations;
	    				var len = availableLocations.length;
	    				for (var i = 0; i < len; i++) {
	    					var option = document.createElement("option");
	    					option.setAttribute("value", availableLocations[i].id);
	    					option.innerHTML = availableLocations[i].name;
	    					locationSelect.appendChild(option);
	    				}
	    			}
	    		}
	    		xhr.open('GET', url, true);
	    		xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
	    		xhr.send();
	    	}

	    	
	    </script>
    </jsp:body>
</t:template>
