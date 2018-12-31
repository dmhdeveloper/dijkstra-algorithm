package za.co.dmh.conf;

import org.neo4j.ogm.session.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.transaction.Neo4jTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class Neo4jConfig {

//    @Value("${spring.data.neo4j.uri}")
//    private String uri;
//    @Value("${spring.data.neo4j.username}")
//    private String username;
//    @Value("${spring.data.neo4j.password}")
//    private String password;
//
//    @Bean
//    public org.neo4j.ogm.config.Configuration getConfiguration() {
//        return new org.neo4j.ogm.config.Configuration
//                .Builder()
//                .uri(uri)
//                .credentials(username, password)
//                .connectionPoolSize(150)
//                .build();
//    }

    @Bean(value = "sessionFactory", name = "sessionFactory")
    public SessionFactory getSessionFactory() {
//        return new SessionFactory(getConfiguration(), "za.co.dmh.core.domain");
        return new SessionFactory("za.co.dmh.core.domain");
    }

    @Bean
    public Neo4jTransactionManager transactionManager() {
        return new Neo4jTransactionManager(getSessionFactory());
    }
}
