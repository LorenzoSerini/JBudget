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

package it.unicam.cs.pa.jbudget097855.persistence;

import java.io.BufferedReader;




import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import it.unicam.cs.pa.jbudget097855.modelvc.Account;
import it.unicam.cs.pa.jbudget097855.modelvc.BasicLedger;
import it.unicam.cs.pa.jbudget097855.modelvc.Movement;
import it.unicam.cs.pa.jbudget097855.modelvc.Scheduled;
import it.unicam.cs.pa.jbudget097855.modelvc.Tag;
import it.unicam.cs.pa.jbudget097855.modelvc.Transaction;

/**
 * This class is responsible for implementing application persistence.
 * Persistence is implemented with Gson library.
 * @author LorenzoSerini
 *
 */

public class LedgerPersistenceManager implements PersistenceManager<BasicLedger> {
	public static final File DEFAULT_JSON_FILE = new File("src\\main\\resources\\persistence\\JsonPersistence.json");
	private Gson gson;
	
	
	public LedgerPersistenceManager(Gson gson) {
		this.gson = gson;
	}
	
	
	public LedgerPersistenceManager() {
		this(basicLedgerAdapter());
	}
	
	@Override
	public void save(BasicLedger ledger, File file) throws IOException {
		String gsonLed = gson.toJson(ledger);
		wrapperSave(gsonLed, file);
	}
	
	/**
	 * Saves ledger in a default file
	 * @param ledger
	 * @throws IOException
	 */
	public void save(BasicLedger ledger) throws IOException {
		this.save(ledger, DEFAULT_JSON_FILE);
	}
	
	
	private void wrapperSave(String text, File file) throws IOException {
		BufferedWriter writer = new  BufferedWriter(new FileWriter(file));
		writer.write(text);
		writer.close();
	}

	@Override
	public BasicLedger load(File file) throws IOException {
		String gsonLed = wrapperLoad(file);
		return gson.fromJson(gsonLed, BasicLedger.class);
	}
	
	/**
	 * Loads ledger from default file
	 * @return The ledger
	 * @throws IOException
	 */
	public BasicLedger load() throws IOException {
		return this.load(DEFAULT_JSON_FILE);
	}
	
	private String wrapperLoad(File file) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String gsonLed = reader.readLine();
		reader.close();
		return gsonLed;
	}
	
	/**
	 * This factory method return a Gson basic ledger adapter
	 * @return Gson basic ledger adapter
	 */
	public static Gson basicLedgerAdapter() {
		GsonBuilder builder = new GsonBuilder();
		builder.registerTypeAdapter(LocalDate.class, new DateAdapter());
		builder.registerTypeAdapter(Tag.class, new Adapter<Tag>());
		builder.registerTypeAdapter(Account.class, new Adapter<Account>());
		builder.registerTypeAdapter(Transaction.class, new Adapter<Transaction>());
		builder.registerTypeAdapter(Scheduled.class, new Adapter<Scheduled>());
		builder.registerTypeAdapter(Movement.class, new MovementAdapter());
		return builder.create();
	}

}
