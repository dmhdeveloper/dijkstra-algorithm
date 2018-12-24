package za.co.dmh.core.dao;

import org.neo4j.graphdb.*;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.io.fs.FileUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.IOException;

@Configuration
public class Neo4jInitializer {

    @Bean
    public GraphDatabaseService prepareDatabase() throws IOException {
        GraphDatabaseService graphDb = new GraphDatabaseFactory().newEmbeddedDatabase(new File("target/neo4j-embedded-db"));
        registerShutdownHook(graphDb);
        return graphDb;
    }

    private static void registerShutdownHook(final GraphDatabaseService graphDb) {
        try {
            FileUtils.deleteRecursively(new File("target/neo4j-embedded-db"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                graphDb.shutdown();
            }
        });
    }
}
