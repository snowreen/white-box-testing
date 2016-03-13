package com.whitebox.testing;

import java.util.List;

import com.whitebox.testing.customer.Customer;
import com.whitebox.testing.inventory.Inventory;
import com.whitebox.testing.inventory.Item;

/**
 * Shopping Cart class will provide the total price for the items in cart.
 *
 */
public class ShoppingCart {
	
	private Inventory inventory;
	
	public ShoppingCart(Inventory inventory) {
		this.inventory = inventory;
	}
	
	/**
	 * Calculate Purchase Price
	 * @param productIDs
	 * @param customer
	 * @return
	 */
	public double calcPurchasePrice (List<String> productIDs, Customer customer) {
		/**
		 * If there is no product in cart, it will return 0.
		 */
		if (productIDs == null || productIDs.size() == 0) {
			return 0;
		}
		/**
		 * If there is more than 50 items in the product list, this will throw 
		 * IllegalArgumentException with a message saying Cart cannot have more than 50 items.
		 */
		if (productIDs.size() > 50) {
			throw new IllegalArgumentException("Cart cannot have more than 50 items");
		}
		double totalPriceBeforeTax = 0;
		/**
		 * Calculating total price by adding up each items price in the cart.
		 */
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
		/**
		 * If a customer is not tax exempt, then 4.5% tax rate applies to the total price.
		 */
		if (!customer.isTaxExempt()) {
			double totalPriceAfterTax = totalPriceBeforeTax + getTaxAmount(totalPriceBeforeTax, 4.5f);
			return totalPriceAfterTax;
		}
		/**
		 * Otherwise customer is tax exempt and for that reason no tax rate applies, return total price before tax.
		 */
		return totalPriceBeforeTax;
	}
	
	/**
	 * Get Discounted Price after applying discount percentage on total price
	 * @param totalPrice
	 * @param discountPercentage
	 * @return
	 */
	private double getDiscountedPrice(double totalPrice, float discountPercentage) {
		return totalPrice - (totalPrice * discountPercentage) / 100;
	}
	
	/**
	 * Get Tax amount by applying tax rate on total price
	 * @param totalPrice
	 * @param taxRate
	 * @return
	 */
	private double getTaxAmount(double totalPrice, float taxRate) {
		return (totalPrice * taxRate) / 100;
	}

}
