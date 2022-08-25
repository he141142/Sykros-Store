package com.example.sykrosstore.Repositories;

import com.example.sykrosstore.entities.Account;
import com.example.sykrosstore.entities.Role;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {

  Optional<Role> findByName(String name);

}
