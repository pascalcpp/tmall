package com.xpcf.tmall.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * @program: tmall_springboot
 * @description: no
 * @author: "xpcf"
 * @create: 2020-03-26 10:42
 **/
@Entity
@Table(name = "category")
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Integer id;
    String name;

    @Transient
    List<Product> products;
    @Transient
    List<List<Product>> productsByRow;
}
