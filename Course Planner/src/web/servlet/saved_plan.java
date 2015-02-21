package web.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.model.Data;
import web.model.QuarterPlan;

/**
 * Servlet implementation class saved_plan
 */
@WebServlet("/saved_plan")
public class saved_plan extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public saved_plan() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Data> d=(List<Data>)request.getSession().getAttribute("Sub_entries");
		List<QuarterPlan> q=(List<QuarterPlan>)request.getSession().getAttribute("Quart");
		String uname=(String)request.getSession().getAttribute("user");
		
		Connection c = null;
        try
        {
        	Class.forName( "com.mysql.jdbc.Driver" );
            String url = "jdbc:mysql://localhost/cs320stu51";
            String username = "root";
            String password = "root";

            c = DriverManager.getConnection( url, username, password );
            Statement stmt = c.createStatement();
            ResultSet rs=stmt.executeQuery("select * from users where user_name='"+uname+"'");
            int a=0;
            int b=0;
            while(rs.next())
            {
            	a=rs.getInt(1);
            	
            }
            Calendar cal = Calendar.getInstance(TimeZone.getDefault());	
            DateFormat dateFormat = new SimpleDateFormat("MM-dd-YYYY hh:mm a");
           
            String sql1="insert into quarterplan (user_id,date) values(?,?)";
            PreparedStatement pt1=c.prepareStatement(sql1);
            pt1.setInt(1,a);
            pt1.setString(2,dateFormat.format(cal.getTime()));
            pt1.executeUpdate();
            String sql2="select * from quarterplan where user_id="+a+"";
            Statement stmt2 = c.createStatement();
            ResultSet rs1=stmt2.executeQuery(sql2);
            while(rs1.next())
            {
            	b=rs1.getInt(1);
            	
            }
            
            String sql="insert into student_quarterplan (quarter_id,quarter,course_id) values (?,?,?)";
            PreparedStatement pt=c.prepareStatement(sql);
            for(int i=0;i<q.size();i++)
            {
            	for(int j=0;j<q.get(i).getTaken().size();j++)
            	{
            		pt.setInt(1, b);
            	pt.setString(2,q.get(i).getNextquarter() );
            	pt.setInt(3,q.get(i).getTaken().get(j).getId());
            	pt.executeUpdate();
            	}
            	
            }
            response.sendRedirect("Display");
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
