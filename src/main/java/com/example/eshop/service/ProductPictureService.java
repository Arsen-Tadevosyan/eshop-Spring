package com.example.eshop.service;

import com.example.eshop.entity.Product;
import com.example.eshop.entity.ProductPicture;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProductPictureService {

    ProductPicture save(ProductPicture productPicture);

    void saveAll(Product product,List<MultipartFile> multipartFiles) throws IOException;

    List<ProductPicture> findAllByProduct(Product product);


}
