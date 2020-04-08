package com.xpcf.tmall.dao;

import com.xpcf.tmall.pojo.Product;
import com.xpcf.tmall.pojo.Property;
import com.xpcf.tmall.pojo.PropertyValue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PropertyValueDAO extends JpaRepository<PropertyValue,Integer> {
    List<PropertyValue> findByProductOrderByIdDesc(Product product);
    PropertyValue getByPropertyAndProduct(Property property,Product product);
}
