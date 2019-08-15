import java.util.ArrayList;
import java.util.Iterator;

public class Tester {
    public static void main(String[] args) {
        SinglyLinkedList<String> list = new SinglyLinkedList<>();

        list.append("First");
        list.append("Second");
        list.append("Third");
        list.append("Fourth");
        //list.append("Fifth");
        //list.append("Sixth");
        //list.append("Seventh");

        list.printAll();

        list.add(2, "Added");
        list.replace(3, "Replaced");

        list.printAll();

        list.pop();
        list.remove(2);
        list.push("New");

        list.printAll();
    }
}
