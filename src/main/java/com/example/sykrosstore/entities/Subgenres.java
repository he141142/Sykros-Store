package com.example.sykrosstore.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
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
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@Entity
public class Subgenres  extends BaseEntity{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  @NotNull @NotEmpty String name;

  @NotNull @NotEmpty String description;

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

  @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
  @JoinColumn(name = "genres_id")
  Genres genres;
}
