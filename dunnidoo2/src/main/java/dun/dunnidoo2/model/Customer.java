package dun.dunnidoo2.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Customer
{
	//Attributes
	private String customerId;
	private String custCode;
	private Integer coId;
	private String status;
	private SEGMENT segment;
	private Integer invoiceAmout;
	private Float openAmount;
	private LocalDate dueDate;
	private LocalDate lbcDate;
	private String cclName;
	private String ccfName;
	private String msisdn;
	private String ccEmail;
	private String corporateName;
	private boolean isFlat ;

	
	//Associations
	//segment declaré plus haut
	private List<Planification> planififcations = new ArrayList<Planification>();
	private Sam sam;
	private Alerte alerte;
	

	//Constructor
	public Customer(String customerId, String custCode, Integer coId, String status, String segment,
			Integer invoiceAmout, Float openAmount, LocalDate dueDate, LocalDate lbcDate, String cclName, String msisdn,
			String ccEmail, String samMail)
	{
		this( customerId,  custCode,  coId,  status, SEGMENT.valueOf(segment) ,
				 invoiceAmout,  openAmount,  dueDate,  lbcDate,  cclName,  msisdn,
				 ccEmail, (Sam)PLANIFICATOR.getUser(samMail) );
		
	}
	
	//Fleet Constructor
	public Customer(String customerId, String custCode, Integer coId, String status, SEGMENT segment,
			Integer invoiceAmout, Float openAmount, LocalDate dueDate, LocalDate lbcDate, String cclName, String msisdn,
			String ccEmail, Sam sam , boolean fleet)
	{
		this.customerId = customerId;
		this.custCode = custCode;
		this.coId = coId;
		this.status = status;
		this.segment = segment;
		this.invoiceAmout = invoiceAmout;
		this.openAmount = openAmount;
		this.dueDate = dueDate;
		this.lbcDate = lbcDate;
		this.cclName = cclName;
		this.msisdn = msisdn;
		this.ccEmail = ccEmail;
		this.sam = sam;
		this.setFlat(true);
		this.getSam().addCustomer(this);
		segment.addCustomer(this);

		
	}
	
	public Customer(String customerId, String custCode, Integer coId, String status, SEGMENT segment,
			Integer invoiceAmout, Float openAmount, LocalDate dueDate, LocalDate lbcDate, String cclName, String msisdn,
			String ccEmail, Sam sam) {
		super();
		this.customerId = customerId;
		this.custCode = custCode;
		this.coId = coId;
		this.status = status;
		this.segment = segment;
		this.invoiceAmout = invoiceAmout;
		this.openAmount = openAmount;
		this.dueDate = dueDate;
		this.lbcDate = lbcDate;
		this.cclName = cclName;
		this.msisdn = msisdn;
		this.ccEmail = ccEmail;
		this.sam = sam;
		//Associations
		this.getSam().addCustomer(this);
		segment.addCustomer(this);
	}
	

	//Getters & Setters
	public String getCustomerId() {return customerId;}
	public void setCustomerId(String customerId) {this.customerId = customerId;}
	public String getCustCode() {return custCode;}
	public void setCustCode(String custCode) {this.custCode = custCode;}
	public Integer getCoId() {return coId;}
	public void setCoId(Integer coId) {this.coId = coId;}
	public Integer getInvoiceAmout() {return invoiceAmout;}
	public void setInvoiceAmout(Integer invoiceAmout) {this.invoiceAmout = invoiceAmout;}
	public Float getOpenAmount() {return this.openAmount;}
	public void setOpenAmount(Float openAmount) {this.openAmount = openAmount;}
	public LocalDate getDueDate() {return dueDate;}
	public void setDueDate(LocalDate dueDate) {this.dueDate = dueDate;}
	public LocalDate getLbcDate() {return lbcDate;}
	public void setLbcDate(LocalDate lbcDate) {this.lbcDate = lbcDate;}
	public String getCclName() {return cclName;}
	public void setCclName(String cclName) {this.cclName = cclName;}
	public String getMsisdn() {return msisdn;}
	public void setMsisdn(String msisdn) {this.msisdn = msisdn;}
	public String getCcEmail() {return ccEmail;}
	public void setCcEmail(String ccEmail) {this.ccEmail = ccEmail;}
	public SEGMENT getSegment() {return segment;}
	public void setSegment(SEGMENT segment) {this.segment.removeCustomer(this);
											 this.segment = segment;
											 segment.addCustomer(this);}
	public List<Planification> getPlanififcations() {return planififcations;}
	public void setPlanififcations(List<Planification> planififcations) {for (Planification planification : this.planififcations) {
																			planification.setCustomer(null);
																		}
																		this.planififcations = planififcations;
																		for (Planification planification : planififcations) {
																			planification.setCustomer(this);
																		}}
	public Sam getSam() {return sam;}
	public void setSam(Sam sam) {this.sam.removeCustomer(this);
								 this.sam = sam;
								 sam.addCustomer(this);}
	public Alerte getAlerte() {return alerte;}
	public void setAlerte(Alerte alerte) {//this.alerte.setCustomer(null);
											this.alerte = alerte;
											//alerte.setCustomer(this);
											}
	public String getStatus() {return status;}
	public void setStatus(String status) {this.status = status;

										  JDBC_DUNNIDOO.UPDATE("UPDATE DUNNING_ELIGIBLE_CUSTOMERS SET STATUS = '"+status+"' WHERE CUSTOMER_ID = "+this.customerId+" ");
	}
	public String getCorporateName() {return corporateName;}
	public void setCorporateName(String corporateName) {this.corporateName = corporateName;}
	public String getCcfName() {return ccfName;}
	public void setCcfName(String ccfName) {this.ccfName=ccfName;}
	public boolean isFlat() {return isFlat;}
	public void setFlat(boolean isFlat) {System.out.println("setFlat() execution avant value = "+isFlat);
										this.isFlat = isFlat;
										System.out.println("setFlat() execution après value = "+this.isFlat);}
	
	//Instance Methods
	public void inDunning()
	{
		this.setStatus("INDUNNING");
	}
	
	public void inException()
	{
		this.setStatus("EXCEPTION");
	}
	
	public void hasPaid()
	{
		setStatus("PAID");
	}
	
	public Map<String,String> getFleet()
	{
		return PLANIFICATOR.getEsb().getFleet(this);
	}

	public List<Planification> plannedPlanifications()
	{
		ArrayList<Planification> resultat = new ArrayList<Planification>();
		for (Planification planification : resultat) 
			if(planification.getStatus().equalsIgnoreCase("PLANNED"))
				resultat.add(planification);
		return resultat;
	}
	//Associations Methods
	public boolean addPlanification(Planification planification){planification.setCustomer(this); return this.getPlanififcations().add(planification);}
	public boolean removePlanification(Planification planification){planification.setCustomer(null); return this.getPlanififcations().remove(planification);}
	
	//Overridden Methods

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", \ncustCode=" + custCode + ", \ncoId=" + coId + ", invoiceAmout="
				+ invoiceAmout + ", \nopenAmount=" + openAmount + ", \ndueDate=" + dueDate + ", \nlbcDate=" + lbcDate
				+ ", \ncclName=" + cclName + ", \nmsisdn=" + msisdn + ", \nccEmail=" + ccEmail + ", \nsegment=" + segment
				+ ", \nplanififcations=" + planififcations + ", \nsam=" + sam + ", \nalerte=" + alerte + "]";
	}
		
}
