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
		 * Calculating price after discount according to number of items in the cart.
		 */
		if (productIDs.size() > 5 && productIDs.size() < 10) {
			/**
			 * cart contains more than 5 items but less than 10 items, the customers gets a 5% discount before tax.
			 */
			totalPriceBeforeTax = getDiscountedPrice(totalPriceBeforeTax, 5f);
		}
		else if (productIDs.size() >= 10) {
			/**
			 * cart contains 10 or more items, the customers gets a 10% discount before tax.
			 */
			totalPriceBeforeTax = getDiscountedPrice(totalPriceBeforeTax, 10f);
		} 
		
		/**
		 * If a customer is a store’s discount shopping club member, then he/she gets an additional 10% discount.
		 */
		if (customer.isMember()) {
			totalPriceBeforeTax = getDiscountedPrice(totalPriceBeforeTax, 10f);
		}
		return totalPriceBeforeTax;
	}
	
	private double getDiscountedPrice(double totalPrice, float discountPercentage) {
		return totalPrice - (totalPrice * discountPercentage) / 100;
	}

}
