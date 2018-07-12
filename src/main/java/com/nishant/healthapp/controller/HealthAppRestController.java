package com.nishant.healthapp.controller;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nishant.healthapp.controller.HealthApplicationJspController.UserInfo;
import com.nishant.healthapp.dao.UserDAO;
import com.nishant.healthapp.domain.DoctorList;
import com.nishant.healthapp.domain.DoctorObj;
import com.nishant.healthapp.domain.User;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@Transactional(propagation=Propagation.REQUIRED)
public class HealthAppRestController {
	final static Logger logger = 
			   LoggerFactory.getLogger(HealthAppRestController.class);
	
	@Autowired
	UserDAO user;
	
//	@RequestMapping(value = "/user", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	UserInfo health(@RequestBody UserInfo user) {
		user.setFirstname("Mr. " + user.getFirstname());

		System.out.println(user);

		return user;
	}

	
//	@RequestMapping(value = "/login", method = RequestMethod.GET, produces = "application/json")
	String login() {
		logger.debug("Inside Login Method of rest Controller");
		return "login";
	}
	
	@RequestMapping(value = "/doctors/find", method = RequestMethod.GET, produces = "application/json")
	public List<User> findDoctors(@RequestParam(value = "email", required = false) String email,
			@RequestParam(value = "password", required = false) String password) {
		
		logger.debug("email ="+email);
		logger.debug("password ="+password);
		List<User> doctors = (password == null) ? user.findByEmail(email)
				: user.findByEmailAndPassword(email, password);
		logger.debug(doctors.toString());
		
		return doctors;
	}
	

//	@RequestMapping(value = "/doctors", method = RequestMethod.GET, produces = "application/json")
	DoctorList searchDoctorsBySpeciality(@RequestParam(value = "speciality", required = false) String speciality) {
		List<DoctorObj> doctorList = new ArrayList<>();
		doctorList.add(new DoctorObj("1", "Nishant", "Chauhan", "Java"));
		doctorList.add(new DoctorObj("2", "Sneha", "Nagpure", "Testing"));
		doctorList.add(new DoctorObj("3", "Sayali", "Baviskar", "Testing"));

		DoctorList specialityDoctors = new DoctorList(doctorList).findDoctorsBySpeciality(speciality);

		logger.debug(specialityDoctors.toString());

		return specialityDoctors;
	}

}
