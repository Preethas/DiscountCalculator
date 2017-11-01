package com.socgen;

import static org.junit.Assert.*;

import java.io.File;
import java.util.List;

import org.junit.Test;

import com.socgen.dataloader.DataStore;
import com.socgen.dataloader.DefaultDataLoader;
import com.socgen.model.Brand;
import com.socgen.model.Category;
import com.socgen.utils.CommonUtils;

public class CommonUtilsTest {

	/**
	 * Tests if the discount is the maximum of all ancestors
	 */
	@Test
	public void testForGetMaxDiscount(){
		
		Category parent = new Category("Mens wear", 50);
		
		Category child = new Category("Trousers", 40);
		
		child.setParent(parent);
		
		Category grandchild = new Category("Cotton", 90);
		
		grandchild.setParent(child);
		
		int discount  = CommonUtils.getMaxDiscount(grandchild);
		
		assertEquals(90,discount);
	}
	
	@Test
	public void testForsearchingExistingBrand(){
		
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("inventory.txt").getFile());
		
		DataStore store = new DataStore(new DefaultDataLoader(),file.getPath());
		try {
			store.init();
			List<Brand> brands = store.getBrands();
			Brand brand = CommonUtils.searchBrand(brands, "Arrow");
			assertNotNull(brand);
		}catch(Exception e){
			assertEquals(true,false);
		}
		
	}
	
	@Test
	public void testForSearchingUnknownBrand(){
		
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("inventory.txt").getFile());
		
		DataStore store = new DataStore(new DefaultDataLoader(),file.getPath());
		try {
			store.init();
			List<Brand> brands = store.getBrands();
			Brand brand = CommonUtils.searchBrand(brands, "Arrows");
			assertNull(brand);
		}catch(Exception e){
			assertEquals(true,false);
		}
		
	}
	
    @Test
	public void testForSearchExistingCategory(){
		
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("inventory.txt").getFile());
		
		DataStore store = new DataStore(new DefaultDataLoader(),file.getPath());
		try {
			store.init();
			List<Category> categories = store.getCategories();
			Category category = CommonUtils.searchCategory(categories, "Dresses");
			assertNotNull(category);
			assertEquals("Dresses",category.getName());
		}catch(Exception e){
			assertEquals(true,false);
		}
		
	}
	
	@Test
	public void testForSearchingUnknownCategory(){
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("inventory.txt").getFile());
		
		DataStore store = new DataStore(new DefaultDataLoader(),file.getPath());
		try {
			store.init();
			List<Category> categories = store.getCategories();
			Category category = CommonUtils.searchCategory(categories, "Dummy");
			assertNull(category);
		}catch(Exception e){
			assertEquals(true,false);
		}
		
	}
	
	
}
