package web.model;

import java.sql.*;

import javax.servlet.ServletException;

public class Dbconnection {

	Connection c;
	Statement st;
	ResultSet rs;

	public Connection getC() throws ServletException, SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost/cs320stu51";
			String username = "cs320stu51";
			String password = "4#h5oVjS";
			Connection c = DriverManager.getConnection(url, username, password);
			return c;
		} catch (ClassNotFoundException e) {
			throw new ServletException(e);
		}
	}

	public void setC(Connection c) {
		this.c = c;
	}

	public Statement getSt() {
		try {
			st = c.createStatement();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return st;
	}

	public void setSt(Statement st) {
		this.st = st;
	}

	public ResultSet getRs() {
		return rs;
	}

	public void setRs(ResultSet rs) {
		this.rs = rs;
	}

}
