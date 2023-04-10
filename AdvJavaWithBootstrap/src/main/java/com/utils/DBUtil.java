package com.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBUtil {

public static Connection getMySQLConnection() {
	Connection con = null;
	try {
		// 1. Load the Driver class
		Class.forName("com.mysql.cj.jdbc.Driver");
		// 2. Establish the connection
		con = DriverManager.getConnection("jdbc:mySQL://localhost:3306/user", "root", "root");
		System.out.println("DB Connected !");
	} catch (Exception e) {
		e.printStackTrace();
		System.err.println("Error while connecting DATABASE");
	}
	return con;
}

public static void cleanup(Connection con, Statement st, ResultSet rs) {
	try {
		// 7.Release the Resources
		if (rs != null)
			rs.close();
		if (st != null)
			st.close();
		if (con != null)
			con.close();
	} catch (Exception e) {
		e.printStackTrace();
	}
}

public static void cleanup(Connection con, Statement st) {
	try {
		// 7.Release the Resources
		if (st != null)
			st.close();
		if (con != null)
			con.close();
	} catch (Exception e) {
		e.printStackTrace();
	}
}
}
