package com.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;

import com.bean.Student;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * Servlet implementation class MainServlet
 */
@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	String WebServiceURI = "http://localhost:8080/REstfulWS/";
	ClientConfig clientConfig = null;
	Client client = null;
	WebTarget webTarget = null;
	Gson gson = null;

	/**
	 * Default constructor.
	 */
	public MainServlet() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at:
		// ").append(request.getContextPath());
		doProcess(request, response);
	}

	private void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		clientConfig = new ClientConfig();
		client = ClientBuilder.newClient(clientConfig);
		webTarget = client.target(WebServiceURI).path("rest");
		gson = new Gson();
		String action = request.getParameter("action");
		if(action==null)
		{
			showStudent(request, response);
		}
		else if (action.equalsIgnoreCase("insert")) {
			Student student = new Student();
			student.setFname(request.getParameter("fname"));
			student.setLname(request.getParameter("lname"));
			student.setEmailid(request.getParameter("emailid"));
			String s = gson.toJson(student);
			Response res = webTarget.path("student").path("add").request()
					.post(Entity.json(s));
			String result = res.readEntity(String.class);
			RequestDispatcher rd;
			if (res.getStatus() == 200) {
				try {
					showStudent(request, response);
				} catch (ServletException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				request.setAttribute("msg", result);
				rd = request.getRequestDispatcher("errorPage.jsp");
				try {
					rd.forward(request, response);
				} catch (ServletException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} else if (action.equalsIgnoreCase("update")) {
			RequestDispatcher rd = null;
			String fname, lname, emailid;
			fname = request.getParameter("fname");
			lname = request.getParameter("lname");
			emailid = request.getParameter("emailid");
			int id = Integer.parseInt(request.getParameter("id"));
			Student student = new Student();
			student.setEmailid(emailid);
			student.setFname(fname);
			student.setLname(lname);
			student.setId(id);
			String stud = gson.toJson(student);
			Response rs = webTarget.path("student").path("update").request()
					.post(Entity.json(stud));
			String result = rs.readEntity(String.class);
			if (rs.getStatus() == 200) {
				showStudent(request, response);
			} else {
				request.setAttribute("msg", result);
				rd = request.getRequestDispatcher("errorPage.jsp");
				rd.forward(request, response);
			}
			System.out.println(result);
		} else if (action.equalsIgnoreCase("delete")) {
			RequestDispatcher rd = null;
			int id = Integer.parseInt(request.getParameter("sid"));
			Response rs = webTarget.path("student").path("delete").queryParam("id", id)
					.request().get();
			String result = rs.readEntity(String.class);
			if (rs.getStatus() == 200) {
				showStudent(request, response);
			} else {
				request.setAttribute("msg", result);
				rd = request.getRequestDispatcher("errorPage.jsp");
				rd.forward(request, response);
			}
			System.out.println(result);
		}
		
	}

	protected void showStudent(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Response rs = webTarget.path("student").path("viewall").request().get();
		String result = rs.readEntity(String.class);
		if (rs.getStatus() == 200) {
			List<Student> studentList = gson.fromJson(result, new TypeToken<List<Student>>() {
			}.getType());
			request.setAttribute("students", studentList);

		} else {
			request.setAttribute("error", "Error In Fetching All Students");
		}
		System.out.println(result);
		RequestDispatcher view = request.getRequestDispatcher("ProfilePage.jsp");
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
