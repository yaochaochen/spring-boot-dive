package com.security.dive.configrue;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()//表单登录
                .and()
                .authorizeRequests()//授权配置
                .anyRequest()//所有请求
                .authenticated();//认证范围
    }
}
