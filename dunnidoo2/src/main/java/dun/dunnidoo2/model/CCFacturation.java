package dun.dunnidoo2.model;

import java.util.List;

public class CCFacturation extends USER
{
	//Constructor
	public CCFacturation(String userId, String userName, String password, String email, String firstName,
			String lastName, USER_PROFILE userProfile) {
		super(userId, userName, password, email, firstName, lastName, userProfile);
	}
	
	//Instance Methods
	public void interruptAlert(Alerte a)
	{
		a.setClosingDoer(this);
		a.interrupt();
		PLANIFICATOR.interruptAlert(a);
		a.notifyInterruption();
	}
	
	//Overridden Methods
	@Override
	public String toString() {
		return "CCFacturation - "+super.toString();
	}
}
