package br.ufrn.imd.market_manager.enums;

public enum EnumDepartment {
	
	ACOUGUE("A�ougue"), FRIOS_E_LATICINIOS("Frios e latic�nios"), BEBIDAS("bebidas"), HORTIFRUTI("Hortiftuti"), MERCEARIA("Mercearia"), PADARIA("Padaria"), PERFUMARIA("Perfumaria");
	
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
