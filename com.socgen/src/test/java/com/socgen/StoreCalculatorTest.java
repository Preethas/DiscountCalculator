package com.socgen;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Test;

import com.socgen.dataloader.DataStore;
import com.socgen.dataloader.DefaultDataLoader;
import com.socgen.discountrules.DefaultDiscountCalculator;
import com.socgen.model.InventoryItem;
import com.socgen.utils.StoreManager;

public class StoreCalculatorTest {

	@Test
	public void testForCalculatingDiscountedPrice(){
		
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("inventory.txt").getFile());
		
		DataStore store = new DataStore(new DefaultDataLoader(),file.getPath());
		try {
			store.init();
			List<InventoryItem> inventoryList = store.getInventory();
			
			int id = 1;
			Optional<InventoryItem> inventoryitem = inventoryList.stream().filter(item -> item.getId() == id).findFirst();

			float discount = new DefaultDiscountCalculator(inventoryitem.get()).calculateDiscountedPrice();
			
			assertEquals(640.0f,discount);
		
			inventoryitem = inventoryList.stream().filter(item -> item.getId() == 2).findFirst();

			discount = new DefaultDiscountCalculator(inventoryitem.get()).calculateDiscountedPrice();
			
			assertEquals(560.0f,discount);
			
			
		}catch(Exception e){
			assertEquals(true,false);
		}
	}
	
	@Test
	public void testForCalculatingTotalPrice(){
		
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("inventory.txt").getFile());
		
		DataStore store = new DataStore(new DefaultDataLoader(),file.getPath());
		StoreManager storeMgr = new StoreManager();
		try {
			store.init();
			List<InventoryItem> items = store.getInventory();
			
			List<String> choiceList = Arrays.asList("1,5");
			
			List<String> results = storeMgr.calculateTotal(choiceList, items);
			assertEquals("Result for choice 1,5 is 2140.0",results.get(0));
			
			
		}catch(Exception e){
			assertEquals(true,false);
		}
	}
	
	
	
}
