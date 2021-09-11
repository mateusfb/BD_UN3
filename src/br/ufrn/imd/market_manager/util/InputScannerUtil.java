package br.ufrn.imd.market_manager.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import br.ufrn.imd.market_manager.enums.EnumDepartment;
import br.ufrn.imd.market_manager.enums.EnumSex;

public class InputScannerUtil {

	private Scanner scanner;
	
	public InputScannerUtil(Scanner scanner) {
		this.scanner = scanner;
	}
	
	public String inputString(String text) {
		String input;
		
		while(true) {
			System.out.print(text);
			input = scanner.nextLine();
			
			if(!input.isEmpty()) {
				break;
			}
			
			System.err.println("Insira uma entrada válida!");
		}
		
		return input;
	}
	
	public Double inputDouble(String text) {
		String input;
		Double inputDouble;
		
		while(true) {
			try {
				System.out.print(text);
				input = scanner.nextLine();
				inputDouble = Double.parseDouble(input);
				
				break;
			} catch (NumberFormatException ex) {
				System.err.println("Insira um valor válido!");
			}
		}
		
		return inputDouble;
	}
	
	public Long inputLong(String text) {
		String input;
		Long inputLong;
		
		while(true) {
			try {
				System.out.print(text);
				input = scanner.nextLine();
				inputLong = Long.parseLong(input);
				
				break;
			} catch (NumberFormatException ex) {
				System.err.println("Insira um valor válido!");
			}
		}
		
		return inputLong;
	}
	
	public LocalDate inputDate(String text) {
		String input;
		LocalDate inputDate;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		while(true) {
			try {
				System.out.print(text);
				input = scanner.nextLine();
				inputDate = LocalDate.parse(input, formatter);
				
				break;
			} catch (DateTimeParseException excep) {
				System.err.println("Insira uma data válida!");
			}
		}
		
		return inputDate;
	}
	
	public EnumSex inputSex() {
		EnumSex sex;
		String input;
		
		while(true) {
			try {
				System.out.print("Selecione o sexo:\n"
						+ "\t [M] Masculino\n"
						+ "\t [F] Feminino\n"
						+ "Entrada: ");
				input = scanner.nextLine();
				sex = EnumSex.valueOf(input);
				
				break;
			} catch (Exception e) {
				System.err.println("Insira uma valor válido!");
			}
		}
		
		return sex;
	}
	
	public EnumDepartment inputDepartment() {
		EnumDepartment department;
		Long input;
		
		while(true) {
			try {
				String str = "";
				
				str += "Selecione o departamento:\n";
				
				int count = 1;
				EnumDepartment[] deps = EnumDepartment.values();
				
				for(EnumDepartment dep : deps) {
					str += "\t [" + count + "] " + dep.getDescription() + "\n";
					count++;
				}
				
				System.out.println(str);
				
				input = inputLong("Entrada: ");
				department = deps[(int) input.longValue()];
				
				break;
			} catch (Exception e) {
				System.err.println("Insira uma valor válido!");
			}
		}
		
		return department;
	}

	public Scanner getScanner() {
		return scanner;
	}

	public void setScanner(Scanner scanner) {
		this.scanner = scanner;
	}
}
