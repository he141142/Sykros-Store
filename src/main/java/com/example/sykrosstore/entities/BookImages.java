package com.example.sykrosstore.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class BookImages  extends BaseEntity{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  @Column(name = "image_url")
  String imageUrl;

  @Column(name = "description")
  String description;

  @ManyToOne
  @JoinColumn(name = "book_id")
  Books book;
}
