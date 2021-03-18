package com.abc.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.saveo.Service.MedicineService;
import com.saveo.bean.BeanMedicine;
import com.saveo.factory.MedicineServiceFactory;


@WebServlet("/getMedicineDetails")
public class GetMedicineDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//set the content type to text /html to use the html file
				response.setContentType("text/html");
		//create the getWritter to render the output
				PrintWriter out=response.getWriter();
				
		//take the input from static page
				String c_unique_code=request.getParameter("c_unique_code");
				
		//creating the reference of MedicineService interface
				MedicineService medicineServce=MedicineServiceFactory.getMedicineService();
		//calling the method of getDetails
				List<BeanMedicine> beanMedicines=medicineServce.getDetails(c_unique_code);
				
				//get the result and store in the beanMedicines reference
				out.println("<html><body>");
				out.println("<h1 Style='color:green;text-align:center'>Medicine Details if ID = "+c_unique_code+"</h1>");
				out.println("<h2 Style='color:blue;text-align:center'>Medicine Details Form</h2>");
				if(beanMedicines !=null) {
					out.println("<center>");
					out.println("<table border='1'>");
					out.println("<tr><td>c_name</td><td>c_batch_no</td><td>d_expiry_date</td><td>n_balance_qty</td><td>c_packaging</td><td>c_unique_code</td><td>c_schemes</td><td>n_mrp</td><td>getC_manufacturer</td><td>hsn_code</td></tr>");
					for(BeanMedicine beanMedicine1:beanMedicines) {
						out.println("<tr>");
						out.println("<td>"+beanMedicine1.getC_name()+"</td>");
						out.println("<td>"+beanMedicine1.getC_batch_no()+"</td>");
						out.println("<td>"+beanMedicine1.getD_expiry_date()+"</td>");
						out.println("<td>"+beanMedicine1.getN_balance_qty()+"</td>");
						out.println("<td>"+beanMedicine1.getC_packaging()+"</td>");
						out.println("<td>"+beanMedicine1.getC_unique_code()+"</td>");
						out.println("<td>"+beanMedicine1.getC_schemes()+"</td>");
						out.println("<td>"+beanMedicine1.getN_mrp()+"</td>");
						out.println("<td>"+beanMedicine1.getC_manufacturer()+"</td>");
						out.println("<td>"+beanMedicine1.getHsn_code()+"</td>");
						
						out.println("</tr>");
						
						
					}
					
					out.println("</table>");
					out.println("<br><a href='welcome.html'>Home</a>");
					out.println("</center>");
				}else {
					out.println("<center>");
					out.println("<h2 style='color:red;text-align:center'>Medicine Not found With the Medicine Id : "+c_unique_code+"</h2>");
					out.println("<br><a href='Details.html'>Try Again</a>");
					out.println("</center>");
				}
				
				out.println("</body></html>");
				
				
				out.close();
				
	}

}
