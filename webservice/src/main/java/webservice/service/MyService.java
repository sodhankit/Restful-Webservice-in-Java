package webservice.service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;



@Path("/users")
public class MyService {
	
	userdao dao=new userdao();
	
	@GET
	//@Path("/users")
	@Produces(MediaType.APPLICATION_JSON)
	public String getUser() {
		List<User> users = dao.getUsers();
		Gson gson=new Gson();
		String usersJson=gson.toJson(users);
		return usersJson;
	}
	
	@POST
	//@Path("/users")
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_JSON)
	public String saveUser(String userJson) {
		System.out.println(userJson);
		Gson gson=new Gson();
		User user=gson.fromJson(userJson, User.class);
		
		
		String result=dao.registerUser(user);
		//System.out.println(result);
		
		return result;
	}
	
	@PUT
	@Path("/{id}")
	@Produces("text/plain")	
	public String updateUser(@PathParam("id") String id) {
		return "update user";
	}
	
	@DELETE
	@Path("/{id}")
	@Produces("text/plain")
	public String deleteUser(@PathParam("id") String id) {
		return "delete User";
	}
}
