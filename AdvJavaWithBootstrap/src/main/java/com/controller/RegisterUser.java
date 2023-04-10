package com.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pojo.Registeruser;
import com.utils.DBUtil;

@WebServlet("/RegisterUser")
public class RegisterUser extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String fname = request.getParameter("fname");
		String mname = request.getParameter("mname");
		String lname = request.getParameter("lname");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String repassword = request.getParameter("repassword");
		RequestDispatcher rd = null;
		if (password.equals(repassword)) 
		{
			Registeruser ru = new Registeruser(fname, mname, lname, email, password);
			try {
				Connection con = DBUtil.getMySQLConnection();

				PreparedStatement ps = con.prepareStatement(
						"INSERT INTO registeruser (fname, mname,lname,email,password) VALUES (?,?,?,?,?);");
				ps.setString(1, ru.getFname());
				ps.setString(2, ru.getMname());
				ps.setString(3, ru.getLname());
				ps.setString(4, ru.getEmail());
				ps.setString(5, ru.getPassword());
				if (ps.executeUpdate() > 0) {
					//if query get executed then execute if loop
					response.sendRedirect("dashboard.jsp");
				} 
				else {
					System.out.println("Something went wrong");
					request.setAttribute("errormessage1", "Something went wrong");//send this error message to particular jsp file
					rd = request.getRequestDispatcher("register.jsp");
					rd.forward(request, response);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
		else {
			request.setAttribute("errormessage", "Password doesn't match please re-enter the details");
			rd = request.getRequestDispatcher("register.jsp");
			rd.forward(request, response);
		}
	}
}
