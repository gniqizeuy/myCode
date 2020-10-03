package com.saul.swagger.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    //配置多个分组
    @Bean
    public Docket docket1(){
        return new Docket(DocumentationType.SWAGGER_2).groupName("group1");
    }

    @Bean //配置docket以配置Swagger具体参数
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("hello") // 配置分组
                .apiInfo(apiInfo())
                .select()// 通过.select()方法，去配置扫描接口,RequestHandlerSelectors配置如何扫描接口
//                any() // 扫描所有，项目中的所有接口都会被扫描到
//                none() // 不扫描接口
//            // 通过方法上的注解扫描，如withMethodAnnotation(GetMapping.class)只扫描get请求
//                withMethodAnnotation(final Class<? extends Annotation> annotation)
//            // 通过类上的注解扫描，如.withClassAnnotation(Controller.class)只扫描有controller注解的类中的接口
//                withClassAnnotation(final Class<? extends Annotation> annotation)
//                basePackage(final String basePackage) // 根据包路径扫描接口
                .apis(RequestHandlerSelectors.basePackage("com.saul.swagger.controller"))
                .paths(PathSelectors.ant("/yzq/**"))
//                any() // 任何请求都扫描
//                none() // 任何请求都不扫描
//                regex(final String pathRegex) // 通过正则表达式控制
//                ant(final String antPattern) // 通过ant()控制
                .build();
    }

    //配置文档信息
    private ApiInfo apiInfo() {
        Contact contact = new Contact("联系人名字", "http://xxx.xxx.com/联系人访问链接", "联系人邮箱");
        return new ApiInfo(
                "Swagger学习", // 标题
                "学习演示如何配置Swagger", // 描述
                "v1.0", // 版本
                "http://terms.service.url/组织链接", // 组织链接
                contact, // 联系人信息
                "Apach 2.0 许可", // 许可
                "许可链接", // 许可连接
                new ArrayList<>()// 扩展
        );
    }

}
