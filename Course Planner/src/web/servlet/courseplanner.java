package web.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Request;

import web.model.Data;
import web.model.Quarter;
import web.model.QuarterPlan;


@WebServlet("/courseplanner")
public class courseplanner extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public courseplanner() {
        super();
        
    }
	public void init(ServletConfig config) throws ServletException
	{
		super.init( config );
		
		
	}
	 
    	 
    	 
     
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		List<Data> sub=new ArrayList<Data>();
		  Connection c = null;
	        try
	        {
	        	Class.forName( "com.mysql.jdbc.Driver" );
	            String url = "jdbc:mysql://localhost/cs320stu51";
	            String username = "root";
	            String password = "root";

	            c = DriverManager.getConnection( url, username, password );
	            Statement stmt = c.createStatement();
	            ResultSet rs = stmt.executeQuery( "select * from courses" );

	            while( rs.next() )
	            {
	               Data entry = new Data( rs.getInt( "id" ),
	                    rs.getString( "code" ), rs.getString( "name" ),rs.getString( "prereq" ) );
	                sub.add( entry );
	            }
	        }
	        catch( SQLException | ClassNotFoundException e )
	        {
	            throw new ServletException( e );
	        }
	        finally
	        {
	            try
	            {
	                if( c != null ) c.close();
	            }
	            catch( SQLException e )
	            {
	                throw new ServletException( e );
	            }
	        }
     request.getSession().setAttribute("Sub_entries", sub);
		request.getRequestDispatcher( "/WEB-INF/Courseplanner.jsp" ).forward(
	            request, response );
		
	}

	
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		List<QuarterPlan> q=new ArrayList<QuarterPlan>();
		List<Data> data=(List<Data>)request.getSession().getAttribute("Sub_entries");
		List<Data> already=new LinkedList<Data>();
				
		
		Calendar cal = Calendar.getInstance(TimeZone.getDefault());	
		int id=0;
		QuarterPlan qy=new QuarterPlan(id,data);
		qy.setWeek(cal.get(Calendar.WEEK_OF_YEAR)+12);
		qy.setYear(cal.get(Calendar.YEAR));
		if(request.getParameterValues("cp")==null)
		{
			List<String> empty=new ArrayList<String>();
			qy.setSelect(qy.get(empty));
			qy.setSub(new ArrayList<String>());
			already.addAll(new ArrayList<Data>());
			q.add(qy);
			request.getSession().setAttribute("already", already);
			request.getSession().setAttribute("Quart",q);
			 response.sendRedirect("next?name="+qy.getNextquarter()+"");
			
			return;
		}
		List<String> course=new LinkedList<String>(Arrays.asList(request.getParameterValues("cp")));
		for(Data d:data)
		{
			if(course.contains(d.getCode()))
			{
				already.add(d);
			}
		}
		qy.setSub(course);
		request.getSession().setAttribute("already", already);
	 if(qy.get(course).size()!=0)
	 {
		qy.setSelect(qy.get(course));
		q.add(qy);
		request.getSession().setAttribute("Quart",q);
		 response.sendRedirect("next?name="+qy.getNextquarter()+"&id=1");
		 return;
	 }
	 else
	 {
		 response.sendRedirect("finish?error=1");
	 }
	 
	 
	
	}

}
