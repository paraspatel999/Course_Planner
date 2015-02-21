package web.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuarterPlan {

	int id;
	String nextquarter;
	int year;
	Quarter currentquarter;
	List<Data> data;
	String nquarter;
	 public String getNquarter() {
		return nquarter;
	}


	public void setNquarter(String nquarter) {
		this.nquarter = nquarter;
	}
	List<Data> taken; 
 public QuarterPlan()
 {}
		List<Data> select;
		List<String> sub;
		int week;
	public int getId() {
		return id;
	}
	
	
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	
	public int getWeek() {
		return week;
	}
	public void setWeek(int week) {
		this.week = week;
	}
	public List<String> getSub() {
		return sub;
	}
	public void setSub(List<String> sub) {
		this.sub = sub;
	}

	
 
	public List<Data> getTaken() {
	return taken;
}
public void setTaken(List<Data> taken) {
	this.taken = taken;
}

	public QuarterPlan(int id,List<Data> data)
	
	{	
		this.id=id;
		this.data=data;		
	}
	
	public List<Data> getSelect() {
		return select;
	}
	public void setSelect(List<Data> select) {
		this.select = select;
	}
	public String getNextquarter() {
		
		Quarter q=new Quarter();
		
		q.setQuarter(week,year);
		
		return q.getQuarter();
	}
	public void setNextquarter(String nextquarter) {
		this.nextquarter = nextquarter;
	}
	public List<Data> getData() {
		return data;
	}
	public void setData(List<Data> data) {
		this.data= data;
	}
	public List<Data> get(List<String> demo)
	{
		List<Data> se=new ArrayList<Data>();
		
		L:for(Data d: data)
		{
			
			if(!demo.contains(d.getCode()))
			   if(d.getPrereq().equals(" "))
				{
					se.add(d);
				    continue L;
				}
				else
				{
					List<String> r =Arrays.asList(d.getPrereq().split(" "));
				     if(demo.containsAll(r))
				    	 se.add(d);
				     continue L;
				}
			}
		return se;
	}
	

	
	

	

	
	
}
