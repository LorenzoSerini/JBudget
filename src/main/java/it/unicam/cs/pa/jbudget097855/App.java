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


import java.io.IOException;
import java.util.Objects;
import java.util.logging.Logger;

import it.unicam.cs.pa.jbudget097855.mviewc.JavaFXJBudget;
import it.unicam.cs.pa.jbudget097855.mviewc.View;

/**
 * This is the JBudget Main Class.
 * 
 * @author LorenzoSerini
 *
 */

public class App {
	  private static Logger logger = Logger.getLogger("it.unicam.cs.pa.jbudget097855.JBudget");
	    private final View view;

	    public enum JBUDGET_TYPE {
	        BASIC;
	    }
	    
	    public App(View view) {
	        this.view = view;
	        logger.info("Application Created.");
	    }
	
	public static void main(String[] args) throws IOException {
		 if (args.length == 0) {
			  createJBudget("BASIC").start();
	        } else {
	            try {
	            	Objects.requireNonNull(createJBudget(args[0])).start();
	            } catch (IllegalArgumentException e) {
	                System.err.println(args[0]+" JBudget is unknown!");
	            }
	        }
	}
	
	  public void start() throws IOException {
	        logger.info("Application Started.");
	        view.open();
	        logger.info("Application Closed.");
	    }
	
	 public static App createJBudget(String code) {
		 
	        switch (JBUDGET_TYPE.valueOf(code.toUpperCase())) {
	            case BASIC:
	                return createBasicJBudget();
	        }
	        return null;
	    }
	
	 public static App createBasicJBudget() {
	      return new App(new JavaFXJBudget());
	    }
}