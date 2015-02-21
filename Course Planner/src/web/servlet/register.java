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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import web.model.Data;

@WebServlet("/register")
public class register extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Map<String, String> map = new HashMap<String, String>();

	public register() {
		super();

	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// String error = request.getParameter("id");
		request.setAttribute("m", map);
		request.getRequestDispatcher( "/WEB-INF/Register.jsp" ).forward(
	            request, response );
		       map.clear();
}

	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		
		List<Data> d=new ArrayList<Data>();

		
		
		
		 Connection c = null;
		try
      {
					//	 Class.forName( "com.mysql.jdbc.Driver" );
          String url = "jdbc:mysql://localhost/cs320stu51";
          String username = "root";
          String password = "root";

          c = DriverManager.getConnection( url, username, password );
          Statement stmt = c.createStatement();
          ResultSet rs = stmt.executeQuery( "select * from users" );
          Data entry=null;
          while( rs.next() )
          {
          	
              entry = new Data(rs.getString( "user_name" ), rs.getString( "password" ),rs.getString( "first_name" ),rs.getString( "last_name" ) );
             d.add(entry);
          }
         
 		 
 		
 		
      
		String u_name = request.getParameter("user_name").trim();
		String pass = request.getParameter("password").trim();
		String re_pass = request.getParameter("re_password").trim();
		if (u_name.equals("")) {
			map.put("uname_empty", "Enter the user name");

		}
		if (u_name.length() < 4) {
			map.put("uname_short", "Username must be at least 4 characters");
		}
		if (pass.equals("")) {
			map.put("pass_empty", "Enter the password");
		}
		if (pass.length() < 4) {
			map.put("pass_short", "Password must be atleast 4 character");
		}
		if (!pass.equals(re_pass)) {
			map.put("pass_match", "Password and re_password should match");
		}

		else {
			for (Data j : d) {
				if (j.getUsername().equalsIgnoreCase(u_name)) {
					map.put("user_exist", "Username already exist");

				}
			}

		}
		if (map.isEmpty()) {
			String sql = "insert into users(user_name,password,first_name,last_name) values (?,?,?,?)";
			PreparedStatement pt=c.prepareStatement(sql);
			 pt.setString( 1, request.getParameter("user_name").trim() );
	            pt.setString( 2,request.getParameter("password").trim());
	            pt.setString( 3,request.getParameter("f_name").trim());
	            pt.setString( 4,request.getParameter("l_name").trim());
	            pt.executeUpdate();
			
			request.getSession().setAttribute("user",
					request.getParameter("user_name").trim());
			response.sendRedirect("Display");
			return;
		}
		response.sendRedirect("register");

	
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

}
}
