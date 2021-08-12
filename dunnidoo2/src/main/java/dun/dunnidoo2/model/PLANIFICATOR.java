package dun.dunnidoo2.model;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public final class PLANIFICATOR
{
	
	//Associations
	private static List<Planification> planifications = new ArrayList<Planification>();
	private static List<Alerte> alertes = new ArrayList<Alerte>();
	private static List<Customer> customers= new ArrayList<Customer>();
	private static List<USER> users = new ArrayList<USER>();
	
	private static ESB esb;
	private static final Administrator DUNNIDOO = new Administrator("USER0", "DUNNIDOO", "pwd", "DUNNIDOO@ooredoo.dz", "DUNNIDOO", "SYSTEM", USER_PROFILE.ADMIN);
	static {PLANIFICATOR.addUser(PLANIFICATOR.DUNNIDOO);}
	//Constructor
	private PLANIFICATOR(){	}
	
	//Getters & Setters
	public static List<Planification> getPlanifications() {return planifications;}
	public static void setPlanifications(List<Planification> planifications) {PLANIFICATOR.planifications = planifications;}
	public static List<Alerte> getAlertes() {return alertes;}
	public static void setAlertes(List<Alerte> alertes) {PLANIFICATOR.alertes = alertes;}
	public static List<Customer> getCustomers() {return customers;}
	public static void setCustomers(List<Customer> customers) {	PLANIFICATOR.customers = customers;}
	public static List<USER> getUsers() {return users;}
	public static void setUsres(List<USER> users) {PLANIFICATOR.users = users;}
	public static ESB getEsb() {return esb;}
	public static void setEsb(ESB esb) {PLANIFICATOR.esb = esb;}
	public static Administrator getSystem() {return DUNNIDOO;}
	
	//Instance Methods
	public static void Plan()
	{
		//TODO
	}
	public static void Archive()
	{
		//TODO
	}
	
	
	public static void updatePlanificationStatus()
	{
		String query = "SELECT PLANIFICATION_ID , STATUS FROM DUNNING_PLANIFICATION WHERE EXECUTION_DATE = to_date('"+new SimpleDateFormat("dd/MM/yyyy").format(Date.valueOf(LocalDate.now()))+"' , 'DD/MM/YYYY' )" ;
		ArrayList<ArrayList> lignes = JDBC_DUNNIDOO.SELECT(query);
		for (ArrayList planif : lignes) 
		{
			PLANIFICATOR.getPlanification(planif.get(0).toString()).setStatus(planif.get(1).toString()); 
		}
	}
	
	

	public static boolean addNewAlert(Alerte a)
	{
		applyAlert(a);
		return addAlertes(a);
	}

	public static List<Customer> getOnHoldCustomers()
	{
		ArrayList<Customer> resultat = new ArrayList<Customer>(); 
		for (Customer customer : customers)
		{
			if(customer.getStatus().equalsIgnoreCase("ONHOLD"))
			{
				resultat.add(customer);
			}
		}
		return resultat;
	}
	
	public static void applyAlert(Alerte a)
	{
		for (Planification planification : a.exceptionWorkFlow())
		{
			planification.setStatus("EXCEPTION");
			planification.extendExecutionDate(a.getPeriode());
		}
	}
	
	public static void checkAlertExtentionValiation() // à J - 1H
	{
		for (Alerte alerte : getAlertes())
		{
			LocalDateTime dateHeureCloture = alerte.getDateCloture().atTime(0, 1);
			if(Math.abs(ChronoUnit.MINUTES.between(LocalDateTime.now(), dateHeureCloture))<=20)
			{
				if(alerte.getNiveau().equals(NIVEAU.NIVEAU6))
					closeAlert(alerte, MOTIF.NIVEAU6_ATTEINT);
				else
					closeAlert(alerte, MOTIF.NORESPONSE);
			}
		}
	}
	
	public static void checkExtendableAlerts()
	{
		for (Alerte alerte : getAlertes()) 
		{
			if(LocalDate.now().plus(2,ChronoUnit.DAYS).equals(alerte.getDateCloture())&&
			   !alerte.getNiveau().equals(NIVEAU.NIVEAU6))
			  alertExtensionRequest(alerte);
		}
	}
	public static void alertExtensionRequest(Alerte a) //à J-2 
	{
		Customer c = a.getCustomer(); 
		a.nextLevelResponsable().addExtendableAlerte(a);
		Mail mail = new Mail(  "Période d'exception "+a.getNiveau().next()+" ",
							   "Demande d'approbation du passage"
							  +"au "+a.getNiveau().next()+" de l'alerte :\n"
							  +"Cust code: "+c.getCustCode()+" \r\n" + 
							   "Nom du demandeur: "+c.getSam().getFirstName()+" "+c.getSam().getLastName()+" \r\n" + 
							   "Titre du demandeur: "+c.getSam().getUserProfile()+"\r\n" + 
							   "Segment : "+c.getSegment().getLibelle()+"\r\n" + 
							   "Motif de l’alerte: "+a.getMotifDeclenchement().getMotif()+"\r\n" + 
							   "Nombre de jours accordés: "+a.getPeriode()+"\r\n" + 
							   "Montant des dus antérieurs: "+c.getOpenAmount()+"\r\n" + 
							   "Montant de la  dernière facture: "+c.getInvoiceAmout()+"\r\n" + 
							   "Date de règlement de la facture: "+c.getDueDate()+"\r\n" + 
							   "\nVeuillez vous prononcer au niveau de DUNNIDOO.",
							   PLANIFICATOR.getUser("DUNNIDOO@ooredoo.dz"),
							   new ArrayList<USER>(Arrays.asList(a.nextLevelResponsable())));
		mail.send();
		LocalDateTime end = LocalDateTime.now().plus(48,ChronoUnit.HOURS);
		NIVEAU niveau = a.getNiveau();
		
/*		while(Math.abs(ChronoUnit.MINUTES.between(LocalDateTime.now(),end)) < 30 )
		{
			try 
			{
				TimeUnit.HOURS.sleep(4);
				if(niveau.equals(a.getNiveau()) && a.getStatus().equalsIgnoreCase("ACTIVE"))
					mail.send();
				else
					break;
				
			} 
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
			
		}*/
		
	}
	

	/*
	 * APPLICATION
	 * Les Classes
	 * Scheduling des Tasks 
	 * Hibernate + Base de Données  Mehdi
	 * Archivage
	 * Invocation WebService
	 * InfoClient - TIBCO
	 		*DunningEligibleCustomers
	 		*getCustomerAndContacts
	 		*getCustomerContracts = getFleet 
	 * SendMail - WS - TIBCO 
	 * Dashboards
	 * {
	 * 	Exporter
	 * 	Files
	 *  Import
	 *  Parse
	 *  
	 * }
	 * Pages : Client (infos client + exception ) Mehdi
	 * 		   Appels Sortants   Amina
	 * 		   Dashboards        Mehdi
	 * 		   Segments          Mehdi
	 * 		   Planifications  : Amina
	 * 		   Exception	   : Amina
	 * 		   Customers	   : Amina
	 * 		   Reporting	     Mehdi
	 * 
	 * Security    Amina
	 * 
	 * PRESENTATION
	 * Diapo ->
	 * Speech - KIFKIF
	 * Simulation -
	 * 
	 * ORGANISATION : 
	 *     LUNDI      -         MARDI             -     MERCREDI      -     JEUDI 
	 * 	Classe-TIBCO 12h   	Hibernate+Dashboards		Les Pages		    Tests            Nchallah
	 * 							WebServices */
	
	public static void closeAlert(Alerte a , MOTIF motif)
	{
		a.cloture(motif);
		a.getCustomer().inDunning();	
		for (Planification planification : a.getCustomer().getPlanififcations()) 
		{
			planification.resume(motif);
		}
		
	}
	
	public static void applyAlertExtension(Alerte a)
	{
		Long periode = a.getPeriode();
		for (Planification planification : a.exceptionWorkFlow()) 
		{
			planification.extendExecutionDate(periode);
		}
	}
	
	public static void interruptAlert(Alerte a)
	{
		for (Planification planification : a.exceptionWorkFlow()) 
		{
			planification.resume(MOTIF.INTERRUPTION);
		}
	}
	
	public static void dunningListExtraction()
	{
		PLANIFICATOR.getEsb().getDunningEligibleCustomers();
		PLANIFICATOR.addCustomer(PLANIFICATOR.loadNewDunningEligibleCustomers());
		PLANIFICATOR.applyDunning();
	}
	
	public static List<Customer> loadNewDunningEligibleCustomers()
	{
		List<Customer> resultat= new ArrayList<Customer>();
		for (ArrayList ligne : JDBC_DUNNIDOO.SELECT("SELECT * FROM DUNNING_ELIGIBLE_CUSTOMERS \r\n" + 
													"WHERE STATUS = 'ONHOLD'")) 
		{
			Customer cust= new Customer(ligne.get(0).toString(), 
					  (String) ligne.get(1), 
					  Integer.valueOf(ligne.get(2).toString()) , 
					  (String) ligne.get(3), 
					  SEGMENT.valueOf((String) ligne.get(4)),
					  Integer.valueOf(ligne.get(5).toString()), 
					  Float.valueOf(ligne.get(6).toString()), 
					  ((Date) ligne.get(7)).toLocalDate(), 
					  ((Date) ligne.get(8)).toLocalDate(), 
					  (String) ligne.get(9), 
					  (String) ligne.get(10),
					  (String) ligne.get(11), 
					  (Sam)PLANIFICATOR.getUser(ligne.get(12).toString()));
			cust.setFlat(false);
			resultat.add(cust); 
		}

		return resultat;
	}
	
	public static void applyDunning()
	{

		//Récupérer les Clients OnHold
		for (Customer customer : getOnHoldCustomers())
		{
			//Declencher le Dunning du Client
			customer.inDunning();
			//Générer les Planifications
			List<Planification> planifications = 
			customer.getSegment().instantPlanifications(customer);
			//Ajouter les Planifications
			addPlanification(planifications);
			//Planifier les Actions 
			planDunningAction(planifications);
		}
		


	}
	
	public static void planDunningAction(Planification planification)
	{
		if(planification.getAction().getTypeRelance().equalsIgnoreCase("target"))
		{
			insertBatchInput(planification);
		}
		else if(planification.getAction().getTypeRelance().equalsIgnoreCase("fleet"))
		{
			insertBatchInput(planification,planification.getCustomer().getFleet());
		}
	}
	
	public static void planDunningAction(List<Planification> planifications)
	{
		for (Planification planification : planifications)
		{
			planDunningAction(planification);
		}
	}
	
	public static void executeOutGoingCalls()
	{
		for (Planification planification : todaysOutGoingCalls()) 
		{
			planification.getCustomer().getSam().addOutGoingCall(planification.getCustomer());
		}
	}
	
	public static void insertBatchInput(Planification planification)
	{
	String planificationId = "";
		for (ArrayList ligne : JDBC_DUNNIDOO.SELECT("SELECT DUNNING_PLANIFICATION_SEQUENCE.NEXTVAL FROM DUAL")) 
		{for (Object id : ligne) {	planificationId = id.toString();}}
		
		planification.setPlanificationId(planificationId);

		String query = "INSERT INTO DUNNING_PLANIFICATION VALUES \r\n" + 
				"("+planification.getPlanificationId()+",\r\n" + 
				" '"+planification.getAction().getActionName()+"' , \r\n" + 
				" to_date('"+new SimpleDateFormat("dd/MM/yyyy").format(Date.valueOf(planification.getExecutionDate()))+"' , 'DD/MM/YYYY' ),\r\n" + 
				" '"+planification.getStatus()+"' ,  \r\n" + 
				" '"+planification.getCustomer().getCustomerId()+"' , \r\n" + 
				" '"+planification.getCustomer().getCustCode()+"' , \r\n" + 
				" '"+planification.getCustomer().getCoId().toString()+"' ,\r\n" + 
				" '"+planification.getCustomer().getInvoiceAmout()+"' ,\r\n" + 
				" '"+planification.getCustomer().getOpenAmount().intValue()+"' , \r\n" + 
				" to_date('"+new SimpleDateFormat("dd/MM/yyyy").format(Date.valueOf(planification.getCustomer().getDueDate()))+"' , 'DD/MM/YYYY' ) ,\r\n" + 
				" to_date('"+new SimpleDateFormat("dd/MM/yyyy").format(Date.valueOf(planification.getCustomer().getLbcDate()))+"' , 'DD/MM/YYYY' ) ,\r\n" + 
				" '"+planification.getCustomer().getCclName()+"' , \r\n" + 
				" '"+planification.getCustomer().getMsisdn()+"' ,\r\n" + 
				" '"+planification.getCustomer().getCcEmail()+"')";
		if(!planification.getAction().equals(ACTION.OUTGOING_CALL))
		JDBC_DUNNIDOO.UPDATE(query);
	}
	
	public static void insertBatchInput(List<Planification> planifications)
	{
		for (Planification planification : planifications)
		{
			insertBatchInput(planification);
		}
	}
	
	public static void insertBatchInput(Planification planification ,Map<String,String> fleet)
	{
		insertBatchInput(planification);
		for (Entry<String, String> flatCustomer : fleet.entrySet())
		{
			/*Customer flatCustomerTemporaire = new Customer(planification.getCustomer().getCustomerId().toLowerCase() ,
		    			 planification.getCustomer().getCustCode().toString(), 
		    			 
		    			 Integer.valueOf(flatCustomer.getKey())CO_ID - flatCustomer , 
		    			 planification.getCustomer().getStatus() , 
		    			 planification.getCustomer().getSegment() , 
		    			 planification.getCustomer().getInvoiceAmout() , 
		    			 planification.getCustomer().getOpenAmount() ,
		    			 planification.getCustomer().getDueDate() ,
		    			 planification.getCustomer().getLbcDate(), 
		    			 planification.getCustomer().getCclName()+"_FLAT_"+planification.getAction().getActionName(), 
		    			 flatCustomer.getValue()MSISDN - flatCustomer,
		    			 planification.getCustomer().getCcEmail(), 
		    			 planification.getCustomer().getSam() );*/
		    			 
			Customer flatCustomerTemporaire = new Customer (planification.getCustomer().getCustomerId().toLowerCase() , 
					planification.getCustomer().getCustCode().toString(), 
					Integer.valueOf(flatCustomer.getKey()), 
					 planification.getCustomer().getStatus() , 
					 planification.getCustomer().getSegment() ,
					 planification.getCustomer().getInvoiceAmout(), 
					 planification.getCustomer().getOpenAmount() , 
					 planification.getCustomer().getDueDate(), 
					 planification.getCustomer().getLbcDate(), 
					 planification.getCustomer().getCclName()+"_FLAT_"+planification.getAction().getActionName(), 
					 flatCustomer.getValue()/*MSISDN - flatCustomer*/,
					 planification.getCustomer().getCcEmail(), 
					 planification.getCustomer().getSam(), true);

			
			insertBatchInput(
			new Planification(planification.getAction(), flatCustomerTemporaire, planification.getExecutionDate()));
		}
	}
	
	public static void closeDunningForPayment()
	{
		for (Customer customer : PLANIFICATOR.duesPaidCustomers()) 
		{
			customer.hasPaid();
			for (Planification planification : customer.plannedPlanifications()) 
			{
					planification.cancel();
			}
			if(customer.getStatus().equalsIgnoreCase("EXCEPTION"))
				closeAlert(customer.getAlerte(), MOTIF.PAYEMENT);
		}
	}
	

	/*
	 * Methode Dashboards:
	 * - Nb Clients par Segment
	 * - Dettes des Clients par Segment
	 * - Dettes par mois
	 * 
	 * Methodes Pour Interface:
	 * 
	 * 
	 * */
	public static Map<SEGMENT , Float > numberOfCustomersPerSegment()
	{
		Map<SEGMENT , Float > result = new TreeMap<SEGMENT , Float > ();
		for (SEGMENT segment : SEGMENT.values()) 
		{
			result.put(segment, (float)((100.0*segment.getCustomers().size())/PLANIFICATOR.getCustomers().size()));
		}
		return result ;
	}
	
	public static Map<SEGMENT , Integer > debtsPerSegment()
	{
		Map<SEGMENT , Integer > result = new TreeMap<SEGMENT , Integer > ();
		for (SEGMENT segment : SEGMENT.values()) 
		{
			int debt = 0 ;
			for (Customer customer : segment.getCustomers()) 
			{
				debt += customer.getOpenAmount();
			}
			result.put(segment, debt);
		}
		return result ;
	}
	
	public static Map<Month , Integer > debtsPerMonths()
	{
		Map<Month , Integer > result = new TreeMap<Month , Integer > ();
		for (Month month : Month.values()) 
		{
			int debt = 0 ;
			for (Customer customer : PLANIFICATOR.getCustomers()) 
			{
				if(customer.getLbcDate().getMonth().equals(month))
					debt += customer.getOpenAmount();
			}
			result.put(month, debt);
		}
		return result ;
	}
	
	//Association Methods
	public static boolean addPlanification(Planification planification){return PLANIFICATOR.getPlanifications().add(planification);}
	public static boolean addPlanification(List<Planification> planifications){return PLANIFICATOR.getPlanifications().addAll(planifications);}
	public static boolean addAlertes(Alerte alerte){return PLANIFICATOR.getAlertes().add(alerte);}
	public static boolean addAlertes(List<Alerte> alertes){return PLANIFICATOR.getAlertes().addAll(alertes);}
	public static boolean addCustomer(Customer customer){return PLANIFICATOR.getCustomers().add(customer);}
	public static boolean addCustomer(List<Customer> customers){return PLANIFICATOR.getCustomers().addAll(customers);}
	public static boolean addUser(USER user){return PLANIFICATOR.getUsers().add(user);}
	public static boolean addUser(List<USER> user){return PLANIFICATOR.getUsers().addAll(users);}
	
	public static boolean removePlanification(Planification planification){return PLANIFICATOR.getPlanifications().remove(planification);}
	public static boolean removePlanification(List<Planification> planifications){return PLANIFICATOR.getPlanifications().removeAll(planifications);}
	public static boolean removeAlertes(Alerte alerte){return PLANIFICATOR.getAlertes().remove(alerte);}
	public static boolean removeAlertes(List<Alerte> alertes){return PLANIFICATOR.getAlertes().removeAll(alertes);}
	public static boolean removeCustomer(Customer customer){return PLANIFICATOR.getCustomers().remove(customer);}
	public static boolean removeCustomer(List<Customer> customers){return PLANIFICATOR.getCustomers().removeAll(customers);}
	public static boolean removeUser(USER user){return PLANIFICATOR.getUsers().remove(user);}
	public static boolean removeUser(List<USER> user){return PLANIFICATOR.getUsers().removeAll(users);}
	
	public static USER getUser(String mail){for (USER user : users) {if(user.getEmail().equals(mail))return user;}return null;}
	public static List<CCFacturation> getCCFacUsers() 
	{
		ArrayList<CCFacturation> resultat = new ArrayList<CCFacturation>();
		for (USER user : PLANIFICATOR.getUsers() )
		{
			if(user instanceof CCFacturation) resultat.add((CCFacturation)user);		
		}
		return resultat;
	}
	public static Customer getCustomer(String customerId)
	{
		for (Customer customer : PLANIFICATOR.getCustomers()) 
		{
			if(customer.getCustomerId().equals(customerId))
			return customer;
		}
		return null;
	}
	
	public static List<Customer> duesPaidCustomers()
	{
		ArrayList<Customer> resultat = new ArrayList<Customer>();
		for (Customer customer : resultat)
			if(customer.getOpenAmount()==0)
				resultat.add(customer);
		return resultat;
	}
	
	public static List<Planification> todaysOutGoingCalls()
	{
		ArrayList<Planification> resultat = new ArrayList<Planification>();
		for (Planification planification : getPlanifications()) 
		{
			if(planification.getExecutionDate().equals(LocalDate.now()) &&
			   planification.getAction().equals(ACTION.OUTGOING_CALL) &&
			   planification.getStatus().equalsIgnoreCase("PLANNED"))
				resultat.add(planification);
		}
		return resultat;
	}
	
	public static Planification getPlanification(String planificationId)
	{
		for (Planification planification : getPlanifications() ) 
		{
			if(planification.getPlanificationId().equals(planificationId))
				return planification;
			
		}
		return null ;
	}
	
	//Affichage
	
	public static List<Customer> inDunningCustomers()
	{
		ArrayList<Customer> resultat = new ArrayList<Customer>();
		for (Customer customer : getCustomers()) 
		{
			if(customer.getStatus().equalsIgnoreCase("INDUNNING"))
				resultat.add(customer);		
		}
		return resultat;
	}
	
	public static List<Customer> duesPayedCustomers()
	{
		ArrayList<Customer> resultat = new ArrayList<Customer>();
		for (Customer customer : getCustomers()) 
		{
			if(customer.getStatus().equalsIgnoreCase("PAID"))
				resultat.add(customer);	
		}
		return resultat;
	} 
	
	public static List<Customer> desactivatedCustomers()
	{
		ArrayList<Customer> resultat = new ArrayList<Customer>();
		for (Customer customer : getCustomers()) 
		{
			if(customer.getStatus().equalsIgnoreCase("DESACTIVATED"))
				resultat.add(customer);	
		}
		return resultat;
	}
	
	public static List<Planification> plannedPlanifications()
	{
		ArrayList<Planification> resultat = new ArrayList<Planification>();
		for (Planification planification : getPlanifications()) 
		{
			if(planification.getStatus().equalsIgnoreCase("PLANNED"))
				resultat.add(planification);	
		}
		return resultat;
	}
	
	public static List<Planification> canceledPlanifications()
	{
		ArrayList<Planification> resultat = new ArrayList<Planification>();
		for (Planification planification : getPlanifications()) 
		{
			if(planification.getStatus().equalsIgnoreCase("CANCELED"))
				resultat.add(planification);	
		}
		return resultat;
	}
	
	public static List<Planification> successPlanifications()
	{
		ArrayList<Planification> resultat = new ArrayList<Planification>();
		for (Planification planification : getPlanifications()) 
		{
			if(planification.getStatus().equalsIgnoreCase("SUCCESS"))
				resultat.add(planification);	
		}
		return resultat;
	}
	
	public static List<Alerte> activeAlertes()
	{
		ArrayList<Alerte> resultat = new ArrayList<Alerte>();
		for (Alerte alerte : getAlertes()) 
		{
			if(alerte.getStatus().equalsIgnoreCase("ACTIVE"))
				resultat.add(alerte);	
		}
		return resultat;
	}
	
	public static List<Alerte> interruptedAlertes()
	{
		ArrayList<Alerte> resultat = new ArrayList<Alerte>();
		for (Alerte alerte : getAlertes()) 
		{
			if(alerte.getStatus().equalsIgnoreCase("INTERRUPTED"))
				resultat.add(alerte);	
		}
		return resultat;
	}
	
	public static List<Alerte> closedAlertes()
	{
		ArrayList<Alerte> resultat = new ArrayList<Alerte>();
		for (Alerte alerte : getAlertes()) 
		{
			if(alerte.getStatus().equalsIgnoreCase("CLOSED"))
				resultat.add(alerte);	
		}
		return resultat;
	}
	//Overridden Methods
	
	static
	{		
		//ESB
		PLANIFICATOR.esb = new EsbTibco();
		
		//USERS
		Superieur arezki = new Superieur("0", "LADJADJA", "pwd", "aladjadj@ooredoo.dz", "AREZKI", "LADJADJ", USER_PROFILE.SUP);
		Sam mehdi = new Sam("1", "BOUDOURM", "123vivaALGERIA", "mboudour@ooredoo.dz", "Mehdi", "BOUDOUR",
				USER_PROFILE.SAM );
		Sam amina = new Sam("2", "BELLEBCIRA", "123vivaALGERIA", "abellebcir@ooredoo.dz", "Amina", "BELLEBCIR",
				USER_PROFILE.SAM);
		addUser(arezki); addUser( mehdi); addUser(amina );
		mehdi.setSuperieur(arezki);	
		amina.setSuperieur(arezki);
		arezki.addSubordonne(mehdi);
		arezki.addSubordonne(amina);
		
		CCFacturation yazid = new CCFacturation("1", "YAZID", "123vivaALGERIA", "yunknown@ooredoo.dz", "Yazid", "Zizoud",
				USER_PROFILE.CCFAC );
		PLANIFICATOR.addUser(yazid);
			
	}
}
