package dun.dunnidoo2.model;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public final class EsbTibco implements ESB
{

	
	//WebServices
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void getDunningEligibleCustomers() 
	{

		/*WEB SERVICE INVOCATION*/
		
		/*DATA EXEMPLE*/
		ArrayList<ArrayList> customers = new ArrayList<ArrayList>();
		customers.add(new ArrayList<Comparable>(Arrays.asList
				(1 ,"001", 83697293 , "ONHOLD" , "SOHO" , 8000 , 6000.0 ,
						new SimpleDateFormat("dd/MM/yyyy").format(Date.valueOf(LocalDate.of(2018, Month.JUNE,3))) ,
						new SimpleDateFormat("dd/MM/yyyy").format(Date.valueOf(LocalDate.of(2018, Month.MAY,25))), "AREZKI_SOHO" , 
						"0555555555","geronimotoutcourt@gmail.com" , "mboudour@ooredoo.dz")));
		customers.add(new ArrayList<Comparable>(Arrays.asList
				(2 ,"001", 83697293 , "ONHOLD" , "SME" , 8000 , 6000.0 ,
						new SimpleDateFormat("dd/MM/yyyy").format(Date.valueOf(LocalDate.of(2018, Month.JUNE,3))) ,
						new SimpleDateFormat("dd/MM/yyyy").format(Date.valueOf(LocalDate.of(2018, Month.MAY,25))), "AREZKI_SME" , 
						"0555555555","geronimotoutcourt@gmail.com", "mboudour@ooredoo.dz")));
		customers.add(new ArrayList<Comparable>(Arrays.asList
				(3 ,"001", 83697293 , "ONHOLD" , "LARGE" , 8000 , 6000.0 ,
						new SimpleDateFormat("dd/MM/yyyy").format(Date.valueOf(LocalDate.of(2018, Month.JUNE,3))) ,
						new SimpleDateFormat("dd/MM/yyyy").format(Date.valueOf(LocalDate.of(2018, Month.MAY,25))), "AREZKI_LARGE" , 
						"0555555555","geronimotoutcourt@gmail.com", "mboudour@ooredoo.dz")));
		customers.add(new ArrayList<Comparable>(Arrays.asList
				(4 ,"001", 83697293 , "ONHOLD" , "KEY" , 8000 , 6000.0 ,
						new SimpleDateFormat("dd/MM/yyyy").format(Date.valueOf(LocalDate.of(2018, Month.JUNE,3))) ,
						new SimpleDateFormat("dd/MM/yyyy").format(Date.valueOf(LocalDate.of(2018, Month.MAY,25))), "AREZKI_KEY" , 
						"0555555555","geronimotoutcourt@gmail.com", "mboudour@ooredoo.dz")));
		customers.add(new ArrayList<Comparable>(Arrays.asList
				(5 ,"001", 83697293 , "ONHOLD" , "SPECIAL_KEY" , 8000 , 6000.0 ,
						new SimpleDateFormat("dd/MM/yyyy").format(Date.valueOf(LocalDate.of(2018, Month.JUNE,3))) ,
						new SimpleDateFormat("dd/MM/yyyy").format(Date.valueOf(LocalDate.of(2018, Month.MAY,25))), "AREZKI_SPECIAL_KEY" , 
				"0555555555","geronimotoutcourt@gmail.com", "abellebcir@ooredoo.dz")));
		customers.add(new ArrayList<Comparable>(Arrays.asList
						(6 ,"001", 83697293 , "ONHOLD" , "VIP" , 8000 , 6000.0 ,
								new SimpleDateFormat("dd/MM/yyyy").format(Date.valueOf(LocalDate.of(2018, Month.JUNE,3))) ,
								new SimpleDateFormat("dd/MM/yyyy").format(Date.valueOf(LocalDate.of(2018, Month.MAY,25))), "AREZKI_VIP" , 
						"0555555555","geronimotoutcourt@gmail.com", "abellebcir@ooredoo.dz")));
		customers.add(new ArrayList<Comparable>(Arrays.asList
						(7 ,"001", 83697293 , "ONHOLD" , "DISTRIBUTEUR" , 8000 , 6000.0 ,
								new SimpleDateFormat("dd/MM/yyyy").format(Date.valueOf(LocalDate.of(2018, Month.JUNE,3))) ,
								new SimpleDateFormat("dd/MM/yyyy").format(Date.valueOf(LocalDate.of(2018, Month.MAY,25))), "AREZKI_DISTRIBUTEUR" , 
						"0555555555","geronimotoutcourt@gmail.com", "abellebcir@ooredoo.dz")));
		customers.add(new ArrayList<Comparable>(Arrays.asList
						(8 ,"001", 83697293 , "ONHOLD" , "OS" , 8000 , 6000.0 ,
								new SimpleDateFormat("dd/MM/yyyy").format(Date.valueOf(LocalDate.of(2018, Month.JUNE,3))) ,
								new SimpleDateFormat("dd/MM/yyyy").format(Date.valueOf(LocalDate.of(2018, Month.MAY,25))), "AREZKI_OS" , 
						"0555555555","geronimotoutcourt@gmail.com", "abellebcir@ooredoo.dz")));
		
		/*INSERT*/
		for (ArrayList ligne : customers)
		{
			
			String query = "INSERT INTO DUNNING_ELIGIBLE_CUSTOMERS VALUES \r\n" + 
			
		      		"( "+ligne.get(0)+" , \r\n" + 
		      		" '"+ligne.get(1)+"' , \r\n" + 
		      		" '"+ligne.get(2)+"' ,\r\n" + 
		      		" '"+ligne.get(3)+"' ,\r\n" + 
		      		" '"+ligne.get(4)+"' ,\r\n" + 
		      		"  "+ligne.get(5)+" ,\r\n" + 
		      		" "+ligne.get(6)+" , \r\n" + 
		      		"to_date('"+ligne.get(7)+"' , 'DD/MM/YYYY' )  ,\r\n" + 
		      		" to_date('"+ligne.get(8)+"' , 'DD/MM/YYYY' ) ,\r\n" + 
		      		" '"+ligne.get(9)+"' , \r\n" + 
		      		" '"+ligne.get(10)+"' ,\r\n" + 
		      		" '"+ligne.get(11)+"' ,\r\n" + 
		      		" '"+ligne.get(12)+"'\r\n" + 
		      		" )\r\n" + 
		      		"";
			JDBC_DUNNIDOO.UPDATE(query);
		}

	}

	@Override
	public boolean sendMail(Mail mail) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public Map<String,String> getFleet(Customer customer) {
		//WEB SERVICE
		
		//CO_ID MSISDN
		Map<String,String> resultat = new TreeMap<String,String>();
		for (int i = 0; i < 10; i++)
		{
			resultat.put(customer.getCoId()+""+i, "055555555"+i);
		}
		return resultat;
	}

	@Override
	public void updateCustomersData() {
		// TODO Auto-generated method stub
		
	}

	
}
