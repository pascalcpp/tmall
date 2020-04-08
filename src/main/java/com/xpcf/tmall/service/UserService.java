package com.xpcf.tmall.service;

import com.xpcf.tmall.dao.UserDAO;
import com.xpcf.tmall.pojo.User;
import com.xpcf.tmall.utils.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * @program: tmall_springboot
 * @description:
 * @author: "xpcf"
 * @create: 2020-04-02 13:38
 **/
@Service
public class UserService {
    @Autowired
    UserDAO userDAO;
    public Boolean isExist(String name){
        User user = getByName(name);
        return user != null;
    }

    public User get(String name , String password){
        return userDAO.getByNameAndPassword(name, password);
    }

    public User getByName(String name){
        return userDAO.findByName(name);
    }

    public Page4Navigator<User> list(Integer start,Integer size,Integer navigatePages){
        Sort sort = new Sort(Sort.Direction.DESC,"id");
        Pageable pageable = new PageRequest(start,size,sort);
        Page pageFromJPA = userDAO.findAll(pageable);
        return new Page4Navigator<>(pageFromJPA,navigatePages);

    }
    public void add(User user){
        userDAO.save(user);
    }
}

