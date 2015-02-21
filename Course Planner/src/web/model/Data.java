package web.model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class Data
{
	String code;
	String name;
	String Prereq;
	String username;
	String password;
	String first_name;
	String last_name;
	int id;
	
	public Data(String username,String password,String first_name,String last_name)
	{
		this.username=username;
		this.password=password;
		
		this.first_name=first_name;
		this.last_name=last_name;
	}

	public Data(int id,String code,String name, String Prereq)
	{
		this.id=id;
		this.code=code;
		this.name=name;
		this.Prereq=Prereq;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	

  public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrereq() {
		return Prereq;
	}

	public void setPrereq(String Prereq) {
		this.Prereq = Prereq;
	}
}