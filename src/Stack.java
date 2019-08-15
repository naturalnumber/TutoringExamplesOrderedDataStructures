public interface Stack <T> extends Collection<T> {
    boolean push(T value);
    T pop();
    T peek();
}
