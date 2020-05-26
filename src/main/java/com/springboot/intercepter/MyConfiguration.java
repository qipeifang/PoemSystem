package com.springboot.intercepter;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyConfiguration implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册拦截器
        MyInterceptor myInterceptor = new MyInterceptor();
        InterceptorRegistration loginRegistry = registry.addInterceptor(myInterceptor);
        // 拦截路径
        loginRegistry.addPathPatterns("/**");
        // 排除路径
        loginRegistry.excludePathPatterns("/getpoet");
        loginRegistry.excludePathPatterns("/displaypoetbyid");
        loginRegistry.excludePathPatterns("/listpoets");
        loginRegistry.excludePathPatterns("/login");
        loginRegistry.excludePathPatterns("/register");
        loginRegistry.excludePathPatterns("/listallpoetrys");
        loginRegistry.excludePathPatterns("/displaypoetrybyid");

        loginRegistry.excludePathPatterns("/getrecommend");
        loginRegistry.excludePathPatterns("/getpoetrytype");
        loginRegistry.excludePathPatterns("/");
        loginRegistry.excludePathPatterns("/");
        // 排除资源请求
        loginRegistry.excludePathPatterns("/css/login/*.css");
        loginRegistry.excludePathPatterns("/js/login/**/*.js");
        loginRegistry.excludePathPatterns("/image/login/*.png");
    }
}