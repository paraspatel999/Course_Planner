package web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.model.Data;


@WebServlet("/edit")
public class edit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public edit() {
        super();
        
    }
    private Data getEntry( Integer id )
    {
        @SuppressWarnings("unchecked")
		List<Data> entries = (List<Data>) getServletContext().getAttribute(
            "Sub_entries" );

        for( Data entry : entries )
            if( entry.getId()== id  ) return entry;

        return null;
    }

	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if((String)request.getSession().getAttribute("user")==null)
		  {
		       response.sendRedirect("login");
		       return;
		  }
		int id=Integer.parseInt(request.getParameter("id"));
		 Connection c = null;
		try
        {
			List<Data> d=new ArrayList<Data>();
			 //Class.forName( "com.mysql.jdbc.Driver" );
            String url = "jdbc:mysql://localhost/cs320stu51";
            String username = "root";
            String password = "root";

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
            List<String> p=new ArrayList<String>();
   		 p =Arrays.asList(d.get(id-1).getPrereq().split(" ")); 
   		 
   		 
   		 
   		 request.setAttribute("p", p);
   		 request.setAttribute("d", d.get(id-1));
   		 request.setAttribute("Sub_entries", d);
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
	   
		
		request.getRequestDispatcher( "/WEB-INF/edit.jsp" ).forward(
	            request, response );

		  
		}
	

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int code=Integer.parseInt(request.getParameter("id"));
		
		

		if((String)request.getSession().getAttribute("user")==null)
		  {
		       response.sendRedirect("login");
		       return;
		  }
		String[] prereq=request.getParameterValues("edit_sub");
		
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
            String url = "jdbc:mysql://localhost/cs320stu51";
            String username = "root";
            String password = "root";

            c = DriverManager.getConnection( url, username, password );
            String sql="update courses set code=?,name=?,prereq=? where id=?";
            PreparedStatement pr=c.prepareStatement(sql);
            pr.setString(1,request.getParameter("code").trim());
            pr.setString(2,request.getParameter("title").trim());
            pr.setString(3,pre);
            pr.setInt(4,code);
            pr.executeUpdate();
          
           
   		
   		 
   		 
   		 
   		 
        }
        catch( SQLException e)
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
	   
		
		
		 
	        
	response.sendRedirect("Display");
	}

}
