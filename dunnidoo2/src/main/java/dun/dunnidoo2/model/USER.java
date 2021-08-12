package dun.dunnidoo2.model;

import java.util.ArrayList;
import java.util.List;

public class USER
{
	//Attributes
	private String userId;
	private String userName;
	private String password ;
	private String email;
	private String firstName;
	private String lastName;
	
	//Associations
	private USER_PROFILE userProfile;
	private List<Notification> notifications = new ArrayList<Notification>();
	private List<Mail> inbox = new ArrayList<Mail>();
	private List<Mail> sent = new ArrayList<Mail>();
	

	
	//Constructor
	public USER(String userId, String userName, String password, String email, String firstName, String lastName,
			String userProfile)
	{
		this( userId,  userName,  password,  email,  firstName,  lastName,
			  USER_PROFILE.valueOf(userProfile) ,new ArrayList<Notification>(), 
			  new ArrayList<Mail>(),new ArrayList<Mail>());	
	}
	public USER(String userId, String userName, String password, String email, String firstName, String lastName,
			USER_PROFILE userProfile)
	{
		this( userId,  userName,  password,  email,  firstName,  lastName,
			  userProfile,new ArrayList<Notification>(), 
			  new ArrayList<Mail>(),new ArrayList<Mail>());	
	}
	public USER(String userId, String userName, String password, String email, String firstName, String lastName,
			USER_PROFILE userProfile, List<Notification> notifications, List<Mail> inbox ,List<Mail> sent) 
	{
		super();
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userProfile = userProfile;
		this.notifications = notifications;
		this.inbox = inbox;
		this.sent = sent;
		//Associations
		for (Mail mail : inbox) {mail.addReceiver(this);}
		for (Mail mail : sent) {mail.setSender(this);}
		
	}
	
	//Getters & Setters
	public String getUserId() {return userId;}
	public void setUserId(String userId) {this.userId = userId;}
	public String getUserName() {return userName;}
	public void setUserName(String userName) {this.userName = userName;}
	public String getPassword() {return password;}
	public void setPassword(String password) {this.password = password;}
	public String getEmail() {return email;}
	public void setEmail(String email) {this.email = email;}
	public String getFirstName() {return firstName;}
	public void setFirstName(String firstName) {this.firstName = firstName;}
	public String getLastName() {return lastName;}
	public void setLastName(String lastName) {this.lastName = lastName;}
	public USER_PROFILE getUserProfile() {return userProfile;}
	public void setUserProfile(USER_PROFILE userProfile) {this.userProfile = userProfile;}
	public List<Notification> getNotifications() {return notifications;}
	public void setNotifications(List<Notification> notifications) {this.notifications = notifications;}
	public List<Mail> getInbox() {return inbox;}
	public void setInbox(List<Mail> inbox) {this.inbox = inbox;}
	public List<Mail> getSent() {return sent;}
	public void setSent(List<Mail> sent) {this.sent = sent;}
	
	//Instance Methods


	//Association Methods
	public boolean addInbox(Mail mail){return this.getInbox().add(mail);}
	public boolean removeInbox(Mail mail){return this.getInbox().remove(mail);}
	public boolean addSent(Mail mail){return this.getSent().add(mail);}
	public boolean removeSent(Mail mail){return this.getSent().remove(mail);}
	public boolean addNotification(Notification mail){return this.getNotifications().add(mail);}
	public boolean removeNotification(Notification mail){return this.getNotifications().remove(mail);}
	
	
	//Overridden Methods
	@Override
	public String toString() {
		return "USER \n[userId=" + userId + ", \nuserName=" + userName + ", \npassword=" + password + ", \nemail=" + email
				+ ", \nfirstName=" + firstName + ", \nlastName=" + lastName + ", \nuserProfile=" + userProfile
				+ ", \nnotifications=" + notifications + ", \nmails=" + inbox + "]";
	}
	
}
