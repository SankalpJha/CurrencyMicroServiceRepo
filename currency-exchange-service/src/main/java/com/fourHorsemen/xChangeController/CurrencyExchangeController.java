package com.fourHorsemen.xChangeController;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.fourHorsemen.xChangeModel.ExchangeValue;
import com.fourHorsemen.xChangeRepo.ExchangeValueRepo;

@RestController
public class CurrencyExchangeController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private Environment env;
	
	@Autowired
	private ExchangeValueRepo evRepo;
	
	@GetMapping("currency-exchange/from/{from}/to/{to}")
	public ExchangeValue retriveExchangeValue(@PathVariable String from, @PathVariable String to) {
		
		ExchangeValue exchangeVal = evRepo.findByFromAndTo(from, to);
		exchangeVal.setPortNum(Integer.parseInt(env.getProperty("local.server.port")));
		
		logger.info("{}", exchangeVal);
		
		return exchangeVal;
	}
}
