package br.com.desafio_java.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurationSupport {

    @Bean
    public Docket greetingApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.desafio_java"))
                .build()
                .apiInfo(metaData());

    }

    private ApiInfo metaData() {
        return new ApiInfoBuilder()
                .title("Controle de manutenção de equipamentos\n")
                .description("Trata-se de um sistema para controle de manutenção de equipamentos, cujo objetivo é manter o registro de todas as manutenções realizadas em equipamentos, bem como o registro dos envolvidos em cada manutenção. Isso é, o registro do cliente proprietário do equipamento, o registro do equipamento, o registro do colaborador que atende o cliente, o registro da ordem de serviço para a manutenção e o registro de todos os acompanhamentos realizados na ordem de serviço.")
                .version("1.0.0")
                .license("Documentação")
                .licenseUrl("https://github.com/viniciusbsi/controle-manutencao-equipamentos/blob/main/README.md")
                .build();
    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}