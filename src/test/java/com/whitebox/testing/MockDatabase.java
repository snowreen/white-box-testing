package com.whitebox.testing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.whitebox.testing.customer.Customer;
import com.whitebox.testing.inventory.Inventory;
import com.whitebox.testing.inventory.Item;

/**
 * This class is to create mock data that will be used to test different criteria.
 *
 */
public class MockDatabase {
	
	/**
	 * This is a mock inventory and we are populating with the mock items.
	 * @return
	 */
	private static final Inventory mockInventory;
	
	static {
		mockInventory = new Inventory();
		Map<String, Item> mockItems = new HashMap<String, Item>();
		mockItems.put("1", new Item("1", "Shampoo", 5F));
		mockItems.put("2", new Item("2", "Soap", 3F));
		mockItems.put("3", new Item("3", "Shower gel", 4F));
		mockInventory.setItems(mockItems);
	}
	
	public static Inventory getMockInventory() {
		return mockInventory;
	}
	
	/**
	 * Get a general customer who is not a member and not a tax exempt
	 * @return
	 */
	public static Customer getMockGeneralCustomer() {
		Customer mockCustomer = new Customer(111, "Syeda", "Nowreen", false, false);
		return mockCustomer;
	}
	
	/**
	 * Get a member customer who is not a tax exempt
	 * @return
	 */
	public static Customer getMockMemberCustomer() {
		Customer mockCustomer = new Customer(111, "Sudheshna", "Bhattacharya", true, false);
		return mockCustomer;
	}
	
	/**
	 * Returning 10 itemIds populated in cart.
	 * @return
	 */
	public static List<String> getCartWithTenItems() {
		List<String> mockCartItems = new ArrayList<String>();
		for (int i = 0; i < 4; i++) {
			mockCartItems.add("1");
		}
		for (int i = 0; i < 3; i++) {
			mockCartItems.add("2");
		}
		for (int i = 0; i < 3; i++) {
			mockCartItems.add("3");
		}
		return mockCartItems;
	}
	
	/**
	 * Returning 12 itemIds populated in cart.
	 * @return
	 */
	public static List<String> getCartWithMoreThanTenItems() {
		List<String> mockCartItems = new ArrayList<String>();
		for (int i = 0; i < 4; i++) {
			mockCartItems.add("1");
		}
		for (int i = 0; i < 4; i++) {
			mockCartItems.add("2");
		}
		for (int i = 0; i < 4; i++) {
			mockCartItems.add("3");
		}
		return mockCartItems;
	}
	
	/**
	 * Returning 7 itemIds populated in cart.
	 * @return
	 */
	public static List<String> getCartWithMoreThanFiveButLessThanTenItems() {
		List<String> mockCartItems = new ArrayList<String>();
		for (int i = 0; i < 3; i++) {
			mockCartItems.add("1");
		}
		for (int i = 0; i < 2; i++) {
			mockCartItems.add("2");
		}
		for (int i = 0; i < 2; i++) {
			mockCartItems.add("3");
		}
		return mockCartItems;
	}
	
	public static double getTotalPriceForCart(List<String> mockCartItems) {
		double totalPrice = 0;
		for (String mockItemId : mockCartItems) {
			totalPrice += mockInventory.getItem(mockItemId).getPrice();
		}
		return totalPrice;
	}
	
	public static double getDiscountedPriceForCartItems(List<String> mockCartItems, float discountPercentage) {
		double totalPriceBeforeDiscount = getTotalPriceForCart(mockCartItems);
		return totalPriceBeforeDiscount - (totalPriceBeforeDiscount * discountPercentage) / 100;
	}
	
	public static double getFinalDiscountedPriceFromTotalPrice(double totalPrice, float discountPercentage) {
		return totalPrice - (totalPrice * discountPercentage) / 100;
	}

}
