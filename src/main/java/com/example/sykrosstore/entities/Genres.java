package com.example.sykrosstore.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@Entity
public class Genres  extends BaseEntity{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  @DateTimeFormat(pattern = "yyyy/MM/dd")
  @JsonFormat(pattern="yyyy/MM/dd")
  @Column(name = "created_at")
  @NotNull
  private Date createdAt = new Date();

  @Column(name = "updated_at")
  @NotNull
  @DateTimeFormat(pattern = "yyyy/MM/dd")
  @JsonFormat(pattern="yyyy/MM/dd")
  private Date updatedAt = new Date();


  @Column @NotEmpty @NotNull String name;

  public String getName(){
    return this.name;
  }

  @OneToMany(mappedBy = "genres", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  List<Subgenres> subgenres = new ArrayList<>();

  public void addSubGenres(List<Subgenres> subgenresList){
    subgenresList.forEach(subgenres1 -> {subgenres1.setGenres(this);});
    this.subgenres = subgenresList;
  }
}
