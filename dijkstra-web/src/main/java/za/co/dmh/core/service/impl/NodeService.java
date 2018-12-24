package za.co.dmh.core.service.impl;

import za.co.dmh.Node;
import za.co.dmh.core.service.DuplicateNodeException;
import za.co.dmh.core.service.INodeService;
import za.co.dmh.core.service.NodeNotFoundException;
import za.co.dmh.response.NodeListResponse;

public class NodeService implements INodeService {

    @Override
    public NodeListResponse findAll() {
        throw new IllegalStateException("Not implemented yet.");
    }

    @Override
    public NodeListResponse findNode(String nodeName) throws NodeNotFoundException {
        throw new IllegalStateException("Not implemented yet.");
    }

    @Override
    public NodeListResponse addNode(Node node) throws DuplicateNodeException {
        throw new IllegalStateException("Not implemented yet.");
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
