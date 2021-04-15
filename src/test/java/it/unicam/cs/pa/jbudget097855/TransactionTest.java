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

import it.unicam.cs.pa.jbudget097855.modelvc.BasicTag;
import it.unicam.cs.pa.jbudget097855.modelvc.BasicTransaction;
import it.unicam.cs.pa.jbudget097855.modelvc.MoneyMovement;
import it.unicam.cs.pa.jbudget097855.modelvc.MovementType;



public class TransactionTest {
	private BasicTransaction transaction;
	MoneyMovement expenditure;
	MoneyMovement interests;
	
	@BeforeEach
	void initTransactionTest() {
		expenditure = new MoneyMovement(MovementType.DEBIT, "", 500);
		interests = new MoneyMovement(MovementType.DEBIT, "", 2);
		
		expenditure.addTag(BasicTag.SPORT);
		interests.addTag(BasicTag.INTERESTS);
		
		transaction = new BasicTransaction();
		
		transaction.addMovement(expenditure);
		transaction.addMovement(interests);
		
		transaction.addTag(BasicTag.BEAUTY);
		transaction.addTag(BasicTag.TECHNOLOGY);
		}
	
	@Test
	void amountTest() {
		assertEquals(-502, transaction.getTotalAmount());
	}
	
	@Test
	void tagsNumberTest() {
		assertEquals(2, transaction.getTags().size());
	}
	
	@Test
	void movementsNumberTest() {
		assertEquals(3, expenditure.getTags().size());
	}
}
