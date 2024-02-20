package com.example.eshop.repository;


import com.example.eshop.entity.Product;
import com.example.eshop.entity.ProductPicture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductPictureRepository extends JpaRepository<ProductPicture, Integer> {

    List<ProductPicture> findAllByProduct(Product product);

}
