package com.cloud.address.es.service.impl;

import java.util.List;

import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import com.cloud.address.batch.DBHelper;
import com.cloud.address.es.model.ESAddress;
import com.cloud.address.es.repository.ESAddressRepository;
import com.cloud.address.es.service.ElasticsearchAddressService;

@Service
public class ElasticsearchAddressServiceImpl implements ElasticsearchAddressService{
	
	@Autowired
	ElasticsearchTemplate es;
	
	@Autowired
	ESAddressRepository esAddressRepository;

	@Autowired
	DBHelper db;

	@Override
	public List<ESAddress> addressQuery(String address, Integer page, Integer size) {
		Pageable pageable = PageRequest.of(page -1, size);
		FunctionScoreQueryBuilder functionScoreQueryBuilder = 
				QueryBuilders.functionScoreQuery(QueryBuilders.fuzzyQuery("address", address));
		
		SearchQuery searchQuery = new NativeSearchQueryBuilder().withPageable(pageable)
				.withQuery(functionScoreQueryBuilder).withSort(SortBuilders.scoreSort().order(SortOrder.DESC)).build();
		
		Page<ESAddress> search = esAddressRepository.search(searchQuery);
		return search.getContent();
	}

	@Override
	public List<ESAddress> addressMatch(String address, Integer page, Integer size) {
		Pageable pageable = PageRequest.of(page -1, size);

		FunctionScoreQueryBuilder functionScoreQueryBuilder = 
				QueryBuilders.functionScoreQuery(QueryBuilders.matchQuery("address", address).operator(Operator.AND));
		
		SearchQuery searchQuery = new NativeSearchQueryBuilder().withPageable(pageable)
				.withQuery(functionScoreQueryBuilder).withSort(SortBuilders.scoreSort().order(SortOrder.DESC)).build();
		
		Page<ESAddress> search = esAddressRepository.search(searchQuery);
		
		return search.getContent();
	}

}
