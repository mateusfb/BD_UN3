package br.ufrn.imd.market_manager.entity;

import java.time.LocalDate;
import java.time.Period;

import br.ufrn.imd.market_manager.enums.EnumSex;

public class Client {
	
	private Long id;
	private String name;
	private String cpf;
	private LocalDate birthDate;
	private EnumSex sex;
	private String phone;
	private Address address;
	
	public Client() {}
	
	public Client(Long id, String name, LocalDate birthDate, EnumSex sex, String phone, Address address) {
		super();
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
		this.sex = sex;
		this.phone = phone;
		this.address = address;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}
	
	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}
	
	public EnumSex getSex() {
		return sex;
	}
	
	public void setSex(EnumSex sex) {
		this.sex = sex;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public Address getAddress() {
		return address;
	}
	
	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "'" + name + "', '" + cpf + "', '" + birthDate + "', '" + sex
				+ "', '" + phone + "', " + address.getId().toString();
	}
	
	public void print() {
		String str = "";
		
		if(name != null) str += "\t" + name + "\n";
		if(cpf != null) str += "\t" + cpf + "\n";
		if(birthDate != null) str += "\t" + Period.between(birthDate, LocalDate.now()).getYears() + " anos \n";
		if(sex != null) str += "\t" + sex.getDescription() + "\n";
		if(phone != null) str += "\t" + phone + "\n";
	
		System.out.println(str);
	}
}
