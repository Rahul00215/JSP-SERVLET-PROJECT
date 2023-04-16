package com.rahul;

import java.awt.image.RescaleOp;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.cj.jdbc.Driver;
import com.mysql.cj.x.protobuf.MysqlxSession.Reset;
import com.mysql.cj.xdevapi.Statement;

import jdk.nashorn.internal.ir.RuntimeNode.Request;

@WebServlet("/sts")
public class Test extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
        
		
		String sn = request.getParameter("std-name");
		String sName = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/student";
			String username = "root";
			String password = "root";
			Connection conn = null;
			conn = DriverManager.getConnection(url, username, password);
			java.sql.Statement stm = conn.createStatement();
			ResultSet rs = stm.executeQuery("select *from student where id =" + sn);
			while (rs.next()) {
				sName = rs.getString("name");
			}

		      conn.close();
		
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Welcome to testing shastra");
		request.setAttribute("data", "welcome  " + sName);
		RequestDispatcher rd = request.getRequestDispatcher("student.jsp");
		rd.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("we are in dopush method");
		
		request.setAttribute("post", "we are the post method");
		
		RequestDispatcher rd = request.getRequestDispatcher("post.jsp");
		rd.forward(request, response);
	}

	
}
