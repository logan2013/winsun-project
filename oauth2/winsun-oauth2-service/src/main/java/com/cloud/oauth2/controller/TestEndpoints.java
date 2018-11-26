package com.cloud.oauth2.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.oauth2.entity.User;
import com.cloud.oauth2.jpa.UserRepestiory;

@RestController
public class TestEndpoints {
	
	@Autowired
	UserRepestiory user;
	
	@RequestMapping("/current")
    public Principal user(Principal user) {
        return user;
    }

	@PreAuthorize("hasAuthority('USER')")
    @GetMapping("/product/{id}")
    public String getProduct(@PathVariable String id) {
        //for debug
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return "product id : " + id;
    }

	@PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/order/{id}")
    public String getOrder(@PathVariable String id) {
        //for debug
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return "order id : " + id;
    }
	
	@GetMapping("/query/user")
	public User queryUser(@RequestParam("username")String username) {
		return user.findUserByName(username);
	}

}

