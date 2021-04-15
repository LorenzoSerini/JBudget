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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyBudgetReport implements BudgetReport{
	private Budget budget;
	private HashMap<Tag, Double> ledgerMap = new HashMap<Tag, Double>();
	private HashMap<Tag, Double> reportMap = new HashMap<Tag, Double>();
	private Ledger ledger;
	
	public MyBudgetReport(Ledger ledger, Budget budget) {
		this.budget=budget;
		this.ledger=ledger;
		generateLedgerReport(ledger, this.ledgerMap);
		generateComparisonReport(budget, this.ledgerMap, this.reportMap);
	}

	@Override
	public List<Tag> tags() {
		return ledger.getTags();
	}

	private void generateLedgerReport(Ledger ledger, HashMap<Tag, Double> map) {
		for(Account a:ledger.getAccounts()) {
			for(Movement m:a.getMovements()) {
				for(Tag t:m.getTags()) {
					map.computeIfPresent(t, (key,value)->value+(m.getAmount()/m.getTags().size()));
					map.computeIfAbsent(t, (value)->(m.getAmount()/m.getTags().size()));
				}
			}
		}
	}
	
	private void generateComparisonReport(Budget budget, HashMap<Tag, Double> ledgerMap, HashMap<Tag, Double> reportMap ) {
		for(Tag t:budget.tags()) {
			if(ledgerMap.get(t)!=null) reportMap.put(t, (ledgerMap.get(t)-budget.get(t)));
			else reportMap.put(t, (0-budget.get(t)));
		}
	}
	
	@Override
	public Map<Tag, Double> ledgerReport() {
		return this.ledgerMap;
	}
	
	@Override
	public Map<Tag, Double> expectedReport() {
		return this.budget.get();
	}
	
	@Override
	public Map<Tag, Double> report(){
		return this.reportMap;
	}

	@Override
	public Budget getBudget() {
		return this.budget;
	}

	@Override
	public double getLedgerReport(Tag t) {
		if(ledgerMap.containsKey(t)) return ledgerMap.get(t);
		else return 0;
	}
	
	@Override
	public double getReport(Tag t) {
		if(reportMap.containsKey(t)) return reportMap.get(t);
		else return 0;
	}
	
	@Override
	public String toString() {
		return this.report().toString();
	}

}
