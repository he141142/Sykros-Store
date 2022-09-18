package com.example.sykrosstore.entities;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

public class BaseEntity {

  @Column(name ="created_at")
  @DateTimeFormat(pattern = "dd/MM/yyyy")
  private Date createdAt = new Date();

  @Column(name ="updated_at")
  @DateTimeFormat(pattern = "dd/MM/yyyy")
  private Date updatedAt = new Date();

  @Column(name = "is_deleted")
  private Boolean isDeleted;
  
}
