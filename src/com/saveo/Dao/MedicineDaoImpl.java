package com.saveo.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.saveo.bean.BeanMedicine;
import com.saveo.factory.MedicineConnectionFactory;

public class MedicineDaoImpl implements MedicineDao
{
	//this creating the SQLquries to exicute in Mysql
	private static final String CSV_SELECT = "select * from Medicine.Productlist";
	private static final String CSV_INSERT = "insert into medicinelist(c_name,c_batch_no,d_expiry_date,n_balance_qty,c_packaging,c_unique_code,c_schemes,n_mrp,c_manufacturer,hsn_code) values (?,?,?,?,?,?,?,?,?,?) ";
	private static final String ORDER_INSERT ="insert into orders(c_name,c_unique_code,orders) values (?,?,?)";
	private static final String Medicine_Insert="insert into medicinelist(c_name,c_batch_no,d_expiry_date,n_balance_qty,c_packaging,c_unique_code,c_schemes,n_mrp,c_manufacturer,hsn_code) values (?,?,?,?,?,?,?,?,?,?) ";
//implementing all the methods present in the DAO interface
	@Override
	public String save(BeanMedicine beanMedicine) 
	{
		//this operation will create the connection object
		String status=null;
		Connection connection=null;
		//this operation will create the prepareStatement object
		PreparedStatement prepareStatement=null;
		int rowAffect=0;
		connection=MedicineConnectionFactory.getConnection();
		try {
			if(connection !=null) {
				prepareStatement=connection.prepareStatement(Medicine_Insert);
				prepareStatement.setString(1, beanMedicine.getC_name());
				prepareStatement.setString(2, beanMedicine.getC_batch_no());
				prepareStatement.setString(3, beanMedicine.getD_expiry_date());
				prepareStatement.setString(4, beanMedicine.getN_balance_qty());
				prepareStatement.setString(5, beanMedicine.getC_packaging());
				prepareStatement.setString(6, beanMedicine.getC_unique_code());
				prepareStatement.setString(7, beanMedicine.getC_schemes());
				prepareStatement.setString(8, beanMedicine.getN_mrp());
				prepareStatement.setString(9, beanMedicine.getC_manufacturer());
				prepareStatement.setString(10,beanMedicine.getHsn_code());
				//this code perform executeupdate in database
				rowAffect=prepareStatement.executeUpdate();
				if(rowAffect==1) {
					status="success";
					
				}else {
					status="failure";
				}
			}
			}catch (SQLException e) {
				if	(e.getErrorCode()==1062) {
					status="duplicate";
				}else {
					status="failure";
				}
				e.printStackTrace();
			}
			return status;
		
	}

	@Override
	public List<BeanMedicine> getDetails(String c_unique_code) 
	{
		final String SQLQuery="select * from medicinelist where c_unique_code='"+c_unique_code+"'";
		//this operation will create the connection object
		Connection connection=null;
		//this operation will create the statement object
		Statement statement=null;
		//this operation will create the resultSet object
		ResultSet resultSet=null;
		//this operation will create the beanMedicine object
		ArrayList<BeanMedicine> beanMedicine=null;
		connection=MedicineConnectionFactory.getConnection();
		try {
			statement=connection.createStatement();
			//this operation perform the executing the SQL Query 
			resultSet=statement.executeQuery(SQLQuery);
			if(resultSet.next()) {
				 beanMedicine=new ArrayList<BeanMedicine>();
				BeanMedicine tempbeanMedicine=new BeanMedicine();
				//this will do the operation of creating bean object and fetching the values
				tempbeanMedicine.setC_name(resultSet.getString(1));
				tempbeanMedicine.setC_batch_no(resultSet.getString(2));
				tempbeanMedicine.setD_expiry_date(resultSet.getString(3));
				tempbeanMedicine.setN_balance_qty(resultSet.getString(4));
				tempbeanMedicine.setC_packaging(resultSet.getString(5));
				tempbeanMedicine.setC_unique_code(resultSet.getString(6));
				tempbeanMedicine.setC_schemes(resultSet.getString(7));
				tempbeanMedicine.setN_mrp(resultSet.getString(8));
				tempbeanMedicine.setC_manufacturer(resultSet.getString(9));
				tempbeanMedicine.setHsn_code(resultSet.getString(10));
				beanMedicine.add(tempbeanMedicine);	
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return beanMedicine;
	}
//this code will override the method of csvPath 
	@Override
	public String csvPath(String path) 
	{
		//this operation will create the connection object
		Connection  connection1,connection=null;
		//this operation will create the statement object
		Statement statement=null;
		//this operation will create the resultSet object
		ResultSet resultSet=null;
		String status=null;
		//this operation will create the prepareStatement object
		PreparedStatement prepareStatement=null;
		
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				connection1 = DriverManager.getConnection("jdbc:Text:///"+path+"");
				connection=MedicineConnectionFactory.getConnection();
				if(connection1 !=null) {
					//this operation will create the statement object
					statement=connection1.createStatement();
					if(statement !=null) {
						//this operation will do the execution
						resultSet=statement.executeQuery(CSV_SELECT);
						if(resultSet !=null) {
							//this operation will create the prepareStatement object
							prepareStatement=connection.prepareStatement(CSV_INSERT);
							while (resultSet.next()) {
								if(prepareStatement !=null) {
									prepareStatement.setString(1, resultSet.getString(1));
									prepareStatement.setString(2, resultSet.getString(2));
									prepareStatement.setString(3, resultSet.getString(3));
									prepareStatement.setString(4, resultSet.getString(4));
									prepareStatement.setString(5, resultSet.getString(5));
									prepareStatement.setString(6, resultSet.getString(6));
									prepareStatement.setString(7, resultSet.getString(7));
									prepareStatement.setString(8, resultSet.getString(8));
									prepareStatement.setString(9, resultSet.getString(9));
									prepareStatement.setString(10, resultSet.getString(10));
									int rowAffect=prepareStatement.executeUpdate();
									if(rowAffect>0) {
										
										status= "success";
									}
									else {
										status="failure";
									}
									
									
								}
							
							}
						}
					}
					
				}
			
			
			} catch (SQLException e) {
				if	(e.getErrorCode()==1062) {
					status="duplicate";
				}else {
					status="failure";
				}
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			return status;		
	}
	//this code will override the method of orders 
	@Override
	public String orders(String medicineId) 
	{
		//this operation will create the connection object
		Connection connection=null;
		Statement statement=null;
		ResultSet resultSet=null;
		String order=null;
		
		
		connection=MedicineConnectionFactory.getConnection();
		try {
			
			statement=connection.createStatement();
			String SQLQuery="select * from medicinelist where c_unique_code='"+medicineId+"'";
			resultSet=statement.executeQuery(SQLQuery);
			
			if(resultSet.next() ==true) 
			{
				
					order="success";
					}
				
				else {
					order="failure";
				}
	
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return order;
		
		
	}
	//this code will override the method of insert 
	@Override
	public String insert(String MedicineId, String MedicineName1, String MedicineOrder)
	{
		//this operation will create the connection object
		
		Connection connection1=null;
		PreparedStatement prepareStatement=null;
		String order=null;
		try {
		connection1=MedicineConnectionFactory.getConnection();
		prepareStatement= connection1.prepareStatement(ORDER_INSERT);
		prepareStatement.setString(1,MedicineName1);
		prepareStatement.setString(2, MedicineId);
		prepareStatement.setString(3, MedicineOrder);
		int rowAffect=prepareStatement.executeUpdate();
		if(rowAffect !=0) 
		{
			order="success";
		}else {
			order="failure";
		}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return order;
}
	//this code will override the method of orderDetails 
	@Override
	public List<BeanMedicine> orderDetails(String c_unique_code) 
	{
		//this operation will create the connection object
		Connection connection=null;
		//this operation will create the Statement object
		Statement statement=null;
		//this operation will create the ResultSet object
		ResultSet resultSet=null;
		//this operation will create the beanMedicine2 object
		ArrayList<BeanMedicine> beanMedicine2=null;
		String ORDER_SELECT="select * from orders where c_unique_code ="+ c_unique_code +"";
		try {
		connection=MedicineConnectionFactory.getConnection();
	
	if(connection !=null) {
			statement=connection.createStatement();
	if(statement !=null) {				
		resultSet=statement.executeQuery(ORDER_SELECT);
			if(resultSet.next()){
				 beanMedicine2=new ArrayList<BeanMedicine>();						
					BeanMedicine tempbeanMedicine=new BeanMedicine();
					tempbeanMedicine.setC_name(resultSet.getString(1));
					tempbeanMedicine.setC_unique_code(resultSet.getString(2));
					tempbeanMedicine.setOrder(resultSet.getString(3));
					tempbeanMedicine.setN_balance_qty(resultSet.getString(4));
					beanMedicine2.add(tempbeanMedicine);
					
				}
								}
			}} catch (SQLException e) {
			
				e.printStackTrace();
		}
	return beanMedicine2;
}
	//this code will override the method of search 
	@Override
	public List<BeanMedicine> search(String c_name) 
	{
		//this operation will create the connection object
		Connection connection=null;
		//this operation will create the statement object
		Statement statement=null;
		//this operation will create the resultSet object
		ResultSet resultSet=null;
		//this operation will create the beanMedicine2 object
		ArrayList<BeanMedicine> beanMedicine2=null;
		String ORDER_SELECT="select  c_name,c_unique_code from medicinelist where c_name = '"+ c_name +"'";
		try {
		connection=MedicineConnectionFactory.getConnection();
	
	if(connection !=null) {
			statement=connection.createStatement();
	if(statement !=null) {				
		resultSet=statement.executeQuery(ORDER_SELECT);
		 beanMedicine2=new ArrayList<BeanMedicine>();	
		 //this code will perform the creation of bean object
			BeanMedicine tempbeanMedicine=new BeanMedicine();
			while(resultSet.next()){
				
					tempbeanMedicine.setC_name(resultSet.getString(1));
					tempbeanMedicine.setC_unique_code(resultSet.getString(2));
					beanMedicine2.add(tempbeanMedicine);
					
				}
								}
			}} catch (SQLException e) {
			
				e.printStackTrace();
		}
	return beanMedicine2;
	}
}


	

	


