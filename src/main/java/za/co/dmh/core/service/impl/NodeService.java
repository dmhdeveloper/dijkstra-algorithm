package za.co.dmh.core.service.node.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import za.co.dmh.core.domain.Node;
import za.co.dmh.core.datalayer.NodeRepository;
import za.co.dmh.core.service.node.NodeNotFoundException;
import za.co.dmh.core.service.node.DuplicateNodeException;
import za.co.dmh.core.service.node.INodeService;
import za.co.dmh.core.domain.response.NodeListResponse;
import za.co.dmh.core.domain.response.Status;

import java.time.LocalTime;
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
        NodeListResponse response = new NodeListResponse();
        response.setStatus(Status.SUCCESS);
        Node node = nodeRepository.findByUniqueId(uniqueId);
        response.getNodeList().add(node);
        return response;
    }

    @Override
    public NodeListResponse addNode(Node node) throws DuplicateNodeException {
        NodeListResponse response = new NodeListResponse();
        response.setStatus(Status.SUCCESS);
        if(node.getId() != null){
            throw new DuplicateNodeException("Node already exists.");
        }
        node.setUniqueId(UUID.randomUUID().toString() + LocalTime.now().toNanoOfDay());
        nodeRepository.save(node);
        response.getNodeList().add(node);
        return response;
    }

    @Override
    public NodeListResponse updateNode(String uniqueId, String nodeName) throws NodeNotFoundException, DuplicateNodeException {
        NodeListResponse response = new NodeListResponse();
        response.setStatus(Status.SUCCESS);
        Node existingNode = nodeRepository.findByUniqueId(uniqueId);
        if(existingNode == null){
            throw new NodeNotFoundException(String.format("Node does not exist with unique ID [%s]. Unable to update node.", uniqueId));
        }
        existingNode.setName(nodeName);
        nodeRepository.save(existingNode);
        response.getNodeList().add(existingNode);
        return response;
    }

    @Override
    public NodeListResponse deleteNode(String nodeName) throws NodeNotFoundException {
        NodeListResponse response = new NodeListResponse();
        response.setStatus(Status.SUCCESS);
        Node node = nodeRepository.findByName(nodeName);
        nodeRepository.delete(node);
        response.getNodeList().add(node);
        response.setMessage(String.format("Node with ID: [%d] Deleted.", node.getId()));
        return response;
    }
}
