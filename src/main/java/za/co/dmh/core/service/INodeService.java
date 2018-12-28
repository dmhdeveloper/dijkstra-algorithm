package za.co.dmh.core.service;

import za.co.dmh.core.domain.Node;
import za.co.dmh.core.domain.response.NodeListResponse;

/**
 * Service for CRUD operations for Nodes.
 */
public interface INodeService {

    /**
     * Retrieve all {@link Node} objects.
     *
     * @return a response object including a list of all nodes.
     */
    public NodeListResponse findAll();

    /**
     * Retrieve a {@link Node} by name.
     *
     * @param nodeName the name of the {@link Node} you want to retrieve.
     * @return a response object including the requested {@link Node}.
     * @throws NodeNotFoundException if the name does not match an existing {@link Node} name.
     */
    public NodeListResponse findNode(String nodeName) throws NodeNotFoundException;

    /**
     * Add a new one if the {@link Node} does not exist.
     *
     * @param node a new {@link Node} to be added.
     * @return a response object including the node added or updated.
     * @throws DuplicateNodeException if the {@link Node} exists already.
     */
    public NodeListResponse addNode(Node node) throws DuplicateNodeException;

    /**
     * Update an existing {@link Node}.
     *
     * @param nodeName The name of the {@link Node} that is to be updated.
     * @param node The {@link Node} that contains the updated variables.
     * @return a response object with the {@link Node} that was updated.
     * @throws NodeNotFoundException if the {@link Node} does not exist.
     * @throws DuplicateNodeException if the {@link Node} does exist but has the same state as another {@link Node}.
     */
    public NodeListResponse updateNode(String nodeName, Node node) throws NodeNotFoundException, DuplicateNodeException;

    /**
     * Remove a {@link Node}.
     *
     * @param nodeName the name of the {@link Node} that is to be deleted.
     * @return a response object including the deleted {@link Node}.
     */
    public NodeListResponse deleteNode(String nodeName) throws NodeNotFoundException;
}
