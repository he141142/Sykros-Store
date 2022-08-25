package com.example.sykrosstore.entities;

import lombok.Data;

@Data
public class BookJSON {

  private String bookName;
  private String bookAuthor;
  private String bookPrice;
  private BookDetail bookDetailJSON;
  private AuthorJSON authorJSON;

}
