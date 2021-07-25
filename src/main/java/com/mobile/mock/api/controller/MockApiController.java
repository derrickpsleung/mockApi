package com.mobile.mock.api.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MockApiController {

	@GetMapping("/{p1}/{p2}")
	@ResponseBody
	public String twoPath(@PathVariable String p1, @PathVariable String p2) throws IOException {

		String val = readAllBytesJava7("json/login.json");
		return val;
	}

	private String readAllBytesJava7(String classPath) throws IOException {

		ClassLoader classLoader = MockApiController.class.getClassLoader();
		InputStream inputStream = classLoader.getResourceAsStream(classPath);
		String data = readFromInputStream(inputStream);

		return data;
	}

	private String readFromInputStream(InputStream inputStream) throws IOException {
		StringBuilder resultStringBuilder = new StringBuilder();
		try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
			String line;
			while ((line = br.readLine()) != null) {
				resultStringBuilder.append(line).append("\n");
			}
		}
		return resultStringBuilder.toString();
	}
}