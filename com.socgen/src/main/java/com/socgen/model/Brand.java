package com.socgen.model;


/**
 * This class represents a Brand 
 * @author srinivasan
 *
 */
public class Brand {

	String name;
	int discount;
	
	public Brand(String name,int discount){
		this.name = name;
		this.discount = discount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
