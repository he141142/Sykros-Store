package com.example.sykrosstore.untils;

import com.example.sykrosstore.constants.RoleList;
import com.example.sykrosstore.entities.Account;
import com.example.sykrosstore.entities.Role;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;

public class Helper<T> {
  final Class<T> t;

  public Helper(Class<T> typeParameterClass) {
    this.t = typeParameterClass;
  }


  public static ArrayList<String> rolesHaveEmail =
      new ArrayList<>(Arrays.asList(RoleList.PUBLISHER, RoleList.CUSTOMER));


  public boolean checkRoleHaveEmail(String role) {
    ArrayList<String> rolesHaveEmail =
        new ArrayList<>(Arrays.asList(RoleList.PUBLISHER, RoleList.USER));
    return rolesHaveEmail.stream().anyMatch(roleElm -> roleElm.equalsIgnoreCase(role));
  }

  public static boolean checkRoleHaveEmailStatic(String role) {
    return rolesHaveEmail.stream().anyMatch(roleElm -> roleElm.equalsIgnoreCase(role));
  }

  public static boolean checkRoleHaveEmailStaticByAccount(Account account) {
    return account.getRoles().stream()
        .map(Role::getName)
        .anyMatch(Helper::checkRoleHaveEmailStatic);
  }

  public static Role getRoleFromJson(JsonParser parser) throws IOException {
    Role role = new Role();
    while (parser.nextToken() != JsonToken.END_OBJECT) {
      String token = parser.getCurrentName();
      if (token.equalsIgnoreCase("name")) {
        parser.nextToken();
        role.setName(parser.getText());
      }
      if (token.equalsIgnoreCase("description")) {
        parser.nextToken();
        role.setDescription(parser.getText());
      }
    }
    return role;
  }

  public static Role getRoleSimpleJsonParser(JSONObject jsonObject) {
    String name = (String) jsonObject.get("name");
    String description = (String) jsonObject.get("description");
    int authority = ((Long) jsonObject.get("authorities")).intValue();
    Role role = new Role();
    role.setName(name);
    role.setAuthority(authority);
    role.setDescription(description);
    return role;
  }

  public T getObjectSimpleJsonParser(JSONObject jsonObject)
      throws InstantiationException, IllegalAccessException {
    Class<?> getClass = t.getClass();
    System.out.println("entry");
    System.out.println(this.t.getName());
    Field[] fields = getClass.getFields();
    System.out.println(fields.length);
    List<Field> list = Arrays.asList(fields);
    T tInstance =  t.newInstance();
    list.forEach(
        field -> {
          try {
            System.out.println(field.getName());
            this.callSetter(
                t,
                field.getName(),
                convertInstanceOfObject(field.getType())
                    .getClass()
                    .cast(jsonObject.get(field.getName())));
            t.getClass().getField(field.getName());
          } catch (NoSuchFieldException e) {
            e.printStackTrace();
          }
        });
    return tInstance;
  }

  public void printObjectClass(ArrayList<T> tList) {
    System.out.println("=========================================");
    List<Field> listField = Arrays.asList(this.t.getClass().getFields());
    tList.forEach(
        type -> {
          listField.forEach(
              field -> {
                System.out.println("Field Name: " + field.getName());
                try {
                  System.out.println("Field value: " + this.callGetter(type, field.getName()));
                } catch (Exception e) {
                  e.printStackTrace();
                }
              });
        });
  }

  public void displayRoles(ArrayList<Role> roles) {
    System.out.println("=========================================");
    System.out.format("%-20s%-20s\n", "name", "description");
    for (Role r : roles) {
      System.out.format("%-20s%-20s\n", r.getName(), r.getDescription());
    }
  }

  public void callSetter(Object obj, String fieldName, Object value) {
    PropertyDescriptor pd;
    try {
      pd = new PropertyDescriptor(fieldName, obj.getClass());
      pd.getWriteMethod().invoke(obj, value);
    } catch (IntrospectionException
        | IllegalAccessException
        | IllegalArgumentException
        | InvocationTargetException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  private String callGetter(Object obj, String fieldName) throws Exception {
    PropertyDescriptor pd;
    try {
      pd = new PropertyDescriptor(fieldName, obj.getClass());
      System.out.println("" + pd.getReadMethod().invoke(obj));
      return "" + pd.getReadMethod().invoke(obj);
    } catch (IntrospectionException
        | IllegalAccessException
        | IllegalArgumentException
        | InvocationTargetException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      throw new Exception("nooo");
    }
  }

  public static <T> T convertInstanceOfObject(Object o) {
    try {
      return (T) o;
    } catch (ClassCastException e) {
      return null;
    }
  }

  public List<String> getFolderName(String path){
    List<String> folders = new ArrayList<>();

    return folders;
  }

  public String ConvertListToString(List<String> stringList){
    stringList.forEach(System.out::println);
    return stringList.stream().toString();
  }

  public List<String>getSubFolderNameFromDirectory(String path){
    File file = new File(path);
    String[] directories = file.list(new FilenameFilter() {
      @Override
      public boolean accept(File current, String name) {
        return new File(current, name).isDirectory();
      }
    });
    return Arrays.stream(directories).collect(Collectors.toList());
  }
}
