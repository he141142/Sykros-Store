package com.example.sykrosstore.entities;

import com.example.sykrosstore.constants.EntityValidators.CustomerValidator;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.Data;

@Data
@Entity
public class Publisher  extends BaseEntity{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  @NotEmpty(message = CustomerValidator.EMAIL_MANDATORY)
  @Pattern(
      regexp = "^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$",
      message = CustomerValidator.EMAIL_INCORRECT_FORMAT)
  @Column(name = "email", nullable = false)
  String email;

  @NotEmpty @NotNull String name;

  String location;

  String website;

  String imgUrl;

  String size;

  Double rate;

  @OneToMany(mappedBy = "publisher")
  List<Books> books = new ArrayList<>();

  @ManyToOne
  @JoinColumn(name = "account_id")
  Account account;

}
