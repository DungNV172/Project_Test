package com.dungnv.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.management.loading.PrivateClassLoader;

import com.dungnv.model.LoginBean;

public class LoginDAO {	
	private String url = "jdbc:mysql://localhost:3306/login";
	private String username = "root";
	private String password = "nguyenvandung";
	
	public boolean validate(LoginBean loginBean) {
		boolean status = false;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, username, password);
			PreparedStatement preparedStatement = 
					con.prepareStatement("select * from login where username = ? and password = ?");
			preparedStatement.setString(1, loginBean.getUsername());
			preparedStatement.setString(2, loginBean.getPassword());
			System.out.println(preparedStatement);
			ResultSet resultSet = preparedStatement.executeQuery();
			status = resultSet.next();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return status;
}
}
