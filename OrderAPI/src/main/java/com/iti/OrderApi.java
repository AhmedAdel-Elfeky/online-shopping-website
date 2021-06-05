package com.iti;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/DML")
public class OrderApi {

	@GET
	@Path("/get/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Order selectEmp(@PathParam("id") int id) {
		Order order = new Order();
		return order.getOrderById(id);
 	}
}
