package se.kth.iv1350.pos.view;
import se.kth.iv1350.pos.model.Amount;
import se.kth.iv1350.pos.model.TotalDTO;
import se.kth.iv1350.pos.controller.Controller;
import java.util.Scanner;

/**
 * This is a replacement for the real view. It has hardcoded calls to all system operations
 * in controller
 * @author magnu
 *
 */
public class View {
	private final Controller contrl;
	private final Scanner in = new Scanner(System.in);
	
	/**
	 * This method creates a new instance. It uses specified controller for all calls to other layers
	 * @param contrl This controller is used for all calls to the other layers
	 */
	public View (Controller contrl) {
		this.contrl = contrl;
	}
	
	/**
	 * Calls all the system operations in the controller to perform a fake sale
	 */
	public void runFakeExecution() {
		contrl.startSale();
		System.out.println("New sale has been started");
		System.out.println();
		
		TotalDTO total = contrl.registerItem(2, 2);
		Amount price = total.getPrice();
		Amount runningTotal = total.getRunningTotal();
		String itemDesc = total.getItemDesc();
		System.out.println("Item: " + itemDesc + "\nPrice: " + price + "\nRunning Total: " + runningTotal);
		System.out.println();
		
		total = contrl.registerItem(1, 2);
		itemDesc = total.getItemDesc();
		price = total.getPrice();
		runningTotal = total.getRunningTotal();
		System.out.println("Item: " + itemDesc + "\nPrice: " + price + "\nRunning total: " + runningTotal);
		System.out.println();
		
		total = contrl.registerItem(2, 1);
		itemDesc = total.getItemDesc();
		price = total.getPrice();
		runningTotal = total.getRunningTotal();
		System.out.println("Item: " + itemDesc + "\nPrice: " + price + "\nRunning total: " + runningTotal);
		System.out.println();
		
		Amount totalPrice = contrl.endSale();
		System.out.println("Total price: " + totalPrice);
		System.out.println();
		
		System.out.println("Enter the amount paid: ");
		double amountPaidInput = in.nextDouble();
		Amount amountPaid = new Amount(amountPaidInput);
		System.out.println();
		
		Amount change = contrl.pay(amountPaid);
		System.out.println("Change: " + change);
		System.out.println();
		
		contrl.printReceipt(); //Prints the receipt
	}
}
