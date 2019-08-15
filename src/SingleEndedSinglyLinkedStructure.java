import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

public abstract class SingleEndedSinglyLinkedStructure<T> extends AbstractLinkedStructure<T> {
    protected Node<T> head = null; // First element, null if empty

    protected SingleEndedSinglyLinkedStructure(String type) {
        super(type);
    }

    /**
     * Empties the list
     *
     * @return True if it succeeded
     */
    public boolean clear() {
        traceCall("clear()"); // Ignore these trace and pad methods

        head = null;
        size = 0;

        trace(type+" is now empty.");

        endCall();
        return true;
    }

    protected boolean validateIndex(int index) {
        return index < 0 || index >= size;
    }

    /**
     * @return Whether or not the collection is empty
     */
    protected boolean isHeadless() {
        //trace(type+"'s head node is null.");

        return head == null;
    }

    /**
     * This sets the provided node as the new head node.
     * This is O(1).
     *
     * @param newNode The new head node.
     */
    protected void setEmptyHead(Node<T> newNode) {
        trace("Setting '"+newNode.value+"' as the new head.");

        head = newNode;

        size++;
    }

    /**
     * This makes the provided node the new head,
     * and sets the old head as the next node.
     * This is O(1) and would require a lot more work in an array backed list.
     *
     * @param newNode The new head node.
     */
    protected void addAtHead(Node<T> newNode) {
        trace("Setting '"+newNode.value+"' as the new head.");

        newNode.next = head; // make the old head the new head's next.
        head = newNode; // make the new node the new head

        size++;
    }

    /**
     * This makes the provided node the new #index
     * This is O(n).
     *
     * @param index The index
     * @param newNode The new node.
     * @return The previous node, for tracing
     */
    protected Node<T> addAtIndex(int index, Node<T> newNode) {
        Node<T> previous = getNode(index-1);

        trace("Setting '"+newNode.value+"' as the new node #"+index+".");

        Node oldNode = previous.next; // Save a reference to the old element m

        previous.next = newNode; // Insert new element at position m

        newNode.next = oldNode; // Place the old element m at position m+1

        size++;

        return previous;
    }

    /**
     * This makes the provided node the new tail.
     * This is O(n), and would be faster in a double ended structure or array structure.
     *
     * @param newNode The new tail node.
     */
    protected void addAtTail(Node<T> newNode) {
        trace("Setting '"+newNode.value+"' as the new tail.");

        Node<T> oldTail = getTail();

        oldTail.next = newNode; // make the new tail the old tail's next.

        size++;
    }

    /**
     * This removes and return the head node,
     * and sets the next node as the new head.
     * (This will null the head if the head had no next.)
     * This is O(1) and would require a lot more work in an array backed list.
     *
     * @return The old head node.
     */
    protected Node<T> removeHead() {
        trace("Removing head node '"+head.value+"'."+((head.next == null) ? type+" is now empty." : " Head is now: "+head.next.value+"."));

        Node<T> temp = head;

        head = head.next; // make the second node the new first node

        size--; // adjust size

        return temp;
    }

    /**
     * This removes the node at the provided index.
     * This is O(n).
     *
     * @param index The index
     * @return The old node, for tracing
     */
    protected Node<T> removeAtIndex(int index) {
        Node<T> previous = getNode(index-1);

        trace("Removing '"+previous.next.value+"' at index #"+index+".");

        Node oldNode = previous.next; // Save a reference to the old element m

        previous.next = oldNode.next; // Remove the element

        size--;

        return oldNode;
    }

    /**
     * This removes the tail node.
     * This is O(n).
     *
     * @return The tail node.
     */
    protected Node<T> removeTail() {
        Node<T> previous = getNodeBeforeTail();

        Node<T> tail = previous.next;

        trace("Removing tail node '"+tail.value+"'. Tail is now: "+previous.value+".");

        previous.next = null; // removes the tail node

        size--; // adjust size

        return tail;
    }

    /**
     * This returns the head node.
     * This is O(1).
     *
     * @return The head node.
     */
    protected Node<T> getHead() {
        return head;
    }

    /**
     * This returns the indexed node.
     * This is O(n).
     *
     * @return The node or null.
     */
    protected Node<T> getNode(int index) {
        subtrace("Beginning the search for node #"+index+"...");

        Node<T> current = head;
        int i = 0;

        // loop through the elements to find # index
        while(current.next != null && i < index) {
            trace("Passing node: #"+(i++)+": '"+current.value+"'.");
            current = current.next; // Move to the next node
        }

        // If we reached the end before the element, return null
        if (current == null) {
            endsub("The end of the "+type+" was reached before #"+index+".");
            return null;
        }

        endsub("Found node #"+i+": '"+current.value+"'.");
        return (i-1 == index) ? current : null;
    }

    /**
     * This returns the tail node.
     * This is O(n).
     *
     * @return The tail node.
     */
    protected Node<T> getTail() {
        subtrace("Beginning the search for the tail node...");

        Node<T> current = head;
        int i = 0;

        // loop through the elements to find the end
        while(current.next != null) {
            trace("Passing node: #"+(i++)+": '"+current.value+"'.");
            current = current.next; // Move to the next node
        }

        endsub("Found the tail, #"+i+": '"+current.value+"'.");
        return current;
    }

    /**
     * This returns the previous to tail node.
     * This is O(n).
     *
     * @return The previous to tail node.
     */
    protected Node<T> getNodeBeforeTail() {
        subtrace("Beginning the search for the before tail node...");

        Node<T> previous = null;
        Node<T> current  = head;
        int     i        = 0;

        // loop through the elements to find the end
        while(current.next != null) {
            trace("Passing node: #"+(i++)+": '"+current.value+"'.");
            previous = current;
            current = current.next; // Move to the next node
        }

        endsub("Found the tail of the structure, #"+i+": '"+current.value+"'.");
        return previous;
    }


    // Helper methods

    // Used for testing
    public void printAll() {
        Node node = head;

        while(node != null) {
            System.out.print(node.value);

            if (node == head) System.out.print(" (Head)");
            else if (node.next == null) System.out.print(" (Tail)");
            System.out.println();

            node = node.next;
        }
        System.out.println(type+" size: " + size);

        System.out.println();
    }

    // Iterator stuff

    protected class SinglyLinkedStructureIterator implements ListIterator<T> {
        private Node<T>    current         = null; // He calls this position
        private Node<T>    previous        = null;
        private boolean previousIsValid = false;

        private int index = -1; // This is not in the notes, it starts at -1 because the next element is 0

        @Override
        public boolean hasNext() {
            traceCall("ListIterator.hasNext()");

            if (current == null) { // If we haven't started, current will still be null
                trace(1, "Haven't started iterating yet, exists first element: " + (head != null));
                pad();

                return head != null; // Our next element is the first element, if it exists.
            }
            else { // Else, if we have started iterating
                trace(1, "Current element has next element: " + (current.next != null));
                pad();

                return current.next != null; // Our next element is the element in current.next, if it exists
            }
        }

        @Override
        public T next() {
            traceCall("ListIterator.next()");
            boolean ignoreThis = toPrint; toPrint = false;

            if (!hasNext()) { // If there is no next element
                throw new NoSuchElementException(); // Throw an exception, I'd probably return null, but exceptions are also common
            }

            toPrint = ignoreThis; // This is just to clean up the trace, ignore this


            if (current == null) { // If we haven't started, current will still be null
                current = head; // Advance to the first node.

                trace(1, "First iteration, new current element #" + (index+1) + ": '" + current.value + "'.");
            }
            else {
                previous = current; // Store the element we are on because we will need it later.
                current = current.next;

                trace(1, "New current element #" + (index+1) + ": '" + current.value + "'.");
            }

            previousIsValid = true; // Set this to true as either previous is valid
            // or we are currently on our head node,
            // which we can remove without using previous.

            index++; // adjust index

            pad();
            return current.value;
        }

        @Override
        public void remove() {
            traceCall("ListIterator.remove()");

            if (!previousIsValid) { // If this is false, we won't be able to remove anything
                throw new IllegalStateException(); // Throw an exception
            }

            if (current == head) { // If we are on the first element, previous will be null
                // but we can still remove, as it is the head
                trace(1, "Removing head element: '" + current.value + "'.");

                //pop(); // We could use our pop method above to remove the first element, like he did

                // But I'd prefer this way:
                head = head.next;
            }
            else {
                trace(1, "Removing element #" + (index) + ": '" + current.value + "', current element #"+(index-1)+": "+previous.value+".");

                previous.next = current.next; // Remove the current element, using our reference to previous
            }

            size--; // adjust size

            index--; // adjust index

            current = previous; // This will point our iterator back one position to previous, or null, if we were on the first element
            previousIsValid = false; // We no longer have a valid previous

            pad();
        }

        @Override
        public void add(T value) {
            traceCall("ListIterator.add("+value+")");

            // Make the new node containing the new element
            Node newNode = new Node(value);

            if (current == null) { // If we haven't started yet, add at the first.
                trace(1, "Adding new head element: '" + value + "'.");


                //push(newValue); // We could use our pop method above to remove the first element, like he did

                // But I'd prefer this way:
                newNode.next = head; // make the old head the new head's next.
                head = newNode; // make the new node the new head

                current = head; // Set our current position to the added element
            }
            else {
                trace(1, "Adding element at index #" + (index) + ": '" + value + "'.");

                newNode.next = current.next; // make the old head the new head's next.
                current.next = newNode; // make the new node the new head

                current = newNode; // Set our current position to the added element
            }

            size++; // adjust size

            index++; // adjust index

            pad();
        }

        @Override
        public void set(T newValue) {
            traceCall("ListIterator.set("+newValue+")");

            if (current == null) { // He has !previousValid, this is better
                throw new IllegalStateException();
            }
            trace(1, "Replacing element '" + current.value + "' at index #" + (index) + " with value '" + newValue + "'.");

            current.value = newValue;

            pad();
        }



        // These aren't in the notes but they are in the interface


        @Override
        public int nextIndex() {
            return index+1;
        }

        @Override
        public int previousIndex() {
            return index-1;
        }



        @Override
        public boolean hasPrevious() {
            return previousIsValid;
        }

        @Override
        public T previous() {
            traceCall("ListIterator.previous()");

            if (!previousIsValid) { // If there is no previous element
                throw new NoSuchElementException(); // Throw an exception, I'd probably return null, but exceptions are also common
            }

            current = previous;

            previousIsValid = false;

            index--; // adjust index

            trace(1, "New current element #" + (index) + ": '" + current.value + "'.");

            pad();
            return current.value;
        }



        @Override
        public void forEachRemaining(Consumer<? super T> consumer) {
            Node<T> temp = current;

            while (temp.next != null) {
                temp = temp.next;

                consumer.accept(temp.value);
            }
        }
    }


    /**
     * The Node class specialization for singly linked structures.
     */
    protected class Node<T> extends AbstractNode<T> {
        protected Node next = null;

        protected Node() {}

        protected Node(T value) {
            super(value);
        }
    }
}
