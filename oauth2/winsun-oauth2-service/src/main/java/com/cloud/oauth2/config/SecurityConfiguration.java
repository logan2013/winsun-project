package com.cloud.oauth2.config;

import java.util.Collection;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.InMemoryUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import com.cloud.oauth2.interceptor.URLInterceptor;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	
	

	@Bean(name = BeanIds.AUTHENTICATION_MANAGER)
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	
//    @Bean
//    @Override
//    protected UserDetailsService userDetailsService(){
//    	
//    	Collection<? extends GrantedAuthority> authorities= AuthorityUtils.createAuthorityList("USER");
//    	UserDetails user = new User("user_1", "{noop}" + "123456", true,
//        true, true, true, authorities);
//    	
//    	Collection<? extends GrantedAuthority> authorities2= AuthorityUtils.createAuthorityList("USER","ADMIN");
//    	UserDetails user2 = new User("user_2", "{noop}" + "123456", true,
//        true, true, true, authorities2);
//    	
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        manager.createUser(user);
//        manager.createUser(user2);
//        return manager;
//    }
//    
    @Bean
    public static NoOpPasswordEncoder passwordEncoder() {
      return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http
            .requestMatchers().anyRequest()
            .and()
                .authorizeRequests()
                .antMatchers("/oauth/*").permitAll();
        // @formatter:on
    }
    
  

}

