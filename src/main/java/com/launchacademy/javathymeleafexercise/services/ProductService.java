package com.launchacademy.javathymeleafexercise.services;

import com.launchacademy.javathymeleafexercise.models.Product;
import java.util.List;

public interface ProductService {
  List<Product> getList();
  void addToList(Product product);
}