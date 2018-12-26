
public class HashDictionary implements DictionaryADT {
    private Node[] list;
    private int tempSize;
    public HashDictionary(int size) {
        list = new Node[size+1];
        tempSize = size;
    }

    @Override
    public int put(Configuration data) throws DictionaryException {
        //calculate the hash for the data configuration
        int hash = calculateHash(data.getStringConfiguration());
        //if no element exists, add data to the hash position of the node list as the head
        if(list[hash] == null) {
            list[hash] = new Node(data);
            return 0;
        }
        //if a node already exists in position, append the current element to the node list
        Node current = list[hash];
        while(current != null) {
            if(current.getConfiguration().equals(data.getStringConfiguration()) ) {
                throw new DictionaryException("Configuration already in dictionary");
            }
            current = current.next;
        }
        current = new Node(data);
        current.next =  list[hash];
        list[hash] = current;
        return 1;
    }

    @Override
    public void remove(String config) throws DictionaryException {
        int hash = calculateHash(config);

        if(list[hash] != null) {
            Node current = list[hash];
            Node prev = null;
            Node next = null;
            while(current != null) {
                next = current.next;
                if(current.getConfiguration().equals(config)) {
                    if(prev == null) {
                        current = next;
                    } else {
                        current = next;
                        prev.next = current;
                    }
                    return;
                }
                prev = current;
                current = current.next;
            }
            throw new DictionaryException("The item you're trying to delete does not exist");
        } else {
            throw new DictionaryException("The item you're trying to delete does not exist");
        }
    }

    @Override
    public int getScore(String config) {
        int hash = calculateHash(config);

        if(list[hash] != null) {
            //traverse through linkedlist to check if the configuration exists
            Node current = list[hash];
            while(current != null) {
                if(current.getConfiguration().equals(config)) {
                    return current.returnScore();
                }
                current = current.next;
            }
        }
        return -1;
    }

    private int calculateHash(String config) {
        int hash = 0;
        int R = 31;

        for(int i = 0; i<config.length();i++) {
            hash = (R * hash + config.charAt(i)) % tempSize;
        }
        return hash;
    }

}