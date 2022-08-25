package com.example.sykrosstore.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests().antMatchers("/resources/**").permitAll();

    http.authorizeRequests()
        // Các trang không yêu cầu login như vậy ai cũng có thể vào được
        .antMatchers("/","/register", "login", "*/candidate/*").permitAll()
        .antMatchers(HttpMethod.POST).permitAll()
        // Trang chỉ dành cho ADMIN
//        .antMatchers("/admin/**").access("hasAnyRole('ADMIN','ROLE_ADMIN')")
        // Trang chỉ dành cho COMPANY
        .antMatchers("/company/**").access("hasAnyRole('COMPANY','ROLE_COMPANY','ROLE_ADMIN')")
        // Cấu hình cho Login Form.
        // Submit URL của trang login
//        .and().formLogin().loginPage("/login").successHandler(new CustomLoginSuccessHandler())
//        .and().formLogin().failureUrl("/fail_login")
//        // Cấu hình cho Logout Page. Khi logout mình trả về trang
        .and().logout().logoutUrl("/auth/logout")
        .invalidateHttpSession(true).and().cors().and().csrf().disable();



//        http.authorizeRequests().and() //
//                .rememberMe().tokenRepository(this.persistentTokenRepository()) //
//                .1tokenValiditySeconds(1 * 24 * 60 * 60); // 24h
  }

  @Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring().antMatchers("/resources/**");
  }
}
