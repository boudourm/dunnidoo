package dun.dunnidoo2.model;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Planification
{
	//Attributes
	private String planificationId ;
	private LocalDate executionDate ;
	private String status;
	
	//Associations
	private ACTION action;
	private Customer customer;
	
	//Constructor
	public Planification(ACTION action,	Customer customer,LocalDate executionDate)
	{
		this(action,customer,executionDate,"PLANNED");
	}
	
	public Planification(ACTION action,	Customer customer,LocalDate executionDate, String status)
	{
		this("",executionDate,status,action,customer);
	}
	
	public Planification(String planificationId, LocalDate executionDate, String status, ACTION action,
			Customer customer) {
		super();
		this.planificationId = planificationId;
		this.executionDate = executionDate;
		this.status = status;
		this.action = action;
		this.customer = customer;
		//Association
		customer.addPlanification(this);
	}
	
	//Getters & Setters
	public String getPlanificationId() {return planificationId;}
	public void setPlanificationId(String planificationId) {this.planificationId = planificationId;}
	public LocalDate getExecutionDate() {return executionDate;}
	public void setExecutionDate(LocalDate executionDate) {this.executionDate = executionDate;
	 JDBC_DUNNIDOO.UPDATE("UPDATE DUNNING_PLANIFICATION SET EXECUTION_DATE = to_date('"+new SimpleDateFormat("dd/MM/yyyy").format(Date.valueOf(executionDate))+"' , 'DD/MM/YYYY' ) WHERE PLANIFICATION_ID = "+this.planificationId+" ");
	 }
	public String getStatus() {return status;}
	public void setStatus(String status) {this.status = status;
	  if(this.getAction().equals(ACTION.DESACTIVATION) && status.equalsIgnoreCase("SUCCESS"))
		  this.getCustomer().setStatus("DESACTIVATED");
	  JDBC_DUNNIDOO.UPDATE("UPDATE DUNNING_PLANIFICATION SET STATUS = '"+status+"' WHERE PLANIFICATION_ID = "+this.planificationId+" ");
}
	public ACTION getAction() {return action;}
	public void setAction(ACTION action) {this.action = action;}
	public Customer getCustomer() {return customer;}
	public void setCustomer(Customer customer) {this.customer = customer;}

	//Instance Methods
	public Planification newPlanification(Customer client , ACTION action)
	{
		//TODO
		return null;
	}
	
	public void extendExecutionDate(Long days)
	{
		this.setExecutionDate(this.getExecutionDate().plus(days , ChronoUnit.DAYS));
	}
	
	public void restrictExectionDate(Long days)
	{
		this.setExecutionDate(this.getExecutionDate().minus(days , ChronoUnit.DAYS));
	}
	
	public void resume(MOTIF motif)
	{
		this.setStatus("PLANNED");
		if(motif.equals(MOTIF.INTERRUPTION))
		this.resetExecutionDate();
		
	}
	
	public void cancel()
	{
		setStatus("CANCELED");
	}
	
	public void resetExecutionDate()
	{
		restrictExectionDate(this.getCustomer().getAlerte().getPeriode() - 
				ChronoUnit.DAYS.between(this.getCustomer().getAlerte().getDateDeclenchement(), 
						LocalDate.now()));
	}

	
	//Overidden Methods
	@Override
	public String toString() {
		return "Planification \n[planificationId=" + planificationId + ", \nexecutionDate=" + executionDate + ", \nstatus="
				+ status + ", \naction=" + action + ", \ncustomer=" + customer + "]";
	}
}
