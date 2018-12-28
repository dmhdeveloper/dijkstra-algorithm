package za.co.dmh.conf;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.IOException;

@Configuration
public class Neo4jServerConfig {

    private static final Logger logger = LoggerFactory.getLogger(Neo4jServerConfig.class);

    @Value("${spring.data.neo4j.databaseDirectory}")
    private String databaseDirectory;

    @Bean
    public GraphDatabaseService graphDatabaseService() throws IOException {
        File file = new File(databaseDirectory);
        if(!file.exists()){
            if(!file.mkdirs()){
                throw new IOException("Unable to create Database Directory");
            }
        }
        GraphDatabaseService graphDatabaseService = new GraphDatabaseFactory().newEmbeddedDatabase(file);
        registerShutdownHook(graphDatabaseService);
        file.deleteOnExit();
        return graphDatabaseService;
    }

    private static void registerShutdownHook(final GraphDatabaseService graphDb) {
        // Registers a shutdown hook for the Neo4j instance so that it
        // shuts down nicely when the VM exits (even if you "Ctrl-C" the
        // running application).
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                graphDb.shutdown();
            }
        });
    }
}
