package dun.dunnidoo2.model;

public enum MOTIF
{
	//Objects
	PROBLEME_FACTURATION("Probl�me li�e � la facturation"),
	SIGNATAIRE_ABSENT("Les signataire est absent"),
	ORDRE_VIREMENT("Paiement par ordre de virement"),
	ECHELONNEMENT("Paiement par �chelonnement"),
	RESILIATION("R�siliation tardive"),
	INTERRUPTION("L'alerte est stop�e par CC Facturation"),
	PAYEMENT("Paiement de la totalit� des dus"),
	REJET_PROLONGATION("Rejet de l'alerte"),
	NORESPONSE("Aucune approbation n'est accord�e dans les d�lais"),
	NIVEAU6_ATTEINT("Alerte arriv�e au bout du niveua 6");
	
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
