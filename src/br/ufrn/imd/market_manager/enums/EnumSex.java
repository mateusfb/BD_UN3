package br.ufrn.imd.market_manager.enums;

public enum EnumSex {
	
	M("Masculino"), F("Feminino");
	
	private String description;
	
	private EnumSex(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
