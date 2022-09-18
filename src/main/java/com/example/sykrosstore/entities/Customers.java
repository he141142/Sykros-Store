package com.example.sykrosstore.entities;

import com.example.sykrosstore.constants.EntityValidators.CustomerValidator;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
public class Customers  extends BaseEntity{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  @NotNull(message = CustomerValidator.FIRST_NAME_MANDATORY)
  @NotEmpty(message = CustomerValidator.FIRST_NAME_MANDATORY)
  @Column(name = "first_name", nullable = false)
  String firstName;

  @NotNull(message = CustomerValidator.LAST_NAME_MANDATORY)
  @NotEmpty(message = CustomerValidator.LAST_NAME_MANDATORY)
  @Column(name = "last_name", nullable = false)
  String lastName;

  @javax.validation.constraints.NotNull(message = CustomerValidator.EMAIL_MANDATORY)
  @NotEmpty(message = CustomerValidator.EMAIL_MANDATORY)
  @Pattern(
      regexp = "^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$",
      message = CustomerValidator.EMAIL_INCORRECT_FORMAT)
  @Column(name = "email", nullable = false)
  String email;

  @NotNull
  @NotEmpty
  @Column(name = "phone_number")
  String phoneNumber;

  @NotNull @NotEmpty @Column String city;

  @NotNull @NotEmpty @Column String country;

  @NotNull @NotEmpty @Column String state;

  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "account_id")
  private Account account;
}
