package br.ufrn.imd.market_manager.main;

import java.util.LinkedList;
import java.util.Scanner;

import br.ufrn.imd.market_manager.controller.ClientController;
import br.ufrn.imd.market_manager.controller.ProductController;
import br.ufrn.imd.market_manager.controller.SaleController;
import br.ufrn.imd.market_manager.util.InputScannerUtil;
import br.ufrn.imd.market_manager.util.Menu;

public class MarketManagerExecutor {
	
	private Scanner scanner = new Scanner(System.in);

	public void runMainMenu() {
		
		System.out.println("\t --------------------------------");
		System.out.println("\t|    SISTEMA DE GERENCIAMENTO    |");
		System.out.println("\t --------------------------------\n");
		
		String title = "Menu Principal";
		LinkedList<String> options = new LinkedList<String>();
		
		options.add("Clientes");
		options.add("Estoque");
		options.add("Venda");
		
		Menu mainMenu = new Menu(title, options);
		
		while(!mainMenu.exit()) {
			mainMenu.showMenu();
			mainMenu.makeSelection(scanner);
			
			String opt = mainMenu.getSelectedOption();
					
			switch (opt) {
			case "1":
				runClientMenu();
				break;
			case "2":
				runStockMenu();
				break;
			case "3":
				runSaleMenu();
				break;
			case "4":
				System.out.println("Encerrando aplicação...");
				break;
			
			default:
				mainMenu.printError();
				break;
			}
		}
	}
	
	public void runClientMenu() {
		
		String title = "Clientes";
		LinkedList<String> options = new LinkedList<String>();
		
		options.add("Buscar cliente");
		options.add("Novo cliente");
		options.add("Editar cliente");
		options.add("Remover cliente");
		
		Menu clientMenu = new Menu(title, options);
		clientMenu.setExitMessage("Voltar");
		
		ClientController controller = new ClientController();
		controller.setInputScanner(new InputScannerUtil(scanner));
		
		while(!clientMenu.exit()) {
			clientMenu.showMenu();
			clientMenu.makeSelection(scanner);
			
			String opt = clientMenu.getSelectedOption();
					
			switch (opt) {
			case "1":
				controller.searchClient();
				break;
			case "2":
				controller.newClient();
				break;
			case "3":
				controller.editClient();
				break;
			case "4":
				controller.removeClient();
				break;
			case "5":
				break;
			
			default:
				clientMenu.printError();
				break;
			}
		}
	}

	public void runStockMenu() {
		
		String title = "Estoque";
		LinkedList<String> options = new LinkedList<String>();
		
		options.add("Buscar produto");
		options.add("Novo produto");
		options.add("Editar produto");
		options.add("Remover produto");
		
		Menu stockMenu = new Menu(title, options);
		stockMenu.setExitMessage("Voltar");
		
		ProductController controller = new ProductController();
		controller.setInputScanner(new InputScannerUtil(scanner));
		
		while(!stockMenu.exit()) {
			stockMenu.showMenu();
			stockMenu.makeSelection(scanner);
			
			String opt = stockMenu.getSelectedOption();
					
			switch (opt) {
			case "1":
				controller.searchClient();
				break;
			case "2":
				controller.newProduct();
				break;
			case "3":
				controller.editProduct();
				break;
			case "4":
				controller.remove();
				break;
			case "5":
				break;
			
			default:
				stockMenu.printError();
				break;
			}
		}
	}
	
	public void runSaleMenu() {
		
		String title = "Vendas";
		LinkedList<String> options = new LinkedList<String>();
		
		options.add("Nova venda");
		options.add("Buscar vendas por produto");
		options.add("Buscar vendas por cliente");
		options.add("Contar vendas por localização");
		
		Menu saleMenu = new Menu(title, options);
		saleMenu.setExitMessage("Voltar");
		
		SaleController controller = new SaleController();
		controller.setInputScanner(new InputScannerUtil(scanner));
		
		while(!saleMenu.exit()) {
			saleMenu.showMenu();
			saleMenu.makeSelection(scanner);
			
			String opt = saleMenu.getSelectedOption();
					
			switch (opt) {
			case "1":
				controller.newSale();
				break;
			case "2":
				controller.searchByProduct();
				break;
			case "3":
				controller.searchByClient();
				break;
			case "4":
				controller.countByZipcode();
				break;
			case "5":
				break;
			
			default:
				saleMenu.printError();
				break;
			}
		}
	}
}
