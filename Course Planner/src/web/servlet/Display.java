package web.servlet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.model.Data;
@WebServlet(urlPatterns ="/Display",loadOnStartup = 1)
public class Display extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public Display() {
        super();
    }
    @SuppressWarnings("unused")
	public void init(ServletConfig config) throws ServletException
    {
    	super.init( config );
    	
    	  
    	  
    	try
		{
    		 Class.forName( "com.mysql.jdbc.Driver" );
			
			

	        
		}
    	catch( ClassNotFoundException e )
        {
            throw new ServletException( e );
        }
		
    
    	
		
    	
  }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
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
       request.setAttribute("Sub_entries", sub);
		request.getRequestDispatcher( "/WEB-INF/Display.jsp" ).forward(
	            request, response );
		
	
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
