package com.nishant.healthapp.services;

import java.util.List;

import com.nishant.healthapp.domain.Doctor;
import com.nishant.healthapp.domain.User;


public interface DoctorService {
	
	void save(Doctor doctor);
	
	List<Doctor> findBySpeciality(String specialityCode);
	
	List<Doctor> findByLocation(String location);
	
	List<Doctor> findByHospital(String hospitalName);

	List<Doctor> findAll();
	
	Doctor findByUserEmailAddress(String email);
	
	int findCount();
	
	Doctor findByUserId(int userId);

	void addDoctor(User user);
}
