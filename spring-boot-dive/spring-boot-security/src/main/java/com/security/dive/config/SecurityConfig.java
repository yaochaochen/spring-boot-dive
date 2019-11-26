package com.security.dive.config;


import com.security.dive.handler.MyAuthenticationFailureHandler;
import com.security.dive.handler.MyAuthenticationSucessHandler;
import com.security.dive.handler.session.MySessionExpiredStrategy;
import com.security.dive.validate.code.ValidateCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

//@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyAuthenticationSucessHandler authenticationSuccessHandler;

    @Autowired
    private MyAuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    private ValidateCodeFilter validateCodeFilter;

    @Autowired
    private MySessionExpiredStrategy sessionExpiredStrategy;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class) //验证码
                .formLogin()//表单登录
                .loginPage("/authentication/require")
                .loginProcessingUrl("/login")
                .successHandler(authenticationSuccessHandler)
                .failureHandler(authenticationFailureHandler)
                .and().authorizeRequests().antMatchers("\"/authentication/require\",\n" +//无需认证接口
                "                            \"/login.html\", \"/code/image\"").permitAll().anyRequest().authenticated()
                .and().sessionManagement()//添加Session管理器
                .invalidSessionUrl("/session/invalid")
                .maximumSessions(1)
                .maxSessionsPreventsLogin(true)
                .expiredSessionStrategy(sessionExpiredStrategy)
                .and()
                .and().csrf().disable();
    }
}
