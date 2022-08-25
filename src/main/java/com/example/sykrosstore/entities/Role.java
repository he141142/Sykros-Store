package com.example.sykrosstore.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;

@Data
@Entity
public class Role extends BaseEntity{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  @NotEmpty @NotNull String name;

  @Column String description;

  @Column @NotNull int authority;
  @Column @Nullable int amount;

  @DateTimeFormat(pattern = "yyyy/MM/dd")
  @JsonFormat(pattern="yyyy/MM/dd")
  @Column(name = "created_at")
  @NotNull
  private Date createdAt = new Date();

  @DateTimeFormat(pattern = "yyyy/MM/dd")
  @JsonFormat(pattern="yyyy/MM/dd")
  @Column(name = "updated_at")
  @NotNull
  private Date updatedAt = new Date();

  @ManyToMany
  @JoinTable(
      name = "user_role",
      joinColumns = @JoinColumn(name = "account_id"),
      inverseJoinColumns = @JoinColumn(name = "role_id"))
  Set<Account> accounts = new HashSet<>();

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Set<Account> getAccounts() {
    return accounts;
  }

  public void setAccounts(Set<Account> accounts) {
    this.accounts = accounts;
  }
  
}
