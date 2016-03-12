package com.whitebox.testing;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ShoppingCartTest {
	
	private ShoppingCart classToTest;
	
	@Before
	public void setUp() {
	}
	
	/**
	 * Test for cart contains 10 items, the customers gets a 10% discount before tax.
	 */
	@Test
	public void testCalcPurchasePriceWhenItemCountTen() {
		classToTest = new ShoppingCart(MockDatabase.getMockInventory());
		Assert.assertEquals(36.9, classToTest.calcPurchasePrice(MockDatabase.getCartWithTenItems(), MockDatabase.getMockCustomer()), 0);
	}

}
