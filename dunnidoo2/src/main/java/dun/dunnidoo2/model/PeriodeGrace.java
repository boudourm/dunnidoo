package dun.dunnidoo2.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.TreeMap;

public class PeriodeGrace
{
	
	//Attributes
	private Long periode;
	
	//Association
	
	
	//Constructor
	public PeriodeGrace(Long periode) {
		super();
		this.periode = periode;
	}

	//Getters & Setters
	public Long getPeriode() {return periode;}
	public void setPeriode(Long periode) {this.periode = periode;}
	
	//Instance Methods
	public LocalDate dateFromNow()
	{
		return LocalDate.now().plus(this.getPeriode(),ChronoUnit.DAYS);
	}



	//Overridden Methods
	@Override
	public String toString() {
		return "PeriodeGrace [periode=" + periode + "]";
	}
	
	static
	{
		//MATRICE des Périodes de Grâces
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

		Map<NIVEAU,PeriodeGrace> periodsByLevel = new TreeMap<NIVEAU,PeriodeGrace>();

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

	}
}
