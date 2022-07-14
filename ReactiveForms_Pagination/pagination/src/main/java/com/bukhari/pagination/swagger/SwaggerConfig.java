package com.bukhari.pagination.swagger;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(title = "Product API", version = "3.0",
        license = @License(name = "Apache 2.0",url = "http://springdoc.org"),
        description = "Product Information",
        termsOfService = "This Application is used for Learning Purpose"))
public class SwaggerConfig {

    //Either we use above annotation @OpenAPIDefinition or we can use the below method to define the description.

//    @Bean
//    public OpenAPI springShopOpenAPI() {
//        return new OpenAPI()
//                .info(new Info().title("SpringShop API")
//                        .description("Spring shop sample application")
//                        .version("v0.0.1")
//                        .license(new License().name("Apache 2.0").url("http://springdoc.org")))
//                .externalDocs(new ExternalDocumentation()
//                        .description("SpringShop Wiki Documentation")
//                        .url("https://springshop.wiki.github.org/docs"));
//    }
//
//    private ApiInfo apiInfo() {
//        return new ApiInfoBuilder().title("Employee Manager Project")
//                .description("Swagger Integration Role Employee Manager")
//                .termsOfServiceUrl("This Application is used for Learning Purpose")
//                .license("Licensed by Bukhari")
//                .licenseUrl("bukhari.com").version("1.0").build();
//    }

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("com.bukhari.pagination") //base Package
                .pathsToMatch("/products/**") // allowed urls optional we can comment it
                .build();
    }
}
