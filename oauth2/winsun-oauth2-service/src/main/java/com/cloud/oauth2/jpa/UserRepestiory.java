package com.cloud.oauth2.jpa;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cloud.oauth2.entity.User;

@Repository
public interface UserRepestiory extends CrudRepository<User, String>{

    @Query("select u from User u where u.user_name = ?1")
	User findUserByName(String username);
	
}
