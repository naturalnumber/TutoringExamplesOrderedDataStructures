import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class TesterCollections {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();


        list.add("First");
        list.add("Second");
        list.add("Third");
        list.add("Fourth");
        list.add("Fifth");
        list.add("Sixth");
        list.add("Seventh");

        list.add("First"); // Double add first


        print(list);


        System.out.println("Add element: '3.5' in position 3");
        list.add(3, "3.5");

        System.out.println("Add element: '4.5' in position 5");
        list.add(5, "4.5");

        System.out.println("Removing element: 'Sixth'; Return value: "+list.remove("Sixth"));

        System.out.println("Adding element: 'Eighth'; Return value: "+list.add("Eighth"));

        System.out.println("Setting element 7 to 'Infinity' replacing: '"+list.set(7, "Infinity")+"'");

        System.out.println();


        print(list);


        Stack<String> stack = new Stack<>(); // Usually in Java we would use a deque here as well


        stack.push("First");
        stack.push("Second");
        stack.push("Third");
        stack.push("Fourth");
        stack.push("Fifth");
        stack.push("Sixth");
        stack.push("Seventh");

        stack.push("First"); // Double add first


        print(stack);


        System.out.println("Popping off element: '"+stack.pop()+"'");

        System.out.println("Popping off element: '"+stack.pop()+"'");

        System.out.println("Peeking at element: '"+stack.peek()+"'");

        System.out.println("Popping off element: '"+stack.pop()+"'");

        System.out.println("Pushing element: '"+stack.push("Eighth")+"'");

        System.out.println();


        print(stack);


        Queue<String> queue = new ArrayDeque<>();


        queue.add("First");
        queue.add("Second");
        queue.add("Third");
        queue.add("Fourth");
        queue.add("Fifth");
        queue.add("Sixth");
        queue.add("Seventh");

        queue.add("First"); // Double add first


        print(queue);


        System.out.println("Polling (removing) element: '"+queue.poll()+"'");

        System.out.println("Polling (removing) element: '"+queue.poll()+"'");

        System.out.println("Peeking at element: '"+queue.peek()+"'");

        System.out.println("Removing element: '"+queue.remove()+"'");

        System.out.println("Offering (adding) element: 'Eighth'; Return value: "+queue.offer("Eighth"));

        System.out.println();


        print(queue);


        Set<String> set = new HashSet<>();


        set.add("First");
        set.add("Second");
        set.add("Third");
        set.add("Fourth");
        set.add("Fifth");
        set.add("Sixth");
        set.add("Seventh");

        set.add("First"); // Double add first


        print(set);


        System.out.println("Removing element: 'Second'; Return value: "+set.remove("Second"));

        System.out.println("Removing element: 'Fifth'; Return value: "+set.remove("Fifth"));

        System.out.println("Adding element: 'First' (again); Return value: "+set.add("First"));

        System.out.println("Removing element: 'Eighth'; Return value: "+set.add("Eighth"));

        System.out.println();


        print(set);
    }


    private static void print(List c) {
        // This code prints the whole list, don't focus on this too much yet
        System.out.println("The list: ");
        c.forEach(x -> System.out.println("\t"+x));
        System.out.println("Number of elements: "+c.size());
        System.out.println();
    }

    private static void print(Stack c) {
        // This code prints the whole stack, don't focus on this too much yet
        System.out.println("The stack: ");
        c.forEach(x -> System.out.println("\t"+x));
        System.out.println("Number of elements: "+c.size());
        System.out.println();
    }

    private static void print(Queue c) {
        // This code prints the whole queue, don't focus on this too much yet
        System.out.println("The queue: ");
        c.forEach(x -> System.out.println("\t"+x));
        System.out.println("Number of elements: "+c.size());
        System.out.println();
    }

    private static void print(Set c) {
        // This code prints the whole set, don't focus on this too much yet
        System.out.println("The set: ");
        c.forEach(x -> System.out.println("\t"+x));
        System.out.println("Number of elements: "+c.size());
        System.out.println();
    }
}
