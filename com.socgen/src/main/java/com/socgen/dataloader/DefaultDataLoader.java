package com.socgen.dataloader;

import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.socgen.exception.BrandNotFoundException;
import com.socgen.exception.CategoryNotFoundException;
import com.socgen.exception.InvalidFormatException;
import com.socgen.model.Brand;
import com.socgen.model.Category;
import com.socgen.model.InventoryItem;
import com.socgen.utils.CommonUtils;

public class DefaultDataLoader implements IDataLoader{
	

	public List<Brand> loadBrands() {
		List<Brand> brands = new ArrayList<Brand>();
		brands.add(createBrand("Wrangler",10));
		brands.add(createBrand("Arrow",20));
		brands.add(createBrand("Vero Moda",60));
		brands.add(createBrand("UCB",0));
		brands.add(createBrand("Adidas",5));
		brands.add(createBrand("Provogue",20));
		return brands;
	}

	public List<Category> loadCategories() {
		
		List <Category> categoryList = new ArrayList<Category>();
		
		Category menswear = new Category("Men's wear",0);
		
		Category shirts = new Category("Shirts",0,menswear);
		Category trousers = new Category("Trousers",0,menswear);
		Category casuals = new Category("Casuals",30,menswear);
		Category jeans = new Category("Jeans",20,menswear);
		
		List<Category> children = new ArrayList<Category>();
		children.add(shirts);
		children.add(trousers);
		children.add(casuals);
		children.add(jeans);
		
		menswear.setChildren(children);
		
		
		Category womenswear = new Category("Women's wear",50);
		
		children = new ArrayList<Category>();
		
		Category dresses = new Category("Dresses",0,womenswear);
		Category footwear = new Category("Footwear",0,womenswear);
		
		children.add(dresses);
		children.add(footwear);
		
		womenswear.setChildren(children);
		
		categoryList.add(menswear);
		categoryList.add(womenswear);
		
		return categoryList;
		
	}
	
	public List<InventoryItem> loadInventory(String path,List<Brand> brands,List<Category> categories) 
			throws Exception{
		
		if (path == null){
    		throw new FileNotFoundException();
    	}
    	
		    List<String> inventoryList = null ;

		
		    List<String> fileLines = Files.lines(Paths.get(path)).collect(Collectors.toList());
		  
		   
			String itemCnt = fileLines.get(0);
			inventoryList = new ArrayList<String>();
			
			
			if (itemCnt != null){
				int cnt  = Integer.parseInt(itemCnt);
				inventoryList = fileLines.stream().skip(1).limit(cnt).collect(Collectors.toList());
			}

		
		
		List<InventoryItem> inventoryItemList = createInventoryList(inventoryList, brands, categories);
		
		return inventoryItemList;
	}
	
	
	private List<InventoryItem> createInventoryList(List<String> inventoryStrList,List<Brand> brands,List<Category> categories) throws Exception{
		

		List<InventoryItem> inventoryItems = new ArrayList<InventoryItem>();
		
		for (String line : inventoryStrList){
			String[] tokens = line.split(",");
			validateTokens(tokens);
			// Every line is of the form 1, Arrow,Shirts,800
			int id = Integer.parseInt(tokens[0].trim());
			
			Brand brand = CommonUtils.searchBrand(brands, tokens[1].trim());
			if (brand == null){
				throw new BrandNotFoundException("Please check the inventory for missing brand : " + tokens[1]);
			}
			
			Category category = CommonUtils.searchCategory(categories, tokens[2].trim());
			if (category == null){
				throw new CategoryNotFoundException("Please check the inventory for missing category : " + tokens[2]);
			}
			
			float price = Float.parseFloat(tokens[3]);
			
			InventoryItem item = new InventoryItem(id,brand,category,price);
			
			inventoryItems.add(item);
			
		}
		
		return inventoryItems;
	}
	
	private void validateTokens(String[] tokens) throws Exception{
		
		if (tokens.length < 4){
			throw new InvalidFormatException("Please check the format of the inventory file");
		}
		
		if (isEmpty(tokens[0])){
			throw new InvalidFormatException("Please check the format of the inventory file : id is missing");
		}
		
		if (isEmpty(tokens[1])){
			throw new InvalidFormatException("Please check the format of the inventory file : brand is missing");
		}
		
		if (isEmpty(tokens[2])){
			throw new InvalidFormatException("Please check the format of the inventory file : category is missing");
		}
		
		if (isEmpty(tokens[3])){
			throw new InvalidFormatException("Please check the format of the inventory file : price is missing");
		}
	}
	
	private boolean isEmpty(String str){
		if (str == null || str.trim().length() == 0){
			return true;
		}
		return false;
	}
	
	
	private Brand createBrand(String name,int discount){
		return new Brand(name,discount);
	}

}
