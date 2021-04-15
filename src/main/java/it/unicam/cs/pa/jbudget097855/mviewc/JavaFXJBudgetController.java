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

package it.unicam.cs.pa.jbudget097855.mviewc;
import java.io.IOException;



import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import it.unicam.cs.pa.jbudget097855.jbexception.DateException;
import it.unicam.cs.pa.jbudget097855.jbexception.TransactionException;
import it.unicam.cs.pa.jbudget097855.modelvc.Account;
import it.unicam.cs.pa.jbudget097855.modelvc.BasicLedger;
import it.unicam.cs.pa.jbudget097855.modelvc.BudgetReport;
import it.unicam.cs.pa.jbudget097855.modelvc.Movement;
import it.unicam.cs.pa.jbudget097855.modelvc.PersonalBudgetManager;
import it.unicam.cs.pa.jbudget097855.modelvc.Scheduled;
import it.unicam.cs.pa.jbudget097855.modelvc.Tag;
import it.unicam.cs.pa.jbudget097855.modelvc.Transaction;
import it.unicam.cs.pa.jbudget097855.mvcontroller.JBudgetController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;


public class JavaFXJBudgetController implements Initializable{
	private JBudgetController controller = new JBudgetController(new BasicLedger(), new PersonalBudgetManager());
	private Logger logger = Logger.getLogger("it.unicam.cs.pa.jbudget097855.mviewc.JavaFXJBudgetController");
	
	@FXML TextField tagName;
	@FXML TextField tagDescription;
	@FXML TableView<Tag> tagTable;
	@FXML TableColumn<Tag, String> columnNameTagTable;
	@FXML TableColumn<Tag, String> columnDescriptionTagTable;
	
	
	@FXML RadioButton assetsBtn;
	@FXML RadioButton liabilitiesBtn;
	@FXML TextField accountName;
	@FXML TextField accountDescription;
	@FXML TextField accountOpeningBalance;
	@FXML TableView<Account> accountsTable;
	@FXML TableColumn<Account, String> columnIdInAccountTable;
	@FXML TableColumn<Account, String> columnNameInAccountTable;
	@FXML TableColumn<Account, String> columnDescriptionInAccountTable;
	@FXML TableColumn<Account, Double> columnBalanceInAccountTable;

	
	@FXML RadioButton creditBtn;
	@FXML RadioButton debitBtn;
	@FXML TextField movementDescription;
	@FXML TextField movementAmount;
	@FXML TextField movementAccount;
	@FXML TableView<Movement> movementsTable;
	@FXML TableColumn<Movement, String> movementAccountColumn;
	@FXML TableColumn<Movement, String> movementTypeColumn;
	@FXML TableColumn<Movement, String> movementDescriptionColumn;
	@FXML TableColumn<Movement, Double> movementAmountColumn;
	@FXML TableColumn<Movement, Tag> movementTagsColumn;
	
	
	@FXML TableView<Transaction> transactionsTable;
	@FXML TableColumn<Transaction, String> transactionIdColumn;
	@FXML TableColumn<Transaction, String> transactionDateColumn;
	@FXML TableColumn<Transaction, Double> transactionAmountColumn;
	@FXML TableColumn<Transaction, Movement> transactionMovementsColumn;
	@FXML Label transactionErrorMessage;
	
	
	@FXML DatePicker schedulerDate;
	@FXML TextField schedulerDescription;
	@FXML TableView<Scheduled> schedulersTable;
	@FXML TableColumn<Scheduled, String> schedulerDateColumn;
	@FXML TableColumn<Scheduled, String> schedulerDescriptionColumn;
	@FXML TableColumn<Scheduled, Transaction> schedulerTransactionsColumn;
	@FXML Label schedulerErrorMessage;
	
	
	@FXML TextField budgetDescription;
	@FXML TextField expectedValue;
	@FXML TextField budgetDescriptionView;
	@FXML ListView<Tag> budgetMapView;
	@FXML ListView<Double> budgetValueView;
	
	@FXML ListView<BudgetReport> reportsList;
	
	@FXML Label saveMessage;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		columnNameTagTable.setCellValueFactory(new PropertyValueFactory<>("name"));
		columnDescriptionTagTable.setCellValueFactory(new PropertyValueFactory<>("description"));
		
		columnIdInAccountTable.setCellValueFactory(new PropertyValueFactory<>("id"));
		columnNameInAccountTable.setCellValueFactory(new PropertyValueFactory<>("name"));
		columnDescriptionInAccountTable.setCellValueFactory(new PropertyValueFactory<>("description"));
		columnBalanceInAccountTable.setCellValueFactory(new PropertyValueFactory<>("balance"));
		
		movementAccountColumn.setCellValueFactory(new PropertyValueFactory<>("account"));
		movementTypeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
		movementDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
		movementAmountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
		movementTagsColumn.setCellValueFactory(new PropertyValueFactory<>("tags"));
		
		transactionIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		transactionDateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
		transactionAmountColumn.setCellValueFactory(new PropertyValueFactory<>("totalAmount"));
		transactionMovementsColumn.setCellValueFactory(new PropertyValueFactory<>("movements"));
		
		schedulerDateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
		schedulerDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
		schedulerTransactionsColumn.setCellValueFactory(new PropertyValueFactory<>("transactions"));
		
		load();
	}
	
	
	private void load() {
		try {
			this.controller.load();
			refreshAll();
		} catch (IOException e) {
			saveMessage.setText("LOAD ERROR: the data was not loaded correctly!");
			logger.warning("LOAD ERROR!");
		}
		
	}
	
	@FXML
	public void savePressed(ActionEvent actionEvent){
		try {
			controller.save();
		} catch (IOException e) {
			saveMessage.setText("SAVE ERROR: When closing the program, the data will be lost!");
			logger.warning("SAVE ERROR!");
		}
	}
	
	@FXML
	public void deleteAllPressed(ActionEvent actionEvent) {
		this.controller = new JBudgetController(new BasicLedger(), new PersonalBudgetManager());
		refreshAll();
	}
	
	
	public void refreshAll() {
		tagTable.getItems().setAll(this.controller.getLedger().getTags());
		accountsTable.getItems().setAll(this.controller.getLedger().getAccounts());
		transactionsTable.getItems().setAll(this.controller.getLedger().getTransactions());
		schedulersTable.getItems().setAll(this.controller.getLedger().getSchedulers());	
	}
	
	
	@FXML
	public void addTagPressed(ActionEvent actionEvent){
		controller.processTag(tagName.getText(),tagDescription.getText());
		tagTable.getItems().add(this.controller.getLedger().getTags().get(this.controller.getLedger().getTags().size()-1));
		tagName.clear();
		tagDescription.clear();
	}
	
	
	@FXML
	public void removeTagPressed(ActionEvent actionEvent){
		Tag tag = tagTable.getSelectionModel().getSelectedItem();
		tagTable.getItems().remove(tag);
		this.controller.deleteTag(tag);
	}
	
	
	@FXML
	public void addAccountPressed(ActionEvent actionEvent){
		if(assetsBtn.isSelected()||liabilitiesBtn.isSelected()) {
			if(assetsBtn.isSelected()) {
				controller.processAccount("assets", accountName.getText(), accountDescription.getText(), Double.parseDouble(accountOpeningBalance.getText()));
			}
			else if(liabilitiesBtn.isSelected()) {
				controller.processAccount("liabilities", accountName.getText(), accountDescription.getText(), Double.parseDouble(accountOpeningBalance.getText()));
			}
		accountsTable.getItems().add(this.controller.getLedger().getAccounts().get(this.controller.getLedger().getAccounts().size()-1));
		assetsBtn.setSelected(false);
		liabilitiesBtn.setSelected(false);
		accountName.clear();
		accountDescription.clear();
		accountOpeningBalance.clear();
		}
	}
	
	@FXML
	public void setTagPressed(ActionEvent actionEvent){
		int tag = tagTable.getSelectionModel().getSelectedIndex();
		int movement = movementsTable.getSelectionModel().getSelectedIndex();
		this.controller.addTagInMovement(movement,tag);
		refreshMovements();
	}
	
	@FXML
	public void setAccountPressed(ActionEvent actionEvent){
		int account = accountsTable.getSelectionModel().getSelectedIndex();
		movementAccount.setText(String.valueOf(account));
	}
	
	@FXML
	public void newMovementPressed(ActionEvent actionEvent){
		if(creditBtn.isSelected()) controller.processMovement(Integer.parseInt(movementAccount.getText()), "credit", movementDescription.getText(), Double.parseDouble(movementAmount.getText()));
		else if(debitBtn.isSelected()) controller.processMovement(Integer.parseInt(movementAccount.getText()), "debit", movementDescription.getText(), Double.parseDouble(movementAmount.getText()));
		
		this.movementsTable.getItems().add(controller.getMovementsStack().get(controller.getMovementsStack().size()-1));
		creditBtn.setSelected(false);
		debitBtn.setSelected(false);
		movementDescription.clear();
		movementAmount.clear();
		movementAccount.clear();
		refreshAccounts();
	}
	
	public void refreshAccounts() {
		accountsTable.getItems().setAll(this.controller.getLedger().getAccounts());
	}
	
	public void refreshMovements() {
		movementsTable.getItems().setAll(this.controller.getMovementsStack());
	}
	
	@FXML
	public void deleteMovementPressed(ActionEvent actionEvent){
		int mIndex = movementsTable.getSelectionModel().getSelectedIndex();
		this.controller.deleteMovement(mIndex);
		refreshMovements();
	}
	
	@FXML
	public void clearMovementsPressed(ActionEvent actionEvent){
		movementsTable.getItems().clear();
		refreshMovements();
	}
	
	@FXML
	public void createTransactionPressed(ActionEvent actionEvent){
		try {
			this.controller.processTransactionWithMovements();
			this.transactionsTable.getItems().add(this.controller.getLedger().getTransactions().get(this.controller.getLedger().getTransactions().size()-1));
			this.movementsTable.getItems().clear();
			refreshAccounts();
			this.transactionErrorMessage.setText("");
		} catch (TransactionException e) {
			transactionErrorMessage.setText(e.getMessage());
		}
	}
	
	@FXML
	public void movementsStagePressed(ActionEvent actionEven) throws IOException{
		Account selectedAccount = accountsTable.getSelectionModel().getSelectedItem();
		JavaFXJBudget.showMovementsStage(selectedAccount);
	}
	
	@FXML
	public void newSchedulerPressed(ActionEvent actionEven){
		try {
		this.controller.addScheduledTransaction(schedulerDate.getValue(), schedulerDescription.getText());
		this.schedulersTable.getItems().add(this.controller.getLedger().getSchedulers().get(this.controller.getLedger().getSchedulers().size()-1));
		this.schedulerDescription.clear();
		this.schedulerErrorMessage.setText("");
		}catch (DateException e) {
			schedulerErrorMessage.setText(e.getMessage());
		}
	}
	
	@FXML
	public void addTransactionInSchedulerPressed(ActionEvent actionEven) {
		int schedulerIndex = schedulersTable.getSelectionModel().getSelectedIndex();
		int transactionIndex = transactionsTable.getSelectionModel().getSelectedIndex();
		this.controller.addTransactionInScheduler(schedulerIndex, transactionIndex);
		refreshScheduler();
	}
	
	@FXML
	public void schedulePressed(ActionEvent actionEven) {
		try {
			this.controller.scheduleTransactions();
			refreshScheduler();
			refreshAll();
		} catch (TransactionException e) {
			schedulerErrorMessage.setText(e.getMessage());
		}
	}
	
	public void refreshScheduler() {
		schedulersTable.getItems().setAll(this.controller.getLedger().getSchedulers());
	}
	
	@FXML
	public void setBudgetDescriptionPressed(ActionEvent actionEvent) {
		this.controller.setBudgetDescription(budgetDescription.getText());
		this.budgetDescriptionView.setText(budgetDescription.getText());
		this.budgetDescription.clear();
	}
	
	@FXML
	public void setExpectedValuePressed(ActionEvent actionEvent) {
		this.controller.setExpectedBudget(tagTable.getSelectionModel().getSelectedIndex(), Double.parseDouble(this.expectedValue.getText()));
		this.budgetMapView.getItems().add(tagTable.getSelectionModel().getSelectedItem());
		this.budgetValueView.getItems().add(Double.parseDouble(this.expectedValue.getText()));
		this.expectedValue.clear();
	}
	
	@FXML
	public void createReportPressed(ActionEvent actionEvent) {
		this.controller.generateReport();
		this.reportsList.getItems().add(this.controller.getBudgetManager().reports().get(this.controller.getBudgetManager().reports().size()-1));
		budgetDescriptionView.clear();
		budgetMapView.getItems().clear();
		budgetValueView.getItems().clear();
	}
	
	
	
	
}
	

