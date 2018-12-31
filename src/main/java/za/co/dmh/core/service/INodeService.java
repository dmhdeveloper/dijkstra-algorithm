package za.co.dmh.core.service;

import za.co.dmh.core.domain.Node;
import za.co.dmh.core.domain.Route;
import za.co.dmh.core.domain.response.BaseResponse;
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
     * @param uniqueId the unique ID of the {@link Node} that is to be deleted.
     * @return a response object including the deleted {@link Node}.
     */
    public NodeListResponse deleteNode(String uniqueId) throws NodeNotFoundException;

    /**
     * Create a new {@link Route} between two {@link Node}.
     *
     * @param distance
     * @param source
     * @param destination
     * @return The response object with the source Node and its Route's.
     * @throws NodeNotFoundException
     * @throws DuplicateRouteException
     */
    public NodeListResponse createRoute(Double distance, String source, String destination) throws NodeNotFoundException, DuplicateRouteException;

    /**
     * Delete a {@link Route}.
     *
     * @param uniqueId
     * @return The response object with the status of the request.
     * @throws NodeNotFoundException
     */
    public BaseResponse deleteRoute(String uniqueId);

    /**
     * Update a {@link Route}.
     *
     * @param uniqueId
     * @return The response object with the status of the request.
     */
    public BaseResponse updateRoute(String uniqueId, Double distance);
}
