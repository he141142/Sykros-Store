package com.example.sykrosstore.controller.RestfulApi;

import com.example.sykrosstore.constants.ServerResponse.CustomResponseEntity;
import com.example.sykrosstore.controller.RestfulApi.dto.GenresDto;
import com.example.sykrosstore.dto.GenresLoader;
import com.example.sykrosstore.services.Interfaces.BookService;
import com.example.sykrosstore.services.Interfaces.IGenresService;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("book")
public class AdminRestController {
  private IGenresService genresService;
  private BookService bookService;

  @Autowired
  public AdminRestController(IGenresService genresService, BookService bookService) {
    this.genresService = genresService;
    this.bookService = bookService;
  }

  @PostMapping("/initial-load-genres")
  public CustomResponseEntity initialLoadBook() throws Exception {
    this.bookService.loadBooks();
    return null;
  }


  @PostMapping("/load-genres")
  public List<GenresDto> loadGenres(){
     return this.genresService.loadGenresFrontEnd();
  }

}
