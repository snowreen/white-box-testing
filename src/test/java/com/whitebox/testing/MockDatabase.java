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
	 */
	private static final Inventory mockInventory;
	
	static {
		mockInventory = new Inventory();
		Map<String, Item> mockItems = new HashMap<String, Item>();
		mockItems.put("1", new Item("1", "Shampoo", 5.35f));
		mockItems.put("2", new Item("2", "Soap", 3.24f));
		mockItems.put("3", new Item("3", "Shower gel", 4.56f));
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
	 * Get a general customer who is not a member but tax exempt
	 * @return
	 */
	public static Customer getMockGeneralCustomerTaxExempt() {
		Customer mockCustomer = new Customer(112, "Test1 First", "Test1 Last", false, true);
		return mockCustomer;
	}
	
	/**
	 * Get a member customer who is not a tax exempt
	 * @return
	 */
	public static Customer getMockMemberCustomer() {
		Customer mockCustomer = new Customer(113, "Sudeshna", "Bhattacharyya", true, false);
		return mockCustomer;
	}
	
	/**
	 * Get a member customer who is tax exempt
	 * @return
	 */
	public static Customer getMockMemberCustomerTaxExempt() {
		Customer mockCustomer = new Customer(114, "Test2 First", "Test2 Last", true, true);
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

    /**
     * Returning 5 itemIds populated in cart.
     * @return
     */
    public static List<String> getCartWithMoreThanZeroButLessThanFiveItems() {
        List<String> mockCartItems = new ArrayList<String>();
        for (int i = 0; i < 2; i++) {
            mockCartItems.add("1");
        }
        for (int i = 0; i < 1; i++) {
            mockCartItems.add("2");
        }
        for (int i = 0; i < 1; i++) {
            mockCartItems.add("2");
        }
        return mockCartItems;
    }


    /**
	 * Returning 60 itemIds populated in cart.
	 * @return
	 */
	public static List<String> getCartWithMoreThanFifty() {
		List<String> mockCartItems = new ArrayList<String>();
		for (int i = 0; i < 20; i++) {
			mockCartItems.add("1");
		}
		for (int i = 0; i < 20; i++) {
			mockCartItems.add("2");
		}
		for (int i = 0; i < 20; i++) {
			mockCartItems.add("3");
		}
		return mockCartItems;
	}
	
	/**
	 * Get total price for mock items in the cart, useful calculating expected total price
	 * @param mockCartItems
	 * @return
	 */
	public static double getTotalPriceForCart(List<String> mockCartItems) {
		double totalPrice = 0;
		for (String mockItemId : mockCartItems) {
			totalPrice += mockInventory.getItem(mockItemId).getPrice();
		}
		totalPrice=roundOff(totalPrice);
		return totalPrice;
	}
	
	/**
	 * Calculate expected price after applying discount to total price of mock items
	 * @param mockCartItems
	 * @param discountPercentage
	 * @return
	 */
	public static double getDiscountedPriceForCartItems(List<String> mockCartItems, float discountPercentage) {
		double totalPriceBeforeDiscount = getTotalPriceForCart(mockCartItems);
		totalPriceBeforeDiscount =totalPriceBeforeDiscount- (totalPriceBeforeDiscount * discountPercentage) / 100;
		totalPriceBeforeDiscount=roundOff(totalPriceBeforeDiscount);
		return totalPriceBeforeDiscount;
	}
	
	/**
	 * Calculate expected final discounted price applying final discount on total price
	 * @param totalPrice
	 * @param discountPercentage
	 * @return
	 */
	public static double getFinalDiscountedPriceFromTotalPrice(double totalPrice, float discountPercentage) {
		totalPrice=totalPrice - (totalPrice * discountPercentage) / 100;
		totalPrice=roundOff(totalPrice);
		return totalPrice;
	}
	
	/**
	 * Get tax amount on expected price before tax
	 * @param totalPriceBeforeTax
	 * @param taxRate
	 * @return
	 */
	public static double getTaxAmount(double totalPriceBeforeTax, float taxRate) {
		double taxAmt=(totalPriceBeforeTax * taxRate) / 100;
		double tax=roundOff(taxAmt);
		return tax;
	}

	/** Perform Rounding Off */
	private static double roundOff(double value)
	{value=Math.round(value*100.0)/100.0;
		return value;}

}
