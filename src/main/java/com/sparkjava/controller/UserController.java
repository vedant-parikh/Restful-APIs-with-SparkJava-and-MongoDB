package com.sparkjava.controller;

import static spark.Spark.*;

import java.io.IOException;
import java.io.StringWriter;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.sparkjava.dao.UserDAO;
import com.sparkjava.vo.UserVO;

public class UserController {
	private static final int HTTP_BAD_REQUEST = 400;
	private static final UserDAO userDAO = new UserDAO();
	
	public UserController() {
		
		post("/createUser", (request, response) -> {
			try {
				ObjectMapper mapper = new ObjectMapper();
				UserVO user = mapper.readValue(request.body(), UserVO.class);
				if (!user.isValid()) {
					response.status(HTTP_BAD_REQUEST);
					return "";
				}
				if(userDAO.createUser(user)) {
					response.status(200);
					response.type("application/text");
					return user.getId();
				} else {
					return "User Already Exist!";
				}
			} catch (JsonParseException jpe) {
				response.status(HTTP_BAD_REQUEST);
				return "";
			}
		});

		get("/getAllUsers", (request, response) -> {
			response.status(200);
			response.type("application/json");
			return dataToJson(userDAO.getAllUsers());
		});

		post("/updateUser", (request, response) -> {
			try {
				ObjectMapper mapper = new ObjectMapper();
				UserVO user = mapper.readValue(request.body(), UserVO.class);
				if (!user.isValid()) {
					response.status(HTTP_BAD_REQUEST);
					return "";
				}
				if(userDAO.updateUser(user)) {
					response.status(200);
					response.type("application/text");
					return user.getId();
				} else {
					return "User Not Found!";
				}
			} catch (JsonParseException jpe) {
				response.status(HTTP_BAD_REQUEST);
				return "";
			}
		});
	}

	public static String dataToJson(Object data) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.enable(SerializationFeature.INDENT_OUTPUT);
			StringWriter sw = new StringWriter();
			mapper.writeValue(sw, data);
			return sw.toString();
		} catch (IOException e) {
			throw new RuntimeException("IOException from a StringWriter?");
		}
	}
}