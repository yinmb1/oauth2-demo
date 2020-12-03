package com.example.oauthserver.config;

import com.example.oauthserver.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter  {

    @Autowired
    UserDetailService userDetailService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;


    //添加商户信息
    public void configure(ClientDetailsServiceConfigurer configurer) throws Exception {

        //withClient Appid
        configurer
                .inMemory()
                    .withClient("order_app")
                    .secret(passwordEncoder.encode("order_app")) //
                    .authorizedGrantTypes("password","client_credentials","refresh_token")
                    .scopes("all"); //设置权限类型,用密码，客户端,刷新的token  权限为所有人

    }

    //定义授权和令牌端点和令牌服务
//    public void configure(AuthorizationServerEndpointsConfigurer endpointsConfigurer){
//
//        //刷新令牌时需要的认证管理和用户信息来源
//        endpointsConfigurer.authenticationManager(authenticationManager);
//     //   endpointsConfigurer.userDetailsService(userDetailsService());
//
//    }


    //配置端点信息
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
            endpoints
                    //用户管理
                    .userDetailsService(userDetailService)
                    .authenticationManager(authenticationManager);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {

        //允许表单认证
        oauthServer.allowFormAuthenticationForClients();

        //允许 check_token 访问
        oauthServer.checkTokenAccess("permitAll()");
    }

//    @Bean
//    AuthenticationManager authenticationManager() {
//        AuthenticationManager authenticationManager = new AuthenticationManager() {
//            public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//                return daoAuhthenticationProvider().authenticate(authentication);
//            }
//        };
//        return authenticationManager;
//    }

//    @Bean
//    public AuthenticationProvider daoAuhthenticationProvider() {
//        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
//        daoAuthenticationProvider.setUserDetailsService(userDetailsService());
//        daoAuthenticationProvider.setHideUserNotFoundExceptions(false);
//        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
//        return daoAuthenticationProvider;
//    }

//    // 设置添加用户信息,正常应该从数据库中读取
//    @Bean
//    UserDetailsService userDetailsService() {
//        InMemoryUserDetailsManager userDetailsService = new InMemoryUserDetailsManager();
//        userDetailsService.createUser(User.withUsername("user_1").password(passwordEncoder().encode("123456"))
//                .authorities("ROLE_USER").build());
//        userDetailsService.createUser(User.withUsername("user_2").password(passwordEncoder().encode("123456"))
//                .authorities("ROLE_USER").build());
//        return userDetailsService;
//    }

//    @Bean
//    PasswordEncoder passwordEncoder() {
//        // 加密方式
//        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        return passwordEncoder;
//    }


}
