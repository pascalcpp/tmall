package com.xpcf.tmall.dao;

import com.xpcf.tmall.pojo.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryDAO extends JpaRepository<Category,Integer> {
}
