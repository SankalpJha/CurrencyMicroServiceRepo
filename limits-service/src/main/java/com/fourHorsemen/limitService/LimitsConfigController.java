package com.fourHorsemen.limitService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitsConfigController {

	@Autowired
	Configuration config;
	
	@GetMapping("/limits")
	public LimitConfiguration retriveLimitFromConfiguration() {
		
		return new LimitConfiguration(config.getMaximum(),config.getMinimum());
	}
}
