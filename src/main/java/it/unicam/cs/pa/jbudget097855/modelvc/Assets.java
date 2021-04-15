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

package it.unicam.cs.pa.jbudget097855.modelvc;

import java.util.LinkedList;

import java.util.List;
import java.util.function.Predicate;


public class Assets extends WrapperAccount{
	private double balance;
	
	private List<Movement> movements = new LinkedList<>();
	
	public Assets(String name, String description, double openingBalance) {
		super(name, description, openingBalance);
		this.balance+=openingBalance;
	}

	@Override
	public double getBalance() {
		return this.balance;
	}
	
	private void setBalance(double money) {
		this.balance+=money;
	}
	

	@Override
	public List<Movement> getMovements() {
		return movements;
	}

	@Override
	public List<Movement> getMovements(Predicate<Movement> p) {
		LinkedList<Movement> subMovimentsList = new LinkedList<>();
		movements.stream()
		.filter(p)
		.forEach(subMovimentsList::add);
	 	return subMovimentsList;
	}
	
	
	@Override
	public void addMovement(Movement m) {
		m.setAccount(this);
		this.movements.add(m);
		setBalance(m.getAmount());
	}
	
	@Override
	public void removeMovement(Movement m) {
		m.setAccount(null);
		this.movements.remove(m);
		setBalance(-m.getAmount());
	}
	
	@Override
	public String toString() {
		return this.getName()+" "+this.getDescription()+" "+this.getBalance();
	}
	
}
