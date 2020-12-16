package com.aimerin.tmall.service;

import com.aimerin.tmall.dao.CategoryDao;
import com.aimerin.tmall.pojo.Category;
import com.aimerin.tmall.pojo.Product;
import com.aimerin.tmall.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@CacheConfig(cacheNames="categories")
public class CategoryService {
    @Autowired
    CategoryDao categoryDao;

    @Cacheable(key = "'categories-page-'+#p0+'-'+#p1")
    public Page4Navigator<Category> list(int start, int size, int navigatePages) {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(start, size, sort);
        Page pageFromJPA = categoryDao.findAll(pageable);
        return new Page4Navigator<>(pageFromJPA, navigatePages);
    }

    public List<Category> list() {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        return categoryDao.findAll(sort);
    }

    @CacheEvict(allEntries = true)
    public void add(Category bean) {
        categoryDao.save(bean);
    }

    @CacheEvict(allEntries = true)
    public void delete(int id) {
        categoryDao.delete(id);
    }

    @Cacheable(key="'categories-one-'+ #p0")
    public Category get(int id) {
        Category c = categoryDao.findOne(id);
        return c;
    }

    @CacheEvict(allEntries = true)
    public void update(Category bean) {
        categoryDao.save(bean);
    }

    public void removeCategoryFromProduct(List<Category> cs) {
        for (Category category : cs) {
            removeCategoryFromProduct(category);
        }
    }
    public void removeCategoryFromProduct(Category category) {
        List<Product> products = category.getProducts();
        if (null != products) {
            for (Product product : products) {
                product.setCategory(null);
            }
        }

        List<List<Product>> productsByRow = category.getProductsByRow();
        if (null != productsByRow) {
            for (List<Product> ps : productsByRow) {
                for (Product p : ps) {
                    p.setCategory(null);
                }
            }
        }
    }
}
