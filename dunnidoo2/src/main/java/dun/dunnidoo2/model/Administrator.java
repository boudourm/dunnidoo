	package dun.dunnidoo2.model;

import java.util.List;

public class Administrator extends USER
{

	//Constructor
	public Administrator(String userId, String userName, String password, String email, String firstName,
			String lastName, USER_PROFILE userProfile) {
		super(userId, userName, password, email, firstName, lastName, userProfile);
	}

	//Instance Methods
	public void validateAlertExtension(Alerte a)
	{
		//TODO
	}
	public void rejectAlertExtension()
	{
		//TODO
	}
	public void triggerAlert()
	{
		//TODO
	}
	
	public void personalizeMail(Mail mail)
	{
		//TODO
	}
	public void interruptAlert(Alerte a)
	{
		//TODO
	}
	
	public boolean addUser(USER user)
	{
		//TODO
		return false;
	}
	
	public boolean removeUser(USER user)
	{
		//TODO
		return false;
	}
	//Overridden Methods
	
}
