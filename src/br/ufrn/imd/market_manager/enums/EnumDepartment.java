package br.ufrn.imd.market_manager.enums;

public enum EnumDepartment {
	
	ACOUGUE("Açougue"), FRIOS_E_LATICINIOS("Frios e laticínios"), BEBIDAS("bebidas"), HORTIFRUTI("Hortiftuti"), MERCEARIA("Mercearia"), PADARIA("Padaria"), PERFUMARIA("Perfumaria");
	
	private String description;
	
	private EnumDepartment(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
