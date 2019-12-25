package com.opentica.inventory.app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Controller for consuming open API's
 *
 */
@RestController
@RequestMapping("/api/open")
public class OpenAPIController {
	//We are using API from https://openweathermap.org/ to get the current weather information
	//Regfer https://openweathermap.org/current for more infomration
	private static final String WEATHER_API = "https://samples.openweathermap.org/data/2.5/weather";

	@GetMapping(path = "getWeather")
	public String getCurrentWeather(@RequestParam String cityName) {
		//Create API with parameters
		final String uri = WEATHER_API + "?q=" + cityName + "&appid=testApp";
		RestTemplate restTemplate = new RestTemplate();
		String result = restTemplate.getForObject(uri, String.class);
		return result;
	}

}
