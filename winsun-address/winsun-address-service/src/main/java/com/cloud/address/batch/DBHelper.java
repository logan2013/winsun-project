package com.cloud.address.batch;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.stereotype.Component;

import com.cloud.address.jpa.model.JpaAddress;
import com.cloud.address.jpa.repository.JpaAddressRepository;

@Component
public class DBHelper {

	@Autowired
	JpaAddressRepository jpa;
	
	
	@Autowired
	ElasticsearchTemplate es;
	/**
	 * mysql传输到elasticsearch
	 * @param str
	 */
	public void testSimpleLogImportBuilderFromExternalDBConfig(){
//		Pageable pageable = PageRequest.of(0, 1000);
//		Page<JpaAddress> page = jpa.findAll(pageable);
//		List<JpaAddress> content = page.getContent();
		
		List<JpaAddress> content = jpa.findAll();
		
		
		List<IndexQuery> query = new ArrayList<IndexQuery>();
		for (JpaAddress jpaAddress : content) {
			IndexQuery indexQuery = new IndexQueryBuilder().withId(String.valueOf(jpaAddress.getId())).withObject(jpaAddress).build();
			query.add(indexQuery);
		}
		
		es.bulkIndex(query);
	}
	
	public void testbatchAdd(List<JpaAddress> address) {
		jpa.saveAll(address);
	}

	
}
