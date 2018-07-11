package com.nishant.healthapp;

import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.nishant.healthapp.controller.UserAccountController;
import com.nishant.healthapp.domain.User;
import com.nishant.healthapp.helpers.ExecutionStatus;
import com.nishant.healthapp.services.UserService;

@ExtendWith(SpringExtension.class)
@Tag("Controller")
public class UserAccountControllerTest {

	private MockMvc mockMvc;
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@MockBean
	private UserService userService;

	@BeforeEach
	public void setUp(TestInfo testInfo) throws Exception {
		this.mockMvc = MockMvcBuilders.standaloneSetup(new UserAccountController(this.userService)).build();
	}

	@Test
	@DisplayName("Should return error message for when user not existing   in the database tries to login.")
	public void Should_ReturnErrorMessage_ForUnmatchingUser() throws Exception {

		User user = new User();
		user.setEmail("foo@bar.com");
		user.setPassword("foobar");
		//
		// Create JSON Representation for User object;
		// Gson is a Java serialization/deserialization library to
		// convert Java Objects into JSON and back
		//
		Gson gson = new Gson();
		String jsonUser = gson.toJson(user);
		//
		// Pre-program the behavior of Mock; When isValidUser method
		// is invoked, return null object
		//
		Mockito.when(this.userService.isValidUser("foo@bar.com", "foobar")).thenReturn(null);
		//
		// Invoking the controller method
		//
		MvcResult result = this.mockMvc
				.perform(post("/account/login/process").contentType(MediaType.APPLICATION_JSON).content(jsonUser))
				.andExpect(status().isOk()).andReturn();
		//
		// Verify the program behavior; Assert the response object
		//
		MockHttpServletResponse response = result.getResponse();
		ObjectMapper mapper = new ObjectMapper();
		logger.debug("=====================================================================================");
		logger.debug(response.getContentAsString());
		logger.debug("=====================================================================================");
		ExecutionStatus responseObj = mapper.readValue(response.getContentAsString(), ExecutionStatus.class);
		assertTrue(responseObj.getCode().equals("USER_LOGIN_UNSUCCESSFUL"));
		assertTrue(responseObj.getMessage().equals("Username or password is incorrect. Please try again!"));
	}
}