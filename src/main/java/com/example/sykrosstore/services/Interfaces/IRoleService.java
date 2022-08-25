package com.example.sykrosstore.services.Interfaces;

import com.example.sykrosstore.entities.Role;
import java.io.IOException;
import java.util.List;

public interface IRoleService {
  String seedRoleData() throws Exception;

  String Test() throws Exception;

  List<String> PathTest();

  List<Role> getRolesList();
}
