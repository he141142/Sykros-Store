package com.example.sykrosstore.constants.EntityValidators.ConstantInterface;

public interface IValidatorEntity<T extends Enum> {
//  String  getFieldMandatory(T enumOfClass);


  String getMessageError(String field, String error);

  default String getMessageError(String msg) {
    return "Unknown err!";
  }
  ;
}
