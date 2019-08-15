public interface List<T> {
    boolean add(int m, T value);
    T remove(int m);
    T get(int m);

    // Not necessary for lists, but useful.
    T replace(int m, T newValue);
}
