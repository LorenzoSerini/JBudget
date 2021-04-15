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

import it.unicam.cs.pa.jbudget097855.modelvc.Account;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class JavaFXJBudget extends Application implements View{
	private Stage primaryStage;
	private BorderPane mainLayout;
	
	@Override
	public void start(Stage stage) throws Exception {
		this.primaryStage=stage;
		this.primaryStage.setTitle("JBUDGET");
		showMain();
	}
	
	
	private void showMain() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/MainStage.fxml"));
		mainLayout = loader.load();
		Scene scene = new Scene(mainLayout);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	
	
	public static void showMovementsStage(Account selectedAccount) throws IOException {
		FXMLLoader loader = new FXMLLoader(JavaFXJBudgetController.class.getResource("/view/MovementsItem.fxml"));
		BorderPane movementsItems = loader.load();
		
		JavaFXMovementsController controller = loader.getController();
		controller.init(selectedAccount);
		
		Stage stage = new Stage();
		Scene scene = new Scene(movementsItems);
		
		stage.setTitle("Movements");
		stage.initModality(Modality.WINDOW_MODAL);
		stage.setScene(scene);
		stage.showAndWait();
	}


	@Override
	public void open() {
		Application.launch(getClass());
	}
	
	
}
