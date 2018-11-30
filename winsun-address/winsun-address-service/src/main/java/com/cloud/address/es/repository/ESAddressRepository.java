package com.cloud.address.es.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.cloud.address.es.model.ESAddress;


@Repository
public interface ESAddressRepository extends ElasticsearchRepository<ESAddress, String>{

}
