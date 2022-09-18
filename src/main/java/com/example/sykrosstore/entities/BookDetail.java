package com.example.sykrosstore.entities;

import java.util.Date;
import lombok.Data;

@Data
public class BookDetail  {
  double price;
  double sale;
  Date PublishDate;
  String Publisher;
  int Pages;
  String Dimensions;
  String Languages;
  String Type;

}
