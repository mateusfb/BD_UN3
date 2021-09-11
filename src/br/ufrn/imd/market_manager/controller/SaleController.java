package br.ufrn.imd.market_manager.controller;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import br.ufrn.imd.market_manager.entity.Client;
import br.ufrn.imd.market_manager.entity.Product;
import br.ufrn.imd.market_manager.entity.Sale;
import br.ufrn.imd.market_manager.repository.ClientRepository;
import br.ufrn.imd.market_manager.repository.ProductRepository;
import br.ufrn.imd.market_manager.repository.SaleRepository;
import br.ufrn.imd.market_manager.util.InputScannerUtil;
import br.ufrn.imd.market_manager.util.Menu;

public class SaleController {
	
	private InputScannerUtil inputScanner;
	
	private SaleRepository repository = new SaleRepository();
	
	private ClientRepository clientRepository = new ClientRepository();
	
	private ProductRepository productRepository = new ProductRepository();

	public InputScannerUtil getInputScanner() {
		return inputScanner;
	}
	
	public void newSale() {
		String cpfCliente = inputScanner.inputString("CPF do cliente: ");
		
		Client client = clientRepository.findByCpf(cpfCliente);
		
		if(client == null) {
			System.out.println("Cliente não encontrado! Encerrando venda...");
			return;
		}
		
		Sale sale = new Sale();
		sale.setClient(client);
		
		String title = "-- COMPRA --";
		LinkedList<String> options = new LinkedList<String>();
		
		options.add("Adicionar produto");
		options.add("Remover produto");
		options.add("Finalizar compra");
		
		Menu menuSale = new Menu(title, options);
		menuSale.setExitMessage("Cancelar compra");
		
		while(!menuSale.exit()) {
			menuSale.showMenu();
			menuSale.makeSelection(inputScanner.getScanner());
			
			String opt = menuSale.getSelectedOption();
			
			switch (opt) {
			case "1":
				addProduct(sale);
				break;
			case "2":
				removeProduct(sale);
				break;
			case "3":
				if(!sale.getProducts().isEmpty()){	
					sale.setDate(LocalDateTime.now());
					repository.save(sale);
					menuSale.setExit(true);
				} else {
					System.err.println("Venda vazia!");
				}
				break;
			case "4":
				break;
				
			default:
				menuSale.printError();
				break;
			}
		}
	}
	
	public void addProduct(Sale sale) {
		String barcode = inputScanner.inputString("Código de barras: ");
		
		Product product = productRepository.findByBarcode(barcode);
		
		if(product == null) {
			System.out.println("Não existe um produto com este código.");
			return;
		}
		
		if(product.getQuantityInStock() > 0) {
			sale.addProduct(product, 1);
		} else {
			System.out.println("Produto fora de estoque!");
		}
		
		System.out.println("Total = R$" + sale.getTotal().toString());
	}
	
	public void removeProduct(Sale sale) {
		String barcode = inputScanner.inputString("Código de barras: ");
		
		Product product = productRepository.findByBarcode(barcode);
		
		if(product == null || !sale.getProducts().containsKey(product)) {
			System.out.println("A venda não possui este produto.");
			return;
		}
		
		sale.removeProduct(product, 1);
		
		System.out.println("Total = R$" + sale.getTotal().toString());
	}

	public void searchByProduct() {
		String barcode = inputScanner.inputString("Código de barras: ");
		
		List<Sale> sales = repository.findAllByProductBarcode(barcode);
		
		if(sales == null) {
			System.out.println("Nenhum resultado encontrado!");
		} else {
			for(Sale sale : sales) {
				sale.print();
			}
		}
	}
	
	public void searchByClient() {
		String cpf = inputScanner.inputString("CPF: ");
		
		List<Sale> sales = repository.findAllByClientCpf(cpf);
		
		if(sales == null) {
			System.out.println("Nenhum resultado encontrado!");
		} else {
			for(Sale sale : sales) {
				sale.print();
			}
		}
	}
	
	public void countByZipcode() {
		String zipcode = inputScanner.inputString("CEP: ");
		
		Long count = repository.countByZipcode(zipcode);
		
		System.out.println("Foram realizadas " + count.toString() + " compras por clientes que possuem endereço neste CEP.");
	}
	
	public void setInputScanner(InputScannerUtil inputScanner) {
		this.inputScanner = inputScanner;
	}

	public SaleRepository getRepository() {
		return repository;
	}

	public void setRepository(SaleRepository repository) {
		this.repository = repository;
	}
}
