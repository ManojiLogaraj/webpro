package com.getvalue;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class DbConnection{
	public static String mailid=null;
	public static String passwrd=null;
	public static String sql;
	public void getValue(String email,String password,String RepeatPass,HttpServletRequest req,HttpServletResponse res)
	{	System.out.println(email+" "+ password+" "+RepeatPass);
		if(password.equals(RepeatPass))
		{		
		try{  
			Class.forName("com.mysql.cj.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/","root","root");  
			Statement stmt=con.createStatement();  
			stmt.executeUpdate("use webpro;");	
			sql="select * from logindetails where mailid='"+email+"'";
			ResultSet rs=stmt.executeQuery(sql);
			while(rs.next()==true)
			{
				mailid=rs.getString(1);
			}
			System.out.println(mailid);
			DbConnection db=new DbConnection();
			if(mailid!=null)
			{   
				if(mailid.equals(email))
				db.RedirectToLogin(req,res);
			}else 
				{
				stmt.executeUpdate("INSERT INTO logindetails VALUES('"+email+"','"+password+"');");
				db.RedirectToLogin(req,res);
				}
		}
		catch(Exception e) {
		System.out.println(e);			
		}
		}
		else
		{
			System.out.println("Password wrong");				
		}
	}
	public void RedirectToLogin(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
	{
		RequestDispatcher rd=req.getRequestDispatcher("/LoginForm.jsp");
		rd.forward(req, res);
	}
}