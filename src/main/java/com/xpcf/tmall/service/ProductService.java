package com.xpcf.tmall.service;

import com.xpcf.tmall.dao.ProductDAO;
import com.xpcf.tmall.pojo.Category;
import com.xpcf.tmall.pojo.Order;
import com.xpcf.tmall.pojo.OrderItem;
import com.xpcf.tmall.pojo.Product;
import com.xpcf.tmall.utils.Page4Navigator;
import org.aspectj.weaver.loadtime.definition.LightXMLParser;
import org.elasticsearch.common.recycler.Recycler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: tmall_springboot
 * @description:
 * @author: "xpcf"
 * @create: 2020-03-29 13:47
 **/
@Service
public class ProductService {
    @Autowired
    ProductDAO productDAO;
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductImageService productImageService;
    @Autowired
    OrderItemService orderItemService;
    @Autowired
    ReviewService reviewService;

    public void add(Product bean){
        productDAO.save(bean);
    }

    public void delete(Integer id){
        productDAO.delete(id);
    }

    public Product get(Integer id){
        return productDAO.findOne(id);
    }

    public void update(Product bean){
        productDAO.save(bean);
    }

    public Page4Navigator<Product>
    list(Integer cid,Integer start,Integer size,Integer navigatePages){
        Category category = categoryService.get(cid);
        Sort sort = new Sort(Sort.Direction.DESC,"id");
        Pageable pageable = new PageRequest(start,size,sort);
        Page<Product> pageFromJPA = productDAO.findByCategory(category,pageable);
        return new Page4Navigator<>(pageFromJPA,navigatePages);
    }

    public void fill(List<Category> categories){
        for(Category category:categories){
            fill(category);
        }
    }
    public void fill(Category category){
        List<Product> products = listByCategory(category);
        productImageService.setFirstProductImages(products);
        category.setProducts(products);
    }

    public void fillByRow(List<Category> categories){
        int productNumberEachRow = 8;
        for(Category category : categories){
            List<Product> products  = category.getProducts();
            List<List<Product>> productsByRow =  new ArrayList<>();
            for (int i =0;i<products.size();i+=productNumberEachRow){
                int size = i+productNumberEachRow;
                size = size>products.size()?products.size():size;
                List<Product> productOfEachRow = products.subList(i,size);
                productsByRow.add(productOfEachRow);
            }
            category.setProductsByRow(productsByRow);
        }
    }
    public void setSaleAndReviewNumber(List<Product> products){
        for(Product product : products){
            setSaleAndReviewNumber(product);
        }
    }

    public void setSaleAndReviewNumber(Product product){
        Integer saleCount = orderItemService.getSaleCount(product);
        product.setSaleCount(saleCount);
        Integer reviewCount = reviewService.getCount(product);
        product.setReviewCount(reviewCount);
    }

    public List<Product> listByCategory(Category category){
        return productDAO.findByCategoryOrderById(category);
    }

    public List<Product> search(String keyword,Integer start,Integer size){
        Sort sort = new Sort(Sort.Direction.DESC,"id");
        Pageable pageable = new PageRequest(start, size, sort);
        List<Product> products = productDAO.findByNameLike("%"+keyword+"%",pageable);
        return products;
    }
}
