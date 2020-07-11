package com.aimerin.tmall.dao;

import com.aimerin.tmall.pojo.Category;
import org.springframework.data.jpa.repository.JpaRepository;

//Todo 之后用mybatis改写持久层代码
public interface CategoryDao extends JpaRepository<Category, Integer> {

}
