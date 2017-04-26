package indi.learn.persistence.services;

import java.sql.Date;

import org.junit.BeforeClass;
import org.junit.Test;

import com.google.gson.Gson;

import indi.learn.beans.StudentPO;

public class StudentServiceTest {
	private static StudentService studentService;
	
	private static Gson gson;
	
	@BeforeClass
	public static void setup() {
		studentService = new StudentService();
		gson = new Gson();
	}

	@Test
	public void findStudentByIdTest() {
		StudentPO po = studentService.findStudentById(1);
		System.out.println(gson.toJson(po));
	}
	
	@Test
	public void updateStudentTest() {
		StudentPO po = new StudentPO();
		po.setId(2);
		po.setName("yinkailin");
		po.setEmail("yinkailin@zbj.com");
		po.setDob(new Date(System.currentTimeMillis()));
		int affectedRows = studentService.updateStudent(po);
		System.out.println("update: " + gson.toJson(affectedRows));
	}
	
	@Test
	public void createStudentTest() {
		StudentPO po = new StudentPO();
//		po.setStudId("1");
		po.setName("yinkailin");
		po.setEmail("yinkailin@zbj.com");
		po.setDob(new Date(System.currentTimeMillis()));
		int affectedRows = studentService.createStudentRecord(po);
		System.out.println("create " + gson.toJson(affectedRows));
	}
	
	
	@Test
	public void deleteStudentById() {
		int affectedRows = studentService.deleteStudent(1);
		System.out.println("delete: " + gson.toJson(affectedRows));
	}

}
