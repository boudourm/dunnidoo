package dun.dunnidoo2.model;

public enum ACTION
{
	//Objects
	SMS1("SMS1" , 1 , "target"),
	SMS2("SMS2" , 2 , "target"),
	SMS3("SMS3" , 3 , "target"),
	SMS4("SMS4" , 4 , "target"),
	SMS5("SMS5" , 5 , "target"),
	SMS6("SMS6" , 6 , "target"),
	SMS7("SMS7" , 7 , "target"),
	SMS8("SMS8" , 8 , "target"),
	MAIL_RELANCE("MAIL_RELANCE" , 9 , "target"),
	MAIL_DESACTIVATION("MAIL_DESACTIVATION" , 10 , "target"),
	BAROUT("BAROUT" , 11 , "fleet"),
	BARIN("BARIN" , 12 , "fleet"),
	DESACTIVATION("DESACTIVATION" , 13 , "fleet"),
	IVR1("IVR1" , 14 , "fleet"),
	IVR2("IVR2" , 15 , "fleet"),
	OUTGOING_CALL("OUTGOING_CALL", 16 , "target");
	
	
	//Attributes
	private  String actionName;
	private Integer actionId;
	private String typeRelance;
		
	
	//Constructor
	private ACTION(String actionName, Integer actionId, String typeRelance) 
	{
		this.actionName = actionName;
		this.actionId = actionId;
		this.typeRelance = typeRelance;
	}

		
	//Getters & Setters
	public String getActionName() {return actionName;}
	public void setActionName(String actionName) {this.actionName = actionName;}
	public Integer getActionId() {return actionId;}
	public void setActionId(Integer actionId) {	this.actionId = actionId;}
	public String getTypeRelance() {return typeRelance;}
	public void setTypeRelance(String typeRelance) {this.typeRelance = typeRelance;}	
	
}
