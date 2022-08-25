package com.example.sykrosstore.constants.ServerResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

public class CustomResponseEntity extends ResponseEntity<Object> {

  public static ObjectNode getObjectNode() {
    ObjectMapper mapper = new ObjectMapper();
    ObjectNode responseBody = mapper.createObjectNode();
    return responseBody;
  }

  public static void putKeyValue(ObjectNode node,String key,String value){
    node.put(key,value);
  }

  public CustomResponseEntity(HttpStatus status) {
    super(status);
  }

  public CustomResponseEntity(Object body, HttpStatus status) {
    super(body, status);
  }


  public CustomResponseEntity(MultiValueMap<String, String> headers, HttpStatus status) {
    super(headers, status);
  }

  public CustomResponseEntity(
      Object body, MultiValueMap<String, String> headers, HttpStatus status) {
    super(body, headers, status);
  }

  public CustomResponseEntity(Object body, MultiValueMap<String, String> headers, int rawStatus) {
    super(body, headers, rawStatus);
  }
}
