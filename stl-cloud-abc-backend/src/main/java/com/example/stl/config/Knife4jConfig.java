package com.example.stl.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.RequestParameterBuilder;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.schema.Example;
import springfox.documentation.schema.ScalarType;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.net.InetAddress;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Knife4j Swagger 配置
 *
 * @author Aaric, created on 2021-08-13T11:35.
 * @version 0.3.0-SNAPSHOT
 * @see com.github.xiaoymin.knife4j.spring.configuration.Knife4jAutoConfiguration
 */
@EnableKnife4j
@EnableOpenApi
@Configuration
public class Knife4jConfig implements InitializingBean {

    @Value("localhost")
    private String serverHost;

    @Value("${server.port}")
    private String serverPort;

    @Value("${knife4j.document.title}")
    private String documentTitle;

    @Value("${knife4j.document.description}")
    private String documentDescription;

    @Value("${knife4j.document.version}")
    private String documentVersion;

    @Value("${knife4j.document.developer.name}")
    private String developerName;

    @Value("${knife4j.document.developer.url}")
    private String developerUrl;

    @Value("${knife4j.document.developer.email}")
    private String developerEmail;

    ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(documentTitle)
                .description(documentDescription)
                .termsOfServiceUrl(MessageFormat.format("http://{0}:{1}/doc.html", serverHost, serverPort))
                .contact(new Contact(developerName, developerUrl, developerEmail))
                .version(documentVersion)
                .build();
    }

    @Bean
    Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .host(serverHost)
                .apiInfo(apiInfo())
                .directModelSubstitute(Date.class, Long.class)
                .globalRequestParameters(globalRequestParameters())
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.stl"))
                .paths(PathSelectors.any())
                .build();
    }

    static List<RequestParameter> globalRequestParameters() {
        List<RequestParameter> params = new ArrayList<>();
        params.add(singleRequestParameter("locale", "语言：zh_CN-简体中文（默认），en_US-美式英语", ParameterType.QUERY, "zh_CN", false));
        /*params.add(singleRequestParameter("Authorization", "Bearer 令牌字符串", ParameterType.HEADER, "Bearer token", false));*/
        return params;
    }

    static RequestParameter singleRequestParameter(String name, String description, ParameterType parameterType, String example, boolean required) {
        return new RequestParameterBuilder()
                .name(name)
                .description(description)
                .in(parameterType)
                .example(new Example(null, null, null, example, null, null))
                //.query(q -> q.model(m -> m.scalarModel(ScalarType.STRING)))
                .query(parameter -> parameter.model(model -> model.scalarModel(ScalarType.STRING)))
                .required(required)
                .build();
    }

    static List<SecurityScheme> securitySchemes() {
        return Arrays.asList(new ApiKey(HttpHeaders.AUTHORIZATION,
                HttpHeaders.AUTHORIZATION,
                "header"));
    }

    static List<SecurityContext> securityContexts() {
        List<SecurityReference> securityReferences = Arrays.asList(new SecurityReference(HttpHeaders.AUTHORIZATION,
                new AuthorizationScope[]{new AuthorizationScope("global", "Bearer 令牌字符串")}));
        SecurityContext securityContext = SecurityContext.builder()
                .securityReferences(securityReferences)
                .operationSelector(selector -> selector.requestMappingPattern().matches("/.*"))
                .build();
        return Arrays.asList(securityContext);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        serverHost = InetAddress.getLocalHost().getHostAddress();
    }
}
