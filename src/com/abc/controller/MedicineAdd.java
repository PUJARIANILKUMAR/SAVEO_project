package com.abc.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.saveo.Service.MedicineService;
import com.saveo.bean.BeanMedicine;
import com.saveo.factory.MedicineServiceFactory;

@WebServlet("/Add")
public class MedicineAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//create the bean object and setting values and take from html
		 BeanMedicine beanMedicine=new BeanMedicine();
			
		 beanMedicine.setC_name(request.getParameter("c_name"));
		 beanMedicine.setC_batch_no(request.getParameter("c_batch_no"));
		 beanMedicine.setD_expiry_date(request.getParameter("d_expiry_date"));
		 beanMedicine.setN_balance_qty(request.getParameter("n_balance_qty"));
		 beanMedicine.setC_packaging(request.getParameter("c_packaging"));
		 beanMedicine.setC_unique_code(request.getParameter("c_unique_code"));
		 beanMedicine.setC_schemes(request.getParameter("c_schemes"));
		 beanMedicine.setN_mrp(request.getParameter("n_mrp"));
		 beanMedicine.setC_manufacturer(request.getParameter("c_manufacturer"));
		 beanMedicine.setHsn_code(request.getParameter("hsn_code"));
		//creating the reference of MedicineService interface
		MedicineService medicineService=MedicineServiceFactory.getMedicineService();
		//calling the method of save
		String status=medicineService.save(beanMedicine);
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
