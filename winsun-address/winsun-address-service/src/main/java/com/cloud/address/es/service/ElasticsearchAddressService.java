package com.cloud.address.es.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.cloud.address.es.model.ESAddress;

@Service
public interface ElasticsearchAddressService {
	
	public List<ESAddress> addressQuery(String address,
			Integer page,
			Integer size);
	
	public List<ESAddress> addressMatch(String address,
			Integer page,
			Integer size);

}
