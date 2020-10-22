class LRUCache {
    // 双向链表保存节点顺序
    private static class Node {
        int key;
        int value;
        Node pre;
        Node next;

        Node(int key, int value, Node pre, Node next) {
            this.key = key;
            this.value = value;
            this.pre = pre;
            this.next = next;
        }

        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private final HashMap<Integer, Node> map;   // HashMap用于快速定位节点，避免顺序查找
    private final int capacity;
    private final Node dummyHead;               // dummyHead和dummyTail用于避免检查相邻节点是否为null
    private final Node dummyTail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>(capacity);
        this.dummyHead = new Node(-1, -1, null, null);
        this.dummyTail = new Node(-1, -1, null, null);
        dummyHead.next = dummyTail;
    }

    public int get(int key) {
        Node result = map.get(key);
        if (result != null) { moveToHead(result); } // 访问过的数据放到队列头部
        return result == null ? -1 : result.value;
    }

    public void put(int key, int value) {
        // 如果key已存在，则更新value，并将该节点放到队列头部
        if (map.containsKey(key)) { 
            Node node = map.get(key);
            node.value = value;
            moveToHead(node);
        }
        // 如果key不存在，则加入到队列
        else {
            Node newNode = new Node(key, value);
            addToHead(newNode);
            map.put(key, newNode);
            // 如果超出最大容量，则删除末尾的key-value对
            if (map.size() > capacity) {
                Node removed = removeTail();
                map.remove(removed.key);
            }
        }
    }

    private void addToHead(Node node) {
        node.pre = dummyHead;
        node.next = dummyHead.next;
        dummyHead.next.pre = node;
        dummyHead.next = node;
    }

    private void removeNode(Node node) {
        node.pre.next = node.next;
        node.next.pre = node.pre;
    }

    private void moveToHead(Node node) {
        removeNode(node);
        addToHead(node);
    }

    private Node removeTail() {
        Node res = dummyTail.pre;
        removeNode(res);
        return res;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("{");
        Node cur = dummyHead.next;
        for (int i = 0; i < map.size(); i++) {
            result.append(cur.key).append("=").append(cur.value);
            if (i != map.size() - 1) {
                result.append(" ,");
                cur = cur.next;
            }
        }
        result.append("}");
        return result.toString();
    }

    public static void main(String[] args) {
        LRUCache LRU = new LRUCache(2);
        LRU.put(2,1);
        System.out.println(LRU);
        LRU.put(1,1);
        System.out.println(LRU);
        LRU.put(2,3);
        System.out.println(LRU);
        LRU.put(4,1);
        System.out.println(LRU);
        System.out.println(LRU.get(1));
        System.out.println(LRU.get(2));
    }
}
