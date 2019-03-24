package com.fourHorsemen.xChangeRepo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fourHorsemen.xChangeModel.ExchangeValue;

public interface ExchangeValueRepo extends JpaRepository<ExchangeValue, Long>{

	ExchangeValue findByFromAndTo(String from, String to);
}
