package indi.learn.persistence.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import indi.learn.beans.StudentPO;

public class PersistenceWithJDBC {
	
	// database parameters needed for creating a connection 
	private static final String DRIVER_CLASS_NAME = "com.mysql.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://localhost/learn_mybatis";
	private static final String USER_NAME = "KevinYin";
	private static final String PASSWORD = "yinkailin";
	
	private static final Connection connection; 
	
	
	// static block to make sure that there is only one Connection instance
	static {
		try {
			// must load driver at first
			Class.forName(DRIVER_CLASS_NAME);
			
			// then create a connection instance using DriverManager utility object
			connection = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	// persist a batch of PO objects
	public static int persistObjects(List<StudentPO> poList) {
		// sql string with parameter holders
		final String SQL_WITH_HOLDER = "insert into student_info(stu_id, stu_name, stu_email, stu_phone_number, stu_address) values(?, ?, ?, ?, ?);";
		
		PreparedStatement statement = null;
		int affectedRows = 0;
		try {
			statement = connection.prepareStatement(SQL_WITH_HOLDER);
			
			// using jdbc batch insert mechanism
			for(StudentPO po : poList) {
				statement.setString(1, po.getStudId());
				statement.setString(2, po.getName());
				statement.setString(3, po.getEmail());
				statement.setString(4, po.getPhoneNumber());
				statement.setString(5, po.getAddress());
//				System.out.println(statement.toString());
				statement.addBatch();
			}
			
			int[] stateCode = statement.executeBatch();
			
			// analyze the result code of all sql statements and 
			// calculate the number of rows which have been affected.
			for(int state : stateCode) {
				affectedRows += (state > 0 ? 1 : 0);
			}
			
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
		return affectedRows;
	}
	
	
	/*
	 * query all records and convert to PO objects
	 * */
	public static List<StudentPO> readObjects() {
		final String SQL_STR = "select * from student_info";
		
		Statement statement = null;
		List<StudentPO> listObjects = new ArrayList<StudentPO>();
		
		
		try {
			statement = connection.createStatement();
			
			ResultSet result = statement.executeQuery(SQL_STR);
			
			for(;result.next();) {
				// parse records to PO object one by one
				StudentPO po = new StudentPO();
				po.setId(result.getInt("id"));
				po.setStudId(result.getString("stu_id"));
				po.setName(result.getString("stu_name"));
				po.setPhoneNumber(result.getString("stu_phone_number"));
				po.setEmail(result.getString("stu_email"));
				po.setAddress(result.getString("stu_address"));
				listObjects.add(po);
			}
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
		return listObjects;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(PersistenceWithJDBC.readObjects());
		
		
		StudentPO po1 = new StudentPO("222012321210178", "HaoPeng", "haopeng@swu.edu.cn", "18883245694", "swu taoyuan no3");
		StudentPO po2 = new StudentPO("222012321210194", "SuShixuan", "SuShixuan@swu.edu.cn", "18883276352", "swu taoyuan no4");
		StudentPO po3 = new StudentPO("222012321210119", "ZhangQi", "ZhangQi@swu.edu.cn", "18883266588", "swu taoyuan no5");
		
		System.out.println(PersistenceWithJDBC.persistObjects(Arrays.asList(po1, po2, po3)));
	}

}
