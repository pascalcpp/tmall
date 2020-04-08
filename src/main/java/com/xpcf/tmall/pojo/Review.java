package com.xpcf.tmall.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })
@Table(name = "review")
public class Review implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	private String content;

	@ManyToOne
	@JoinColumn(name = "uid")
	private User user;

	@ManyToOne
	@JoinColumn(name = "pid")
	private Product product;

	private Date createDate;

}
