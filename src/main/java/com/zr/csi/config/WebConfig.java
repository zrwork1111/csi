package com.zr.csi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;  
import org.springframework.context.annotation.Configuration;  
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;  
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;  
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;  
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;  
import org.springframework.web.servlet.view.InternalResourceViewResolver;  
import org.springframework.web.servlet.view.JstlView;

import com.zr.csi.interceptor.LoginInterceptor;  
  
@Configuration  
public class WebConfig extends WebMvcConfigurerAdapter {  
    @Autowired  
    LoginInterceptor loginInterceptor;  
  
    /** 
     * 不需要登录拦截的url:登录注册和验证码 
     */  
    final String[] notLoginInterceptPaths = {"/signin","/login/**","/index/**","/register/**","/kaptcha.jpg/**","/kaptcha/**"};//"/", "/login/**", "/person/**", "/register/**", "/validcode", "/captchaCheck", "/file/**", "/contract/htmltopdf", "/questions/**", "/payLog/**", "/error/**" };  
  
    @Override  
    public void addInterceptors(InterceptorRegistry registry) {  
        // 登录拦截器  
        registry.addInterceptor(loginInterceptor).addPathPatterns("/**").excludePathPatterns(notLoginInterceptPaths);  
    }  
  
    @Override  
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {  
        configurer.enable();  
    }  
  
    @Bean  
    public InternalResourceViewResolver viewResolver() {  
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();  
        resolver.setPrefix("/templates/");  
        resolver.setSuffix(".html");  
        resolver.setViewClass(JstlView.class);  
        return resolver;  
    }  
  
    @Override  
    public void addViewControllers(ViewControllerRegistry registry) {  
  
    }  
}  
