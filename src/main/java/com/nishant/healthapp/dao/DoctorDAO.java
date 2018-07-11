package com.nishant.healthapp.dao;


import java.util.List;

import com.nishant.healthapp.domain.Doctor;

public interface DoctorDAO {
	
	List<Doctor> findAll();
	
	List<Doctor> findBySpecialityCode(String code);
	
	int findAllCount();
	
	List<Doctor> findByLocation(String location);
	
	List<Doctor> findByHospital(String hospitalName);
	
	Doctor findByUserId(int userId);

	Doctor save(Doctor doctor);
}
