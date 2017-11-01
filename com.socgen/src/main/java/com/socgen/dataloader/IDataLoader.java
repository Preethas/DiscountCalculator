package com.socgen.dataloader;

import java.util.List;

import com.socgen.model.Brand;
import com.socgen.model.Category;
import com.socgen.model.InventoryItem;

public interface IDataLoader {
   List<Brand> loadBrands();
   List<Category> loadCategories();
   List<InventoryItem> loadInventory(String path,List<Brand> brands,List<Category> categories) throws Exception;
}
