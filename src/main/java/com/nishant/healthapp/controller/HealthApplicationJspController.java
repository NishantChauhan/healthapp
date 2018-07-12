package com.nishant.healthapp.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HealthApplicationJspController{

	public class UserInfo {
		public String getFirstname() {
			return firstname;
		}
		public void setFirstname(String firstname) {
			this.firstname = firstname;
		}
		public String getLastname() {
			return lastname;
		}
		public void setLastname(String lastname) {
			this.lastname = lastname;
		}
		public String firstname = "Nishant";
		public String lastname = "Chauhan";
	}

	@RequestMapping("/")
	String home() {
		return "index";
	}

	@RequestMapping("/index")
	String index() {
		return "index.html";
	}
	
//	@RequestMapping("/model")
	ModelAndView model(ModelMap model, @RequestParam("mode") String mode) {

		switch (mode) {

		case "JSP":
			// Will result in display of index.jsp page

			return new ModelAndView("index");

		case "JSPKey":

			// Will result in display of index.jsp page.
			// The JSP page could consist of code such as "Hello ${name}"
			// which will get displayed as "Hello Nishant Chauhan"

			return new ModelAndView("index", "name", "Nishant Chauhan");

		case "JSPModelObject":

			// Will result in display of index.jsp page.
			// The JSP page could consist of code such as
			// "Hello ${model.firstName} ${model.lastName}"
			// which will get displayed as "Hello Nishant Chauhan"

			return new ModelAndView("index", "model", new UserInfo());

		case "JSPMap":

			// Will result in display of index.jsp page.
			// The JSP page could consist of code such as "Hello ${name}"
			// which will get displayed as "Hello Nishant Chauhan"

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("name", "Nishant Chauhan");
			return new ModelAndView("index", map);
		}
		return null;
	}

}
