package web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.model.Data;


@WebServlet("/add")
public class add extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 String url = "jdbc:mysql://localhost/cs320stu51";
     String username = "root";
     String password = "root";

    public add() {
        super();
       
    }
   
	
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		if((String)request.getSession().getAttribute("user")==null)
		  {
		       response.sendRedirect("login");
		       return;
		  }
		
		 Connection c = null;
		try
      {
			List<Data> d=new ArrayList<Data>();
			// Class.forName( "com.mysql.jdbc.Driver" );
         

          c = DriverManager.getConnection( url, username, password );
          Statement stmt = c.createStatement();
          ResultSet rs = stmt.executeQuery( "select * from courses" );
          Data entry=null;
          while( rs.next() )
          {
          	
              entry = new Data( rs.getInt( "id" ),
                  rs.getString( "code" ), rs.getString( "name" ),rs.getString( "prereq" ) );
             d.add(entry);
          }
         
 		 
 		 
 		
 		 request.setAttribute("Sub_entries", d);
      }
      catch( SQLException  e )
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
		request.getRequestDispatcher( "/WEB-INF/add.jsp" ).forward(
	            request, response );
				  
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		PrintWriter out=response.getWriter();
		
		if((String)request.getSession().getAttribute("user")==null)
		  {
		       response.sendRedirect("login");
		       return;
		  }
		String code=request.getParameter("code");
		String name=request.getParameter("title");
		
		String[] prereq=request.getParameterValues("add_sub");
		String pre="";
		if(prereq==null)
		{
			pre=" ";
		}
		else{
		
		for(int i=0;i<prereq.length;i++)
		{
			if(pre=="")
			    pre=prereq[i];
			else
				pre=pre+" "+prereq[i];
		}
		}
		Connection c = null;
		try
        {
			List<Data> d=new ArrayList<Data>();
			 //Class.forName( "com.mysql.jdbc.Driver" );
           
            c = DriverManager.getConnection( url, username, password );
            String sql = "insert into courses(code,name, prereq) values (?,?,?)";
            PreparedStatement pt=c.prepareStatement(sql);
           
   		 
            pt.setString( 1, code );
            pt.setString( 2,name);
            pt.setString( 3,pre);
            pt.executeUpdate();
            response.sendRedirect("Display");
   		
        }
        catch( SQLException  e )
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
		
		
		 
		
		 
		
	}

}
