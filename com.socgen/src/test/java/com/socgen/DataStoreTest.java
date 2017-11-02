package com.socgen;

import java.io.File;
import java.util.List;

import org.junit.Test;

import com.socgen.dataloader.DataStore;
import com.socgen.dataloader.DefaultDataLoader;
import com.socgen.exception.BrandNotFoundException;
import com.socgen.exception.CategoryNotFoundException;
import com.socgen.exception.InvalidFormatException;
import com.socgen.model.Brand;
import com.socgen.model.Category;
import com.socgen.model.InventoryItem;

import junit.framework.TestCase;

public class DataStoreTest extends TestCase {
    
	/**
	 * Tests that the store can be initialized with an inventory file in proper format
	 */
	@Test
	public void testForWorkingInventoryFile(){
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("inventory.txt").getFile());
		
		
		DataStore store = new DataStore(new DefaultDataLoader(),file.getPath());
		try {
			store.init();
			List<Brand> brands = store.getBrands();
			List<Category> categories = store.getCategories();
			List<InventoryItem> inventory = store.getInventory();
			
			assertEquals(6,brands.size());
			assertEquals(2,categories.size());
			assertEquals(5,inventory.size());
			
		} catch (Exception e) {
			e.printStackTrace();
			assertEquals(true, false);
		}
		
	}
	
	
	/**
	 * The inventory file is passed as an argument to the program
	 * But the Brands and Category are loaded by the program
	 * So we need to check for discrepancy between the brands in the file and the ones loaded by the program
	 */
	@Test
	public void testForUnknownBrandsInInventoryFile(){
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("inventory_unknown_brand.txt").getFile());
		
		DataStore store = new DataStore(new DefaultDataLoader(),file.getPath());
		try {
			store.init();
			assertEquals(true, false);
			
		} catch (Exception e) {
			assertEquals(BrandNotFoundException.class,e.getClass());
			
		}
		
	}
	
	/**
	 * The inventory file is passed as an argument to the program
	 * But the Brands and Category are loaded by the program
	 * So we need to check for discrepancy between the category in the file and the ones loaded by the program
	 */
	@Test
	public void testForUnknownCategoryInInventoryFile(){
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("inventory_unknown_category.txt").getFile());
		
		DataStore store = new DataStore(new DefaultDataLoader(),file.getPath());
		try {
			store.init();
			assertEquals(true, false);
			
		} catch (Exception e) {
			assertEquals(CategoryNotFoundException.class,e.getClass());
			
		}
		
	}
	
	
	/**
	 * Test for empty brand name in inventory file
	 */
    @Test
	public void testForMissingBrandsInInventoryFile(){
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("inventory_missing_brand.txt").getFile());
		
		DataStore store = new DataStore(new DefaultDataLoader(),file.getPath());
		try {
			store.init();
			assertEquals(true, false);
			
		} catch (Exception e) {
			assertEquals(InvalidFormatException.class,e.getClass());
			
		}
		
	}
	
	/**
	 * Test for empty category name in inventory file
	 */
    @Test
	public void testForMissingCategoryInInventoryFile(){
		
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("inventory_missing_brand.txt").getFile());
		
		DataStore store = new DataStore(new DefaultDataLoader(),file.getPath());
		try {
			store.init();
			assertEquals(true, false);
			
		} catch (Exception e) {
			assertEquals(InvalidFormatException.class,e.getClass());
			
		}
		
	}
	
	
	
}
