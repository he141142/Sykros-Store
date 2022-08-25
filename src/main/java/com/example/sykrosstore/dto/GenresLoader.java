package com.example.sykrosstore.dto;

import com.example.sykrosstore.entities.Genres;
import com.example.sykrosstore.entities.Subgenres;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Data;

@Data
public class GenresLoader {
  ArrayList<Genres> genresArrayList;
  List<Subgenres> fictionList;
  List<Subgenres> noFictionList;

  public GenresLoader(ArrayList<Genres> genresArrayList) {
    this.genresArrayList = genresArrayList;
    if (this.genresArrayList.size()!=0){
      this.fictionList = this.getSubGenres(com.example.sykrosstore.constants.Genres.GENRES_FICTION);
      this.noFictionList =
          this.getSubGenres(com.example.sykrosstore.constants.Genres.GENRES_NO_FICTION);
    }
  }

  private List<Subgenres> getSubGenres(String subGenres) {
    return this.genresArrayList.stream()
        .filter(genres -> genres.getName().equalsIgnoreCase(subGenres))
        .collect(Collectors.toList())
        .get(0)
        .getSubgenres();
  }
}
