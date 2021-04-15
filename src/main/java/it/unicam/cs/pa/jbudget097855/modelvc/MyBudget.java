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

import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class MyBudget implements Budget{
	private HashMap<Tag, Double> tagMap = new HashMap<Tag, Double>();
	private List<Tag> tagList = new LinkedList<>();
	private String description;
	private static String NO_NAME= "no_name_budget";
	
	public MyBudget(String description) {
		this.description=description;
	}
	
	public MyBudget() {
		this.description=NO_NAME;
	}

	@Override
	public List<Tag> tags() {
		return this.tagList;
	}

	@Override
	public void set(Tag t, double expected) {
		tagMap.computeIfPresent(t, (key, value)->value+expected);
		tagMap.computeIfAbsent(t, (value)->expected);
		if(!tagList.contains(t))this.tagList.add(t);
	}
	
	@Override
	public Map<Tag, Double> get() {
		return this.tagMap;
	}

	@Override
	public double get(Tag t) {
		if(tagMap.containsKey(t)) return tagMap.get(t);
		else return 0;
	}
	
	@Override
	public void removeTag(Tag t) {
		this.tagMap.remove(t);
		this.tagList.remove(t);
	}
	
	@Override
	public String getDescription() {
		return this.description;
	}
	
	@Override
	public void setDescription(String description) {
		this.description=description;
	}
	
	@Override
	public String toString() {
		return this.description+" "+this.tagMap.entrySet();
	}

}
