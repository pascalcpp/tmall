package com.xpcf.tmall.comparator;

import com.xpcf.tmall.pojo.Product;

import java.util.Comparator;

/**
 * @program: tmall_springboot
 * @description:
 * @author: "xpcf"
 * @create: 2020-04-04 18:50
 **/
public class ProductAllComparator implements Comparator<Product> {
    @Override
    public int compare(Product p1, Product p2) {
        return p2.getReviewCount()*p2.getSaleCount() -
                p1.getReviewCount()*p1.getSaleCount();
        /*
        * Returns a negative number, p1 first
        * */
    }
}
