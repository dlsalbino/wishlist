package com.azusah.wishlist.infrastructure.documentation.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfiguration {

    @Bean
    public OpenAPI defineOpenApi() {

        var server = new Server();
        server.setUrl("http://localhost:8080/api/wishlist");
        server.setDescription("Wishlist API");

        var contact = new Contact();
        contact.setName("Daniel Albino");
        contact.setEmail("albino.daniel@ecommerce.com");
        contact.setUrl("https://www.linkedin.com/in/dlsalbino");

        var information = new Info()
                .title("E-commerce  Wishlist API")
                .version("1.0")
                .description("API to handle customer's Wishlist inside an E-commerce")
                .contact(contact);

        return new OpenAPI()
                .info(information)
                .servers(List.of(server));
    }
}
