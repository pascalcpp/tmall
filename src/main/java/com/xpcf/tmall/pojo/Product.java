package com.xpcf.tmall.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * @program: tmall_springboot
 * @description:
 * @author: "xpcf"
 * @create: 2020-03-29 13:39
 **/
@Entity
@Data
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
@Table(name = "product")
@Document(indexName = "tmall_springboot",type = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "cid")
    private Category category;

    private String name;
    private String subTitle;
    private float originalPrice;
    private float promotePrice;
    private Integer stock;
    private Date createDate;
    @Transient
    private ProductImage firstProductImage;
    @Transient
    private List<ProductImage> productSingleImages;
    @Transient
    private List<ProductImage> productDetailImages;
    @Transient
    private Integer reviewCount;
    @Transient
    private Integer saleCount;
}
