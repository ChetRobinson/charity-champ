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

class VolunteerShift extends Donation{
	
	static final String type = CharityChampConstants.MID_OHIO_FOOD_BANK_SHIFT
	
	Integer numberOfVolunteers
	String comments
	String leaderName
	GlobalNumericSetting mealFactor
		
	Date dateCreated
	Date lastUpdated

    static constraints = {
		numberOfVolunteers min : 1
		comments nullable : true
		leaderName nullable : true
			
    }
	
	public BigDecimal getNumberOfMeals(){
		
		return rounded(this.mealFactor?.value.multiply(numberOfVolunteers))
					
	}
		
	private BigDecimal rounded(BigDecimal aNumber){
		return aNumber.setScale(CharityChampConstants.DECIMALS, CharityChampConstants.ROUNDING_MODE);
	 }
}
