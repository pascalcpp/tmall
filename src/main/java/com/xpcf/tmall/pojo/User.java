package com.xpcf.tmall.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;

/**
 * @program: tmall_springboot
 * @description:
 * @author: "xpcf"
 * @create: 2020-04-02 13:25
 **/
@Data
@Table(name = "user")
@Entity
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    private String name;

    private String password;

    private String salt;

    @Transient
    private String anonymousName;

    public String getAnonymousName(){
        if(null!=anonymousName)
            return anonymousName;
        if(null==name)
            anonymousName= null;
        else if(name.length()<=1)
            anonymousName = "*";
        else if(name.length()==2)
            anonymousName = name.substring(0,1) +"*";
        else {
            char[] cs =name.toCharArray();
            for (int i = 1; i < cs.length-1; i++) {
                cs[i]='*';
            }
            anonymousName = new String(cs);
        }
        return anonymousName;
    }
}
