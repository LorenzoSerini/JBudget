/*******************************************************************************
 * MIT License
 * Copyright (c) 2020 Lorenzo Serini
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *******************************************************************************/
/**
 * 
 */

package it.unicam.cs.pa.jbudget097855;

import static org.junit.jupiter.api.Assertions.assertEquals;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unicam.cs.pa.jbudget097855.modelvc.Assets;
import it.unicam.cs.pa.jbudget097855.modelvc.Liabilities;
import it.unicam.cs.pa.jbudget097855.modelvc.MoneyMovement;
import it.unicam.cs.pa.jbudget097855.modelvc.MovementType;


public class AccountTest {
		private Assets bankAccount;
		private Liabilities creditCard;
		private MoneyMovement expenditure;
		private MoneyMovement interests;
		private MoneyMovement revenue;
		private MoneyMovement refundRevenue;
	
	@BeforeEach
	void initAssets() {
		bankAccount = new Assets("Lorenzo's Bank Account", "", 500.0);
		creditCard = new Liabilities("Lorenzo's Bank Account", "", 100.0);
		
		expenditure = new MoneyMovement(MovementType.CREDIT,  "", 500.0);
		interests = new MoneyMovement(MovementType.DEBIT, "", 2);
		
		bankAccount.addMovement(expenditure);
		bankAccount.addMovement(interests);
		
		
		revenue = new MoneyMovement(MovementType.DEBIT,"sold sports items", 1000);
		refundRevenue = new MoneyMovement(MovementType.CREDIT, "refund of shipping costs", 20);
		
		creditCard.addMovement(revenue);
		creditCard.addMovement(refundRevenue);
		
		}
	
	@Test
	void balanceAssetsTest() {
		assertEquals(998, bankAccount.getBalance());
	}
	
	@Test
	void balanceLaibilitiesTest() {
		assertEquals(1080, creditCard.getBalance());
	}
	
	@Test
	void movementsNumberTest() {
		creditCard.addMovement(expenditure);
		assertEquals(2, bankAccount.getMovements().size());
		assertEquals(3, creditCard.getMovements().size());
	}
	
	
}
