package br.ufrn.imd.market_manager.controller;

import java.time.LocalDate;

import br.ufrn.imd.market_manager.entity.Address;
import br.ufrn.imd.market_manager.entity.Client;
import br.ufrn.imd.market_manager.enums.EnumSex;
import br.ufrn.imd.market_manager.repository.ClientRepository;
import br.ufrn.imd.market_manager.util.InputScannerUtil;

public class ClientController {
	
	InputScannerUtil inputScanner;
	
	ClientRepository repository = new ClientRepository();
	
	public void newClient() {
		Client client= new Client();
		
		String name = inputScanner.inputString("Nome: ");
		String cpf = inputScanner.inputString("CPF: ");
		LocalDate birth_date = inputScanner.inputDate("Data de Nascimento (dd/mm/aaaa): ");
		EnumSex sex = inputScanner.inputSex();
		String phone = inputScanner.inputString("Telefone: ");
		
		System.out.println("- ENDEREÇO -");
		
		Address address = new Address();
		
		String place = inputScanner.inputString("Logradouro: ");
		String number = inputScanner.inputString("Número: ");
		String zipcode = inputScanner.inputString("CEP: ");
		String city = inputScanner.inputString("Cidade: ");
		String state = inputScanner.inputString("Estado: ");
		
		address.setPlace(place);
		address.setNumber(number);
		address.setZipcode(zipcode);
		address.setCity(city);
		address.setState(state);
		
		client.setName(name);
		client.setCpf(cpf);
		client.setBirthDate(birth_date);
		client.setSex(sex);
		client.setPhone(phone);
		client.setAddress(address);
		
		save(client);
	}
	
	public void editClient() {
		String searchedCpf = inputScanner.inputString("CPF: ");
	
		Client client = repository.findByCpf(searchedCpf);
		
		String name = inputScanner.inputString("Nome: ");
		String cpf = inputScanner.inputString("CPF: ");
		LocalDate birth_date = inputScanner.inputDate("Data de Nascimento (dd/mm/aaaa): ");
		EnumSex sex = inputScanner.inputSex();
		String phone = inputScanner.inputString("Telefone: ");
		
		System.out.println("- ENDEREÇO -");
		
		Address address = new Address();
		
		String place = inputScanner.inputString("Logradouro: ");
		String number = inputScanner.inputString("Número: ");
		String zipcode = inputScanner.inputString("CEP: ");
		String city = inputScanner.inputString("Cidade: ");
		String state = inputScanner.inputString("Estado: ");
		
		address.setPlace(place);
		address.setNumber(number);
		address.setZipcode(zipcode);
		address.setCity(city);
		address.setState(state);
		
		client.setName(name);
		client.setCpf(cpf);
		client.setBirthDate(birth_date);
		client.setSex(sex);
		client.setPhone(phone);
		client.setAddress(address);
		
		save(client);
	}
	
	public void save(Client client) {
		Client clientDuplicate = repository.findByCpf(client.getCpf());
		
		if(clientDuplicate != null && !clientDuplicate.getId().equals(client.getId())) {
			System.err.println("Já existe um cliente cadastrado com este cpf!");
			return;
		}
		
		repository.save(client);
	}
	
	public void searchClient() {
		String cpf = inputScanner.inputString("CPF: ");
		
		Client client = repository.findByCpf(cpf);
		
		if(client != null) {
			client.print();
		} else {
			System.out.println("Nenhum resultado encontrado!");
		}
	}
	
	public void removeClient() {
		String cpf = inputScanner.inputString("CPF: ");
		
		repository.remove(cpf);
	}

	public InputScannerUtil getInputScanner() {
		return inputScanner;
	}

	public void setInputScanner(InputScannerUtil inputScanner) {
		this.inputScanner = inputScanner;
	}

	public ClientRepository getRepository() {
		return repository;
	}

	public void setRepository(ClientRepository repository) {
		this.repository = repository;
	}
}
