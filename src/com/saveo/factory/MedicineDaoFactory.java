package com.saveo.factory;

import com.saveo.Dao.MedicineDao;
import com.saveo.Dao.MedicineDaoImpl;

public class MedicineDaoFactory 
{
	//creating the factory object to the MedicineDao
	public static MedicineDao medicineDao=null;
	static {
		medicineDao=new MedicineDaoImpl();
	}
	//creating the method of getMedicineDao
	public static MedicineDao getMedicineDao() 
	{
		return medicineDao;
	}

}
