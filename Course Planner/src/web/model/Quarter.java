package web.model;



public class Quarter {

	String quarter;
    
   
	
	public Quarter()
	{
		
	}
	public String getQuarter() {
		return quarter;
	}

	public void setQuarter(int week,int year) {
		
		if(week <= 12)
          quarter="Winter "+year;		
		else if(week >12 && week<=24)
		  quarter="Spring "+year;
		else if(week>24 && week<=37)
		  quarter="Summer "+year;
		else
		{		   
		  quarter="Fall "+year;
		}
		
	}

	
	
}
