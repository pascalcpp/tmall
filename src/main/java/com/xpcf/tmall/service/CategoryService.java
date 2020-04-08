package com.xpcf.tmall.service;

import com.xpcf.tmall.dao.CategoryDAO;
import com.xpcf.tmall.pojo.Category;
import com.xpcf.tmall.pojo.Product;
import com.xpcf.tmall.utils.Page4Navigator;
import org.elasticsearch.common.recycler.Recycler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import sun.security.krb5.internal.PAData;

import java.util.List;

/**
 * @program: tmall_springboot
 * @description: no
 * @author: "xpcf"
 * @create: 2020-03-26 10:52
 **/
@Service
public class CategoryService {
    @Autowired CategoryDAO categoryDAO;

    public Page4Navigator<Category> list(int start, int size, int navigatePages) {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(start, size,sort);
        Page pageFromJPA =categoryDAO.findAll(pageable);
        return new Page4Navigator<>(pageFromJPA,navigatePages);
    }
    public List<Category> list() {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        return categoryDAO.findAll(sort);
    }
    public void add(Category bean){
        categoryDAO.save(bean);
    }
    public void delete(Integer id){
        categoryDAO.delete(id);
    }
    public Category get(Integer id){
        return categoryDAO.findOne(id);
    }
    public void update(Category bean){
        categoryDAO.save(bean);
    }

    public void removeCategoryFromProduct(List<Category> categories){
        for(Category category:categories){
            removeCategoryFromProduct(category);
        }
    }

    public void removeCategoryFromProduct(Category category){
        List<Product> products = category.getProducts();
        if(products != null){
            for(Product product:products){
                product.setCategory(null);
            }
        }

        List<List<Product>> productByRow = category.getProductsByRow();
        if(productByRow != null){
            for(List<Product> products1 : productByRow){
                for(Product product:products){
                    product.setCategory(null);
                }
            }
        }
    }
}

