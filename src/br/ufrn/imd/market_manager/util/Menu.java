package br.ufrn.imd.market_manager.util;

import java.util.LinkedList;
import java.util.Scanner;

public class Menu {
	
	private String title;
	private LinkedList<String> options;
	private String selectedOption;
	private String errorMessage = "Selecione uma opção válida!";
	private String exitMessage = "Sair";
	private String exitOptionIndex;
	private boolean exit;
	
	public Menu() {}

	public Menu(String title, LinkedList<String> options) {
		super();
		this.title = title;
		this.options = options;
	}
	
	public void showTitle() {
		System.out.println("### " + this.title + " ###");
	}
	
	public void showOptions() {
		Integer num = 1;
		
		for(String option : this.options) {
			System.out.println("[" + num.toString() + "] " + option);
			num++;
		}
		
		System.out.println("[" + num.toString() + "] " + exitMessage);
		this.exitOptionIndex = num.toString();
	}
	
	public void showMenu() {
		this.showTitle();
		System.out.println();
		this.showOptions();
		System.out.println();
	}
	
	public void printError() {
		System.err.println(errorMessage);
	}
	
	public void makeSelection(Scanner scanner) {
		System.out.print("Selecione uma opção: ");
		this.selectedOption = scanner.nextLine();
		
		if(this.selectedOption.equals(exitOptionIndex)) {
			this.exit = true;
		}
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public LinkedList<String> getOptions() {
		return options;
	}

	public void setOptions(LinkedList<String> options) {
		this.options = options;
	}

	public String getSelectedOption() {
		return selectedOption;
	}

	public void setSelectedOption(String selectedOption) {
		this.selectedOption = selectedOption;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getExitMessage() {
		return exitMessage;
	}

	public void setExitMessage(String exitMessage) {
		this.exitMessage = exitMessage;
	}

	public String getExitOptionIndex() {
		return exitOptionIndex;
	}

	public void setExitOptionIndex(String exitOptionIndex) {
		this.exitOptionIndex = exitOptionIndex;
	}

	public boolean exit() {
		return exit;
	}

	public void setExit(boolean exit) {
		this.exit = exit;
	}
}
