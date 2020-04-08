package com.xpcf.tmall.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.xpcf.tmall.service.OrderService;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.jpa.criteria.OrderImpl;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "order_")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class Order implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
	private Integer id;

    @ManyToOne
    @JoinColumn(name = "uid")
    private User user;

	private String orderCode;

	private String address;

	private String post;

	private String receiver;

	private String mobile;

	private String userMessage;

	private java.util.Date createDate;

	private java.util.Date payDate;

	private java.util.Date deliveryDate;

	private java.util.Date confirmDate;

	private String status;

	@Transient
    private List<OrderItem> orderItems;

	@Transient
    private float total;

	@Transient
    private Integer totalNumber;

	@Transient

    private String statusDesc;

    public String getStatusDesc(){
        String desc ="未知";
        switch(status){
            case OrderService.waitPay:
                desc="待付款";
                break;
            case OrderService.waitDelivery:
                desc="待发货";
                break;
            case OrderService.waitConfirm:
                desc="待收货";
                break;
            case OrderService.waitReview:
                desc="等评价";
                break;
            case OrderService.finish:
                desc="完成";
                break;
            case OrderService.delete:
                desc="刪除";
                break;
            default:
                desc="未知";
        }
        return desc;
    }

}
