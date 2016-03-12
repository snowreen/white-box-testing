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
	
	public static Inventory getMockInventory() {
		Inventory mockInventory = new Inventory();
		Map<String, Item> mockItems = new HashMap<String, Item>();
		mockItems.put("1", new Item("1", "Shampoo", 5F));
		mockItems.put("2", new Item("2", "Soap", 3F));
		mockItems.put("3", new Item("3", "Shower gel", 4F));
		mockInventory.setItems(mockItems);
		return mockInventory;
	}
	
	public static Customer getMockCustomer() {
		Customer mockCustomer = new Customer(111, "Syeda", "Nowreen", true, true);
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

}
