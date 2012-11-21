<%@ page import="com.charitychamp.Group" %>
<g:applyLayout name="groupLayout">
<html>
	<head>
		
		
	</head>
   			<body>
   					<div>
   						<br/>
						<div id="groupLeaders">
							Your Operation Feed Leaders are: <br/><br/>
							Leader - ${groupInstance.department?.office?.business?.charityLeader} (${groupInstance.department?.office?.business?.charityLeader?.userId}) - ${groupInstance.department?.office?.business?.charityLeader?.email}
							<br/>
									
							Captain - ${groupInstance.department?.office?.charityCaptain} (${groupInstance.department?.office?.charityCaptain?.userId}) - ${groupInstance.department?.office?.charityCaptain?.email}
							<br/>
							
							Lieutenant - ${groupInstance.department?.charityLieutenant} (${groupInstance.department?.charityLieutenant?.userId}) - ${groupInstance.department?.charityLieutenant?.email}
						
						</div>	
						<br/>
						<div id="groupOverViewActions" class="opFeedManagement">
							<g:link class="linkButton" controller="group" action="activities" id="${groupInstance.id}" >Activities</g:link>
							<g:actionSubmit class="buttons" value="Activities" controller="activity" action="list" />
							<g:actionSubmit class="buttons" value="Food Bank Shifts" action="Update" />
							<g:actionSubmit class="buttons" value="Jeans" action="Update" />
						</div>
					
					</div>
				
   		</body>
</html>
</g:applyLayout>