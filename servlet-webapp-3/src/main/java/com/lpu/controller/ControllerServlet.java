package com.lpu.controller;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lpu.factory.MyFactory;
import com.lpu.model.ToDo;

public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private List<String> errors;
	MyFactory factory= MyFactory.getMyFactory();


	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doProcess(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);

	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		errors=new ArrayList<String>();
		String name = request.getParameter("name").toString();
		int id =0;
		if(name.length()<8)
		{
			errors.add("invalid name.");
		}
		try {
	
			id= Integer.parseInt(request.getParameter("id").toString());
		} catch (Exception e) {
			errors.add("todo id must be numeric");
		}
	
		String c_by=request.getParameter("c_by").toString();
		if(c_by.length()<=0)
		{
			errors.add("completeed by field cant not be blank");
		}
			
		if(errors.isEmpty())
		{
			ToDo todo=new ToDo(id, name, c_by);
			try {
				PreparedStatement prepareStatement = factory.getMyConnection().prepareStatement("insert into todo values (?,?,? )");
				prepareStatement.setInt(1, todo.getId());
				prepareStatement.setString(2,todo.getName());
				prepareStatement.setString(3, todo.getCompletedBy());
				prepareStatement.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("todo", todo);//key and value pair
			RequestDispatcher view=request.getRequestDispatcher("success.jsp");
			view.forward(request, response);
		}
		else
		{
			
			request.setAttribute("error",errors);//key and value pair
			RequestDispatcher view=request.getRequestDispatcher("error.jsp");
			view.forward(request, response);
		}
		
		
	}

}