package com.example.sykrosstore.entities;

import com.example.sykrosstore.constants.EntityValidators.AuthorValidator;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Authors  extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  @NotNull
  @NotEmpty
  @Column(name = "first_name")
  String firstName;

  @NotNull
  @NotEmpty
  @Column(name = "last_name")
  String lastName;

  @NotNull(message = AuthorValidator.PSEUDONYM_MANDATORY)
  @NotEmpty(message = AuthorValidator.PSEUDONYM_MANDATORY)
  String pseudonym;

  @NotNull(message = AuthorValidator.DOB_MANDATORY)
  Date born;

  @NotNull @NotEmpty String description;

  @OneToMany(mappedBy = "author")
  List<Books> books = new ArrayList<>();
}
