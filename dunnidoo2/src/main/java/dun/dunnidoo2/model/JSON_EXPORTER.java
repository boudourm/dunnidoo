package dun.dunnidoo2.model;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.mapping.Array;

import com.google.gson.Gson;

public class JSON_EXPORTER //WRAPPER
{
	//Export An Array to JSON File
	public static void exportObject(Object input , String export)
	{
		Gson gson = new Gson();
		try{		
			PrintWriter writer = new PrintWriter(export+".json", "UTF-8");
			writer.print(gson.toJson(input));
			writer.close();
		}catch(Exception e){
			System.out.println("Error "+e.getMessage());
			
		}
	}
	
	public static void exportList(List input , String export)
	{
		exportObject((List)input, export);
	}
	
	public static void exportMap(Map input , String export)
	{
		exportObject((Map)input, export);
	}
	
	public static void exportMapValuesAsList(Map input , String export)
	{
		exportObject(new ArrayList(input.values()), export);
	}
	
	//Export for DASHBOARDS
	public static void exportGraphe()
	{
		exportMapValuesAsList(PLANIFICATOR.debtsPerMonths(), "src/main/webapp/assets/js/graphe");
	}
	
	public static void exportHistogramme()
	{
		exportMapValuesAsList(PLANIFICATOR.debtsPerSegment(), "src/main/webapp/assets/js/histogramme");
	}
	
	
	public static void exportCamambert()
	{
		exportMapValuesAsList(PLANIFICATOR.numberOfCustomersPerSegment(), "src/main/webapp/assets/js/camambert");
	}
	
	/*
	public void printSolution() {
		System.out.println();
		System.out.println("Consequence: ");
		/*for (int i=0;i<this.size();i++) {
			System.out.print(this.get(i).size()+" ");
			System.out.print("Route "+(i+1)+": ");
			for (int j=0;j<this.get(i).size();j++) {
				System.out.print(this.get(i).get(j).getID()+" ");
				
			}
			System.out.println();
		}*/
	/*	Gson gson = new Gson();
		System.out.println(gson.toJson(this.routes));
		Random r = new Random();
		int Low = 1;
		int High = 1000;
		int Result = r.nextInt(High-Low) + Low;
		
		try{
		
			PrintWriter writer = new PrintWriter("sol/the-file-name"+Result+".json", "UTF-8");
			writer.print(gson.toJson(this.routes));
			writer.close();
			
			
		}catch(Exception e){
			System.out.println("Error "+e.getMessage());
			
		}
		for (int i = 0; i < routes.size();i++){
			try{
			
				PrintWriter writer = new PrintWriter("sol/"+Result+"Ch"+i+".json", "UTF-8");
				writer.print(gson.toJson(this.routes.get(i)));
				writer.close();
				
				
			}catch(Exception e){
				System.out.println("Error "+e.getMessage());
				
			}
			
		}
		System.out.println();
	}*/
}
