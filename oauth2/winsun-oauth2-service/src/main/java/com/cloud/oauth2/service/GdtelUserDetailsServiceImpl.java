package com.cloud.oauth2.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cloud.oauth2.jpa.UserRepestiory;

import java.util.Collection;

@Service("gdtelUserDetailsService")
public class GdtelUserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UserRepestiory userRepestiory;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        

    	com.cloud.oauth2.entity.User winsun_user = userRepestiory.findUserByName(username);
        Collection<? extends GrantedAuthority> authorities
                = AuthorityUtils.createAuthorityList(winsun_user.getUser_role().split(","));
        UserDetails user = new User(winsun_user.getUser_name(), winsun_user.getUser_password(), true,
        		
                true, true, true, authorities);

        return user;
    }
}
