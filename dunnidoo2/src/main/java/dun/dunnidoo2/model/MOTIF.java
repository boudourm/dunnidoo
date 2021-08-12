package dun.dunnidoo2.model;

public enum MOTIF
{
	//Objects
	PROBLEME_FACTURATION("Problème liée à la facturation"),
	SIGNATAIRE_ABSENT("Les signataire est absent"),
	ORDRE_VIREMENT("Paiement par ordre de virement"),
	ECHELONNEMENT("Paiement par échelonnement"),
	RESILIATION("Résiliation tardive"),
	INTERRUPTION("L'alerte est stopée par CC Facturation"),
	PAYEMENT("Paiement de la totalité des dus"),
	REJET_PROLONGATION("Rejet de l'alerte"),
	NORESPONSE("Aucune approbation n'est accordée dans les délais"),
	NIVEAU6_ATTEINT("Alerte arrivée au bout du niveua 6");
	
	//Attributes
	private String motif;
	
	//Constructor
	private MOTIF(String motif)
	{
		this.motif = motif;
	}
	
	//Getters & Setters
	public String getMotif() {return motif;}
	public void setMotif(String motif) {this.motif = motif;}
	

}
