package com.socgen.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.socgen.discountrules.DefaultDiscountCalculator;
import com.socgen.exception.ItemNotFoundException;
import com.socgen.model.InventoryItem;

public class StoreManager {
	
	
	public  List<String> calculateTotal(List<String> choiceList,List<InventoryItem> items) throws Exception{

		float total = 0;
		
		List<String> result = new ArrayList<String>();

		for (String choice : choiceList) {
			String[] tokens = choice.split(",");
			for (String token : tokens) {
				int id = Integer.parseInt(token);
				Optional<InventoryItem> inventoryitem = items.stream().filter(item -> item.getId() == id).findFirst();

				if (inventoryitem.isPresent()) {
					InventoryItem item = inventoryitem.get();
					float price = new DefaultDiscountCalculator(item).calculateDiscountedPrice();
					total = total + price;
                    
				} else {
					 throw new ItemNotFoundException("Item" + id + "not found in inventory");
				}
			}
			result.add("Result for choice " + choice + " is " + total);
			total = 0;
		}
		
		return result;

	}

}
