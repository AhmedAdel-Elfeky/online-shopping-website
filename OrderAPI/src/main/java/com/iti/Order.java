package com.iti;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement

public class Order {

	 int order_id; 
	 int quantity; 
	 String status;   
	 String date;
	 String state;
	 String expected_delivery_date;
	
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getExpected_delivery_date() {
		return expected_delivery_date;
	}

	public void setExpected_delivery_date(String expected_delivery_date) {
		this.expected_delivery_date = expected_delivery_date;
	}

	public int getOrder_id() {
		return order_id;
	}

	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	 public Order getOrderById(int id){
		 DataBase db = new DataBase();
		 db.connect();
		   Order order = new Order();
		   try {
			ResultSet rs = db.select("select * from orders where order_id ='"+id+"';");
			
			if(rs.next()) {
				order.setOrder_id(id);
				order.setStatus(rs.getString("status"));
				order.setQuantity(rs.getInt("quantity"));
                order.setDate(rs.getString("date"));
                order.setState(rs.getString("state"));
                order.setExpected_delivery_date(rs.getString("expected_delivery_date"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   db.disconnect();
		   return order;
	   }

}
