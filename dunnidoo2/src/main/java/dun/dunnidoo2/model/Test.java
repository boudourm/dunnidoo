package dun.dunnidoo2.model;

import java.util.Calendar;

public class Test
{
	public static void main(String[] args)
	{
		JDBC_DUNNIDOO.CONNECT();
		/*
		Customer client = new Customer("8" ,"001", 83697293 , "ONHOLD" , SEGMENT.SOHO , 8000 , (float)6000.0 ,
				LocalDate.of(2018, Month.JUNE,3) ,
				LocalDate.of(2018, Month.MAY,25), "AREZKI" , 
		"0555555555","geronimotoutcourt@gmail.com", (Sam)PLANIFICATOR.getUser("abellebcir@ooredoo.dz"));
		
		PLANIFICATOR.insertBatchInput(client.getSegment().instantPlanifications(client));
		*/
		
		
		/*for (ArrayList ligne : JDBC_DUNNIDOO.SELECT("SELECT * FROM DUNNING_ELIGIBLE_CUSTOMERS \r\n" + 
				"WHERE STATUS = 'ONHOLD'")) 
		{
			for (Object object : ligne)
			{
				System.out.println(object);
			}
		}*/

		/*PLANIFICATOR.addCustomer(PLANIFICATOR.loadNewDunningEligibleCustomers());
		for (Customer customer : PLANIFICATOR.getOnHoldCustomers()) {
			System.out.println(customer);
			System.out.println("**********************************************");
		}*/
		//System.out.println(JDBC_DUNNIDOO.SELECT("SELECT * FROM DUNNING_PLANIFICATION"));
		/*
		
		int n = 0 ;
		for (Customer customer : PLANIFICATOR.getOnHoldCustomers())
		{
			//Declencher le Dunning du Client
			customer.inDunning();
			//Générer les Planifications
			System.out.println("***************************************************");
			List<Planification> planifications = customer.getSegment().instantPlanifications(customer);
			for(Planification p : planifications)
			{
				System.out.println("----------------------------------------------------");
				System.out.println(p.getAction());
				System.out.println(p.getExecutionDate());
				System.out.println(p.getCustomer().getSegment());
				System.out.println(p.getStatus());
			}
			PLANIFICATOR.insertBatchInput(planifications);
			n+=planifications.size();
			//Planifier les Actions 
			//planDunningAction(planifications);
		}*/
	/*	new EsbTibco().getDunningEligibleCustomers();
		List<Customer> c = PLANIFICATOR.loadNewDunningEligibleCustomers(); 
		for (int i = 0; i<8;i++)
		{	System.out.println(c.get(i).getSegment().getLibelle());
			PLANIFICATOR.insertBatchInput(c.get(i).getSegment().instantPlanifications(c.get(i)));
		}		
		*/
		
		
		/*Planification ta3 wahed fihoum*/
/*
		for (Planification planification : PLANIFICATOR.getCustomer("2").getPlanififcations()) 
		{System.out.println("-----------------------------------");
			System.out.println(planification.getAction());
			System.out.println(planification.getCustomer().getCclName());
			System.out.println(planification.getExecutionDate());
			System.out.println(planification.getStatus());
			System.out.println("-----------------------------------");
		}*/
		//((Sam)PLANIFICATOR.getUser("mboudour@ooredoo.dz")).triggerAlert(PLANIFICATOR.getCustomer("2"), MOTIF.PROBLEME_FACTURATION);
/*	ZZZZZ	System.out.println("\n#ALERTE\n");
		for (Planification planification : PLANIFICATOR.getCustomer("2").getPlanififcations()) 
		{System.out.println("-----------------------------------");
			System.out.println(planification.getAction());
			System.out.println(planification.getCustomer().getCclName());
			System.out.println(planification.getExecutionDate());
			System.out.println(planification.getStatus());
			System.out.println("-----------------------------------");
		}

		((CCFacturation)PLANIFICATOR.
				getUser("yunknown@ooredoo.dz")).
		interruptAlert(PLANIFICATOR.getCustomer("2").
				getAlerte());
		for (Planification planification : PLANIFICATOR.getCustomer("2").getPlanififcations()) 
		{System.out.println("-----------------------------------");
			System.out.println(planification.getAction());
			System.out.println(planification.getCustomer().getCclName());
			System.out.println(planification.getExecutionDate());
			System.out.println(planification.getStatus());
			System.out.println("-----------------------------------");
		}
		*/
		
		
		

	/*	for (ArrayList ligne : JDBC_DUNNIDOO.SELECT("SELECT * FROM DUNNING_PLANIFICATION ORDER BY CCLNAME DESC"))
		{
			System.out.println(ligne);
		}
		System.out.println(JDBC_DUNNIDOO.SELECT("SELECT COUNT(*) FROM DUNNING_PLANIFICATION"));
		*/
	/*	
		
		
		System.out.println("Done !");
		
		JDBC_DUNNIDOO.UPDATE("DELETE FROM DUNNING_PLANIFICATION");
		JDBC_DUNNIDOO.UPDATE("DELETE FROM DUNNING_ELIGIBLE_CUSTOMERS");
		
		PLANIFICATOR.dunningListExtraction();
*/
		/*
		for (ArrayList ligne : JDBC_DUNNIDOO.SELECT("SELECT * FROM DUNNING_PLANIFICATION ORDER BY CCLNAME DESC"))
		{
			System.out.println(ligne);
		}
		*/
/*		System.out.println(JDBC_DUNNIDOO.SELECT("SELECT COUNT(*) FROM DUNNING_PLANIFICATION"));
		
		
		for (SEGMENT segment : SEGMENT.values()) 
		{
			System.out.println(segment.getLibelle());
			for (Customer customer : segment.getCustomers())
			{
				System.out.println(customer.getCclName());
			}
		}
		
		
		
		System.out.println(PLANIFICATOR.numberOfCustomersPerSegment());
		System.out.println(PLANIFICATOR.debtsPerSegment());
		System.out.println(PLANIFICATOR.debtsPerMonths());
		System.out.println("Done !");
		
	
		JDBC_DUNNIDOO.UPDATE("DELETE FROM DUNNING_PLANIFICATION");
		JDBC_DUNNIDOO.UPDATE("DELETE FROM DUNNING_ELIGIBLE_CUSTOMERS");
	*/
		/*
		System.out.println("--------------IDs :");

		for (Planification p : PLANIFICATOR.getPlanifications())
		{
			System.out.println(p.getPlanificationId());
		}
		
		*/
		/*
 * 
 * 
		Customer client = new Customer("8" ,"001", 83697293 , "ONHOLD" , SEGMENT.VIP , 8000 , (float)6000.0 ,
				LocalDate.of(2018, Month.JUNE,3) ,
				LocalDate.of(2018, Month.MAY,25), "AREZKI" , 
		"0555555555","geronimotoutcourt@gmail.com", (Sam)PLANIFICATOR.getUser("abellebcir@ooredoo.dz"));
	*/ /*	
		Alerte  a = new Alerte(client, MOTIF.PROBLEME_FACTURATION);
		a.setNiveau(NIVEAU.NIVEAU2);
		for (USER intervenant : a.IntervenantsList())
		{
			System.out.println(intervenant.getUserName()+" - "+intervenant.getUserProfile());
		}
		System.out.println();
		System.out.println(a.responsable().getUserName()+" - "+a.responsable().getUserProfile());
*//*
		((Sam)PLANIFICATOR.getUser("abellebcir@ooredoo.dz")).triggerAlert(client, MOTIF.PROBLEME_FACTURATION);
		System.out.println("--------------------");
		System.out.println(client.getAlerte().buildAlertMail().getSubject());
		System.out.println("--------------------");
		System.out.println(client.getAlerte().getMotifDeclenchement());
		System.out.println(client.getAlerte().getDateDeclenchement());
		System.out.println(client.getAlerte().getDateCloture());
		System.out.println(client.getAlerte().getPeriode());
		System.out.println(client.getAlerte().getNiveau());
		System.out.println(client.getAlerte().getCustomer().getCclName());
		System.out.println(client.getAlerte().getCustomer().getStatus());
		System.out.println("--------------------");
		System.out.println(client.getAlerte().buildAlertMail().getBody());
		System.out.println("--------------------");
		System.out.println(client.getAlerte().buildAlertMail().send());
		System.out.println("--------------------");
		for (USER user : client.getAlerte().IntervenantsList())
		{
			for (Notification notif : user.getNotifications()) 
			{
				System.out.println(notif.getContent());
			}
		}*/
		
		Calendar calendar = Calendar.getInstance();
		
//		Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());

	/*	calendar.set(Calendar.HOUR_OF_DAY, 20);
		calendar.set(Calendar.MINUTE, 49);
		calendar.set(Calendar.SECOND, 10);

		Date time = calendar.getTime();
		Timer timer = new Timer();
		
		timer.schedule(new FirstTask(), time);
		
		System.out.println("Niporte quel Truc");
		*/
		
		JDBC_DUNNIDOO.UPDATE("DELETE FROM DUNNING_PLANIFICATION");
		JDBC_DUNNIDOO.UPDATE("DELETE FROM DUNNING_ELIGIBLE_CUSTOMERS");
		
		PLANIFICATOR.dunningListExtraction();
		
		System.out.println(PLANIFICATOR.numberOfCustomersPerSegment());
		System.out.println(PLANIFICATOR.debtsPerSegment());
		System.out.println(PLANIFICATOR.debtsPerMonths());
		System.out.println("Done !");
		
		
		JDBC_DUNNIDOO.UPDATE("DELETE FROM DUNNING_PLANIFICATION");
		JDBC_DUNNIDOO.UPDATE("DELETE FROM DUNNING_ELIGIBLE_CUSTOMERS");
		
		JSON_EXPORTER.exportHistogramme();
		JSON_EXPORTER.exportGraphe();
		JSON_EXPORTER.exportCamambert();
		
		JSON_EXPORTER.exportMapValuesAsList(PLANIFICATOR.debtsPerSegment(), "export1");
		JDBC_DUNNIDOO.CLOSE();
	}
}
                       