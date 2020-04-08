package com.xpcf.tmall.comparator;

import com.xpcf.tmall.pojo.Product;

import java.util.Comparator;

/**
 * @program: tmall_springboot
 * @description:
 * @author: "xpcf"
 * @create: 2020-04-04 19:40
 **/
public class ProductReviewComparator implements Comparator<Product> {
    @Override
    public int compare(Product o1, Product o2) {
        return o2.getReviewCount()-o1.getReviewCount();
    }
}
