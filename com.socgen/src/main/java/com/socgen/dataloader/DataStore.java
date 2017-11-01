package com.socgen.dataloader;

import java.util.List;

import com.socgen.model.Brand;
import com.socgen.model.Category;
import com.socgen.model.InventoryItem;

public class DataStore {
	
	IDataLoader loader ;
  
	List<Brand> brands;
	
	List<Category> categories;
	
	List<InventoryItem> inventory;
	
	String inventoryFilePath;
	
	public DataStore(IDataLoader loader,String inventoryFilePath){
		this.loader = loader;
		this.inventoryFilePath = inventoryFilePath;
	}
	
	public void init() throws Exception{
		brands = this.loader.loadBrands();
		categories = this.loader.loadCategories();
		inventory = this.loader.loadInventory(inventoryFilePath, brands, categories);
	}

	public List<Brand> getBrands() {
		return brands;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public List<InventoryItem> getInventory() {
		return inventory;
	}

	
	
	
	
	
}
