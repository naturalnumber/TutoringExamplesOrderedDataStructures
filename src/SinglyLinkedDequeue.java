public class SinglyLinkedDequeue<T> extends SinglyLinkedStack<T> implements Queue<T> {

    public SinglyLinkedDequeue() {
        super("Queue");
    }

    protected SinglyLinkedDequeue(String type) {
        super(type);
    }

    // List doesn't need to have the stack method push, it is just convenient to have them.
    /**
     * Adds a new element to the first place (head) of the queue. (Notes equivalent: addFirst)
     * This is O(1), as it takes roughly the same amount of steps no matter how long the current queue is.
     * In an ArrayList, this would be O(n), as we would have to move every element down.
     * In an array backed stack this would also be O(1), because of how they use the array differently.
     *
     * @param value The new element to add
     * @return True if it succeeded
     */
    //public boolean push(T value) { return super.push(value); } // In SinglyLinkedStack

    /**
     * Removes the first element from the queue and returns it's value. (Notes equivalent: removeFirst)
     * This is a common operation in stacks and queues, where you often want to take the first element
     * and do something with it.
     * This is O(1), and would likely be O(1) in any serious implementation of a stack or queue,
     * but would be O(n) in ArrayList.
     *
     * @return The value of the first element, or null
     */
    //public T pop() { return super.pop(); } // In SinglyLinkedStack

    /**
     * Returns the value of the first element of the queue, without changing the queue. (Notes equivalent: getFirst)
     * This is a common operation in stacks and queues, where you often want to look at an element
     * before popping it off.
     * This is O(1).
     *
     * @return The value of the first element, or null
     */
    //public T peek() { return super.peek(); } // In SinglyLinkedStack

    /**
     * Adds a new element to the last place (end or tail) of the queue.
     * This is O(n), as we have to take n steps to get to the end of the queue.
     * In an ArrayList, this would be O(1), unless we needed more space.
     * Some implementations of linked queues, such as queues, are designed to do this in O(1)
     *
     * @param value The new element to add
     * @return True if it succeeded
     */
    @Override
    public boolean append(T value) {
        traceCall("append("+value+")"); // Ignore these trace methods

        // Make the new node containing the new element
        Node<T> toAppend = new Node<T>(value);

        // Case where there is no head (queue is empty)
        if (isHeadless()) {
            setEmptyHead(toAppend); // Make new top
            endCall("Empty "+type.toLowerCase()+": "+type.toLowerCase()+" is now '"+value+"'."); // Ignore these trace methods
            return true;
        }
        // Case where queue isn't empty
        addAtTail(toAppend); // Make new top

        endCall("Appended: '"+value+"'."); // Ignore these trace methods
        return true;
    }

    // Helper methods

    // Used for testing
    @Override
    public void printAll() {
        Node node = head;

        while(node != null) {
            System.out.print(node.value);

            if (node == head) System.out.print(" (Front/Head)");
            else if (node.next == null) System.out.print(" (End/Tail)");
            System.out.println();

            node = node.next;
        }
        System.out.println(type+" size: " + size);

        System.out.println();
    }
}
