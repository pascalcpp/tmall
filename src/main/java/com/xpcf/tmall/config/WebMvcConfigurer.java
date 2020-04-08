package com.xpcf.tmall.config;

import com.xpcf.tmall.interceptor.LoginInterceptor;
import com.xpcf.tmall.interceptor.OtherInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.ejb.EJBs;
import javax.print.attribute.standard.MediaSize;

@Configuration
class WebMvcConfigurer extends WebMvcConfigurerAdapter{

    @Bean
    public LoginInterceptor getLoginIntercepter() {
        return new LoginInterceptor();
    }

    @Bean
    public OtherInterceptor getOtherInterceptor(){
        return new OtherInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(getLoginIntercepter())
                .addPathPatterns("/**");
        registry.addInterceptor(getOtherInterceptor())
                .addPathPatterns("/**");
    }
}