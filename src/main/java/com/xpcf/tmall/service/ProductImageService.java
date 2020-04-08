package com.xpcf.tmall.service;

import com.xpcf.tmall.dao.ProductImageDAO;
import com.xpcf.tmall.pojo.OrderItem;
import com.xpcf.tmall.pojo.Product;
import com.xpcf.tmall.pojo.ProductImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: tmall_springboot
 * @description:
 * @author: "xpcf"
 * @create: 2020-03-29 15:48
 **/
@Service
public class ProductImageService {
    public static final String type_single = "single";
    public static final String type_detail = "detail";
    @Autowired
    ProductService productService;
    @Autowired
    ProductImageDAO productImageDAO;

    public void add(ProductImage bean){
        productImageDAO.save(bean);
    }
    public void delete(Integer id){
        productImageDAO.delete(id);
    }
    public ProductImage get(Integer id){
        return productImageDAO.findOne(id);
    }
    public List<ProductImage> listSingleProductImages(Product product){
        return productImageDAO.findByProductAndTypeOrderByIdDesc(product,type_single);
    }
    public List<ProductImage> listDetailProductImages(Product product){
        return productImageDAO.findByProductAndTypeOrderByIdDesc(product,type_detail);
    }
    public void setFirstProductImage(Product product) {
        List<ProductImage> singleImages = listSingleProductImages(product);
        if(!singleImages.isEmpty())
            product.setFirstProductImage(singleImages.get(0));
        else
            product.setFirstProductImage(new ProductImage()); //这样做是考虑到产品还没有来得及设置图片，但是在订单后台管理里查看订单项的对应产品图片。

    }
    public void setFirstProductImages(List<Product> products) {
        for (Product product : products)
            setFirstProductImage(product);
    }

    public void setFirstProdutImagesOnOrderItems(List<OrderItem> ois) {
        for (OrderItem orderItem : ois) {
            setFirstProductImage(orderItem.getProduct());
        }
    }

}
