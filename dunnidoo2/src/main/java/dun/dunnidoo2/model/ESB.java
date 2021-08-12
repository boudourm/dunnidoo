package dun.dunnidoo2.model;

import java.util.List;
import java.util.Map;

public interface ESB
{
	//Urls
	public String getDunningEligibleCustomersWebServicesUrl = "";
	public String sendMailWebServicesUrl = "";
	public String getFleetWebServicesUrl = "";
	public String updateCustomersDataWebServicesUrl = "";
	
	//Behaviors
	public void getDunningEligibleCustomers();
	public boolean sendMail(Mail mail);
	public Map<String,String> getFleet(Customer customer);
	public void updateCustomersData();
	
}
