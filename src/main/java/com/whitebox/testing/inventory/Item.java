package com.whitebox.testing.inventory;

public class Item {
	
	private String itemId;
	private String itemName;
	private float price;
	
	public Item(String itemId, String itemName, float price) {
		super();
		this.itemId = itemId;
		this.itemName = itemName;
		this.price = price;
	}
	
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}

}
