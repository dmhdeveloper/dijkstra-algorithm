package za.co.dmh.core.domain.response;

import za.co.dmh.core.domain.Node;

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
