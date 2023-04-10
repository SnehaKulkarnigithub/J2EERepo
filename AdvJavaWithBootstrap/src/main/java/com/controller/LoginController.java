package com.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.utils.DBUtil;

@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// check connectivity using @webservlet
				// Write select query for check user is present or not
				try {
					Connection con = DBUtil.getMySQLConnection();
					PreparedStatement ps = con.prepareStatement("Select * FROM registeruser where email = ? AND password = ? ;");
					ps.setString(1, request.getParameter("email"));
					ps.setString(2, request.getParameter("password"));
					ResultSet rs = ps.executeQuery();
					RequestDispatcher rd = null;

					// step 3 : navigate data
					if (rs.next()) {
						//if user found then execute
						response.sendRedirect("dashboard.jsp");
					} else {
						//if user not found then execute
						request.setAttribute("errormessage", "Invalid Email and Password");//send this error message to particular jsp file
						rd = request.getRequestDispatcher("index.jsp");
						rd.forward(request, response);
					}
					con.close();
				} catch (Exception e) {
					System.out.println("Error while execute query");
				}
	}

}
