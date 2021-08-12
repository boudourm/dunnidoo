package dun.dunnidoo2.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.stereotype.Repository;

public enum SEGMENT
{
	//Objects
	SOHO("SOHO"),
	SME("SME"),
	OS("OS"),
	LARGE("LARGE"),
	KEY("KEY"),
	SPECIAL_KEY("SPECIAL KEY"),
	VIP("VIP"),
	DISTRIBUTEUR("DISTRIBUTEUR");
	
	
	//Attributes
	private String libelle;
	
	//Associations
	private List<Relance> relances = new ArrayList<Relance>();
	private List<Customer> customers = new ArrayList<Customer>();
	private Map<NIVEAU, PeriodeGrace> periodeGraceByNiveau = new  TreeMap<NIVEAU , PeriodeGrace>();

	private  Long BAROUTSafterException  ;
	private  Long BARINSafterException   ;
	private  Long DESACTIVATIONSafterException ;

	//Constructor
	private SEGMENT(String libelle)
	{
		this.libelle = libelle;
	} 	
	//Getters & Setters
	public String getLibelle() {return libelle;}
	public void setLibelle(String libelle) {this.libelle = libelle;}
	public List<Relance> getRelances() {return relances;}
	public void setRelances(List<Relance> relances) {this.relances = relances;}
	public List<Customer> getCustomers() {return customers;}
	public void setCustomers(List<Customer> customers) {this.customers = customers;}
	public Map<NIVEAU, PeriodeGrace> getPeriodeGraceByNiveau() {return periodeGraceByNiveau;}
	public void setPeriodeGraceByNiveau(Map<NIVEAU, PeriodeGrace> periodeGraceByNiveau) {this.periodeGraceByNiveau = periodeGraceByNiveau;}
	public Long getBAROUTSafterException() {return BAROUTSafterException;}
	public void setBAROUTSafterException(Long bAROUTSafterException) {	BAROUTSafterException = bAROUTSafterException;}
	public Long getBARINSafterException() {	return BARINSafterException;}
	public void setBARINSafterException(Long bARINSafterException) {BARINSafterException = bARINSafterException;}
	public Long getDESACTIVATIONSafterException() {	return DESACTIVATIONSafterException;}
	public void setDESACTIVATIONSafterException(Long dESACTIVATIONSafterException) {DESACTIVATIONSafterException = dESACTIVATIONSafterException;}
	
	//Instance Methods
	public List<Planification> instantPlanifications(Customer client)
	{
		List<Planification> resultat = new ArrayList<Planification>();
		for (Relance relance : this.getRelances()) 
		{
			resultat.add(relance.instantPlanification(client));
		}
		return resultat;
	}
	
	public static List<SEGMENT> list()
	{
		List<SEGMENT> result = new ArrayList<SEGMENT>();
		for (SEGMENT segment : SEGMENT.values())
		{
			result.add(segment);
		}
		return result;
	}
	
	public static SEGMENT get(String libelle)
	{
		return SEGMENT.valueOf(libelle);
	}
	//Association Methods
	
	public boolean addRelance (Relance relance){return this.getRelances().add(relance);}
	public boolean addCustomer (Customer customer){System.out.println(customer.getCclName()+ " isFlat = "+ customer.isFlat()); if(!customer.isFlat()) return this.getCustomers().add(customer); return false; }
	public boolean removeRelance (Relance relance){	return this.getRelances().remove(relance);}
	public boolean removeCustomer (Customer customer){return this.getCustomers().remove(customer);}


	static
	{

		
		
		//DUNNING de Chaque SEGMENT
		//SOHO
		SEGMENT.SOHO.addRelance(new Relance((long)3, ACTION.SMS1));
		SEGMENT.SOHO.addRelance(new Relance((long)15, ACTION.SMS2));
		SEGMENT.SOHO.addRelance(new Relance((long)25, ACTION.SMS3));
		SEGMENT.SOHO.addRelance(new Relance((long)30, ACTION.SMS4));
		SEGMENT.SOHO.addRelance(new Relance((long)30, ACTION.BAROUT));
		SEGMENT.SOHO.addRelance(new Relance((long)30, ACTION.IVR1));
		SEGMENT.SOHO.addRelance(new Relance((long)33, ACTION.SMS5));
		SEGMENT.SOHO.addRelance(new Relance((long)35, ACTION.SMS6));
		SEGMENT.SOHO.addRelance(new Relance((long)40, ACTION.SMS7));
		SEGMENT.SOHO.addRelance(new Relance((long)40, ACTION.BARIN));
		SEGMENT.SOHO.addRelance(new Relance((long)40, ACTION.IVR2));
		SEGMENT.SOHO.addRelance(new Relance((long)51, ACTION.MAIL_RELANCE));
		SEGMENT.SOHO.addRelance(new Relance((long)61, ACTION.SMS8));		
		SEGMENT.SOHO.addRelance(new Relance((long)90, ACTION.DESACTIVATION));
		SEGMENT.SOHO.addRelance(new Relance((long)90, ACTION.MAIL_DESACTIVATION));
				
		//SME
		SEGMENT.SME.addRelance(new Relance((long)3, ACTION.SMS1));
		SEGMENT.SME.addRelance(new Relance((long)15, ACTION.SMS2));
		SEGMENT.SME.addRelance(new Relance((long)25, ACTION.SMS3));
		SEGMENT.SME.addRelance(new Relance((long)25, ACTION.OUTGOING_CALL));
		SEGMENT.SME.addRelance(new Relance((long)30, ACTION.SMS4));
		SEGMENT.SME.addRelance(new Relance((long)30, ACTION.BAROUT));
		SEGMENT.SME.addRelance(new Relance((long)30, ACTION.IVR1));
		SEGMENT.SME.addRelance(new Relance((long)33, ACTION.SMS5));
		SEGMENT.SME.addRelance(new Relance((long)35, ACTION.SMS6));
		SEGMENT.SME.addRelance(new Relance((long)40, ACTION.SMS7));
		SEGMENT.SME.addRelance(new Relance((long)40, ACTION.IVR2));
		SEGMENT.SME.addRelance(new Relance((long)40, ACTION.BARIN));
		SEGMENT.SME.addRelance(new Relance((long)51, ACTION.MAIL_RELANCE));
		SEGMENT.SME.addRelance(new Relance((long)61, ACTION.SMS8));
		SEGMENT.SME.addRelance(new Relance((long)90, ACTION.DESACTIVATION));
		SEGMENT.SME.addRelance(new Relance((long)90, ACTION.MAIL_DESACTIVATION));

	
		
		//LARGE
		SEGMENT.LARGE.addRelance(new Relance((long)3, ACTION.SMS1));//fact
		SEGMENT.LARGE.addRelance(new Relance((long)3, ACTION.OUTGOING_CALL));
		SEGMENT.LARGE.addRelance(new Relance((long)25, ACTION.SMS2));//warning
		SEGMENT.LARGE.addRelance(new Relance((long)25, ACTION.OUTGOING_CALL));
		SEGMENT.LARGE.addRelance(new Relance((long)30, ACTION.SMS4));//susp
		SEGMENT.LARGE.addRelance(new Relance((long)30, ACTION.BAROUT));
		SEGMENT.LARGE.addRelance(new Relance((long)30, ACTION.IVR1));
		SEGMENT.LARGE.addRelance(new Relance((long)35, ACTION.SMS5));//fact2
		SEGMENT.LARGE.addRelance(new Relance((long)35, ACTION.SMS6));//fact2
		SEGMENT.LARGE.addRelance(new Relance((long)35, ACTION.OUTGOING_CALL));
		SEGMENT.LARGE.addRelance(new Relance((long)40, ACTION.SMS7));
		SEGMENT.LARGE.addRelance(new Relance((long)40, ACTION.IVR2));
		SEGMENT.LARGE.addRelance(new Relance((long)40, ACTION.BARIN));
		SEGMENT.LARGE.addRelance(new Relance((long)51, ACTION.MAIL_RELANCE));
		SEGMENT.LARGE.addRelance(new Relance((long)120, ACTION.DESACTIVATION));
		SEGMENT.LARGE.addRelance(new Relance((long)120, ACTION.MAIL_DESACTIVATION));	

		//OS
		SEGMENT.OS.addRelance(new Relance((long)3, ACTION.SMS1));//fact
		SEGMENT.OS.addRelance(new Relance((long)3, ACTION.OUTGOING_CALL));
		SEGMENT.OS.addRelance(new Relance((long)25, ACTION.SMS2));//warning
		SEGMENT.OS.addRelance(new Relance((long)25, ACTION.OUTGOING_CALL));
		SEGMENT.OS.addRelance(new Relance((long)30, ACTION.SMS4));//susp
		SEGMENT.OS.addRelance(new Relance((long)30, ACTION.BAROUT));
		SEGMENT.OS.addRelance(new Relance((long)30, ACTION.IVR1));
		SEGMENT.OS.addRelance(new Relance((long)35, ACTION.SMS5));//fact2
		SEGMENT.OS.addRelance(new Relance((long)35, ACTION.SMS6));//fact2
		SEGMENT.OS.addRelance(new Relance((long)35, ACTION.OUTGOING_CALL));
		SEGMENT.OS.addRelance(new Relance((long)40, ACTION.SMS7));
		SEGMENT.OS.addRelance(new Relance((long)40, ACTION.IVR2));
		SEGMENT.OS.addRelance(new Relance((long)40, ACTION.BARIN));
		SEGMENT.OS.addRelance(new Relance((long)51, ACTION.MAIL_RELANCE));
		SEGMENT.OS.addRelance(new Relance((long)120, ACTION.DESACTIVATION));
		SEGMENT.OS.addRelance(new Relance((long)120, ACTION.MAIL_DESACTIVATION));	

		
		//KEY
		SEGMENT.KEY.addRelance(new Relance((long)3, ACTION.SMS1));//fact
		SEGMENT.KEY.addRelance(new Relance((long)3, ACTION.OUTGOING_CALL));
		SEGMENT.KEY.addRelance(new Relance((long)25, ACTION.SMS2));//warning
		SEGMENT.KEY.addRelance(new Relance((long)25, ACTION.OUTGOING_CALL));
		SEGMENT.KEY.addRelance(new Relance((long)30, ACTION.SMS4));//susp
		SEGMENT.KEY.addRelance(new Relance((long)30, ACTION.BAROUT));
		SEGMENT.KEY.addRelance(new Relance((long)30, ACTION.IVR1));
		SEGMENT.KEY.addRelance(new Relance((long)35, ACTION.SMS5));//fact2
		SEGMENT.KEY.addRelance(new Relance((long)35, ACTION.SMS6));//fact2
		SEGMENT.KEY.addRelance(new Relance((long)35, ACTION.OUTGOING_CALL));
		SEGMENT.KEY.addRelance(new Relance((long)40, ACTION.SMS7));
		SEGMENT.KEY.addRelance(new Relance((long)40, ACTION.IVR2));
		SEGMENT.KEY.addRelance(new Relance((long)40, ACTION.BARIN));
		SEGMENT.KEY.addRelance(new Relance((long)51, ACTION.MAIL_RELANCE));
		SEGMENT.KEY.addRelance(new Relance((long)180, ACTION.DESACTIVATION));
		SEGMENT.KEY.addRelance(new Relance((long)180, ACTION.MAIL_DESACTIVATION));	

		//SMS1 = info fact1 
		//SMS2 = warning* dates BAROUT
		//SMS3 = warning** BAROUT
		//SMS4 = BAROUT passé
		//SMS5 = info fact1 + fact2
		//SMS6 = warning BARIN
		//SMS7 = BARIN passé
		//SMS8 = DESACTIVATION passé			
		
		//SPECIAL_KEY
		SEGMENT.SPECIAL_KEY.addRelance(new Relance((long)3, ACTION.SMS1));//fact
		SEGMENT.SPECIAL_KEY.addRelance(new Relance((long)3, ACTION.OUTGOING_CALL));
		SEGMENT.SPECIAL_KEY.addRelance(new Relance((long)25, ACTION.SMS2));//warning
		SEGMENT.SPECIAL_KEY.addRelance(new Relance((long)25, ACTION.OUTGOING_CALL));
		SEGMENT.SPECIAL_KEY.addRelance(new Relance((long)30, ACTION.SMS4));//susp
		SEGMENT.SPECIAL_KEY.addRelance(new Relance((long)30, ACTION.BAROUT));
		SEGMENT.SPECIAL_KEY.addRelance(new Relance((long)30, ACTION.IVR1));
		SEGMENT.SPECIAL_KEY.addRelance(new Relance((long)35, ACTION.SMS5));//fact2
		SEGMENT.SPECIAL_KEY.addRelance(new Relance((long)35, ACTION.SMS6));//fact2
		SEGMENT.SPECIAL_KEY.addRelance(new Relance((long)35, ACTION.OUTGOING_CALL));
		SEGMENT.SPECIAL_KEY.addRelance(new Relance((long)40, ACTION.SMS7));
		SEGMENT.SPECIAL_KEY.addRelance(new Relance((long)40, ACTION.IVR2));
		SEGMENT.SPECIAL_KEY.addRelance(new Relance((long)40, ACTION.BARIN));
		SEGMENT.SPECIAL_KEY.addRelance(new Relance((long)51, ACTION.MAIL_RELANCE));
		SEGMENT.SPECIAL_KEY.addRelance(new Relance((long)180, ACTION.DESACTIVATION));
		SEGMENT.SPECIAL_KEY.addRelance(new Relance((long)180, ACTION.MAIL_DESACTIVATION));	
	
		//VIP
		SEGMENT.VIP.addRelance(new Relance((long)3, ACTION.SMS1));//fact
		SEGMENT.VIP.addRelance(new Relance((long)3, ACTION.OUTGOING_CALL));
		SEGMENT.VIP.addRelance(new Relance((long)25, ACTION.SMS2));//warning
		SEGMENT.VIP.addRelance(new Relance((long)25, ACTION.OUTGOING_CALL));
		SEGMENT.VIP.addRelance(new Relance((long)30, ACTION.SMS4));//susp
		SEGMENT.VIP.addRelance(new Relance((long)30, ACTION.BAROUT));
		SEGMENT.VIP.addRelance(new Relance((long)30, ACTION.IVR1));
		SEGMENT.VIP.addRelance(new Relance((long)35, ACTION.SMS5));//fact2
		SEGMENT.VIP.addRelance(new Relance((long)35, ACTION.SMS6));//fact2
		SEGMENT.VIP.addRelance(new Relance((long)35, ACTION.OUTGOING_CALL));
		SEGMENT.VIP.addRelance(new Relance((long)40, ACTION.SMS7));
		SEGMENT.VIP.addRelance(new Relance((long)40, ACTION.IVR2));
		SEGMENT.VIP.addRelance(new Relance((long)40, ACTION.BARIN));
		SEGMENT.VIP.addRelance(new Relance((long)51, ACTION.MAIL_RELANCE));
		SEGMENT.VIP.addRelance(new Relance((long)180, ACTION.DESACTIVATION));
		SEGMENT.VIP.addRelance(new Relance((long)180, ACTION.MAIL_DESACTIVATION));	
	
	
		//DISTRIBUTEUR
		SEGMENT.DISTRIBUTEUR.addRelance(new Relance((long)3, ACTION.SMS1));//fact
		SEGMENT.DISTRIBUTEUR.addRelance(new Relance((long)3, ACTION.OUTGOING_CALL));
		SEGMENT.DISTRIBUTEUR.addRelance(new Relance((long)25, ACTION.SMS2));//warning
		SEGMENT.DISTRIBUTEUR.addRelance(new Relance((long)25, ACTION.OUTGOING_CALL));
		SEGMENT.DISTRIBUTEUR.addRelance(new Relance((long)30, ACTION.SMS4));//susp
		SEGMENT.DISTRIBUTEUR.addRelance(new Relance((long)30, ACTION.BAROUT));
		SEGMENT.DISTRIBUTEUR.addRelance(new Relance((long)30, ACTION.IVR1));
		SEGMENT.DISTRIBUTEUR.addRelance(new Relance((long)35, ACTION.SMS5));//fact2
		SEGMENT.DISTRIBUTEUR.addRelance(new Relance((long)35, ACTION.SMS6));//fact2
		SEGMENT.DISTRIBUTEUR.addRelance(new Relance((long)35, ACTION.OUTGOING_CALL));
		SEGMENT.DISTRIBUTEUR.addRelance(new Relance((long)40, ACTION.SMS7));
		SEGMENT.DISTRIBUTEUR.addRelance(new Relance((long)40, ACTION.IVR2));
		SEGMENT.DISTRIBUTEUR.addRelance(new Relance((long)40, ACTION.BARIN));
		SEGMENT.DISTRIBUTEUR.addRelance(new Relance((long)51, ACTION.MAIL_RELANCE));
		SEGMENT.DISTRIBUTEUR.addRelance(new Relance((long)180, ACTION.DESACTIVATION));
		SEGMENT.DISTRIBUTEUR.addRelance(new Relance((long)180, ACTION.MAIL_DESACTIVATION));	

		//Exceptions
		Map<NIVEAU,PeriodeGrace> periodsByLevel = new TreeMap<NIVEAU,PeriodeGrace>();

		PeriodeGrace p0= 	new PeriodeGrace((long) 0);
		PeriodeGrace p11= 	new PeriodeGrace((long) 11);
		PeriodeGrace p15= 	new PeriodeGrace((long) 15);
		PeriodeGrace p12=  	new PeriodeGrace((long) 12);
		PeriodeGrace p9=   	new PeriodeGrace((long) 9);
		PeriodeGrace p7=   	new PeriodeGrace((long) 7);
		PeriodeGrace p5=  	new PeriodeGrace((long) 5);
		PeriodeGrace p2=   	new PeriodeGrace((long) 2);
		PeriodeGrace p20= 	new PeriodeGrace((long) 20);
		PeriodeGrace p23=  	new PeriodeGrace((long) 23);
		PeriodeGrace p13=  	new PeriodeGrace((long) 13);
		PeriodeGrace p18=  	new PeriodeGrace((long) 18);
		PeriodeGrace p180= 	new PeriodeGrace((long) 180);
		//SEGMENTS
		
		//SOHO
		periodsByLevel.put(NIVEAU.NIVEAU1, p0);
		periodsByLevel.put(NIVEAU.NIVEAU2, p0);
		periodsByLevel.put(NIVEAU.NIVEAU3, p0);
		periodsByLevel.put(NIVEAU.NIVEAU4, p0);
		periodsByLevel.put(NIVEAU.NIVEAU5, p0);
		periodsByLevel.put(NIVEAU.NIVEAU6, p0);
		SEGMENT.SOHO.setPeriodeGraceByNiveau(periodsByLevel);
		
		//SME
		periodsByLevel = new TreeMap<NIVEAU,PeriodeGrace>();
		periodsByLevel.put(NIVEAU.NIVEAU1, p11);
		periodsByLevel.put(NIVEAU.NIVEAU2, p15);
		periodsByLevel.put(NIVEAU.NIVEAU3, p12);
		periodsByLevel.put(NIVEAU.NIVEAU4, p9);
		periodsByLevel.put(NIVEAU.NIVEAU5, p7);
		periodsByLevel.put(NIVEAU.NIVEAU6, p0);
		SEGMENT.SME.setPeriodeGraceByNiveau(periodsByLevel);
		
		//LARGE
		periodsByLevel = new TreeMap<NIVEAU,PeriodeGrace>();
		periodsByLevel.put(NIVEAU.NIVEAU1, p11);
		periodsByLevel.put(NIVEAU.NIVEAU2, p15);
		periodsByLevel.put(NIVEAU.NIVEAU3, p12);
		periodsByLevel.put(NIVEAU.NIVEAU4, p9);
		periodsByLevel.put(NIVEAU.NIVEAU5, p7);
		periodsByLevel.put(NIVEAU.NIVEAU6, p0);
		SEGMENT.LARGE.setPeriodeGraceByNiveau(periodsByLevel);
		
		//OS
		periodsByLevel = new TreeMap<NIVEAU,PeriodeGrace>();
		periodsByLevel.put(NIVEAU.NIVEAU1, p11);
		periodsByLevel.put(NIVEAU.NIVEAU2, p15);
		periodsByLevel.put(NIVEAU.NIVEAU3, p12);
		periodsByLevel.put(NIVEAU.NIVEAU4, p9);
		periodsByLevel.put(NIVEAU.NIVEAU5, p7);
		periodsByLevel.put(NIVEAU.NIVEAU6, p0);
		SEGMENT.OS.setPeriodeGraceByNiveau(periodsByLevel);
		
		//KEY
		periodsByLevel = new TreeMap<NIVEAU,PeriodeGrace>();
		periodsByLevel.put(NIVEAU.NIVEAU1, p20);
		periodsByLevel.put(NIVEAU.NIVEAU2, p23);
		periodsByLevel.put(NIVEAU.NIVEAU3, p18);
		periodsByLevel.put(NIVEAU.NIVEAU4, p13);
		periodsByLevel.put(NIVEAU.NIVEAU5, p7);
		periodsByLevel.put(NIVEAU.NIVEAU6, p5);
		SEGMENT.KEY.setPeriodeGraceByNiveau(periodsByLevel);
		
		//SPECIAL_KEY
		periodsByLevel = new TreeMap<NIVEAU,PeriodeGrace>();
		periodsByLevel.put(NIVEAU.NIVEAU1, p20);
		periodsByLevel.put(NIVEAU.NIVEAU2, p23);
		periodsByLevel.put(NIVEAU.NIVEAU3, p18);
		periodsByLevel.put(NIVEAU.NIVEAU4, p13);
		periodsByLevel.put(NIVEAU.NIVEAU5, p7);
		periodsByLevel.put(NIVEAU.NIVEAU6, p5);
		SEGMENT.SPECIAL_KEY.setPeriodeGraceByNiveau(periodsByLevel);
		//VIP
		periodsByLevel = new TreeMap<NIVEAU,PeriodeGrace>();
		periodsByLevel.put(NIVEAU.NIVEAU1, p180);
		periodsByLevel.put(NIVEAU.NIVEAU2, p20);
		periodsByLevel.put(NIVEAU.NIVEAU3, p23);
		periodsByLevel.put(NIVEAU.NIVEAU4, p18);
		periodsByLevel.put(NIVEAU.NIVEAU5, p13);
		periodsByLevel.put(NIVEAU.NIVEAU6, p7);
		SEGMENT.VIP.setPeriodeGraceByNiveau(periodsByLevel);
		//DISTRIBUTEUR
		periodsByLevel = new TreeMap<NIVEAU,PeriodeGrace>();
		periodsByLevel.put(NIVEAU.NIVEAU1, p180);
		periodsByLevel.put(NIVEAU.NIVEAU2, p20);
		periodsByLevel.put(NIVEAU.NIVEAU3, p23);
		periodsByLevel.put(NIVEAU.NIVEAU4, p18);
		periodsByLevel.put(NIVEAU.NIVEAU5, p13);
		periodsByLevel.put(NIVEAU.NIVEAU6, p7);
		SEGMENT.DISTRIBUTEUR.setPeriodeGraceByNiveau(periodsByLevel);
		
		
		//Restrictions à la fin des Périodes d'Exceptions
		//SOHO
		SEGMENT.SOHO.setBAROUTSafterException((long)0);
		SEGMENT.SOHO.setBARINSafterException((long)0);
		SEGMENT.SOHO.setDESACTIVATIONSafterException((long)0);
		
		
		//SME
		SEGMENT.SME.setBAROUTSafterException((long)2);
		SEGMENT.SME.setBARINSafterException((long)2);
		SEGMENT.SME.setDESACTIVATIONSafterException((long)2);
	
		
		//LARGE
		SEGMENT.LARGE.setBAROUTSafterException((long)7);
		SEGMENT.LARGE.setBARINSafterException((long)2);
		SEGMENT.LARGE.setDESACTIVATIONSafterException((long)2);
	
		
		//OS
		SEGMENT.OS.setBAROUTSafterException((long)7);
		SEGMENT.OS.setBARINSafterException((long)2);
		SEGMENT.OS.setDESACTIVATIONSafterException((long)2);
	
		
		//KEY
		SEGMENT.KEY.setBAROUTSafterException((long)7);
		SEGMENT.KEY.setBARINSafterException((long)2);
		SEGMENT.KEY.setDESACTIVATIONSafterException((long)2);
	
		
		//SPECIAL_KEY
		SEGMENT.SPECIAL_KEY.setBAROUTSafterException((long)7);
		SEGMENT.SPECIAL_KEY.setBARINSafterException((long)2);
		SEGMENT.SPECIAL_KEY.setDESACTIVATIONSafterException((long)2);
	
		//VIP
		SEGMENT.VIP.setBAROUTSafterException((long)5);
		SEGMENT.VIP.setBARINSafterException((long)2);
		SEGMENT.VIP.setDESACTIVATIONSafterException((long)2);
	
		//DISTRIBUTEUR
		SEGMENT.DISTRIBUTEUR.setBAROUTSafterException((long)5);
		SEGMENT.DISTRIBUTEUR.setBARINSafterException((long)2);
		SEGMENT.DISTRIBUTEUR.setDESACTIVATIONSafterException((long)2);
	
	}
	
}
