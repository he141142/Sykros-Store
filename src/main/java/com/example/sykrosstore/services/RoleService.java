package com.example.sykrosstore.services;

import com.example.sykrosstore.Repositories.RoleRepository;
import com.example.sykrosstore.constants.ServerResponse.SuccessMsg;
import com.example.sykrosstore.entities.Role;
import com.example.sykrosstore.services.Interfaces.IRoleService;
import com.example.sykrosstore.untils.Helper;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

@Service
public class RoleService implements IRoleService {

  @Autowired
  RoleRepository roleRepository;

  Helper<Role> helper = new Helper<Role>(Role.class);

  private boolean isRoleExisted(Role role){
      return roleRepository.findByName(role.getName()).isPresent();
  }


  @Override
  public String seedRoleData() throws Exception {
    ObjectMapper objectMapper = new ObjectMapper();
    String path =
        new ClassPathResource("C:/Users/84866/Desktop/Sykros-Store/src/main/resources/roles.json")
            .getPath();
    JSONParser jsonParser = new JSONParser();
    ArrayList<Role> roles = new ArrayList<>();
    try (FileReader reader = new FileReader(path)) {

      // Read JSON file
      Object obj = jsonParser.parse(reader);
      JSONArray roleList = (JSONArray) obj;
      System.out.println(roleList);

      for (Object j: roleList){
        roles.add(Helper.getRoleSimpleJsonParser((JSONObject) j));
      }

      for (Role role: roles){
        System.out.println(role.getName());
        if (!isRoleExisted(role)) {
          System.out.println("Insert....");
          role.setAmount(0);
          roleRepository.save(role);
        }
        System.out.println(role.getName());
      }
      return SuccessMsg.ROLE_SEEDING_SUCCESSFULLY;
    } catch (Exception e) {
      e.printStackTrace();
      throw new Exception(e.getMessage());
    }
  }


  @Override
  public String Test() throws Exception {
    String path =
        new ClassPathResource("C:/Users/84866/Desktop/Sykros-Store/src/main/resources/roles.json")
            .getPath();
    System.out.println(path);
    JSONParser jsonParser = new JSONParser();
    ArrayList<Role> roles = new ArrayList<>();
    try (FileReader reader = new FileReader(path)) {

      // Read JSON file
      Object obj = jsonParser.parse(reader);
      JSONArray roleList = (JSONArray) obj;
      System.out.println(roleList);
      for (Object j: roleList){
        roles.add(helper.getObjectSimpleJsonParser((JSONObject) j));
      }

      helper.printObjectClass(roles);
      return SuccessMsg.ROLE_SEEDING_SUCCESSFULLY;
    } catch (ParseException | IOException e) {
      e.printStackTrace();
      throw new Exception(e.getMessage());
    } catch (Exception e){
      e.printStackTrace();
      throw new Exception(e.getMessage());
    }
  }



  @Override
  public List<String> PathTest(){
    File file = new File("src/main/resources/jsonStorage");
    String[] directories = file.list(new FilenameFilter() {
      @Override
      public boolean accept(File current, String name) {
        return new File(current, name).isDirectory();
      }
    });
    return Arrays.stream(Objects.requireNonNull(directories)).collect(Collectors.toList());
  }

  @Override
  public List<Role> getRolesList(){
    return (List<Role>) this.roleRepository.findAll();
  }


}
