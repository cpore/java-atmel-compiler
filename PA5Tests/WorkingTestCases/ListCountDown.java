import meggy.Meggy;

class ListCountDown {
    public static void main(String[] a){
	    new List().runProgram(3);
    }
}

class List {

    Node head;
    
    public void runProgram( int p ) {
        int x;
        Node iter;
        Node prev;
    
        /* create count down list */
        head = new Node();
        prev = head;
        x = p;
        while ( 0 < x ) {
            iter = new Node();
            prev = prev.init( x, iter);
            iter = iter.initLast( x - 1 );
            prev = iter;
            x = x-1;
        }
        
        /* print list */
        iter = head;
        while ( ! iter.isLast() ) {
            Meggy.setPixel( (byte)(iter.getValue()), (byte)(iter.getValue()),
                        Meggy.Color.BLUE);
            iter = iter.getNext();
        }
        Meggy.setPixel( (byte)(iter.getValue()), (byte)(iter.getValue()),
                        Meggy.Color.VIOLET);

    }
    
}

/* Nodes in a singly-linked list.
 */
class Node {
    int value;
    Node next;
    boolean last;   /* is this the last node in the list? */

    public Node init(int p, Node node) {
        value = p;
        next = node;
        last = false;
        return this;
    }
    public Node initLast(int p) {
        value = p;
        last = true;
        return this;
    }
    public boolean isLast() {
        return last;
    }
    public int getValue() {
        return value;
    }
    public Node getNext() {
        return next;
    }
}