package za.co.dmh.response;

import za.co.dmh.Node;

import java.util.ArrayList;
import java.util.List;

public class NodeListResponse extends BaseResponse {

    private List<Node> nodeList;

    public List<Node> getNodeList() {
        if (nodeList == null) {
            nodeList = new ArrayList<>();
        }
        return nodeList;
    }
}
