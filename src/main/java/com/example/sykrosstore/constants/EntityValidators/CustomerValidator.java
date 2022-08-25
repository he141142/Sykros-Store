package com.example.sykrosstore.constants.EntityValidators;

public class CustomerValidator {
  public static final String EMAIL_MANDATORY = "Email is mandatory!";
  public static final String LAST_NAME_MANDATORY = "Last name is mandatory!";
  public static final String FIRST_NAME_MANDATORY = "First name is mandatory!";
  public static final String EMAIL_INCORRECT_FORMAT = "Email is incorrect format!";

  //
  //  public static String getFieldMandatory(Customer customer) {
  //    switch (customer) {
  //      case EMAIL:
  //        return getMandatoryFieldMessage("email");
  //      case FIRST_NAME:
  //        return getMandatoryFieldMessage("first name");
  //      case LAST_NAME:
  //        return getMandatoryFieldMessage("last name");
  //      case PASSWORD:
  //        return getMandatoryFieldMessage("password");
  //      default:
  //        return "Field doesn't exist!";
  //    }
  //  }
  //
  //  public static String getMandatoryFieldMessage(String field) {
  //    return "Field: " + field + " is mandatory!";
  //  }
  //
  //  @Override
  //  public String getMessageError(String field, String error) {
  //    return null;
  //  }
}
