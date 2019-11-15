package com.security.dive.configrue;


import com.security.dive.handler.MyAuthenticationFailureHandler;
import com.security.dive.handler.MyAuthenticationSucessHandler;
import com.security.dive.validate.code.ValidateCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyAuthenticationSucessHandler authenticationSucessHandler;

    @Autowired
    private MyAuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    private ValidateCodeFilter validateCodeFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class) //验证码
                .formLogin()//表单登录
                .loginPage("/authentication/require")
                .loginProcessingUrl("/login")
                .successHandler(authenticationSucessHandler)
                .failureHandler(authenticationFailureHandler)
                .and().authorizeRequests().antMatchers("\"/authentication/require\",\n" +
                "                            \"/login.html\", \"/code/image\"").permitAll().anyRequest().authenticated().and().csrf().disable();
    }
}
