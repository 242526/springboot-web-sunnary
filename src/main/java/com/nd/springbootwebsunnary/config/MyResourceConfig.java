package com.nd.springbootwebsunnary.config;

import com.nd.springbootwebsunnary.config.internationalization.LoginHandlerInterceptor;
import com.nd.springbootwebsunnary.config.internationalization.MyLocaleResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/****
 * 扩展SpringMvc, 需要既保留springboot的自动配置，还能扩展自己的
 */
@Configuration
public class MyResourceConfig implements WebMvcConfigurer {
    /***
     * addResourceHandlers: 我们要配置静态资源不过滤
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //addResourceHandler /**表示的是根路径下的所有资源
        registry.addResourceHandler("/**").
                addResourceLocations("classpath:/static/").
                addResourceLocations("classpath:/public/").
                addResourceLocations("classpath:/resources/");
    }

    /***
     * 配置视图控制器
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("login");
        registry.addViewController("/login.html").setViewName("login");
        registry.addViewController("/main.html").setViewName("dashboard");
    }

    /***
     * 区域信息解析器
     */
    @Bean
    public LocaleResolver localeResolver(){

        return new MyLocaleResolver();
    }


    /***
     * 注册拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //addPathPatterns("/**") 拦截任意下的路径请求
        //静态资源？
        //springboot已经做好静态资源映射了
        registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**").
                excludePathPatterns("/index.html","/","/user/login", "/**/*.css",
                        "/**/*.js", "/**/*.png", "/**/*.jpg",
                        "/**/*.jpeg", "/**/*.gif", "/**/fonts/*",
                        "/**/*.svg");
    }
}
