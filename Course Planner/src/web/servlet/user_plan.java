package web.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.model.Data;
import web.model.QuarterPlan;

/**
 * Servlet implementation class user_plan
 */
@WebServlet("/user_plan")
public class user_plan extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public user_plan() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		List<QuarterPlan> q=new ArrayList<QuarterPlan>();
		
		List<String> saved_plan=new ArrayList<String>();
		  Connection c = null;
	        try
	        {
	        	Class.forName( "com.mysql.jdbc.Driver" );
	            String url = "jdbc:mysql://localhost/cs320stu51";
	            String username = "root";
	            String password = "root";
  
	            c = DriverManager.getConnection( url, username, password );
	            
	            if(request.getParameter("id")!=null)
	            {
	            
	            String sq="select distinct sq.quarter from student_quarterplan sq where sq.quarter_id=?";
	            PreparedStatement pt=c.prepareStatement(sq);
	            pt.setInt(1, Integer.parseInt(request.getParameter("id")) );
	              ResultSet rs2=pt.executeQuery();
	              while(rs2.next())
	              {
	            	 
	            		  
	            		  QuarterPlan qa=new QuarterPlan();
	            		  qa.setNquarter(rs2.getString("quarter"));
	            		  q.add(qa);
	            	
	            	  
	            	  
	              }
	              for(int i=0;i<q.size();i++)
	              {
	            	  List<Data> sub=new ArrayList<Data>();
	            	  String sq1="select c.id,c.code,c.name,c.prereq from student_quarterplan sq,courses c where sq.quarter=? and sq.quarter_id=? and sq.course_id=c.id ";
			            PreparedStatement pt1=c.prepareStatement(sq1);
			            pt1.setString(1, q.get(i).getNquarter() );
			            pt1.setInt(2, Integer.parseInt(request.getParameter("id")) );
			            ResultSet co=pt1.executeQuery();
			            while(co.next())
			            {
			            	
			            	sub.add(new Data( co.getInt(1),
	                    co.getString( "code" ), co.getString( "name" ),co.getString( "prereq" )));
			            	
			            }
			            q.get(i).setTaken(sub);
	            	  
	              }
	             
	           
	              request.setAttribute("Quart", q);
	              request.getRequestDispatcher( "/WEB-INF/Saved_plan.jsp" ).forward(
		    	            request, response );
	              return;
	              
	            	
	            }
	            String uname=(String)request.getSession().getAttribute("user");
	            Statement stmt = c.createStatement();
	            ResultSet rs=stmt.executeQuery("select * from users where user_name='"+uname+"'");
	            int a=0;
	            
	            while(rs.next())
	            {
	            	a=rs.getInt(1);
	            	
	            }
	            List<Integer> index=new ArrayList<Integer>();
	            Statement stmt1 = c.createStatement();
	            String sql="select * from quarterplan where user_id="+a+" order by date DESC" ;
	            ResultSet rs1=stmt1.executeQuery(sql);
	            
	            while(rs1.next())
	            {
	            	index.add(rs1.getInt(1));
	            	   saved_plan.add(rs1.getString("date"));
	            
	            }
	            request.setAttribute("Plans", saved_plan);
	            request.setAttribute("Ids", index);
	            request.getRequestDispatcher( "/WEB-INF/Saved_plan.jsp?dis=1" ).forward(
	    	            request, response );
	          
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
	        }}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<QuarterPlan> q=new ArrayList<QuarterPlan>();
		List<Data> sub=new ArrayList<Data>();
		List<String> saved_plan=new ArrayList<String>();
		
		 Connection c = null;
	        try
	        {
	        	Class.forName( "com.mysql.jdbc.Driver" );
	            String url = "jdbc:mysql://localhost/cs320stu51";
	            String username = "root";
	            String password = "root";

	            c = DriverManager.getConnection( url, username, password );
	            String uname=(String)request.getSession().getAttribute("user");
	            Statement stmt = c.createStatement();
	            ResultSet rs=stmt.executeQuery("select * from users where user_name='"+uname+"'");
	            int a=0;
	            int b=0;
	            while(rs.next())
	            {
	            	a=rs.getInt(1);
	            	
	            }
	            Statement stmt1 = c.createStatement();
	            String sql="select * from quarterplan q,student_quarterplan sq,courses c" +
	            		" where q.user_id="+a+" and q.id=sq.id and sq.course_id=c.id";
	            ResultSet rs1=stmt1.executeQuery(sql);
	            int x=0;
	            while(rs1.next())
	            {
	            	if(x==0)
	            	   saved_plan.add(rs1.getString("date"));
	            	sub.add(new Data(rs.getInt( "id" ),
	                    rs.getString( "code" ), rs.getString( "name" ),rs.getString( "prereq" )));
	            	
	            }
	            
	          /*  Statement stmt = c.createStatement();
	            ResultSet rs = stmt.executeQuery( "select * from courses" );

	            while( rs.next() )
	            {
	               Data entry = new Data( rs.getInt( "id" ),
	                    rs.getString( "code" ), rs.getString( "name" ),rs.getString( "prereq" ) );
	                sub.add( entry );
	            }*/
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
		
		
	}

}
