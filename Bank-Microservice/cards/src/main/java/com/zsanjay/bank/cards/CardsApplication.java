package com.zsanjay.bank.cards;

import com.zsanjay.bank.cards.dto.CardsContactInfoDto;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableJpaAuditing
@EnableConfigurationProperties(value = {CardsContactInfoDto.class})
@OpenAPIDefinition(
        info = @Info(
                title = "Cards microservice REST API Documentation",
                description = "Bank Cards microservice REST API Documentation",
                version = "v1",
                contact = @Contact(
                        name = "Sanjay Mehta",
                        email = "zsanjayofficial94@gmail.com",
                        url = "https://www.zsanjay.github.io"
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "https://www.zsanjay.github.io"
                )
        ),
        externalDocs = @ExternalDocumentation(
                description = "Bank Cards microservice REST API Documentation",
                url = "https://www.example.com/swagger-ui.html"
        )
)
public class CardsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CardsApplication.class, args);
	}

}
