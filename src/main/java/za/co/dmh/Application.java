package za.co.dmh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = "za.co.dmh")
@EntityScan(basePackages = "za.co.dmh")
@EnableNeo4jRepositories(basePackages = "za.co.dmh.core.datalayer")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
