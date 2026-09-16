package com.educandoweb.course.services;

import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.educandoweb.course.entities.Order;
import com.educandoweb.course.repositories.OrderRepository;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    public List<Order> findAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Order findById(Long id) {

        Order order = repository.findById(id).get();

        Hibernate.initialize(order.getItems());

        order.getItems().forEach(item -> {
            Hibernate.initialize(item.getProduct().getCategories());
        });

        return order;
    }
}