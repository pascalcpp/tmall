package com.xpcf.tmall.dao;

import com.xpcf.tmall.pojo.Order;
import com.xpcf.tmall.pojo.OrderItem;
import com.xpcf.tmall.pojo.Product;
import com.xpcf.tmall.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @program: tmall_springboot
 * @description:
 * @author: "xpcf"
 * @create: 2020-04-02 18:49
 **/
public interface OrderItemDAO extends JpaRepository<OrderItem,Integer> {
    List<OrderItem> findByOrderOrderByIdDesc(Order order);
    List<OrderItem> findByProduct(Product product);
    List<OrderItem> findByUserAndOrderIsNull(User user);
}
