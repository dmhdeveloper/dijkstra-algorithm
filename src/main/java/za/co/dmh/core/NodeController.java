package za.co.dmh.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.dmh.core.domain.Node;
import za.co.dmh.core.domain.Route;
import za.co.dmh.core.service.DuplicateNodeException;
import za.co.dmh.core.service.DuplicateRouteException;
import za.co.dmh.core.service.INodeService;
import za.co.dmh.core.service.NodeNotFoundException;
import za.co.dmh.core.domain.response.BaseResponse;
import za.co.dmh.core.domain.response.Status;

@RestController("/")
public class NodeController {

    @Autowired
    INodeService service;

    @RequestMapping(
            path = "/nodes",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<BaseResponse> getNodes() {
        BaseResponse response = service.findAll();
        return ResponseEntity.ok(response);
    }

    @RequestMapping(
            path = "/nodes/{uniqueId}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<BaseResponse> getNode(@PathVariable(name = "uniqueId") String uniqueId) {
        try {
            BaseResponse response = service.findNode(uniqueId);
            return ResponseEntity.ok(response);
        } catch (NodeNotFoundException e) {
            BaseResponse errorResponse = new BaseResponse();
            errorResponse.setStatus(Status.FAILURE);
            errorResponse.setMessage("Unable to retrieve Node.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }

    @RequestMapping(
            path = "/nodes/{nodeName}",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<BaseResponse> addNode(@PathVariable(name = "nodeName") String nodeName, @RequestBody Node node) {
        try {
            node.setName(nodeName);
            BaseResponse response = service.addNode(node);
            return ResponseEntity.ok(response);
        } catch (DuplicateNodeException e) {
            BaseResponse errorResponse = new BaseResponse();
            errorResponse.setStatus(Status.FAILURE);
            errorResponse.setMessage("Unable to add Node.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }

    @RequestMapping(
            path = "/nodes/{uniqueId}/{nodeName}",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<BaseResponse> updateNode(@PathVariable(name = "uniqueId") String uniqueId, @PathVariable(name = "nodeName") String nodeName) {
        try {
            BaseResponse response = service.updateNode(uniqueId, nodeName);
            return ResponseEntity.ok(response);
        } catch (NodeNotFoundException e) {
            BaseResponse errorResponse = new BaseResponse();
            errorResponse.setStatus(Status.FAILURE);
            errorResponse.setMessage("Unable to update Node.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        } catch (DuplicateNodeException e) {
            BaseResponse errorResponse = new BaseResponse();
            errorResponse.setStatus(Status.FAILURE);
            errorResponse.setMessage("Unable to add Node.");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
        }
    }

    @RequestMapping(
            path = "/nodes/{uniqueId}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<BaseResponse> deleteNode(@PathVariable(name = "uniqueId") String uniqueId) {
        try {
            BaseResponse response = service.deleteNode(uniqueId);
            return ResponseEntity.ok(response);
        } catch (NodeNotFoundException e) {
            BaseResponse errorResponse = new BaseResponse();
            errorResponse.setStatus(Status.FAILURE);
            errorResponse.setMessage("Unable to update Node.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }

    @RequestMapping(
            path = "/nodes/route",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<BaseResponse> createRoute(@RequestBody Route route) {
        try {
            BaseResponse response = service.createRoute(route.getDistance(), route.getSource().getUniqueId(), route.getDestination().getUniqueId());
            return ResponseEntity.ok(response);
        } catch (NodeNotFoundException e) {
            BaseResponse errorResponse = new BaseResponse();
            errorResponse.setStatus(Status.FAILURE);
            errorResponse.setMessage("Unable to create Route.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        } catch (DuplicateRouteException e) {
            BaseResponse errorResponse = new BaseResponse();
            errorResponse.setStatus(Status.FAILURE);
            errorResponse.setMessage("Unable to create Route.");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
        }
    }

    @RequestMapping(
            path = "/nodes/route/{uniqueId}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<BaseResponse> deleteRoute(@PathVariable String uniqueId) {
        return ResponseEntity.ok(service.deleteRoute(uniqueId));
    }

    @RequestMapping(
            path = "/nodes/route/{uniqueId}",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<BaseResponse> updateRoute(@PathVariable String uniqueId, @RequestBody Route route) {
        return ResponseEntity.ok(service.updateRoute(uniqueId, route.getDistance()));
    }
}
