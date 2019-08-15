/**
 * Collects some of the common traits to a singly linked structure.
 *
 * @param <T>
 */
//, N extends AbstractLinkedStructure.AbstractNode
public abstract class AbstractLinkedStructure<T> extends AbstractCollection<T> {
    //protected AbstractNode head = null; // First element, null if empty

    public AbstractLinkedStructure(String type) {
        super(type);
    }

    /**
     * The Node class
     */
    abstract class AbstractNode<T> {
        protected T    value = null;

        protected AbstractNode() {}

        protected AbstractNode(T value) {
            this.value = value;
        }
    }
}
