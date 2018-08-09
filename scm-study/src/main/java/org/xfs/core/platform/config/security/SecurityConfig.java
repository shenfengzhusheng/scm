package org.xfs.core.platform.config.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity  
@EnableGlobalMethodSecurity(prePostEnabled = true)  
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
    private static Md5PasswordEncoder md5Encoder = new Md5PasswordEncoder();  
    @Autowired  
    private DataSource dataSource;  
    @Autowired  
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {  
        auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(md5Encoder);  
    }  
    
	/**
	 * 无安全内容
	 */
	@Override
	public void configure(WebSecurity web)throws Exception{
		 web.ignoring().antMatchers("/resource/**");  
	}
	
	
	 @Override  
	 protected void configure(HttpSecurity http) throws Exception {  
		 
	 }
}
