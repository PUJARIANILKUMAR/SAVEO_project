package com.saveo.Dao;

import java.util.List;



import com.saveo.bean.BeanMedicine;

public interface MedicineDao 
{
	//creating the method of save
	public String save(BeanMedicine beanMedicine);
	//creating the method of getDetails
	public List<BeanMedicine> getDetails(String c_unique_code);
	//creating the method of csvPath
	public String csvPath(String path);
	//creating the method of orders
	public String orders(String medicineId);
	//creating the method of insert
	public String insert(String MedicineId, String MedicineName1, String MedicineOrder);
	//creating the method of orderDetails
	public List<BeanMedicine> orderDetails(String c_unique_code);
	//creating the method of search
	public List<BeanMedicine> search(String c_name);

}
