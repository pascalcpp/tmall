package com.xpcf.tmall.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

/**
 * @program: tmall_springboot
 * @description:
 * @author: "xpcf"
 * @create: 2020-04-02 12:31
 **/
@Data
@Entity
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })
@Table(name = "propertyvalue")
public class PropertyValue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "pid")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "ptid")
    private Property property;

    private String value;
}
