package com.picker.client;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OrderDataAccess {
														//Accesses the database and executes the queries.
	private Connection connection;
	private String sql = "SELECT * FROM orderItemTable WHERE orderStatus = 'Incomplete' ORDER BY orderID ASC;";
	private final String ORDER_ID = "orderID";
	private final String ORDER_TYPE = "orderType";
	private final String ITEM_ID = "itemID";
	private final String CUSTOMER_ID = "customerID";
	public OrderDataAccess(String dbURL, String user, String password) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated constructor stub
        if (connection == null || connection.isClosed()) {							//checks if the connection is closed.
        	try {
				Class.forName("com.mysql.cj.jdbc.Driver").newInstance();			//tries to create new connection to the database server
				connection = DriverManager.getConnection(dbURL, user, password);	//uses parameters to log in to the database.
        		System.out.println("trying to connecto to the server");


			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

        }
				
	}

	public void shutdown() throws SQLException {			
		if(connection != null) {
			System.out.println("closing connection");
			connection.close();
		}
	}
	public List<Order> getOrderList() throws SQLException{		//builds a list of available orders.
		
		try (
			Statement statement = connection.createStatement();
			ResultSet rSet = statement.executeQuery(sql);
		){
			List<Order> orderList = new ArrayList<>();
			while(rSet.next()) {
				String orderID = rSet.getString(ORDER_ID);
				String orderType = rSet.getString(ORDER_TYPE);
				String itemID = rSet.getString(ITEM_ID);
				String customerID = rSet.getString(CUSTOMER_ID);
				Order order = new Order(orderID,orderType,itemID,customerID);
				orderList.add(order);

			}
			return orderList;
		}
		
	}

}
