package com.example.eshop.service.impl;

import com.example.eshop.entity.Product;
import com.example.eshop.entity.ProductPicture;
import com.example.eshop.repository.ProductPictureRepository;
import com.example.eshop.service.ProductPictureService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductPictureServiceImpl implements ProductPictureService {

    private final ProductPictureRepository productPictureRepository;

    @Value("${eshop.picture.upload.directory}")
    private String uploadDirectory;

    @Override
    public ProductPicture save(ProductPicture productPicture) {
        return productPictureRepository.save(productPicture);
    }

    @Override
    public void saveAll(Product product, List<MultipartFile> multipartFiles) {
        if (multipartFiles != null && !multipartFiles.isEmpty()) {
            for (MultipartFile multipartFile : multipartFiles) {
                String picName = System.currentTimeMillis() + "_" + multipartFile.getOriginalFilename();
                File file = new File(uploadDirectory, picName);
                try {
                    multipartFile.transferTo(file);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                save(ProductPicture.builder()
                        .product(product)
                        .picName(picName)
                        .build());
            }
        }
    }

    @Override
    public List<ProductPicture> findAllByProduct(Product product) {
        return productPictureRepository.findAllByProduct(product);
    }
}
