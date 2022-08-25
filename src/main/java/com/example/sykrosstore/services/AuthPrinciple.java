package com.example.sykrosstore.services;

import com.example.sykrosstore.constants.RoleList;
import com.example.sykrosstore.entities.Account;
import com.example.sykrosstore.entities.Role;
import com.example.sykrosstore.untils.Helper;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class AuthPrinciple implements UserDetails {

  Set<GrantedAuthority> authorities;
  String email;
  String passwordEncoder;
  Long id;
  String userName;

  public AuthPrinciple(
      String passwordEncoder, Long id, String email, Set<GrantedAuthority> roles, String userName) {
    this.authorities = roles;
    this.passwordEncoder = passwordEncoder;
    this.id = id;
    this.email = email;
    this.userName = userName;
  }

  public static AuthPrinciple build(Account account) {
    if (account == null) return null;
    Set<Role> userRoles = account.getRoles();
    Set<GrantedAuthority> authorities = new HashSet<>();
    for (Role role : userRoles) {
      SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + role.getName());
      authorities.add(authority);
    }
    boolean haveEmail = Helper.checkRoleHaveEmailStaticByAccount(account);
    String userName = getUserNameAccordingRole(account);
    String email = getEmailAccordingRole(account);
    return new AuthPrinciple(
        account.getPasswordHash(), account.getId(), email, authorities, userName);
  }

  private static String getUserNameAccordingRole(Account account) {
    ArrayList<String> roleToString =
        account.getRoles().stream()
            .map(Role::getName)
            .collect(Collectors.toCollection(ArrayList::new));

    if (roleToString.contains(RoleList.ADMIN)) {
      return "swp391@gmail.com";
    }

    if (roleToString.contains(RoleList.AUTHOR)) {
      // Not supported yet
    }
    if (roleToString.contains(RoleList.USER)) {
      return account.getUserName();
    }
    return null;
  }

  private static String getEmailAccordingRole(Account account) {
    ArrayList<String> roleToString =
        account.getRoles().stream()
            .map(Role::getName)
            .collect(Collectors.toCollection(ArrayList::new));

    if (roleToString.contains(RoleList.ADMIN)) {
      return "swp391@gmail.com";
    }

    if (roleToString.contains(RoleList.AUTHOR)) {
      // Not supported yet
    }
    if (roleToString.contains(RoleList.CUSTOMER)) {
      if (account.getCustomersList() == null) return null;
      if (account.getCustomersList().size() == 0) return null;
      return account.getCustomersList().get(0).getEmail();
    }

    if (roleToString.contains(RoleList.PUBLISHER)) {
      if (account.getCustomersList() == null) return null;
      if (account.getPublishers().size() == 0) return null;
      return account.getPublishers().get(0).getEmail();
    }
    return null;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  @Override
  public String getPassword() {
    return this.passwordEncoder;
  }

  @Override
  public String getUsername() {
    return this.email;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
