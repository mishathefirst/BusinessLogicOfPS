package com.example.projBLSS.main_server.configurations;

import com.example.projBLSS.main_server.beans.Album;
import com.example.projBLSS.main_server.beans.Picture;
import com.example.projBLSS.main_server.beans.User;
import org.springframework.context.annotation.*;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
@EnableAsync
public class ShutterstockConfiguration extends WebMvcConfigurerAdapter {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Bean
    @Scope(scopeName = "prototype")
    public Picture getPicture(){
        return new Picture();
    }

    @Bean
    @Scope(scopeName = "prototype")
    public User getUser(){
        return new User();
    }

    @Bean
    @Scope(scopeName = "prototype")
    public Album getAlbum(){ return new Album(); }
}
