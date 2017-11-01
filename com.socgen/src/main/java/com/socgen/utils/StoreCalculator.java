package com.socgen.utils;

import java.util.List;
import java.util.Optional;

import com.socgen.exception.ItemNotFoundException;
import com.socgen.model.Brand;
import com.socgen.model.Category;
import com.socgen.model.InventoryItem;

public class StoreCalculator {

	public static float calculateDiscountedPrice(InventoryItem item) {
		Brand brand = item.getBrand();
		Category category = item.getCategory();
		float price = item.getPrice();
		int discountPercentage = calculateDiscount(brand, category);
		float discount = (discountPercentage * price)/100;
		float finalPrice = price - discount;
		return finalPrice;
	}
	
	public static void calculateTotal(List<String> choiceList,List<InventoryItem> items) throws Exception{

		float total = 0;

		for (String choice : choiceList) {
			String[] tokens = choice.split(",");
			for (String token : tokens) {
				int id = Integer.parseInt(token);
				Optional<InventoryItem> inventoryitem = items.stream().filter(item -> item.getId() == id).findFirst();

				if (inventoryitem.isPresent()) {
					InventoryItem item = inventoryitem.get();
					float price = StoreCalculator.calculateDiscountedPrice(item);
					total = total + price;
                    
				} else {
					 throw new ItemNotFoundException("Item" + id + "not found in inventory");
				}
			}
			System.out.println("Result for choice " + choice + " is " + total);
			total = 0;
		}

	}

	private static int calculateDiscount(Brand brand, Category category) {

		int maxDiscount = Integer.MIN_VALUE;

		while (category != null) {
			int discount = category.getDiscount();
			if (discount > maxDiscount)
				maxDiscount = discount;
			category = category.getParent();
		}

		return Math.max(brand.getDiscount(), maxDiscount);

	}

}
