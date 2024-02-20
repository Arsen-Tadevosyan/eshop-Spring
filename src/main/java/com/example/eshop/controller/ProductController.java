package com.example.eshop.controller;

import com.example.eshop.entity.Product;
import com.example.eshop.service.CategoryService;
import com.example.eshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    private final CategoryService categoryService;


    @GetMapping("/products/{id}")
    public String productSinglePage(@PathVariable("id") int id, ModelMap modelMap) {
        Product byId = productService.findById(id);
        if (byId == null) {
            return "redirect:/";
        }
        modelMap.addAttribute("product", byId);
        modelMap.addAttribute("categories", categoryService.findAll());
        return "user/singleProduct";
    }

}
