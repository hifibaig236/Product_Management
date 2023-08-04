package com.mywebapp.web.ProductEntity;

import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepository extends JpaRepository<Product, Integer> {
    public Integer countById(Integer id);
}
