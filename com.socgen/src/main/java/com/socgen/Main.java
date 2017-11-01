package com.socgen;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.socgen.dataloader.DataStore;
import com.socgen.dataloader.DefaultDataLoader;
import com.socgen.model.InventoryItem;
import com.socgen.utils.StoreCalculator;

public class Main {
	public static void main(String[] args) {

		try {

			DataStore store = new DataStore(new DefaultDataLoader(), "/Users/srinivasan/inventory.txt");
			store.init();
			readInput(store);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	static void readInput(DataStore store) throws Exception {

		List<InventoryItem> items = store.getInventory();
		// Display Inventory
		System.out.println("STORE INVENTORY");
		System.out.println("**************************");
		for (InventoryItem item : items) {
			System.out.println(item);
		}
		System.out.println("**************************");
		// Accept user input
		System.out.print("Enter items count : ");
		Scanner reader = new Scanner(System.in);
		int cnt = reader.nextInt();
		int i = 0;
		List<String> choiceList = new ArrayList<String>();
		while (i < cnt) {
			System.out.println();
			System.out.println("Enter product ids - example 1,2 : ");
			choiceList.add(reader.next());
			i++;
		}

		StoreCalculator.calculateTotal(choiceList, items);
	}

}
