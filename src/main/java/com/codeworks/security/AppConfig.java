/*package com.codeworks.security;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
@ComponentScan({ "com.codeworks.service","com.codeworks.spring.dao","com.codeworks.mvcweb.controller","com.codeworks.security","com.codeworks.db"})
@Import({ SecurityConfig.class })
public class  AppConfig extends WebMvcConfigurerAdapter
{
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
}
*/