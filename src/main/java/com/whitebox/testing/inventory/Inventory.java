package com.whitebox.testing.inventory;

import java.util.Map;

/**
 * 
 * Inventory holds the items available for shopping
 *
 */
public class Inventory {
	
	private Map<String, Item> items;

	public Item getItem(String itemId) {
		return items.get(itemId);
	}

	public void setItems(Map<String, Item> items) {
		this.items = items;
	}

}
