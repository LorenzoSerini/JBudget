package it.unicam.cs.pa.jbudget097855;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unicam.cs.pa.jbudget097855.jbexception.TransactionException;
import it.unicam.cs.pa.jbudget097855.modelvc.AccountType;
import it.unicam.cs.pa.jbudget097855.modelvc.BasicLedger;
import it.unicam.cs.pa.jbudget097855.modelvc.BasicTransaction;
import it.unicam.cs.pa.jbudget097855.modelvc.MoneyMovement;
import it.unicam.cs.pa.jbudget097855.modelvc.MovementType;
import it.unicam.cs.pa.jbudget097855.persistence.LedgerPersistenceManager;

public class PersistenceTest {
	private BasicLedger ledger;
	private MoneyMovement expenditure;
	private MoneyMovement interests;
	private BasicTransaction transaction1;
	private LedgerPersistenceManager persistenceManager;
	
	private static final File DEFAULT_TXT_TEST_FILE = new File("src\\test\\resources\\JsonPersistence.txt"); 
	
	@BeforeEach
	void initAssets() {
		try {
			
		expenditure = new MoneyMovement(MovementType.CREDIT,  "", 500.0);
		interests = new MoneyMovement(MovementType.DEBIT, "", 2);
		
		transaction1=new BasicTransaction();
		
		ledger = new BasicLedger();
		ledger.addAccount(AccountType.ASSETS, "Lorenzo", "provaBankAccount", 10000);
		ledger.addAccount(AccountType.LIABILITIES, "Lorenzo", "provaCreditCard", 2000);
		
		ledger.addTag("prova", "prova");
		ledger.addTag("tec", "tecnologia");
		
		ledger.getAccounts().get(0).addMovement(expenditure);
		ledger.getAccounts().get(0).addMovement(interests);
		
		transaction1.addMovement(expenditure);
		transaction1.addMovement(interests);
		
		ledger.addTransaction(transaction1);
		
		persistenceManager = new LedgerPersistenceManager();
		
		} catch (TransactionException e) {
			e.printStackTrace();
		}
		}
	
	@Test
	void saveAndLoadTest() {
		try {
			persistenceManager.save(ledger, DEFAULT_TXT_TEST_FILE);
			this.ledger = persistenceManager.load();
			persistenceManager.save(new BasicLedger(), DEFAULT_TXT_TEST_FILE);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void TagControlTest() {
		assertEquals(2, this.ledger.getTags().size());
	}

	@Test
	void AccountControlTest() {
		assertEquals(10996, this.ledger.getAccounts().get(0).getBalance());
	}
	
	@Test
	void TransactionControlTest() {
		assertEquals(498, this.ledger.getTransactions().get(0).getTotalAmount());
	}

}
