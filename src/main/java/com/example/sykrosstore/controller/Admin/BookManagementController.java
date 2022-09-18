package com.example.sykrosstore.controller.Admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin/book")
public class BookManagementController {
  @GetMapping("/")
  public String adminPage() {
    return "/Admin/books/book-management";
  }

}
