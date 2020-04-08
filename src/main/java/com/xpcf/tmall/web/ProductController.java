package com.xpcf.tmall.web;

import com.xpcf.tmall.pojo.Product;
import com.xpcf.tmall.service.CategoryService;
import com.xpcf.tmall.service.ProductImageService;
import com.xpcf.tmall.service.ProductService;
import com.xpcf.tmall.utils.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: tmall_springboot
 * @description:
 * @author: "xpcf"
 * @create: 2020-03-29 14:08
 **/
@RestController
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductImageService productImageService;
    @GetMapping(value = "/categories/{id}/products")
    public Page4Navigator<Product> list
            (@PathVariable(value = "id") Integer cid,
             @RequestParam(value = "start",defaultValue = "0") Integer start,
             @RequestParam(value = "size",defaultValue = "5") Integer size
             ) throws Exception{
        start=start<0?0:start;
        Page4Navigator<Product> page = productService.list(cid,start,size,5);
        productImageService.setFirstProductImages(page.getContent());
        return page;
    }
    @GetMapping(value = "/products/{id}")
    public Product get(@PathVariable(value = "id") Integer id) throws Exception{
        return productService.get(id);
    }
    @PostMapping(value = "/products")
    public Object add(@RequestBody Product bean) throws Exception{
        productService.add(bean);
        return bean;
    }
    @DeleteMapping(value = "/products/{id}")
    public String delete(@PathVariable(value = "id") Integer id, HttpServletRequest request) throws Exception{
        productService.delete(id);
        return null;
    }
    @PutMapping(value = "/products")
    public Object update(@RequestBody Product bean) throws Exception{
        productService.update(bean);
        return bean;
    }
}
