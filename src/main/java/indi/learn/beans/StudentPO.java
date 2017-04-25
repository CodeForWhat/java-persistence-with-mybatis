package indi.learn.beans;

import java.sql.Date;

import org.apache.ibatis.type.Alias;

@Alias("student")
public class StudentPO {
	private int id;
	private String studId;
	private String name;
	private String email;
	private String phoneNumber;
	private String address;
	private Date dob;

	public StudentPO() {

	}

	public StudentPO(String studId, String name, String email, String phoneNumber, String address) {
		this.studId = studId;
		this.name = name;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.address = address;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStudId() {
		return studId;
	}

	public void setStudId(String studId) {
		this.studId = studId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	@Override
	public String toString() {
		return "StudentPO [id=" + id + ", studId=" + studId + ", name=" + name + ", email=" + email + ", phoneNumber="
				+ phoneNumber + ", address=" + address + ", dob=" + dob + "]";
	}
}
