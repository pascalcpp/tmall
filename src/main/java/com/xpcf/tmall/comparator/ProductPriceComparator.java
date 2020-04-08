package com.xpcf.tmall.comparator;

import com.xpcf.tmall.pojo.Product;

import java.util.Comparator;

/**
 * @program: tmall_springboot
 * @description:
 * @author: "xpcf"
 * @create: 2020-04-04 19:39
 **/
public class ProductPriceComparator implements Comparator<Product> {
    @Override
    public int compare(Product o1, Product o2) {
        return (int)(o1.getPromotePrice()-o2.getPromotePrice());
    }
}
