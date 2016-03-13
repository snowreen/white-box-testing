package com.whitebox.testing;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ShoppingCartTest {
	
	private ShoppingCart classToTest;
	
	@Before
	public void setUp() {
	}
	
	/**
	 * Test for cart contains exact 10 items, the general customers gets a 10% discount before tax.
	 */
	@Test
	public void testCalcPurchasePriceWhenItemCountTenForGeneralCustomer() {
		List<String> cartWithTenItems = MockDatabase.getCartWithTenItems();
		double totalPriceBeforeTaxAfterDiscountExpected = MockDatabase.getDiscountedPriceForCartItems(cartWithTenItems, 10f);
		
		classToTest = new ShoppingCart(MockDatabase.getMockInventory());
		Assert.assertEquals(totalPriceBeforeTaxAfterDiscountExpected, classToTest.calcPurchasePrice(cartWithTenItems, MockDatabase.getMockGeneralCustomer()), 0);
	}
	
	/**
	 * Test for cart contains exact 10 items, the member customers gets a 10% discount for number of items
	 * and then additional 10% discount for being member of discount shopping club.
	 */
	@Test
	public void testCalcPurchasePriceWhenItemCountTenForMemberCustomer() {
		
		List<String> cartWithTenItems = MockDatabase.getCartWithTenItems();
		//Applying 10% discount for 10 items in the cart 
		double totalPriceBeforeTaxAfterDiscountExpected = MockDatabase.getDiscountedPriceForCartItems(cartWithTenItems, 10f);
		//Now Applying additional 10% discount for being a member
		double finalDiscountedPriceExpected = MockDatabase.getFinalDiscountedPriceFromTotalPrice(totalPriceBeforeTaxAfterDiscountExpected, 10f);
		
		classToTest = new ShoppingCart(MockDatabase.getMockInventory());
		Assert.assertEquals(finalDiscountedPriceExpected, classToTest.calcPurchasePrice(cartWithTenItems, MockDatabase.getMockMemberCustomer()), 0);
	}
	
	/**
	 * Test for cart contains more than 10 items, the customers gets a 10% discount before tax.
	 */
	@Test
	public void testCalcPurchasePriceWhenItemCountMoreThanTen() {
		List<String> cartWithMoreThanTenItems = MockDatabase.getCartWithMoreThanTenItems();
		double totalPriceBeforeTaxAfterDiscountExpected = MockDatabase.getDiscountedPriceForCartItems(cartWithMoreThanTenItems, 10f);
		
		classToTest = new ShoppingCart(MockDatabase.getMockInventory());
		Assert.assertEquals(totalPriceBeforeTaxAfterDiscountExpected, classToTest.calcPurchasePrice(cartWithMoreThanTenItems, MockDatabase.getMockGeneralCustomer()), 0);
	}

	/**
	 * Test for cart contains more than 5 but less than 10 items, the customers gets a 5% discount before tax.
	 */
	@Test
	public void testCalcPurchasePriceWhenItemCountMoreThanFiveButLessThanTen() {
		List<String> cartWithMoreThanFiveButLessThanTen = MockDatabase.getCartWithMoreThanFiveButLessThanTenItems();
		double totalPriceBeforeTaxAfterDiscountExpected = MockDatabase.getDiscountedPriceForCartItems(cartWithMoreThanFiveButLessThanTen, 5f);
		
		classToTest = new ShoppingCart(MockDatabase.getMockInventory());
		Assert.assertEquals(totalPriceBeforeTaxAfterDiscountExpected, classToTest.calcPurchasePrice(cartWithMoreThanFiveButLessThanTen, MockDatabase.getMockGeneralCustomer()), 0);
	}
}
