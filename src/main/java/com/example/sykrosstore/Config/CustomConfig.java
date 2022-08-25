package com.example.sykrosstore.Config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Configuration
@ConfigurationProperties(prefix = "cust")
public class CustomConfig {
  private String temp;
  public static class Datasource {
    private String MYSQL_HOST;
    private String MYSQL_PORT;
    private String MYSQL_DATABASE;
    private String MYSQL_USER;
    private String MYSQL_PASS;

    public String getMYSQL_HOST() {
      return MYSQL_HOST;
    }

    public void setMYSQL_HOST(String MYSQL_HOST) {
      this.MYSQL_HOST = MYSQL_HOST;
    }

    public String getMYSQL_PORT() {
      return MYSQL_PORT;
    }

    public void setMYSQL_PORT(String MYSQL_PORT) {
      this.MYSQL_PORT = MYSQL_PORT;
    }

    public String getMYSQL_DATABASE() {
      return MYSQL_DATABASE;
    }

    public void setMYSQL_DATABASE(String MYSQL_DATABASE) {
      this.MYSQL_DATABASE = MYSQL_DATABASE;
    }

    public String getMYSQL_USER() {
      return MYSQL_USER;
    }

    public void setMYSQL_USER(String MYSQL_USER) {
      this.MYSQL_USER = MYSQL_USER;
    }

    public String getMYSQL_PASS() {
      return MYSQL_PASS;
    }

    public void setMYSQL_PASS(String MYSQL_PASS) {
      this.MYSQL_PASS = MYSQL_PASS;
    }

    // standard getters and setters
  }

  public String getTemp() {
    return temp;
  }

  public void setTemp(String temp) {
    this.temp = temp;
  }

  public void setDatasource(Datasource datasource) {
    this.datasource = datasource;
  }

  private Datasource datasource;

  public Datasource getDatasource() {
    return datasource;
  }
}
