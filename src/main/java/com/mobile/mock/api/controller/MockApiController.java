package com.mobile.mock.api.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MockApiController {

	private static final Map<String, String> pathJsonMap = new LinkedHashMap<String, String>() {
		private static final long serialVersionUID = 1L;
		{
			put("key1", "json/login.json");
			put("key2", "json/login.json");
		}
	};

	@GetMapping("/{p1}/{p2}")
	@ResponseBody
	public String twoPath(@PathVariable String p1, @PathVariable String p2) throws IOException {

		List<String> list = Arrays.asList(p1, p2);
		String key = String.join(",", list);
		return readAllBytesJava7(pathJsonMap.get(key));

	}

	@GetMapping("/{p1}/{p2}/{p3}")
	@ResponseBody
	public String threePath(@PathVariable String p1, @PathVariable String p2, @PathVariable String p3)
			throws IOException {

		List<String> list = Arrays.asList(p1, p2, p3);
		String key = String.join(",", list);
		return readAllBytesJava7(pathJsonMap.get(key));
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