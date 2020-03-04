package controller;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * Servlet implementation class ServiceConstroller
 */
public class ServiceConstroller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServiceConstroller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		Client client=ClientBuilder.newClient();
		WebTarget target=client.target("http://localhost:8080/webservice/rest/users");
		
		User user=new User();
		user.setFullName("Amit");
		user.setEmail("amit@gmail.com");
		user.setPassword("Amit123");
		Gson gson =new Gson();
		String userJson=gson.toJson(user);
		
		Response resp=target.request().post(Entity.json(userJson));
		String result=resp.readEntity(String.class);
		response.getWriter().append(result);
		
		/*Response resp=target.request().get();
		String result=resp.readEntity(String.class);
		Gson gson=new Gson();
		Type type = new TypeToken<List<User>>() {}.getType();
		List<User> users = gson.fromJson(result, type);
		String str="<table border='1px'>";		
		for(User user: users) {
			str+="<tr>"
					+"<td>"+user.getId()+"</td>"
					+"<td>"+user.getFullName()+"</td>"
					+"<td>"+user.getEmail()+"</td>"
					+"</tr>";
		}
		str+="</table>";
		response.setContentType("text/html");
		response.getWriter().append(str);*/
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
