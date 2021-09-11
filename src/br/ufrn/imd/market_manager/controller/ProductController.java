package br.ufrn.imd.market_manager.controller;

import br.ufrn.imd.market_manager.entity.Product;
import br.ufrn.imd.market_manager.enums.EnumDepartment;
import br.ufrn.imd.market_manager.repository.ProductRepository;
import br.ufrn.imd.market_manager.util.InputScannerUtil;

public class ProductController {
	
	private InputScannerUtil inputScanner;
	
	private ProductRepository repository = new ProductRepository();
	
	public void newProduct() {
		Product product = new Product();
		
		String description = inputScanner.inputString("Descrição: ");
		String barcode = inputScanner.inputString("Código de barras: ");
		EnumDepartment department = inputScanner.inputDepartment();
		Long quantityInStock = inputScanner.inputLong("Quantidade em estoque: ");
		Double price = inputScanner.inputDouble("Preço: ");
		
		product.setDescription(description);
		product.setBarcode(barcode);
		product.setDepartment(department);
		product.setQuantityInStock(quantityInStock);
		product.setPrice(price);
		
		save(product);
	}
	
	public void editProduct() {
		String searchedBarcode = inputScanner.inputString("Código de barras: ");
		
		Product product = repository.findByBarcode(searchedBarcode);
		
		String description = inputScanner.inputString("Descrição: ");
		String barcode = inputScanner.inputString("Código de barras: ");
		EnumDepartment department = inputScanner.inputDepartment();
		Long quantityInStock = inputScanner.inputLong("Quantidade em estoque: ");
		Double price = inputScanner.inputDouble("Preço: ");
		
		product.setDescription(description);
		product.setBarcode(barcode);
		product.setDepartment(department);
		product.setQuantityInStock(quantityInStock);
		product.setPrice(price);
		
		save(product);
	}
	
	public void save(Product product) {
		Product productDuplicate = repository.findByBarcode(product.getBarcode());
		
		if(productDuplicate != null && !productDuplicate.getId().equals(product.getId())) {
			System.err.println("Já existe produto cadastrado com este código de barras!");
			return;
		}
		
		repository.save(product);
	}
	
	public void searchClient() {
		String barcode = inputScanner.inputString("Código de barras: ");
		
		Product product = repository.findByBarcode(barcode);
		
		if(product != null) {
			product.print();
		} else {
			System.out.println("Nenhum resultado encontrado!");
		}
	}
	
	public void remove() {
		String barcode = inputScanner.inputString("Código de barras: ");
		
		repository.remove(barcode);
	}

	public InputScannerUtil getInputScanner() {
		return inputScanner;
	}

	public void setInputScanner(InputScannerUtil inputScanner) {
		this.inputScanner = inputScanner;
	}

	public ProductRepository getRepository() {
		return repository;
	}

	public void setRepository(ProductRepository repository) {
		this.repository = repository;
	}
	
}
