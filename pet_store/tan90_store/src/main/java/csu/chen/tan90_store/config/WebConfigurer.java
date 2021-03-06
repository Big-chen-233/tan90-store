package csu.chen.tan90_store.config;

import csu.chen.tan90_store.interceptor.LoginInterceptor;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.*;

@SpringBootConfiguration
public class WebConfigurer implements WebMvcConfigurer {
    @Bean
    public LoginInterceptor getLoginInterceptor() {
        return new LoginInterceptor();
    }
    @Override

    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/api/file/**").addResourceLocations("file:" + "D:\\ProgramCode\\pet_store\\images\\");
    }
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //所有请求都允许跨域，使用这种配置方法就不能在 interceptor 中再配置 header 了
        registry.addMapping("/**")
                .allowCredentials(true)
                .allowedOrigins("http://localhost:8080")
                .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")
                .allowedHeaders("*")
                .maxAge(3600);
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getLoginInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/index.html")
                .excludePathPatterns("/api/**")
                .excludePathPatterns("/api/login/login")
                .excludePathPatterns("/api/login/register")
                .excludePathPatterns("/api/logout")
                .excludePathPatterns("/api/categories/*/products")
                .excludePathPatterns("/api/categories/*/products/*/items")
                .excludePathPatterns("/api/shopcart/getList")
                .excludePathPatterns("/api/shopcart/update")
                .excludePathPatterns("/api/shopcart/buy")
                .excludePathPatterns("/api/shopcart/valChange")
                .excludePathPatterns("/api/shopcart/delete")
                .excludePathPatterns("/api/shopcart/getOrder");
        //草你大爷的shiro

    }




}