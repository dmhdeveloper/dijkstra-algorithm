package za.co.dmh.core.datalayer;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import za.co.dmh.core.domain.Node;

import java.util.List;

@Repository
public interface NodeRepository extends Neo4jRepository<Node, Long> {

    public List<Node> findAll();

    public Node findByName(@Param("name") String name);

    public Node findByUniqueId(@Param("uniqueId") String uniqueId);

    @Query("MATCH ()-[r:ROUTE {uniqueId: {uniqueId}}]-() DELETE r")
    public void deleteRoute(@Param("uniqueId") String uniqueId);

    @Query("MATCH ()-[r:ROUTE {uniqueId: {uniqueId}}]-() SET r.distance = {distance}")
    public void updateRoute(@Param("uniqueId") String uniqueId, Double distance);

}
