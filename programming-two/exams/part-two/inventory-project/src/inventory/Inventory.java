package inventory;

import java.util.Scanner;
import java.util.ArrayList;

import calculations.Calc;

public class Inventory {
	
	public static ArrayList<Product> products = new ArrayList<>();
	public static Double IVA = 0.16, exchangeRate = 40.00; 
	
	// MENU functions
	public static void addProduct() {
		Scanner addScanner = new Scanner(System.in);
		
		System.out.println("=== ADD PRODUCT =====================");
		
		System.out.print("How many items would you like to enter?\nAmount: ");
		Integer itemsToAdd = addScanner.nextInt();
		addScanner.nextLine();
		
		for (Integer i = 0; i < itemsToAdd; i++) {
			String productId = "";
			String productName = "";
			Double productPrice = 0.0;
			Integer productQuantity = 0;
			Integer productDiscount = 0;
			
			System.out.println("=== ADD PRODUCT =====================");
			System.out.println("[Item #" + (i+1) + "]");
			
			while (true) {
				System.out.print("ID (4 letters): ");
				productId = addScanner.nextLine().toUpperCase();
				Boolean invalid = false;
				
				if (productId.length() != 4) {
					System.out.println("[Invalid ID. Must be 4 letters long]");
					invalid = true;
				}

				for (Integer j = 0; j < products.size(); j++) {
					if (products.get(j).getId().equals(productId)) {
						System.out.println("[Invalid ID. Repeated ID's can't be provided]");
						invalid = true;
						break;
					}
				}
				
				if (!invalid) {
					break;
				}
			}

			while (true) {
				System.out.print("Name: ");
				productName = addScanner.nextLine();
				if (!productName.equals("")) {
					break;
				}
				
				System.out.println("[Invalid name. Must not be empty]");
			}

			while (true) {
				System.out.print("Price (USD): ");
				productPrice = addScanner.nextDouble();
				addScanner.nextLine();
				if (productPrice > 0) {
					break;
				}
				
				System.out.println("[Invalid price. Must be more than 0]");
			}

			while (true) {
				System.out.print("Quantity (Units): ");
				productQuantity = addScanner.nextInt();
				addScanner.nextLine();
				if (productQuantity > 0) {
					break;
				}
				
				System.out.println("[Invalid quantity. Must be more than 0]");
			}

			while (true) {
				System.out.print("Discount (%): ");
				productDiscount = addScanner.nextInt();
				addScanner.nextLine();
				if (productDiscount >= 0 && productDiscount < 100) {
					break;
				}
				
				System.out.println("[Invalid discount. Must be between 0 and 100]");
			}

			products.add(new Product(productId, productName, productPrice, productQuantity, productDiscount));
		}
	}
	
	public static void editProduct() {
		if (products.size() <= 0) {
			System.out.println("[No products to edit]");
			return;
		}
		
		Scanner editScanner = new Scanner(System.in);
		Scanner optScanner = new Scanner(System.in);
		Integer index = -1;
		
		System.out.println("=== EDIT PRODUCT ====================\n"
				+ "ID   | NAME\n"
				+ "-----------");
		for (Integer i = 0; i < products.size(); i++) {
			System.out.println(products.get(i).id + " | " + products.get(i).name);
		}
		
		System.out.print("Provide the ID of the item you want to edit ('q' to exit): ");
		String selectedID = editScanner.nextLine();

		if (selectedID.equals("q")) {
			System.out.println("[Exiting...]");
			return;
		}

		for (Integer i = 0; i < products.size(); i++) {
			if (products.get(i).id.equals(selectedID)) {
				index = i;
				break;
			}
		}
		
		if (index == -1) {
			System.out.println("[Item not found or incorrect ID]");
			return;
		}
		
		System.out.print("Select the property to edit:\n"
				+ "1. ID\n"
				+ "2. Name\n"
				+ "3. Price\n"
				+ "4. Quantity\n"
				+ "5. Discount\n"
				+ "6. Exit\n"
				+ "Option: ");
		Integer opt = optScanner.nextInt();
		
		Scanner switchScanner = new Scanner(System.in);
		
		switch (opt) {
			case 1:
				while (true) {
					System.out.print("New ID (4 letters): ");
					String productID = switchScanner.nextLine().toUpperCase();
					Boolean invalid = false;
					
					if (productID.length() != 4) {
						System.out.println("[Invalid ID. Must be 4 letters long]");
						invalid = true;
					}

					for (Integer j = 0; j < products.size(); j++) {
						if (products.get(j).getId().equals(productID)) {
							System.out.println("[Invalid ID. Repeated ID's can't be provided]");
							invalid = true;
							break;
						}
					}
					
					if (!invalid) {
						products.get(index).id = productID;
						break;
					}
				}
				break;
			case 2:
				System.out.print("New name: ");
				products.get(index).id = switchScanner.nextLine();
				break;
			case 3:
				while (true) {
					System.out.print("New price (USD): ");
					Double productPrice = switchScanner.nextDouble();
					switchScanner.nextLine();
					if (productPrice > 0) {
						products.get(index).price = productPrice;
						break;
					}
					
					System.out.println("[Invalid price. Must be more than 0]");
				}
				break;
			case 4:
				while (true) {
					System.out.print("New quantity (Units): ");
					Integer productQuantity = switchScanner.nextInt();
					switchScanner.nextLine();
					if (productQuantity > 0) {
						products.get(index).quantity = productQuantity;
						break;
					}
					
					System.out.println("[Invalid quantity. Must be more than 0]");
				}
				break;
			case 5:
				while (true) {
					System.out.print("New discount (%): ");
					Integer productDiscount = switchScanner.nextInt();
					switchScanner.nextLine();
					if (productDiscount >= 0 && productDiscount < 100) {
						products.get(index).discount = productDiscount;
						break;
					}
					
					System.out.println("[Invalid discount. Must be between 0 and 100]");
				}
				break;
			case 6:
				System.out.println("[Exiting...]");
				break;
			default:
				System.out.println("[Option not allowed]");
				break;
		}
	}
	
	public static void listProducts() {
		if (products.size() <= 0) {
			System.out.println("[No products to list]");
			return;
		}
		
		System.out.println("=== LIST PRODUCTS ===================\n"
				+ "ID   | NAME\n"
				+ "-----------");
		for (Integer i = 0; i < products.size(); i++) {
			System.out.println(products.get(i).id + " | " + products.get(i).name);
		}
	}

	public static void showStockStatistics() {
		if (products.size() <= 0) {
			System.out.println("[No statistics available]");
			return;
		}
		
		String netTotal = Calc.calculateNetTotal(products); 
		String grossTotal = Calc.calculateGrossTotal(products);
		String cheapestInStock = Calc.calculateCheapExpensive(products).get("cheap");
		String mostExpensiveInStock = Calc.calculateCheapExpensive(products).get("expensive");
		String lowestQuantity = Calc.calculateLowestHighestQuantity(products).get("lowest");
		String highestQuantity = Calc.calculateLowestHighestQuantity(products).get("highest");
		String lowestDiscount = Calc.calculateLowestHighestDiscount(products).get("lowest");
		String highestDiscount = Calc.calculateLowestHighestDiscount(products).get("highest");
		String totalProducts = Calc.calculateTotalAmountOfProducts(products);

		System.out.println("=== STATISTICS ======================\n"
		+ "- Amount of different products in the inventory: " + products.size() + "\n"
		+ "- Total amount of products: " + totalProducts + "\n"
			+ "- Inventory's net total in products: " + netTotal + "\n"
			+ "- Inventory's gross total in products: " + grossTotal + "\n"
			+ "- Cheapest product: " + cheapestInStock + "\n"
			+ "- Most expensive product: " + mostExpensiveInStock + "\n"
			+ "- Lowest quantity product: " + lowestQuantity + "\n"
			+ "- Highest quantity product: " + highestQuantity + "\n"
			+ "- Lowest discount product: " + lowestDiscount + "\n"
			+ "- Highest discount product: " + highestDiscount
		);
	}
	
	public static void modifySettings() {
		Scanner settingScanner = new Scanner(System.in);
		Scanner switchScanner = new Scanner(System.in);
		
		System.out.println("=== SETTINGS ========================");
		System.out.print("Select a setting to modify: \n"
				+ "1. Exchange rate (Current: BsS." + String.format("%.2f", exchangeRate) + ")\n"
				+ "2. IVA (Current: " + String.format("%.0f", (IVA * 100)) + "%)\n"
				+ "3. Exit\n"
				+ "Option: ");
		Integer opt = settingScanner.nextInt();
		
		switch (opt) {
			case 1:
				while (true) {
					System.out.print("Insert a new ER (BsS per dollar): ");
					Double tempER = switchScanner.nextDouble();
					switchScanner.nextLine();
					if (tempER > 0) {
						exchangeRate = tempER;
						return;
					}
					
					System.out.println("[ER not allowed. Must be more than 0]");
				}
			case 2:
				while (true) {
					System.out.print("Insert a new IVA (%): ");
					Double tempIVA = switchScanner.nextDouble();
					switchScanner.nextLine();
					if (tempIVA >= 0 && tempIVA <= 100) {
						IVA = tempIVA / 100;
						return;
					}
					
					System.out.println("[IVA not allowed. Must be between 0 and 100]");
				}
			case 3:
				System.out.println("[Exiting...]");
				break;
			default:
				System.out.println("[Option not allowed]");
				break;
		}
	}
	
	public static void main(String[] args) {
		Boolean flag = true;
		Scanner mainScanner = new Scanner(System.in);
		
        do {
            System.out.print("=== MENU ============================\n"
                    + "1. Enter X new products\n"
                    + "2. Edit product\n"
                    + "3. List products\n"
                    + "4. Statistics\n"
                    + "5. Settings\n"
                    + "6. [Exit]\n"
                    + "Option: ");
            Integer opt = mainScanner.nextInt();

            switch (opt) {
                case 1: 
                    addProduct();
                    Utils.waitForUserInput();
                    break;
                    
                case 2:
                    editProduct();
                    Utils.waitForUserInput();
                    break;
                
                case 3: 
                	listProducts();
                	Utils.waitForUserInput();
                    break;
                
                case 4: 
                    showStockStatistics();
                    Utils.waitForUserInput();
                    break;
                
                case 5: 
                    modifySettings();
                    Utils.waitForUserInput();
                    break;
                
                case 6: 
                    System.out.println("[Exiting...]");
                    Utils.waitForUserInput();
                    flag = false;
                    break;
                
                default:
                    System.out.println("[Option not allowed]");
                    Utils.waitForUserInput();
                    break;
            }

            mainScanner.nextLine();

        } while (flag);

        mainScanner.close();
    }
}
