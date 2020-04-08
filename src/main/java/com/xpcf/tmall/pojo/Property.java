package com.xpcf.tmall.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

/**
 * @program: tmall_springboot
 * @description:
 * @author: "xpcf"
 * @create: 2020-03-29 10:37
 **/
@Entity
@Data
@Table(name = "property")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @JoinColumn(name = "cid")
    @ManyToOne
    private Category category;

    @Column(name = "name")
    private String name;
}
