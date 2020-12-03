package com.example.orderserver.auth;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;


@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

  //注册自身的资源ID
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId("order_app");
    }

    /**
     * 资源服务器的安全配置
     * @param http
     * @throws Exception
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().
                antMatchers("/order/find/**").access("#oauth2.hasScope('all')")//表示 资源服务器的该请求 需要令牌有读权限
                .and()
                .authorizeRequests().antMatchers("/order/save").access("#oauth2.hasScope('write')")//表示资源服务器的该请求 需要令牌有写权限
                .and()
                .authorizeRequests().antMatchers("/item").permitAll();//表示不需要令牌也可以访问.
    }

}
