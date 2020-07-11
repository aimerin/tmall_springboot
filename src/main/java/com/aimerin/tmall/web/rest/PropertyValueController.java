package com.aimerin.tmall.web.rest;

import com.aimerin.tmall.pojo.Product;
import com.aimerin.tmall.pojo.PropertyValue;
import com.aimerin.tmall.service.ProductService;
import com.aimerin.tmall.service.PropertyValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//todo 目前的属性一旦添加了属性值就无法删除，之后要考虑如何删除属性
@RestController
public class PropertyValueController {
    @Autowired
    PropertyValueService propertyValueService;

    @Autowired
    ProductService productService;

    @GetMapping("/products/{pid}/propertyValues")
    public List<PropertyValue> list(@PathVariable("pid") int pid) {
        Product product = productService.get(pid);
        propertyValueService.init(product);
        List<PropertyValue> propertyValues = propertyValueService.list(product);
        return propertyValues;
    }

    @PutMapping("/propertyValues")
    public Object update(@RequestBody PropertyValue bean) {
        propertyValueService.update(bean);
        return bean;
    }
}
