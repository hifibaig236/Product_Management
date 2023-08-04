package com.mywebapp.web;


import com.mywebapp.web.ProductEntity.Product;
import com.mywebapp.web.ProductEntity.ProductRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class ProductRepositoryTests {
    @Autowired
    private ProductRepository repo;

    @Test
    public void testAddNew() {
        Product user = new Product();

        user.setName("Lotion");
        user.setCategory("cosmetic");
        user.setPrice("200");

        Product savedUser = repo.save(user);

        Assertions.assertThat(savedUser).isNotNull();
        Assertions.assertThat(savedUser.getId()).isGreaterThan(0);

    }
    @Test
    public void testListAll() {
        Iterable<Product> users = repo.findAll();
        Assertions.assertThat(users).hasSizeGreaterThan(0);
        for (Product pro : users) {
            System.out.println(pro);
        }
    }
    @Test
    public void testUpdate() {
        Integer userId = 1;
        Optional<Product> optionalUser = repo.findById(userId);
        Product user = optionalUser.get();
        user.setCategory("skin care");
        repo.save(user);

        Product updatedUser = repo.findById(userId).get();
        Assertions.assertThat(updatedUser.getCategory()).isEqualTo("skin care");
    }

    @Test
    public void testGet() {
        Integer userId = 2;
        Optional<Product> optionalUser = repo.findById(userId);
        Assertions.assertThat(optionalUser).isPresent();
        System.out.println(optionalUser.get());
    }

    @Test
    public void testDelete() {
        Integer userId = 2;
        repo.deleteById(userId);

        Optional<Product> optionalUser = repo.findById(userId);
        Assertions.assertThat(optionalUser).isNotPresent();
    }
}
