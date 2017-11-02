package com.socgen;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.socgen.dataloader.DataStore;
import com.socgen.dataloader.DefaultDataLoader;
import com.socgen.discountrules.IDiscountCalculator;
import com.socgen.exception.InvalidArgumentException;
import com.socgen.exception.ItemNotFoundException;
import com.socgen.model.InventoryItem;
import com.socgen.utils.CommonUtils;
import com.socgen.utils.StoreManager;

public class Main {
	public static void main(String[] args) {

		try {

			if (args.length > 0 && args[0] != null && args[0].length() > 0) {
				DataStore store = new DataStore(new DefaultDataLoader(), args[0]);

				store.init();
				processResult(store);
			} else {
				throw new InvalidArgumentException("Please enter inventory file path");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	static void processResult(DataStore store) throws Exception {

		List<InventoryItem> items = store.getInventory();
		// Display Inventory
		System.out.println("STORE INVENTORY");
		System.out.println("**************************");
		for (InventoryItem item : items) {
			System.out.println(item);
		}
		System.out.println("**************************");
		// Accept user input
		System.out.println("Enter items count : ");
		Scanner reader = new Scanner(System.in);
		int cnt = reader.nextInt();
		int i = 0;
		List<String> choiceList = new ArrayList<String>();
		while (i < cnt) {
			System.out.println();
			System.out.println("Enter product ids - {example 1,2}  : ");
			String choice = reader.next();
			boolean isValid = CommonUtils.validateInputChoice(choice, items);
			if (!isValid) throw new ItemNotFoundException("Please check the input");
			choiceList.add(reader.next());
			i++;
		}
		StoreManager mgr = new StoreManager();
		List<String> results = mgr.calculateTotal(choiceList, items);
		results.forEach(System.out::println);
	}

}
