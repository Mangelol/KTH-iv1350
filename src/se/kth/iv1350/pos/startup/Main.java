package se.kth.iv1350.pos.startup;
import se.kth.iv1350.pos.integration.ExternalSystemCreator;
import se.kth.iv1350.pos.view.View;
import se.kth.iv1350.pos.controller.Controller;

/**
 * The Main class is used to start the application
 * @author magnu
 *
 */
public class Main {
	/**
	 * The Main method is used to start the application
	 * @param args 
	 */
	public static void main(String[] args) {
		ExternalSystemCreator systemCreator = new ExternalSystemCreator();
		Controller contrl = new Controller(systemCreator);
		
		View view = new View(contrl);
		view.runFakeExecution();
	}

}
