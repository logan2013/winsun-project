package com.cloud.oauth2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

@Configuration
public class OAuth2ServerConfig {

    private static final String DEMO_RESOURCE_ID = "oauth2";

    @Configuration
    @EnableResourceServer
    protected static class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
    	
    	@Autowired
        RedisConnectionFactory redisConnectionFactory;

        @Override
        public void configure(ResourceServerSecurityConfigurer resources) {
            resources.resourceId(DEMO_RESOURCE_ID).stateless(true).tokenStore(new RedisTokenStore(redisConnectionFactory));
        }

        @Override
        public void configure(HttpSecurity http) throws Exception {
            // @formatter:off
            http
                    // Since we want the protected resources to be accessible in the UI as well we need
                    // session creation to be allowed (it's disabled by default in 2.0.6)
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                    .and()
                    .requestMatchers().anyRequest()
                    .and()
                    .anonymous()
                    .and()
                    .authorizeRequests()
//                    .antMatchers("/product/**").hasRole("ADMIN")
                    .antMatchers("/product/**").authenticated()
                    .antMatchers("/order/**").authenticated()
                    .and()
                    .csrf().disable();//配置order访问控制，必须认证过后才可以访问
            // @formatter:on
        }
    }

    @Configuration
    @EnableAuthorizationServer
    protected static class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

        @Autowired
        AuthenticationManager authenticationManager;
        @Autowired
        RedisConnectionFactory redisConnectionFactory;
        @Autowired
        SourceConfig sourceConfig;
        @Autowired
    	UserDetailsService gdtelUserDetailsService;

        @Override
        public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
            //配置两个客户端,一个用于password认证一个用于client认证
//            clients.inMemory().withClient("client_1")
//                    .resourceIds(DEMO_RESOURCE_ID,DEMO_RESOURCE_ID2)
//                    .authorizedGrantTypes("client_credentials", "refresh_token")
//                    .scopes("select")
//                    .authorities("ADMIN","USER")
//                    .secret("123456")
//                    .and().withClient("client_2")
//                    .resourceIds(DEMO_RESOURCE_ID,DEMO_RESOURCE_ID2)
//                    .authorizedGrantTypes("password", "refresh_token")
//                    .scopes("select")
//                    .authorities("client")
//                    .secret("123456");
        	
        	JdbcClientDetailsService clientDetailsService = new JdbcClientDetailsService(sourceConfig.dataSource());
    		clientDetailsService.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
    		clients.withClientDetails(clientDetailsService);
        }

        @Override
        public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
            endpoints
            		.allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST)
                    .tokenStore(new RedisTokenStore(redisConnectionFactory))
                    .authenticationManager(authenticationManager)
                    .userDetailsService(gdtelUserDetailsService);;
        }

        @Override
        public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
            //允许表单认证
            oauthServer.allowFormAuthenticationForClients()
            .tokenKeyAccess("permitAll()")
			.checkTokenAccess("isAuthenticated()");
        }

    }

}
