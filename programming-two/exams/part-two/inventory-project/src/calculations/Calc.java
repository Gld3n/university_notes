package calculations;

import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

import inventory.Product;
import static inventory.Inventory.IVA;
import static inventory.Inventory.exchangeRate;

public class Calc {

	public static Map<String, String> formatPrice(Double price) {
	    Double PriceInBs = price * exchangeRate;
	    Double NetPrice = price + (price * IVA);
	    Double NetPriceInBs = NetPrice * exchangeRate;

	    Map<String, String> formattedPrices = new HashMap<>();

	    StringBuilder formattedPriceBuilder = new StringBuilder();
	    formattedPriceBuilder.append("$").append(String.format("%.2f", price))
	            .append(" (BsS.").append(String.format("%.2f", PriceInBs)).append(")");
	    formattedPrices.put("price", formattedPriceBuilder.toString());

	    StringBuilder formattedNetPriceBuilder = new StringBuilder();
	    formattedNetPriceBuilder.append("$").append(String.format("%.2f", NetPrice))
	            .append(" (BsS.").append(String.format("%.2f", NetPriceInBs)).append(")");
	    formattedPrices.put("netPrice", formattedNetPriceBuilder.toString());

	    return formattedPrices;
	}

	public static Map<String, String> calculateCheapExpensive(ArrayList<Product> products) {
		Map<String, String> cheapAndExpensive = new HashMap<>();
		
		String cheapID = products.get(0).getId(), expensiveID = products.get(0).getId();
		Double lowestPrice = products.get(0).price, highestPrice = 0.0;
		
		for (Integer i = 0; i < products.size(); i++) {
			Product currentProduct = products.get(i);
			if (currentProduct.price < lowestPrice) {
				lowestPrice = currentProduct.price;
				cheapID = currentProduct.getId();
			}
		}
		
		for (Integer i = 0; i < products.size(); i++) {
			Product currentProduct = products.get(i);
			if (currentProduct.price > highestPrice) {
				highestPrice = currentProduct.price;
				expensiveID = currentProduct.getId();
			}
		}
		
		String formattedExpensivePrice = formatPrice(highestPrice).get("price");		
		StringBuilder expensivePrice = new StringBuilder();
		expensivePrice.append("[").append(expensiveID).append(" | ")
				.append(formattedExpensivePrice).append("] ");
		
		String formattedCheapPrice = formatPrice(lowestPrice).get("price");
		StringBuilder cheapPrice = new StringBuilder();
		cheapPrice.append("[").append(cheapID).append(" | ")
				.append(formattedCheapPrice).append("] ");
		
		cheapAndExpensive.put("expensive", expensivePrice.toString());
		cheapAndExpensive.put("cheap", cheapPrice.toString());
		
		return cheapAndExpensive;
	}

	public static Map<String, String> calculateLowestHighestDiscount(ArrayList<Product> products) {
		Map<String, String> lowestHighestDiscount = new HashMap<>();
		
		String lowestDiscountID = products.get(0).getId(), highestDiscountID = products.get(0).getId();
		Integer lowestDiscount = products.get(0).discount, highestDiscount = 0;
		
		for (Integer i = 0; i < products.size(); i++) {
			Product currentProduct = products.get(i);
			if (currentProduct.discount < lowestDiscount) {
				lowestDiscount = currentProduct.discount;
				lowestDiscountID = currentProduct.getId();
			}
		}
		
		for (Integer i = 0; i < products.size(); i++) {
			Product currentProduct = products.get(i);
			if (currentProduct.discount > highestDiscount) {
				highestDiscount = currentProduct.discount;
				highestDiscountID = currentProduct.getId();
			}
		}
		
		StringBuilder highestDiscountBuilder = new StringBuilder();
		highestDiscountBuilder.append("[").append(highestDiscountID).append(" | ")
				.append(highestDiscount).append("%] ");
		
		StringBuilder lowestDiscountBuilder = new StringBuilder();
		lowestDiscountBuilder.append("[").append(lowestDiscountID).append(" | ")
				.append(lowestDiscount).append("%] ");
		
		String highestDiscountString = highestDiscountBuilder.toString();
		String lowestDiscountString = lowestDiscountBuilder.toString();
		
		lowestHighestDiscount.put("highest", highestDiscountString);
		lowestHighestDiscount.put("lowest", lowestDiscountString);
		
		return lowestHighestDiscount;
	}

	public static Map<String, String> calculateLowestHighestQuantity(ArrayList<Product> products) {
		Map<String, String> lowestHighestQuantity = new HashMap<>();
		
		String lowestQuantityID = products.get(0).getId(), highestQuantityID = products.get(0).getId();
		Integer lowestQuantity = products.get(0).quantity, highestQuantity = 0;
		
		for (Integer i = 0; i < products.size(); i++) {
			Product currentProduct = products.get(i);
			if (currentProduct.quantity < lowestQuantity) {
				lowestQuantity = currentProduct.quantity;
				lowestQuantityID = currentProduct.getId();
			}
			if (currentProduct.quantity > highestQuantity) {
				highestQuantity = currentProduct.quantity;
				highestQuantityID = currentProduct.getId();
			}
		}
		
		StringBuilder highestQuantityBuilder = new StringBuilder();
		highestQuantityBuilder.append("[").append(highestQuantityID).append(" | ")
				.append(highestQuantity).append("u] ");
		
		StringBuilder lowestQuantityBuilder = new StringBuilder();
		lowestQuantityBuilder.append("[").append(lowestQuantityID).append(" | ")
				.append(lowestQuantity).append("u] ");
		
		String highestQuantityString = highestQuantityBuilder.toString();
		String lowestQuantityString = lowestQuantityBuilder.toString();
		
		lowestHighestQuantity.put("highest", highestQuantityString);
		lowestHighestQuantity.put("lowest", lowestQuantityString);
		
		return lowestHighestQuantity;
	}
	
	public static String calculateGrossTotal(ArrayList<Product> products) {
		Double grossTotal = 0.0;
		
		for (Integer i = 0; i < products.size(); i++) {
			grossTotal += products.get(i).price * products.get(i).quantity;
		}
		String formattedGross = formatPrice(grossTotal).get("price");
		
		return formattedGross;
	}
	
	public static String calculateNetTotal(ArrayList<Product> products) {
		Double total = 0.0;
		
		for (Integer i = 0; i < products.size(); i++) {
			Product currentProduct = products.get(i);
			Double grossProduct = currentProduct.price * currentProduct.quantity;
			Double netProduct = grossProduct - (grossProduct * currentProduct.discount / 100);
			total += netProduct;
		}
		
		Double netTotal = total + (total * IVA);
		String formattedNet = formatPrice(netTotal).get("price");
		
		return formattedNet;
	}

	public static String calculateTotalAmountOfProducts(ArrayList<Product> products) {
		Integer totalAmount = 0;
		
		for (Integer i = 0; i < products.size(); i++) {
			totalAmount += products.get(i).quantity;
		}
		
		return totalAmount.toString();
	}
}
