package com.cloud.address.es.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

@Document(indexName = "winsun",type = "address",shards = 5,replicas = 1,refreshInterval = "-1")
public class ESAddress {

	@Id
	private String Id;
	
	@Field(index = false)
	private String grid_code;
	
	@Field(index = true,searchAnalyzer = "ik_max_word",analyzer = "ik_max_word")
	private String address;
	
	@Field(index = false)
	private Integer addr_level;
	
//	@Field(index = true,searchAnalyzer = "ik_max_word",analyzer = "ik_max_word")
//	private Object sugtext;

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getGrid_code() {
		return grid_code;
	}

	public void setGrid_code(String grid_code) {
		this.grid_code = grid_code;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getAddr_level() {
		return addr_level;
	}

	public void setAddr_level(Integer addr_level) {
		this.addr_level = addr_level;
	}
	
	
	
}
