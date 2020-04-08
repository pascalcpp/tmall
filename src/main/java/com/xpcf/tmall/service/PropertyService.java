package com.xpcf.tmall.service;

import com.xpcf.tmall.dao.PropertyDAO;
import com.xpcf.tmall.pojo.Category;
import com.xpcf.tmall.pojo.Property;
import com.xpcf.tmall.utils.Page4Navigator;
import org.codehaus.groovy.antlr.treewalker.PreOrderTraversal;
import org.omg.PortableServer.LIFESPAN_POLICY_ID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: tmall_springboot
 * @description:
 * @author: "xpcf"
 * @create: 2020-03-29 10:47
 **/
@Service
public class PropertyService {
    @Autowired
    CategoryService categoryService;
    @Autowired
    PropertyDAO propertyDAO;
    public void add(Property bean){
        propertyDAO.save(bean);
    }
    public void delete(Integer id){
        propertyDAO.delete(id);
    }
    public Property get(Integer id){
        return propertyDAO.findOne(id);
    }
    public void update(Property bean){
        propertyDAO.save(bean);
    }
    public Page4Navigator<Property>
    list(Integer cid,Integer start,Integer size,Integer navigatePages){
        Category category = categoryService.get(cid);
        Sort sort = new Sort(Sort.Direction.DESC,"id");
        Pageable pageable = new PageRequest(start,size,sort);
        Page<Property> pageFromJPA = propertyDAO.findByCategory(category,pageable);
        return new Page4Navigator<>(pageFromJPA,navigatePages);
    }
    public List<Property> listByCategory(Category category){
        return propertyDAO.findByCategory(category);
    }
}
