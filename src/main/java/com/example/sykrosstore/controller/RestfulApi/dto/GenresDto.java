package com.example.sykrosstore.controller.RestfulApi.dto;

import java.util.ArrayList;
import lombok.Data;

@Data
public class GenresDto {

  String title;
  ArrayList<String> subGenresName;
  public  GenresDto( String title,
  ArrayList<String> subGenresName){
    this.subGenresName = subGenresName;
    this.title=title;
  }

  public  GenresDto( String title){
    this.title=title;
    this.subGenresName = new ArrayList<>();
  }

  public void addSubGenres(String subGenres){
    this.subGenresName.add(subGenres);
  }


}
