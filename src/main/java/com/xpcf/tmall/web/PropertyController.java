package com.xpcf.tmall.web;

import com.xpcf.tmall.pojo.Property;
import com.xpcf.tmall.service.PropertyService;
import com.xpcf.tmall.utils.Page4Navigator;
import groovy.lang.GrabExclude;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: tmall_springboot
 * @description:
 * @author: "xpcf"
 * @create: 2020-03-29 11:35
 **/
@RestController
public class PropertyController {
    @Autowired
    PropertyService propertyService;

    @GetMapping("/categories/{cid}/properties")
    public Page4Navigator<Property> list(@PathVariable(value = "cid") Integer cid
            , @RequestParam(value = "start",defaultValue = "0") Integer start
            ,@RequestParam(value = "size",defaultValue = "5") Integer size) throws Exception{
        start = start<0?0:start;
        Page4Navigator<Property> page = propertyService.list(cid,start,size,5);
        return page;
    }
    @GetMapping("/properties/{id}")
    public Property get(@PathVariable(value = "id") Integer id) throws Exception{
        return propertyService.get(id);
    }
    @PostMapping("/properties")
    public Object add(@RequestBody Property bean) throws Exception{
        propertyService.add(bean);
        return bean;
    }
    @DeleteMapping("/properties/{id}")
    public String delete(@PathVariable(value = "id") Integer id, HttpServletRequest request)
            throws Exception{
        propertyService.delete(id);
        return null;
    }
    @PutMapping("/properties")
    public Object update(@RequestBody Property bean) throws Exception{
        propertyService.update(bean);
        return bean;
    }
}
