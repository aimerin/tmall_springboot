package com.aimerin.tmall.service;

import com.aimerin.tmall.dao.OrderItemDao;
import com.aimerin.tmall.pojo.Order;
import com.aimerin.tmall.pojo.OrderItem;
import com.aimerin.tmall.pojo.Product;
import com.aimerin.tmall.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemService {
    @Autowired
    OrderItemDao orderItemDao;
    @Autowired
    ProductImageService productImageService;

    public void fill(List<Order> orders) {
        for (Order order : orders)
            fill(order);
    }

    public void update(OrderItem orderItem) {
        orderItemDao.save(orderItem);
    }

    public void add(OrderItem orderItem) {
        orderItemDao.save(orderItem);
    }

    public void delete(int id) {
        orderItemDao.delete(id);
    }

    public OrderItem get(int id) {
        return orderItemDao.getOne(id);
    }

    public void fill(Order order) {
        List<OrderItem> orderItems = listByOrder(order);
        float total = 0;
        int totalNumber = 0;
        for (OrderItem oi : orderItems) {
            total += oi.getNumber() * oi.getProduct().getPromotePrice();
            totalNumber += oi.getNumber();
            productImageService.setFirstProductImage(oi.getProduct());
        }
        order.setTotal(total);
        order.setOrderItems(orderItems);
        order.setTotalNumber(totalNumber);
    }

    public List<OrderItem> listByOrder(Order order) {
        return orderItemDao.findByOrderOrderByIdDesc(order);
    }

    public List<OrderItem> listByProduct(Product product) {
        return orderItemDao.findByProduct(product);
    }

    public List<OrderItem> listByUser(User user) {
        return orderItemDao.findByUserAndOrderIsNull(user);
    }

    public int getSaleCount(Product product) {
        List<OrderItem> ois = listByProduct(product);
        int result = 0;
        for (OrderItem oi : ois) {
            if (null != oi.getOrder()) {
                if (null != oi.getOrder() && null != oi.getOrder().getPayDate()) {
                    result += oi.getNumber();
                }
            }
        }
        return result;
    }
}
