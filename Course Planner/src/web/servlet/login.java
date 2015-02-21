package web.servlet;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.model.Data;


@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public login() {
        super();
    }
    

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		request.getRequestDispatcher( "/WEB-INF/Login.jsp" ).forward(
	            request, response );
			}

	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		 Connection c = null;
	        try
	        {
	            String url = "jdbc:mysql://localhost/cs320stu51";
	            String username = "root";
	            String password = "root";

	            c = DriverManager.getConnection( url, username, password );
	            /*Prepairedstatement stmt = c.createStatement();*/
	            String login="select * from users where user_name=? and password=?";
	          int cnt=0;
	          PreparedStatement stmt=c.prepareStatement(login);
	          stmt.setString(1,request.getParameter( "user_name" ).trim());
	          stmt.setString(2,request.getParameter( "password" ).trim());
	            ResultSet rs = stmt.executeQuery();
	            Data entry=null;
	            while(rs.next())
	            {
	            	cnt++;
                       entry= new Data( 
	                    rs.getString( "user_name" ), rs.getString( "password" ),rs.getString( "first_name" ),rs.getString( "last_name" ) );
	              
	            }
	            if(cnt==0)
	               {
	            	   response.sendRedirect( "login" );
	            	   return;
	               }
	            request.getSession().setAttribute( "user",entry.getUsername());
	            response.sendRedirect( "Display" );
	        }
	        catch( SQLException e )
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
		
		/*for(int i=0;i<auth.size();i++)
		{
			
		 if( request.getParameter( "user_name" ).trim().equals( auth.get(i).getUsername() )
		            && request.getParameter( "password" ).trim().equals( auth.get(i).getPassword() ) )
		        {
		            request.getSession().setAttribute( "user", auth.get(i).getUsername());
		            response.sendRedirect( "Display" );
		            return;
		        }
		        
	   }*/
		/* response.sendRedirect( "login" );*/
	}
}


