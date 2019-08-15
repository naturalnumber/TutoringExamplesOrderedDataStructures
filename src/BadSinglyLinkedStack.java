public class BadSinglyLinkedStack<T> extends SingleEndedSinglyLinkedStructure<T> implements Stack<T> { // T is the generic type

    public BadSinglyLinkedStack() {
        super("Stack");
    }

    /**
     * Adds a new element to the top of the stack (tail).
     * This is O(n).
     *
     * @param value The new element to add
     * @return True if it succeeded
     */
    @Override
    public boolean push(T value) {
        traceCall("push("+value+")"); // Ignore these trace methods

        // Make the new node containing the new element
        Node<T> toPush = new Node<T>(value);

        // Case where there is no head (list is empty)
        if (isHeadless()) {
            setEmptyHead(toPush); // Make new top
            endCall("Empty stack: stack is now '"+value+"'."); // Ignore these trace methods
            return true;
        }
        // Case where list isn't empty
        addAtTail(toPush); // Make new top

        endCall("Pushed: '"+value+"'."); // Ignore these trace methods
        return true;
    }

    /**
     * Removes the element from the top of the stack (tail) and returns it's value.
     * This is O(n).
     *
     * @return The value of the top element, or null
     */
    @Override
    public T pop() {
        traceCall("pop()");

        Node<T> popped;

        // Case where the stack is empty
        // I choose to return a null value, others may throw an exception
        if (isHeadless()) {
            endCall("Empty stack: unable to pop anything off.");
            return null;
        }
        // Case where head is tail
        else if (size() == 1) {
            popped = removeHead();
        }
        else {
            popped = removeTail();// Remove top
        }

        endCall("Popped off: '"+popped.value+"'.");
        return popped.value;
    }

    /**
     * Returns the value of the top element of the stack (tail), without changing the stack.
     * This is O(n).
     *
     * @return The value of the top element, or null
     */
    @Override
    public T peek() {
        traceCall("peek()");

        // If empty, return null
        if (isHeadless()) {
            endCall("Empty stack: unable to peek at anything.");
            return null;
        }

        Node<T> seen = getTail(); // Get top

        endCall("Peeked at: '"+seen.value+"'.");
        return seen.value;
    }

    // Helper methods

    // Used for testing
    @Override
    public void printAll() {
        String print = "";

        Node node = head;

        while(node != null) {
            print = node.value.toString()+((node == head) ? " (Bottom/Head)\n" : (node.next == null) ? (" (Top/Tail)\n") : "\n")+print;

            node = node.next;
        }
        System.out.print(print);

        System.out.println(type+" size: " + size);

        System.out.println();
    }
}
