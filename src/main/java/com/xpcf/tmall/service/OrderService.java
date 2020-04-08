package com.xpcf.tmall.service;

import com.xpcf.tmall.dao.OrderDAO;
import com.xpcf.tmall.pojo.Order;
import com.xpcf.tmall.pojo.OrderItem;
import com.xpcf.tmall.pojo.User;
import com.xpcf.tmall.utils.Page4Navigator;
import org.aspectj.weaver.ast.Or;
import org.hibernate.event.spi.SaveOrUpdateEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @program: tmall_springboot
 * @description:
 * @author: "xpcf"
 * @create: 2020-04-02 18:53
 **/
@Service
public class OrderService {
    public static final String waitPay = "waitPay";
    public static final String waitDelivery = "waitDelivery";
    public static final String waitConfirm = "waitConfirm";
    public static final String waitReview = "waitReview";
    public static final String finish = "finish";
    public static final String delete = "delete";

    @Autowired
    OrderDAO orderDAO;
    @Autowired
    OrderItemService orderItemService;
    public Page4Navigator<Order> list(Integer start,Integer size,Integer navigatePages){
        Sort sort = new Sort(Sort.Direction.DESC,"id");
        Pageable pageable = new PageRequest(start,size,sort);
        Page pageFromJPA = orderDAO.findAll(pageable);
        return new Page4Navigator<Order>(pageFromJPA,navigatePages);
    }

    public void removeOrderFromOrderItem(List<Order> orders){
        for(Order order : orders){
            removeOrderFromOrderItem(order);
        }
    }

    public void removeOrderFromOrderItem(Order order){
        List<OrderItem> orderItems = order.getOrderItems();
        for(OrderItem orderItem : orderItems){
            orderItem.setOrder(null);
        }
    }

    public Order get(Integer id){
        return orderDAO.findOne(id);
    }

    public void update(Order bean){
        orderDAO.save(bean);
    }
    @Transactional(propagation= Propagation.REQUIRED,rollbackForClassName="Exception")
    public float add(Order order, List<OrderItem> ois) {
        float total = 0;
        add(order);

        if(false)
            throw new RuntimeException();

        for (OrderItem oi: ois) {
            oi.setOrder(order);
            orderItemService.update(oi);
            total+=oi.getProduct().getPromotePrice()*oi.getNumber();
        }
        return total;
    }
    public void add(Order order){
        orderDAO.save(order);
    }

    public List<Order> listByUserWithoutDelete(User user) {
        List<Order> orders = listByUserAndNotDeleted(user);
        orderItemService.fill(orders);
        return orders;
    }

    public List<Order> listByUserAndNotDeleted(User user){
        return orderDAO.findByUserAndStatusNotOrderByIdDesc(user,OrderService.delete);
    }
    public void cacl(Order o) {
        List<OrderItem> orderItems = o.getOrderItems();
        float total = 0;
        for (OrderItem orderItem : orderItems) {
            total+=orderItem.getProduct().getPromotePrice()*orderItem.getNumber();
        }
        o.setTotal(total);
    }
}
