package dun.dunnidoo2.model;

public enum USER_PROFILE 
{
	//Objects
	SAM("SAM"),
	SUP("Superviseur"),
	CDS("Chef de service"),
	DA("Directeur"),
	DIRECTEUR("Directeur"),
	CCO("Chief Creative Officer"),
	CCFAC("CC Facturaction"),
	ADMIN("Admin");
	
	//Attributes
	private String libelle;

	//Constructor
	private USER_PROFILE(String libelle) {
		this.libelle = libelle;
	}
	
	//Getters & Setters
	public String getLibelle() {return libelle;}
	public void setLibelle(String libelle) {this.libelle = libelle;}
	
	//Instance Methods


}
