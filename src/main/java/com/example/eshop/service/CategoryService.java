package com.example.eshop.service;

import com.example.eshop.entity.Category;

import java.util.List;

public interface CategoryService {

    Category save(Category category);

    List<Category> findAll();
}
