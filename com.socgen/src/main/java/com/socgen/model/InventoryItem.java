package com.socgen.model;

/**
 * This class represents an inventory item
 * @author srinivasan
 *
 */

public class InventoryItem {

	int id;
	Brand brand;
	Category category;
	float price;
	
	
	
	public InventoryItem(int id,Brand brand,Category category,float price){
		this.id = id;
		this.brand = brand;
		this.category = category;
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}
	
	public boolean equals(Object o){
		 // If the object is compared with itself then return true  
        if (o == this) {
            return true;
        }
 
        /* Check if o is an instance of InventoryItem or not
          "null instanceof [type]" also returns false */
        if (!(o instanceof InventoryItem)) {
            return false;
        }
         
        // typecast o to InventoryItem so that we can compare data members 
        InventoryItem c = (InventoryItem) o;
         
        // Compare the data members and return accordingly 
        return c.getId() == this.getId();
	}
	
	public int hashcode(){
		return id;
	}
	
	public String toString(){
		return String.join(",",new String[]{id + "",brand.getName(),category.getName(),
		String.valueOf(price)});
	}

}
