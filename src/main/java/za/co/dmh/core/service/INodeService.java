package za.co.dmh.core.service.node;

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
     * Retrieve a {@link Node} by unique ID.
     *
     * @param uniqueId the unique ID of the {@link Node} you want to retrieve.
     * @return a response object including the requested {@link Node}.
     * @throws NodeNotFoundException if the name does not match an existing {@link Node} name.
     */
    public NodeListResponse findNode(String uniqueId) throws NodeNotFoundException;

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
     * @param uniqueId The unique ID of the {@link Node} that is to be updated.
     * @param nodeName The new name of the {@link Node}
     * @return a response object with the {@link Node} that was updated.
     * @throws NodeNotFoundException if the {@link Node} does not exist.
     * @throws DuplicateNodeException if the {@link Node} does exist but has the same state as another {@link Node}.
     */
    public NodeListResponse updateNode(String uniqueId, String nodeName) throws NodeNotFoundException, DuplicateNodeException;

    /**
     * Remove a {@link Node}.
     *
     * @param nodeName the name of the {@link Node} that is to be deleted.
     * @return a response object including the deleted {@link Node}.
     */
    public NodeListResponse deleteNode(String nodeName) throws NodeNotFoundException;
}
