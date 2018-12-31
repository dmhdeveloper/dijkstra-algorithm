package za.co.dmh.core.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.StartNode;
import org.neo4j.ogm.annotation.EndNode;

@RelationshipEntity(type = "ROUTE")
public class Route {

    @Id
    @GeneratedValue
    @JsonIgnore
    private Long id;
    private String uniqueId;
    @StartNode
    @JsonIgnoreProperties("routeList")
    private Node source;
    @EndNode
    @JsonIgnoreProperties("routeList")
    private Node destination;
    private Double distance;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Node getSource() {
        return source;
    }

    public void setSource(Node source) {
        this.source = source;
    }

    public Node getDestination() {
        return destination;
    }

    public void setDestination(Node destination) {
        this.destination = destination;
    }
}
