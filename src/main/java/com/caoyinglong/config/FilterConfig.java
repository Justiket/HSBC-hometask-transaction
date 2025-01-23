package com.caoyinglong.config;

import com.caoyinglong.filter.IpFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean<IpFilter> ddosFilterRegistration() {
        FilterRegistrationBean<IpFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new IpFilter());
        // 设置过滤器的拦截路径
        registration.addUrlPatterns("/*");
        return registration;
    }
}
