package com.xpcf.tmall.web;

import com.xpcf.tmall.pojo.Product;
import com.xpcf.tmall.pojo.PropertyValue;
import com.xpcf.tmall.service.ProductService;
import com.xpcf.tmall.service.PropertyValueService;
import groovy.grape.GrapeIvy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: tmall_springboot
 * @description:
 * @author: "xpcf"
 * @create: 2020-04-02 12:55
 **/
@RestController
public class PropertyValueController {
    @Autowired
    PropertyValueService propertyValueService;
    @Autowired
    ProductService productService;

    @GetMapping("/products/{pid}/propertyValues")
    public List<PropertyValue> list(@PathVariable("pid") Integer pid) throws Exception{
        Product product = productService.get(pid);
        propertyValueService.init(product);
        List<PropertyValue> propertyValues = propertyValueService.list(product);
        return propertyValues;
    }

    @PutMapping("/propertyValues")
    public Object update(@RequestBody PropertyValue bean) throws Exception{
        propertyValueService.update(bean);
        return bean;
    }
}
