package br.ufrn.imd.market_manager.entity;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;

public class Sale {
	
	private Long id;
	private Client client;
	private Double total = 0.0;
	private LocalDateTime date;
	
	private HashMap<Product, Long> products;

	public Sale() {
		this.products = new HashMap<Product, Long>();
	}

	public Sale(Long id, Client client, Double total, LocalDateTime date) {
		super();
		this.id = id;
		this.client = client;
		this.total = total;
		this.date = date;
		
		this.products = new HashMap<Product, Long>();
	}
	
	public void addProduct(Product product, long amount) {
		if(products.containsKey(product)) {
			products.put(product, products.get(product) + amount);
		} else {
			products.put(product, amount);
		}
		
		this.total += product.getPrice() * amount;
	}
	
	public void removeProduct(Product product, long amount) {
		if(products.containsKey(product)) {
			long newAmount = products.get(product) - amount;
			
			if(amount <= 0) {
				products.remove(product);
			} else {
				products.put(product, newAmount);
			}
		}
		

		this.total -= product.getPrice() * amount;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public HashMap<Product, Long> getProducts() {
		return products;
	}

	public void setProducts(HashMap<Product, Long> products) {
		this.products = products;
	}

	@Override
	public String toString() {
		return client.getId() + ", " + total + ", '" + Timestamp.valueOf(date) + "'";
	}
	
	public void print() {
		System.out.println("\t -> Compra no valor de R$" + total.toString() 
						 + "\n\t    realizada em " + date.toString()
						 + "\n\t    por " + client.getName());
	}
}
