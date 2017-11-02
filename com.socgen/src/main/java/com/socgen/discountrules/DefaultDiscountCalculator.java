package com.socgen.discountrules;


import com.socgen.model.InventoryItem;

/**
 * This class uses brandDiscount and categoryMaximumDiscount rules to compute discount 
 * @author srinivasan
 *
 */
public class DefaultDiscountCalculator implements IDiscountCalculator{
	
	
	InventoryItem item;
	IRule brandDiscountRule;
	IRule categoryDiscountRule;
	
	public DefaultDiscountCalculator(InventoryItem item){
		this.item = item;
		this.brandDiscountRule = new BrandDiscountRule(item.getBrand());
		this.categoryDiscountRule = new CategoryMaximumDiscountRule(item.getCategory());
	}

	@Override
	public float calculateDiscountedPrice() {
		
		int brandDiscount = brandDiscountRule.computePercentageDiscount();
		int categoryDiscount = categoryDiscountRule.computePercentageDiscount();
		
		// Final discount is the maximum of brand discount and category discount
		int discountPercentage = Math.max(brandDiscount, categoryDiscount);
		
		float price = item.getPrice();
		
		float discount = (discountPercentage * price)/100;
		float finalPrice = price - discount;
		return finalPrice;
	}

}
