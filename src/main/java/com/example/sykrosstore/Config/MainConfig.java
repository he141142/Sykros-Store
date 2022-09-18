package com.example.sykrosstore.Config;

import lombok.CustomLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
public class MainConfig {

  @Autowired
  CustomConfig customConfig;

  public String getHostName(){
    return customConfig.getDatasource().getMYSQL_PASS();
  }
}
