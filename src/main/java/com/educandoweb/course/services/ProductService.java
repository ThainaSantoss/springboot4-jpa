package com.educandoweb.course.services;

import java.util.List;
import java.util.Optional;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.educandoweb.course.entities.Product;
import com.educandoweb.course.repositories.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public List<Product> findAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Product findById(Long id) {

        Optional<Product> obj = repository.findById(id);

        Product product = obj.get();

        Hibernate.initialize(product.getCategories());

        System.out.println(
            "PRODUCT SERVICE - categories inicializado? "
            + Hibernate.isInitialized(product.getCategories())
        );

        return product;
    }
}