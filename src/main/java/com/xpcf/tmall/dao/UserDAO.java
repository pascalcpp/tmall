package com.xpcf.tmall.dao;

import com.xpcf.tmall.pojo.User;
import org.springframework.beans.factory.config.CustomEditorConfigurer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDAO extends JpaRepository<User,Integer> {
    User findByName(String name);
    User getByNameAndPassword(String name,String password);
}
