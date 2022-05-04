package se.kth.iv1350.pos.controller;
import se.kth.iv1350.pos.integration.Inventory;
import se.kth.iv1350.pos.integration.ItemDTO;
import se.kth.iv1350.pos.integration.Accounting;
import se.kth.iv1350.pos.integration.ExternalSystemCreator;
import se.kth.iv1350.pos.model.CashPayment;
import se.kth.iv1350.pos.model.Amount;
import se.kth.iv1350.pos.model.Sale;
import se.kth.iv1350.pos.model.TotalDTO;

/**
 * This is the only controller for the application. All calls to the model pass through here
 * @author magnu
 *
 */

public class Controller {
	private Amount amountPaid;
	private Amount change;
	private Amount totalCost;
	private Sale sale;
	private Accounting acc;
	private Inventory inv;
	private ItemDTO registeredItem;
	private TotalDTO total;
	private CashPayment payment;
	
	/**
	 * Creates a new instance
	 * @param systemCreator Creates a new instance of external systems
	 */
	public Controller(ExternalSystemCreator systemCreator) {
		acc = systemCreator.getAccounting();
		inv = systemCreator.getInventory();
	}
	
	/**
	 * This method starts a new sale. It must be called first in sale.
	 */
	public void startSale() {
		sale = new Sale();
	}
	
	/**
	 * This method registers a new item. It gets called for every registration of every item.
	 * @param itemId Is used to identify the specific item
	 * @param quantity Is the amount of the specific item
	 * @return Returns item price, description and running price
	 */
	public TotalDTO registerItem(int itemId, int quantity) {
		if (quantity == 0) {
			quantity = 1;
		}
		
		boolean itemFound = inv.findItem(itemId);
		
		if (itemFound) {
			registeredItem = inv.fetchItem(itemId);
			total = sale.currentRegisteredItemInfo(registeredItem, quantity);
		}
		
		return total;
	}
	
	/**
	 * When every item is registered the sale ends. The running total will be the same as total price
	 * for the entire sale.
	 * @return Total prie for the entire sale is returned
	 */
	public Amount endSale() {
		totalCost = sale.getRunningTotal();
		payment = new CashPayment(totalCost);
		
		return totalCost;
	}
	
	/**
	 * This method pays the paid amount to payment. Sets the value for paid amount, then the change
	 * can be calculated based on that.
	 * @param amountPaid The amount paid by the customer
	 * @return Returns the change
	 */
	public Amount pay(Amount amountPaid) {
		this.amountPaid = amountPaid;
		payment.setAmountPaid(amountPaid);
		change = payment.getCalculatedChange();
		
		return change;
	}
	
	/**
	 * This method prints the receipt and updates the external systems
	 */
	public void printReceipt() {
		sale.updateExternalSystems();
		sale.printReceipt(totalCost, amountPaid, change);
	}
}
