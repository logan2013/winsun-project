package com.cloud.address.jpa.model;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.elasticsearch.annotations.Document;


@Document(indexName = "winsun",type = "address",shards = 5,replicas = 1,refreshInterval = "-1")
@Entity
@Table(name = "address_info")
public class JpaAddress {

	@Id
	private int id;

	private String grid_code;
	
	private String address;
	
	private Integer addr_level;


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
