package com.nishant.healthapp.domain;

import java.util.List;
import java.util.stream.Collectors;

import com.google.gson.Gson;

public class DoctorList {

	List<DoctorObj> doctorList;
	
	public DoctorList(List<DoctorObj> doctorList) {
		this.doctorList=doctorList;
	}

	public DoctorList findDoctorsBySpeciality(String speciality) {
		return new DoctorList(doctorList.stream().filter( x -> speciality.equals(x.getSpeciality())).collect(Collectors.toList()));
	}
	@Override
	public String toString() {
		Gson gson = new Gson();
		return gson.toJson(this);
	}

	public List<DoctorObj> getDoctorList() {
		return doctorList;
	}

	public void setDoctorList(List<DoctorObj> doctorList) {
		this.doctorList = doctorList;
	}

}
