package com.example.sykrosstore.entities;

import com.example.sykrosstore.constants.EntityValidators.BookValidator;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Books extends BaseEntity{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  @NotEmpty(message = BookValidator.TITLE_MANDATORY)
  @NotNull(message = BookValidator.TITLE_MANDATORY)
  String title;

  @NotEmpty(message = BookValidator.EDITION_MANDATORY)
  @NotNull(message = BookValidator.EDITION_MANDATORY)
  String edition;

  @NotNull(message = BookValidator.PUBLICATION_DATE_MANDATORY)
  Date publicationDate;

  @NotNull @NotEmpty Integer availableQuantity = 0;

  @NotNull @NotEmpty Integer page;

  @NotNull(message = BookValidator.DESCRIPTION_MANDATORY)
  @NotEmpty(message = BookValidator.DESCRIPTION_MANDATORY)
  String description;

  @NotNull(message = BookValidator.PRICE_MANDATORY)
  Double price;

  @Column(name = "sale_prices")
  Double salePrice;

  Integer pageNumber;

  String Dimensions;

  String language;

  String type;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "author_id")
  Authors author;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "publisher_id")
  Publisher publisher;


  @OneToMany(mappedBy = "book")
  List<BookImages> bookImages = new ArrayList<>();

  @ManyToMany
  @JoinTable(
      name = "user_role",
      joinColumns = @JoinColumn(name = "role_id"),
      inverseJoinColumns = @JoinColumn(name = "book_id"))
  Set<Role> roles = new HashSet<>();

  @ManyToMany
  @JoinTable(
      name = "books_tags",
      joinColumns = @JoinColumn(name = "tag_id"),
      inverseJoinColumns = @JoinColumn(name = "book_id"))
  Set<Tags> tags = new HashSet<>();

}
