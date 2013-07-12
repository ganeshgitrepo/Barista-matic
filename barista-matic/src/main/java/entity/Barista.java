package entity;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

public class Barista {
	private HashMap<String, Integer> inventory;	// HashMap of ingredients and cost per unit
	private HashMap<String, Integer> sales;		// HashMap of drinks and corresponding sales
	private Double expenses;					// Expenses determined from restocking cost
	private Double revenue;						// Total money received from drink sales

	public Barista() {
		this.inventory = setupInventory();
		this.sales = new HashMap<String, Integer>();
		//this.expenses = 
		this.revenue = 0.00;
	}

	private HashMap<String, Integer> setupInventory() {
		try {
			FileInputStream in = new FileInputStream("../../resources/barista.properties");
			Properties properties = new Properties();
			HashMap<String, Integer> inventory = new HashMap();
			properties.load(in);
			in.close();
			while (properties.propertyNames().hasMoreElements()) {
				String prop = properties.propertyNames().nextElement().toString();
				if (prop.contains("ingredients")) {
					this.inventory.put(prop.replaceAll("(ingredients.)", ""),
							Integer.valueOf((String) properties.get(prop)));
				}
			}
		} catch (FileNotFoundException e) {
			System.err.println("FileNotFoundException: Couldn't localte barista.properties.");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("IOException: There was a problem reading from the inputstream.");
			e.printStackTrace();
		}

		return inventory;
	}

	public void restockAll() {

	}

	public void calculateExpenses(Drink[] drinks) {

	}
}
