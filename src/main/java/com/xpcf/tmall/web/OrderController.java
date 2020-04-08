package com.xpcf.tmall.web;

import com.xpcf.tmall.pojo.Order;
import com.xpcf.tmall.pojo.OrderItem;
import com.xpcf.tmall.service.OrderItemService;
import com.xpcf.tmall.service.OrderService;
import com.xpcf.tmall.utils.Page4Navigator;
import com.xpcf.tmall.utils.Result;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Date;

/**
 * @program: tmall_springboot
 * @description:
 * @author: "xpcf"
 * @create: 2020-04-02 20:17
 **/
@RestController
public class OrderController {
    @Autowired
    OrderService orderService;
    @Autowired
    OrderItemService orderItemService;

    @GetMapping("/orders")
    public Page4Navigator<Order> list(@RequestParam(value = "start",defaultValue = "0") Integer start,
                                      @RequestParam(value = "size",defaultValue = "5") Integer size)
                                        throws Exception{
        start=start>0?start:0;
        Page4Navigator<Order> page = orderService.list(start,size,5);
        orderItemService.fill(page.getContent());
        orderService.removeOrderFromOrderItem(page.getContent());
        return page;
    }
    @PutMapping("deliveryOrder/{oid}")
    public Object deliveryOrder(@PathVariable("oid") Integer oid) throws IOException {
        Order order = orderService.get(oid);
        order.setDeliveryDate(new Date());
        order.setStatus(OrderService.waitConfirm);
        orderService.update(order);
        return Result.success();
    }
}
