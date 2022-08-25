package com.example.sykrosstore.Config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.context.annotation.Bean;

public class ThymeleafConfiguration {
    @Bean(name = "thymeLeafExtension")
    public ThymeLeafExtension thymeLeafExtension(){
      return new ThymeLeafExtension() {

        @Override
        public String getHello() {
          return "hello";
        }


        @Override
        public List<Integer> listNumber() {
          return Arrays.asList(1,2,3,4);
        }

        @Override
        public String getProgress(int authority){
            return authority <=20?"bg-success":
                authority <=50 ? "bg-warning" : "bg-danger";
        }

      };
    }

    public interface ThymeLeafExtension{
       String getHello();
       List<Integer> listNumber();
       String getProgress(int authority);
    }
}
