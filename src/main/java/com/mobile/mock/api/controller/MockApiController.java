package com.mobile.mock.api.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class MockApiController {

	private static final Map<String, String> pathJsonMap = new LinkedHashMap<String, String>() {
		private static final long serialVersionUID = 1L;
		{
			put("/unitTrust/securitiesAccounts", "json/securityAccResp.json");
			put("/unitTrust/utmip/orderStatus/mip", "json/orderStatusResp.json");
			put("/unitTrust/utmip/orderStatus/mip/contributionHistory", "json/orderStatusHistoryResp.json");
			put("/unitTrust/utmip/enquiry/riskAssessmentInfo", "json/riskAssInfoResp.json");
			put("/unitTrust/utmip/subscription/init", "json/subInit.json");
			put("/unitTrust/utmip/subscription/validation", "json/subValidation.json");
			put("/unitTrust/utmip/subscription/complete", "json/subComplete.json");
			put("/security/otp/initialization", "json/otpInit.json");
			put("/security/otp/validation", "json/otpValidation.json");
		}
	};

	@RequestMapping(value = "/{p1}/{p2}", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String twoPath(@PathVariable String p1, @PathVariable String p2) throws IOException {

		return readAllBytesJava7(p1, p2);

	}

	@RequestMapping(value = "/{p1}/{p2}/{p3}", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String threePath(@PathVariable String p1, @PathVariable String p2, @PathVariable String p3)
			throws IOException {

		return readAllBytesJava7(p1, p2, p3);
	}

	@RequestMapping(value = "/{p1}/{p2}/{p3}/{p4}", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String fourPath(@PathVariable String p1, @PathVariable String p2, @PathVariable String p3,
			@PathVariable String p4) throws IOException {

		return readAllBytesJava7(p1, p2, p3, p4);
	}

	@RequestMapping(value = "/{p1}/{p2}/{p3}/{p4}/{p5}", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String fivePath(@PathVariable String p1, @PathVariable String p2, @PathVariable String p3,
			@PathVariable String p4, @PathVariable String p5) throws IOException {

		return readAllBytesJava7(p1, p2, p3, p4, p5);
	}

	private String readAllBytesJava7(String... args) throws IOException {

		List<String> list = Arrays.asList(args);
		String key = "/" + String.join("/", list);

		System.out.println("readAllBytesJava7 key: " + key);

		ClassLoader classLoader = MockApiController.class.getClassLoader();
		InputStream inputStream = classLoader.getResourceAsStream(pathJsonMap.get(key));
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