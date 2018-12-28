package za.co.dmh.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import za.co.dmh.core.domain.Node;
import za.co.dmh.core.datalayer.NodeRepository;
import za.co.dmh.core.service.DuplicateNodeException;
import za.co.dmh.core.service.INodeService;
import za.co.dmh.core.service.NodeNotFoundException;
import za.co.dmh.core.domain.response.NodeListResponse;
import za.co.dmh.core.domain.response.Status;

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
    public NodeListResponse findNode(String nodeName) throws NodeNotFoundException {
        throw new IllegalStateException("Not implemented yet.");
    }

    @Override
    public NodeListResponse addNode(Node node) throws DuplicateNodeException {
        NodeListResponse response = new NodeListResponse();
        response.setStatus(Status.SUCCESS);
        nodeRepository.save(node);
        response.getNodeList().add(node);
        return response;
    }

    @Override
    public NodeListResponse updateNode(String nodeName, Node node) throws NodeNotFoundException, DuplicateNodeException {
        throw new IllegalStateException("Not implemented yet.");
    }

    @Override
    public NodeListResponse deleteNode(String nodeName) throws NodeNotFoundException {
        throw new IllegalStateException("Not implemented yet.");
    }
}
