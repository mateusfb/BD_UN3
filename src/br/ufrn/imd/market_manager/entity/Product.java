package br.ufrn.imd.market_manager.entity;

import br.ufrn.imd.market_manager.enums.EnumDepartment;

public class Product {

	private Long id;
	private String description;
	private String barcode;
	private EnumDepartment department;
	private Long quantityInStock;
	private Double price;
	
	public Product() {}

	public Product(Long id, String description, String barcode, EnumDepartment department, Long quantityInStock,
			Double price) {
		super();
		this.id = id;
		this.description = description;
		this.barcode = barcode;
		this.department = department;
		this.quantityInStock = quantityInStock;
		this.price = price;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public EnumDepartment getDepartment() {
		return department;
	}

	public void setDepartment(EnumDepartment department) {
		this.department = department;
	}

	public Long getQuantityInStock() {
		return quantityInStock;
	}

	public void setQuantityInStock(Long quantityInStock) {
		this.quantityInStock = quantityInStock;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "'" + this.description + "', '" + this.barcode + "', '" + this.department.toString()
				+ "', " + this.quantityInStock.toString() + ", " + price.toString();
	}
	
	public void print() {
		String str = "";
		
		if(description != null) str += "\t" + description + "\n";
		if(barcode != null) str += "\t" + barcode + "\n";
		if(department != null) str += "\t" + department.getDescription() + "\n";
		if(quantityInStock != null) str += "\t" + quantityInStock.toString() + " em estoque \n";
		if(price != null) str += "\tR$" + price.toString() + "\n";
	
		System.out.println(str);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((barcode == null) ? 0 : barcode.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (barcode == null) {
			if (other.barcode != null)
				return false;
		} else if (!barcode.equals(other.barcode))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
