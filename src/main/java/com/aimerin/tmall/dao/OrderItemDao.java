package com.aimerin.tmall.dao;

import com.aimerin.tmall.pojo.Order;
import com.aimerin.tmall.pojo.OrderItem;
import com.aimerin.tmall.pojo.Product;
import com.aimerin.tmall.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemDao extends JpaRepository<OrderItem, Integer> {
    List<OrderItem> findByOrderOrderByIdDesc(Order order);

    List<OrderItem> findByProduct(Product product);

    List<OrderItem> findByUserAndOrderIsNull(User user);
}
