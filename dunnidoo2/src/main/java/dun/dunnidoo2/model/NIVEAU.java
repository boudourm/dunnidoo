package dun.dunnidoo2.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public enum NIVEAU {
	// Objects
	NIVEAU1("Niveau 1",USER_PROFILE.SAM,new ArrayList<USER_PROFILE>(Arrays.asList(USER_PROFILE.SAM ,USER_PROFILE.SUP , USER_PROFILE.CCFAC))),
	NIVEAU2("Niveau 2",USER_PROFILE.SUP,new ArrayList<USER_PROFILE>(Arrays.asList(USER_PROFILE.SAM ,USER_PROFILE.SUP , USER_PROFILE.CCFAC , USER_PROFILE.CDS))),
	NIVEAU3("Niveau 3",USER_PROFILE.CDS,new ArrayList<USER_PROFILE>(Arrays.asList(USER_PROFILE.SAM ,USER_PROFILE.SUP , USER_PROFILE.CCFAC , USER_PROFILE.CDS ))),
	NIVEAU4("Niveau 4",USER_PROFILE.DA,new ArrayList<USER_PROFILE>(Arrays.asList(USER_PROFILE.SAM ,USER_PROFILE.SUP , USER_PROFILE.CCFAC , USER_PROFILE.CDS , USER_PROFILE.DA ))),
	NIVEAU5("Niveau 5",USER_PROFILE.DIRECTEUR,new ArrayList<USER_PROFILE>(Arrays.asList(USER_PROFILE.SAM ,USER_PROFILE.SUP , USER_PROFILE.CCFAC , USER_PROFILE.CDS , USER_PROFILE.DA ,USER_PROFILE.DIRECTEUR ))),
	NIVEAU6("Niveau 6",USER_PROFILE.CCO,new ArrayList<USER_PROFILE>(Arrays.asList(USER_PROFILE.SAM ,USER_PROFILE.SUP , USER_PROFILE.CCFAC , USER_PROFILE.CDS , USER_PROFILE.DA ,USER_PROFILE.DIRECTEUR , USER_PROFILE.CCO )));
	
	// Attributes
	private String libelle;
	private USER_PROFILE responsable;
	private List<USER_PROFILE> intervenants ;
	
	//Associations
	private Map<SEGMENT,PeriodeGrace> periodeGraceBySegment = new TreeMap<SEGMENT,PeriodeGrace>();
	
	private static Map<SEGMENT,Long> BAROUTSafterException = new TreeMap<SEGMENT,Long>();
	private static Map<SEGMENT,Long> BARINSafterException = new TreeMap<SEGMENT,Long>();
	private static Map<SEGMENT,Long> DESACTIVATIONSafterException = new TreeMap<SEGMENT,Long>();
	
	// Constructor
	private NIVEAU(String libelle, USER_PROFILE responsable, List<USER_PROFILE> intervenants) {
		this.libelle = libelle;
		this.responsable = responsable;
		this.intervenants = intervenants;
	}	
	
	// Getters & Setters
	public void setLibelle(String libelle) {this.libelle = libelle;	}
	public USER_PROFILE getResponsable() {return responsable;}
	public void setResponsable(USER_PROFILE responsable) {this.responsable = responsable;}
	public List<USER_PROFILE> getIntervenants() {return intervenants;}
	public void setIntervenants(List<USER_PROFILE> intervenants) {this.intervenants = intervenants;}
	public String getLibelle() {return libelle;}
	public Map<SEGMENT, PeriodeGrace> getPeriodeGraceBySegment() {return periodeGraceBySegment;}
	public void setPeriodeGraceBySegment(Map<SEGMENT, PeriodeGrace> periodeGraceBySegment) {this.periodeGraceBySegment = periodeGraceBySegment;}
	public static Map<SEGMENT, Long> getBAROUTSafterException() {return BAROUTSafterException;}
	public static void setBAROUTSafterException(Map<SEGMENT, Long> bAROUTSafterException) {	BAROUTSafterException = bAROUTSafterException;}
	public static Map<SEGMENT, Long> getBARINSafterException() {return BARINSafterException;}
	public static void setBARINSafterException(Map<SEGMENT, Long> bARINSafterException) {BARINSafterException = bARINSafterException;}
	public static Map<SEGMENT, Long> getDESACTIVATIONSafterException() {return DESACTIVATIONSafterException;}
	public static void setDESACTIVATIONSafterException(Map<SEGMENT, Long> dESACTIVATIONSafterException) {DESACTIVATIONSafterException = dESACTIVATIONSafterException;}

	//Instance Methods
	
	public NIVEAU next()
	{
		if(!this.equals(NIVEAU6))
		{
			return values()[ordinal()+1];
		}
		else
		{
			return null;
		}
	}
	
	static
	{
		Map<SEGMENT,PeriodeGrace> periodsBySegment = new TreeMap<SEGMENT,PeriodeGrace>();
		
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
		

		//NIVEAUX
		
		//NIVEAU1
		periodsBySegment.put(SEGMENT.SOHO, p0);
		periodsBySegment.put(SEGMENT.SME, p11);
		periodsBySegment.put(SEGMENT.LARGE, p11);
		periodsBySegment.put(SEGMENT.OS, p11);
		periodsBySegment.put(SEGMENT.KEY, p20);
		periodsBySegment.put(SEGMENT.SPECIAL_KEY, p20);
		periodsBySegment.put(SEGMENT.VIP, p180);
		periodsBySegment.put(SEGMENT.DISTRIBUTEUR, p180);
		NIVEAU.NIVEAU1.setPeriodeGraceBySegment(periodsBySegment);
		
		//NIVEAU2
		periodsBySegment = new TreeMap<SEGMENT,PeriodeGrace>();
		periodsBySegment.put(SEGMENT.SOHO, p0);
		periodsBySegment.put(SEGMENT.SME, p15);
		periodsBySegment.put(SEGMENT.LARGE, p15);
		periodsBySegment.put(SEGMENT.OS, p15);	
		periodsBySegment.put(SEGMENT.KEY, p23);
		periodsBySegment.put(SEGMENT.SPECIAL_KEY, p23);
		periodsBySegment.put(SEGMENT.VIP, p20);
		periodsBySegment.put(SEGMENT.DISTRIBUTEUR, p20);
		NIVEAU.NIVEAU2.setPeriodeGraceBySegment(periodsBySegment);
		
		//NIVEAU3
		periodsBySegment = new TreeMap<SEGMENT,PeriodeGrace>();
		periodsBySegment.put(SEGMENT.SOHO, p0);
		periodsBySegment.put(SEGMENT.SME, p12);
		periodsBySegment.put(SEGMENT.LARGE, p12);
		periodsBySegment.put(SEGMENT.OS, p12);
		periodsBySegment.put(SEGMENT.KEY, p18);
		periodsBySegment.put(SEGMENT.SPECIAL_KEY, p18);
		periodsBySegment.put(SEGMENT.VIP, p23);
		periodsBySegment.put(SEGMENT.DISTRIBUTEUR, p23);
		NIVEAU.NIVEAU3.setPeriodeGraceBySegment(periodsBySegment);
	
		//NIVEAU4
		periodsBySegment = new TreeMap<SEGMENT,PeriodeGrace>();
		periodsBySegment.put(SEGMENT.SOHO, p0);
		periodsBySegment.put(SEGMENT.SME, p9);
		periodsBySegment.put(SEGMENT.LARGE, p9);
		periodsBySegment.put(SEGMENT.OS, p9);
		periodsBySegment.put(SEGMENT.KEY, p13);
		periodsBySegment.put(SEGMENT.SPECIAL_KEY, p13);
		periodsBySegment.put(SEGMENT.VIP, p18);
		periodsBySegment.put(SEGMENT.DISTRIBUTEUR, p18);
		NIVEAU.NIVEAU4.setPeriodeGraceBySegment(periodsBySegment);

		//NIVEAU5
		periodsBySegment = new TreeMap<SEGMENT,PeriodeGrace>();
		periodsBySegment.put(SEGMENT.SOHO, p0);
		periodsBySegment.put(SEGMENT.SME, p5);
		periodsBySegment.put(SEGMENT.LARGE, p5);
		periodsBySegment.put(SEGMENT.OS, p5);
		periodsBySegment.put(SEGMENT.KEY, p5);
		periodsBySegment.put(SEGMENT.SPECIAL_KEY, p5);
		periodsBySegment.put(SEGMENT.VIP, p13);
		periodsBySegment.put(SEGMENT.DISTRIBUTEUR, p13);
		NIVEAU.NIVEAU5.setPeriodeGraceBySegment(periodsBySegment);

		//NIVEAU6
		periodsBySegment = new TreeMap<SEGMENT,PeriodeGrace>();
		periodsBySegment.put(SEGMENT.SOHO, p0);
		periodsBySegment.put(SEGMENT.SME, p0);
		periodsBySegment.put(SEGMENT.LARGE, p0);
		periodsBySegment.put(SEGMENT.OS, p0);
		periodsBySegment.put(SEGMENT.KEY, p5);
		periodsBySegment.put(SEGMENT.SPECIAL_KEY, p5);
		periodsBySegment.put(SEGMENT.VIP, p7);
		periodsBySegment.put(SEGMENT.DISTRIBUTEUR, p7);
		NIVEAU.NIVEAU6.setPeriodeGraceBySegment(periodsBySegment);

		
		//BAROUT
		Map<SEGMENT,Long> retrictionsAfterException = new TreeMap<SEGMENT,Long>();
		retrictionsAfterException.put(SEGMENT.SOHO, (long) 0);
		retrictionsAfterException.put(SEGMENT.SME, (long) 2);
		retrictionsAfterException.put(SEGMENT.LARGE,(long) 7);
		retrictionsAfterException.put(SEGMENT.OS, (long)7);
		retrictionsAfterException.put(SEGMENT.KEY, (long)7);
		retrictionsAfterException.put(SEGMENT.SPECIAL_KEY,(long) 7);
		retrictionsAfterException.put(SEGMENT.VIP,(long) 5);
		retrictionsAfterException.put(SEGMENT.DISTRIBUTEUR,(long) 5);
		NIVEAU.setBAROUTSafterException(retrictionsAfterException);

		//BARIN
		retrictionsAfterException = new TreeMap<SEGMENT,Long>();
		retrictionsAfterException.put(SEGMENT.SOHO, (long) 0);
		retrictionsAfterException.put(SEGMENT.SME, (long) 2);
		retrictionsAfterException.put(SEGMENT.LARGE,(long) 2);
		retrictionsAfterException.put(SEGMENT.OS, (long)2);
		retrictionsAfterException.put(SEGMENT.KEY, (long)2);
		retrictionsAfterException.put(SEGMENT.SPECIAL_KEY,(long) 2);
		retrictionsAfterException.put(SEGMENT.VIP,(long) 2);
		retrictionsAfterException.put(SEGMENT.DISTRIBUTEUR,(long) 2);
		NIVEAU.setBARINSafterException(retrictionsAfterException);
		
		//DESACTIVATION
		retrictionsAfterException = new TreeMap<SEGMENT,Long>();
		retrictionsAfterException.put(SEGMENT.SOHO, (long) 0);
		retrictionsAfterException.put(SEGMENT.SME, (long) 2);
		retrictionsAfterException.put(SEGMENT.LARGE,(long) 2);
		retrictionsAfterException.put(SEGMENT.OS, (long)2);
		retrictionsAfterException.put(SEGMENT.KEY, (long)2);
		retrictionsAfterException.put(SEGMENT.SPECIAL_KEY,(long) 2);
		retrictionsAfterException.put(SEGMENT.VIP,(long) 2);
		retrictionsAfterException.put(SEGMENT.DISTRIBUTEUR,(long) 2);
		NIVEAU.setDESACTIVATIONSafterException(retrictionsAfterException);
	}

}
