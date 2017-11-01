package com.socgen.utils;

import java.util.List;

import com.socgen.model.Brand;
import com.socgen.model.Category;


public class CommonUtils {
	
	public static int getMaxDiscount(Category category){
		int max = Integer.MIN_VALUE;
		
		while (category != null){
			if (category.getDiscount() > max ){
				max = category.getDiscount();
			}
			category = category.getParent();
		}
		return max;
	}
	
	public static Brand searchBrand(List<Brand> brands,String brandName){
		Brand result = null;
		for (Brand b : brands){
			if (b.getName().equals(brandName)){
				result = b;
				break;
			}
		}
		return result;
	}
	
	public static Category searchCategory(List<Category> categoryList,String categoryName){
		Category category = searchRecursively(categoryList,categoryName);
		return category;
	}
	
	private static Category searchRecursively(List<Category> categoryList,String categoryName){
		 Category result = null;
		 for (Category category : categoryList){
			if (category.getName().equals(categoryName)){
				result = category;
				break;
			} else
			if (category.getChildren() != null) {
			  result = searchRecursively(category.getChildren(),categoryName);
			  if (result != null) break;
			  
			}
			
         }
		return result;
	}

}
