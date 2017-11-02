package com.socgen.model;

import java.util.List;

/** 
 * This class represents a Category of an item
 * Every category will have a reference to its parent and children
 * Sample { Mens wear -> Shirts 
 *                        -> Formal wear 
 *                        -> Casual wear
 *                        
 *        }
 *           
 * @author srinivasan
 *
 */
public class Category {
	
	String name;
	Category parent;
	List<Category> children;
	int discount;
	
	public Category(String name,int discount){
		this.name = name;
		this.discount = discount;
	}
	
	public Category(String name,int discount,Category parent){
		this.name = name;
		this.discount = discount;
		this.parent = parent;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Category getParent() {
		return parent;
	}

	public void setParent(Category parent) {
		this.parent = parent;
	}

	public List<Category> getChildren() {
		return children;
	}

	public void setChildren(List<Category> children) {
		this.children = children;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}
	
	public String toString(){
		return this.name;
	}
    
}
