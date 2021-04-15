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

import java.net.URL;

import java.util.ResourceBundle;

import it.unicam.cs.pa.jbudget097855.modelvc.Account;
import it.unicam.cs.pa.jbudget097855.modelvc.Movement;
import it.unicam.cs.pa.jbudget097855.modelvc.Tag;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class JavaFXMovementsController implements Initializable{

	private Account selectedAccount;
	
	@FXML TextField movementsAccountId;
	@FXML TextField movementsAccountName;
	@FXML TextField movementsAccountDescription;
	@FXML TextField movementsAccountBalance;
	@FXML TableView<Movement> movementsTable;
	@FXML TableColumn<Movement, String> typeColumn;
	@FXML TableColumn<Movement, String> dateColumn;
	@FXML TableColumn<Movement, String> descriptionColumn;
	@FXML TableColumn<Movement, Double> amountColumn;
	@FXML TableColumn<Movement, Tag> tagsColumn;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
		dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
		descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
		amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
		tagsColumn.setCellValueFactory(new PropertyValueFactory<>("tags"));
	}
	
	
	public void init(Account selectedAccount) {
		this.selectedAccount=selectedAccount;
		this.movementsAccountId.setText(this.selectedAccount.getId());
		this.movementsAccountName.setText(this.selectedAccount.getName());
		this.movementsAccountDescription.setText(this.selectedAccount.getDescription());
		this.movementsAccountBalance.setText(String.valueOf(this.selectedAccount.getBalance()));
		this.movementsTable.getItems().addAll(this.selectedAccount.getMovements());
	}
	
	
}
