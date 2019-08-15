public class SinglyLinkedStack<T> extends SingleEndedSinglyLinkedStructure<T> implements Stack<T> { // T is the generic type

    public SinglyLinkedStack() {
        super("Stack");
    }

    protected SinglyLinkedStack(String type) {
        super(type);
    }

    /**
     * Adds a new element to the top of the stack (head).
     * This is O(1).
     *
     * @param value The new element to add
     * @return True if it succeeded
     */
    @Override
    public boolean push(T value) {
        traceCall("push("+value+")"); // Ignore these trace methods

        // Make the new node containing the new element
        Node<T> toPush = new Node<T>(value);

        // Case where there is no head (stack is empty)
        if (isHeadless()) {
            setEmptyHead(toPush); // Make new top
            endCall("Empty "+type.toLowerCase()+": "+type.toLowerCase()+" is now '"+value+"'."); // Ignore these trace methods
            return true;
        }
        // Case where stack isn't empty
        addAtHead(toPush); // Make new top

        endCall("Pushed: '"+value+"'."); // Ignore these trace methods
        return true;
    }

    /**
     * Removes the element from the top of the stack (head) and returns it's value.
     * This is O(1).
     *
     * @return The value of the top element, or null
     */
    @Override
    public T pop() {
        traceCall("pop()");

        // Case where the stack is empty
        // I choose to return a null value, others may throw an exception
        if (isHeadless()) {
            endCall("Empty "+type.toLowerCase()+": unable to pop anything off.");
            return null;
        }

        Node<T> popped = removeHead();// Remove top

        endCall("Popped off: '"+popped.value+"'.");
        return popped.value;
    }

    /**
     * Returns the value of the top element of the stack (head), without changing the stack.
     * This is O(1).
     *
     * @return The value of the top element, or null
     */
    @Override
    public T peek() {
        traceCall("peek()");

        // If empty, return null
        if (isHeadless()) {
            endCall("Empty "+type.toLowerCase()+": unable to peek at anything.");
            return null;
        }

        Node<T> seen = getHead(); // Get top

        endCall("Peeked at: '"+seen.value+"'.");
        return seen.value;
    }

    // Helper methods

    // Used for testing
    @Override
    public void printAll() {
        Node node = head;

        while(node != null) {
            System.out.print(node.value);

            if (node == head) System.out.print(" (Top/Head)");
            else if (node.next == null) System.out.print(" (Bottom/Tail)");
            System.out.println();

            node = node.next;
        }
        System.out.println(type+" size: " + size);

        System.out.println();
    }
}
