package rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan( { "rest" , "com.persistence.service", "com.persistence.dao"})
@EntityScan("com.persistence.dao.entities")
@EnableJpaRepositories(basePackages = "com.persistence.dao.impl")
@SpringBootApplication
public class Application extends SpringBootServletInitializer {

    // Used when launching as an executable jar or war
    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }

    // Used when deploying to a standalone servlet container
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }
}