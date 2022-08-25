package com.example.sykrosstore.services;

import com.example.sykrosstore.Repositories.UserRepository;
import com.example.sykrosstore.entities.Account;
import com.example.sykrosstore.services.Interfaces.IUserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServices implements IUserServices {

  UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Account account = userRepository.findByUserName(username).get();
    return AuthPrinciple.build(account);
  }
}
