package com.example.sykrosstore.services;

import com.example.sykrosstore.entities.BookJSON;
import com.example.sykrosstore.services.Interfaces.BookService;
import com.example.sykrosstore.untils.Helper;
import java.io.FileReader;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

@Service
public class BookServiceIpml implements BookService {
  Helper<BookJSON> booksJSONHelper = new Helper<>(BookJSON.class);

  @Value("${sykos.sampleBook.resource}")
  String pathAbsoulute;


  public void loadBooks() throws Exception {
    String samplePathResource = new ClassPathResource(pathAbsoulute).getPath();

    JSONParser jsonParser = new JSONParser();
    ArrayList<BookJSON> bookJSONS = new ArrayList<>();
    try (FileReader reader = new FileReader(samplePathResource)) {
      System.out.println(this.pathAbsoulute);
      Object obj = jsonParser.parse(reader);
      JSONArray books = (JSONArray) obj;
      for (Object j: books){
        System.out.println(j.toString());
        bookJSONS.add((BookJSON) this.booksJSONHelper.getObjectSimpleJsonParser((JSONObject) j));
      }
      for (BookJSON book: bookJSONS){
        System.out.println(book.getBookName());
        System.out.println(book.getBookAuthor());
      }
    } catch (Exception e) {
      e.printStackTrace();
      throw new Exception(e.getMessage());
    }
    }


  }

