package com.example.sykrosstore.controller;

import com.example.sykrosstore.constants.ServerResponse.CustomResponseEntity;
import com.example.sykrosstore.constants.ServerResponse.Error.ApiError;
import com.example.sykrosstore.dto.GenresLoader;
import com.example.sykrosstore.entities.Genres;
import com.example.sykrosstore.entities.Role;
import com.example.sykrosstore.entities.Subgenres;
import com.example.sykrosstore.services.Interfaces.IGenresService;
import com.example.sykrosstore.services.Interfaces.IRoleService;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin")
public class AdminController {
  ObjectNode resBody = CustomResponseEntity.getObjectNode();

  @Qualifier("genresService")
  private IGenresService genresService;
  IRoleService roleService;
  @Autowired
  public AdminController(IGenresService genresService, IRoleService roleService) {
    this.genresService = genresService;
    this.roleService = roleService;
  }


  @GetMapping("/home")
  public String adminPage() {
    System.out.println("Entry");
    return "/Admin/index";
  }

  @GetMapping("/loadGenres")
  public String genresPage(Model model) {
    GenresLoader genresLoader = this.genresService.getGenresReadyModel();
    if (genresLoader.getGenresArrayList().size()!=0){
      model.addAttribute("fiction", genresLoader.getFictionList());
      model.addAttribute("noFiction", genresLoader.getNoFictionList());
    }
    model.addAttribute("genresList", genresLoader.getGenresArrayList());
    return "/Admin/Pages/InitialLoad/load-genres";
  }

  @GetMapping("/roleLoading")
  public String rolePage(Model model) {
    List<Role> roles = this.roleService.getRolesList();
    model.addAttribute("roles",roles);
    return "/Admin/Pages/InitialLoad/load-role";
  }



  @PostMapping("/seed-roles")
  public CustomResponseEntity seedRoles() {
    try {
      String responseMsg = this.roleService.seedRoleData();
      CustomResponseEntity.putKeyValue(resBody, "message", responseMsg);
      return new CustomResponseEntity(resBody, HttpStatus.OK);
    } catch (Exception e) {
      System.out.println(e);
      ApiError apiError = new ApiError(resBody);
      CustomResponseEntity.putKeyValue(resBody, "message", apiError.getMessage());
      return new CustomResponseEntity(apiError, apiError.getHttpStatus());
    }
  }


  @PostMapping("/seed-genres")
  public CustomResponseEntity seedGenres() {
    try {
      String responseMsg = this.genresService.InitialLoadGenres();
      CustomResponseEntity.putKeyValue(resBody, "message", responseMsg);
      return new CustomResponseEntity(resBody, HttpStatus.OK);
    } catch (Exception e) {
      System.out.println(e);
      ApiError apiError = new ApiError(resBody);
      CustomResponseEntity.putKeyValue(resBody, "message", apiError.getMessage());
      return new CustomResponseEntity(apiError, apiError.getHttpStatus());
    }
  }
}
