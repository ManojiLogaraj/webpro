package com.getvalue;
import java.io.IOException;
import java.io.PrintWriter;
import  java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/action_page")
public class Action_page extends HttpServlet
{	static String email=null;
	static String pass=null;
	static String RepeatPass=null;
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException, ServletException
	{	email=req.getParameter("email");
		pass=req.getParameter("psw");
		RepeatPass=req.getParameter("psw-repeat");
		Action_page AP=new Action_page();
		AP.CheckFromDb(null,null,req,res);
		}
	public void CheckFromDb(String mailid, String passwrd,HttpServletRequest req,HttpServletResponse res)
	{	DbConnection db=new DbConnection();
		db.getValue(email, pass,RepeatPass,req,res);
		RequestDispatcher rd=req.getRequestDispatcher("LoginForm.jsp");
	}
}

