package app;

import java.io.IOException;
import java.util.List;

import model.Product;
import services.ProductServices;

public class App {
	
	
	public static void main(String[] args) throws IOException {
		
		
		List<Product>  list = ProductServices.loadItems("REWE-eBon.pdf");
		
		
		for (Product p : list) {
			System.out.println(p);
		}
	}

}
