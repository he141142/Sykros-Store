package com.example.sykrosstore.Config;

import java.util.Arrays;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;


@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
  // To enable CORS
  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
    final CorsConfiguration configuration = new CorsConfiguration();

    configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000")); // www - obligatory
//        configuration.setAllowedOrigins(ImmutableList.of("*"));  //set access from all domains
    configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
    configuration.setAllowCredentials(true);
    configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));

    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);

    return source;
  }
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests().antMatchers("/resources/**").permitAll();

    http.authorizeRequests()
        // C??c trang kh??ng y??u c???u login nh?? v???y ai c??ng c?? th??? v??o ???????c
        .antMatchers("/","/register", "login", "*/candidate/*").permitAll()
        .antMatchers(HttpMethod.POST).permitAll()
        // Trang ch??? d??nh cho ADMIN
//        .antMatchers("/admin/**").access("hasAnyRole('ADMIN','ROLE_ADMIN')")
        // Trang ch??? d??nh cho COMPANY
        .antMatchers("/company/**").access("hasAnyRole('COMPANY','ROLE_COMPANY','ROLE_ADMIN')")
        // C???u h??nh cho Login Form.
        // Submit URL c???a trang login
//        .and().formLogin().loginPage("/login").successHandler(new CustomLoginSuccessHandler())
//        .and().formLogin().failureUrl("/fail_login")
//        // C???u h??nh cho Logout Page. Khi logout m??nh tr??? v??? trang
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
