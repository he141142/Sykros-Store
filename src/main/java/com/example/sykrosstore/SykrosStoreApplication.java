package com.example.sykrosstore;

import com.example.sykrosstore.Config.CustomConfig;
import com.example.sykrosstore.Config.MainConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.example.sykrosstore.untils", "com.example.sykrosstore"})
@EnableConfigurationProperties(CustomConfig.class)
public class SykrosStoreApplication {


  public static void main(String[] args) {
    SpringApplication.run(SykrosStoreApplication.class, args);
  }

}
