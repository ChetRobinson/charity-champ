/*
 * Copyright 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.charitychamp

import java.math.BigDecimal;
import java.util.Date;

class JeansPayment extends Donation{
	
	static final String type = CharityChampConstants.JEANS_PAYMENT
	
	String employeeUserId
	String payerFirstName
	String payerLastName
	String payerPhone
	String payerEmail
	BigDecimal amountCollected

	Date dateCreated
	Date lastUpdated
	
    static constraints = {
		employeeUserId blank : false
		payerFirstName blank : false
		payerLastName blank : false
		payerPhone nullable : true
		payerEmail nullable : true, email : true
		amountCollected min : 1 as BigDecimal
			
    }
	
	public BigDecimal getNumberOfMeals(){
		
			def numberOfMealsDollarBuys = CharityChampUtils.findNumberOfMealsADollarBuys(super.getDonationDate())
			return rounded(this.amountCollected.multiply(numberOfMealsDollarBuys))
					
	}
		
	private BigDecimal rounded(BigDecimal aNumber){
		return aNumber.setScale(CharityChampConstants.DECIMALS, CharityChampConstants.ROUNDING_MODE);
	 }
	
	public BigDecimal getProfit(){
		return amountCollected
	}
	
}
