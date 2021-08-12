package dun.dunnidoo2.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Relance
{
	//Attributes
	private Long ageFacture;
	
	//Associations
	private ACTION action;
	
	//Constructor
	public Relance(Long ageFacture, ACTION action)
	{
		super();
		this.ageFacture = ageFacture;
		this.action = action;
	}

	//Getters & Setters
	public Long getAgeFacture() {return ageFacture;}
	public void setAgeFacture(Long ageFacture) {this.ageFacture = ageFacture;}
	public ACTION getAction() {	return action;	}
	public void setAction(ACTION action) {this.action = action;}

	
	//Instance Methods
	public Planification instantPlanification(Customer  client)
	{

		
		return new Planification(this.getAction(), client, LocalDate.now().plus(this.getAgeFacture(),ChronoUnit.DAYS));
	}
	
	public Planification customPlanififcation(Customer client , LocalDate debut)
	{
		return new Planification(this.getAction(), client, debut.plus(this.getAgeFacture(),ChronoUnit.DAYS));
	}

	
	//Overridden Methods
	@Override
	public String toString() {
		return "Relance \n[ageFacture=" + ageFacture + ",  \naction=" + action + "]";
	}
}
