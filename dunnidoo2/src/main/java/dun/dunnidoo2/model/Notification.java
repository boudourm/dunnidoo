package dun.dunnidoo2.model;

import java.util.ArrayList;
import java.util.List;

public class Notification {
	
	//Attributes
	private String content;

	//Associations
	private List<USER> Recievers  = new ArrayList<USER>();

	
	//Constructor
	public Notification(String content, List<USER> recievers) {
		super();
		this.content = content;
		Recievers = recievers;
		//Associations
		for (USER user : recievers) {user.addNotification(this);}
	}

	//Getters & Setters
	public String getContent() {return content;}
	public void setContent(String content) {this.content = content;}
	public List<USER> getRecievers() {return Recievers;}
	public void setRecievers(List<USER> recievers) {Recievers = recievers;}	

	//Instance Methods
	
	public static Notification newNotification (String content , List<USER> recievers)
	{
		return null;
	}

	public void notifying()
	{
		for (USER user : Recievers) 
		{
			user.addNotification(this);
		}
	}
	//Association Methods
	
	public boolean addReciever(USER reciever){return this.getRecievers().add(reciever);}
	public boolean removeReciever(USER reciever){return this.getRecievers().remove(reciever);}
	
	//Overridden Methods
	@Override
	public String toString() 
	{
		return "Notification [\ncontent=" + content + ", \nRecievers=" + Recievers + "]";
	}


}
