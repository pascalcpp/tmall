package com.xpcf.tmall.service;

import com.xpcf.tmall.dao.ReviewDAO;
import com.xpcf.tmall.pojo.Product;
import com.xpcf.tmall.pojo.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.security.krb5.internal.APRep;

import java.util.List;

/**
 * @program: tmall_springboot
 * @description:
 * @author: "xpcf"
 * @create: 2020-04-04 17:17
 **/
@Service
public class ReviewService {
    @Autowired
    ReviewDAO reviewDAO;
    @Autowired
    ProductService productService;

    public void add(Review review){
        reviewDAO.save(review);
    }

    public List<Review> list(Product product){
        return reviewDAO.findByProductOrderByIdDesc(product);
    }

    public Integer getCount(Product product){
        return reviewDAO.countByProduct(product);
    }
}
