package com.example.sykrosstore.controller;

import com.example.sykrosstore.Config.CustomConfig;
import com.example.sykrosstore.constants.ServerResponse.CustomResponseEntity;
import com.example.sykrosstore.constants.ServerResponse.Error.ApiError;
import com.example.sykrosstore.services.Interfaces.IRoleService;
import com.example.sykrosstore.untils.Helper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
  ObjectNode resBody = CustomResponseEntity.getObjectNode();

  @Autowired CustomConfig customConfig;

  IRoleService roleService;

  @Autowired
  public TestController(IRoleService roleService) {
    this.roleService = roleService;
  }

  @Value("${cust.datasource.MYSQL_PASS}")
  String pass;

  @GetMapping("/greeting")
  public String Hello() {
    return customConfig.getDatasource().getMYSQL_PASS();
  }

  @GetMapping("/seed-roles")
  public CustomResponseEntity seedRoles() {
    try {
      String responseMsg = this.roleService.seedRoleData();
      CustomResponseEntity.putKeyValue(resBody, "message", responseMsg);
      CustomResponseEntity customResponseEntity = new CustomResponseEntity(resBody, HttpStatus.OK);
      return customResponseEntity;
    } catch (Exception e) {
      ApiError apiError = new ApiError(resBody);
      CustomResponseEntity.putKeyValue(resBody, "message", apiError.getMessage());
      return new CustomResponseEntity(apiError, apiError.getHttpStatus());
    }
  }

  @GetMapping("/TestGenericFunction")
  public CustomResponseEntity testGenericFunc(){
    ObjectNode resBody = CustomResponseEntity.getObjectNode();
    try {
      String responseMsg = this.roleService.Test();
      CustomResponseEntity.putKeyValue(resBody, "message", responseMsg);
      CustomResponseEntity customResponseEntity = new CustomResponseEntity(resBody, HttpStatus.OK);
      return customResponseEntity;
    } catch (Exception e) {
      ApiError apiError = new ApiError(resBody);
      CustomResponseEntity.putKeyValue(resBody, "message", apiError.getMessage());
      return new CustomResponseEntity(apiError, apiError.getHttpStatus());
    }
  }

  @GetMapping("/testPath")
  public CustomResponseEntity testPath(){
    ObjectNode resBody = CustomResponseEntity.getObjectNode();
    try {
      List<String> responseMsg = this.roleService.PathTest();
      Helper h = new Helper(Object.class);
      CustomResponseEntity.putKeyValue(resBody, "message", h.ConvertListToString(responseMsg));
      CustomResponseEntity customResponseEntity = new CustomResponseEntity(resBody, HttpStatus.OK);
      return customResponseEntity;
    } catch (Exception e) {
      ApiError apiError = new ApiError(resBody);
      CustomResponseEntity.putKeyValue(resBody, "message", apiError.getMessage());
      return new CustomResponseEntity(apiError, apiError.getHttpStatus());
    }
  }
}
