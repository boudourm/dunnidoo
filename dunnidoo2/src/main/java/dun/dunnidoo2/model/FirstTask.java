package dun.dunnidoo2.model;

import java.util.TimerTask;

public class FirstTask extends TimerTask
{
	
	@Override
	public void run()
	{	
		//System.out.println("First Task Execution");
	
	JDBC_DUNNIDOO.UPDATE("DELETE FROM DUNNING_PLANIFICATION");
	JDBC_DUNNIDOO.UPDATE("DELETE FROM DUNNING_ELIGIBLE_CUSTOMERS");
	
	PLANIFICATOR.dunningListExtraction();
	
	System.out.println(PLANIFICATOR.numberOfCustomersPerSegment());
	System.out.println(PLANIFICATOR.debtsPerSegment());
	System.out.println(PLANIFICATOR.debtsPerMonths());
	System.out.println("Done !");
	
	
	JDBC_DUNNIDOO.UPDATE("DELETE FROM DUNNING_PLANIFICATION");
	JDBC_DUNNIDOO.UPDATE("DELETE FROM DUNNING_ELIGIBLE_CUSTOMERS");
	}
}
