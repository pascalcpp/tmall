package com.xpcf.tmall.service;


import com.xpcf.tmall.dao.OrderItemDAO;
import com.xpcf.tmall.pojo.Order;
import com.xpcf.tmall.pojo.OrderItem;
import com.xpcf.tmall.pojo.Product;
import com.xpcf.tmall.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: tmall_springboot
 * @description:
 * @author: "xpcf"
 * @create: 2020-04-02 18:54
 **/
@Service
public class OrderItemService {
    @Autowired
    OrderItemDAO orderItemDAO;
    @Autowired
    ProductImageService productImageService;

    public void fill(List<Order> orders){
        for(Order order:orders){
            fill(order);
        }
    }

    public void fill(Order order){
        List<OrderItem> orderItems = listByOrder(order);
        float total = 0;
        Integer totalNumber = 0;
        for(OrderItem oi:orderItems){
            total += oi.getNumber() * oi.getProduct().getPromotePrice();
            totalNumber += oi.getNumber();
            productImageService.setFirstProductImage(oi.getProduct());
        }
        order.setTotal(total);
        order.setTotalNumber(totalNumber);
        order.setOrderItems(orderItems);
    }

    public Integer getSaleCount(Product product){
        List<OrderItem> orderItems = orderItemDAO.findByProduct(product);
        Integer result = 0;
        for(OrderItem orderItem : orderItems){
            if(orderItem.getOrder()!=null){
                if(orderItem.getOrder()!=null && orderItem.getOrder().getPayDate()!=null){
                    result += orderItem.getNumber();
                }
            }
        }
        return result;
    }

    public List<OrderItem> listByOrder(Order order){
       return orderItemDAO.findByOrderOrderByIdDesc(order);
    }

    public List<OrderItem> listByUser(User user){
        return orderItemDAO.findByUserAndOrderIsNull(user);
    }

    public void add(OrderItem orderItem){
        orderItemDAO.save(orderItem);
    }

    public void update(OrderItem orderItem){
        orderItemDAO.save(orderItem);
    }

    public OrderItem get(Integer oiid){
        return orderItemDAO.findOne(oiid);
    }
    public void delete(Integer oiid){
        orderItemDAO.delete(oiid);
    }
}
