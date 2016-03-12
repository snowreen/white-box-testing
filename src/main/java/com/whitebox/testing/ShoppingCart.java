package com.whitebox.testing;

import java.util.List;

import com.whitebox.testing.customer.Customer;
import com.whitebox.testing.inventory.Inventory;
import com.whitebox.testing.inventory.Item;

public class ShoppingCart {
	
	private Inventory inventory;
	
	public ShoppingCart(Inventory inventory) {
		this.inventory = inventory;
	}

	public double calcPurchasePrice (List<String> productIDs, Customer customer) {
		double totalPriceBeforeTax = 0;
		for (String itemId : productIDs) {
			Item item = inventory.getItem(itemId);
			totalPriceBeforeTax += item.getPrice();
		}
		/**
		 * cart contains 10 or more items, the customers gets a 10% discount before tax.
		 */
		if (productIDs.size() >= 10) {
			totalPriceBeforeTax = totalPriceBeforeTax - (totalPriceBeforeTax * 10)/100;
		}
		return totalPriceBeforeTax;
	}

}
