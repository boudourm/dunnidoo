package dun.dunnidoo2.model;

import java.util.ArrayList;
import java.util.List;

import javax.mail.search.ReceivedDateTerm;

public class Mail {
	// Attributes
	private String subject;
	private String body;

	// Associations
	private USER sender;
	private List<USER> recievers = new ArrayList<USER>();;

	// Constructor
	public Mail(String subject, String body, USER sender, List<USER> recievers) 
	{
		super();
		this.subject = subject;
		this.body = body;
		this.sender = sender;
		this.recievers = recievers;
		
		//Associations
		for (USER user : recievers) {user.addInbox(this);}
		sender.addSent(this);
	}

	// Getters & Setters
	public String getSubject() {return subject;}
	public void setSubject(String subject) {this.subject = subject;}
	public String getBody() {return body;}
	public void setBody(String body) {this.body = body;}
	public USER getSender() {return sender;}
	public void setSender(USER sender) {this.sender.removeSent(this);
										this.sender = sender;
										sender.addSent(this);}
	public List<USER> getRecievers() {return recievers;}
	public void setRecievers(List<USER> recievers) {//for (USER user : recievers) {
														//user
													//}
													this.recievers = recievers;}	

	// Instance Methods
	
	public boolean send ()
	{
		return PLANIFICATOR.getEsb().sendMail(this);
	}
	
	public void personnalize(USER sender, List<USER> Recievers , String subject ,String body)
	{
		setBody(body);
		setRecievers(Recievers);
		setSender(sender);
		setSubject(subject);
	}
	

	// Association Methods
	public boolean addReceiver(USER user)
	{
		return this.getRecievers().add(user);
	}
	public boolean removeReceiver(USER user)
	{
		return this.getRecievers().remove(user);
	}
	
	// Overridden Methods

}
