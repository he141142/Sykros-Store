package com.example.sykrosstore.constants.ServerResponse.Error;

import com.example.sykrosstore.constants.ServerResponse.ApiResponse;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.http.HttpStatus;

public class ApiError extends ApiResponse {
  String message;

  public ApiError(HttpStatus httpStatus, ObjectNode body, String message) {
    super(body, httpStatus);
    this.message = message;
  }

  public ApiError(ObjectNode body){
    super(body,HttpStatus.BAD_REQUEST);

  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
