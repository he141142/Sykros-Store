package com.example.sykrosstore.constants.ServerResponse;

import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ApiResponse {
  ObjectNode responseBody;
  HttpStatus httpStatus;

  public ApiResponse(ObjectNode responseBody, HttpStatus httpStatus) {
    this.responseBody = responseBody;
    this.httpStatus = httpStatus;
  }
}
