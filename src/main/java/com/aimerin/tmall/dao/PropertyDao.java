package com.aimerin.tmall.dao;

import com.aimerin.tmall.pojo.Category;
import com.aimerin.tmall.pojo.Property;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PropertyDao extends JpaRepository<Property, Integer> {
    //根据分类进行查询
    Page<Property> findByCategory(Category category, Pageable pageable);

    List<Property> findByCategory(Category category);
}
