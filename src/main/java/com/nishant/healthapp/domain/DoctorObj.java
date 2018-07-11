package com.nishant.healthapp.domain;

import com.google.gson.Gson;

public class DoctorObj {
	public DoctorObj(String id, String firstName, String lastName, String speciality) {
		this.id=id;
		this.firstName=firstName;
		this.lastName=lastName;
		this.speciality=speciality;
	}
	
	String id;
	String firstName;
	String lastName;
	String speciality;
	@Override
	public String toString() {
		Gson gson = new Gson();
		String jsonDoc = gson.toJson(this);
		return jsonDoc;
	
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getSpeciality() {
		return speciality;
	}
	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}

}
