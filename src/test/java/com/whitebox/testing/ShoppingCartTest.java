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
		totalPriceAfterTax=Math.round(totalPriceAfterTax*100.0)/100.0;
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
		totalPriceAfterTax=Math.round(totalPriceAfterTax*100.0)/100.0;
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
		totalPriceAfterTax=Math.round(totalPriceAfterTax*100.0)/100.0;
		
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

    /**
     * Test for net total without tax and without discount applied for cart with 10 items.
     */
    @Test
    public void testCalcPurchasePriceForCartWithTenItems() {
        List<String> cartWithTenItems = MockDatabase.getCartWithTenItems();
        double totalPriceFor10Items = MockDatabase.getTotalPriceForCart(cartWithTenItems);

        classToTest = new ShoppingCart(MockDatabase.getMockInventory());
        Assert.assertEquals(totalPriceFor10Items, classToTest.getSubTotalAmt(cartWithTenItems, MockDatabase.getMockMemberCustomer()), 0);
    }

    /**
     * Test for net total without tax and without discount applied for cart between 5 and 10 items.
     */
    @Test
    public void testCalcPurchasePriceForCartWithMoreThanFiveButLessThanTenItems() {
        List<String> cartWithMoreThanFiveButLessThanTenItems = MockDatabase.getCartWithMoreThanFiveButLessThanTenItems();
        double totalPriceFor10Items = MockDatabase.getTotalPriceForCart(cartWithMoreThanFiveButLessThanTenItems);

        classToTest = new ShoppingCart(MockDatabase.getMockInventory());
        Assert.assertEquals(totalPriceFor10Items, classToTest.getSubTotalAmt(cartWithMoreThanFiveButLessThanTenItems, MockDatabase.getMockMemberCustomer()), 0);
    }


    /**
     * Test for tax amount for cart with 10 items for tax exempt customer.
     */
    @Test
    public void testCalcTaxAmtForCartWithTenItemsForTaxExemptCustomer() {
        List<String> cartWithTenItems = MockDatabase.getCartWithTenItems();

        classToTest = new ShoppingCart(MockDatabase.getMockInventory());
        Assert.assertEquals(0, classToTest.getTaxAmt(cartWithTenItems, MockDatabase.getMockGeneralCustomerTaxExempt()), 0);
    }

    /**
     * Test for tax amount for cart with more than 5 but less than 10 items for tax exempt customer.
     */
    @Test
    public void testCalcTaxAmtForCartWithMoreThanFiveButLessThanTenItemsForTaxExemptCustomer() {
        List<String> cartWithMoreThanFiveButLessThanTenItems = MockDatabase.getCartWithMoreThanFiveButLessThanTenItems();

        classToTest = new ShoppingCart(MockDatabase.getMockInventory());
        Assert.assertEquals(0, classToTest.getTaxAmt(cartWithMoreThanFiveButLessThanTenItems, MockDatabase.getMockGeneralCustomerTaxExempt()), 0);
    }

    /**
     * Test for tax amount for cart with 10 items for non tax exempt customer.
     */
    @Test
    public void testCalcTaxAmtForCartWithTenItemsForNonTaxExemptCustomer() {
        List<String> cartWithTenItems = MockDatabase.getCartWithTenItems();
        double totalPriceBeforeTaxAfterDiscountExpected = MockDatabase.getDiscountedPriceForCartItems(cartWithTenItems, 10f);
        double taxAmt=MockDatabase.getTaxAmount(totalPriceBeforeTaxAfterDiscountExpected, 4.5f);

        classToTest = new ShoppingCart(MockDatabase.getMockInventory());
        Assert.assertEquals(taxAmt, classToTest.getTaxAmt(cartWithTenItems, MockDatabase.getMockGeneralCustomer()), 0);
    }

    /**
     * Test for tax amount for cart with more than 5 but less than 10 items for non tax exempt customer.
     */
    @Test
    public void testCalcTaxAmtForCartWithMoreThanFiveButLessThanTenItemsForNonTaxExemptCustomer() {
        List<String> cartWithMoreThanFiveButLessThanTenItems = MockDatabase.getCartWithMoreThanFiveButLessThanTenItems();
        double totalPriceBeforeTaxAfterDiscountExpected = MockDatabase.getDiscountedPriceForCartItems(cartWithMoreThanFiveButLessThanTenItems, 5f);
        double taxAmt=MockDatabase.getTaxAmount(totalPriceBeforeTaxAfterDiscountExpected, 4.5f);

        classToTest = new ShoppingCart(MockDatabase.getMockInventory());
        Assert.assertEquals(taxAmt, classToTest.getTaxAmt(cartWithMoreThanFiveButLessThanTenItems, MockDatabase.getMockGeneralCustomer()), 0);
    }

    //------------------------

    /**
     * Test for tax amount for cart with 10 items for member tax exempt customer.
     */
    @Test
    public void testCalcTaxAmtForCartWithTenItemsForMemberTaxExemptCustomer() {
        List<String> cartWithTenItems = MockDatabase.getCartWithTenItems();

        classToTest = new ShoppingCart(MockDatabase.getMockInventory());
        Assert.assertEquals(0, classToTest.getTaxAmt(cartWithTenItems, MockDatabase.getMockMemberCustomerTaxExempt()), 0);
    }

    /**
     * Test for tax amount for cart with more than 5 but less than 10 items for member tax exempt customer.
     */
    @Test
    public void testCalcTaxAmtForCartWithMoreThanFiveButLessThanTenItemsForMemberTaxExemptCustomer() {
        List<String> cartWithMoreThanFiveButLessThanTenItems = MockDatabase.getCartWithMoreThanFiveButLessThanTenItems();

        classToTest = new ShoppingCart(MockDatabase.getMockInventory());
        Assert.assertEquals(0, classToTest.getTaxAmt(cartWithMoreThanFiveButLessThanTenItems, MockDatabase.getMockMemberCustomerTaxExempt()), 0);
    }

    /**
     * Test for tax amount for cart with 10 items for member non tax exempt customer.
     */
    @Test
    public void testCalcTaxAmtForCartWithTenItemsForMemberNonTaxExemptCustomer() {
        List<String> cartWithTenItems = MockDatabase.getCartWithTenItems();
        double totalPriceBeforeTaxAfterDiscountExpected = MockDatabase.getDiscountedPriceForCartItems(cartWithTenItems, 10f);
        double finalDiscountedPriceFromTotalPrice = MockDatabase.getFinalDiscountedPriceFromTotalPrice(totalPriceBeforeTaxAfterDiscountExpected, 10f);
        double taxAmt=MockDatabase.getTaxAmount(finalDiscountedPriceFromTotalPrice, 4.5f);

        classToTest = new ShoppingCart(MockDatabase.getMockInventory());
        Assert.assertEquals(taxAmt, classToTest.getTaxAmt(cartWithTenItems, MockDatabase.getMockMemberCustomer()), 0);
    }

    /**
     * Test for tax amount for cart with more than 5 but less than 10 items for member non tax exempt customer.
     */
    @Test
    public void testCalcTaxAmtForCartWithMoreThanFiveButLessThanTenItemsForMemberNonTaxExemptCustomer() {
        List<String> cartWithMoreThanFiveButLessThanTenItems = MockDatabase.getCartWithMoreThanFiveButLessThanTenItems();
        double totalPriceBeforeTaxAfterDiscountExpected = MockDatabase.getDiscountedPriceForCartItems(cartWithMoreThanFiveButLessThanTenItems, 5f);
        double finalDiscountedPriceFromTotalPrice = MockDatabase.getFinalDiscountedPriceFromTotalPrice(totalPriceBeforeTaxAfterDiscountExpected, 10f);
        double taxAmt=MockDatabase.getTaxAmount(finalDiscountedPriceFromTotalPrice,4.5f);

        classToTest = new ShoppingCart(MockDatabase.getMockInventory());
        Assert.assertEquals(taxAmt, classToTest.getTaxAmt(cartWithMoreThanFiveButLessThanTenItems, MockDatabase.getMockMemberCustomer()), 0);
    }

    //--------------------------------

    /**
     * Test for discount amount for cart with 10 items for general customer.
     */

    @Test
    public void testCalcDiscountAmtForCartWithTenItemsForGeneralCustomer() {
        List<String> cartWithTenItems = MockDatabase.getCartWithTenItems();
        double totalPriceFor10Items = MockDatabase.getTotalPriceForCart(cartWithTenItems);
        double totalPriceBeforeTaxAfterDiscountExpected = MockDatabase.getDiscountedPriceForCartItems(cartWithTenItems, 10f);
        double discountAmt=totalPriceFor10Items-totalPriceBeforeTaxAfterDiscountExpected;
        discountAmt=Math.round(discountAmt*100.0)/100.0;

        classToTest = new ShoppingCart(MockDatabase.getMockInventory());
        Assert.assertEquals(discountAmt, classToTest.getDiscountAmt(cartWithTenItems, MockDatabase.getMockGeneralCustomer()), 0);
    }

    /**
     * Test for discount amount for cart with more than 5 but less than 10 items for general customer.
     */

    @Test
    public void testCalcDiscountAmtForCartWithMoreThanFiveButLessThanTenItemsForGeneralCustomer() {
        List<String> cartWithMoreThanFiveButLessThanTenItems = MockDatabase.getCartWithMoreThanFiveButLessThanTenItems();
        double totalPriceForItems = MockDatabase.getTotalPriceForCart(cartWithMoreThanFiveButLessThanTenItems);
        double totalPriceBeforeTaxAfterDiscountExpected = MockDatabase.getDiscountedPriceForCartItems(cartWithMoreThanFiveButLessThanTenItems, 5f);
        double discountAmt=totalPriceForItems-totalPriceBeforeTaxAfterDiscountExpected;
        discountAmt=Math.round(discountAmt*100.0)/100.0;

        classToTest = new ShoppingCart(MockDatabase.getMockInventory());
        Assert.assertEquals(discountAmt, classToTest.getDiscountAmt(cartWithMoreThanFiveButLessThanTenItems, MockDatabase.getMockGeneralCustomer()), 0);
    }

    /**
     * Test for discount amount for cart with 10 items for member customer.
     */

    @Test
    public void testCalcDiscountAmtForCartWithTenItemsForMemberCustomer() {
        List<String> cartWithTenItems = MockDatabase.getCartWithTenItems();
        double totalPriceFor10Items = MockDatabase.getTotalPriceForCart(cartWithTenItems);
        double totalPriceBeforeTaxAfterDiscountExpected = MockDatabase.getDiscountedPriceForCartItems(cartWithTenItems, 10f);
        double finalDiscountedPriceFromTotalPrice = MockDatabase.getFinalDiscountedPriceFromTotalPrice(totalPriceBeforeTaxAfterDiscountExpected, 10f);
        double discountAmt = totalPriceFor10Items - finalDiscountedPriceFromTotalPrice;
        discountAmt=Math.round(discountAmt*100.0)/100.0;

        classToTest = new ShoppingCart(MockDatabase.getMockInventory());
        Assert.assertEquals(discountAmt, classToTest.getDiscountAmt(cartWithTenItems, MockDatabase.getMockMemberCustomer()), 0);
    }

    /**
     * Test for discount amount for cart with more than 5 but less than 10 items for member customer.
     */

    @Test
    public void testCalcDiscountAmtForCartWithMoreThanFiveButLessThanTenItemsForMemberCustomer() {
        List<String> cartWithMoreThanFiveButLessThanTenItems = MockDatabase.getCartWithMoreThanFiveButLessThanTenItems();
        double totalPriceForItems = MockDatabase.getTotalPriceForCart(cartWithMoreThanFiveButLessThanTenItems);
        double totalPriceBeforeTaxAfterDiscountExpected = MockDatabase.getDiscountedPriceForCartItems(cartWithMoreThanFiveButLessThanTenItems, 5f);
        double finalDiscountedPriceFromTotalPrice = MockDatabase.getFinalDiscountedPriceFromTotalPrice(totalPriceBeforeTaxAfterDiscountExpected, 10f);
        double discountAmt = totalPriceForItems - finalDiscountedPriceFromTotalPrice;
        discountAmt=Math.round(discountAmt*100.0)/100.0;

        classToTest = new ShoppingCart(MockDatabase.getMockInventory());
        Assert.assertEquals(discountAmt, classToTest.getDiscountAmt(cartWithMoreThanFiveButLessThanTenItems, MockDatabase.getMockMemberCustomer()), 0);
    }

    /**
     * Test for cart contains more than 0 but less than 5 items, the general tax exempt customers gets no discount and no tax applied.
     */
    @Test
    public void testCalcPurchasePriceWhenItemCountMoreThanZeroButLessThanFiveForGeneralCustomerTaxExempt() {
        List<String> cartWithMoreThanZeroButLessThanFive = MockDatabase.getCartWithMoreThanZeroButLessThanFiveItems();
        double totalPriceBeforeTax = MockDatabase.getTotalPriceForCart(cartWithMoreThanZeroButLessThanFive);

        classToTest = new ShoppingCart(MockDatabase.getMockInventory());
        Assert.assertEquals(totalPriceBeforeTax, classToTest.calcPurchasePrice(cartWithMoreThanZeroButLessThanFive, MockDatabase.getMockGeneralCustomerTaxExempt()), 0);
    }

    /**
     * Test for cart contains more than 0 but less than 5 items, the general non tax exempt customers gets 4.5% tax applied.
     */
    @Test
    public void testCalcPurchasePriceWhenItemCountMoreThanZeroButLessThanFiveForGeneralCustomerNonTaxExempt() {
        List<String> cartWithMoreThanZeroButLessThanFive = MockDatabase.getCartWithMoreThanZeroButLessThanFiveItems();
        double totalPriceBeforeTax= MockDatabase.getTotalPriceForCart(cartWithMoreThanZeroButLessThanFive);
        double totalPriceAfterTax = totalPriceBeforeTax + MockDatabase.getTaxAmount(totalPriceBeforeTax, 4.5f);

        classToTest = new ShoppingCart(MockDatabase.getMockInventory());
        Assert.assertEquals(totalPriceAfterTax, classToTest.calcPurchasePrice(cartWithMoreThanZeroButLessThanFive, MockDatabase.getMockGeneralCustomer()), 0);
    }

    /**
     * Test for cart contains more than 0 but less than 5 items, the member tax exempt customers gets
     * 10% discount for being member of discount shopping club and no tax applied.
     */
    @Test
    public void testCalcPurchasePriceWhenItemCountMoreThanZeroButLessThanFiveForMemberCustomerTaxExempt() {
        List<String> cartWithMoreThanZeroButLessThanFive = MockDatabase.getCartWithMoreThanZeroButLessThanFiveItems();

        //Applying 10% discount for being a member
        double DiscountedPriceBeforeTaxExpected = MockDatabase.getDiscountedPriceForCartItems(cartWithMoreThanZeroButLessThanFive, 10f);

        classToTest = new ShoppingCart(MockDatabase.getMockInventory());
        Assert.assertEquals(DiscountedPriceBeforeTaxExpected, classToTest.calcPurchasePrice(cartWithMoreThanZeroButLessThanFive, MockDatabase.getMockMemberCustomerTaxExempt()), 0);
    }

    /**
     * Test for cart contains more than 0 but less than 5 items, the member non tax exempt customers gets
     * then additional 10% discount for being member of discount shopping club and 4.5% tax applied.
     */
    @Test
    public void testCalcPurchasePriceWhenItemCountMoreThanZeroButLessThanFiveForMemberCustomerNonTaxExempt() {
        List<String> cartWithMoreThanZeroButLessThanFive = MockDatabase.getCartWithMoreThanZeroButLessThanFiveItems();

        //Now Applying additional 10% discount for being a member
        double finalDiscountedPriceBeforeTaxExpected = MockDatabase.getDiscountedPriceForCartItems(cartWithMoreThanZeroButLessThanFive, 10f);
        double totalPriceAfterTax = finalDiscountedPriceBeforeTaxExpected + MockDatabase.getTaxAmount(finalDiscountedPriceBeforeTaxExpected, 4.5f);
        totalPriceAfterTax=Math.round(totalPriceAfterTax*100.0)/100.0;

        classToTest = new ShoppingCart(MockDatabase.getMockInventory());
        Assert.assertEquals(totalPriceAfterTax, classToTest.calcPurchasePrice(cartWithMoreThanZeroButLessThanFive, MockDatabase.getMockMemberCustomer()), 0);
    }

}
