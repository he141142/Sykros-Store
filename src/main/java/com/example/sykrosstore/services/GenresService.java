package com.example.sykrosstore.services;

import com.example.sykrosstore.Repositories.GenresRepository;
import com.example.sykrosstore.constants.Folderpath;
import com.example.sykrosstore.constants.ServerResponse.Success.GenresController.GenresMessage;
import com.example.sykrosstore.dto.GenresLoader;
import com.example.sykrosstore.entities.Genres;
import com.example.sykrosstore.entities.Subgenres;
import com.example.sykrosstore.services.Interfaces.IGenresService;
import com.example.sykrosstore.untils.Helper;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenresService implements IGenresService {

  Helper<Genres> genresHelper = new Helper<>(Genres.class);
  @Autowired GenresRepository genresRepository;

  @Override
  @Transactional
  public String InitialLoadGenres() throws Exception {
    try {
      List<String> genresNames =
          genresHelper.getSubFolderNameFromDirectory(Folderpath.GENRES_DIRECTORY);
      List<Genres> genresList =
          genresNames.stream()
              .map(
                  name -> {
                    Genres g = new Genres();
                    g.setName(name);
                    return g;
                  })
              .collect(Collectors.toList());

      genresList =
          genresList.stream()
              .map(
                  mainGenres -> {
                    List<Subgenres> subgenresList =
                        genresHelper
                            .getSubFolderNameFromDirectory(
                                Folderpath.GENRES_DIRECTORY + "/" + mainGenres.getName())
                            .stream()
                            .map(
                                subGenresName -> {
                                  Subgenres subgenres = new Subgenres();
                                  subgenres.setName(subGenresName);
                                  subgenres.setDescription("This is description!");
                                  return subgenres;
                                })
                            .collect(Collectors.toList());
                    mainGenres.addSubGenres(subgenresList);
                    return mainGenres;
                  })
              .collect(Collectors.toList());
      genresRepository.saveAll(genresList);
      return GenresMessage.INITIAL_LOAD_SUCCESS;
    } catch (Exception e) {
      throw new Exception(GenresMessage.INITIAL_LOAD_FAILED);
    }
  }

  @Override
  public List<Genres> loadAllGenres() {
    return (List<Genres>) genresRepository.findAll();
  }

  @Override
  public GenresLoader getGenresReadyModel() {
    List<Genres> allGenres = this.loadAllGenres();
    GenresLoader genresLoader = new GenresLoader((ArrayList<Genres>) allGenres);
    return genresLoader;
  }
}
