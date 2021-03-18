package com.abc.controller;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.saveo.Service.MedicineService;
import com.saveo.factory.MedicineServiceFactory;


@WebServlet("/order")
public class MedicineOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//set the content type to text /html to use the html file
		response.setContentType("text/html");
		//take the input from static page
		String MedicineId= request.getParameter("c_unique_code");
		//creating the reference of MedicineService interface
		MedicineService medicineService=MedicineServiceFactory.getMedicineService();
		//calling the method of getDetails
		String status=medicineService.orders(MedicineId);
		//get the result and store in the status reference
		 if(status.equals("success")) {
			   request.getRequestDispatcher("Order.html").forward(request, response);
		   }else if(status.equalsIgnoreCase("failure")){
			   request.getRequestDispatcher("failure.html").forward(request, response);
			   
		   }else {
			   request.getRequestDispatcher("duplicate.html").forward(request, response);
			    
		   }
		
	}

}
