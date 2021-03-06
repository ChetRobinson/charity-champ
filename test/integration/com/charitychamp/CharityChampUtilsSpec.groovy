package com.charitychamp

import static org.junit.Assert.*
import grails.plugin.spock.UnitSpec
import grails.test.mixin.*
import grails.test.mixin.support.*

import org.joda.time.DateTime
import org.joda.time.LocalDate
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */

class CharityChampUtilsSpec extends UnitSpec {

	def "Calling currentCampaign should return the campaingn we are currently in"() {
		
		//Campaigns are set up in Bootstrap.groovy
		setup:
				
		def currentDate = new LocalDate(2012, 10, 25)
		
		
		when:
		
		def foundCampaign = CharityChampUtils.currentCampaign(currentDate)
		
		then:
	
		foundCampaign.name == 'Second Campaign'

	}
	
	def "Find First Campaign when date is the first day of campaign"() {
		
		//Campaigns are set up in Bootstrap.groovy
		setup:
				
		def currentDate = new LocalDate(2011, 1, 1)
		
		
		when:
		
		def foundCampaign = CharityChampUtils.currentCampaign(currentDate)
		
		then:
	
		foundCampaign.name == 'First Campaign'

	}
	
	def "Find First Campaign when date is the last day of campaign"() {
		
		//Campaigns are set up in Bootstrap.groovy
		setup:
				
		def currentDate = new LocalDate(2011, 12, 31)
		
		
		when:
		
		def foundCampaign = CharityChampUtils.currentCampaign(currentDate)
		
		then:
	
		foundCampaign.name == 'First Campaign'

	}
	
	def "Find Second Campaign when date is the first day of campaign"() {
		
		//Campaigns are set up in Bootstrap.groovy
		setup:
				
		def currentDate = new LocalDate(2012, 1, 1)
		
		
		when:
		
		def foundCampaign = CharityChampUtils.currentCampaign(currentDate)
		
		then:
	
		foundCampaign.name == 'Second Campaign'

	}
	
		
	def "Calling currentCampaign should return null if more than one campaign is found"() {
		
		//Campaigns are set up in Bootstrap.groovy
		setup:
		
		DateTime startDateThree = new DateTime(2013, 1 , 2, 0, 0)
		DateTime endDateThree = new DateTime(2013, 12 , 30, 0, 0)
		
		def campaign = new Campaign(name : "Fourth Campaign", startDate : startDateThree.toDate(), endDate : endDateThree.toDate()).save(flush:true)
		def currentDate = new LocalDate(2013, 10, 25)
		
		
		when:
		
		def foundCampaign = CharityChampUtils.currentCampaign(currentDate)
		
		then:
	
		foundCampaign == null

	}
	
	def "Calling currentCampaign should return null if no campaign is found"() {
		
		//Campaigns are set up in Bootstrap.groovy
		setup:
	
		def currentDate = new LocalDate(2014, 10, 25)
		
		
		when:
		
		def foundCampaign = CharityChampUtils.currentCampaign(currentDate)
		
		then:
	
		foundCampaign == null

	}
	
	def "donation date occurs within the campaign"(){
		
		setup:
		def campaign = Campaign.findByName("Second Campaign")
		def date = new LocalDate(2012, 4, 10)
		
		when:
		
		def isDateGood = CharityChampUtils.donationOccursWithinValidCampaign(campaign, date.toDate())
		
		then:
		
		isDateGood == true
		
		
		
		
	}
	
	def "donation date occurs on first day of the campaign"(){
		
		setup:
		def campaign = Campaign.findByName("Second Campaign")
		def date = new LocalDate(2012, 1, 1)
		
		when:
		
		def isDateGood = CharityChampUtils.donationOccursWithinValidCampaign(campaign, date.toDate())
		
		then:
		
		isDateGood == true
		
		
		
		
	}
	
	def "donation date occurs on last day of the campaign"(){
		
		setup:
		def campaign = Campaign.findByName("Second Campaign")
		def date = new LocalDate(2012, 12, 31)
		
		when:
		
		def isDateGood = CharityChampUtils.donationOccursWithinValidCampaign(campaign, date.toDate())
		
		then:
		
		isDateGood == true
		
		
		
		
	}
	
	def "donation date occurs on one day before the start of the campaign"(){
		
		setup:
		def campaign = Campaign.findByName("Second Campaign")
		def date = new LocalDate(2011, 12, 31)
		
		when:
		
		def isDateGood = CharityChampUtils.donationOccursWithinValidCampaign(campaign, date.toDate())
		
		then:
		
		isDateGood == false
		
		
		
		
	}
	
	def "donation date occurs on one day after the end of the campaign"(){
		
		setup:
		def campaign = Campaign.findByName("Second Campaign")
		def date = new LocalDate(2013, 1, 1)
		
		when:
		
		def isDateGood = CharityChampUtils.donationOccursWithinValidCampaign(campaign, date.toDate())
		
		then:
		
		isDateGood == false
		
		
		
		
	}
	
	def "find the most recent number of meals a dollar buys"(){
		
		when:
		def date1 = new LocalDate(2011,1,1)
		def date2 = new LocalDate(2012,1,1)
		def date3 = new LocalDate(2013,1,1)
		def donationDate = new LocalDate(2012,10,5)
		def mealsADollarBuys1 = new GlobalNumericSetting(name : "Meals a Dollar Buys", mofbShift : false, value : new BigDecimal('33.3'), effectiveDate: date1.toDate()).save()
		def mealsADollarBuys2 = new GlobalNumericSetting(name : "Meals a Dollar Buys", mofbShift : false, value : new BigDecimal('40.0'), effectiveDate: date2.toDate()).save()
		def mealsADollarBuys3 = new GlobalNumericSetting(name : "Meals a Dollar Buys", mofbShift : false, value : new BigDecimal('50.0'), effectiveDate: date3.toDate()).save()
		def numOfMealsDollarBuys = CharityChampUtils.findNumberOfMealsADollarBuys(donationDate.toDate())
		
		then:
		numOfMealsDollarBuys.compareTo(new BigDecimal('40.0')) == 0
		
	}
	
	def "find Number Of Meals A Dollar Buys should return 0 if there are none"(){
		
		when:
		
		def donationDate = new LocalDate(2009,10,5)
	
		def numOfMealsDollarBuys = CharityChampUtils.findNumberOfMealsADollarBuys(donationDate.toDate())
		
		then:
		numOfMealsDollarBuys.compareTo(new BigDecimal('0')) == 0
		
	}
	
	def "find the most recent goal per employee"(){
		
		when:
		def date1 = new LocalDate(2012,7,1)
		def date2 = new LocalDate(2012,7,15)
		def date3 = new LocalDate(2012,8,1)
		def donationDate = new LocalDate(2012,7,20)
		def goalPerEmployee1 = new GlobalNumericSetting(name : CharityChampConstants.GOAL_PER_EMPLOYEE_NAME_VALUE, mofbShift : false, value : new BigDecimal('33.3'), effectiveDate: date1.toDate()).save(failOnError:true)
		def goalPerEmployee2 = new GlobalNumericSetting(name : CharityChampConstants.GOAL_PER_EMPLOYEE_NAME_VALUE, mofbShift : false, value : new BigDecimal('56.7'), effectiveDate: date2.toDate()).save()
		def goalPerEmployee3 = new GlobalNumericSetting(name : CharityChampConstants.GOAL_PER_EMPLOYEE_NAME_VALUE, mofbShift : false, value : new BigDecimal('67.8'), effectiveDate: date3.toDate()).save()
				
		def goalPerEmployee = CharityChampUtils.findCurrentGoalForEmployee(donationDate.toDate())
		
		then:
		goalPerEmployee.equals(new BigDecimal('56.70'))
		
		
	}
}
