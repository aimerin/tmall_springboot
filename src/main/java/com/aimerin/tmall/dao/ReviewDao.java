package com.aimerin.tmall.dao;

import com.aimerin.tmall.pojo.Product;
import com.aimerin.tmall.pojo.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
//提供两个查询方法，一个返回某产品对应的评价集合，一个返回某产品对应的评价数量
public interface ReviewDao extends JpaRepository<Review, Integer> {
    List<Review> findByProductOrderByIdDesc(Product product);

    int countByProduct(Product product);

}
