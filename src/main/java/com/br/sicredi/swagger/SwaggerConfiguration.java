package com.br.sicredi.swagger;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

  @Value("${aplicacao.nome}")
  private String nome;

  @Value("${aplicacao.descricao}")
  private String descricao;

  @Value("${aplicacao.versao}")
  private String versao;

  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2).select()
        .apis(RequestHandlerSelectors.basePackage("com.br.sicredi.api.rest"))
            .paths(PathSelectors.any()).build()
            .apiInfo(apiInfo()).useDefaultResponseMessages(false);
  }

  private ApiInfo apiInfo() {
    return new ApiInfo(nome, descricao, versao,
        "Criada para fins de demonstração",
        null, "MIT", "https://opensource.org/licenses/MIT", Collections.emptyList()
    );
  }

}
