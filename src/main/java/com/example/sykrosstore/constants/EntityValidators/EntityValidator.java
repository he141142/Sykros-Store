package com.example.sykrosstore.constants.EntityValidators;

import com.example.sykrosstore.constants.EntityValidators.ConstantInterface.IValidatorEntity;

public class EntityValidator {
  public static CustomerValidator customerValidator;

  public static CustomerValidator getCustomerValidator() {
    return customerValidator;
  }

  public static void setCustomerValidator(
      CustomerValidator customerValidator) {
    EntityValidator.customerValidator = customerValidator;
  }
}
