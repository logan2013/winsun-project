package com.cloud.address.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cloud.address.jpa.model.JpaAddress;

@Repository
public interface JpaAddressRepository extends JpaRepository<JpaAddress, String>{
	

}
