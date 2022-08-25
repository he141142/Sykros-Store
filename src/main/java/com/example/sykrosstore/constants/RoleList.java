package com.example.sykrosstore.constants;

import java.util.ArrayList;
import java.util.Arrays;

public class RoleList {
  public static final String ADMIN = "ADMIN";
  public static final String USER = "USER";
  public static final String GUEST = "GUESTS";
  public static final String PUBLISHER = "PUBLISHER";
  public static final String AUTHOR = "AUTHOR";
  public static final String CUSTOMER = "CUSTOMER";

  public ArrayList<String> getRoles() {
    return new ArrayList<>(Arrays.asList(ADMIN, PUBLISHER, USER, GUEST, AUTHOR, CUSTOMER));
  }
}
