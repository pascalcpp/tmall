package com.xpcf.tmall.comparator;

import com.xpcf.tmall.pojo.Product;

import java.util.Comparator;

/**
 * @program: tmall_springboot
 * @description:
 * @author: "xpcf"
 * @create: 2020-04-04 19:33
 **/
public class ProductDateComparator implements Comparator<Product> {
    @Override
    public int compare(Product o1, Product o2) {
        return o1.getCreateDate().compareTo(o2.getCreateDate());
    }
}
