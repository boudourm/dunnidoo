package dun.dunnidoo2.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;


public class Alerte 
{
	//Attributes
	private String status ;
	private LocalDate dateDeclenchement ;
	private LocalDate dateCloture;

	//Associations
	private Customer customer ;
	private MOTIF motifDeclenchement ;
	private MOTIF motifCloture ;	
	private NIVEAU niveau ;
	private USER closingDoer;
	//Constructors

	public Alerte(Customer customer , MOTIF motif) 
	{
		this("ACTIVE", LocalDate.now(), 
		NIVEAU.
		NIVEAU1.
		getPeriodeGraceBySegment().
		get(customer.getSegment()).
		dateFromNow(),
		customer,
		motif,
		null,
		NIVEAU.NIVEAU1);
	}	

	public Alerte(String status, LocalDate dateDeclenchement, LocalDate dateCloture, Customer customer,
			MOTIF motifDeclenchement, MOTIF motifCloture, NIVEAU niveau) {
		super();
		this.status = status;
		this.dateDeclenchement = dateDeclenchement;
		this.dateCloture = dateCloture;
		this.customer = customer;
		this.motifDeclenchement = motifDeclenchement;
		this.motifCloture = motifCloture;
		this.niveau = niveau;
		//Associations
		customer.setAlerte(this);
		
	}
	
	//Getters & Setters
	public String getStatus() {return status;}
	public void setStatus(String status) {this.status = status;}
	public LocalDate getDateDeclenchement() {return dateDeclenchement;}
	public void setDateDeclenchement(LocalDate dateDeclenchement) {this.dateDeclenchement = dateDeclenchement;}
	public LocalDate getDateCloture() {return dateCloture;}
	public void setDateCloture(LocalDate dateCloture) {this.dateCloture = dateCloture;}
	public Customer getCustomer() {return customer;}
	public void setCustomer(Customer customer) {//this.customer.setAlerte(null);
												this.customer = customer;
												//customer.setAlerte(this);
												}
	public MOTIF getMotifDeclenchement() {return motifDeclenchement;}
	public void setMotifDeclenchement(MOTIF motifDeclenchement) {this.motifDeclenchement = motifDeclenchement;}	
	public MOTIF getMotifCloture() {return motifCloture;}
	public void setMotifCloture(MOTIF motifCloture) {this.motifCloture = motifCloture;}	
	public NIVEAU getNiveau() {return niveau;}
	public void setNiveau(NIVEAU niveau) {this.niveau = niveau;}
	public USER getClosingDoer() {return closingDoer;}
	public void setClosingDoer(USER closingDoer) {this.closingDoer = closingDoer;}	
	
	
	//Instance Methods



	public static Alerte newAlerte(Customer customer , MOTIF motifDeclenchement)
	{
		customer.inException();
		return new Alerte(customer , motifDeclenchement);
	}
	
	public Mail buildAlertMail()
	{
		return new Mail("ALERTE",
						"Période d’exception "+this.getNiveau().getLibelle()+" :\n"
					  + " "+this.getPeriode()+" jours \nest émise "
					  + "par Mr/Mme "+this.responsable().getFirstName()+" "
					  + " "+this.responsable().getLastName()+" "
					  + "\ntitre du poste : "+this.responsable().getUserProfile().getLibelle()+" "
					  + "\npour le compte N° "+this.getCustomer().getCustCode()+" "
					  + "\nde l’entreprise (Raison Sociale) \nSegment client : "+this.getCustomer().getSegment().getLibelle()+" "
					  + "\npour le motif : "+this.getMotifDeclenchement().getMotif()+" "
					  + "."
					  + " \nMontant des dus antérieures : "+this.getCustomer().getOpenAmount()+" DZD, "
					  + "\nMontant des du facture actuelle : "+this.getCustomer().getInvoiceAmout()+" DZD." ,
					  this.responsable() , this.IntervenantsList());
	}
	
	public void notifyTriggering()
	{
		USER r = this.responsable();
		new Notification("ALERTE déclechée : pour le compte "
						 +this.getCustomer().getCustCode()+
						 " par "+r.getFirstName()+
						 " "+r.getLastName()+".", IntervenantsList()).notifying();
	}
	
	public void notifyInterruption()
	{
		USER r = this.getClosingDoer();
		new Notification("ALERTE interrompue : pour le compte "
						 +this.getCustomer().getCustCode()+
						 " par "+r.getFirstName()+
						 " "+r.getLastName()+".", IntervenantsList()).notifying();
	}
	
	public void interrupt()
	{
		this.setStatus("INTERRUPTED");
		this.getCustomer().inDunning();
	
		this.setMotifCloture(MOTIF.INTERRUPTION);
		this.setDateCloture(LocalDate.now());
	}
	
	public void cloture(MOTIF motifCloture)
	{
		if(motifCloture.equals(MOTIF.REJET_PROLONGATION))
			setClosingDoer(nextLevelResponsable());
		else
			setClosingDoer(PLANIFICATOR.getSystem());
		setStatus("CLOSED");
		setDateCloture(LocalDate.now());
		setMotifCloture(motifCloture);
	}
	
	public  List<Planification> exceptionWorkFlow()
	{
		ArrayList<Planification> resultat = new ArrayList<Planification>();
		for (Planification planification : this.getCustomer().getPlanififcations()) 
		{			//BO BI DESC SMS4 SMS7 SMS8
			if(planification.getAction().equals(ACTION.BARIN)   ||
			   planification.getAction().equals(ACTION.BAROUT)  ||
			   planification.getAction().equals(ACTION.SMS2) 	||
			   planification.getAction().equals(ACTION.SMS3) 	||
			   planification.getAction().equals(ACTION.SMS4) 	||
			   planification.getAction().equals(ACTION.SMS6) 	||
			   planification.getAction().equals(ACTION.SMS7) 	||
			   planification.getAction().equals(ACTION.SMS8) 	||
			   planification.getAction().equals(ACTION.IVR1) 	||
			   planification.getAction().equals(ACTION.IVR2) 	||
			   planification.getAction().equals(ACTION.MAIL_DESACTIVATION) 	||
			   planification.getAction().equals(ACTION.DESACTIVATION))
			   
					resultat.add(planification);
		}
		return resultat;
	}
	
	public Long getPeriode()
	{
		return this.getCustomer().getSegment().getPeriodeGraceByNiveau().get(this.getNiveau()).getPeriode();
	}
	
	public List<USER> IntervenantsList()
	{
		ArrayList<USER> resultat = new ArrayList<USER>();
		ArrayList<USER> list = new ArrayList<USER>();
		list.add(this.getCustomer().getSam());
		Superieur temp = this.getCustomer().getSam().getSuperieur();
		while(temp != null)
		{
			list.add(temp);
			temp = temp.getSuperieur();
		}
		for (USER_PROFILE up : this.getNiveau().getIntervenants()) 
		{
			for (USER user : list) 
			{
				if(user.getUserProfile().equals(up))
				{
					resultat.add(user);
					break;
				}
			}
		}
		resultat.remove(this.responsable());
		resultat.addAll(PLANIFICATOR.getCCFacUsers());
		return resultat;
	}
	
	public USER responsable()
	{
		ArrayList<USER> list = new ArrayList<USER>();
		list.add(this.getCustomer().getSam());
		Superieur temp = this.getCustomer().getSam().getSuperieur();
		while(temp != null)
		{
			list.add(temp);
			temp = temp.getSuperieur();
		}
		for (USER user : list) 
		{
			if(user.getUserProfile().equals(this.getNiveau().getResponsable()))
			{
				return user;
			}
		}
		return null;
	}
	
	public Superieur nextLevelResponsable()
	{
		if(!this.getNiveau().equals(NIVEAU.NIVEAU6))
		{
			ArrayList<USER> list = new ArrayList<USER>();
			list.add(this.getCustomer().getSam());
			Superieur temp = this.getCustomer().getSam().getSuperieur();
			while(temp != null)
			{
				list.add(temp);
				temp = temp.getSuperieur();
			}
			for (USER user : list) 
			{
				if(user.getUserProfile().equals(this.getNiveau().getResponsable()))
				{
					return (Superieur)user;
				}
			}
			return null;
		
		}
		else
		{
			return null;
		}
		}
	public void nextLevel()
	{
		this.setNiveau(this.getNiveau().next());
		this.getDateCloture().plus(this.getPeriode() , ChronoUnit.DAYS);
	}
	//Association Methods

	//Overridden Methods
	@Override
	public String toString() {
		return "Alerte [status=" + status + ",\ndateDeclenchement=" + dateDeclenchement + ",\ndateCloture=" + dateCloture
				+ ",\ncustomer=" + customer + ",\nmotifDeclenchement=" + motifDeclenchement + ",\nmotifCloture="
				+ motifCloture + ",\nniveau=" + niveau + "]";
	}



	//equals


}
