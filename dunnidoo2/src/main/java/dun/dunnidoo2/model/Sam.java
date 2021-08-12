package dun.dunnidoo2.model;

import java.util.ArrayList;
import java.util.List;

public class Sam extends USER 
{

	//Associations
	private List<Customer> outGoingCallCompaign = new ArrayList<Customer>();
	private List<Customer> customers = new ArrayList<Customer>(); 
	private Superieur superieur;
	
	//Constructor
	public Sam(String userId, String userName, String password, String email, String firstName, String lastName,
			USER_PROFILE userProfile) {
		
		this(userId, userName, password, email, firstName, lastName, userProfile, new ArrayList<Notification>(), new ArrayList<Mail>(),
				new ArrayList<Customer>(),new ArrayList<Customer>(),null);
	}
	
	public Sam(String userId, String userName, String password, String email, String firstName, String lastName,
			USER_PROFILE userProfile, List<Notification> notifications, List<Mail> mails,
			List<Customer> outGoingCallCompaign, List<Customer> customers, Superieur superieur) {
		super(userId, userName, password, email, firstName, lastName, userProfile);
		this.outGoingCallCompaign = outGoingCallCompaign;
		this.customers = customers;
		this.superieur = superieur;
		//Associations
		for (Customer customer : customers) {customer.setSam(this);}
		if(superieur != null)superieur.addSubordonne(this);
	}
	
	//Getters & Setters
	public List<Customer> getOutGoingCallCompaign() {return outGoingCallCompaign;}
	public void setOutGoingCallCompaign(List<Customer> outGoingCallCompaign) {this.outGoingCallCompaign = outGoingCallCompaign;}
	public List<Customer> getCustomers() {return customers;}
	public void setCustomers(List<Customer> customers) {this.customers = customers;}
	public Superieur getSuperieur() {return superieur;}
	public void setSuperieur(Superieur superieur) {this.superieur = superieur;}

	//Instance Methods
	public void triggerAlert(Customer customer , MOTIF motif)
	{
		if(this.getCustomers().contains(customer))
		{
			Alerte a = Alerte.newAlerte(customer, motif);
			PLANIFICATOR.addNewAlert(a);
			Mail m = a.buildAlertMail();
		//	personalizeMail(m);
			m.send();
			a.notifyTriggering();
		}
	}
	
	public void personalizeMail(Mail mail)
	{
		//TODO
	}
	
	//Association Methods
	public boolean addCustomer(Customer customer) {return this.getCustomers().add(customer);}
	public boolean addOutGoingCall(Customer customer){return this.getOutGoingCallCompaign().add(customer);}
	public boolean removeCustomer(Customer customer){return this.getCustomers().remove(customer);}	
	public boolean removeOutGoingCall(Customer customer){return this.getOutGoingCallCompaign().remove(customer);}

	//Overridden Methods
	@Override
	public String toString() {
		return "Sam "+super.toString()+"\n[outGoingCallCompaign=" + outGoingCallCompaign + ", \ncustomers=" + customers + "]";
	}
}
