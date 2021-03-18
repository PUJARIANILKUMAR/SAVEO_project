package com.saveo.factory;

import com.saveo.Service.MedicineService;
import com.saveo.Service.MedicineServiceImpl;

public class MedicineServiceFactory 
{
	public static MedicineService medicineService=null;
	//using the static block create reference of MedicineService
	static {
		medicineService=new MedicineServiceImpl();
	}
	//creating the method getMedicineService
	public static MedicineService  getMedicineService() 
	{
		
		return medicineService;
		
	}

}
