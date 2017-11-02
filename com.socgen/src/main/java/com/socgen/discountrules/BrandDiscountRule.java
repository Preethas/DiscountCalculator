package com.socgen.discountrules;

import com.socgen.model.Brand;

public class BrandDiscountRule implements IRule {
	
	Brand brand;
	
	BrandDiscountRule(Brand brand){
		this.brand = brand;
	}

	@Override
	public int computePercentageDiscount() {
		return brand.getDiscount();
	}

}
