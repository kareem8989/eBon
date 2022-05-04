package model;

public class Product {
	
	private String name;
	double price;
	
	
	
	public Product (String line){
		String[] pieces = line.split("[|]");
		
		this.name = pieces[0];
		this.price = Double.parseDouble(pieces[1]);
	}

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}


	@Override
	public String toString() {
		return "Product [name=" + name + ", price=" + price + "]";
	}
	
	
	

}
