package dun.dunnidoo2.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JDBC_DUNNIDOO
{
	//DB parameters
	public static String DRIVER = "oracle.jdbc.driver.OracleDriver";
	public static String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	public static String USER = "MEHDI";
	public static String PASSWORD= "pwd";
	
	//CONNECTION
	private static Connection CONNECTION;
	
	//DB Queries
	public static void CONNECT()
	{
		if(CONNECTION == null)
		{
			try {
				Class.forName(DRIVER);
				CONNECTION = DriverManager.getConnection(URL, USER, PASSWORD);
			} catch (ClassNotFoundException | SQLException e) {

				e.printStackTrace();
			}
		}

	}
	
	public static ArrayList<ArrayList> SELECT(String query)
	{
		 try {		         
		      //Création d'un objet Statement
		      Statement state = CONNECTION.createStatement();
		      //L'objet ResultSet contient le résultat de la requête SQL
		      ResultSet result = state.executeQuery(query);
		    //On récupère les MetaData
		        ResultSetMetaData resultMeta = result.getMetaData();
	   
		        ArrayList<ArrayList> alal = new ArrayList<ArrayList>();
		        while(result.next()) {  

		        	ArrayList al = new ArrayList<>();
		          for(int i = 1; i <= resultMeta.getColumnCount(); i++) {
		        	  
		        	  al.add(result.getObject(i));
		          }
		         alal.add(al) ;
		        }

		          result.close();
		          state.close();
		          return alal;
		             
		        } 
		        catch (Exception e) {
		        	
		          e.printStackTrace();
		          return null;
		        }  
	}
	
	public static void UPDATE(String query)
	{
		 try {
		      /*INSERT*/

		      Statement st = CONNECTION.createStatement(); 
		      st.executeUpdate(query); 
		      st.close();

		         
		    } catch (Exception e) {
		      e.printStackTrace();
		    } 
	}
	
	public static void CLOSE(ResultSet result)
	{
	      try {
			result.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void CLOSE(Statement statement) 
	{
		try {
			statement.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}
	
	public static void CLOSE()
	{
		try {
			CONNECTION.close();
			CONNECTION = null;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

	//

}
