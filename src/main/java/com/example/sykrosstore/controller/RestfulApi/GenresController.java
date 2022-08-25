package com.example.sykrosstore.controller.RestfulApi;

import com.example.sykrosstore.constants.ServerResponse.CustomResponseEntity;
import com.example.sykrosstore.constants.ServerResponse.Error.ApiError;
import com.example.sykrosstore.services.Interfaces.IGenresService;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("genres")
public class GenresController {

  IGenresService genresService;

  @Autowired
  public GenresController(IGenresService genresService) {
    this.genresService = genresService;
  }

  @GetMapping("initialLoad")
  public CustomResponseEntity initialLoad() {
    ObjectNode resBody = CustomResponseEntity.getObjectNode();
    try {
      String serviceResponse = this.genresService.InitialLoadGenres();
      CustomResponseEntity.putKeyValue(resBody, "message", serviceResponse);
      CustomResponseEntity customResponseEntity = new CustomResponseEntity(resBody, HttpStatus.OK);
      return customResponseEntity;
    } catch (Exception e) {
      ApiError apiError = new ApiError(resBody);
      CustomResponseEntity.putKeyValue(resBody, "message", apiError.getMessage());
      return new CustomResponseEntity(apiError, apiError.getHttpStatus());
    }
  }
}
