package com.shop.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.XmlViewResolver;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

/**
 * Created by 15764 on 2017-07-16.
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages={
        "com.shop.controller"
})
public class MvcConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/js/**").addResourceLocations("/WEB-INF/static/js/");
        registry.addResourceHandler("/css/**").addResourceLocations("/WEB-INF/static/css/");
        registry.addResourceHandler("/fonts/**").addResourceLocations("/WEB-INF/static/fonts/");
        registry.addResourceHandler("/images/**").addResourceLocations("/WEB-INF/static/images/");
        registry.addResourceHandler("/themes/**").addResourceLocations("/WEB-INF/static/themes/");
        super.addResourceHandlers(registry);
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        super.addViewControllers(registry);
    }
    @Bean
    public InternalResourceViewResolver internalResourceViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setOrder(2);
        resolver.setPrefix("/WEB-INF/jsp/");
        resolver.setSuffix(".jsp");
        return resolver;
    }
    @Bean
    public XmlViewResolver xmlViewResolver(@Value("classpath:views.xml")Resource location){
        XmlViewResolver resolver=new XmlViewResolver();
        resolver.setLocation(location);
        return resolver;
    }
    @Bean
    public FreeMarkerViewResolver freeMarkerHtmlViewResolver() {
        FreeMarkerViewResolver resolver = new FreeMarkerViewResolver();
        resolver.setContentType("text/html");
        resolver.setOrder(0);
        resolver.setSuffix(".html");
        return resolver;
    }
    @Bean
    public FreeMarkerViewResolver freeMarkerFtlViewResolver() {
        FreeMarkerViewResolver resolver = new FreeMarkerViewResolver();
        resolver.setSuffix(".ftl");
        resolver.setContentType("text/html");
        resolver.setOrder(1);
        return resolver;
    }
    @Bean
    public FreeMarkerConfigurer freeMarkerConfigurer() {
        FreeMarkerConfigurer configurer = new FreeMarkerConfigurer();
        configurer.setTemplateLoaderPaths("/WEB-INF/template/");
        configurer.setDefaultEncoding("UTF-8");
        return configurer;
    }
    @Bean("multipartResolver")
    public MultipartResolver multipartResolver(){
        CommonsMultipartResolver mr=new CommonsMultipartResolver();
        mr.setMaxUploadSize(10240000);
        return mr;
    }
}
