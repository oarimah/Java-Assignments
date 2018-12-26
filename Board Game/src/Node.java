public class Node {
    public Node next;
    private Configuration val;

    public Node(Configuration value) {
        val = value;
    }

    public void next(Configuration value) {
        next = new Node(value);
    }

    public boolean hasNext() {
        if(next == null) {
            return false;
        } else {
            return true;
        }
    }

    public String getConfiguration() {
        return val.getStringConfiguration();
    }

    public int returnScore() {
        return val.getScore();
    }
}