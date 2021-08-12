package dun.dunnidoo2.model;

import java.util.ArrayList;
import java.util.List;

public class Superieur extends USER
{
	//Associations
	private Superieur superieur; 
	private List<USER> subordonnes = new ArrayList<USER>();
	private List<Alerte> extendableAlertes = new ArrayList<Alerte>();
	
	//Constructor
	public Superieur(String userId, String userName, String password, String email, String firstName, String lastName,
			USER_PROFILE userProfile) {
		
		this(userId, userName, password, email, firstName, lastName, userProfile,
				null ,new ArrayList<USER>());
	}
	
	public Superieur(String userId, String userName, String password, String email, String firstName, String lastName,
			USER_PROFILE userProfile, Superieur superieur ,  List<USER> subordonnes) 
	{
		super(userId, userName, password, email, firstName, lastName, userProfile);
		this.subordonnes = subordonnes;
		this.superieur = superieur;
		for (USER user : subordonnes) 
		{
			if(user instanceof Superieur) ((Superieur) user).setSuperieur(this);
			if(user instanceof Sam) ((Sam) user).setSuperieur(this);
		}
		if(superieur != null)superieur.addSubordonne(this);
	}
	//Getters & Setters
	public List<USER> getSubordonnes() {return subordonnes;}
	public void setSubordonnes(List<USER> subordonnes) {this.subordonnes = subordonnes;}
	public Superieur getSuperieur() {return superieur;}
	public void setSuperieur(Superieur superieur) {this.superieur = superieur;}
	public List<Alerte> getExtendableAlertes() {return extendableAlertes;}
	public void setExtendableAlertes(List<Alerte> extendableAlertes) {this.extendableAlertes = extendableAlertes;}

	//Instance Methods
	public void validateAlertExtension(Alerte a)
	{
		a.nextLevel();
		PLANIFICATOR.applyAlertExtension(a);
	}
	public void rejectAlertExtension( Alerte a)
	{
		PLANIFICATOR.closeAlert(a,MOTIF.REJET_PROLONGATION);
		
	}
	
	//Association Methods
	public boolean addSubordonne(USER subordonne){return this.getSubordonnes().add(subordonne);}
	public boolean removeSubordonne(USER subordonne){return this.getSubordonnes().remove(subordonne);}
	public boolean addExtendableAlerte(Alerte alerte) {return this.getExtendableAlertes().add(alerte);}
	public boolean removeExtendableAlerte(Alerte alerte) {return this.getExtendableAlertes().remove(alerte);}
	
	//Overridden Methods
	@Override
	public String toString() {
		return "Superieur - "+super.toString()+" \n[subordonnes=" + subordonnes + "]";
	}
}
