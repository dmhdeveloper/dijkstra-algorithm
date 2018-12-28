package za.co.dmh.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import za.co.dmh.core.domain.Node;
import za.co.dmh.core.service.DuplicateNodeException;
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
            path = "/nodes/{nodeName}",
            method = RequestMethod.GET,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<BaseResponse> getNode(String nodeName) {
        try {
            BaseResponse response = service.findNode(nodeName);
            return ResponseEntity.ok(response);
        } catch (NodeNotFoundException e) {
            BaseResponse errorResponse = new BaseResponse();
            errorResponse.setStatus(Status.FAILURE);
            errorResponse.setMessage("Unable to retrieve Node: Node does not exist.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }

    @RequestMapping(
            path = "/nodes/{nodeName}",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<BaseResponse> addNode(@RequestBody Node node) {
        try {
            BaseResponse response = service.addNode(node);
            return ResponseEntity.ok(response);
        } catch (DuplicateNodeException e) {
            BaseResponse errorResponse = new BaseResponse();
            errorResponse.setStatus(Status.FAILURE);
            errorResponse.setMessage("Unable to add Node: Node already exists.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }

    @RequestMapping(
            path = "/nodes/{nodeName}",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<BaseResponse> updateNode(String nodeName, Node node) {
        try {
            BaseResponse response = service.updateNode(nodeName, node);
            return ResponseEntity.ok(response);
        } catch (NodeNotFoundException e) {
            BaseResponse errorResponse = new BaseResponse();
            errorResponse.setStatus(Status.FAILURE);
            errorResponse.setMessage("Unable to update Node: Node does not exist.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        } catch (DuplicateNodeException e) {
            BaseResponse errorResponse = new BaseResponse();
            errorResponse.setStatus(Status.FAILURE);
            errorResponse.setMessage("Unable to add Node: Node already exists.");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
        }
    }

    @RequestMapping(
            path = "/nodes/{nodeName}",
            method = RequestMethod.DELETE,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<BaseResponse> deleteNode(String nodeName) {
        try {
            BaseResponse response = service.deleteNode(nodeName);
            return ResponseEntity.ok(response);
        } catch (NodeNotFoundException e) {
            BaseResponse errorResponse = new BaseResponse();
            errorResponse.setStatus(Status.FAILURE);
            errorResponse.setMessage("Unable to update Node: Node does not exist.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }
}
