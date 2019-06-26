package com.zr.csi.interceptor;

import org.apache.log4j.Logger;  
import org.springframework.stereotype.Component;  
import org.springframework.stereotype.Controller;  
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.zr.csi.model.User;

import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
import javax.servlet.http.HttpSession;  
  
import java.util.HashSet;  
import java.util.Set;  
  
/** 
 * 登录验证拦截 
 * 
 */  
@Controller  
@Component  
public class LoginInterceptor extends HandlerInterceptorAdapter {  
      
    Logger log = Logger.getLogger(LoginInterceptor.class);  
    @Override  
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)  
            throws Exception {  
        String basePath = request.getContextPath();  
        String path = request.getRequestURI();  
       
        response.setHeader("Access-Control-Allow-Origin", "*"); 
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE"); 
        response.setHeader("Access-Control-Max-Age", "3600"); 
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with,Content-Type"); 
        return true;/*
        
        
        if(!doLoginInterceptor(path, basePath) ){//是否进行登陆拦截  
            return true;  
        }  
          
        HttpSession session = request.getSession(); 
        User user = (User) session.getAttribute("user");
        String contextPath = request.getContextPath();
        if(user==null){  
            response.sendRedirect(contextPath+"/csi/login");
        }
        
        return true;  */
    }  
      
    /** 
     * 是否进行登陆过滤 
     * @param path 
     * @param basePath 
     * @return 
     */  
    private boolean doLoginInterceptor(String path,String basePath){  
        path = path.substring(basePath.length());  
        Set<String> notLoginPaths = new HashSet<>();  
        
        notLoginPaths.add("/index");  
        notLoginPaths.add("/signin");  
        notLoginPaths.add("/login");  
        notLoginPaths.add("/register");  
        notLoginPaths.add("/kaptcha.jpg");  
        notLoginPaths.add("/kaptcha");  
          
        if(notLoginPaths.contains(path)) return false;  
        return true;  
    }  
}  


