import java.util.HashMap;

public class LRUDemo {

    private Node head;
    private Node end;
    // 缓存存储上限
    private int limit;

    private HashMap<String,Node> hashMap;

    public LRUDemo(int limit) {
        this.limit = limit;
        hashMap = new HashMap<>();
    }

    /**
     * 插入数据
     * @param key
     * @param value
     */
    public void put(String key,String value){
        Node node = hashMap.get(key);
        if (node == null){
            // 如果key不存在，则插入key-value
            if (hashMap.size() >= limit){
                String oldKey = removeNode(head);
                hashMap.remove(oldKey);
            }
            node = new Node(key,value);
            addNode(node);
            hashMap.put(key,node);
        } else {
            // 如果key存在，则刷新key-values
            node.value = value;
            refreshNode(node);
        }
    }

    /**
     * 根据key获取value
     * @param key
     * @return
     */
    public String get(String key){
        Node node = hashMap.get(key);
        if (node == null){
            return null;
        }
        refreshNode(node);
        return node.value;
    }

    /**
     * 根据key移除节点
     * @param key
     */
    public void remove(String key){
        Node node = hashMap.get(key);
        if (node == null) {
            return;
        }
        removeNode(node);
        hashMap.remove(key);
    }

    /**
     * 在尾部添加节点
     * @param node
     */
    private void addNode(Node node){
        if (end != null) {
            end.next = node;
            node.pre = end;
            node.next = null;
        }
        end = node;
        if (head == null){
            head = node;
        }
    }

    /**
     * 移除Node
     * @param node
     * @return
     */
    public String removeNode(Node node){
        // 只有一个节点
        if (node == head && node == end){
            head = null;
            end = null;
        } else if(node == end){
            // 移除尾节点
            end =node.pre;
            end.next = null;
        } else if (node == head){
            // 移除头节点
            head = node.next;
            head.pre = null;
        } else {
            // 移除中间节点
            node.pre.next = node.next;
            node.next.pre = node.pre;
        }
        return node.key;
    }

    /**
     * 刷新被访问的Node节点位置
     * @param node
     */
    private void refreshNode(Node node){
        // 如果访问的是尾节点，无须移动节点
        if (node == end){
            return;
        }
        // 移除节点
        removeNode(node);
        // 重新插入节点
        addNode(node);
    }

    private void outNode(Node node){
        while (node != null){
            System.out.print(node.key+":"+node.value+";");
            node = node.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        LRUDemo lru = new LRUDemo(5);
        lru.put("no","chn13123");
        lru.put("name","kenney");
        lru.put("age","18");
        lru.put("address","北京市");
        lru.put("workspace","渣打");
        lru.outNode(lru.head);
        lru.put("tag","it");
        lru.outNode(lru.head);
        lru.get("age");
        lru.outNode(lru.head);
        lru.put("game","ol");
        lru.outNode(lru.head);
        lru.remove("tag");
        lru.outNode(lru.head);
    }
}
