package com.aimerin.tmall.service;

import com.aimerin.tmall.dao.ProductImageDao;
import com.aimerin.tmall.pojo.OrderItem;
import com.aimerin.tmall.pojo.Product;
import com.aimerin.tmall.pojo.ProductImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductImageService {
    public static final String type_single = "single";
    public static final String type_detail = "detail";

    @Autowired
    ProductImageDao productImageDao;
    @Autowired
    ProductService productService;

    public void add(ProductImage bean) {
        productImageDao.save(bean);
    }

    public void delete(int id) {
        productImageDao.delete(id);
    }

    public ProductImage get(int id) {
        return productImageDao.findOne(id);
    }

    public List<ProductImage> listSingleProductImages(Product product) {
        return productImageDao.findByProductAndTypeOrderByIdDesc(product, type_single);
    }

    public List<ProductImage> listDetailProductImages(Product product) {
        return productImageDao.findByProductAndTypeOrderByIdDesc(product, type_detail);
    }

    public void setFirstProductImage(Product product) {
        List<ProductImage> singleImages = listSingleProductImages(product);
        if(!singleImages.isEmpty())
            product.setFirstProductImage(singleImages.get(0));
        else
            product.setFirstProductImage(new ProductImage());
    }

    public void setFirstProdcutImages(List<Product> products) {
        for (Product product : products) {
            setFirstProductImage(product);
        }
    }

    public void setFirstProductImageOnOrderItems(List<OrderItem> ois) {
        for (OrderItem orderItem : ois) {
            setFirstProductImage(orderItem.getProduct());
        }
    }
}
