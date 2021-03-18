package com.abc.controller;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.saveo.Service.MedicineService;
import com.saveo.factory.MedicineServiceFactory;


@WebServlet("/display")
public class InsertOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//set the content type to text /html to use the html file
		response.setContentType("text/html");
		//take the input from static page
		String MedicineId= request.getParameter("id");
		String MedicineName1=request.getParameter("name");
		String MedicineOrder=request.getParameter("quantity");
		//creating the reference of MedicineService interface
		MedicineService medicineService=MedicineServiceFactory.getMedicineService();
		String status=medicineService.insert(MedicineId, MedicineName1, MedicineOrder);
		//get the result and store in the status reference
		 if(status.equals("success")) {
			   request.getRequestDispatcher("success.html").forward(request, response);
		   }else if(status.equalsIgnoreCase("failure")){
			   request.getRequestDispatcher("failure.html").forward(request, response);
			   
		   }else {
			   request.getRequestDispatcher("duplicate.html").forward(request, response);
			    
		   }
		
	}

}
