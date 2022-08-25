package com.example.sykrosstore.Repositories;

import com.example.sykrosstore.entities.Account;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<Account, Long> {
  Optional<Account> findByUserName(String userName);
}
