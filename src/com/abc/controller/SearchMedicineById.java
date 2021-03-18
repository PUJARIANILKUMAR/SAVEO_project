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


@WebServlet("/search")
public class SearchMedicineById extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//set the content type to text /html to use the html file
		response.setContentType("text/html");
		//create the getWritter to render the output
		PrintWriter out=response.getWriter();
		//take the input from static page
		String c_name=request.getParameter("name");
		//creating the reference of MedicineService interface
		MedicineService medicineServce=MedicineServiceFactory.getMedicineService();
		//calling the method of search
		List<BeanMedicine> beanMedicines=medicineServce.search(c_name);
		//get the result and store in the beanMedicines reference
		out.println("<html><body>");
		out.println("<h1 Style='color:red;text-align:center'>Medicine Details of name : "+c_name+"</h1>");
		out.println("<h2 Style='color:blue;text-align:center'>Medicine Search Details</h2>");
		if(beanMedicines !=null) {
			out.println("<center>");
			out.println("<table border='1'>");
			out.println("<tr><td>c_name</td><td>c_unique_code</td></tr>");
			for(BeanMedicine beanMedicine1:beanMedicines) {
				out.println("<tr>");
				out.println("<td>"+beanMedicine1.getC_name()+"</td>");
				out.println("<td>"+beanMedicine1.getC_unique_code()+"</td>");
				out.println("</tr>");	
			}
			out.println("</table>");
			out.println("<br><a href='welcome.html'>Home</a>");
			out.println("</center>");
		}else {
			out.println("<center>");
			out.println("<h2 style='color:red;'>Medicine Not found With the Medicine Id : "+c_name+"</h2>");
			out.println("<br><a href='orderdetails.html'>Try Again</a>");
			out.println("</center>");
		}
		
		out.println("</body></html>");
		
		
		
		out.close();
	}

}
