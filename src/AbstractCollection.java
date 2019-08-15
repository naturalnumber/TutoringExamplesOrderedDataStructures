/**
 * This holds a few methods common to all classes, mostly for testing.
 */
public abstract class AbstractCollection<T> implements Collection<T> {
    public boolean toPrint = false; // This is bad practice, I'm using it for teaching only
    protected int depth = 0;
    protected String spacer = "\t";

    String type;

    protected int size = 0; // List size

    public AbstractCollection(String type) {
        this.type = type;
    }

    // Returns the size of the list, this isn't usually what it is called, I named it this to make it an obvious getter/accessor
    public int getSize() {
        return size;
    }

    // Returns the size of the list, this is the more common name for this function
    public int size() {
        return size;
    }



    // Helper methods for tracing, ignore this
    void trace(Object m) {
        //if (this.toPrint) System.out.println(m);
        if (this.toPrint) {
            for (int i = 0; i < depth; i++) {
                System.out.print(spacer);
            }
            System.out.println(m);
        }
    }
    void trace(int depth, Object m) {
        if (this.toPrint) {
            for (int i = 0; i < depth; i++) {
                System.out.print(spacer);
            }
            System.out.println(m);
        }
    }
    void pad() {
        if (this.toPrint) System.out.println();
    }
    void subtrace(Object m) {
        trace(m);
        this.depth++;
    }
    void endsub() {
        this.depth--;
    }
    void endsub(String m) {
        this.depth--;
        trace(m);
    }
    void traceCall(String m) {
        subtrace("Called: "+m);
    }
    void endCall() {
        endsub();
        pad();
    }
    void endCall(String m) {
        endsub();
        trace(m);
        pad();
    }
}
