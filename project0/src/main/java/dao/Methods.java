package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import common.util.Jdbc_connect;
import service.Services;
import transactions.util.Transactions;

public class Methods implements Services 
{

	public Transactions getUser(String name) 
	{
		try (Connection connect = Jdbc_connect.getConnection()) 
		{
			@SuppressWarnings("resource")
			Connection connection = connect;
	        String query = "SELECT name, password, balance, admin " + "FROM bank_table WHERE name = ?";
	        PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(query);
	        preparedStatement.setString(1, name);

			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) 
			{
				return new Transactions(resultSet.getString("name"), resultSet.getString("password"), resultSet.getFloat("balance"), resultSet.getBoolean("admin"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean insertUser(Transactions param) 
	{
		try (Connection connect = Jdbc_connect.getConnection()) 
		{
			 @SuppressWarnings("resource")
			Connection connection = connect;
		        String query = "insert into bank_table(name,password,balance,admin)"
		        		+ "VALUES (?, ?, ?, ?)";
		        PreparedStatement statement = (PreparedStatement) connection.prepareStatement(query);
		        statement.setString(1, param.name);
		        statement.setString(2, param.password);
		        statement.setFloat(3, param.balance);
		        statement.setBoolean(4, param.admin);

			return statement.executeUpdate() > 0;
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return false;
	}
	public boolean refreshUser(Transactions param) 
	{
		try (Connection connect = Jdbc_connect.getConnection()) 
		{
			 @SuppressWarnings("resource")
			Connection connection = connect;
		        String query = "UPDATE bank_table SET "+ "balance = ?, admin = ? WHERE name = ?";
		        PreparedStatement statement = (PreparedStatement) connection.prepareStatement(query);
		        statement.setFloat  (1, param.balance);
		        statement.setBoolean(2, param.admin);
		        statement.setString (3 , param.name);

		} catch (SQLException e) 
		{
			e.printStackTrace();
		} 
		return false;
	}

	public Map<String, Transactions> getAll() 
	{
		Map<String, Transactions> map = new HashMap<>();
		try (Connection con = Jdbc_connect.getConnection()) 
		{
			 @SuppressWarnings("resource")
				Connection connection = con;
			        String query = "SELECT name, password, balance, admin "+ "FROM bank_table";
			        PreparedStatement statement = (PreparedStatement) connection.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) 
			{
				Transactions newUser = new Transactions(resultSet.getString("name"), 
						resultSet.getString("password"), 
						resultSet.getFloat("balance"),
						resultSet.getBoolean("admin"));
				map.put(newUser.name, newUser);
			}
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return map;
	}

	private static Methods instance;
	public static Methods getter() {
		
		if (instance == null) 
		{
			instance = new Methods();
		}
		return instance;
	}

	
	
		
	
}