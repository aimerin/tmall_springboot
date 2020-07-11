package com.aimerin.tmall.service;

import com.aimerin.tmall.dao.ReviewDao;
import com.aimerin.tmall.pojo.Product;
import com.aimerin.tmall.pojo.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {
    @Autowired
    ReviewDao reviewDao;
    @Autowired
    ProductService productService;

    public void add(Review review) {
        reviewDao.save(review);
    }

    public List<Review> list(Product product) {
        List<Review> result = reviewDao.findByProductOrderByIdDesc(product);
        return result;
    }

    public int getCount(Product product) {
        return reviewDao.countByProduct(product);
    }
}
