package com.xpcf.tmall.comparator;

import com.xpcf.tmall.pojo.Product;

import java.util.Comparator;

/**
 * @program: tmall_springboot
 * @description:
 * @author: "xpcf"
 * @create: 2020-04-04 20:45
 **/
public class ProductSaleCountComparator implements Comparator<Product> {
    @Override
    public int compare(Product o1, Product o2) {
        return o2.getSaleCount()-o1.getSaleCount();
    }
}
