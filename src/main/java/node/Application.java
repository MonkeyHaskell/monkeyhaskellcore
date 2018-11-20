package node;

public class Application extends Node {

    private Node leftNode;
    private Node rightNode;

    public Node getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(Node leftNode) {
        this.leftNode = leftNode;
    }

    public Node getRightNode() {
        return rightNode;
    }

    public void setRightNode(Node rightNode) {
        this.rightNode = rightNode;
    }

    public void changeNode(final Node previousNode, final Node newNode) throws NoNodeFoundException {
        if(leftNode == previousNode) {
            leftNode = newNode;
        } else if(rightNode == previousNode) {
            rightNode = newNode;
        } else {
            throw new NoNodeFoundException();
        }
    }
}
