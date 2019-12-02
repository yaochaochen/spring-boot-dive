package com.security.dive.config;

import com.security.dive.exception.SimpleAccessDeniedHandler;
import com.security.dive.exception.SimpleAuthenticationEntryPoint;
import com.security.dive.filter.JsonLoginPostProcessor;
import com.security.dive.filter.JwtAuthenticationFilter;
import com.security.dive.filter.LoginPostProcessor;
import com.security.dive.filter.PreLoginFilter;
import com.security.dive.handler.CustomLogoutHandler;
import com.security.dive.handler.CustomLogoutSuccessHandler;
import com.security.dive.jwt.JwtTokenGenerator;
import com.security.dive.jwt.JwtTokenStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;
import java.util.Collection;

@Configuration
@ConditionalOnClass(WebSecurityConfigurerAdapter.class)
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
public class CustomSpringBootWebSecurityConfiguration {


    private static final String LOGIN_PROCESSING_URL = "/process";


    @Bean
    public JsonLoginPostProcessor jsonLoginPostProcessor() {
        return new JsonLoginPostProcessor();
    }

    @Bean
    public PreLoginFilter preLoginFilter(Collection<LoginPostProcessor> loginPostProcessors) {
        return new PreLoginFilter(LOGIN_PROCESSING_URL, loginPostProcessors);
    }

    /**
     * Jwt 认证过滤器.
     *
     * @param jwtTokenGenerator jwt 工具类 负责 生成 验证 解析
     * @param jwtTokenStorage   jwt 缓存存储接口
     * @return the jwt authentication filter
     */
    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter(JwtTokenGenerator jwtTokenGenerator, JwtTokenStorage jwtTokenStorage) {
        return new JwtAuthenticationFilter(jwtTokenGenerator, jwtTokenStorage);
    }


    @Configuration
    @Order(SecurityProperties.BASIC_AUTH_ORDER)
    static class DefaultConfigurerAdapter extends WebSecurityConfigurerAdapter {

        @Resource
        private PreLoginFilter preLoginFilter;

        @Autowired
        private AuthenticationSuccessHandler authenticationSuccessHandler;

        @Autowired
        private AuthenticationFailureHandler authenticationFailureHandler;

        @Autowired
        private JwtAuthenticationFilter jwtAuthenticationFilter;


        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            super.configure(auth);
        }

        @Override
        public void configure(WebSecurity web) throws Exception {
            super.configure(web);
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.csrf().disable()
                    .cors()
                    .and()
                    //session生成策略 无状态策略
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                    //统一异常
                    .exceptionHandling().accessDeniedHandler(new SimpleAccessDeniedHandler())
                    .authenticationEntryPoint(new SimpleAuthenticationEntryPoint())
                    .and()
                    .authorizeRequests().antMatchers("/foo/test").hasAnyRole("ADMIN")
                    .and()
                    .authorizeRequests().anyRequest().authenticated()
                    .and()
                    //多方式登录
                    .addFilterBefore(preLoginFilter, UsernamePasswordAuthenticationFilter.class)
                    //jwt必须配置在UsernamePasswordAuthenticationFilter之前
                    .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                    //表单提交
                    .formLogin().loginProcessingUrl(LOGIN_PROCESSING_URL)
                    .successForwardUrl("/login/success")
                    //token信息
                    .successHandler(authenticationSuccessHandler)
                    .failureForwardUrl("/login/failure")
                    .failureHandler(authenticationFailureHandler)
                    //退出
                    .and()
                    .logout()
                    .addLogoutHandler(new CustomLogoutHandler())
                    .logoutSuccessHandler(new CustomLogoutSuccessHandler());


        }
    }


}
