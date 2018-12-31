package za.co.dmh.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import za.co.dmh.core.domain.Node;
import za.co.dmh.core.datalayer.NodeRepository;
import za.co.dmh.core.domain.Route;
import za.co.dmh.core.domain.response.BaseResponse;
import za.co.dmh.core.service.DuplicateRouteException;
import za.co.dmh.core.service.NodeNotFoundException;
import za.co.dmh.core.service.DuplicateNodeException;
import za.co.dmh.core.service.INodeService;
import za.co.dmh.core.domain.response.NodeListResponse;
import za.co.dmh.core.domain.response.Status;

import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class NodeService implements INodeService {

    @Autowired
    NodeRepository nodeRepository;

    @Override
    public NodeListResponse findAll() {
        NodeListResponse response = new NodeListResponse();
        response.setStatus(Status.SUCCESS);
        response.getNodeList().addAll(nodeRepository.findAll());
        return response;
    }

    @Override
    public NodeListResponse findNode(String uniqueId) throws NodeNotFoundException {
        Node node = nodeRepository.findByUniqueId(uniqueId);
        if(node == null){
            throw new NodeNotFoundException(String.format("Node does not exist with unique ID [%s]. Unable to retrieve node.", uniqueId));
        }
        NodeListResponse response = new NodeListResponse();
        response.setStatus(Status.SUCCESS);
        response.getNodeList().add(node);
        return response;
    }

    @Override
    public NodeListResponse addNode(Node node) throws DuplicateNodeException {
        if(node.getId() != null){
            throw new DuplicateNodeException("Node already exists.");
        }
        NodeListResponse response = new NodeListResponse();
        response.setStatus(Status.SUCCESS);
        node.setUniqueId(UUID.randomUUID().toString() + LocalTime.now().toNanoOfDay());
        nodeRepository.save(node);
        response.getNodeList().add(node);
        return response;
    }

    @Override
    public NodeListResponse updateNode(String uniqueId, String nodeName) throws NodeNotFoundException, DuplicateNodeException {
        Node existingNode = nodeRepository.findByUniqueId(uniqueId);
        if(existingNode == null){
            throw new NodeNotFoundException(String.format("Node does not exist with unique ID [%s]. Unable to update node.", uniqueId));
        }
        NodeListResponse response = new NodeListResponse();
        response.setStatus(Status.SUCCESS);
        existingNode.setName(nodeName);
        nodeRepository.save(existingNode);
        response.getNodeList().add(existingNode);
        return response;
    }

    @Override
    public NodeListResponse deleteNode(String uniqueId) throws NodeNotFoundException {
        Node node = nodeRepository.findByUniqueId(uniqueId);
        if(node == null){
            throw new NodeNotFoundException(String.format("Unable to find Node with unique ID [%s]. Could not delete Node.", uniqueId));
        }
        NodeListResponse response = new NodeListResponse();
        response.setStatus(Status.SUCCESS);
        nodeRepository.delete(node);
        response.getNodeList().add(node);
        response.setMessage(String.format("Node with ID: [%d] Deleted.", node.getId()));
        return response;
    }

    @Override
    public NodeListResponse createRoute(Double distance, String source, String destination) throws NodeNotFoundException, DuplicateRouteException {
        Node sourceNode = nodeRepository.findByUniqueId(source);
        Node destinationNode = nodeRepository.findByUniqueId(destination);
        if(sourceNode == null || destinationNode == null){
            throw new NodeNotFoundException("Source or Destination Node does not exist. Unable to create Route.");
        }
        List<Route> routeList = sourceNode.getRouteList();
        for (Route route: routeList) {
            if((route.getSource().getUniqueId().equals(source) && route.getDestination().getUniqueId().equals(destination)) ||
                    (route.getSource().getUniqueId().equals(destination) && route.getDestination().getUniqueId().equals(source))){
                throw new DuplicateRouteException("Route already exists for Source and Destination Nodes. Unable to create Route.");
            }
        }
        Route route = new Route();
        route.setDistance(distance);
        route.setSource(sourceNode);
        route.setDestination(destinationNode);
        route.setUniqueId(UUID.randomUUID().toString() + LocalTime.now().toNanoOfDay());
        sourceNode.getRouteList().add(route);
        nodeRepository.save(sourceNode);
        NodeListResponse response = new NodeListResponse();
        response.setStatus(Status.SUCCESS);
        response.setMessage(String.format("Route [%s] added.", route.getUniqueId()));
        return response;
    }

    @Override
    public NodeListResponse deleteRoute(String uniqueId) {
        nodeRepository.deleteRoute(uniqueId);
        NodeListResponse response = new NodeListResponse();
        response.setStatus(Status.SUCCESS);
        response.setMessage(String.format("Route [%s] deleted.", uniqueId));
        return response;
    }

    @Override
    public BaseResponse updateRoute(String uniqueId, Double distance) {
        nodeRepository.updateRoute(uniqueId, distance);
        NodeListResponse response = new NodeListResponse();
        response.setStatus(Status.SUCCESS);
        response.setMessage(String.format("Route [%s] updated.", uniqueId));
        return response;
    }
}
