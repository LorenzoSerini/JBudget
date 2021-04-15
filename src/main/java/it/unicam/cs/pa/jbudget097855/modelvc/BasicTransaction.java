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

import java.time.LocalDate;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class BasicTransaction implements Transaction{
	private final UUID id = UUID.randomUUID();
	private List<Movement> movements = new LinkedList<>();
	private LocalDate date;
	private List<Tag> tags = new LinkedList<Tag>();
	private double amount;
	
	public BasicTransaction() {
		this.amount=0;
		setDate(LocalDate.now());
	}

	@Override
	public String getId() {
		return this.id.toString();
	}

	@Override
	public List<Movement> getMovements() {
		return this.movements;
	}

	@Override
	public LocalDate getDate() {
		return this.date;
	}

	@Override
	public List<Tag> getTags() {
		return this.tags;
	}

	@Override
	public void addTag(Tag tag) {
		this.tags.add(tag);
		for(Movement m:this.getMovements()) m.addTag(tag);
	}

	@Override
	public void removeTag(Tag tag) {
		this.tags.remove(tag);
		for(Movement m:this.getMovements()) m.removeTag(tag);
	}

	@Override
	public double getTotalAmount() {
		return this.amount;
	}

	@Override
	public void setDate(LocalDate ld) {
		this.date=ld;
		for(Movement m:this.movements) m.setDate(ld);  
	}

	@Override
	public void addMovement(Movement m) {
		m.setTransaction(this);
		for(Tag t:this.tags) {
			if(!m.getTags().contains(t)) m.addTag(t);
		}
		this.movements.add(m);
		setTotalAmount(m.getAmount());
	}

	@Override
	public void removeMovement(Movement m) {
		m.setTransaction(null);
		this.movements.remove(m);
		setTotalAmount(-m.getAmount());
	}
	
	private void setTotalAmount(double money) {
		this.amount+=money;
	}
	
	@Override
	public String toString() {
		return this.movements+" AMOUNT TRANSACTION:"+this.getTotalAmount();
	}
	
}
