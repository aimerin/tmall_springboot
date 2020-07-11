package com.aimerin.tmall.dao;

import com.aimerin.tmall.pojo.Product;
import com.aimerin.tmall.pojo.Property;
import com.aimerin.tmall.pojo.PropertyValue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PropertyValueDao extends JpaRepository<PropertyValue, Integer> {
    List<PropertyValue> findByProductOrderByIdDesc(Product product);

    PropertyValue getByPropertyAndProduct(Property property, Product product);

}
