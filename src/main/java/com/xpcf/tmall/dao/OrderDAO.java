package com.xpcf.tmall.dao;

import com.xpcf.tmall.pojo.Order;
import com.xpcf.tmall.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import javax.tools.JavaCompiler;
import java.util.List;

public interface OrderDAO extends JpaRepository<com.xpcf.tmall.pojo.Order,Integer> {
    List<Order> findByUserAndStatusNotOrderByIdDesc(User user,String status);
}
