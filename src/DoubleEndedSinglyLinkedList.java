import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

public class DoubleEndedSinglyLinkedList<T>  extends SinglyLinkedDequeue<T> implements List<T> { // T is the generic type

    public DoubleEndedSinglyLinkedList() {
        super("List"); // Ties into class structure
    }

    // List doesn't need to have the stack and queue methods, it is just convenient to have them.
    /**
     * Adds a new element to the first place (head) of the list. (Notes equivalent: addFirst)
     * This is O(1), as it takes roughly the same amount of steps no matter how long the current list is.
     * In an ArrayList, this would be O(n), as we would have to move every element down.
     * In an array backed stack this would also be O(1), because of how they use the array differently.
     *
     * @param value The new element to add
     * @return True if it succeeded
     */
    //public boolean push(T value) { return super.push(value); } // In SinglyLinkedStack

    /**
     * Removes the first element from the list and returns it's value. (Notes equivalent: removeFirst)
     * This is a common operation in stacks and queues, where you often want to take the first element
     * and do something with it.
     * This is O(1), and would likely be O(1) in any serious implementation of a stack or queue,
     * but would be O(n) in ArrayList.
     *
     * @return The value of the first element, or null
     */
    //public T pop() { return super.pop(); } // In SinglyLinkedStack

    /**
     * Returns the value of the first element of the list, without changing the list. (Notes equivalent: getFirst)
     * This is a common operation in stacks and queues, where you often want to look at an element
     * before popping it off.
     * This is O(1).
     *
     * @return The value of the first element, or null
     */
    //public T peek() { return super.peek(); } // In SinglyLinkedStack

    /**
     * Adds a new element to the last place (end or tail) of the list.
     * This is O(n), as we have to take n steps to get to the end of the list.
     * In an ArrayList, this would be O(1), unless we needed more space.
     * Some implementations of linked lists, such as queues, are designed to do this in O(1)
     *
     * @param value The new element to add
     * @return True if it succeeded
     */
    //public boolean append(T value) { super.append(value); } // In SinglyLinkedDequeue

    /**
     * Returns the value of the element at position m.
     * This is what some would call the random access method.
     * It is O(n), as we have to walk on average n/2 steps.
     * In ArrayList it is O(1).
     *
     * @param m The index to access
     * @return The value at the given index, or null
     */
    public T get(int m) {
        traceCall("get("+m+")");

        // Case where there is no head (list is empty)
        if (isHeadless()) {
            endCall("Empty "+type.toLowerCase()+": unable to get anything."); // Ignore these trace methods
            return null;
        }

        // If the index makes no sense, return null
        if (validateIndex(m)) {
            endCall("Invalid index: "+m+"; unable to get it.");
            return null;
        }

        Node<T> node = getNode(m);

        endCall("Got #"+m+": '"+node.value+"'.");
        return node.value; // Return the value stored in element #m
    }

    /**
     * Replaces the value at index m with the given value.
     * This is what some would call the random access method.
     * It is O(n), as we have to walk on average n/2 steps.
     * In ArrayList it is O(1).
     *
     * @param m The index to access
     * @param newValue The new value
     * @return The old value at this position, or null
     */
    public T replace(int m, T newValue) {
        traceCall("replace("+m+", "+newValue+")");

        // Case where there is no head (list is empty)
        if (isHeadless()) {
            endCall("Empty "+type.toLowerCase()+": unable to replace anything."); // Ignore these trace methods
            return null;
        }

        // If the index makes no sense, return null
        if (validateIndex(m)) {
            endCall("Invalid index: "+m+"; unable to replace it.");
            return null;
        }

        Node<T> node = getNode(m);

        // Store and replace the old value
        T oldValue = node.value;
        node.value = newValue;

        endCall("Replaced #"+m+": '"+oldValue+"' with '"+node.value+"'.");
        return oldValue; // Return the old value stored in element m
    }

    /**
     * Adds (inserts) a value at index m, moving all following elements down 1 place.
     * This is what some would call the random insertion method.
     * It is O(n), as we have to walk on average n/2 steps.
     * In ArrayList it is O(n) as you need to shift on average n/2 values.
     *
     * @param m The index to access
     * @param value The new value
     * @return True if succeeded
     */
    public boolean add(int m, T value) {
        traceCall("add("+m+", "+value+")");

        // If the index makes no sense, return null
        if (validateIndex(m)) {
            endCall("Invalid index: "+m+"; unable to add.");
            return false;
        }

        // Make the new node containing the new element
        Node newNode = new Node(value);

        // Case where there is no head (list is empty)
        if (isHeadless()) {
            setEmptyHead(newNode);
            endCall("Empty "+type.toLowerCase()+": "+type.toLowerCase()+" is now '"+value+"'."); // Ignore these trace methods
            return true;
        }

        Node<T> before = addAtIndex(m, newNode);


        endCall("Added #"+m+": '"+newNode.value+"' after '"+before.value+"'.");
        return true;
    }

    /**
     * Removes and returns the value at index m, moving all following elements up 1 place.
     * This is what some would call the random removal method.
     * It is O(n), as we have to walk on average n/2 steps.
     * In ArrayList it is O(n) as you need to shift on average n/2 values.
     *
     * @param m The index to access
     * @return The value at position m, or null
     */
    public T remove(int m) {
        traceCall("remove("+m+")");

        // Case where the list is empty
        if (isHeadless()) {
            endCall("Empty "+type.toLowerCase()+": unable to remove anything.");
            return null;
        }

        // If the index makes no sense, return null
        if (validateIndex(m)) {
            endCall("Invalid index: "+m+"; unable to remove.");
            return null;
        }

        Node<T> oldNode = removeAtIndex(m);

        endCall("Removed #"+m+": '"+oldNode.value+"'.");
        return oldNode.value;
    }

    /**
     * Creates and returns an iterator for this list.
     * Because it is a private internal class, others have to ask us to instantiate the iterator.
     * This gives us control.
     * As ListIterator is a public interface, others can still use the iterator we give out.
     *
     * (We are ignoring a whole lot of issues here regarding concurrency (multiple threads, or multiple iterators)
     *  because that is a topic for another day.)
     *
     * @return An instance of our iterator
     */
    public ListIterator<T> listIterator() {
        traceCall("listIterator()");

        ListIterator<T> iterator = new SinglyLinkedStructureIterator();

        endCall();
        return iterator;
    }

    // Helper methods

    // Used for testing
    @Override
    public void printAll() {
        Node node = head;
        int i = 0;

        while(node != null) {
            System.out.print(i);
            System.out.print(": ");
            System.out.print(node.value);

            if (node == head) System.out.print(" (Head)");
            else if (node.next == null) System.out.print(" (Tail)");
            System.out.println();

            node = node.next;
        }
        System.out.println(type+" size: " + size);

        System.out.println();
    }
}
