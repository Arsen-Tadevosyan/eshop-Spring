package com.example.eshop.controller;


import com.example.eshop.service.CategoryService;
import com.example.eshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


@Controller
@RequiredArgsConstructor
public class MainController {

    private final CategoryService categoryService;

    private final ProductService productService;

    @Value("${picture.upload.directory}")
    private String uploadDirectory;


    @GetMapping("/")
    public String mainPage(ModelMap modelMap) {
        modelMap.addAttribute("products", productService.findAll());
        modelMap.addAttribute("categories", categoryService.findAll());
        return "user/home";
    }

    @GetMapping(value = "getImage"
            , produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getImage(@RequestParam("picName") String picName) throws IOException {
        File file = new File(uploadDirectory, picName);
        if (file.exists()) {
            return IOUtils.toByteArray(new FileInputStream(file));
        }
        return null;
    }
}
