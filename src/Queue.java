public interface Queue<T> {
    boolean append(T value); // Queue
    T pop(); // Next
    T peek();
}
