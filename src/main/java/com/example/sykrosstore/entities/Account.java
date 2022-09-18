package com.example.sykrosstore.entities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.Data;

@Data
@Entity
public class Account extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  @NotNull()
  @NotEmpty
  @Pattern(regexp = "((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$")
  String password;

  @Column(name = "user_name")
  @NotNull
  @NotEmpty
  String userName;

  @NotNull @NotEmpty String status;

  @Column(name = "password_hash")
  String passwordHash;

  @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
  private List<Customers> customersList = new ArrayList<>();

  @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
  private List<Publisher> publishers = new ArrayList<>();

  @ManyToMany
  @JoinTable(
      name = "user_role",
      joinColumns = @JoinColumn(name = "role_id"),
      inverseJoinColumns = @JoinColumn(name = "account_id"))
  Set<Role> roles = new HashSet<>();

}
