package com.example.sykrosstore.constants.ServerResponse.Success;

import com.example.sykrosstore.constants.ServerResponse.ApiResponse;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.http.HttpStatus;

public class ApiSuccess extends ApiResponse {
  String msg;

  public ApiSuccess(ObjectNode responseBody, HttpStatus httpStatus) {
    super(responseBody, httpStatus);
  }

  public ApiSuccess(ObjectNode responseBody) {
    super(responseBody, HttpStatus.OK);
  }
}
