package com.abc.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.saveo.Service.MedicineService;
import com.saveo.factory.MedicineServiceFactory;

@WebServlet("/CSV")
public class CSVSaver extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//collect the data from static page using request object
		String path=request.getParameter("path");
		//create the object of MedicineService using factory object
		MedicineService medicineService=MedicineServiceFactory.getMedicineService();
		//call the method of csvPath() using reference of MedicineService 
		String status=medicineService.csvPath(path);
		//if status is success  the success.html will call
		if(status.equals("success")) {
			   request.getRequestDispatcher("success.html").forward(request, response);
			   //if status is failure the failure.html will call 
		   }else if(status.equalsIgnoreCase("failure")){
			   request.getRequestDispatcher("failure.html").forward(request, response);
			 //if the records are already exist  duplicate.html will call 
		   }else {
			   request.getRequestDispatcher("duplicate.html").forward(request, response);
			    
		   }
		
		
	}

}
