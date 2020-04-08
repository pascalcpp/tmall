package com.xpcf.tmall.service;

import com.xpcf.tmall.dao.PropertyValueDAO;
import com.xpcf.tmall.pojo.Product;
import com.xpcf.tmall.pojo.Property;
import com.xpcf.tmall.pojo.PropertyValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: tmall_springboot
 * @description:
 * @author: "xpcf"
 * @create: 2020-04-02 12:41
 **/
@Service
public class PropertyValueService {
    @Autowired
    PropertyService propertyService;
    @Autowired
    PropertyValueDAO propertyValueDAO;

    public void update(PropertyValue bean){
        propertyValueDAO.save(bean);
    }

    public void init(Product product){
        List<Property> propertyList = propertyService.listByCategory(product.getCategory());
        for(Property property:propertyList){
            PropertyValue propertyValue = propertyValueDAO.getByPropertyAndProduct(property,product);
            if(propertyValue == null){
                propertyValue = new PropertyValue();
                propertyValue.setProduct(product);
                propertyValue.setProperty(property);
                propertyValueDAO.save(propertyValue);
            }
        }
    }

    public PropertyValue getByPropertyAndProduct(Property property,Product product){
        return propertyValueDAO.getByPropertyAndProduct(property,product);
    }

    public List<PropertyValue> list(Product product){
        return propertyValueDAO.findByProductOrderByIdDesc(product);
    }

}
