package algorithm;

import lombok.val;

/**
 * Given a string which contains only lowercase letters,
 * remove duplicate letters so that every letter appears once and only once.
 * You must make sure your result is the smallest in lexicographical order among all possible results.
 * Input: "bcabc"
 * Output: "abc"
 *
 * Input: "cbacdcbc"
 * Output: "acdb"
 */
public class RemoveDuplicateLetter {

    public static void main(String[] args) {
        String input = "cbacdcbc";
        System.out.println(input);
        String s =removeDuplicateLetters(input);
//        System.out.println(s);
    }

    /**
     * 插入填充
     * @param s
     * @return
     *
     * a: 2
     * b: 1,8
     * c: 6
     * d: 3,4,5
     */
    public static String removeDuplicateLetters(String s) {

        byte[] values  =s.getBytes();
        Node head =  new Node();
        Node tail = head;
        int len = 0;
        for(byte value:values){
            Node current = head;
//            System.out.println(value);
//            boolean ending = false;
            while(true){
                if(current.next==null){//已经遍历到结尾
                    if(current.letter!=value){//不等则加入
                        Node newNode = new Node(value);
                        current.next = newNode;
                        newNode.prev = current;
                        tail = newNode;
                        len++;
                    }
                    break;
                }
                if(value==current.letter){//如果遍历到，则跟后面的所有判断，看是不是有比这个小的
                    Node nextNode = current.next;
//                    while(nextNode!=null){
                        if(nextNode.letter<value){//如果后一个字母序比当前小，则移除当前节点，在移动到最后添加
                            current.prev.next = current.next;
                            current.next.prev= current.prev;
                            tail.next = current;
                            current.prev = tail;
                            current.next =null;
                            tail = current;
                            break;
                        }
//                        nextNode = nextNode.next;
//                    }
                    break;
                }
                current = current.next;
            }
//            print(head.next,len);
        }
        Node current = head.next;
        byte[] bytes= new byte[len];
        int index =0;
        while(true){
            bytes[index++] =current.letter;
            current =current.next;
            if(current==null)break;
        }
        return new String(bytes);
    }

    /*public static  void print(Node current,int len){
        byte[] bytes= new byte[len];
        int index =0;
        while(true){
            bytes[index++] =current.letter;
            current =current.next;
            if(current==null)break;
        }
//        System.out.println(new String(bytes));
    }*/
    static class Node{
        public byte letter =0;
        public Node next =null;
        public Node prev =null;
        public Node(){}
        public Node(byte _letter){
            letter =_letter;
        }
    }
}
