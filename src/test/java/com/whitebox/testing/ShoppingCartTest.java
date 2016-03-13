package com.whitebox.testing;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * 
 * This is the test class for ShoppingCart, 
 * All the criteria and edge cases are covered.
 *
 */
public class ShoppingCartTest {
	
	private ShoppingCart classToTest;
	
	@Rule
	public ExpectedException expectedEx = ExpectedException.none();
	
	@Before
	public void setUp() {
	}
	
	/**
	 * Test for cart contains exact 10 items, the general tax exempt customers gets a 10% discount and no tax applied
	 */
	@Test
	public void testCalcPurchasePriceWhenItemCountExactTenForGeneralCustomerTaxExempt() {
		List<String> cartWithTenItems = MockDatabase.getCartWithTenItems();
		double totalPriceBeforeTaxAfterDiscountExpected = MockDatabase.getDiscountedPriceForCartItems(cartWithTenItems, 10f);
		
		classToTest = new ShoppingCart(MockDatabase.getMockInventory());
		Assert.assertEquals(totalPriceBeforeTaxAfterDiscountExpected, classToTest.calcPurchasePrice(cartWithTenItems, MockDatabase.getMockGeneralCustomerTaxExempt()), 0);
	}
	
	/**
	 * Test for cart contains exact 10 items, the general non tax exempt customers gets a 10% discount and 4.5% tax applied
	 */
	@Test
	public void testCalcPurchasePriceWhenItemCountExactTenForGeneralCustomerNonTaxExempt() {
		List<String> cartWithTenItems = MockDatabase.getCartWithTenItems();
		double totalPriceBeforeTaxAfterDiscountExpected = MockDatabase.getDiscountedPriceForCartItems(cartWithTenItems, 10f);
		double totalPriceAfterTax = totalPriceBeforeTaxAfterDiscountExpected + MockDatabase.getTaxAmount(totalPriceBeforeTaxAfterDiscountExpected, 4.5f);
		
		classToTest = new ShoppingCart(MockDatabase.getMockInventory());
		Assert.assertEquals(totalPriceAfterTax, classToTest.calcPurchasePrice(cartWithTenItems, MockDatabase.getMockGeneralCustomer()), 0);
	}
	
	/**
	 * Test for cart contains exact 10 items, the member tax exempt customers gets a 10% discount for number of items
	 * and then additional 10% discount for being member of discount shopping club and no tax applied.
	 */
	@Test
	public void testCalcPurchasePriceWhenItemCountExactTenForMemberCustomerTaxExempt() {
		
		List<String> cartWithTenItems = MockDatabase.getCartWithTenItems();
		//Applying 10% discount for 10 items in the cart 
		double totalPriceBeforeTaxAfterDiscountExpected = MockDatabase.getDiscountedPriceForCartItems(cartWithTenItems, 10f);
		//Now Applying additional 10% discount for being a member
		double finalDiscountedPriceExpected = MockDatabase.getFinalDiscountedPriceFromTotalPrice(totalPriceBeforeTaxAfterDiscountExpected, 10f);
		
		classToTest = new ShoppingCart(MockDatabase.getMockInventory());
		Assert.assertEquals(finalDiscountedPriceExpected, classToTest.calcPurchasePrice(cartWithTenItems, MockDatabase.getMockMemberCustomerTaxExempt()), 0);
	}
	
	/**
	 * Test for cart contains exact 10 items, the member non tax exempt customers gets a 10% discount for number of items
	 * and then additional 10% discount for being member of discount shopping club and 4.5% tax applied.
	 */
	@Test
	public void testCalcPurchasePriceWhenItemCountExactTenForMemberCustomerNonTaxExempt() {
		
		List<String> cartWithTenItems = MockDatabase.getCartWithTenItems();
		//Applying 10% discount for 10 items in the cart 
		double totalPriceBeforeTaxAfterDiscountExpected = MockDatabase.getDiscountedPriceForCartItems(cartWithTenItems, 10f);
		//Now Applying additional 10% discount for being a member
		double finalDiscountedPriceExpected = MockDatabase.getFinalDiscountedPriceFromTotalPrice(totalPriceBeforeTaxAfterDiscountExpected, 10f);
		double totalPriceAfterTax = finalDiscountedPriceExpected + MockDatabase.getTaxAmount(finalDiscountedPriceExpected, 4.5f);
		
		classToTest = new ShoppingCart(MockDatabase.getMockInventory());
		Assert.assertEquals(totalPriceAfterTax, classToTest.calcPurchasePrice(cartWithTenItems, MockDatabase.getMockMemberCustomer()), 0);
	}
	
	/**
	 * Test for cart contains more than 10 items, the general tax exempt customers gets a 10% discount and no tax applied.
	 */
	@Test
	public void testCalcPurchasePriceWhenItemCountMoreThanTenForGeneralCustomerTaxExempt() {
		List<String> cartWithMoreThanTenItems = MockDatabase.getCartWithMoreThanTenItems();
		double totalPriceBeforeTaxAfterDiscountExpected = MockDatabase.getDiscountedPriceForCartItems(cartWithMoreThanTenItems, 10f);
		
		classToTest = new ShoppingCart(MockDatabase.getMockInventory());
		Assert.assertEquals(totalPriceBeforeTaxAfterDiscountExpected, classToTest.calcPurchasePrice(cartWithMoreThanTenItems, MockDatabase.getMockGeneralCustomerTaxExempt()), 0);
	}
	
	/**
	 * Test for cart contains more than 10 items, the general non tax exempt customers gets a 10% discount and 4.5% tax applied.
	 */
	@Test
	public void testCalcPurchasePriceWhenItemCountMoreThanTenForGeneralCustomerNonTaxExempt() {
		List<String> cartWithMoreThanTenItems = MockDatabase.getCartWithMoreThanTenItems();
		double totalPriceBeforeTaxAfterDiscountExpected = MockDatabase.getDiscountedPriceForCartItems(cartWithMoreThanTenItems, 10f);
		double totalPriceAfterTax = totalPriceBeforeTaxAfterDiscountExpected + MockDatabase.getTaxAmount(totalPriceBeforeTaxAfterDiscountExpected, 4.5f);
		
		classToTest = new ShoppingCart(MockDatabase.getMockInventory());
		Assert.assertEquals(totalPriceAfterTax, classToTest.calcPurchasePrice(cartWithMoreThanTenItems, MockDatabase.getMockGeneralCustomer()), 0);
	}
	
	/**
	 * Test for cart contains more than 10 items, the member tax exempt customers gets a 10% discount for number of items 
	 * and then additional 10% discount for being member of discount shopping club and no tax applied.
	 */
	@Test
	public void testCalcPurchasePriceWhenItemCountMoreThanTenForMemberCustomerTaxExempt() {
		List<String> cartWithMoreThanTenItems = MockDatabase.getCartWithMoreThanTenItems();
		//Applying 10% discount for more than 10 items in the cart
		double totalPriceBeforeTaxAfterDiscountExpected = MockDatabase.getDiscountedPriceForCartItems(cartWithMoreThanTenItems, 10f);
		//Now Applying additional 10% discount for being a member
		double finalDiscountedPriceExpected = MockDatabase.getFinalDiscountedPriceFromTotalPrice(totalPriceBeforeTaxAfterDiscountExpected, 10f);
		
		classToTest = new ShoppingCart(MockDatabase.getMockInventory());
		Assert.assertEquals(finalDiscountedPriceExpected, classToTest.calcPurchasePrice(cartWithMoreThanTenItems, MockDatabase.getMockMemberCustomerTaxExempt()), 0);
	}
	
	/**
	 * Test for cart contains more than 10 items, the member non tax exempt customers gets a 10% discount for number of items 
	 * and then additional 10% discount for being member of discount shopping club and 4.5% tax applied.
	 */
	@Test
	public void testCalcPurchasePriceWhenItemCountMoreThanTenForMemberCustomerNonTaxExempt() {
		List<String> cartWithMoreThanTenItems = MockDatabase.getCartWithMoreThanTenItems();
		//Applying 10% discount for more than 10 items in the cart
		double totalPriceBeforeTaxAfterDiscountExpected = MockDatabase.getDiscountedPriceForCartItems(cartWithMoreThanTenItems, 10f);
		//Now Applying additional 10% discount for being a member
		double finalDiscountedPriceExpected = MockDatabase.getFinalDiscountedPriceFromTotalPrice(totalPriceBeforeTaxAfterDiscountExpected, 10f);
		double totalPriceAfterTax = finalDiscountedPriceExpected + MockDatabase.getTaxAmount(finalDiscountedPriceExpected, 4.5f);
		
		classToTest = new ShoppingCart(MockDatabase.getMockInventory());
		Assert.assertEquals(totalPriceAfterTax, classToTest.calcPurchasePrice(cartWithMoreThanTenItems, MockDatabase.getMockMemberCustomer()), 0);
	}
	
	/**
	 * Test for cart contains more than 5 but less than 10 items, the general tax exempt customers gets a 5% discount and no tax applied.
	 */
	@Test
	public void testCalcPurchasePriceWhenItemCountMoreThanFiveButLessThanTenForGeneralCustomerTaxExempt() {
		List<String> cartWithMoreThanFiveButLessThanTen = MockDatabase.getCartWithMoreThanFiveButLessThanTenItems();
		double totalPriceBeforeTaxAfterDiscountExpected = MockDatabase.getDiscountedPriceForCartItems(cartWithMoreThanFiveButLessThanTen, 5f);
		
		classToTest = new ShoppingCart(MockDatabase.getMockInventory());
		Assert.assertEquals(totalPriceBeforeTaxAfterDiscountExpected, classToTest.calcPurchasePrice(cartWithMoreThanFiveButLessThanTen, MockDatabase.getMockGeneralCustomerTaxExempt()), 0);
	}
	
	/**
	 * Test for cart contains more than 5 but less than 10 items, the general non tax exempt customers gets a 5% discount and 4.5% tax applied.
	 */
	@Test
	public void testCalcPurchasePriceWhenItemCountMoreThanFiveButLessThanTenForGeneralCustomerNonTaxExempt() {
		List<String> cartWithMoreThanFiveButLessThanTen = MockDatabase.getCartWithMoreThanFiveButLessThanTenItems();
		double totalPriceBeforeTaxAfterDiscountExpected = MockDatabase.getDiscountedPriceForCartItems(cartWithMoreThanFiveButLessThanTen, 5f);
		double totalPriceAfterTax = totalPriceBeforeTaxAfterDiscountExpected + MockDatabase.getTaxAmount(totalPriceBeforeTaxAfterDiscountExpected, 4.5f);
		
		classToTest = new ShoppingCart(MockDatabase.getMockInventory());
		Assert.assertEquals(totalPriceAfterTax, classToTest.calcPurchasePrice(cartWithMoreThanFiveButLessThanTen, MockDatabase.getMockGeneralCustomer()), 0);
	}
	
	/**
	 * Test for cart contains more than 5 but less than 10 items, the member tax exempt customers gets a 5% discount and 
	 * then additional 10% discount for being member of discount shopping club and no tax applied.
	 */
	@Test
	public void testCalcPurchasePriceWhenItemCountMoreThanFiveButLessThanTenForMemberCustomerTaxExempt() {
		List<String> cartWithMoreThanFiveButLessThanTen = MockDatabase.getCartWithMoreThanFiveButLessThanTenItems();
		//Applying 5% discount for more than 5 items but less than 10 items in the cart
		double totalPriceBeforeTaxAfterDiscountExpected = MockDatabase.getDiscountedPriceForCartItems(cartWithMoreThanFiveButLessThanTen, 5f);
		//Now Applying additional 10% discount for being a member
		double finalDiscountedPriceExpected = MockDatabase.getFinalDiscountedPriceFromTotalPrice(totalPriceBeforeTaxAfterDiscountExpected, 10f);
		
		classToTest = new ShoppingCart(MockDatabase.getMockInventory());
		Assert.assertEquals(finalDiscountedPriceExpected, classToTest.calcPurchasePrice(cartWithMoreThanFiveButLessThanTen, MockDatabase.getMockMemberCustomerTaxExempt()), 0);
	}
	
	/**
	 * Test for cart contains more than 5 but less than 10 items, the member non tax exempt customers gets a 5% discount and 
	 * then additional 10% discount for being member of discount shopping club and 4.5% tax applied.
	 */
	@Test
	public void testCalcPurchasePriceWhenItemCountMoreThanFiveButLessThanTenForMemberCustomerNonTaxExempt() {
		List<String> cartWithMoreThanFiveButLessThanTen = MockDatabase.getCartWithMoreThanFiveButLessThanTenItems();
		//Applying 5% discount for more than 5 items but less than 10 items in the cart
		double totalPriceBeforeTaxAfterDiscountExpected = MockDatabase.getDiscountedPriceForCartItems(cartWithMoreThanFiveButLessThanTen, 5f);
		//Now Applying additional 10% discount for being a member
		double finalDiscountedPriceExpected = MockDatabase.getFinalDiscountedPriceFromTotalPrice(totalPriceBeforeTaxAfterDiscountExpected, 10f);
		double totalPriceAfterTax = finalDiscountedPriceExpected + MockDatabase.getTaxAmount(finalDiscountedPriceExpected, 4.5f);
		
		classToTest = new ShoppingCart(MockDatabase.getMockInventory());
		Assert.assertEquals(totalPriceAfterTax, classToTest.calcPurchasePrice(cartWithMoreThanFiveButLessThanTen, MockDatabase.getMockMemberCustomer()), 0);
	}
	
	/**
	 * Test for cart contains more than 50 items, should throw IllegalArgumentException.
	 */
	@Test
	public void testCalcPurchasePriceWhenItemCountMoreThanFifty() {
		expectedEx.expect(IllegalArgumentException.class);
		expectedEx.expectMessage("Cart cannot have more than 50 items");
		List<String> cartWithMoreThanFifty = MockDatabase.getCartWithMoreThanFifty();
		
		classToTest = new ShoppingCart(MockDatabase.getMockInventory());
		classToTest.calcPurchasePrice(cartWithMoreThanFifty, MockDatabase.getMockMemberCustomer());
	}
	
	/**
	 * Test for cart contains no item, should return 0.
	 */
	@Test
	public void testCalcPurchasePriceWhenNoItem() {
		List<String> cartWithNoItem = new ArrayList<String>();
		
		classToTest = new ShoppingCart(MockDatabase.getMockInventory());
		Assert.assertEquals(0, classToTest.calcPurchasePrice(cartWithNoItem, MockDatabase.getMockMemberCustomer()), 0);
	}
	
	/**
	 * When list product id is null, should return 0.
	 */
	@Test
	public void testCalcPurchasePriceWhenProductIdListIsNull() {
		List<String> cartWithNoItem = null;
		
		classToTest = new ShoppingCart(MockDatabase.getMockInventory());
		Assert.assertEquals(0, classToTest.calcPurchasePrice(cartWithNoItem, MockDatabase.getMockMemberCustomer()), 0);
	}
}
