package com.xpcf.tmall.dao;

import com.xpcf.tmall.pojo.Product;
import com.xpcf.tmall.pojo.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @program: tmall_springboot
 * @description:
 * @author: "xpcf"
 * @create: 2020-04-04 17:14
 **/
public interface ReviewDAO extends JpaRepository<Review,Integer> {
    List<Review> findByProductOrderByIdDesc(Product product);
    Integer countByProduct(Product product);
}
