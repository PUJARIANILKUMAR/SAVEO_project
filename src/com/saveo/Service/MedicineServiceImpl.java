package com.saveo.Service;

import java.util.List;

import com.saveo.Dao.MedicineDao;
import com.saveo.bean.BeanMedicine;
import com.saveo.factory.MedicineDaoFactory;

public class MedicineServiceImpl implements  MedicineService
{
//this method overriding the  save method and calling the save  method in dao layer
	@Override
	public String save(BeanMedicine beanMedicine) 
	{
		
		MedicineDao medicineDao=MedicineDaoFactory.getMedicineDao();
		 String status=medicineDao.save(beanMedicine);
		
		return status;
	}
	//this method overriding all the methods from service and calling the DAO layer
	@Override
	public List<BeanMedicine> getDetails(String c_unique_code) {
		MedicineDao medicineDao=MedicineDaoFactory.getMedicineDao();
		 List<BeanMedicine> beanMedicine=medicineDao.getDetails(c_unique_code);
		
		return beanMedicine;
		
	}
	@Override
	public String csvPath(String path) 
	{
		MedicineDao medicineDao=MedicineDaoFactory.getMedicineDao();
		String result=medicineDao.csvPath(path);
		 
		return result;
	}
	@Override
	public String orders(String MedicineId) 
	{
		MedicineDao medicineDao=MedicineDaoFactory.getMedicineDao();
		 String order=medicineDao.orders(MedicineId);
		
		return order;
		
	}
	@Override
	public String insert(String MedicineId, String MedicineName1, String MedicineOrder) {
		MedicineDao medicineDao=MedicineDaoFactory.getMedicineDao();
		 String order=medicineDao.insert(MedicineId, MedicineName1, MedicineOrder);
		
		return order;
	}
	@Override
	public List<BeanMedicine> orderDetails(String c_unique_code) {
		MedicineDao medicineDao=MedicineDaoFactory.getMedicineDao();
		 List<BeanMedicine> beanMedicine=medicineDao.orderDetails(c_unique_code);
		
		return beanMedicine;
	}
	
	@Override
	public List<BeanMedicine> search(String c_name) {
		MedicineDao medicineDao=MedicineDaoFactory.getMedicineDao();
		 List<BeanMedicine> beanMedicine=medicineDao.search(c_name);
		
		return beanMedicine;
	}

	



}
