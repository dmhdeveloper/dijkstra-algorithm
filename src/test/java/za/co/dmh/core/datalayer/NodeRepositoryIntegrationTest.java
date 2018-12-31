package za.co.dmh.core.datalayer;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import za.co.dmh.Application;
import za.co.dmh.core.domain.Node;
import za.co.dmh.core.domain.Route;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@ActiveProfiles(profiles = "test")
public class NodeRepositoryIntegrationTest {

    @Autowired
    private NodeRepository nodeRepository;

    @Before
    public void initializeDatabase() {
        Node nodeA = new Node();
        nodeA.setName("A");
        nodeA.setUniqueId(UUID.randomUUID().toString());

        Node nodeB = new Node();
        nodeB.setName("B");
        nodeB.setUniqueId(UUID.randomUUID().toString());

        Node nodeC = new Node();
        nodeC.setName("C");
        nodeC.setUniqueId(UUID.randomUUID().toString());

        Route routeA = new Route();
        routeA.setSource(nodeA);
        routeA.setDestination(nodeB);
        routeA.setUniqueId(UUID.randomUUID().toString());
        routeA.setDistance(12.0);

        Route routeB = new Route();
        routeB.setSource(nodeA);
        routeB.setDestination(nodeC);
        routeB.setUniqueId(UUID.randomUUID().toString());
        routeB.setDistance(14.0);

        nodeA.setRouteList(List.of(routeA, routeB));

        nodeRepository.save(nodeC);
        nodeRepository.save(nodeB);
        nodeRepository.save(nodeA);
    }

    @Test
    @DirtiesContext
    public void testGetRoutes(){
        Node node = nodeRepository.findByName("A");
        assertEquals(2, node.getRouteList().size());
    }

    @Test
    @DirtiesContext
    public void testNodeAHasRouteToBandC(){
        Node node = nodeRepository.findByName("A");
        Node nodeB = nodeRepository.findByName("B");
        Node nodeC = nodeRepository.findByName("C");
        List<Route> routeList = node.getRouteList();
        Set<String> values = new HashSet<>();
        for (Route route: routeList) {
            if(route.getDestination().getName().equals(nodeB.getName())){
                values.add("B");
            } else if(route.getDestination().getName().equals(nodeC.getName())){
                values.add("C");
            }
        }
        assertEquals(2, values.size());
        assertTrue(values.contains("B"));
        assertTrue(values.contains("C"));
    }

}
