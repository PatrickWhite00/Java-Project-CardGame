public class List<T>
{
    static class Node<T>{
        Node<T> next;
        T data;
        public Node(T data)
        {
            this.data=data;
            this.next=null;
        }
    }

    Node<T> head;
    int length;

    public boolean isEmpty()
    {
        if(this.length==0)
            return true;
        else
            return false;
    }

    public void add(T data)
    {
        Node<T> temp=new Node<>(data);

        // check if list is empty
        if (this.head == null) {
            head = temp;
        }
        // if list exists
        else {
            Node<T> X = head;
            // Iterate the List
            while (X.next != null) {
                X = X.next;
            }
            X.next = temp;
        }
        // Increase the len after adding new node
        length++;
    }

    public void remove(T key)
    {
        Node<T> prev = new Node<>(null);
        prev.next = head;
        Node<T> next = head.next;
        Node<T> temp = head;
        // This will check whether the value is present or not
        boolean exists = false;
        if (head.data == key) {
            head = head.next;
            // Node is present which we will want to remove
            exists = true;
        }
        while (temp.next != null) {
            // Convert the value to be compared to string
            if (String.valueOf(temp.data).equals(String.valueOf(key))) {
                prev.next = next;
                exists = true;
                break;
            }
            prev = temp;
            temp = temp.next;
            next = temp.next;
        }
        if (exists == false && String.valueOf(temp.data).equals(String.valueOf(key))) {
            prev.next = null;
            exists = true;
        }
        // When the node which we want to delete exists
        if (exists) {
            // reduce the len of linked list
            length--;
        }
        // If it does not exist
        else {
            System.out.println("Not found in linked list");
        }
    }

    public void addAt(int pos,T data)
    {
        Node<T> current=head;

//        // Check position if its valid or not
//        if (pos > length + 1) {
//            System.out.println("Position not found");
//            return;
//        }
//        // if new node is to be added in the beginning
//        if (pos == 1) {
//            Node<T> temp = head;
//            head = new Node<T>(data);
//            head.next = temp;
//            return;
//        }
        // Temporary node to store previous head

        Node<T> prev = new Node<T>(null);
        // Interating
        while (current!=null && pos>=0) {
            prev = current;
            current = current.next;
            pos--;
        }
        if(pos>0)
        {
            System.out.println("Error");
        }
        if(prev!=null)
            prev.data=data;
    }
    public int size()
    {
        return length;
    }

    public T get(int pos)
    {
        Node<T> current=head;

        int count=0;
        while (current!=null)
        {
            if(count==pos)
            {
                return current.data;
            }
            count++;
            current = current.next;
        }
        assert (false);
        return null;
    }
}
