package com.example.sykrosstore.Config;

import java.nio.charset.StandardCharsets;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

public class MVCConfig implements WebMvcConfigurer {
  @Bean
  public SpringTemplateEngine springTemplateEngine(ApplicationContext applicationContext) {
    SpringTemplateEngine springTemplateEngine = new SpringTemplateEngine();
    springTemplateEngine.addDialect(new SpringSecurityDialect());
    springTemplateEngine.addTemplateResolver(emailTemplateResolver());
    return springTemplateEngine;
  }

  public ClassLoaderTemplateResolver emailTemplateResolver() {
    ClassLoaderTemplateResolver emailTemplateResolver = new ClassLoaderTemplateResolver();
    emailTemplateResolver.setPrefix("/templates/");
    emailTemplateResolver.setSuffix(".html");
    emailTemplateResolver.setTemplateMode(TemplateMode.HTML);
    emailTemplateResolver.setCharacterEncoding(StandardCharsets.UTF_8.name());
    emailTemplateResolver.setCacheable(false);
    return emailTemplateResolver;
  }

  @Bean
  public ClassLoaderTemplateResolver CustomTemplateResolver() {
    ClassLoaderTemplateResolver configurer = new ClassLoaderTemplateResolver();
//        configurer.setPrefix("templates/");
    configurer.setSuffix(".html");
    configurer.setTemplateMode(TemplateMode.HTML);
    configurer.setCharacterEncoding("UTF-8");
    configurer.setOrder(0);  // this is important. This way spring //boot will listen to both places 0 and 1
    configurer.setCheckExistence(true);
    return configurer;
  }
}
