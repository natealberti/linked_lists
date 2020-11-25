package Program_6;

public class List {

    private Link head;
    private Link tail;
    private int size;

    //constructor for empty list
    public List() {
        head = null;
        tail = null;
        size = 0;
    }

    //constructor for list with one item initialized
    public List(Link head) {
        this.head = head;
        tail = head;
        size = 1;
    }

    public Link get(int pos) {
        Link current = head;
        for(int i = 1; i < pos; i++) {
            current = current.next;
        }
        return current;
    }

    //returns size of the list
    public int getSize() {
        return size;
    }

    //inserts an object at a specific position
    public void insert(Object obj, int pos) {

        Link insert = new Link(obj, null);

        //case where list is empty
        if(size==0) {
            head = new Link(null, null);
            head = insert;
            tail = head;
            size++;
        }

        //case where list is only one item, insert at back
        else if(size==1 && pos == 2) {
            head.next = insert;
            tail = head.next;
            size++;
        }

        //case where list is only one item, insert at front
        else if(size==1 && pos == 1) {
            Link holder = head;
            head = insert;
            head.next = holder;
            size++;
        }

        //inserts at a specific spot in the list
        else {
            Link prev = head;
            Link current = head;
            for(int i = 1; i < pos; i++) {
                prev = current;
                current = current.next;
            }
            insert.next = current;
            prev.next = insert;
            size++;
        }
    }

    //inserts at end of the list
    public void insertAtEnd(Object obj) {
        Link insert = new Link(obj, null);

        if(size == 0) {
            head = new Link(null, null);
            tail = new Link(null, null);
            head = insert;
            tail = head;
            size++;
        }

        else {
            tail.next = insert;
            tail = insert;
            size++;
        }
    }

    //deletes a range of links
    public void deleteRange(int start, int end) {

        //if range is the whole list
        if(start == 1 && end == size) {
            head = null;
            tail = null;
            size -= end-start+1;
        }

        //if range starts at the head
        else if(start == 1) {
            Link current = head;
            Link prev = current;
            for(int i = 1; i <= end; i++) {
                current = current.next;
            }
            head = current;
            size -= end-start+1;
        }

        else {
            //getting link corresponding with right bound
            Link last = head;
            for(int i = 1; i <= end; i++) {
                last = last.next;
            }

            //getting link corresponding with left bound
            Link first = head;
            for(int i = 2; i < start; i++) {
                first = first.next;
            }

            //deleting in between the links
            first.next = last;
            size -= end-start+1;

            //resetting tail
            Link current = head;
            for(int i = 1; i < size; i++) {
                current = current.next;
            } tail = current;
        }

    }

    //searches list and deletes the link with specified data
    public void deleteItem(Object obj) {
        Link current = head;
        Link prev = current;
        for(int i = 0; i <= size; i++) {
            //if object is found at current
            if(current.data.equals(obj)) {
                size--;
                //if object is in the head
                if(i == 1) {
                    head = head.next;
                }

                //if object is in the tail
                else if(i == size) {
                    tail = prev;
                    tail.next = null;
                }

                else {
                    prev.next = current.next;
                }

            }
            //bunny hopping
            prev = current;
            current = current.next;
        }
    }

    //returns an item at a specified position
    public Object retrieve(int pos) {
        Link current = head;
        for(int i = 1; i < pos; i++) {
            current = current.next;
        } return current.data;
    }

    //returns the amount of times an object is in the list
    public List find(Object obj) {
        List found = new List();
        Link current = head;
        for(int i = 1; i <= size; i++) {
            if(current.data.equals(obj)) {
                found.insertAtEnd(i);
            }
            current = current.next;
        } return found;
    }

    public String toString() {
        String str = "[";
        Link current = head;
        for(int i = 1; i <= size; i++) {
                str += current.data;
                if(i != size)
                    str += ", ";
                current = current.next;
        }
        return str + "]";
    }

}
