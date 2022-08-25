package com.example.sykrosstore.services.Interfaces;

import com.example.sykrosstore.dto.GenresLoader;
import com.example.sykrosstore.entities.Genres;
import com.example.sykrosstore.entities.Subgenres;
import java.util.List;

public interface IGenresService {
  String InitialLoadGenres() throws Exception;
  List<Genres> loadAllGenres();
  GenresLoader getGenresReadyModel();
}


