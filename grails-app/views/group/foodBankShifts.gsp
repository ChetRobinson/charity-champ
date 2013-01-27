<%@ page import="com.charitychamp.VolunteerShift" %>
<g:applyLayout name="groupLayout">
<html>
	<head>
	
		<title>Food Bank Shifts</title>
	</head>
	<body>
		
		<div id="list-volunteerSHift" class="content" role="main">
			<h2>Food Bank Shifts</h2><br/>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
		
			<g:link class="breadCrumbButton" controller="group" action="addFoodBankShift" id="${groupInstance.id}" >Add New Shift</g:link><br/><br/>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="donationDate" title="${message(code: 'volunteerShift.donation.date.label', default: 'Shift Date')}" />
					
						<g:sortableColumn property="numberOfVolunteers" title="${message(code: 'volunteerShift.numberOfVolunteers.label', default: 'Number Of Volunteers')}" />
					
						<g:sortableColumn property="comments" title="${message(code: 'volunteerShift.comments.label', default: 'Comments')}" />
					
						<g:sortableColumn property="leaderName" title="${message(code: 'volunteerShift.leaderName.label', default: 'In Charge')}" />
					
						<g:sortableColumn property="mealFactor" title="${message(code: 'volunteerShift.mealFactor.label', default: 'Meal Factor')}" />
						
						<g:sortableColumn property="numberOfMeals" title="${message(code: 'activity.number.of.meals.label', default: 'Number Of Meals')}" />
					
						
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${foodBankShiftList}" status="i" var="foodBankShiftInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="editFoodBankShift" id="${foodBankShiftInstance.id}"  params="[groupId : groupInstance.id]" ><g:formatDate format="yyyy-MM-dd" date="${foodBankShiftInstance?.donationDate}"/></g:link></td>
						
						<td><g:formatNumber number="${foodBankShiftInstance?.numberOfVolunteers}" type="number" /></td>
											
						<td>${fieldValue(bean: foodBankShiftInstance, field: "comments")}</td>
						
						<td>${fieldValue(bean: foodBankShiftInstance, field: "leaderName")}</td>
					
						<td><g:formatNumber number="${foodBankShiftInstance?.mealFactor?.value}"  type="number" maxFractionDigits="2"  /></td>
						
						<td>${fieldValue(bean: foodBankShiftInstance, field: "numberOfMeals")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${foodBankShiftListTotal}" />
			</div>
		</div>
			
	</body>
	
</html>
</g:applyLayout>
