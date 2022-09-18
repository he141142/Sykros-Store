package com.example.sykrosstore.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "books_tags")
public class BookTags {

  @Id
  @Column(name = "id", nullable = false)
  private Long id;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @ManyToOne
  @JoinColumn(name = "tag_id")
  Tags tag;

  @ManyToOne
  @JoinColumn(name = "book_id")
  Books book;
}
