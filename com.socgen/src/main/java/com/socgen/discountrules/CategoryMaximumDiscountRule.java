package com.socgen.discountrules;

import com.socgen.model.Category;

public class CategoryMaximumDiscountRule implements IRule{
	
	Category category;
	
	CategoryMaximumDiscountRule(Category category){
		this.category = category;
	}

	@Override
	public int computePercentageDiscount() {
		int maxDiscount = Integer.MIN_VALUE;

		while (category != null) {
			int discount = category.getDiscount();
			if (discount > maxDiscount)
				maxDiscount = discount;
			category = category.getParent();
		}
		return maxDiscount;
	}

}
